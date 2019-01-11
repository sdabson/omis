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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.country.domain.Country;
import omis.country.exception.CountryExistsException;
import omis.country.service.delegate.CountryDelegate;
import omis.region.exception.StateExistsException;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.vehicle.service.OffenderVehicleManager;

/**.
 * Test finding states
 *
 * @author Yidong Li
 * @version 0.0.1 (Dec. 27, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"vehicle", "service"})
public class OffenderVehicleManagerFindStatesTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Instance factory */
	
	/* Delegates. */
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	/* Service to test. */
	@Autowired
	@Qualifier("offenderVehicleManager")
	private OffenderVehicleManager offenderVehicleManager;
	
	/* Test methods. */
	
	/**
	 * Test finding all states
	 * 
	 * @throws CountryExistsException 
	 * @throws StateExistsException
	 */
	@Test
	public void testFindingStates() throws CountryExistsException,
		StateExistsException {
		// Arrangements
		Country country = this.countryDelegate.create("United State of America",
			"US", true);
		this.stateDelegate.create("Montana", "MT", country, true, true);
		this.stateDelegate.create("Indiana", "IN", country, true, true);
		
		// Action
		Integer count = this.offenderVehicleManager.findStates().size();
		
		// Assertion
		assert 2 == count
		: String.format("Wrong state number: #%d found; #%d expected",
		count, 2);
	}
}