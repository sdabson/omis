package omis.prisonterm.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.prisonterm.domain.PrisonTerm;
import omis.prisonterm.domain.PrisonTermStatus;
import omis.prisonterm.exception.ActivePrisonTermExistsException;
import omis.prisonterm.service.PrisonTermService;
import omis.prisonterm.service.delegate.PrisonTermDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to update prison terms.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"prisonterm", "service"})
public class PrisonTermServiceUpdateTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Service delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	

	@Autowired
	@Qualifier("userAccountDelegate")
	private UserAccountDelegate userAccountDelegate;

	@Autowired
	@Qualifier("prisonTermDelegate")
	private PrisonTermDelegate prisonTermDelegate;

	/* Service to test. */
	
	@Autowired
	@Qualifier("prisonTermService")
	private PrisonTermService prisonTermService;
	
	/* Test methods. */
	
	/**
	 * Tests the update of action date for a prison term.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ActivePrisonTermExistsException thrown when active prison term 
	 * exists
	 */
	@Test
	public void testUpdateActionDate() throws DuplicateEntityFoundException, 
			ActivePrisonTermExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date actionDate = this.parseDateText("01/01/2017");
		Integer preSentenceCredits = 0;
		Date sentenceDate = this.parseDateText("01/05/2017");
		Integer sentenceTermYears = 15;
		Integer sentenceTermDays = 0;
		Date paroleEligibilityDate = this.parseDateText("01/05/2027");
		Date projectedDischargeDate = this.parseDateText("01/05/2032");
		Date maximumDischargeDate = this.parseDateText("01/05/2032");
		PrisonTermStatus status = PrisonTermStatus.ACTIVE;
		Boolean sentenceToFollow = false;
		String comments = "Comments go here";
		UserAccount verificationUser = this.userAccountDelegate
				.findByUsername("AUDIT");
		Date verificationDate = this.parseDateText("02/01/2017");
		PrisonTerm prisonTerm = this.prisonTermDelegate.create(offender, 
				actionDate, preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		Date newActionDate = this.parseDateText("01/02/2017");
		
		// Action
		prisonTerm = this.prisonTermService.update(prisonTerm, newActionDate, 
				preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("actionDate", newActionDate)
		.addExpectedValue("preSentenceCredits", preSentenceCredits)
		.addExpectedValue("sentenceDate", sentenceDate)
		.addExpectedValue("sentenceTermYears", sentenceTermYears)
		.addExpectedValue("sentenceTermDays", sentenceTermDays)
		.addExpectedValue("paroleEligibilityDate", paroleEligibilityDate)
		.addExpectedValue("projectedDischargeDate", projectedDischargeDate)
		.addExpectedValue("maximumDischargeDate", maximumDischargeDate)
		.addExpectedValue("status", status)
		.addExpectedValue("sentenceToFollow", sentenceToFollow)
		.addExpectedValue("comments", comments)
		.addExpectedValue("verificationUser", verificationUser)
		.addExpectedValue("verificationDate", verificationDate)
		.performAssertions(prisonTerm);
	}
	
	/**
	 * Tests the update of pre sentence credits for a prison term.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ActivePrisonTermExistsException thrown when active prison term 
	 * exists
	 */
	@Test
	public void testUpdatePreSentenceCredits() throws DuplicateEntityFoundException, 
			ActivePrisonTermExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date actionDate = this.parseDateText("01/01/2017");
		Integer preSentenceCredits = 0;
		Date sentenceDate = this.parseDateText("01/05/2017");
		Integer sentenceTermYears = 15;
		Integer sentenceTermDays = 0;
		Date paroleEligibilityDate = this.parseDateText("01/05/2027");
		Date projectedDischargeDate = this.parseDateText("01/05/2032");
		Date maximumDischargeDate = this.parseDateText("01/05/2032");
		PrisonTermStatus status = PrisonTermStatus.ACTIVE;
		Boolean sentenceToFollow = false;
		String comments = "Comments go here";
		UserAccount verificationUser = this.userAccountDelegate
				.findByUsername("AUDIT");
		Date verificationDate = this.parseDateText("02/01/2017");
		PrisonTerm prisonTerm = this.prisonTermDelegate.create(offender, 
				actionDate, preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		Integer newPreSentenceCredits = 1;
		
		// Action
		prisonTerm = this.prisonTermService.update(prisonTerm, actionDate, 
				newPreSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("actionDate", actionDate)
		.addExpectedValue("preSentenceCredits", newPreSentenceCredits)
		.addExpectedValue("sentenceDate", sentenceDate)
		.addExpectedValue("sentenceTermYears", sentenceTermYears)
		.addExpectedValue("sentenceTermDays", sentenceTermDays)
		.addExpectedValue("paroleEligibilityDate", paroleEligibilityDate)
		.addExpectedValue("projectedDischargeDate", projectedDischargeDate)
		.addExpectedValue("maximumDischargeDate", maximumDischargeDate)
		.addExpectedValue("status", status)
		.addExpectedValue("sentenceToFollow", sentenceToFollow)
		.addExpectedValue("comments", comments)
		.addExpectedValue("verificationUser", verificationUser)
		.addExpectedValue("verificationDate", verificationDate)
		.performAssertions(prisonTerm);
	}
	
	/**
	 * Tests the update of sentence date for a prison term.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ActivePrisonTermExistsException thrown when active prison term 
	 * exists
	 */
	@Test
	public void testUpdateSentenceDate() throws DuplicateEntityFoundException, 
			ActivePrisonTermExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date actionDate = this.parseDateText("01/01/2017");
		Integer preSentenceCredits = 0;
		Date sentenceDate = this.parseDateText("01/05/2017");
		Integer sentenceTermYears = 15;
		Integer sentenceTermDays = 0;
		Date paroleEligibilityDate = this.parseDateText("01/05/2027");
		Date projectedDischargeDate = this.parseDateText("01/05/2032");
		Date maximumDischargeDate = this.parseDateText("01/05/2032");
		PrisonTermStatus status = PrisonTermStatus.ACTIVE;
		Boolean sentenceToFollow = false;
		String comments = "Comments go here";
		UserAccount verificationUser = this.userAccountDelegate
				.findByUsername("AUDIT");
		Date verificationDate = this.parseDateText("02/01/2017");
		PrisonTerm prisonTerm = this.prisonTermDelegate.create(offender, 
				actionDate, preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		Date newSentenceDate = this.parseDateText("01/06/2017");
		
		// Action
		prisonTerm = this.prisonTermService.update(prisonTerm, actionDate, 
				preSentenceCredits, newSentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("actionDate", actionDate)
		.addExpectedValue("preSentenceCredits", preSentenceCredits)
		.addExpectedValue("sentenceDate", newSentenceDate)
		.addExpectedValue("sentenceTermYears", sentenceTermYears)
		.addExpectedValue("sentenceTermDays", sentenceTermDays)
		.addExpectedValue("paroleEligibilityDate", paroleEligibilityDate)
		.addExpectedValue("projectedDischargeDate", projectedDischargeDate)
		.addExpectedValue("maximumDischargeDate", maximumDischargeDate)
		.addExpectedValue("status", status)
		.addExpectedValue("sentenceToFollow", sentenceToFollow)
		.addExpectedValue("comments", comments)
		.addExpectedValue("verificationUser", verificationUser)
		.addExpectedValue("verificationDate", verificationDate)
		.performAssertions(prisonTerm);
	}
	/**
	 * Tests the update of sentence term years for a prison term.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ActivePrisonTermExistsException thrown when active prison term 
	 * exists
	 */
	@Test
	public void testUpdateSentenceTermYears() 
			throws DuplicateEntityFoundException, 
			ActivePrisonTermExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date actionDate = this.parseDateText("01/01/2017");
		Integer preSentenceCredits = 0;
		Date sentenceDate = this.parseDateText("01/05/2017");
		Integer sentenceTermYears = 15;
		Integer sentenceTermDays = 0;
		Date paroleEligibilityDate = this.parseDateText("01/05/2027");
		Date projectedDischargeDate = this.parseDateText("01/05/2032");
		Date maximumDischargeDate = this.parseDateText("01/05/2032");
		PrisonTermStatus status = PrisonTermStatus.ACTIVE;
		Boolean sentenceToFollow = false;
		String comments = "Comments go here";
		UserAccount verificationUser = this.userAccountDelegate
				.findByUsername("AUDIT");
		Date verificationDate = this.parseDateText("02/01/2017");
		PrisonTerm prisonTerm = this.prisonTermDelegate.create(offender, 
				actionDate, preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		Integer newSentenceTermYears = 20;
		
		// Action
		prisonTerm = this.prisonTermService.update(prisonTerm, actionDate, 
				preSentenceCredits, sentenceDate, newSentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("actionDate", actionDate)
		.addExpectedValue("preSentenceCredits", preSentenceCredits)
		.addExpectedValue("sentenceDate", sentenceDate)
		.addExpectedValue("sentenceTermYears", newSentenceTermYears)
		.addExpectedValue("sentenceTermDays", sentenceTermDays)
		.addExpectedValue("paroleEligibilityDate", paroleEligibilityDate)
		.addExpectedValue("projectedDischargeDate", projectedDischargeDate)
		.addExpectedValue("maximumDischargeDate", maximumDischargeDate)
		.addExpectedValue("status", status)
		.addExpectedValue("sentenceToFollow", sentenceToFollow)
		.addExpectedValue("comments", comments)
		.addExpectedValue("verificationUser", verificationUser)
		.addExpectedValue("verificationDate", verificationDate)
		.performAssertions(prisonTerm);
	}
	/**
	 * Tests the update of sentence term days for a prison term.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ActivePrisonTermExistsException thrown when active prison term 
	 * exists
	 */
	@Test
	public void testUpdateSentenceTermDays() 
			throws DuplicateEntityFoundException, 
			ActivePrisonTermExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date actionDate = this.parseDateText("01/01/2017");
		Integer preSentenceCredits = 0;
		Date sentenceDate = this.parseDateText("01/05/2017");
		Integer sentenceTermYears = 15;
		Integer sentenceTermDays = 0;
		Date paroleEligibilityDate = this.parseDateText("01/05/2027");
		Date projectedDischargeDate = this.parseDateText("01/05/2032");
		Date maximumDischargeDate = this.parseDateText("01/05/2032");
		PrisonTermStatus status = PrisonTermStatus.ACTIVE;
		Boolean sentenceToFollow = false;
		String comments = "Comments go here";
		UserAccount verificationUser = this.userAccountDelegate
				.findByUsername("AUDIT");
		Date verificationDate = this.parseDateText("02/01/2017");
		PrisonTerm prisonTerm = this.prisonTermDelegate.create(offender, 
				actionDate, preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		Integer newSentenceTermDays = 180;
		
		// Action
		prisonTerm = this.prisonTermService.update(prisonTerm, actionDate, 
				preSentenceCredits, sentenceDate, sentenceTermYears, 
				newSentenceTermDays, paroleEligibilityDate, 
				projectedDischargeDate, maximumDischargeDate, status,  
				sentenceToFollow, comments, verificationUser, verificationDate);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("actionDate", actionDate)
		.addExpectedValue("preSentenceCredits", preSentenceCredits)
		.addExpectedValue("sentenceDate", sentenceDate)
		.addExpectedValue("sentenceTermYears", sentenceTermYears)
		.addExpectedValue("sentenceTermDays", newSentenceTermDays)
		.addExpectedValue("paroleEligibilityDate", paroleEligibilityDate)
		.addExpectedValue("projectedDischargeDate", projectedDischargeDate)
		.addExpectedValue("maximumDischargeDate", maximumDischargeDate)
		.addExpectedValue("status", status)
		.addExpectedValue("sentenceToFollow", sentenceToFollow)
		.addExpectedValue("comments", comments)
		.addExpectedValue("verificationUser", verificationUser)
		.addExpectedValue("verificationDate", verificationDate)
		.performAssertions(prisonTerm);
	}
	/**
	 * Tests the update of parole eligibility date for a prison term.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ActivePrisonTermExistsException thrown when active prison term 
	 * exists
	 */
	@Test
	public void testUpdateParoleEligibilityDate() 
			throws DuplicateEntityFoundException, 
			ActivePrisonTermExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date actionDate = this.parseDateText("01/01/2017");
		Integer preSentenceCredits = 0;
		Date sentenceDate = this.parseDateText("01/05/2017");
		Integer sentenceTermYears = 15;
		Integer sentenceTermDays = 0;
		Date paroleEligibilityDate = this.parseDateText("01/05/2027");
		Date projectedDischargeDate = this.parseDateText("01/05/2032");
		Date maximumDischargeDate = this.parseDateText("01/05/2032");
		PrisonTermStatus status = PrisonTermStatus.ACTIVE;
		Boolean sentenceToFollow = false;
		String comments = "Comments go here";
		UserAccount verificationUser = this.userAccountDelegate
				.findByUsername("AUDIT");
		Date verificationDate = this.parseDateText("02/01/2017");
		PrisonTerm prisonTerm = this.prisonTermDelegate.create(offender, 
				actionDate, preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		Date newParoleEligibilityDate = this.parseDateText("01/05/2037");
		
		// Action
		prisonTerm = this.prisonTermService.update(prisonTerm, actionDate, 
				preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, newParoleEligibilityDate, 
				projectedDischargeDate, maximumDischargeDate, status,  
				sentenceToFollow, comments, verificationUser, verificationDate);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("actionDate", actionDate)
		.addExpectedValue("preSentenceCredits", preSentenceCredits)
		.addExpectedValue("sentenceDate", sentenceDate)
		.addExpectedValue("sentenceTermYears", sentenceTermYears)
		.addExpectedValue("sentenceTermDays", sentenceTermDays)
		.addExpectedValue("paroleEligibilityDate", newParoleEligibilityDate)
		.addExpectedValue("projectedDischargeDate", projectedDischargeDate)
		.addExpectedValue("maximumDischargeDate", maximumDischargeDate)
		.addExpectedValue("status", status)
		.addExpectedValue("sentenceToFollow", sentenceToFollow)
		.addExpectedValue("comments", comments)
		.addExpectedValue("verificationUser", verificationUser)
		.addExpectedValue("verificationDate", verificationDate)
		.performAssertions(prisonTerm);
	}
	/**
	 * Tests the update of projected discharge date for a prison term.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ActivePrisonTermExistsException thrown when active prison term 
	 * exists
	 */
	@Test
	public void testUpdateProjectedDischargeDate() 
			throws DuplicateEntityFoundException, 
			ActivePrisonTermExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date actionDate = this.parseDateText("01/01/2017");
		Integer preSentenceCredits = 0;
		Date sentenceDate = this.parseDateText("01/05/2017");
		Integer sentenceTermYears = 15;
		Integer sentenceTermDays = 0;
		Date paroleEligibilityDate = this.parseDateText("01/05/2027");
		Date projectedDischargeDate = this.parseDateText("01/05/2032");
		Date maximumDischargeDate = this.parseDateText("01/05/2032");
		PrisonTermStatus status = PrisonTermStatus.ACTIVE;
		Boolean sentenceToFollow = false;
		String comments = "Comments go here";
		UserAccount verificationUser = this.userAccountDelegate
				.findByUsername("AUDIT");
		Date verificationDate = this.parseDateText("02/01/2017");
		PrisonTerm prisonTerm = this.prisonTermDelegate.create(offender, 
				actionDate, preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		Date newProjectedDischargeDate = this.parseDateText("01/05/2022");
		
		// Action
		prisonTerm = this.prisonTermService.update(prisonTerm, actionDate, 
				preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, 
				newProjectedDischargeDate, maximumDischargeDate, status,  
				sentenceToFollow, comments, verificationUser, verificationDate);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("actionDate", actionDate)
		.addExpectedValue("preSentenceCredits", preSentenceCredits)
		.addExpectedValue("sentenceDate", sentenceDate)
		.addExpectedValue("sentenceTermYears", sentenceTermYears)
		.addExpectedValue("sentenceTermDays", sentenceTermDays)
		.addExpectedValue("paroleEligibilityDate", paroleEligibilityDate)
		.addExpectedValue("projectedDischargeDate", newProjectedDischargeDate)
		.addExpectedValue("maximumDischargeDate", maximumDischargeDate)
		.addExpectedValue("status", status)
		.addExpectedValue("sentenceToFollow", sentenceToFollow)
		.addExpectedValue("comments", comments)
		.addExpectedValue("verificationUser", verificationUser)
		.addExpectedValue("verificationDate", verificationDate)
		.performAssertions(prisonTerm);
	}
	/**
	 * Tests the update of maximum discharge date for a prison term.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ActivePrisonTermExistsException thrown when active prison term 
	 * exists
	 */
	@Test
	public void testUpdateMaximumDischargeDate() 
			throws DuplicateEntityFoundException, 
			ActivePrisonTermExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date actionDate = this.parseDateText("01/01/2017");
		Integer preSentenceCredits = 0;
		Date sentenceDate = this.parseDateText("01/05/2017");
		Integer sentenceTermYears = 15;
		Integer sentenceTermDays = 0;
		Date paroleEligibilityDate = this.parseDateText("01/05/2027");
		Date projectedDischargeDate = this.parseDateText("01/05/2032");
		Date maximumDischargeDate = this.parseDateText("01/05/2032");
		PrisonTermStatus status = PrisonTermStatus.ACTIVE;
		Boolean sentenceToFollow = false;
		String comments = "Comments go here";
		UserAccount verificationUser = this.userAccountDelegate
				.findByUsername("AUDIT");
		Date verificationDate = this.parseDateText("02/01/2017");
		PrisonTerm prisonTerm = this.prisonTermDelegate.create(offender, 
				actionDate, preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		Date newMaximumDischargeDate = this.parseDateText("01/05/2035");
		
		// Action
		prisonTerm = this.prisonTermService.update(prisonTerm, actionDate, 
				preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				newMaximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("actionDate", actionDate)
		.addExpectedValue("preSentenceCredits", preSentenceCredits)
		.addExpectedValue("sentenceDate", sentenceDate)
		.addExpectedValue("sentenceTermYears", sentenceTermYears)
		.addExpectedValue("sentenceTermDays", sentenceTermDays)
		.addExpectedValue("paroleEligibilityDate", paroleEligibilityDate)
		.addExpectedValue("projectedDischargeDate", projectedDischargeDate)
		.addExpectedValue("maximumDischargeDate", newMaximumDischargeDate)
		.addExpectedValue("status", status)
		.addExpectedValue("sentenceToFollow", sentenceToFollow)
		.addExpectedValue("comments", comments)
		.addExpectedValue("verificationUser", verificationUser)
		.addExpectedValue("verificationDate", verificationDate)
		.performAssertions(prisonTerm);
	}
	/**
	 * Tests the update of status for a prison term.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ActivePrisonTermExistsException thrown when active prison term 
	 * exists
	 */
	@Test
	public void testUpdateStatus() throws DuplicateEntityFoundException, 
			ActivePrisonTermExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date actionDate = this.parseDateText("01/01/2017");
		Integer preSentenceCredits = 0;
		Date sentenceDate = this.parseDateText("01/05/2017");
		Integer sentenceTermYears = 15;
		Integer sentenceTermDays = 0;
		Date paroleEligibilityDate = this.parseDateText("01/05/2027");
		Date projectedDischargeDate = this.parseDateText("01/05/2032");
		Date maximumDischargeDate = this.parseDateText("01/05/2032");
		PrisonTermStatus status = PrisonTermStatus.ACTIVE;
		Boolean sentenceToFollow = false;
		String comments = "Comments go here";
		UserAccount verificationUser = this.userAccountDelegate
				.findByUsername("AUDIT");
		Date verificationDate = this.parseDateText("02/01/2017");
		PrisonTerm prisonTerm = this.prisonTermDelegate.create(offender, 
				actionDate, preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		PrisonTermStatus newStatus = PrisonTermStatus.HISTORICAL;
		
		// Action
		prisonTerm = this.prisonTermService.update(prisonTerm, actionDate, 
				preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, newStatus, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("actionDate", actionDate)
		.addExpectedValue("preSentenceCredits", preSentenceCredits)
		.addExpectedValue("sentenceDate", sentenceDate)
		.addExpectedValue("sentenceTermYears", sentenceTermYears)
		.addExpectedValue("sentenceTermDays", sentenceTermDays)
		.addExpectedValue("paroleEligibilityDate", paroleEligibilityDate)
		.addExpectedValue("projectedDischargeDate", projectedDischargeDate)
		.addExpectedValue("maximumDischargeDate", maximumDischargeDate)
		.addExpectedValue("status", newStatus)
		.addExpectedValue("sentenceToFollow", sentenceToFollow)
		.addExpectedValue("comments", comments)
		.addExpectedValue("verificationUser", verificationUser)
		.addExpectedValue("verificationDate", verificationDate)
		.performAssertions(prisonTerm);
	}

	/**
	 * Tests the update of sentence to follow for a prison term.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ActivePrisonTermExistsException thrown when active prison term 
	 * exists
	 */
	@Test
	public void testUpdateSentenceToFollow() 
			throws DuplicateEntityFoundException, 
			ActivePrisonTermExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date actionDate = this.parseDateText("01/01/2017");
		Integer preSentenceCredits = 0;
		Date sentenceDate = this.parseDateText("01/05/2017");
		Integer sentenceTermYears = 15;
		Integer sentenceTermDays = 0;
		Date paroleEligibilityDate = this.parseDateText("01/05/2027");
		Date projectedDischargeDate = this.parseDateText("01/05/2032");
		Date maximumDischargeDate = this.parseDateText("01/05/2032");
		PrisonTermStatus status = PrisonTermStatus.ACTIVE;
		Boolean sentenceToFollow = false;
		String comments = "Comments go here";
		UserAccount verificationUser = this.userAccountDelegate
				.findByUsername("AUDIT");
		Date verificationDate = this.parseDateText("02/01/2017");
		PrisonTerm prisonTerm = this.prisonTermDelegate.create(offender, 
				actionDate, preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		Boolean newSentenceToFollow = true;
		
		// Action
		prisonTerm = this.prisonTermService.update(prisonTerm, actionDate, 
				preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, newSentenceToFollow, comments, 
				verificationUser, verificationDate);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("actionDate", actionDate)
		.addExpectedValue("preSentenceCredits", preSentenceCredits)
		.addExpectedValue("sentenceDate", sentenceDate)
		.addExpectedValue("sentenceTermYears", sentenceTermYears)
		.addExpectedValue("sentenceTermDays", sentenceTermDays)
		.addExpectedValue("paroleEligibilityDate", paroleEligibilityDate)
		.addExpectedValue("projectedDischargeDate", projectedDischargeDate)
		.addExpectedValue("maximumDischargeDate", maximumDischargeDate)
		.addExpectedValue("status", status)
		.addExpectedValue("sentenceToFollow", newSentenceToFollow)
		.addExpectedValue("comments", comments)
		.addExpectedValue("verificationUser", verificationUser)
		.addExpectedValue("verificationDate", verificationDate)
		.performAssertions(prisonTerm);
	}
	/**
	 * Tests the update of comments for a prison term.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ActivePrisonTermExistsException thrown when active prison term 
	 * exists
	 */
	@Test
	public void testUpdateComments() throws DuplicateEntityFoundException, 
			ActivePrisonTermExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date actionDate = this.parseDateText("01/01/2017");
		Integer preSentenceCredits = 0;
		Date sentenceDate = this.parseDateText("01/05/2017");
		Integer sentenceTermYears = 15;
		Integer sentenceTermDays = 0;
		Date paroleEligibilityDate = this.parseDateText("01/05/2027");
		Date projectedDischargeDate = this.parseDateText("01/05/2032");
		Date maximumDischargeDate = this.parseDateText("01/05/2032");
		PrisonTermStatus status = PrisonTermStatus.ACTIVE;
		Boolean sentenceToFollow = false;
		String comments = "Comments go here";
		UserAccount verificationUser = this.userAccountDelegate
				.findByUsername("AUDIT");
		Date verificationDate = this.parseDateText("02/01/2017");
		PrisonTerm prisonTerm = this.prisonTermDelegate.create(offender, 
				actionDate, preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		String newComments = "More comments go here";
		
		// Action
		prisonTerm = this.prisonTermService.update(prisonTerm, actionDate, 
				preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, newComments, 
				verificationUser, verificationDate);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("actionDate", actionDate)
		.addExpectedValue("preSentenceCredits", preSentenceCredits)
		.addExpectedValue("sentenceDate", sentenceDate)
		.addExpectedValue("sentenceTermYears", sentenceTermYears)
		.addExpectedValue("sentenceTermDays", sentenceTermDays)
		.addExpectedValue("paroleEligibilityDate", paroleEligibilityDate)
		.addExpectedValue("projectedDischargeDate", projectedDischargeDate)
		.addExpectedValue("maximumDischargeDate", maximumDischargeDate)
		.addExpectedValue("status", status)
		.addExpectedValue("sentenceToFollow", sentenceToFollow)
		.addExpectedValue("comments", newComments)
		.addExpectedValue("verificationUser", verificationUser)
		.addExpectedValue("verificationDate", verificationDate)
		.performAssertions(prisonTerm);
	}
	/**
	 * Tests the update of verification date for a prison term.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ActivePrisonTermExistsException thrown when active prison term 
	 * exists
	 */
	@Test
	public void testUpdateVerificationDate() 
			throws DuplicateEntityFoundException, 
			ActivePrisonTermExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date actionDate = this.parseDateText("01/01/2017");
		Integer preSentenceCredits = 0;
		Date sentenceDate = this.parseDateText("01/05/2017");
		Integer sentenceTermYears = 15;
		Integer sentenceTermDays = 0;
		Date paroleEligibilityDate = this.parseDateText("01/05/2027");
		Date projectedDischargeDate = this.parseDateText("01/05/2032");
		Date maximumDischargeDate = this.parseDateText("01/05/2032");
		PrisonTermStatus status = PrisonTermStatus.ACTIVE;
		Boolean sentenceToFollow = false;
		String comments = "Comments go here";
		UserAccount verificationUser = this.userAccountDelegate
				.findByUsername("AUDIT");
		Date verificationDate = this.parseDateText("02/01/2017");
		PrisonTerm prisonTerm = this.prisonTermDelegate.create(offender, 
				actionDate, preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		Date newVerificationDate = this.parseDateText("03/01/2017");
		
		// Action
		prisonTerm = this.prisonTermService.update(prisonTerm, actionDate, 
				preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, newVerificationDate);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("actionDate", actionDate)
		.addExpectedValue("preSentenceCredits", preSentenceCredits)
		.addExpectedValue("sentenceDate", sentenceDate)
		.addExpectedValue("sentenceTermYears", sentenceTermYears)
		.addExpectedValue("sentenceTermDays", sentenceTermDays)
		.addExpectedValue("paroleEligibilityDate", paroleEligibilityDate)
		.addExpectedValue("projectedDischargeDate", projectedDischargeDate)
		.addExpectedValue("maximumDischargeDate", maximumDischargeDate)
		.addExpectedValue("status", status)
		.addExpectedValue("sentenceToFollow", sentenceToFollow)
		.addExpectedValue("comments", comments)
		.addExpectedValue("verificationUser", verificationUser)
		.addExpectedValue("verificationDate", newVerificationDate)
		.performAssertions(prisonTerm);
	}
	
	/**
	 * Tests the {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ActivePrisonTermExistsException thrown when active prison term 
	 * exists 
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException, 
			ActivePrisonTermExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date actionDate = this.parseDateText("01/01/2017");
		Integer preSentenceCredits = 0;
		Date sentenceDate = this.parseDateText("01/05/2017");
		Integer sentenceTermYears = 15;
		Integer sentenceTermDays = 0;
		Date paroleEligibilityDate = this.parseDateText("01/05/2027");
		Date projectedDischargeDate = this.parseDateText("01/05/2032");
		Date maximumDischargeDate = this.parseDateText("01/05/2032");
		PrisonTermStatus status = PrisonTermStatus.ACTIVE;
		Boolean sentenceToFollow = false;
		String comments = "Comments go here";
		UserAccount verificationUser = this.userAccountDelegate
				.findByUsername("AUDIT");
		Date verificationDate = this.parseDateText("02/01/2017");
		this.prisonTermDelegate.create(offender, actionDate, preSentenceCredits, 
				sentenceDate, sentenceTermYears, sentenceTermDays, 
				paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		PrisonTermStatus secondStatus = PrisonTermStatus.HISTORICAL;
		PrisonTerm prisonTerm = this.prisonTermDelegate.create(offender, 
				actionDate, preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, secondStatus, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		
		// Action
		this.prisonTermService.update(prisonTerm, actionDate, 
				preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
	}
	
	/**
	 * Tests the {@code ActivePrisonTermExistsException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ActivePrisonTermExistsException thrown when active prison term 
	 * exists 
	 */
	@Test(expectedExceptions = {ActivePrisonTermExistsException.class})
	public void testActivePrisonTermExistsException() 
			throws DuplicateEntityFoundException, 
			ActivePrisonTermExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date actionDate = this.parseDateText("01/01/2017");
		Integer preSentenceCredits = 0;
		Date sentenceDate = this.parseDateText("01/05/2017");
		Integer sentenceTermYears = 15;
		Integer sentenceTermDays = 0;
		Date paroleEligibilityDate = this.parseDateText("01/05/2027");
		Date projectedDischargeDate = this.parseDateText("01/05/2032");
		Date maximumDischargeDate = this.parseDateText("01/05/2032");
		PrisonTermStatus status = PrisonTermStatus.ACTIVE;
		Boolean sentenceToFollow = false;
		String comments = "Comments go here";
		UserAccount verificationUser = this.userAccountDelegate
				.findByUsername("AUDIT");
		Date verificationDate = this.parseDateText("02/01/2017");
		this.prisonTermDelegate.create(offender, actionDate, preSentenceCredits, 
				sentenceDate, sentenceTermYears, sentenceTermDays, 
				paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		PrisonTermStatus secondStatus = PrisonTermStatus.HISTORICAL;
		Date secondActionDate = this.parseDateText("02/02/2017");
		PrisonTerm prisonTerm = this.prisonTermDelegate.create(offender, 
				secondActionDate, preSentenceCredits, sentenceDate, 
				sentenceTermYears, sentenceTermDays, paroleEligibilityDate, 
				projectedDischargeDate, maximumDischargeDate, secondStatus,  
				sentenceToFollow, comments, verificationUser, verificationDate);
		
		// Action
		this.prisonTermService.update(prisonTerm, secondActionDate, 
				preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
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
