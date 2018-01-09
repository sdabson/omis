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
import omis.religion.domain.ReligiousAccommodation;
import omis.religion.domain.ReligiousAccommodationAuthorization;
import omis.religion.domain.ReligiousPreference;
import omis.religion.service.ReligiousPreferenceService;
import omis.religion.service.delegate.ReligionDelegate;
import omis.religion.service.delegate.ReligionGroupDelegate;
import omis.religion.service.delegate.ReligiousAccommodationAuthorizationDelegate;
import omis.religion.service.delegate.ReligiousAccommodationDelegate;
import omis.religion.service.delegate.ReligiousPreferenceDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.service.delegate.UserAccountDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to authorize religious preference accommodations.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"religion", "service"})
public class ReligiousPreferenceServiceAuthorizeAccommodationTests
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
	
	@Autowired
	@Qualifier("religiousAccommodationDelegate")
	private ReligiousAccommodationDelegate religiousAccommodationDelegate;
	
	@Autowired
	@Qualifier("religiousAccommodationAuthorizationDelegate")
	private ReligiousAccommodationAuthorizationDelegate 
			religiousAccommodationAuthorizationDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("religiousPreferenceService")
	private ReligiousPreferenceService religiousPreferenceService;

	/* Test methods. */

	/**
	 * Tests authorization of a religious preference accommodation.
	 * 
	 * @throws OperationNotAuthorizedException if operation is not authorized
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date conflicts with an existing 
	 * preference 
	 */
	@Test
	public void testAuthorizeAccommodation() 
			throws OperationNotAuthorizedException, 
			DuplicateEntityFoundException, DateConflictException {
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
		ReligiousPreference preference = this.religiousPreferenceDelegate.save(
				offender, religion, dateRange, comment, verificationSignature);
		ReligiousAccommodation accommodation = 
				this.religiousAccommodationDelegate.create("Accommodation", 
						(short) 1, true);

		// Action
		ReligiousAccommodationAuthorization religiousAccommodationAuthorization 
				= this.religiousPreferenceService.authorizeAccommodation(
						preference, accommodation);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("preference", preference)
			.addExpectedValue("accommodation", accommodation)
			.performAssertions(religiousAccommodationAuthorization);

	}

	/**
	 * Tests {@code OperationNotAuthorizedException} is thrown.
	 * 
	 * @throws OperationNotAuthorizedException if operation is not authorized
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date conflicts with an existing 
	 * preference
	 */
	@Test(expectedExceptions = {OperationNotAuthorizedException.class})
	public void testOperationNotAuthorizedException() 
			throws OperationNotAuthorizedException, 
			DuplicateEntityFoundException, DateConflictException {
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
		ReligiousPreference preference = this.religiousPreferenceDelegate.save(
				offender, religion, dateRange, comment, verificationSignature);
		ReligiousAccommodation accommodation = 
				this.religiousAccommodationDelegate.create("Accommodation", 
						(short) 1, true);
		this.religiousAccommodationAuthorizationDelegate.authorize(preference, 
				accommodation);
				
		// Action
		this.religiousPreferenceService.authorizeAccommodation(preference, 
				accommodation);
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