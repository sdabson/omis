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
package omis.courtdocument.service.testng;

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
import omis.courtdocument.domain.CourtDocumentAssociation;
import omis.courtdocument.domain.CourtDocumentCategory;
import omis.courtdocument.service.CourtDocumentAssociationService;
import omis.courtdocument.service.delegate.CourtDocumentAssociationDelegate;
import omis.courtdocument.service.delegate.CourtDocumentCategoryDelegate;
import omis.datatype.DateRange;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.docket.service.delegate.DocketDelegate;
import omis.document.domain.Document;
import omis.document.service.delegate.DocumentDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.location.service.delegate.LocationDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.organization.domain.Organization;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create court document associations.
 *
 * @author Josh Divine
 * @version 0.1.0 (Aug 9, 2018)
 * @since OMIS 3.0
 */
@Test
public class CourtDocumentAssociationServiceCreateCourtDocumentAssociationTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	private DocketDelegate docketDelegate;

	@Autowired
	private OffenderDelegate offenderDelegate;

	@Autowired
	private DocumentDelegate documentDelegate;

	@Autowired
	private CourtDocumentCategoryDelegate courtDocumentCategoryDelegate;

	@Autowired
	private CourtDocumentAssociationDelegate courtDocumentAssociationDelegate;

	@Autowired
	private CourtDelegate courtDelegate;

	@Autowired
	private LocationDelegate locationDelegate;
	
	@Autowired
	private OrganizationDelegate organizationDelegate;
	
	@Autowired
	private AddressDelegate addressDelegate;
	
	@Autowired
	private ZipCodeDelegate zipCodeDelegate;
	
	@Autowired
	private CityDelegate cityDelegate;
	
	@Autowired
	private StateDelegate stateDelegate;
	
	@Autowired
	private CountryDelegate countryDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("courtDocumentAssociationService")
	private CourtDocumentAssociationService courtDocumentAssociationService;

	/* Test methods. */

	/**
	 * Tests the creation of court document associations.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket already exists
	 */
	@Test
	public void testCreateCourtDocumentAssociation() 
			throws DuplicateEntityFoundException, DocketExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		Organization organization = this.organizationDelegate.create("Org", "O", 
				null);
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "ST", country, false, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city, "12345", null, true);
		Address address = this.addressDelegate.findOrCreate("123 Easy St.", 
				null, null, null, zipCode);
		Location location = this.locationDelegate.create(organization, 
				new DateRange(this.parseDateText("01/01/2000"), null), address);
		Court court = this.courtDelegate.create("Court", CourtCategory.CITY, 
				location);
		Docket docket = this.docketDelegate.create(offender, court, 
				"DC-1234-12");
		Date date = this.parseDateText("08/01/2018");
		Document document = this.documentDelegate.create(date, "Filename", 
				"ext", "Title");
		CourtDocumentCategory category = this.courtDocumentCategoryDelegate
				.create("Category", true);

		// Action
		CourtDocumentAssociation courtDocumentAssociation = this
				.courtDocumentAssociationService.createCourtDocumentAssociation(
						docket, offender, document, date, category);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("docket", docket)
			.addExpectedValue("offender", offender)
			.addExpectedValue("document", document)
			.addExpectedValue("date", date)
			.addExpectedValue("category", category)
			.performAssertions(courtDocumentAssociation);
	}

	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket already exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException, DocketExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		Organization organization = this.organizationDelegate.create("Org", "O", 
				null);
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "ST", country, false, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city, "12345", null, true);
		Address address = this.addressDelegate.findOrCreate("123 Easy St.", 
				null, null, null, zipCode);
		Location location = this.locationDelegate.create(organization, 
				new DateRange(this.parseDateText("01/01/2000"), null), address);
		Court court = this.courtDelegate.create("Court", CourtCategory.CITY, 
				location);
		Docket docket = this.docketDelegate.create(offender, court, 
				"DC-1234-12");
		Date date = this.parseDateText("08/01/2018");
		Document document = this.documentDelegate.create(date, "Filename", 
				"ext", "Title");
		CourtDocumentCategory category = this.courtDocumentCategoryDelegate
				.create("Category", true);
		this.courtDocumentAssociationDelegate.create(docket, offender, document, 
				date, category);

		// Action
		this.courtDocumentAssociationService.createCourtDocumentAssociation(
				docket, offender, document, date, category);
	}

	// Parses date text
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}
}