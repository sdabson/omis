package omis.stg.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.audit.domain.VerificationMethod;
import omis.audit.domain.VerificationSignature;
import omis.audit.service.delegate.VerificationMethodDelegate;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupActivityLevel;
import omis.stg.domain.SecurityThreatGroupAffiliation;
import omis.stg.domain.SecurityThreatGroupAffiliationNote;
import omis.stg.domain.SecurityThreatGroupChapter;
import omis.stg.domain.SecurityThreatGroupRank;
import omis.stg.service.SecurityThreatGroupAffiliationService;
import omis.stg.service.delegate.SecurityThreatGroupActivityLevelDelegate;
import omis.stg.service.delegate.SecurityThreatGroupAffiliationDelegate;
import omis.stg.service.delegate.SecurityThreatGroupAffiliationNoteDelegate;
import omis.stg.service.delegate.SecurityThreatGroupChapterDelegate;
import omis.stg.service.delegate.SecurityThreatGroupDelegate;
import omis.stg.service.delegate.SecurityThreatGroupRankDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.service.delegate.UserAccountDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to update security threat group affiliation notes.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"stg", "service"})
public class SecurityThreatGroupAffiliationServiceUpdateNoteTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */

	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupDelegate")
	private SecurityThreatGroupDelegate securityThreatGroupDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	@Autowired
	@Qualifier("cityDelegate")
	private CityDelegate cityDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupActivityLevelDelegate")
	private SecurityThreatGroupActivityLevelDelegate 
		securityThreatGroupActivityLevelDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupChapterDelegate")
	private SecurityThreatGroupChapterDelegate securityThreatGroupChapterDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupRankDelegate")
	private SecurityThreatGroupRankDelegate securityThreatGroupRankDelegate;
	
	@Autowired
	@Qualifier("userAccountDelegate")
	private UserAccountDelegate accountDelegate;
	
	@Autowired
	@Qualifier("verificationMethodDelegate")
	private VerificationMethodDelegate verificationMethodDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupAffiliationDelegate")
	private SecurityThreatGroupAffiliationDelegate 
		securityThreatGroupAffiliationDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupAffiliationNoteDelegate")
	private SecurityThreatGroupAffiliationNoteDelegate 
		securityThreatGroupAffiliationNoteDelegate;
	
	/* Services. */
	@Autowired
	@Qualifier("securityThreatGroupAffiliationService")
	private SecurityThreatGroupAffiliationService securityThreatGroupAffiliationService;

	/* Test methods. */
	
	/**
	 * Tests update of the date for a security threat group affiliation note.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateNoteDate() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("07/01/2017"));
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "ST", country, true, 
				true);
		SecurityThreatGroup group = this.securityThreatGroupDelegate.create(
				"Group", state);
		City city = this.cityDelegate.create("City", true, state, country);
		SecurityThreatGroupActivityLevel activityLevel = 
				this.securityThreatGroupActivityLevelDelegate.create("Level");
		SecurityThreatGroupChapter chapter = 
				this.securityThreatGroupChapterDelegate.create("Chapter", 
						group);
		SecurityThreatGroupRank rank = this.securityThreatGroupRankDelegate
				.create("Rank", group);
		String moniker = "Moniker";
		String comment = "Comment";
		VerificationMethod method = this.verificationMethodDelegate.create(
				"Method", (short) 1, true);
		VerificationSignature verificationSignature = new VerificationSignature(
				this.accountDelegate.findByUsername("AUDIT"), 
				this.parseDateText("02/01/2017"), true, method);
		SecurityThreatGroupAffiliation affiliation = 
				this.securityThreatGroupAffiliationDelegate.create(offender,
						dateRange, group, activityLevel, chapter, rank, state, 
						city, moniker, comment, verificationSignature);
		Date date = this.parseDateText("03/01/2017");
		String note = "Note";
		SecurityThreatGroupAffiliationNote affiliationNote = 
				this.securityThreatGroupAffiliationNoteDelegate.addNote(
						affiliation, date, note);
		Date newDate = this.parseDateText("03/02/2017");

		// Action
		affiliationNote = this.securityThreatGroupAffiliationService.updateNote(
						affiliationNote, newDate, note);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("affiliation", affiliation)
			.addExpectedValue("date", newDate)
			.addExpectedValue("note", note)
			.performAssertions(affiliationNote);
	}

	/**
	 * Tests update of the note for a security threat group affiliation note.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateNoteNote() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("07/01/2017"));
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "ST", country, true, 
				true);
		SecurityThreatGroup group = this.securityThreatGroupDelegate.create(
				"Group", state);
		City city = this.cityDelegate.create("City", true, state, country);
		SecurityThreatGroupActivityLevel activityLevel = 
				this.securityThreatGroupActivityLevelDelegate.create("Level");
		SecurityThreatGroupChapter chapter = 
				this.securityThreatGroupChapterDelegate.create("Chapter", 
						group);
		SecurityThreatGroupRank rank = this.securityThreatGroupRankDelegate
				.create("Rank", group);
		String moniker = "Moniker";
		String comment = "Comment";
		VerificationMethod method = this.verificationMethodDelegate.create(
				"Method", (short) 1, true);
		VerificationSignature verificationSignature = new VerificationSignature(
				this.accountDelegate.findByUsername("AUDIT"), 
				this.parseDateText("02/01/2017"), true, method);
		SecurityThreatGroupAffiliation affiliation = 
				this.securityThreatGroupAffiliationDelegate.create(offender,
						dateRange, group, activityLevel, chapter, rank, state, 
						city, moniker, comment, verificationSignature);
		Date date = this.parseDateText("03/01/2017");
		String note = "Note";
		SecurityThreatGroupAffiliationNote affiliationNote = 
				this.securityThreatGroupAffiliationNoteDelegate.addNote(
						affiliation, date, note);
		String newNote = "New note";

		// Action
		affiliationNote = this.securityThreatGroupAffiliationService.updateNote(
						affiliationNote, date, newNote);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("affiliation", affiliation)
			.addExpectedValue("date", date)
			.addExpectedValue("note", newNote)
			.performAssertions(affiliationNote);
	}
	
	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("07/01/2017"));
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "ST", country, true, 
				true);
		SecurityThreatGroup group = this.securityThreatGroupDelegate.create(
				"Group", state);
		City city = this.cityDelegate.create("City", true, state, country);
		SecurityThreatGroupActivityLevel activityLevel = 
				this.securityThreatGroupActivityLevelDelegate.create("Level");
		SecurityThreatGroupChapter chapter = 
				this.securityThreatGroupChapterDelegate.create("Chapter", 
						group);
		SecurityThreatGroupRank rank = this.securityThreatGroupRankDelegate
				.create("Rank", group);
		String moniker = "Moniker";
		String comment = "Comment";
		VerificationMethod method = this.verificationMethodDelegate.create(
				"Method", (short) 1, true);
		VerificationSignature verificationSignature = new VerificationSignature(
				this.accountDelegate.findByUsername("AUDIT"), 
				this.parseDateText("02/01/2017"), true, method);
		SecurityThreatGroupAffiliation affiliation = 
				this.securityThreatGroupAffiliationDelegate.create(offender,
						dateRange, group, activityLevel, chapter, rank, state, 
						city, moniker, comment, verificationSignature);
		Date date = this.parseDateText("03/01/2017");
		String note = "Note";
		this.securityThreatGroupAffiliationNoteDelegate.addNote(affiliation, 
				date, note);
		Date secondDate = this.parseDateText("04/01/2017");
		SecurityThreatGroupAffiliationNote affiliationNote = 
				this.securityThreatGroupAffiliationNoteDelegate.addNote(
						affiliation, secondDate, note);

		// Action
		affiliationNote = this.securityThreatGroupAffiliationService.updateNote(
						affiliationNote, date, note);

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