package omis.religion.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
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
import omis.util.PropertyValueAsserter;

/**
 * Tests method to save the initial religious preference.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"religion", "service"})
public class ReligiousPreferenceServiceSaveInitialTests
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
	
	/* Services. */

	@Autowired
	@Qualifier("religiousPreferenceService")
	private ReligiousPreferenceService religiousPreferenceService;

	/* Test methods. */

	/**
	 * Tests the initial save of a religious preference.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testSaveInitial() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		ReligionGroup group = this.religionGroupDelegate.create("Group", 
				(short) 1, true);
		Religion religion = this.religionDelegate.create("Religion", group, 
				(short) 1, true);
		Date startDate = this.parseDateText("01/01/2017");

		// Action
		ReligiousPreference religiousPreference = 
				this.religiousPreferenceService.saveInitial(offender, religion, 
						startDate);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("religion", religion)
			.addExpectedValue("dateRange", new DateRange(startDate, null))
			.performAssertions(religiousPreference);

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
		ReligionGroup group = this.religionGroupDelegate.create("Group", 
				(short) 1, true);
		Religion religion = this.religionDelegate.create("Religion", group, 
				(short) 1, true);
		Date startDate = this.parseDateText("01/01/2017");
		this.religiousPreferenceDelegate.createInitial(offender, religion, 
				startDate);

		// Action
		this.religiousPreferenceService.saveInitial(offender, religion, 
				startDate);
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