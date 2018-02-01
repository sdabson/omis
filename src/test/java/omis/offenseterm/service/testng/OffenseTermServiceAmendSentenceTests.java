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
import omis.sentence.service.delegate.SentenceDelegate;
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
public class OffenseTermServiceAmendSentenceTests 
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
	
	@Autowired
	@Qualifier("sentenceDelegate")
	private SentenceDelegate sentenceDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("offenseTermService")
	private OffenseTermService offenseTermService;
	
	/**
	 * Tests amend sentence service method.
	 * 
	 * @throws DuplicateEntityFoundException
	 * @throws DocketExistsException 
	 * @throws CourtCaseExistsException 
	 * @throws ConvictionExistsException 
	 * @throws SentenceExistsException 
	 */
	@Test
	public void testAmendSentence() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException, ConvictionExistsException, 
			SentenceExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel();
		personnel.setJudge(judge);
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
				OffenderDangerDesignator.NON_DANGEROUS, personnel, 
				new CourtCaseFlags(false, false, false, false), "Words");
		Offense offense = this.offenseDelegate.create("Drugs", "Drugs", null,
				OffenseClassification.DRUG, "1234", true);
		Integer count = 3;
		Conviction conviction = this.convictionDelegate.convict(courtCase, 
				offense, OffenseSeverity.MISDEMEANOR, 
				this.parseDateText("01/03/2017"), count, 
				new ConvictionFlags(false, false, false, false));
		SentenceCategory category = this.sentenceCategoryDelegate.create(
				"Category", TermRequirement.REQUIRED, 
				TermRequirement.REQUIRED, TermRequirement.NOT_ALLOWED);
		LegalDispositionCategory legalDispositionCategory
			= this.legalDispositionCategoryDelegate.create("Original Sentence", 
				true);
		Date sentencePronouncementDate = this.parseDateText("02/02/2017");
		ConvictionCredit credit = new ConvictionCredit();
		Date effectiveDate = this.parseDateText("02/03/2017");
		Date turnSelfInDate= this.parseDateText("02/03/2017");
		Term prisonTerm = new Term(1, 0, 0);
		Term probationTerm = new Term(5, 0, 0);
		Term deferredTerm = null;
		SentenceLengthClassification lengthClassification = 
				SentenceLengthClassification.NOT_LIFE;
		SentenceConnection connection = new SentenceConnection(null, 
				SentenceConnectionClassification.INITIAL);
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction)  + 1;
		Sentence sentence = this.sentenceDelegate.sentence(conviction, 
				connection, prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory,
				effectiveDate, sentencePronouncementDate, credit, 
				turnSelfInDate, changeOrder);
		Date secondEffectiveDate = this.parseDateText("05/01/2017");
		
		// Action
		Sentence amendedSentence = this.offenseTermService.amendSentence(
				sentence, connection, category, lengthClassification, 
				legalDispositionCategory, sentencePronouncementDate, credit, 
				secondEffectiveDate, turnSelfInDate, prisonTerm, probationTerm, 
				deferredTerm);
		
		// Assert
		PropertyValueAsserter.create()
			.addExpectedValue("active", true)
			.addExpectedValue("category", category)
			.addExpectedValue("changeOrder", this.sentenceDelegate
					.countSentencesByConviction(conviction))
			.addExpectedValue("connection", connection)
			.addExpectedValue("conviction", conviction)
			.addExpectedValue("credit", credit)
			.addExpectedValue("deferredTerm", deferredTerm)
			.addExpectedValue("effectiveDate", secondEffectiveDate)
			.addExpectedValue("legalDispositionCategory", 
					legalDispositionCategory)
			.addExpectedValue("lengthClassification", lengthClassification)
			.addExpectedValue("prisonTerm", prisonTerm)
			.addExpectedValue("probationTerm", probationTerm)
			.addExpectedValue("pronouncementDate", sentencePronouncementDate)
			.addExpectedValue("turnSelfInDate", turnSelfInDate)
			.performAssertions(amendedSentence);
	}
	
	/**
	 * Tests amend sentence service method with a consecutive sentence.
	 * 
	 * @throws DuplicateEntityFoundException
	 * @throws DocketExistsException 
	 * @throws CourtCaseExistsException 
	 * @throws ConvictionExistsException 
	 * @throws SentenceExistsException 
	 */
	@Test
	public void testAmendSentenceWithConsecutive() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException, ConvictionExistsException, 
			SentenceExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel();
		personnel.setJudge(judge);
		Country unitedStates = this.countryDelegate.create("United States", 
				"US", true);
		State mt = this.stateDelegate.create("Montana", "MT", unitedStates, 
				true, true);
		City helena = this.cityDelegate.create("Helena", true, mt, 
				unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate.create(helena, "59602", null, 
				true);
		Address courtAddress = this.addressDelegate.findOrCreate("1000", null, null, 
				BuildingCategory.APARTMENT,mt59602);
		Organization courtOrg = this.organizationDelegate.create("Some Court", 
				"SC", null);
		Location courtLoc = this.locationDelegate.create(courtOrg, null, 
				courtAddress);
		Court court = this.courtDelegate.create("Some Court", 
				CourtCategory.COUNTY, courtLoc);
		Docket docket = this.docketDelegate.create(person, court, "12345");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, mt, 
				pronouncementDate, JurisdictionAuthority.STATE, null, 
				OffenderDangerDesignator.NON_DANGEROUS, personnel, 
				new CourtCaseFlags(false, false, false, false), "Words");
		Offense offense = this.offenseDelegate.create("Drugs", "Drugs", null,
				OffenseClassification.DRUG, "1234", true);
		Integer count = 3;
		Conviction conviction = this.convictionDelegate.convict(courtCase, 
				offense, OffenseSeverity.MISDEMEANOR, 
				this.parseDateText("01/03/2017"), count, 
				new ConvictionFlags(false, false, false, false));
		SentenceCategory category = this.sentenceCategoryDelegate.create(
				"Category", TermRequirement.REQUIRED, 
				TermRequirement.REQUIRED, TermRequirement.NOT_ALLOWED);
		LegalDispositionCategory legalDispositionCategory
			= this.legalDispositionCategoryDelegate.create("Original Sentence", 
				true);
		Date sentencePronouncementDate = this.parseDateText("02/02/2017");
		ConvictionCredit credit = new ConvictionCredit();
		Date effectiveDate = this.parseDateText("02/03/2017");
		Date turnSelfInDate= this.parseDateText("02/03/2017");
		Term prisonTerm = new Term(1, 0, 0);
		Term probationTerm = new Term(5, 0, 0);
		Term deferredTerm = null;
		SentenceLengthClassification lengthClassification = 
				SentenceLengthClassification.NOT_LIFE;
		SentenceConnection connection = new SentenceConnection(null, 
				SentenceConnectionClassification.INITIAL);
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction)  + 1;
		Sentence sentence = this.sentenceDelegate.sentence(conviction, 
				connection, prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory,
				effectiveDate, sentencePronouncementDate, credit, 
				turnSelfInDate, changeOrder);
		Offense secondOffense = this.offenseDelegate.create("Rugs", "Rugs", 
				null, OffenseClassification.PROPERTY, "12345", true);
		Conviction secondConviction = this.convictionDelegate.convict(
				courtCase, secondOffense, OffenseSeverity.FELONY, 
				this.parseDateText("01/03/2017"), count, 
				new ConvictionFlags(false, false, false, false));
		Date secondEffectiveDate = this.parseDateText("05/01/2017");
		Integer secondChangeOrder = this.sentenceDelegate
				.countSentencesByConviction(conviction) + 1;
		this.sentenceDelegate.sentence(
				secondConviction, new SentenceConnection(sentence, 
						SentenceConnectionClassification.CONSECUTIVE), 
				prisonTerm, probationTerm, null, category, 
				SentenceLengthClassification.NOT_LIFE, legalDispositionCategory, 
				secondEffectiveDate, sentencePronouncementDate, credit, null, 
				secondChangeOrder);
		
		// Action
		Sentence amendedSentence = this.offenseTermService.amendSentence(
				sentence, connection, category, lengthClassification, 
				legalDispositionCategory, sentencePronouncementDate, credit, 
				secondEffectiveDate, turnSelfInDate, prisonTerm, probationTerm, 
				deferredTerm);
		
		// Assert
		PropertyValueAsserter.create()
			.addExpectedValue("active", true)
			.addExpectedValue("category", category)
			.addExpectedValue("changeOrder", this.sentenceDelegate
					.countSentencesByConviction(conviction))
			.addExpectedValue("connection", connection)
			.addExpectedValue("conviction", conviction)
			.addExpectedValue("credit", credit)
			.addExpectedValue("deferredTerm", deferredTerm)
			.addExpectedValue("effectiveDate", secondEffectiveDate)
			.addExpectedValue("legalDispositionCategory", 
					legalDispositionCategory)
			.addExpectedValue("lengthClassification", lengthClassification)
			.addExpectedValue("prisonTerm", prisonTerm)
			.addExpectedValue("probationTerm", probationTerm)
			.addExpectedValue("pronouncementDate", sentencePronouncementDate)
			.addExpectedValue("turnSelfInDate", turnSelfInDate)
			.performAssertions(amendedSentence);
	}
	
	/**
	 * Tests amend sentence service method properly throws exception.
	 * 
	 * @throws DuplicateEntityFoundException
	 * @throws DocketExistsException 
	 * @throws CourtCaseExistsException 
	 * @throws ConvictionExistsException 
	 * @throws SentenceExistsException 
	 */
	@Test(expectedExceptions = {SentenceExistsException.class})
	public void testSentenceExistsException() 
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
		LegalDispositionCategory legalDispositionCategory
			= this.legalDispositionCategoryDelegate.create("Original Sentence", 
				true);
		Date sentencePronouncementDate = this.parseDateText("02/02/2017");
		ConvictionCredit credit = new ConvictionCredit();
		Date effectiveDate = this.parseDateText("02/03/2017");
		Term prisonTerm = new Term(1, 0, 0);
		Term probationTerm = new Term(5, 0, 0);
		SentenceConnection connection = new SentenceConnection(null, 
				SentenceConnectionClassification.INITIAL);
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction) + 1;
		Sentence sentence = this.sentenceDelegate.sentence(conviction, 
				connection, prisonTerm, probationTerm, null, category, 
				SentenceLengthClassification.NOT_LIFE, legalDispositionCategory,
				effectiveDate, sentencePronouncementDate, credit, null, 
				changeOrder);
		
		// Action
		this.offenseTermService.amendSentence(sentence, connection, category,
				SentenceLengthClassification.NOT_LIFE, legalDispositionCategory,
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
