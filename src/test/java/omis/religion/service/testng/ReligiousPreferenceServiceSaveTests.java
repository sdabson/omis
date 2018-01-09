package omis.religion.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.audit.domain.VerificationMethod;
import omis.audit.domain.VerificationSignature;
import omis.audit.service.delegate.VerificationMethodDelegate;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.exception.OperationNotAuthorizedException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.religion.domain.Religion;
import omis.religion.domain.ReligionGroup;
import omis.religion.domain.ReligiousPreference;
import omis.religion.service.ReligiousPreferenceService;
import omis.religion.service.delegate.ReligionDelegate;
import omis.religion.service.delegate.ReligionGroupDelegate;
import omis.religion.service.delegate.ReligiousPreferenceDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.service.delegate.UserAccountDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to save religious preferences.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"religion", "service"})
public class ReligiousPreferenceServiceSaveTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("religionDelegate")
	private ReligionDelegate religionDelegate;
	
	@Autowired
	@Qualifier("religionGroupDelegate")
	private ReligionGroupDelegate religionGroupDelegate;
	
	@Autowired
	@Qualifier("religiousPreferenceDelegate")
	private ReligiousPreferenceDelegate religiousPreferenceDelegate;
	
	@Autowired
	@Qualifier("userAccountDelegate")
	private UserAccountDelegate accountDelegate;
	
	@Autowired
	@Qualifier("verificationMethodDelegate")
	private VerificationMethodDelegate verificationMethodDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("religiousPreferenceService")
	private ReligiousPreferenceService religiousPreferenceService;

	/* Test methods. */

	/**
	 * Tests the save method for a religous preference.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date conflicts with an existing 
	 * preference
	 * @throws OperationNotAuthorizedException if operation is not permitted
	 */
	@Test
	public void testSave() throws DuplicateEntityFoundException, 
			DateConflictException, OperationNotAuthorizedException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		ReligionGroup group = this.religionGroupDelegate.create("Group", 
				(short) 1, true);
		Religion religion = this.religionDelegate.create("Religion", group, 
				(short) 1, true);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("06/01/2017"));
		String comment = "Comment";
		VerificationMethod method = this.verificationMethodDelegate.create(
				"Method", (short) 1, true);
		VerificationSignature verificationSignature = new VerificationSignature(
				this.accountDelegate.findByUsername("AUDIT"), 
				this.parseDateText("02/01/2017"), true, method);

		// Action
		ReligiousPreference religiousPreference = 
				this.religiousPreferenceService.save(offender, religion, 
						dateRange, comment, verificationSignature);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("religion", religion)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("comment", comment)
			.addExpectedValue("verificationSignature", verificationSignature)
			.performAssertions(religiousPreference);
	}

	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date conflicts with an existing 
	 * preference
	 * @throws OperationNotAuthorizedException if operation is not permitted
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException, DateConflictException, 
			OperationNotAuthorizedException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		ReligionGroup group = this.religionGroupDelegate.create("Group", 
				(short) 1, true);
		Religion religion = this.religionDelegate.create("Religion", group, 
				(short) 1, true);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("06/01/2017"));
		String comment = "Comment";
		VerificationMethod method = this.verificationMethodDelegate.create(
				"Method", (short) 1, true);
		VerificationSignature verificationSignature = new VerificationSignature(
				this.accountDelegate.findByUsername("AUDIT"), 
				this.parseDateText("02/01/2017"), true, method);
		this.religiousPreferenceDelegate.save(offender, religion, dateRange, 
				comment, verificationSignature);
		
		// Action
		this.religiousPreferenceService.save(offender, religion, dateRange, 
				comment, verificationSignature);

	}

	/**
	 * Tests {@code DateConflictException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date conflicts with an existing 
	 * preference
	 * @throws OperationNotAuthorizedException if operation is not permitted
	 */
	@Test(expectedExceptions = {DateConflictException.class})
	public void testDateConflictException() 
			throws DuplicateEntityFoundException, DateConflictException, 
			OperationNotAuthorizedException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		ReligionGroup group = this.religionGroupDelegate.create("Group", 
				(short) 1, true);
		Religion religion = this.religionDelegate.create("Religion", group, 
				(short) 1, true);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("06/01/2017"));
		String comment = "Comment";
		VerificationMethod method = this.verificationMethodDelegate.create(
				"Method", (short) 1, true);
		VerificationSignature verificationSignature = new VerificationSignature(
				this.accountDelegate.findByUsername("AUDIT"), 
				this.parseDateText("02/01/2017"), true, method);
		this.religiousPreferenceDelegate.save(offender, religion, dateRange, 
				comment, verificationSignature);
		DateRange conflictDateRange = new DateRange(
				this.parseDateText("04/01/2017"), 
				this.parseDateText("06/01/2017"));
		
		// Action
		this.religiousPreferenceService.save(offender, religion, 
				conflictDateRange, comment, verificationSignature);
	}

	/**
	 * Tests {@code OperationNotAuthorizedException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date conflicts with an existing 
	 * preference
	 * @throws OperationNotAuthorizedException if operation is not permitted
	 */
	@Test(expectedExceptions = {OperationNotAuthorizedException.class})
	public void testOperationNotAuthorizedException() 
			throws DuplicateEntityFoundException, DateConflictException, 
			OperationNotAuthorizedException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		ReligionGroup group = this.religionGroupDelegate.create("Group", 
				(short) 1, true);
		Religion religion = this.religionDelegate.create("Religion", group, 
				(short) 1, true);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("06/01/2017"));
		String comment = "Comment";
		VerificationMethod method = this.verificationMethodDelegate.create(
				"Method", (short) 1, true);
		VerificationSignature verificationSignature = new VerificationSignature(
				null, this.parseDateText("02/01/2017"), true, method);
		this.religiousPreferenceDelegate.save(offender, religion, dateRange, 
				comment, verificationSignature);
		DateRange conflictDateRange = new DateRange(
				this.parseDateText("12/01/2016"), 
				this.parseDateText("01/01/2017"));
		
		// Action
		this.religiousPreferenceService.save(offender, religion, 
				conflictDateRange, comment, null);
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