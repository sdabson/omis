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
package omis.offenseterm.service.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.address.exception.ZipCodeExistsException;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.conviction.domain.Conviction;
import omis.conviction.domain.OffenseSeverity;
import omis.conviction.domain.component.ConvictionFlags;
import omis.conviction.exception.ConvictionExistsException;
import omis.conviction.service.delegate.ConvictionDelegate;
import omis.country.domain.Country;
import omis.country.exception.CountryExistsException;
import omis.country.service.delegate.CountryDelegate;
import omis.court.domain.Court;
import omis.court.domain.CourtCategory;
import omis.court.service.delegate.CourtDelegate;
import omis.courtcase.domain.Charge;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.exception.ChargeExistsException;
import omis.courtcase.exception.CourtCaseExistsException;
import omis.courtcase.service.delegate.ChargeDelegate;
import omis.courtcase.service.delegate.CourtCaseDelegate;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.docket.service.delegate.DocketDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.location.exception.LocationExistsException;
import omis.location.service.delegate.LocationDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.offense.domain.Offense;
import omis.offense.domain.OffenseClassification;
import omis.offense.service.delegate.OffenseDelegate;
import omis.offenseterm.service.OffenseTermService;
import omis.organization.domain.Organization;
import omis.organization.exception.OrganizationExistsException;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.exception.CityExistsException;
import omis.region.exception.StateExistsException;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.sentence.domain.LegalDispositionCategory;
import omis.sentence.domain.Sentence;
import omis.sentence.domain.SentenceCategory;
import omis.sentence.domain.TermRequirement;
import omis.sentence.domain.component.SentenceConnection;
import omis.sentence.exception.ConnectedSentenceExistsException;
import omis.sentence.exception.SentenceExistsException;
import omis.sentence.service.delegate.LegalDispositionCategoryDelegate;
import omis.sentence.service.delegate.SentenceCategoryDelegate;
import omis.sentence.service.delegate.SentenceDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.DateUtility;

/**
 * Tests for remove method of offense term service.
 * 
 * @author Stephen Abson
 * @since OMIS 3.0
 * @version 0.1.0 (Feb 22, 2019)
 */
