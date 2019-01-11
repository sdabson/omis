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
import omis.stg.domain.SecurityThreatGroupActivityNote;
import omis.stg.exception.SecurityThreatGroupActivityExistsException;
import omis.stg.exception.SecurityThreatGroupActivityNoteExistsException;
import omis.stg.service.SecurityThreatGroupActivityService;
import omis.stg.service.delegate.SecurityThreatGroupActivityDelegate;
import omis.stg.service.delegate.SecurityThreatGroupActivityNoteDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create security threat group activity notes.
 *
 * @author Josh Divine
 * @author Sheronda Vaughn
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class SecurityThreatGroupActivityServiceAddNoteTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupActivityDelegate")
	private SecurityThreatGroupActivityDelegate 
		securityThreatGroupActivityDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupActivityNoteDelegate")
	private SecurityThreatGroupActivityNoteDelegate 
		securityThreatGroupActivityNoteDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("securityThreatGroupActivityService")
	private SecurityThreatGroupActivityService 
		securityThreatGroupActivityService;

	/* Test methods. */
	
	/**
	 * Tests creation of a security threat group activity note.
	 * 
	 * @throws SecurityThreatGroupActivityNoteExistsException if duplicate entity exists
	 * @throws SecurityThreatGroupActivityExistsException 
	 */
	@Test
	public void testAddNote() throws SecurityThreatGroupActivityNoteExistsException, 
		SecurityThreatGroupActivityExistsException {
		// Arrangements
		Date reportDate = this.parseDateText("01/01/2017");
		Person reportedBy = this.personDelegate.create("Doe", "John", null, 
				null);
		String summary = "Summary";
		SecurityThreatGroupActivity activity = 
				this.securityThreatGroupActivityDelegate.create(reportDate, 
						reportedBy, summary);
		Date date = this.parseDateText("02/01/2017");
		String value = "Value";

		// Action
		SecurityThreatGroupActivityNote securityThreatGroupActivityNote = 
				this.securityThreatGroupActivityService.addNote(activity, date, 
						value);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("activity", activity)
			.addExpectedValue("date", date)
			.addExpectedValue("value", value)
			.performAssertions(securityThreatGroupActivityNote);
	}

	/**
	 * Tests {@code SecurityThreatGroupActivityNoteExistsException} is thrown.
	 * 
	 * @throws SecurityThreatGroupActivityNoteExistsException if duplicate entity exists
	 * @throws SecurityThreatGroupActivityExistsException 
	 */
	@Test(expectedExceptions = {SecurityThreatGroupActivityNoteExistsException.class})
	public void testSecurityThreatGroupActivityNoteExistsException() 
			throws SecurityThreatGroupActivityNoteExistsException, 
			SecurityThreatGroupActivityExistsException {
		// Arrangements
		Date reportDate = this.parseDateText("01/01/2017");
		Person reportedBy = this.personDelegate.create("Doe", "John", null, 
				null);
		String summary = "Summary";
		SecurityThreatGroupActivity activity = 
				this.securityThreatGroupActivityDelegate.create(reportDate, 
						reportedBy, summary);
		Date date = this.parseDateText("02/01/2017");
		String value = "Value";
		this.securityThreatGroupActivityNoteDelegate.addNote(activity, date, 
				value);
		// Action
		this.securityThreatGroupActivityService.addNote(activity, date, value);
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