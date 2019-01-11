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
package omis.presentenceinvestigation.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.court.domain.Court;
import omis.court.domain.CourtCategory;
import omis.court.service.delegate.CourtDelegate;
import omis.datatype.DateRange;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.docket.service.delegate.DocketDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.location.service.delegate.LocationDelegate;
import omis.organization.domain.Organization;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.presentenceinvestigation.domain.PresentenceInvestigationCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationDocketAssociation;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.PresentenceInvestigationRequestService;
import omis.presentenceinvestigation.service.delegate.PresentenceInvestigationCategoryDelegate;
import omis.presentenceinvestigation.service.delegate.PresentenceInvestigationDocketAssociationDelegate;
import omis.presentenceinvestigation.service.delegate.PresentenceInvestigationRequestDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create presentence investigation docket associations.
 *
 * @author Josh Divine
 * @version 0.1.0 (Oct 25, 2018)
 * @since OMIS 3.0
 */
@Test
public class PresentenceInvestigationRequestServiceCreatePresentenceInvestigationDocketAssociationTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	private PresentenceInvestigationRequestDelegate 
			presentenceInvestigationRequestDelegate;

	@Autowired
	private DocketDelegate docketDelegate;

	@Autowired
	private PersonDelegate personDelegate;
	
	@Autowired
	private UserAccountDelegate userAccountDelegate;
	
	@Autowired
	private PresentenceInvestigationCategoryDelegate
			presentenceInvestigationCategoryDelegate;
	
	@Autowired
	private CourtDelegate courtDelegate;
	
	@Autowired
	private LocationDelegate locationDelegate;
	
	@Autowired
	private OrganizationDelegate organizationDelegate;
	
	@Autowired
	private AddressDelegate addressDelegate;
	
	@Autowired
	private CountryDelegate countryDelegate;
	
	@Autowired
	private StateDelegate stateDelegate;
	
	@Autowired
	private CityDelegate cityDelegate;
	
	@Autowired
	private ZipCodeDelegate zipCodeDelegate;
	
	@Autowired
	private PresentenceInvestigationDocketAssociationDelegate 
			presentenceInvestigationDocketAssociationDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("presentenceInvestigationRequestService")
	private PresentenceInvestigationRequestService 
			presentenceInvestigationRequestService;

	/* Test methods. */

	/**
	 * Tests the method to create a presentence investigation docket association.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket already exists
	 */
	@Test
	public void testCreatePresentenceInvestigationDocketAssociation() 
			throws DuplicateEntityFoundException, DocketExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Wayne", "Bruce", "Alen", 
				null);
		Person user = this.personDelegate.create("Grayson", "Richard", "J", 
				null);
		UserAccount userAccount = this.userAccountDelegate.create(user, 
				"ROBIN34", "password1", this.parseDateText("12/12/2299"), 0, 
				true);
		PresentenceInvestigationCategory category = this
				.presentenceInvestigationCategoryDelegate.create("PSI Category", 
						true);
		PresentenceInvestigationRequest presentenceInvestigationRequest = this
				.presentenceInvestigationRequestDelegate.create(
						userAccount, this.parseDateText("01/01/2016"),
						this.parseDateText("12/31/2017"), person,  
						this.parseDateText("03/25/2015"), category, 
						this.parseDateText("04/01/2017"));
		Organization organization = this.organizationDelegate.create("Batcave", 
				"TBC", null);
		Country country = this.countryDelegate.create("Country", "USA", true);
		State state = this.stateDelegate.create("State", "ST", country, true, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city, "12345", null, true);
		Address address = this.addressDelegate.findOrCreate("123value", null,
				null, null, zipCode);
		Location location = this.locationDelegate.create(organization,
				new DateRange(this.parseDateText("01/01/2001"),
						this.parseDateText("01/01/2020")), address);
		Court court = this.courtDelegate.create("Court Of Justice!",
				CourtCategory.CITY, location);
		Docket docket = this.docketDelegate.create(person, court, "Docket");

		// Action
		PresentenceInvestigationDocketAssociation 
			presentenceInvestigationDocketAssociation = this
				.presentenceInvestigationRequestService
				.createPresentenceInvestigationDocketAssociation(
						presentenceInvestigationRequest, docket);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("presentenceInvestigationRequest", 
					presentenceInvestigationRequest)
			.addExpectedValue("docket", docket)
			.performAssertions(presentenceInvestigationDocketAssociation);
	}

	/**
	 * Tests that {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket already exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException, DocketExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Wayne", "Bruce", "Alen", 
				null);
		Person user = this.personDelegate.create("Grayson", "Richard", "J", 
				null);
		UserAccount userAccount = this.userAccountDelegate.create(user, 
				"ROBIN34", "password1", this.parseDateText("12/12/2299"), 0, 
				true);
		PresentenceInvestigationCategory category = this
				.presentenceInvestigationCategoryDelegate.create("PSI Category", 
						true);
		PresentenceInvestigationRequest presentenceInvestigationRequest = this
				.presentenceInvestigationRequestDelegate.create(
						userAccount, this.parseDateText("01/01/2016"),
						this.parseDateText("12/31/2017"), person,  
						this.parseDateText("03/25/2015"), category, 
						this.parseDateText("04/01/2017"));
		Organization organization = this.organizationDelegate.create("Batcave", 
				"TBC", null);
		Country country = this.countryDelegate.create("Country", "USA", true);
		State state = this.stateDelegate.create("State", "ST", country, true, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city, "12345", null, true);
		Address address = this.addressDelegate.findOrCreate("123value", null,
				null, null, zipCode);
		Location location = this.locationDelegate.create(organization,
				new DateRange(this.parseDateText("01/01/2001"),
						this.parseDateText("01/01/2020")), address);
		Court court = this.courtDelegate.create("Court Of Justice!",
				CourtCategory.CITY, location);
		Docket docket = this.docketDelegate.create(person, court, "Docket");
		this.presentenceInvestigationDocketAssociationDelegate.create(
				presentenceInvestigationRequest, docket);
		
		// Action
		this.presentenceInvestigationRequestService
			.createPresentenceInvestigationDocketAssociation(
					presentenceInvestigationRequest, docket);
	}
	
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
}