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

/**
 * Tests method to create security threat group activities.
 *
 * @author Josh Divine
 * @author Sheronda Vaughn
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class SecurityThreatGroupActivityServiceCreateTests
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
	 * Tests creation of a security threat group activity.
	 * 
	 * @throws SecurityThreatGroupActivityExistsException if duplicate entity exists
	 */
	@Test
	public void testCreate() throws SecurityThreatGroupActivityExistsException {
		// Arrangements
		Date reportDate = this.parseDateText("01/01/2017");
		Person reportedBy = this.personDelegate.create("Doe", "John", null, 
				null);
		String summary = "Summary";

		// Action
		SecurityThreatGroupActivity securityThreatGroupActivity = 
				this.securityThreatGroupActivityService.create(reportDate, 
						reportedBy, summary);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("reportDate", reportDate)
			.addExpectedValue("reportedBy", reportedBy)
			.addExpectedValue("summary", summary)
			.performAssertions(securityThreatGroupActivity);
	}

	/**
	 * Tests {@code SecurityThreatGroupActivityExistsException} is thrown.
	 * 
	 * @throws SecurityThreatGroupActivityExistsException if duplicate entity exists
	 * @throws SecurityThreatGroupActivityExistsException 
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
		// Action
		this.securityThreatGroupActivityService.create(reportDate, reportedBy, 
				summary);

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