@Test(groups = {"service", "offenseTerm"})
public class OffenseTermServiceRemoveTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Service. */
	
	@Autowired
	@Qualifier("offenseTermService")
	private OffenseTermService offenseTermService;
	
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
	@Qualifier("courtDelegate")
	private CourtDelegate courtDelegate;
	
	@Autowired
	@Qualifier("docketDelegate")
	private DocketDelegate docketDelegate;
	
	@Autowired
	@Qualifier("courtCaseDelegate")
	private CourtCaseDelegate courtCaseDelegate;
	
	@Autowired
	@Qualifier("offenseDelegate")
	private OffenseDelegate offenseDelegate;
	
	@Autowired
	@Qualifier("convictionDelegate")
	private ConvictionDelegate convictionDelegate;
	
	@Autowired
	@Qualifier("chargeDelegate")
	private ChargeDelegate chargeDelegate;
	
	@Autowired
	@Qualifier("sentenceCategoryDelegate")
	private SentenceCategoryDelegate sentenceCategoryDelegate;
	
	@Autowired
	@Qualifier("legalDispositionCategoryDelegate")
	private LegalDispositionCategoryDelegate legalDispositionCategoryDelegate;
	
	@Autowired
	@Qualifier("sentenceDelegate")
	private SentenceDelegate sentenceDelegate;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("dateUtility")
	private DateUtility dateUtility;
	
	/* Unit tests. */
	
	/**
	 * Tests removal of court case.
	 * 
	 * @throws DuplicateEntityFoundException if court exists (switch to entity
	 * specific exception)
	 * @throws LocationExistsException if location exists 
	 * @throws ZipCodeExistsException if ZIP code exists
	 * @throws CityExistsException if city exists
	 * @throws StateExistsException if State exists
	 * @throws CountryExistsException if country exists
	 * @throws OrganizationExistsException if organization exists
	 * @throws CourtCaseExistsException if court case exists
	 * @throws DocketExistsException if docket exists
	 * @throws ConnectedSentenceExistsException if attempt is made to remove
	 * court case with a sentence that another sentence on another court case
	 * is consecutive to
	 */
	public void test()
			throws OrganizationExistsException,
				CountryExistsException,
				StateExistsException,
				CityExistsException,
				ZipCodeExistsException,
				LocationExistsException,
				DuplicateEntityFoundException,
				DocketExistsException,
				CourtCaseExistsException,
				ConnectedSentenceExistsException {
		
		// Arranges offender with court case
		Offender offender = this.createOffender();
		Court court = this.createCourt();
		CourtCase courtCase = this.createCourtCase(
				offender, court, "DC 1000 12");
		
		// Action - removes court case
		this.offenseTermService.remove(courtCase);
		
		// Asserts court case was removed
		assert this.courtCaseDelegate.findByDefendant(offender).size() == 0
				: "Court cases exist for offender";
	}
	
	/**
	 * Tests removal of court case with conviction.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities are found
	 * @throws CourtCaseExistsException if court case exists
	 * @throws DocketExistsException if docket exists
	 * @throws LocationExistsException if location exists
	 * @throws ZipCodeExistsException if ZIP code exists
	 * @throws CityExistsException if city exists
	 * @throws StateExistsException if State exists
	 * @throws CountryExistsException if country exists
	 * @throws OrganizationExistsException if organization exists
	 * @throws ConvictionExistsException if conviction exists
	 * @throws ConnectedSentenceExistsException if sentences exists with
	 * connections to other sentences
	 */
	public void testWithConviction()
			throws OrganizationExistsException, CountryExistsException,
				StateExistsException, CityExistsException,
				ZipCodeExistsException, LocationExistsException,
				DocketExistsException, CourtCaseExistsException,
				DuplicateEntityFoundException, ConvictionExistsException,
				ConnectedSentenceExistsException {
		
		// Arranges offender with court case and conviction
		Offender offender = this.createOffender();
		CourtCase courtCase = this.createCourtCase(
				offender, this.createCourt(), "DC 11 111");
		this.createConviction(courtCase,
				this.createOffense("Theft", "Theft", "DC 11111"),
				this.dateUtility.parseDateText("12/12/2012"), 1);
		
		// Action - removes court case
		this.offenseTermService.remove(courtCase);
		
		// Asserts court case was removed
		assert this.courtCaseDelegate.findByDefendant(offender).size() == 0
				: "Court case was not removed";
	}
	
	/**
	 * Tests removal of court case with charge.
	 * 
	 * @throws ConnectedSentenceExistsException if court case has sentence
	 * to which another sentence is consecutively connected
	 * @throws DuplicateEntityFoundException if duplicate entities exist
	 * @throws CourtCaseExistsException if court case exists
	 * @throws DocketExistsException if docket exists
	 * @throws LocationExistsException if location exists
	 * @throws ZipCodeExistsException if ZIP code exists
	 * @throws CityExistsException if city exists
	 * @throws StateExistsException if State exists
	 * @throws CountryExistsException if country exists
	 * @throws OrganizationExistsException if organization exists
	 * @throws ChargeExistsException if charge exists
	 */
	public void testWithCharge()
			throws ConnectedSentenceExistsException,
				OrganizationExistsException, CountryExistsException,
				StateExistsException, CityExistsException,
				ZipCodeExistsException, LocationExistsException,
				DocketExistsException, CourtCaseExistsException,
				DuplicateEntityFoundException, ChargeExistsException {
		
		// Arranges offender with court case and conviction
		Offender offender = this.createOffender();
		CourtCase courtCase = this.createCourtCase(
				offender, this.createCourt(), "DC 11111");
		this.createCharge(
				courtCase, this.createOffense("Theft", "Theft", "THEFT1"),
				this.dateUtility.parseDateText("12/12/2013"), 3);
		
		// Action - removes court case
		this.offenseTermService.remove(courtCase);
		
		// Asserts court case was removed
		assert this.courtCaseDelegate.findByDefendant(offender).size() == 0
				: "Court case was not removed";
	}
	
	/**
	 * Tests that {@code ConnectedSentenceExistsException} is thrown
	 * when an attempt is made to remove court case with a sentence that
	 * another sentence on another court case is consecutive to.
	 * @throws DuplicateEntityFoundException if duplicate entities exist
	 * @throws LocationExistsException if location exists
	 * @throws ZipCodeExistsException if ZIP code exists
	 * @throws CityExistsException if city exists
	 * @throws StateExistsException if State exists
	 * @throws CountryExistsException if country exists
	 * @throws OrganizationExistsException if organization exists
	 * @throws CourtCaseExistsException if court case exists
	 * @throws DocketExistsException if docket exists
	 * @throws ConvictionExistsException if conviction exists
	 * @throws SentenceExistsException if sentence exists
	 * @throws ConnectedSentenceExistsException if attempt is made to remove
	 * court case with a sentence that another sentence on another court case
	 * is consecutive to - asserted
	 */
	@Test(expectedExceptions = {ConnectedSentenceExistsException.class})
	public void testConnectedSentenceExistsException()
			throws OrganizationExistsException, CountryExistsException,
				StateExistsException, CityExistsException,
				ZipCodeExistsException, LocationExistsException,
				DuplicateEntityFoundException, DocketExistsException,
				CourtCaseExistsException, ConvictionExistsException,
				SentenceExistsException, ConnectedSentenceExistsException {
		
		// Arranges offender with two court cases, each with a sentence
		// One sentence is consecutive to the other
		Offender offender = this.createOffender();
		Court court = this.createCourt();
		CourtCase firstCourtCase = this.createCourtCase(
				offender, court, "DC 11 1111");
		Offense firstOffense = this.createOffense("Theft", "Theft", "THEFT-1");
		Integer firstCounts = 1;
		Date firstDate = this.dateUtility.parseDateText("12/12/2012");
		Conviction firstConviction = this.createConviction(firstCourtCase,
				firstOffense, firstDate, firstCounts);
		SentenceCategory category = this.createSentenceCategory();
		Date firstEffectiveDate = this.dateUtility.parseDateText("12/24/2012");
		LegalDispositionCategory legalDispositionCategory
			= this.createLegalDispositionCategory();
		Sentence firstSentence = this.createSentence(firstConviction, category,
				legalDispositionCategory, firstEffectiveDate,
				SentenceConnection.createInitial());
		CourtCase secondCourtCase = this.createCourtCase(
				offender, court, "DC 222 222");
		Offense secondOffense = this.createOffense(
				"Homocide", "Homocide", "HOMOCIDE-1");
		Integer secondCounts = 1;
		Date secondDate = this.dateUtility.parseDateText("12/12/2013");
		Conviction secondConviction = this.createConviction(
				secondCourtCase, secondOffense, secondDate, secondCounts);
		Date secondEffectiveDate = this.dateUtility.parseDateText("12/24/2013");
		this.createSentence(secondConviction, category,
				legalDispositionCategory, secondEffectiveDate,
				SentenceConnection.createConsecutive(firstSentence));
		
		// Attempts to remove court case with initial sentence to which
		// consecutive sentences exist
		this.offenseTermService.remove(firstCourtCase);
	}
	
	/* Helper methods. */
	
	// Returns offender
	private Offender createOffender() {
		return this.offenderDelegate.createWithoutIdentity(
				"Le Chiffre", "Julius", null, null);
	}
	
	// Returns court
	private Court createCourt()
			throws OrganizationExistsException,
				CountryExistsException,
				StateExistsException,
				CityExistsException,
				ZipCodeExistsException,
				LocationExistsException,
				DuplicateEntityFoundException {
		Organization organization = this.organizationDelegate.create(
				"City Court", "CCRT", null);
		Country country = this.countryDelegate.create(
				"United States", "US", true);
		State state = this.stateDelegate.create(
				"Montana", "MT", country, true, true);
		City city = this.cityDelegate.create("Helena", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(
				city, "59602", null, true);
		Address address = this.addressDelegate.findOrCreate(
				"1000 1000ST 1000 APT 1000", null, null, null, zipCode);
		Location location = this.locationDelegate.create(
				organization, null, address);
		return this.courtDelegate.create("City Court", CourtCategory.CITY,
				location);
	}
	
	// Returns court case
	private CourtCase createCourtCase(
			final Offender offender, final Court court,
			final String docketValue)
					throws DocketExistsException, CourtCaseExistsException {
		Docket docket = this.docketDelegate.create(
				offender, court, docketValue);
		return this.courtCaseDelegate.create(
				docket, null, null, null, null, null, null, null, null, null);
	}
	
	// Returns offense
	private Offense createOffense(final String name, final String shortName,
			final String violationCode)
			throws DuplicateEntityFoundException {
		return this.offenseDelegate.create(
				name, shortName, null, OffenseClassification.PERSON,
				violationCode, true);
	}
	
	// Returns conviction
	private Conviction createConviction(final CourtCase courtCase,
			final Offense offense, final Date date, final Integer counts)
					throws ConvictionExistsException {
		return this.convictionDelegate.create(
				courtCase, OffenseSeverity.FELONY, offense, date, counts,
				new ConvictionFlags(true, false, true, false));
	}
	
	// Returns charge
	private Charge createCharge(
			final CourtCase courtCase, final Offense offense,
			final Date date, final Integer counts)
					throws ChargeExistsException {
		return this.chargeDelegate.create(
				courtCase, offense, date, null, counts);
	}
	
	// Returns sentence category
	private SentenceCategory createSentenceCategory()
			throws DuplicateEntityFoundException {
		return this.sentenceCategoryDelegate.create(
				"Prison", TermRequirement.REQUIRED, TermRequirement.OPTIONAL,
				TermRequirement.NOT_ALLOWED);
	}
	
	// Returns legal disposition category
	private LegalDispositionCategory createLegalDispositionCategory()
			throws DuplicateEntityFoundException {
		return this.legalDispositionCategoryDelegate.create("Original", true);
	}
	
	// Returns sentence
	private Sentence createSentence(
			final Conviction conviction, final SentenceCategory category,
			final LegalDispositionCategory legalDispositionCategory,
			final Date effectiveDate, final SentenceConnection connection)
					throws SentenceExistsException {
		return this.sentenceDelegate.create(conviction, connection, null, null,
				null, category, null, legalDispositionCategory, effectiveDate,
				null, null, null, true, 0);
	}
}