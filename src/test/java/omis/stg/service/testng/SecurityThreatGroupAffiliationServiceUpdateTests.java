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
import omis.stg.domain.SecurityThreatGroupChapter;
import omis.stg.domain.SecurityThreatGroupRank;
import omis.stg.service.SecurityThreatGroupAffiliationService;
import omis.stg.service.delegate.SecurityThreatGroupActivityLevelDelegate;
import omis.stg.service.delegate.SecurityThreatGroupAffiliationDelegate;
import omis.stg.service.delegate.SecurityThreatGroupChapterDelegate;
import omis.stg.service.delegate.SecurityThreatGroupDelegate;
import omis.stg.service.delegate.SecurityThreatGroupRankDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.service.delegate.UserAccountDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to update security threat group affiliations.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"stg", "service"})
public class SecurityThreatGroupAffiliationServiceUpdateTests
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
	
	/* Services. */
	
	@Autowired
	@Qualifier("securityThreatGroupAffiliationService")
	private SecurityThreatGroupAffiliationService 
		securityThreatGroupAffiliationService;

	/* Test methods. */
	
	/**
	 * Tests update of the date range for a security threat group affiliation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateDateRange() throws DuplicateEntityFoundException {
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
		DateRange newDateRange = new DateRange(this.parseDateText("02/01/2017"), 
				this.parseDateText("07/01/2017"));
		
		// Action
		affiliation = this.securityThreatGroupAffiliationService.update(
				affiliation, newDateRange, group, activityLevel, chapter, rank, 
				state, city, moniker, comment, verificationSignature);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("activityLevel", activityLevel)
			.addExpectedValue("chapter", chapter)
			.addExpectedValue("city", city)
			.addExpectedValue("comment", comment)
			.addExpectedValue("dateRange", newDateRange)
			.addExpectedValue("group", group)
			.addExpectedValue("moniker", moniker)
			.addExpectedValue("rank", rank)
			.addExpectedValue("state", state)
			.addExpectedValue("verificationSignature", verificationSignature)
			.performAssertions(affiliation);
	}
	
	/**
	 * Tests update of the group for a security threat group affiliation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateGroup() throws DuplicateEntityFoundException {
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
		SecurityThreatGroup newGroup = this.securityThreatGroupDelegate.create(
				"New Group", state);
		
		// Action
		affiliation = this.securityThreatGroupAffiliationService.update(
				affiliation, dateRange, newGroup, activityLevel, chapter, rank, 
				state, city, moniker, comment, verificationSignature);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("activityLevel", activityLevel)
			.addExpectedValue("chapter", chapter)
			.addExpectedValue("city", city)
			.addExpectedValue("comment", comment)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("group", newGroup)
			.addExpectedValue("moniker", moniker)
			.addExpectedValue("rank", rank)
			.addExpectedValue("state", state)
			.addExpectedValue("verificationSignature", verificationSignature)
			.performAssertions(affiliation);
	}
	
	/**
	 * Tests update of the activity level for a security threat group affiliation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateActivityLevel() throws DuplicateEntityFoundException {
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
		SecurityThreatGroupActivityLevel newActivityLevel = 
				this.securityThreatGroupActivityLevelDelegate.create("Level 2");
		
		// Action
		affiliation = this.securityThreatGroupAffiliationService.update(
				affiliation, dateRange, group, newActivityLevel, chapter, rank, 
				state, city, moniker, comment, verificationSignature);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("activityLevel", newActivityLevel)
			.addExpectedValue("chapter", chapter)
			.addExpectedValue("city", city)
			.addExpectedValue("comment", comment)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("group", group)
			.addExpectedValue("moniker", moniker)
			.addExpectedValue("rank", rank)
			.addExpectedValue("state", state)
			.addExpectedValue("verificationSignature", verificationSignature)
			.performAssertions(affiliation);
	}
	
	/**
	 * Tests update of the chapter for a security threat group affiliation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateChapter() throws DuplicateEntityFoundException {
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
		SecurityThreatGroupChapter newChapter = 
				this.securityThreatGroupChapterDelegate.create("Chapter 2", 
						group);
		
		// Action
		affiliation = this.securityThreatGroupAffiliationService.update(
				affiliation, dateRange, group, activityLevel, newChapter, rank, 
				state, city, moniker, comment, verificationSignature);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("activityLevel", activityLevel)
			.addExpectedValue("chapter", newChapter)
			.addExpectedValue("city", city)
			.addExpectedValue("comment", comment)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("group", group)
			.addExpectedValue("moniker", moniker)
			.addExpectedValue("rank", rank)
			.addExpectedValue("state", state)
			.addExpectedValue("verificationSignature", verificationSignature)
			.performAssertions(affiliation);
	}
	
	/**
	 * Tests update of the rank for a security threat group affiliation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateRank() throws DuplicateEntityFoundException {
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
		SecurityThreatGroupRank newRank = this.securityThreatGroupRankDelegate
				.create("Rank 2", group);
		
		// Action
		affiliation = this.securityThreatGroupAffiliationService.update(
				affiliation, dateRange, group, activityLevel, chapter, newRank, 
				state, city, moniker, comment, verificationSignature);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("activityLevel", activityLevel)
			.addExpectedValue("chapter", chapter)
			.addExpectedValue("city", city)
			.addExpectedValue("comment", comment)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("group", group)
			.addExpectedValue("moniker", moniker)
			.addExpectedValue("rank", newRank)
			.addExpectedValue("state", state)
			.addExpectedValue("verificationSignature", verificationSignature)
			.performAssertions(affiliation);
	}
	
	/**
	 * Tests update of the state for a security threat group affiliation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateState() throws DuplicateEntityFoundException {
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
		State newState = this.stateDelegate.create("State 2", "ST", country, 
				true, true);
		
		// Action
		affiliation = this.securityThreatGroupAffiliationService.update(
				affiliation, dateRange, group, activityLevel, chapter, rank, 
				newState, city, moniker, comment, verificationSignature);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("activityLevel", activityLevel)
			.addExpectedValue("chapter", chapter)
			.addExpectedValue("city", city)
			.addExpectedValue("comment", comment)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("group", group)
			.addExpectedValue("moniker", moniker)
			.addExpectedValue("rank", rank)
			.addExpectedValue("state", newState)
			.addExpectedValue("verificationSignature", verificationSignature)
			.performAssertions(affiliation);
	}
	
	/**
	 * Tests update of the city for a security threat group affiliation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateCity() throws DuplicateEntityFoundException {
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
		City newCity = this.cityDelegate.create("City 2", true, state, country);
		
		// Action
		affiliation = this.securityThreatGroupAffiliationService.update(
				affiliation, dateRange, group, activityLevel, chapter, rank, 
				state, newCity, moniker, comment, verificationSignature);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("activityLevel", activityLevel)
			.addExpectedValue("chapter", chapter)
			.addExpectedValue("city", newCity)
			.addExpectedValue("comment", comment)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("group", group)
			.addExpectedValue("moniker", moniker)
			.addExpectedValue("rank", rank)
			.addExpectedValue("state", state)
			.addExpectedValue("verificationSignature", verificationSignature)
			.performAssertions(affiliation);
	}
	
	/**
	 * Tests update of the moniker for a security threat group affiliation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateMoniker() throws DuplicateEntityFoundException {
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
		String newMoniker = "New moniker";
		
		// Action
		affiliation = this.securityThreatGroupAffiliationService.update(
				affiliation, dateRange, group, activityLevel, chapter, rank, 
				state, city, newMoniker, comment, verificationSignature);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("activityLevel", activityLevel)
			.addExpectedValue("chapter", chapter)
			.addExpectedValue("city", city)
			.addExpectedValue("comment", comment)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("group", group)
			.addExpectedValue("moniker", newMoniker)
			.addExpectedValue("rank", rank)
			.addExpectedValue("state", state)
			.addExpectedValue("verificationSignature", verificationSignature)
			.performAssertions(affiliation);
	}
	
	/**
	 * Tests update of the comment for a security threat group affiliation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateComment() throws DuplicateEntityFoundException {
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
		String newComment = "New comment";
		
		// Action
		affiliation = this.securityThreatGroupAffiliationService.update(
				affiliation, dateRange, group, activityLevel, chapter, rank, 
				state, city, moniker, newComment, verificationSignature);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("activityLevel", activityLevel)
			.addExpectedValue("chapter", chapter)
			.addExpectedValue("city", city)
			.addExpectedValue("comment", newComment)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("group", group)
			.addExpectedValue("moniker", moniker)
			.addExpectedValue("rank", rank)
			.addExpectedValue("state", state)
			.addExpectedValue("verificationSignature", verificationSignature)
			.performAssertions(affiliation);
	}
	
	/**
	 * Tests update of the verification signature for a security threat group 
	 * affiliation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateVerificationSignature() 
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
		VerificationSignature newVerificationSignature = 
				new VerificationSignature(
				this.accountDelegate.findByUsername("AUDIT"), 
				this.parseDateText("02/01/2017"), false, method);
		
		// Action
		affiliation = this.securityThreatGroupAffiliationService.update(
				affiliation, dateRange, group, activityLevel, chapter, rank, 
				state, city, moniker, comment, newVerificationSignature);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("activityLevel", activityLevel)
			.addExpectedValue("chapter", chapter)
			.addExpectedValue("city", city)
			.addExpectedValue("comment", comment)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("group", group)
			.addExpectedValue("moniker", moniker)
			.addExpectedValue("rank", rank)
			.addExpectedValue("state", state)
			.addExpectedValue("verificationSignature", newVerificationSignature)
			.performAssertions(affiliation);
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
		this.securityThreatGroupAffiliationDelegate.create(offender, dateRange, 
				group, activityLevel, chapter, rank, state, city, moniker, 
				comment, verificationSignature);
		DateRange secondDateRange = new DateRange(
				this.parseDateText("02/01/2017"), 
				this.parseDateText("08/01/2017"));
		SecurityThreatGroupAffiliation affiliation = 
				this.securityThreatGroupAffiliationDelegate.create(offender, 
						secondDateRange, group, activityLevel, chapter, rank, 
						state, city, moniker, comment, verificationSignature);
	
		// Action
		affiliation = this.securityThreatGroupAffiliationService.update(
				affiliation, dateRange, group, activityLevel, chapter, rank, 
				state, city, moniker, comment, verificationSignature);
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