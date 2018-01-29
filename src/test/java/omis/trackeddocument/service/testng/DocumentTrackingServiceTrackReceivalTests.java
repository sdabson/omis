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
package omis.trackeddocument.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.domain.ZipCode;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.court.domain.Court;
import omis.court.domain.CourtCategory;
import omis.court.service.delegate.CourtDelegate;
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
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.trackeddocument.domain.TrackedDocumentCategory;
import omis.trackeddocument.domain.TrackedDocumentReceival;
import omis.trackeddocument.exception.TrackedDocumentReceivalExistsException;
import omis.trackeddocument.service.DocumentTrackingService;
import omis.trackeddocument.service.delegate.TrackedDocumentCategoryDelegate;
import omis.trackeddocument.service.delegate.TrackedDocumentReceivalDelegate;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tracked document test.
 * 
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Dec 21, 2017)
 * @since OMIS 3.0
 */

public class DocumentTrackingServiceTrackReceivalTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	@Autowired
	@Qualifier("docketDelegate")
	private DocketDelegate docketDelegate;
		
	@Autowired
	@Qualifier("trackedDocumentCategoryDelegate")
	private TrackedDocumentCategoryDelegate 
		trackedDocumentCategoryDelegate;
	
	@Autowired
	@Qualifier("trackedDocumentReceivalDelegate")
	private TrackedDocumentReceivalDelegate trackedDocumentReceivalDelegate;
	
	@Autowired
	@Qualifier("userAccountDelegate")
	private UserAccountDelegate userAccountDelegate;	
	
	@Autowired
	@Qualifier("documentTrackingService")
	private DocumentTrackingService documentTrackingService;	
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("courtDelegate")
	private CourtDelegate courtDelegate;
	
	@Autowired
	@Qualifier("locationDelegate")
	private LocationDelegate locationDelegate;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	@Autowired
	@Qualifier("cityDelegate")
	private CityDelegate cityDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("zipCodeDelegate")
	private ZipCodeDelegate zipCodeDelegate;
	
	@Autowired
	@Qualifier("organizationDelegate")
	private OrganizationDelegate organizationDelegate;
	
	@Autowired
	@Qualifier("addressDelegate")
	private AddressDelegate addressDelegate;

	/**
	 * Test tracked document receival method trackReceival.
	 *
	 *
	 * @throws DuplicateEntityFoundException duplicate entityh found exception
	 * @throws DocketExistsException docket exists exception
	 */
	@Test
	public void testTrackReceival() 
		  throws DuplicateEntityFoundException, DocketExistsException {
		//Arrangements
		final Person person = this.personDelegate.create("LName", "FName", 
		  "MName", null);
		final CourtCategory courtCategory = CourtCategory.COUNTY;
		final Court court = this.courtDelegate.create(
				  "L & C", courtCategory, null);
		final Docket docket = this.docketDelegate.create(
				person, court, "value");
		final TrackedDocumentCategory category 
		  	= this.trackedDocumentCategoryDelegate.create("categoryName", true);
		final Date receivedDate = this.parseDateText("08/15/2016");
		final UserAccount receivedByUserAccount = this.userAccountDelegate
				  .findByUsername("UserName");
	  
		//Action
		TrackedDocumentReceival trackedDocumentReceival 
			= this.documentTrackingService.trackReceival(
					docket, category, receivedDate, receivedByUserAccount);
		  
		//Assertions
		PropertyValueAsserter.create()
		  	.addExpectedValue("docket", docket)
		  	.addExpectedValue("category", category)
		  	.addExpectedValue("receivedDate", receivedDate)
		  	.addExpectedValue("receivedByUserAccount", receivedByUserAccount)
		  	.performAssertions(trackedDocumentReceival);
	  
	}

	/**
	 * Test tracked document receival exists exception.
	 *
	 *
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 * @throws DocketExistsException docket exists exception
	 */
	@Test(expectedExceptions = {TrackedDocumentReceivalExistsException.class})
	public void testTrackedDocumentReceivalExistsException() 
			  throws DuplicateEntityFoundException, DocketExistsException {
		//Arrangements
		final Person person = this.personDelegate.create("LName", "FName", 
				"MName", null);
		final CourtCategory courtCategory = CourtCategory.COUNTY;
		final Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		final State mt = this.stateDelegate.create(
				"Montana", "MT", unitedStates, true, true);
		final City helena = this.cityDelegate.create("Helena",
				true, mt, unitedStates);
		final ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		final Address courtAddress = this.addressDelegate.findOrCreate(
				"1000", null, null, BuildingCategory.APARTMENT,
				mt59602);
		final Organization courtOrg = this.organizationDelegate
				.create("Some Court", "SC", null);
		final Location courtLocation = this.locationDelegate.create(
				courtOrg, null, courtAddress);
		final Court court = this.courtDelegate.create(
				"L & C", courtCategory, courtLocation);
		final Docket docket = this.docketDelegate.create(
				person, court, "value");
		final TrackedDocumentCategory category 
		  	= this.trackedDocumentCategoryDelegate.create("categoryName", true);
		final Date receivedDate = this.parseDateText("08/15/2016");
		final UserAccount receivedByUserAccount = this.userAccountDelegate
			.create(person, "USERNAME", "password1", new Date(), 
					(Integer) 0, true);
		this.trackedDocumentReceivalDelegate.trackReceival(
			  docket, category, receivedDate, receivedByUserAccount);
			  
		//Action
		this.documentTrackingService.trackReceival(
  			docket, category, receivedDate, receivedByUserAccount);
	}

	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
}