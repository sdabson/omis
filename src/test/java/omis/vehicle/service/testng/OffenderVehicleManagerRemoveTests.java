/*
 * OMIS - Offender Management Information System.
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.region.domain.State;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.vehicle.dao.VehicleAssociationDao;
import omis.vehicle.domain.VehicleAssociation;
import omis.vehicle.exception.VehicleAssociationExistsException;
import omis.vehicle.service.OffenderVehicleManager;

/**
 * Vehicle association remove tests.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 20, 2018)
 * @since OMIS 3.0
 */
public class OffenderVehicleManagerRemoveTests
extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Data access objects. */
	@Autowired
	@Qualifier("vehicleAssociationDao")
	private VehicleAssociationDao vehicleAssociationDao;
	
	/* Delegates */
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	/* Service. */
	@Autowired
	@Qualifier("offenderVehicleManager")
	private OffenderVehicleManager offenderVehicleManager;
	
	/* Constructor. */
	
	/**
	 * Instantiates an vehicle association service remove tests.
	 */
	public OffenderVehicleManagerRemoveTests() {
		//Default constructor.
	}
	
	/**
	 * Tests the removal of a vehicle association.
	 * @throws VehicleAssociationExistsException 
	 */
	@Test
	public void testRemove() throws VehicleAssociationExistsException {
		// Arrangement
		Offender offender = this.offenderDelegate.createWithoutIdentity(
			"lastName", "firstName", "middleName", "Mr.");
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2018"),
			null);
		
		Country country = this.countryDelegate.findOrCreate("United States", 
			"US", true);
		State state = this.stateDelegate.findOrCreate("Montana", "MT", country, 
			true, true);
		
		// Action
		VehicleAssociation vehicleAssociation = this.offenderVehicleManager
			.associateVehicle(offender, null, null, 2018, "ownerDescription",
			dateRange, null, "123456789", state, null);
				
		//Action
		this.offenderVehicleManager.remove(vehicleAssociation);
		
		//Assertion
		assert !this.vehicleAssociationDao.findAll().contains(vehicleAssociation)
			: "Vehicle association was not eremoved.";
	}
	
	/* Helper methods */
	
	/*
	 * Parses the specified string and returns a {@code Date} object
	 * representing the parsed {@code String}.
	 *  
	 * @param text text to parse
	 * @return date 
	 */
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}
}