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
import omis.address.service.delegate.AddressUnitDesignatorDelegate;
import omis.address.service.delegate.StreetSuffixDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.conviction.domain.Conviction;
import omis.conviction.domain.OffenseSeverity;
import omis.conviction.domain.component.ConvictionCredit;
import omis.conviction.domain.component.ConvictionFlags;
import omis.conviction.exception.ConvictionExistsException;
import omis.conviction.service.delegate.ConvictionDelegate;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.court.domain.Court;
import omis.court.domain.CourtCategory;
import omis.court.service.delegate.CourtDelegate;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.domain.JurisdictionAuthority;
import omis.courtcase.domain.OffenderDangerDesignator;
import omis.courtcase.domain.component.CourtCaseFlags;
import omis.courtcase.domain.component.CourtCasePersonnel;
import omis.courtcase.exception.CourtCaseExistsException;
import omis.courtcase.service.delegate.ChargeDelegate;
import omis.courtcase.service.delegate.CourtCaseDelegate;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.docket.service.delegate.DocketDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.location.service.delegate.LocationDelegate;
import omis.offense.domain.Offense;
import omis.offense.domain.OffenseClassification;
import omis.offense.service.delegate.OffenseDelegate;
import omis.offenseterm.service.OffenseTermService;
import omis.organization.domain.Organization;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.sentence.domain.LegalDispositionCategory;
import omis.sentence.domain.Sentence;
import omis.sentence.domain.SentenceCategory;
import omis.sentence.domain.SentenceConnectionClassification;
import omis.sentence.domain.SentenceLengthClassification;
import omis.sentence.domain.TermRequirement;
import omis.sentence.domain.component.SentenceConnection;
import omis.sentence.exception.SentenceExistsException;
import omis.sentence.service.delegate.LegalDispositionCategoryDelegate;
import omis.sentence.service.delegate.SentenceCategoryDelegate;
import omis.term.domain.component.Term;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests offense term service.
 *
 * @author Josh Divine 
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test
public class OffenseTermServiceSentenceTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("locationDelegate")
	private LocationDelegate locationDelegate;
	
	@Autowired
	@Qualifier("organizationDelegate")
	private OrganizationDelegate organizationDelegate;
	
	@Autowired
	@Qualifier("addressDelegate")
	private AddressDelegate addressDelegate;
	
	@Autowired
	@Qualifier("streetSuffixDelegate")
	private StreetSuffixDelegate streetSuffixDelegate;
	
	@Autowired
	@Qualifier("addressUnitDesignatorDelegate")
	private AddressUnitDesignatorDelegate addressUnitDesignatorDelegate;
	
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
	@Qualifier("courtDelegate")
	private CourtDelegate courtDelegate;
	
	@Autowired
	@Qualifier("courtCaseDelegate")
	private CourtCaseDelegate courtCaseDelegate;
	
	@Autowired
	@Qualifier("docketDelegate")
	private DocketDelegate docketDelegate;
	
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
	@Qualifier("legalDispositionCategoryDelegate")
	private LegalDispositionCategoryDelegate legalDispositionCategoryDelegate;
	
	@Autowired
	@Qualifier("sentenceCategoryDelegate")
	private SentenceCategoryDelegate sentenceCategoryDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("offenseTermService")
	private OffenseTermService offenseTermService;
	
	/**
	 * Tests the creation of a sentence.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 * @throws SentenceExistsException if sentence exists
	 */
	@Test
	public void testSentence() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException, ConvictionExistsException, 
			SentenceExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		Country unitedStates = this.countryDelegate.create("United States", 
				"US", true);
		State mt = this.stateDelegate.create("Montana", "MT", unitedStates, 
				true, true);
		City helena = this.cityDelegate.create("Helena", true, mt, 
				unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate.create(helena, "59602", null, 
				true);
		Address courtAddress = this.addressDelegate.findOrCreate("1000", null, null, 
				BuildingCategory.APARTMENT, mt59602);
		Organization courtOrg = this.organizationDelegate.create("Some Court", 
				"SC", null);
		Location courtLoc = this.locationDelegate.create(courtOrg, null, 
				courtAddress);
		Court court = this.courtDelegate.create("Some Court", 
				CourtCategory.COUNTY, courtLoc);
		Docket docket = this.docketDelegate.create(person, court, "12345");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, mt, 
				pronouncementDate, JurisdictionAuthority.STATE, null, 
				OffenderDangerDesignator.NON_DANGEROUS, 
				new CourtCasePersonnel(judge, null, null), 
				new CourtCaseFlags(false, false, false, false), "Words");
		Offense offense = this.offenseDelegate.create("Drugs", "Drugs", null,
				OffenseClassification.DRUG, "1234", true);
		Integer count = 3;
		Date convictionDate = this.parseDateText("01/03/2017");
		Conviction conviction = this.convictionDelegate.convict(courtCase, 
				offense, OffenseSeverity.MISDEMEANOR, convictionDate, count, 
				new ConvictionFlags(false, false, false, false));
		SentenceCategory category = this.sentenceCategoryDelegate.create(
				"Category", TermRequirement.REQUIRED, 
				TermRequirement.REQUIRED, TermRequirement.NOT_ALLOWED);
		SentenceLengthClassification lengthClassification 
				= SentenceLengthClassification.NOT_LIFE;
		LegalDispositionCategory legalDispositionCategory
				= this.legalDispositionCategoryDelegate.create(
						"Original Sentence", true);
		Date sentencePronouncementDate = this.parseDateText("02/02/2017");
		ConvictionCredit credit = new ConvictionCredit();
		Date effectiveDate = this.parseDateText("02/03/2017");
		Term prisonTerm = new Term(1, 0, 0);
		Term probationTerm = new Term(5, 0, 0);
		SentenceConnection connection = new SentenceConnection(null, 
				SentenceConnectionClassification.INITIAL);
		
		// Action
		Sentence sentence = this.offenseTermService.sentence(conviction, 
				connection, category, lengthClassification, 
				legalDispositionCategory, sentencePronouncementDate, credit, 
				effectiveDate, null, prisonTerm, probationTerm, 
				null);
		
		//Assert
		PropertyValueAsserter.create()
				.addExpectedValue("conviction", conviction)
				.addExpectedValue("connection", connection)
				.addExpectedValue("credit", credit)
				.addExpectedValue("deferredTerm", null)
				.addExpectedValue("effectiveDate", effectiveDate)
				.addExpectedValue("legalDispositionCategory", 
						legalDispositionCategory)
				.addExpectedValue("lengthClassification", lengthClassification)
				.addExpectedValue("prisonTerm", prisonTerm)
				.addExpectedValue("probationTerm", probationTerm)
				.addExpectedValue("turnSelfInDate", null)
				.addExpectedValue("pronouncementDate", 
						sentencePronouncementDate)
				.performAssertions(sentence);
	}
	
	/**
	 * Tests the creation of a sentence fails when a term doesn't have a 
	 * non-null, greater than zero value.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 * @throws SentenceExistsException if sentence exists
	 */
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void testIllegalArgumentException() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException, ConvictionExistsException, 
			SentenceExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		Country unitedStates = this.countryDelegate.create("United States", 
				"US", true);
		State mt = this.stateDelegate.create("Montana", "MT", unitedStates, 
				true, true);
		City helena = this.cityDelegate.create("Helena", true, mt, 
				unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate.create(helena, "59602", null, 
				true);
		Address courtAddress = this.addressDelegate.findOrCreate("123 Some Other Street", null, null,
				BuildingCategory.APARTMENT, mt59602);
		Organization courtOrg = this.organizationDelegate.create("Some Court", 
				"SC", null);
		Location courtLoc = this.locationDelegate.create(courtOrg, null, 
				courtAddress);
		Court court = this.courtDelegate.create("Some Court", 
				CourtCategory.COUNTY, courtLoc);
		Docket docket = this.docketDelegate.create(person, court, "12345");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, mt, 
				pronouncementDate, JurisdictionAuthority.STATE, null, 
				OffenderDangerDesignator.NON_DANGEROUS, 
				new CourtCasePersonnel(judge, null, null), 
				new CourtCaseFlags(false, false, false, false), "Words");
		Offense offense = this.offenseDelegate.create("Drugs", "Drugs", null,
				OffenseClassification.DRUG, "1234", true);
		Integer count = 3;
		Date convictionDate = this.parseDateText("01/03/2017");
		Conviction conviction = this.convictionDelegate.convict(courtCase, 
				offense, OffenseSeverity.MISDEMEANOR, convictionDate, count, 
				new ConvictionFlags(false, false, false, false));
		SentenceCategory category = this.sentenceCategoryDelegate.create(
				"Category", TermRequirement.REQUIRED, 
				TermRequirement.REQUIRED, TermRequirement.NOT_ALLOWED);
		SentenceLengthClassification lengthClassification 
				= SentenceLengthClassification.NOT_LIFE;
		LegalDispositionCategory legalDispositionCategory
				= this.legalDispositionCategoryDelegate.create(
						"Original Sentence", true);
		Date sentencePronouncementDate = this.parseDateText("02/02/2017");
		ConvictionCredit credit = new ConvictionCredit();
		Date effectiveDate = this.parseDateText("02/03/2017");
		Term prisonTerm = new Term(0, 0, 0); // will cause exception
		Term probationTerm = new Term(5, 0, 0);
		SentenceConnection connection = new SentenceConnection(null, 
				SentenceConnectionClassification.INITIAL);
		
		// Action
		this.offenseTermService.sentence(conviction, connection, category, 
				lengthClassification, legalDispositionCategory, 
				sentencePronouncementDate, credit, effectiveDate, null, 
				prisonTerm, probationTerm, null);
	}
	
	/* Helper methods */
	
	// Parses date text
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}
}
