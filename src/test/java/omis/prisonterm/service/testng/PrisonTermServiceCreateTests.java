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
 * Tests method to create prison terms.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"prisonterm", "service"})
public class PrisonTermServiceCreateTests
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
	 * Tests the creation of a prison term.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ActivePrisonTermExistsException 
	 */
	@Test
	public void testCreate() throws DuplicateEntityFoundException, ActivePrisonTermExistsException {
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
		
		// Action
		PrisonTerm prisonTerm = this.prisonTermService.create(offender, 
				actionDate, preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate, null);
		
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
		.addExpectedValue("verificationDate", verificationDate)
		.performAssertions(prisonTerm);
	}
	
	/**
	 * Tests the {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException thrown when two existing terms have 
	 * "HISTORICAL" status and the same action date 
	 * @throws ActivePrisonTermExistsException 
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException, ActivePrisonTermExistsException {
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
				verificationUser, verificationDate, null);
		
		// Action
		this.prisonTermService.create(offender, actionDate, preSentenceCredits, 
				sentenceDate, sentenceTermYears, sentenceTermDays, 
				paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate, null);
		
		this.prisonTermService.create(offender, actionDate, preSentenceCredits, 
				sentenceDate, sentenceTermYears, sentenceTermDays, 
				paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate, null);
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
