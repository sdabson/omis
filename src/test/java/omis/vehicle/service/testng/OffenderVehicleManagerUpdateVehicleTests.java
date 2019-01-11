/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.vehicle.service.testng;

import java.beans.PropertyEditor;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.audit.AuditComponentRetriever;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.region.domain.State;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;
import omis.vehicle.domain.VehicleAssociation;
import omis.vehicle.domain.VehicleColor;
import omis.vehicle.domain.VehicleMake;
import omis.vehicle.domain.VehicleModel;
import omis.vehicle.exception.VehicleAssociationExistsException;
import omis.vehicle.exception.VehicleColorExistsException;
import omis.vehicle.exception.VehicleMakeExistsException;
import omis.vehicle.exception.VehicleModelExistsException;
import omis.vehicle.service.OffenderVehicleManager;
import omis.vehicle.service.delegate.VehicleColorDelegate;
import omis.vehicle.service.delegate.VehicleLicenseDelegate;
import omis.vehicle.service.delegate.VehicleMakeDelegate;
import omis.vehicle.service.delegate.VehicleModelDelegate;
import omis.vehicle.service.delegate.VehicleOwnerAssociationDelegate;

/**
 * Test updating vehicle association
 *
 * @author Yidong Li
 * @version 0.0.1 (Dec. 19, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"vehicle", "service"})
public class OffenderVehicleManagerUpdateVehicleTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Instance factory */
	
	/* Delegates. */
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("auditComponentRetriever")
	private AuditComponentRetriever auditComponentRetriever;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	@Autowired
	@Qualifier("vehicleModelDelegate")
	private VehicleModelDelegate vehicleModelDelegate;
	
	@Autowired
	@Qualifier("vehicleMakeDelegate")
	private VehicleMakeDelegate vehicleMakeDelegate;
	
	@Autowired
	@Qualifier("vehicleColorDelegate")
	private VehicleColorDelegate vehicleColorDelegate;
	
	@Autowired
	@Qualifier("vehicleOwnerAssociationDelegate")
	private VehicleOwnerAssociationDelegate vehicleOwnerAssociationDelegate;
	
	@Autowired
	@Qualifier("vehicleLicenseDelegate")
	private VehicleLicenseDelegate vehicleLicenseDelegate;
		
	/* Service to test. */
	@Autowired
	@Qualifier("offenderVehicleManager")
	private OffenderVehicleManager offenderVehicleManager;
	
	/* Property editor factories. */
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Test methods. */
	
	/**
	 * Test creating vehicle association
	 * @throws VehicleAssociationExistsException vehicle association already exists  
	 * @throws VehicleMakeExistsException 
	 * @throws VehicleModelExistsException 
	 * @throws VehicleColorExistsException 
	 */
	@Test
	public void testVehicleAssociationUpdate()
		throws VehicleAssociationExistsException, VehicleMakeExistsException,
		VehicleModelExistsException, VehicleColorExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity(
			"lastName", "firstName", "middleName", "Mr.");
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2018"), null);
		
		Country country = this.countryDelegate.findOrCreate("United States", 
			"US", true);
		State state = this.stateDelegate.findOrCreate("Montana", "MT", country, 
			true, true);
		
		VehicleMake vehicleMake = this.vehicleMakeDelegate.create(
			"Vehicle maker", true);
		VehicleModel vehicleModel = this.vehicleModelDelegate.create(
			vehicleMake, "Vehicle model", true);
		VehicleColor vehicleColor = this.vehicleColorDelegate.create(
			"Vehicle color", true);
		
		
		VehicleAssociation vehicleAssociation = this.offenderVehicleManager
			.associateVehicle(offender, vehicleModel, vehicleColor, 2018,
			"ownerDescription",	dateRange, null, "123456789", state, vehicleMake);
		
		DateRange updatedDateRange = new DateRange(this.parseDateText("12/19/2018"), 
			null);
		VehicleMake updatedVehicleMake = this.vehicleMakeDelegate.create(
			"Updated vehicle maker", true);
		VehicleModel updatedVehicleModel = this.vehicleModelDelegate.create(
			vehicleMake, "Updated vehicle model", true);
		VehicleColor updatedVehicleColor = this.vehicleColorDelegate.create(
			"Updated vehicle color", true);
		Integer updatedYear = 2019;
		String updatedComments = "Updated comments";
		State updatedState = this.stateDelegate.findOrCreate("Indiana", "IN",
			country, true, true);
		String updatedPlateNumber = "987654321";
		String updatedOwnerDescription = "Updated owner description";
		Offender updatedOffender = this.offenderDelegate.createWithoutIdentity(
				"UpdatedlastName", "UpdatedFirstName", "UpdatedMiddleName", "Ms.");
		
		
				
		// Action
		this.offenderVehicleManager.updateVehicle(vehicleAssociation,
			updatedVehicleModel, updatedVehicleColor, updatedYear,
			updatedDateRange, updatedComments, updatedVehicleMake, updatedState,
			updatedPlateNumber, updatedOwnerDescription, updatedOffender);
		
		// Assertion
		PropertyValueAsserter.create()
		.addExpectedValue("offender", vehicleAssociation.getOffender())
		.addExpectedValue("dateRange", vehicleAssociation.getDateRange())
		.addExpectedValue("year", vehicleAssociation.getYear())
		.addExpectedValue("vehicleModel", vehicleAssociation.getVehicleModel())
		.addExpectedValue("vehicleMake", vehicleAssociation.getVehicleMake())
		.addExpectedValue("vehicleColor", vehicleAssociation.getVehicleColor())
		.performAssertions(vehicleAssociation);
	}
	
	/**
	 * Tests {@code VehicleAssociationExistsException} is thrown.
	 * 
	 * @throws VehicleAssociationExistsException if vehicle association already 
	 * exists
	 */
	@Test(expectedExceptions = {VehicleAssociationExistsException.class})
	public void testVehicleAssociationExistsException() 
		throws VehicleAssociationExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity(
			"lastName", "firstName", "middleName", "Mr.");
		DateRange dateRangeOne = new DateRange(this.parseDateText("01/01/2018"),
			null);
		Country country = this.countryDelegate.findOrCreate("United States", 
			"US", true);
		State stateOne = this.stateDelegate.findOrCreate("Montana", "MT", country, 
			true, true);
		VehicleAssociation vehicleAssociationOne = this.offenderVehicleManager
			.associateVehicle(offender, null, null, null, "ownerDescription",
			dateRangeOne, null, "123456789", stateOne, null);
		DateRange dateRangeTwo = new DateRange(this.parseDateText("11/01/2018"),
			null);
		State stateTwo = this.stateDelegate.findOrCreate("Missouri", "MO", country, 
			true, true);
		this.offenderVehicleManager
			.associateVehicle(offender, null, null, null, "ownerDescription",
			dateRangeTwo, null, "987654321", stateTwo, null);
		
		// Action
		this.offenderVehicleManager.updateVehicle(vehicleAssociationOne, null,
		null, null, dateRangeTwo, null, null, stateTwo, "987654321",
		"ownerDescription", offender);
	}	
		
	// Parse date text
	private Date parseDateText(final String dateText) {
		PropertyEditor propertyEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		propertyEditor.setAsText(dateText);
		return (Date) propertyEditor.getValue();
	}
}
