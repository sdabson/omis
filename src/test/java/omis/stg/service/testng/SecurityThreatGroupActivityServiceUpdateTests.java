package omis.stg.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.service.SecurityThreatGroupActivityService;
import omis.stg.service.delegate.SecurityThreatGroupActivityDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

public class SecurityThreatGroupActivityServiceUpdateTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupActivityDelegate")
	private SecurityThreatGroupActivityDelegate 
		securityThreatGroupActivityDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("securityThreatGroupActivityService")
	private SecurityThreatGroupActivityService 
		securityThreatGroupActivityService;

	/* Test methods. */
	
	/**
	 * Tests update of the report date for a security threat group activity.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateReportDate() throws DuplicateEntityFoundException {
		// Arrangements
		Date reportDate = this.parseDateText("01/01/2017");
		Person reportedBy = this.personDelegate.create("Doe", "John", null, 
				null);
		String summary = "Summary";
		SecurityThreatGroupActivity activity = 
				this.securityThreatGroupActivityDelegate.create(reportDate, 
						reportedBy, summary);
		Date newReportDate = this.parseDateText("01/05/2017");

		// Action
		activity = this.securityThreatGroupActivityService.update(activity, 
				reportedBy, newReportDate, summary);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("reportDate", newReportDate)
			.addExpectedValue("reportedBy", reportedBy)
			.addExpectedValue("summary", summary)
			.performAssertions(activity);
	}
	
	/**
	 * Tests update of the report by for a security threat group activity.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateReportBy() throws DuplicateEntityFoundException {
		// Arrangements
		Date reportDate = this.parseDateText("01/01/2017");
		Person reportedBy = this.personDelegate.create("Doe", "John", null, 
				null);
		String summary = "Summary";
		SecurityThreatGroupActivity activity = 
				this.securityThreatGroupActivityDelegate.create(reportDate, 
						reportedBy, summary);
		Person newReportedBy = this.personDelegate.create("Doe", "Jane", null, 
				null);

		// Action
		activity = this.securityThreatGroupActivityService.update(activity, 
				newReportedBy, reportDate, summary);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("reportDate", reportDate)
			.addExpectedValue("reportedBy", newReportedBy)
			.addExpectedValue("summary", summary)
			.performAssertions(activity);
	}
	
	/**
	 * Tests update of the summary for a security threat group activity.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateSummary() throws DuplicateEntityFoundException {
		// Arrangements
		Date reportDate = this.parseDateText("01/01/2017");
		Person reportedBy = this.personDelegate.create("Doe", "John", null, 
				null);
		String summary = "Summary";
		SecurityThreatGroupActivity activity = 
				this.securityThreatGroupActivityDelegate.create(reportDate, 
						reportedBy, summary);
		String newSummary = "New summary";

		// Action
		activity = this.securityThreatGroupActivityService.update(activity, 
				reportedBy, reportDate, newSummary);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("reportDate", reportDate)
			.addExpectedValue("reportedBy", reportedBy)
			.addExpectedValue("summary", newSummary)
			.performAssertions(activity);
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
		Date reportDate = this.parseDateText("01/01/2017");
		Person reportedBy = this.personDelegate.create("Doe", "John", null, 
				null);
		String summary = "Summary";
		this.securityThreatGroupActivityDelegate.create(reportDate, reportedBy, 
				summary);
		Date secondReportDate = this.parseDateText("02/01/2017");
		SecurityThreatGroupActivity activity = 
				this.securityThreatGroupActivityDelegate.create(
						secondReportDate, reportedBy, summary);

		// Action
		activity = this.securityThreatGroupActivityService.update(activity, 
				reportedBy, reportDate, summary);
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