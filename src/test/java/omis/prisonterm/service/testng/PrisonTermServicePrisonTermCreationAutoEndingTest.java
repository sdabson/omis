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
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;

/**
 * Tests prison term creation with auto ending 
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"prisonterm"})
public class PrisonTermServicePrisonTermCreationAutoEndingTest
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegates. */
	@Autowired
	@Qualifier("userAccountDelegate")
	private UserAccountDelegate userAccountDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	/* Service */
	@Autowired
	@Qualifier("prisonTermService")
	private PrisonTermService prisonTermService;
	
	/**
	 * Tests the creation of prison term with auto ending.
	 * @throws DuplicateEntityFoundException 
	 * @throws ActivePrisonTermExistsException 
	 */
	@Test
	public void testPrisonTermCreationWithAutoEnding() 
		throws DuplicateEntityFoundException, ActivePrisonTermExistsException{
		// Arrangements
		// Create 1st prison term with status of "ACTIVE"
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date actionDate1 = this.parseDateText("01/01/2015");
		Integer preSentenceCredits1 = 0;
		Date sentenceDate1 = this.parseDateText("01/05/2015");
		Integer sentenceTermYears1 = 15;
		Integer sentenceTermDays1 = 0;
		Date paroleEligibilityDate1 = this.parseDateText("01/05/2025");
		Date projectedDischargeDate1 = this.parseDateText("01/05/2030");
		Date maximumDischargeDate1 = this.parseDateText("01/05/2030");
		PrisonTermStatus status1 = PrisonTermStatus.ACTIVE;
		Boolean sentenceToFollow1 = false;
		String comments1 = "Prison term1";
		UserAccount verificationUser1 = this.userAccountDelegate
				.findByUsername("AUDIT");
		Date verificationDate1 = this.parseDateText("02/01/2015");
										 
		PrisonTerm term1 = this.prisonTermService.create(offender, actionDate1, 
			preSentenceCredits1, sentenceDate1, sentenceTermYears1, 
			sentenceTermDays1, paroleEligibilityDate1, projectedDischargeDate1, 
			maximumDischargeDate1, status1, sentenceToFollow1, comments1, 
			verificationUser1, verificationDate1);
				
		// Create the 2nd term with status of "ACTIVE"
		Date actionDate2 = this.parseDateText("01/01/2017");
		Integer preSentenceCredits2 = 0;
		Date sentenceDate2 = this.parseDateText("01/05/2017");
		Integer sentenceTermYears2 = 15;
		Integer sentenceTermDays2 = 0;
		Date paroleEligibilityDate2 = this.parseDateText("01/05/2027");
		Date projectedDischargeDate2 = this.parseDateText("01/05/2032");
		Date maximumDischargeDate2 = this.parseDateText("01/05/2032");
		PrisonTermStatus status2 = PrisonTermStatus.ACTIVE;
		Boolean sentenceToFollow2 = false;
		String comments2 = "Prison term2";
		UserAccount verificationUser2 = this.userAccountDelegate
			.findByUsername("AUDIT");
		Date verificationDate2 = this.parseDateText("02/01/2017");
										 
		this.prisonTermService.create(offender, actionDate2, 
			preSentenceCredits2, sentenceDate2, sentenceTermYears2, 
			sentenceTermDays2, paroleEligibilityDate2, projectedDischargeDate2, 
			maximumDischargeDate2, status2, sentenceToFollow2, comments2, 
			verificationUser2, verificationDate2);
		
		// Assertions
		// After the creation of 2nd prison term with status of "ACTIVE", the 
		// status of the 1st one should be set to "HISTORICAL". 
		assert PrisonTermStatus.HISTORICAL.equals(term1.getStatus())
		: String.format("Prison term 1 has not been automatically set to "
				+ "HISTORICAL yet");
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