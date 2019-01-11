/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.stg.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.exception.SecurityThreatGroupActivityExistsException;
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
	 * @throws SecurityThreatGroupActivityExistsException 
	 */
	@Test
	public void testUpdateReportDate() throws SecurityThreatGroupActivityExistsException {
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
	 * @throws SecurityThreatGroupActivityExistsException 
	 */
	@Test
	public void testUpdateReportBy() throws SecurityThreatGroupActivityExistsException {
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
	 * @throws SecurityThreatGroupActivityExistsException 
	 */
	@Test
	public void testUpdateSummary() throws SecurityThreatGroupActivityExistsException {
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
	 * Tests {@code SecurityThreatGroupActivityExistsException} is thrown.
	 * 
	 * @throws SecurityThreatGroupActivityExistsException if duplicate entity exists
	 */
	@Test(expectedExceptions = {SecurityThreatGroupActivityExistsException.class})
	public void testSecurityThreatGroupActivityExistsException() 
			throws SecurityThreatGroupActivityExistsException {
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