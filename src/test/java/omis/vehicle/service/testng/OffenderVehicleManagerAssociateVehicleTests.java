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
import omis.vehicle.exception.VehicleAssociationExistsException;
import omis.vehicle.service.OffenderVehicleManager;

/**
 * Test associating vehicle
 *
 * @author Yidong Li
 * @version 0.0.1 (Dec. 18, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"vehicle", "service"})
public class OffenderVehicleManagerAssociateVehicleTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
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
	 */
	@Test
	public void testAssociationVehicle()
		throws VehicleAssociationExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity(
			"lastName", "firstName", "middleName", "Mr.");
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2018"), null);
		
		Country country = this.countryDelegate.findOrCreate("United States", 
			"US", true);
		State state = this.stateDelegate.findOrCreate("Montana", "MT", country, 
			true, true);
		
		// Action
		VehicleAssociation vehicleAssociation = this.offenderVehicleManager
			.associateVehicle(offender, null, null, 2018, "ownerDescription",
			dateRange, null, "123456789", state, null);
		
		// Assertion
		PropertyValueAsserter.create()
		.addExpectedValue("offender", vehicleAssociation.getOffender())
		.addExpectedValue("dateRange", vehicleAssociation.getDateRange())
		.addExpectedValue("year", vehicleAssociation.getYear())
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
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2018"), null);
				
		Country country = this.countryDelegate.findOrCreate("United States", 
			"US", true);
		State state = this.stateDelegate.findOrCreate("Montana", "MT", country, 
			true, true);
		
		this.offenderVehicleManager.associateVehicle(offender, null, null, 2018,
			"ownerDescription", dateRange, null, "123456789", state, null);
		
		// Action
		this.offenderVehicleManager.associateVehicle(offender, null, null, 2018,
			"ownerDescription",	dateRange, null, "123456789", state, null);
	}	
		
	// Parse date text
	private Date parseDateText(final String dateText) {
		PropertyEditor propertyEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		propertyEditor.setAsText(dateText);
		return (Date) propertyEditor.getValue();
	}
}
