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
import omis.courtcase.domain.component.CourtCaseFlags;
import omis.courtcase.domain.component.CourtCasePersonnel;
import omis.courtcase.exception.CourtCaseExistsException;
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
import omis.offenseterm.service.HistoricalOffenseTermService;
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
import omis.sentence.exception.SentenceExistsException;
import omis.sentence.service.delegate.LegalDispositionCategoryDelegate;
import omis.sentence.service.delegate.SentenceCategoryDelegate;
import omis.sentence.service.delegate.SentenceDelegate;
import omis.term.domain.component.Term;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests historical offense term service create method.
 *
 * @author Josh Divine 
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test
public class HistoricalOffenseTermServiceCreateTests 
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
	@Qualifier("historicalOffenseTermService")
	private HistoricalOffenseTermService historicalOffenseTermService;
	
	/**
	 * Tests the creation of a historical offense term.
	 * 
	 * @throws SentenceExistsException thrown when the sentence already exists
	 * @throws CourtCaseExistsException thrown when the court case already 
	 * exists
	 * @throws DuplicateEntityFoundException thrown when the entity already 
	 * exists
	 * @throws ConvictionExistsException thrown when the conviction already 
	 * exists
	 * @throws DocketExistsException thrown when the docket already exists
	 */
	@Test
	public void testCreate() throws SentenceExistsException, 
			CourtCaseExistsException, DuplicateEntityFoundException, 
			ConvictionExistsException, DocketExistsException {
		// Arrangements
		Person defendant = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel courtCasePersonnel = new CourtCasePersonnel();
		courtCasePersonnel.setJudge(judge);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State mt = this.stateDelegate.create(
				"Montana", "MT", unitedStates, true, true);
		City helena = this.cityDelegate.create("Helena",
				true, mt, unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		Address courtAddress = this.addressDelegate.findOrCreate(
				"1000", null, null, BuildingCategory.APARTMENT,
				 mt59602);
		Organization courtOrg = this.organizationDelegate
				.create("Some Court", "SC", null);
		Location courtLoc = this.locationDelegate.create(
				courtOrg, null, courtAddress);
		Court court = this.courtDelegate.create("Some Court", 
				CourtCategory.COUNTY, courtLoc);
		Docket docket = this.docketDelegate.create(defendant, court, 
				"Docket 1234");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, null, 
				pronouncementDate, null, null, null, courtCasePersonnel, 
				new CourtCaseFlags(), null);
		Offense offense = this.offenseDelegate.create("Some Offense", 
				"Some Offense", null, OffenseClassification.WEAPON, "Code-123", 
				true);
		OffenseSeverity severity = OffenseSeverity.FELONY;
		Date date = this.parseDateText("01/03/2017");
		Integer counts = 1;
		ConvictionFlags flags = new ConvictionFlags();
		flags.setParoleIneligible(false);
		flags.setSexualOffense(false);
		flags.setSupervisedReleaseIneligible(false);
		flags.setViolentOffense(false);
		Conviction conviction = this.convictionDelegate.create(courtCase, 
				severity, offense, date, counts, flags);
		SentenceCategory category = this.sentenceCategoryDelegate.create(
				"Category", TermRequirement.REQUIRED, 
				TermRequirement.NOT_ALLOWED, TermRequirement.NOT_ALLOWED);
		SentenceLengthClassification lengthClassification = 
				SentenceLengthClassification.NOT_LIFE;
		LegalDispositionCategory legalDispositionCategory = this.
				legalDispositionCategoryDelegate.create("Legal", true);
		ConvictionCredit credit = new ConvictionCredit(0, 0);
		Date effectiveDate = this.parseDateText("01/04/2017");
		Date turnSelfInDate = null;
		Term prisonTerm = new Term(5, 0, 0);
		Term probationTerm = null;
		Term deferredTerm = null;
		
		// Action
		Sentence sentence = this.historicalOffenseTermService.create(conviction, 
				category, lengthClassification, legalDispositionCategory, 
				pronouncementDate, credit, effectiveDate, turnSelfInDate, 
				prisonTerm, probationTerm, deferredTerm);
		
		// Assert
		PropertyValueAsserter.create()
		.addExpectedValue("conviction", conviction)
		.addExpectedValue("category", category)
		.addExpectedValue("lengthClassification", lengthClassification)
		.addExpectedValue("legalDispositionCategory", legalDispositionCategory)
		.addExpectedValue("pronouncementDate", pronouncementDate)
		.addExpectedValue("credit", credit)
		.addExpectedValue("effectiveDate", effectiveDate)
		.addExpectedValue("turnSelfInDate", turnSelfInDate)
		.addExpectedValue("prisonTerm", prisonTerm)
		.addExpectedValue("probationTerm", probationTerm)
		.addExpectedValue("deferredTerm", deferredTerm)
		.performAssertions(sentence);
	}
	
	/**
	 * Tests the {@code SentenceExistsException} is thrown.
	 * 
	 * @throws SentenceExistsException thrown when the sentence already exists
	 * @throws CourtCaseExistsException thrown when the court case already 
	 * exists
	 * @throws DuplicateEntityFoundException thrown when the entity already 
	 * exists
	 * @throws ConvictionExistsException thrown when the conviction already 
	 * exists
	 * @throws DocketExistsException thrown when the docket already exists
	 */
	@Test(expectedExceptions = {SentenceExistsException.class})
	public void testSentenceExistsException() throws SentenceExistsException, 
			CourtCaseExistsException, DuplicateEntityFoundException, 
			ConvictionExistsException, DocketExistsException {
		// Arrangements
		Person defendant = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel courtCasePersonnel = new CourtCasePersonnel();
		courtCasePersonnel.setJudge(judge);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State mt = this.stateDelegate.create(
				"Montana", "MT", unitedStates, true, true);
		City helena = this.cityDelegate.create("Helena",
				true, mt, unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		Address courtAddress = this.addressDelegate.findOrCreate(
				"1000", null, null, BuildingCategory.APARTMENT,
				 mt59602);
		Organization courtOrg = this.organizationDelegate
				.create("Some Court", "SC", null);
		Location courtLoc = this.locationDelegate.create(
				courtOrg, null, courtAddress);
		Court court = this.courtDelegate.create("Some Court", 
				CourtCategory.COUNTY, courtLoc);
		Docket docket = this.docketDelegate.create(defendant, court, 
				"Docket 1234");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, null, 
				pronouncementDate, null, null, null, courtCasePersonnel, 
				new CourtCaseFlags(), null);
		Offense offense = this.offenseDelegate.create("Some Offense", 
				"Some Offense", null, OffenseClassification.WEAPON, "Code-123", 
				true);
		OffenseSeverity severity = OffenseSeverity.FELONY;
		Date date = this.parseDateText("01/03/2017");
		Integer counts = 1;
		ConvictionFlags flags = new ConvictionFlags();
		flags.setParoleIneligible(false);
		flags.setSexualOffense(false);
		flags.setSupervisedReleaseIneligible(false);
		flags.setViolentOffense(false);
		Conviction conviction = this.convictionDelegate.create(courtCase, 
				severity, offense, date, counts, flags);
		SentenceCategory category = this.sentenceCategoryDelegate.create(
				"Category", TermRequirement.REQUIRED, 
				TermRequirement.NOT_ALLOWED, TermRequirement.NOT_ALLOWED);
		SentenceLengthClassification lengthClassification = 
				SentenceLengthClassification.NOT_LIFE;
		LegalDispositionCategory legalDispositionCategory = this.
				legalDispositionCategoryDelegate.create("Legal", true);
		ConvictionCredit credit = new ConvictionCredit(0, 0);
		Date effectiveDate = this.parseDateText("01/04/2017");
		Date turnSelfInDate = null;
		Term prisonTerm = new Term(5, 0, 0);
		Term probationTerm = null;
		Term deferredTerm = null;
		int nextChangeOrder = this.sentenceDelegate
				.countSentencesByConviction(conviction) + 1;
		this.sentenceDelegate.create(conviction, null, prisonTerm, 
				probationTerm, deferredTerm, category, lengthClassification, 
				legalDispositionCategory, effectiveDate, pronouncementDate, 
				credit, turnSelfInDate, true, nextChangeOrder);
		
		// Action
		this.historicalOffenseTermService.create(conviction, category, 
				lengthClassification, legalDispositionCategory, 
				pronouncementDate, credit, effectiveDate, turnSelfInDate, 
				prisonTerm, probationTerm, deferredTerm);
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
