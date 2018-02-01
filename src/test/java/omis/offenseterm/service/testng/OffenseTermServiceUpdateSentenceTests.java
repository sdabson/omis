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
public class OffenseTermServiceUpdateSentenceTests 
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
	 * Tests the update of the connection for a sentence.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 * @throws SentenceExistsException if sentence exists
	 */
	@Test
	public void testUpdateSentenceConnection() 
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
		SentenceConnection connection = SentenceConnection.createInitial();
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction);
		Sentence sentence = this.sentenceDelegate.sentence(conviction, 
				connection, prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory,
				effectiveDate, sentencePronouncementDate, credit, 
				turnSelfInDate, changeOrder);
		SentenceConnection updateConnection = SentenceConnection
				.createConcurrent();
		
		// Action
		Sentence updatedSentence = this.offenseTermService.updateSentence(
				sentence, updateConnection, category, lengthClassification, 
				legalDispositionCategory, sentencePronouncementDate, credit, 
				effectiveDate, turnSelfInDate, prisonTerm, probationTerm, 
				deferredTerm);
		
		//Assert
		PropertyValueAsserter.create()
				.addExpectedValue("category", category)
				.addExpectedValue("conviction", conviction)
				.addExpectedValue("connection", updateConnection)
				.addExpectedValue("credit", credit)
				.addExpectedValue("deferredTerm", deferredTerm)
				.addExpectedValue("effectiveDate", effectiveDate)
				.addExpectedValue("legalDispositionCategory", 
						legalDispositionCategory)
				.addExpectedValue("lengthClassification", lengthClassification)
				.addExpectedValue("prisonTerm", prisonTerm)
				.addExpectedValue("probationTerm", probationTerm)
				.addExpectedValue("turnSelfInDate", turnSelfInDate)
				.addExpectedValue("pronouncementDate", 
						sentencePronouncementDate)
				.performAssertions(updatedSentence);
	}
	
	/**
	 * Tests the update of the category for a sentence.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 * @throws SentenceExistsException if sentence exists
	 */
	@Test
	public void testUpdateSentenceCategory() 
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
		Address courtAddress = this.addressDelegate.findOrCreate("1000 Some Street", null, null, 
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
		SentenceConnection connection = SentenceConnection.createInitial();
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction);
		Sentence sentence = this.sentenceDelegate.sentence(conviction, 
				connection, prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory,
				effectiveDate, sentencePronouncementDate, credit, 
				turnSelfInDate, changeOrder);
		SentenceCategory updateCategory = this.sentenceCategoryDelegate.create(
				"Category2", TermRequirement.REQUIRED, 
				TermRequirement.REQUIRED, TermRequirement.NOT_ALLOWED);
		
		// Action
		Sentence updatedSentence = this.offenseTermService.updateSentence(
				sentence, connection, updateCategory, lengthClassification, 
				legalDispositionCategory, sentencePronouncementDate, credit, 
				effectiveDate, turnSelfInDate, prisonTerm, probationTerm, 
				deferredTerm);
		
		//Assert
		PropertyValueAsserter.create()
				.addExpectedValue("category", updateCategory)
				.addExpectedValue("conviction", conviction)
				.addExpectedValue("connection", connection)
				.addExpectedValue("credit", credit)
				.addExpectedValue("deferredTerm", deferredTerm)
				.addExpectedValue("effectiveDate", effectiveDate)
				.addExpectedValue("legalDispositionCategory", 
						legalDispositionCategory)
				.addExpectedValue("lengthClassification", lengthClassification)
				.addExpectedValue("prisonTerm", prisonTerm)
				.addExpectedValue("probationTerm", probationTerm)
				.addExpectedValue("turnSelfInDate", turnSelfInDate)
				.addExpectedValue("pronouncementDate", 
						sentencePronouncementDate)
				.performAssertions(updatedSentence);
	}
	
	/**
	 * Tests the update of the credit for a sentence.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 * @throws SentenceExistsException if sentence exists
	 */
	@Test
	public void testUpdateSentenceCredit() 
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
		Address courtAddress = this.addressDelegate.findOrCreate("1000 Some Street", null, null, 
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
		SentenceConnection connection = SentenceConnection.createInitial();
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction);
		Sentence sentence = this.sentenceDelegate.sentence(conviction, 
				connection, prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory,
				effectiveDate, sentencePronouncementDate, credit, 
				turnSelfInDate, changeOrder);
		ConvictionCredit updateCredit = new ConvictionCredit(10, 0);
		
		// Action
		Sentence updatedSentence = this.offenseTermService.updateSentence(
				sentence, connection, category, lengthClassification, 
				legalDispositionCategory, sentencePronouncementDate, 
				updateCredit, effectiveDate, turnSelfInDate, prisonTerm, 
				probationTerm, deferredTerm);
		
		//Assert
		PropertyValueAsserter.create()
				.addExpectedValue("category", category)
				.addExpectedValue("conviction", conviction)
				.addExpectedValue("connection", connection)
				.addExpectedValue("credit", updateCredit)
				.addExpectedValue("deferredTerm", deferredTerm)
				.addExpectedValue("effectiveDate", effectiveDate)
				.addExpectedValue("legalDispositionCategory", 
						legalDispositionCategory)
				.addExpectedValue("lengthClassification", lengthClassification)
				.addExpectedValue("prisonTerm", prisonTerm)
				.addExpectedValue("probationTerm", probationTerm)
				.addExpectedValue("turnSelfInDate", turnSelfInDate)
				.addExpectedValue("pronouncementDate", 
						sentencePronouncementDate)
				.performAssertions(updatedSentence);
	}
	
	/**
	 * Tests the update of the deferred term for a sentence.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 * @throws SentenceExistsException if sentence exists
	 */
	@Test
	public void testUpdateSentenceDeferredTerm() 
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
		Address courtAddress = this.addressDelegate.findOrCreate("1000 Some Street", null, null, 
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
		SentenceConnection connection = SentenceConnection.createInitial();
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction);
		Sentence sentence = this.sentenceDelegate.sentence(conviction, 
				connection, prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory,
				effectiveDate, sentencePronouncementDate, credit, 
				turnSelfInDate, changeOrder);
		Term updateDeferredTerm = new Term(0, 6, 0);
		
		// Action
		Sentence updatedSentence = this.offenseTermService.updateSentence(
				sentence, connection, category, lengthClassification, 
				legalDispositionCategory, sentencePronouncementDate, 
				credit, effectiveDate, turnSelfInDate, prisonTerm, 
				probationTerm, updateDeferredTerm);
		
		//Assert
		PropertyValueAsserter.create()
				.addExpectedValue("category", category)
				.addExpectedValue("conviction", conviction)
				.addExpectedValue("connection", connection)
				.addExpectedValue("credit", credit)
				.addExpectedValue("deferredTerm", updateDeferredTerm)
				.addExpectedValue("effectiveDate", effectiveDate)
				.addExpectedValue("legalDispositionCategory", 
						legalDispositionCategory)
				.addExpectedValue("lengthClassification", lengthClassification)
				.addExpectedValue("prisonTerm", prisonTerm)
				.addExpectedValue("probationTerm", probationTerm)
				.addExpectedValue("turnSelfInDate", turnSelfInDate)
				.addExpectedValue("pronouncementDate", 
						sentencePronouncementDate)
				.performAssertions(updatedSentence);
	}
	
	/**
	 * Tests the update of the effective date for a sentence.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 * @throws SentenceExistsException if sentence exists
	 */
	@Test
	public void testUpdateSentenceEffectiveDate() 
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
		Address courtAddress = this.addressDelegate.findOrCreate("1000 Some Street", null, null, 
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
		SentenceConnection connection = SentenceConnection.createInitial();
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction);
		Sentence sentence = this.sentenceDelegate.sentence(conviction, 
				connection, prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory,
				effectiveDate, sentencePronouncementDate, credit, 
				turnSelfInDate, changeOrder);
		Date updateEffectiveDate = this.parseDateText("02/03/2017");
		
		// Action
		Sentence updatedSentence = this.offenseTermService.updateSentence(
				sentence, connection, category, lengthClassification, 
				legalDispositionCategory, sentencePronouncementDate, 
				credit, updateEffectiveDate, turnSelfInDate, prisonTerm, 
				probationTerm, deferredTerm);
		
		//Assert
		PropertyValueAsserter.create()
				.addExpectedValue("category", category)
				.addExpectedValue("conviction", conviction)
				.addExpectedValue("connection", connection)
				.addExpectedValue("credit", credit)
				.addExpectedValue("deferredTerm", deferredTerm)
				.addExpectedValue("effectiveDate", updateEffectiveDate)
				.addExpectedValue("legalDispositionCategory", 
						legalDispositionCategory)
				.addExpectedValue("lengthClassification", lengthClassification)
				.addExpectedValue("prisonTerm", prisonTerm)
				.addExpectedValue("probationTerm", probationTerm)
				.addExpectedValue("turnSelfInDate", turnSelfInDate)
				.addExpectedValue("pronouncementDate", 
						sentencePronouncementDate)
				.performAssertions(updatedSentence);
	}
	
	/**
	 * Tests the update of the legal disposition category for a sentence.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 * @throws SentenceExistsException if sentence exists
	 */
	@Test
	public void testUpdateSentenceLegalDispositionCategory() 
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
		Address courtAddress = this.addressDelegate.findOrCreate("1000 Some Street", null, null, 
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
		SentenceConnection connection = SentenceConnection.createInitial();
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction);
		Sentence sentence = this.sentenceDelegate.sentence(conviction, 
				connection, prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory,
				effectiveDate, sentencePronouncementDate, credit, 
				turnSelfInDate, changeOrder);
		LegalDispositionCategory updateLegalDispositionCategory
			= this.legalDispositionCategoryDelegate.create("Sentence", true);
		
		// Action
		Sentence updatedSentence = this.offenseTermService.updateSentence(
				sentence, connection, category, lengthClassification, 
				updateLegalDispositionCategory, sentencePronouncementDate, 
				credit, effectiveDate, turnSelfInDate, prisonTerm, 
				probationTerm, deferredTerm);
		
		//Assert
		PropertyValueAsserter.create()
				.addExpectedValue("category", category)
				.addExpectedValue("conviction", conviction)
				.addExpectedValue("connection", connection)
				.addExpectedValue("credit", credit)
				.addExpectedValue("deferredTerm", deferredTerm)
				.addExpectedValue("effectiveDate", effectiveDate)
				.addExpectedValue("legalDispositionCategory", 
						updateLegalDispositionCategory)
				.addExpectedValue("lengthClassification", lengthClassification)
				.addExpectedValue("prisonTerm", prisonTerm)
				.addExpectedValue("probationTerm", probationTerm)
				.addExpectedValue("turnSelfInDate", turnSelfInDate)
				.addExpectedValue("pronouncementDate", 
						sentencePronouncementDate)
				.performAssertions(updatedSentence);
	}
	
	/**
	 * Tests the update of the length classification for a sentence.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 * @throws SentenceExistsException if sentence exists
	 */
	@Test
	public void testUpdateSentenceLengthClassification() 
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
		Address courtAddress = this.addressDelegate.findOrCreate("1000 Some Street", null, null, 
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
		SentenceConnection connection = SentenceConnection.createInitial();
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction);
		Sentence sentence = this.sentenceDelegate.sentence(conviction, 
				connection, prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory,
				effectiveDate, sentencePronouncementDate, credit, 
				turnSelfInDate, changeOrder);
		SentenceLengthClassification updateLengthClassification = 
				SentenceLengthClassification.NOT_LIFE;
		
		// Action
		Sentence updatedSentence = this.offenseTermService.updateSentence(
				sentence, connection, category, updateLengthClassification, 
				legalDispositionCategory, sentencePronouncementDate, 
				credit, effectiveDate, turnSelfInDate, prisonTerm, 
				probationTerm, deferredTerm);
		
		//Assert
		PropertyValueAsserter.create()
				.addExpectedValue("category", category)
				.addExpectedValue("conviction", conviction)
				.addExpectedValue("connection", connection)
				.addExpectedValue("credit", credit)
				.addExpectedValue("deferredTerm", deferredTerm)
				.addExpectedValue("effectiveDate", effectiveDate)
				.addExpectedValue("legalDispositionCategory", 
						legalDispositionCategory)
				.addExpectedValue("lengthClassification", 
						updateLengthClassification)
				.addExpectedValue("prisonTerm", prisonTerm)
				.addExpectedValue("probationTerm", probationTerm)
				.addExpectedValue("turnSelfInDate", turnSelfInDate)
				.addExpectedValue("pronouncementDate", 
						sentencePronouncementDate)
				.performAssertions(updatedSentence);
	}
	
	/**
	 * Tests the update of the prison term for a sentence.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 * @throws SentenceExistsException if sentence exists
	 */
	@Test
	public void testUpdateSentencePrisonTerm() 
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
		Address courtAddress = this.addressDelegate.findOrCreate("1000 Some Street", null, null, 
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
		SentenceConnection connection = SentenceConnection.createInitial();
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction);
		Sentence sentence = this.sentenceDelegate.sentence(conviction, 
				connection, prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory,
				effectiveDate, sentencePronouncementDate, credit, 
				turnSelfInDate, changeOrder);
		Term updatePrisonTerm = new Term(5, 0, 0);
		
		// Action
		Sentence updatedSentence = this.offenseTermService.updateSentence(
				sentence, connection, category, lengthClassification, 
				legalDispositionCategory, sentencePronouncementDate, 
				credit, effectiveDate, turnSelfInDate, updatePrisonTerm, 
				probationTerm, deferredTerm);
		
		//Assert
		PropertyValueAsserter.create()
				.addExpectedValue("category", category)
				.addExpectedValue("conviction", conviction)
				.addExpectedValue("connection", connection)
				.addExpectedValue("credit", credit)
				.addExpectedValue("deferredTerm", deferredTerm)
				.addExpectedValue("effectiveDate", effectiveDate)
				.addExpectedValue("legalDispositionCategory", 
						legalDispositionCategory)
				.addExpectedValue("lengthClassification", lengthClassification)
				.addExpectedValue("prisonTerm", updatePrisonTerm)
				.addExpectedValue("probationTerm", probationTerm)
				.addExpectedValue("turnSelfInDate", turnSelfInDate)
				.addExpectedValue("pronouncementDate", 
						sentencePronouncementDate)
				.performAssertions(updatedSentence);
	}
	
	/**
	 * Tests the update of the probation term for a sentence.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 * @throws SentenceExistsException if sentence exists
	 */
	@Test
	public void testUpdateSentenceProbationTerm() 
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
		Address courtAddress = this.addressDelegate.findOrCreate("1000 Some Street", null, null, 
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
		SentenceConnection connection = SentenceConnection.createInitial();
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction);
		Sentence sentence = this.sentenceDelegate.sentence(conviction, 
				connection, prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory,
				effectiveDate, sentencePronouncementDate, credit, 
				turnSelfInDate, changeOrder);
		Term updateProbationTerm = new Term(10, 6, 0);
		
		// Action
		Sentence updatedSentence = this.offenseTermService.updateSentence(
				sentence, connection, category, lengthClassification, 
				legalDispositionCategory, sentencePronouncementDate, 
				credit, effectiveDate, turnSelfInDate, prisonTerm, 
				updateProbationTerm, deferredTerm);
		
		//Assert
		PropertyValueAsserter.create()
				.addExpectedValue("category", category)
				.addExpectedValue("conviction", conviction)
				.addExpectedValue("connection", connection)
				.addExpectedValue("credit", credit)
				.addExpectedValue("deferredTerm", deferredTerm)
				.addExpectedValue("effectiveDate", effectiveDate)
				.addExpectedValue("legalDispositionCategory", 
						legalDispositionCategory)
				.addExpectedValue("lengthClassification", lengthClassification)
				.addExpectedValue("prisonTerm", prisonTerm)
				.addExpectedValue("probationTerm", updateProbationTerm)
				.addExpectedValue("turnSelfInDate", turnSelfInDate)
				.addExpectedValue("pronouncementDate", 
						sentencePronouncementDate)
				.performAssertions(updatedSentence);
	}
	
	/**
	 * Tests the update of the turn self in date for a sentence.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 * @throws SentenceExistsException if sentence exists
	 */
	@Test
	public void testUpdateSentenceTurnSelfInDate() 
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
		Address courtAddress = this.addressDelegate.findOrCreate("1000 Some Street", null, null, 
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
		SentenceConnection connection = SentenceConnection.createInitial();
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction);
		Sentence sentence = this.sentenceDelegate.sentence(conviction, 
				connection, prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory,
				effectiveDate, sentencePronouncementDate, credit, 
				turnSelfInDate, changeOrder);
		Date updateTurnSelfInDate = this.parseDateText("02/05/2017");
		
		// Action
		Sentence updatedSentence = this.offenseTermService.updateSentence(
				sentence, connection, category, lengthClassification, 
				legalDispositionCategory, sentencePronouncementDate, 
				credit, effectiveDate, updateTurnSelfInDate, prisonTerm, 
				probationTerm, deferredTerm);
		
		//Assert
		PropertyValueAsserter.create()
				.addExpectedValue("category", category)
				.addExpectedValue("conviction", conviction)
				.addExpectedValue("connection", connection)
				.addExpectedValue("credit", credit)
				.addExpectedValue("deferredTerm", deferredTerm)
				.addExpectedValue("effectiveDate", effectiveDate)
				.addExpectedValue("legalDispositionCategory", 
						legalDispositionCategory)
				.addExpectedValue("lengthClassification", lengthClassification)
				.addExpectedValue("prisonTerm", prisonTerm)
				.addExpectedValue("probationTerm", probationTerm)
				.addExpectedValue("turnSelfInDate", updateTurnSelfInDate)
				.addExpectedValue("pronouncementDate", 
						sentencePronouncementDate)
				.performAssertions(updatedSentence);
	}
	
	/**
	 * Tests the update of the pronouncement date for a sentence.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 * @throws SentenceExistsException if sentence exists
	 */
	@Test
	public void testUpdateSentencePronouncementDate() 
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
		Address courtAddress = this.addressDelegate.findOrCreate("1000 Some Street", null, null, 
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
		SentenceConnection connection = SentenceConnection.createInitial();
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction);
		Sentence sentence = this.sentenceDelegate.sentence(conviction, 
				connection, prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory,
				effectiveDate, sentencePronouncementDate, credit, 
				turnSelfInDate, changeOrder);
		Date updatePronouncementDate = this.parseDateText("02/01/2017");
		
		// Action
		Sentence updatedSentence = this.offenseTermService.updateSentence(
				sentence, connection, category, lengthClassification, 
				legalDispositionCategory, updatePronouncementDate, 
				credit, effectiveDate, turnSelfInDate, prisonTerm, 
				probationTerm, deferredTerm);
		
		//Assert
		PropertyValueAsserter.create()
				.addExpectedValue("category", category)
				.addExpectedValue("conviction", conviction)
				.addExpectedValue("connection", connection)
				.addExpectedValue("credit", credit)
				.addExpectedValue("deferredTerm", deferredTerm)
				.addExpectedValue("effectiveDate", effectiveDate)
				.addExpectedValue("legalDispositionCategory", 
						legalDispositionCategory)
				.addExpectedValue("lengthClassification", lengthClassification)
				.addExpectedValue("prisonTerm", prisonTerm)
				.addExpectedValue("probationTerm", probationTerm)
				.addExpectedValue("turnSelfInDate", turnSelfInDate)
				.addExpectedValue("pronouncementDate", updatePronouncementDate)
				.performAssertions(updatedSentence);
	}
	
	
	/**
	 * Tests {@code IllegalArgumentException} is thrown when a circular sentence 
	 * connection is attempted.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 * @throws SentenceExistsException if sentence exists
	 */
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void testIllegalArgumentExceptionForCircularSentenceConnection() 
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
		SentenceConnection connection = SentenceConnection.createInitial();
		Sentence sentence = this.sentenceDelegate.sentence(conviction, 
				connection, prisonTerm, probationTerm, null, category, 
				SentenceLengthClassification.NOT_LIFE, legalDispositionCategory,
				effectiveDate, sentencePronouncementDate, credit, null, 1);
		Offense consecOffense = this.offenseDelegate.create("Rugs", "Rugs", 
				null, OffenseClassification.PROPERTY, "12345", true);
		Conviction consecutiveConviction = this.convictionDelegate.convict(
				courtCase, consecOffense, OffenseSeverity.FELONY, 
				convictionDate, count, 
				new ConvictionFlags(false, false, false, false));
		Sentence consecutive = this.sentenceDelegate.sentence(
				consecutiveConviction, SentenceConnection.createConsecutive(
						sentence), 
				prisonTerm, probationTerm, null, category, 
				SentenceLengthClassification.NOT_LIFE, legalDispositionCategory, 
				effectiveDate, sentencePronouncementDate, credit, null, 1);
		connection.setSentence(consecutive);
		
		// Action
		this.sentenceDelegate.updateSentence(sentence, connection, prisonTerm,  
				probationTerm, null, category, 
				SentenceLengthClassification.NOT_LIFE, legalDispositionCategory, 
				effectiveDate, sentencePronouncementDate, credit, null);
	}
	
	/**
	 * Tests {@code IllegalArgumentException} is thrown when a term required by 
	 * the category does not have a non-null, greater than zero value.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 * @throws SentenceExistsException if sentence exists
	 */
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void testIllegalArgumentExceptionForTerm() 
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
		Address courtAddress = this.addressDelegate.findOrCreate("1000 Some Street", null, null, 
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
		SentenceConnection connection = SentenceConnection.createInitial();
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction);
		Sentence sentence = this.sentenceDelegate.sentence(conviction, 
				connection, prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory,
				effectiveDate, sentencePronouncementDate, credit, 
				turnSelfInDate, changeOrder);
		Term updatedPrisonTerm = new Term(0, 0, 0);
		
		// Action
		this.offenseTermService.updateSentence(sentence, connection, category, 
				lengthClassification, legalDispositionCategory, 
				sentencePronouncementDate, credit, effectiveDate, 
				turnSelfInDate, updatedPrisonTerm, probationTerm, deferredTerm);
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
