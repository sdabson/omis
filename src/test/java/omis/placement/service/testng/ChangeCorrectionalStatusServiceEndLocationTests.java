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
package omis.placement.service.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.domain.ZipCode;
import omis.address.exception.ZipCodeExistsException;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.country.domain.Country;
import omis.country.exception.CountryExistsException;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.location.domain.Location;
import omis.location.exception.LocationExistsException;
import omis.location.service.delegate.LocationDelegate;
import omis.locationterm.domain.LocationReason;
import omis.locationterm.domain.LocationTerm;
import omis.locationterm.exception.LocationReasonExistsException;
import omis.locationterm.exception.LocationReasonTermExistsException;
import omis.locationterm.exception.LocationTermExistsException;
import omis.locationterm.service.delegate.LocationReasonDelegate;
import omis.locationterm.service.delegate.LocationReasonTermDelegate;
import omis.locationterm.service.delegate.LocationTermDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.organization.domain.Organization;
import omis.organization.exception.OrganizationExistsException;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.placement.service.ChangeCorrectionalStatusService;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.exception.CityExistsException;
import omis.region.exception.StateExistsException;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.DateUtility;
import omis.util.PropertyValueAsserter;

/**
 * Tests for end location method of service to change correctional statuses.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Dec 20, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"placement", "service"})
public class ChangeCorrectionalStatusServiceEndLocationTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("organizationDelegate")
	private OrganizationDelegate organizationDelegate;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("cityDelegate")
	private CityDelegate cityDelegate;
	
	@Autowired
	@Qualifier("zipCodeDelegate")
	private ZipCodeDelegate zipCodeDelegate;
	
	@Autowired
	@Qualifier("addressDelegate")
	private AddressDelegate addressDelegate;
	
	@Autowired
	@Qualifier("locationDelegate")
	private LocationDelegate locationDelegate;
	
	@Autowired
	@Qualifier("locationTermDelegate")
	private LocationTermDelegate locationTermDelegate;
	
	@Autowired
	@Qualifier("locationReasonDelegate")
	private LocationReasonDelegate locationReasonDelegate;
	
	@Autowired
	@Qualifier("locationReasonTermDelegate")
	private LocationReasonTermDelegate locationReasonTermDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("changeCorrectionalStatusService")
	private ChangeCorrectionalStatusService changeCorrectionalStatusService;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("dateUtility")
	private DateUtility dateUtility;
	
	/* Tests. */
	
	/**
	 * Tests ending of location term.
	 * 
	 * @throws ZipCodeExistsException if ZIP code exists
	 * @throws CityExistsException if city exists
	 * @throws StateExistsException if State exists
	 * @throws CountryExistsException if country exists
	 * @throws OrganizationExistsException if organization exists
	 * @throws LocationExistsException if location exists
	 * @throws LocationTermExistsException if location term exists
	 */
	@Test(description = "Succeeds in ending location term")
	public void testSuccess()
			throws ZipCodeExistsException,
				CityExistsException,
				StateExistsException,
				CountryExistsException,
				OrganizationExistsException,
				LocationExistsException,
				LocationTermExistsException {
		
		// Arranges offender with an open, existing location term
		Offender offender = this.createOffender();
		Address address = this.addressDelegate.findOrCreate(
				"1000 1000TH ST 1000", null, null, BuildingCategory.HOUSE,
				this.createZipCode("59602", null,
						this.createCityInState("Helena",
								this.createMontana(this.createUsa()))));
		Organization prison = this.organizationDelegate.create(
				"Prison", "PRSN", null);
		Location prisonLocation = this.locationDelegate.create(
				prison, null, address);
		Date locationTermStartDate
			= this.dateUtility.parseDateText("12/12/2012");
		this.locationTermDelegate.create(offender, prisonLocation,
				locationTermStartDate, null, false, null);
		
		// Action - ends location term
		Date locationTermEndDate = this.dateUtility.parseDateText("12/12/2018");
		this.changeCorrectionalStatusService.endLocation(
				offender, locationTermEndDate);
		
		// Asserts that end date was updated
		LocationTerm locationTerm = this.locationTermDelegate
				.findByOffenderOnDate(offender, locationTermStartDate);
		PropertyValueAsserter.create()
			.addExpectedValue("dateRange.endDate", locationTermEndDate)
			.performAssertions(locationTerm);
	}
	
	/**
	 * Tests ending of location reason term.
	 * 
	 * @throws LocationReasonTermExistsException if location reason term exists
	 * @throws LocationReasonExistsException if location reason exists
	 * @throws LocationTermExistsException if location term exists
	 * @throws LocationExistsException if location exists
	 * @throws OrganizationExistsException if organization exists
	 * @throws ZipCodeExistsException if ZIP code exists
	 * @throws CityExistsException if city exists
	 * @throws StateExistsException if State exists
	 * @throws CountryExistsException if country exists
	 */
	@Test(description = "Succeeds in ending location reason term")
	public void testSuccessWithReason()
			throws LocationReasonTermExistsException,
				LocationReasonExistsException,
				LocationTermExistsException,
				LocationExistsException,
				OrganizationExistsException,
				ZipCodeExistsException,
				CityExistsException,
				StateExistsException,
				CountryExistsException {
		
		// Arranges offender with an open, existing location term and reason
		Offender offender = this.createOffender();
		Address address = this.addressDelegate.findOrCreate(
				"2000 2000TH ST", null, null, BuildingCategory.HOUSE,
				this.createZipCode("59602", null,
						this.createCityInState("Helena",
								this.createMontana(this.createUsa()))));
		Organization jail = this.organizationDelegate.create(
				"Jail", "JL", null);
		Location jailLocation = this.locationDelegate.create(
				jail, null, address);
		Date locationTermStartDate
			= this.dateUtility.parseDateText("12/12/2013");
		LocationTerm jailTerm = this.locationTermDelegate.create(
				offender, jailLocation, locationTermStartDate, null, false,
				null);
		LocationReason pendingNewCharges = this.locationReasonDelegate
				.create("Pending New Charges", (short) 1, true);
		this.locationReasonTermDelegate.create(jailTerm,
				new DateRange(locationTermStartDate, null),
				pendingNewCharges);
		
		// Action - ends location
		Date endDate = this.dateUtility.parseDateText("12/12/2018");
		this.changeCorrectionalStatusService.endLocation(offender, endDate);
		
		// Asserts that reason term was ended
		PropertyValueAsserter.create()
			.addExpectedValue("dateRange.endDate", endDate)
			.performAssertions(this.locationReasonTermDelegate
					.findForOffenderOnDate(offender, locationTermStartDate));
	}
	
	/* Helper methods. */
	
	// Returns offender
	private Offender createOffender() {
		return this.offenderDelegate.createWithoutIdentity(
				"Blofeld", "Julius", null, "X");
	}
	
	// Returns USA
	private Country createUsa() throws CountryExistsException {
		return this.countryDelegate.create(
				"United States of America", "USA", true);
	}
	
	// Returns Montana
	private State createMontana(final Country usa) throws StateExistsException {
		return this.stateDelegate.create("Montana", "MT", usa, true, true);
	}
	
	// Returns city
	private City createCityInState(final String name, final State state)
			throws CityExistsException {
		return this.cityDelegate.create(name, true, state, state.getCountry());
	}
	
	// Returns ZIP code
	private ZipCode createZipCode(
			final String value, final String extension, final City city)
				throws ZipCodeExistsException {
		return this.zipCodeDelegate.create(city, value, extension, true);
	}
}