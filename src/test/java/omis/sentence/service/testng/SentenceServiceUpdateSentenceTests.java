package omis.sentence.service.testng;

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
import omis.sentence.service.SentenceService;
import omis.sentence.service.delegate.LegalDispositionCategoryDelegate;
import omis.sentence.service.delegate.SentenceCategoryDelegate;
import omis.sentence.service.delegate.SentenceDelegate;
import omis.term.domain.component.Term;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests sentence service.
 *
 * @author Josh Divine 
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test
public class SentenceServiceUpdateSentenceTests 
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
	@Qualifier("offenseDelegate")
	private OffenseDelegate offenseDelegate;
	
	@Autowired
	@Qualifier("convictionDelegate")
	private ConvictionDelegate convictionDelegate;
	
	@Autowired
	@Qualifier("sentenceCategoryDelegate")
	private SentenceCategoryDelegate sentenceCategoryDelegate;

	@Autowired
	@Qualifier("docketDelegate")
	private DocketDelegate docketDelegate;
	
	@Autowired
	@Qualifier("sentenceDelegate")
	private SentenceDelegate sentenceDelegate;
	
	@Autowired
	@Qualifier("legalDispositionCategoryDelegate")
	private LegalDispositionCategoryDelegate legalDispositionCategoryDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("sentenceService")
	private SentenceService sentenceService;
	
	@Test(expectedExceptions = {SentenceExistsException.class})
	public void testSentenceExistsException() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException, ConvictionExistsException, 
			SentenceExistsException {
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
		Date convictionDate = this.parseDateText("01/03/2017");
		ConvictionFlags flags = new ConvictionFlags();
		flags.setParoleIneligible(false);
		flags.setSexualOffense(false);
		flags.setSupervisedReleaseIneligible(false);
		flags.setViolentOffense(false);
		Conviction conviction = this.convictionDelegate.create(courtCase, 
				OffenseSeverity.MISDEMEANOR, offense, convictionDate, 2, flags);
		Date effectiveDate = this.parseDateText("02/01/2017");
		Term prisonTerm = new Term(1, 0, 0);
		Term probationTerm = new Term(5, 0, 0);
		Term deferredTerm = null;
		SentenceCategory category = this.sentenceCategoryDelegate.create(
				"Category", TermRequirement.REQUIRED, 
				TermRequirement.REQUIRED, TermRequirement.NOT_ALLOWED);
		ConvictionCredit credit = new ConvictionCredit();
		SentenceLengthClassification lengthClassification 
				= SentenceLengthClassification.NOT_LIFE;
		Date turnSelfInDate = this.parseDateText("01/31/2017");
		LegalDispositionCategory legalDispositionCategory 
			= this.legalDispositionCategoryDelegate.create("Original Sentence", 
					true);
		SentenceConnection connection = new SentenceConnection();
		connection.setClassification(SentenceConnectionClassification.INITIAL);
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction);
		this.sentenceDelegate.create(conviction, connection, prisonTerm, 
				probationTerm, deferredTerm, category, lengthClassification, 
				legalDispositionCategory, effectiveDate, pronouncementDate, 
				credit, turnSelfInDate, true, changeOrder);
		Date secondEffDate = this.parseDateText("02/02/2017");
		Sentence sentence = this.sentenceDelegate.create(conviction, 
				connection, prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory, secondEffDate, 
				pronouncementDate, credit, turnSelfInDate, true, changeOrder);
		SentenceLengthClassification updatedLengthClassification 
			= SentenceLengthClassification.DEATH;

		// Action
		Sentence updatedSentence = this.sentenceService.updateSentence(
				sentence, connection, prisonTerm, probationTerm, deferredTerm, 
				category, updatedLengthClassification, legalDispositionCategory, 
				effectiveDate, pronouncementDate, credit, turnSelfInDate);
		
		//Assert
		assertValues(updatedSentence, conviction, connection, prisonTerm, 
				probationTerm, deferredTerm, category, 
				updatedLengthClassification, legalDispositionCategory, 
				effectiveDate, pronouncementDate, credit, 
				turnSelfInDate);
	}
	
	@Test
	public void testUpdateSentence() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException, ConvictionExistsException, 
			SentenceExistsException {
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
		Date convictionDate = this.parseDateText("01/03/2017");
		ConvictionFlags flags = new ConvictionFlags();
		flags.setParoleIneligible(false);
		flags.setSexualOffense(false);
		flags.setSupervisedReleaseIneligible(false);
		flags.setViolentOffense(false);
		Conviction conviction = this.convictionDelegate.create(courtCase, 
				OffenseSeverity.MISDEMEANOR, offense, convictionDate, 2, flags);
		Date effectiveDate = this.parseDateText("02/01/2017");
		Term prisonTerm = new Term(1, 0, 0);
		Term probationTerm = new Term(5, 0, 0);
		Term deferredTerm = null;
		SentenceCategory category = this.sentenceCategoryDelegate.create(
				"Category", TermRequirement.REQUIRED, 
				TermRequirement.REQUIRED, TermRequirement.NOT_ALLOWED);
		ConvictionCredit credit = new ConvictionCredit();
		SentenceLengthClassification lengthClassification 
				= SentenceLengthClassification.NOT_LIFE;
		Date turnSelfInDate = this.parseDateText("01/31/2017");
		LegalDispositionCategory legalDispositionCategory 
			= this.legalDispositionCategoryDelegate.create("Original Sentence", 
					true);
		SentenceConnection connection = new SentenceConnection();
		connection.setClassification(SentenceConnectionClassification.INITIAL);
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction);
		Sentence sentence = this.sentenceDelegate.create(conviction, 
				connection, prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory, effectiveDate, 
				pronouncementDate, credit, turnSelfInDate, true, changeOrder);
		SentenceLengthClassification updatedLengthClassification 
			= SentenceLengthClassification.DEATH;

		// Action
		Sentence updatedSentence = this.sentenceService.updateSentence(
				sentence, connection, prisonTerm, probationTerm, deferredTerm, 
				category, updatedLengthClassification, legalDispositionCategory, 
				effectiveDate, pronouncementDate, credit, turnSelfInDate);
		
		//Assert
		assertValues(updatedSentence, conviction, connection, prisonTerm, 
				probationTerm, deferredTerm, category, 
				updatedLengthClassification, legalDispositionCategory, 
				effectiveDate, pronouncementDate, credit, 
				turnSelfInDate);
	}
	
	// Parses date text
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}
	
	// Asserts values are correct
	private void assertValues(final Sentence sentence, 
			final Conviction conviction, 
			final SentenceConnection connection, final Term prisonTerm, 
			final Term probationTerm, final Term deferredTerm, 
			final SentenceCategory category,  
			final SentenceLengthClassification lengthClassification,
			final LegalDispositionCategory legalDispositionCategory,
			final Date effectiveDate, final Date pronouncementDate,
			final ConvictionCredit credit, Date turnSelfInDate) {
		assert conviction.equals(sentence.getConviction()) : String
			.format("Wrong conviction: %d found; %d expected",
				sentence.getConviction().getId(),
				conviction.getId());
		if (prisonTerm == null || sentence.getPrisonTerm() == null) {
			assert prisonTerm == null && sentence.getPrisonTerm() == null 
				: String.format("Mismatched prison term nulls: %s found; %s "
						+ "expected", isNull(sentence.getPrisonTerm()), 
						isNull(prisonTerm));
		} else {
			assert prisonTerm.equals(sentence.getPrisonTerm()) : String
			.format("Wrong prison term: %d,%d,%d found; %d,%d,%d expected",
				sentence.getPrisonTerm().getYears(), 
				sentence.getPrisonTerm().getMonths(), 
				sentence.getPrisonTerm().getDays(),
				prisonTerm.getYears(), prisonTerm.getMonths(), 
				prisonTerm.getDays());
		}
		if (probationTerm == null || sentence.getProbationTerm() == null) {
			assert probationTerm == null && sentence.getProbationTerm() == null 
				: String.format("Mismatched probation term nulls: %s found; %s "
						+ "expected", isNull(sentence.getProbationTerm()), 
						isNull(probationTerm));
		} else {
			assert probationTerm.equals(sentence.getProbationTerm()) : String
			.format("Wrong probation term: %d,%d,%d found; %d,%d,%d expected",
				sentence.getProbationTerm().getYears(), 
				sentence.getProbationTerm().getMonths(), 
				sentence.getProbationTerm().getDays(),
				probationTerm.getYears(), probationTerm.getMonths(), 
				probationTerm.getDays());
		}
		if (deferredTerm == null || sentence.getDeferredTerm() == null) {
			assert deferredTerm == null && sentence.getDeferredTerm() == null 
				: String.format("Mismatched deferred term nulls: %s found; %s "
						+ "expected", isNull(sentence.getDeferredTerm()), 
						isNull(deferredTerm));
		} else {
			assert deferredTerm.equals(sentence.getDeferredTerm()) : String
			.format("Wrong deferred term: %d,%d,%d found; %d,%d,%d expected",
				sentence.getDeferredTerm().getYears(), 
				sentence.getDeferredTerm().getMonths(), 
				sentence.getDeferredTerm().getDays(),
				deferredTerm.getYears(), deferredTerm.getMonths(), 
				deferredTerm.getDays());
		}
		assert category.equals(sentence.getCategory()) : String.format(
			"Wrong category: %s found; %s expected",
			sentence.getCategory().getName(), category.getName());
		assert lengthClassification.equals(sentence.getLengthClassification()) 
			: String.format("Wrong length classification: %s found; %s expected",
			sentence.getLengthClassification(), lengthClassification);
		assert legalDispositionCategory.equals(sentence
				.getLegalDispositionCategory()) : String.format(
					"Wrong legal disposition category: %s found; %s expected", 
					sentence.getLegalDispositionCategory().getName(), 
					legalDispositionCategory.getName());
		if (effectiveDate == null || sentence.getEffectiveDate() == null) {
			assert effectiveDate == null && sentence.getEffectiveDate() == null
				: String.format("Mismatched effective date nulls: %s found; %s "
						+ "expected", isNull(sentence.getEffectiveDate()), 
						isNull(effectiveDate));
		} else {
			assert effectiveDate.equals(sentence.getEffectiveDate()) 
				: String.format("Wrong effective date: %1$tm/%1$td/%1$ty found;"
					+ " %2$tm/%2$td/%2$ty expected", 
					sentence.getEffectiveDate(), effectiveDate);
		}
		if (pronouncementDate == null 
				|| sentence.getPronouncementDate() == null) {
			assert pronouncementDate == null 
					&& sentence.getPronouncementDate() == null
				: String.format("Mismatched pronouncement date nulls: %s found; "
					+ "%s expected", isNull(sentence.getPronouncementDate()), 
					isNull(pronouncementDate));
		} else {
			assert pronouncementDate.equals(sentence.getPronouncementDate()) 
				: String.format("Wrong pronouncement date: %1$tm/%1$td/%1$ty "
					+ "found; %2$tm/%2$td/%2$ty expected", 
					sentence.getPronouncementDate(), pronouncementDate);
		}
		if (credit == null || sentence.getCredit() == null) {
			assert credit == null && sentence.getCredit() == null 
					: String.format("Mismatched credit nulls: %s found; %s "
						+ "expected", isNull(sentence.getCredit()), 
						isNull(credit));
		} else {
			assert credit.equals(sentence.getCredit()) : String.format(
					"Wrong credit: %d/%d found; %d/%d expected", 
					sentence.getCredit().getJailTime(), 
					sentence.getCredit().getStreetTime(), 
					credit.getJailTime(), credit.getStreetTime());
		}
		if (turnSelfInDate == null || sentence.getTurnSelfInDate() == null) {
			assert turnSelfInDate == null 
					&& sentence.getTurnSelfInDate() == null
				: String.format("Mismatched turn self in date nulls: %s found; "
					+ "%s expected", isNull(sentence.getTurnSelfInDate()), 
					isNull(turnSelfInDate));
		} else {
			assert turnSelfInDate.equals(sentence.getTurnSelfInDate()) 
				: String.format("Wrong turn self in date: %1$tm/%1$td/%1$ty "
					+ "found; %2$tm/%2$td/%2$ty expected", 
					sentence.getTurnSelfInDate(), turnSelfInDate);
		}
		assert connection.equals(sentence.getConnection()) 
			: String.format("Wrong connection: %s found; %s expected", 
					sentence.getConnection().getClassification(), 
					connection.getClassification());
	}

	
	// Returns a string if the object is null or not null
	private String isNull(Object object) {
		return object == null ? "null" : "not null";
	}
}
