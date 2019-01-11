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

import omis.exception.DuplicateEntityFoundException;
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
 * Tests method to update security threat group activity notes.
 *
 * @author Josh Divine
 * @author Sheronda Vaughn
 * @version 0.1.1 (Apr 10, 2018)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupActivityServiceUpdateNoteTests
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
	 * Tests update of the date for a security threat group activity note.
	 * 
	 * @throws SecurityThreatGroupActivityNoteExistsException 
	 * @throws SecurityThreatGroupActivityExistsException 
	 */
	@Test
	public void testUpdateNoteDate() throws SecurityThreatGroupActivityNoteExistsException, 
		SecurityThreatGroupActivityExistsException {
		// Arrangements
		Person reportedBy = this.personDelegate.create("Doe", "John", null, 
				null);
		SecurityThreatGroupActivity activity = this
				.securityThreatGroupActivityDelegate.create(
						this.parseDateText("01/01/2017"), reportedBy, 
						"Summary");
		Date date = this.parseDateText("02/01/2017");
		String value = "Value";
		SecurityThreatGroupActivityNote note = this
				.securityThreatGroupActivityNoteDelegate.addNote(activity, date, 
						value);
		Date newDate = this.parseDateText("02/02/2017");
		
		// Action
		note = this.securityThreatGroupActivityService.updateNote(note, 
				newDate, value);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("activity", activity)
			.addExpectedValue("date", newDate)
			.addExpectedValue("value", value)
			.performAssertions(note);
	}
	
	/**
	 * Tests update of the value for a security threat group activity note.
	 * @throws SecurityThreatGroupActivityExistsException 
	 * @throws SecurityThreatGroupActivityNoteExistsException 
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateNoteValue() throws SecurityThreatGroupActivityExistsException,
		SecurityThreatGroupActivityNoteExistsException  {
		// Arrangements
		Person reportedBy = this.personDelegate.create("Doe", "John", null, 
				null);
		SecurityThreatGroupActivity activity = this
				.securityThreatGroupActivityDelegate.create(
						this.parseDateText("01/01/2017"), reportedBy, 
						"Summary");
		Date date = this.parseDateText("02/01/2017");
		String value = "Value";
		SecurityThreatGroupActivityNote note = this
				.securityThreatGroupActivityNoteDelegate.addNote(activity, 
						date, value);
		String newValue = "Value 2";
		
		// Action
		note = this.securityThreatGroupActivityService.updateNote(note, date, 
				newValue);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("activity", activity)
			.addExpectedValue("date", date)
			.addExpectedValue("value", newValue)
			.performAssertions(note);
	}

	 /**
	 * Tests update of the value for a security threat group activity note 
	 * when an existing note exists with the same date and value but a 
	 * different activity to test fix for production issue [TICK:21288].
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws SecurityThreatGroupActivityNoteExistsException 
	 * @throws SecurityThreatGroupActivityExistsException 
	 */
	@Test
	public void testUpdateNoteDuplicateDateAndValueWithDifferentActivity() 
			throws SecurityThreatGroupActivityNoteExistsException, 
			SecurityThreatGroupActivityExistsException {
		// Arrangements
		Date reportDate = this.parseDateText("01/01/2017");
		Person reportedBy = this.personDelegate.create("Doe", "John", null, 
				null);
		SecurityThreatGroupActivity activity = 
				this.securityThreatGroupActivityDelegate.create(reportDate, 
						reportedBy, "Summary");
		Date date = this.parseDateText("02/01/2017");
		String value = "Value";
		SecurityThreatGroupActivityNote note = this
				.securityThreatGroupActivityNoteDelegate.addNote(activity, date, 
						value);
		String newValue = "Value 2";
		SecurityThreatGroupActivity newActivity = 
				this.securityThreatGroupActivityDelegate.create(reportDate, 
						reportedBy, "Summary2");
		this.securityThreatGroupActivityNoteDelegate.addNote(newActivity, date, 
				newValue);
		
		// Action
		note = this.securityThreatGroupActivityService.updateNote(note, 
				date, newValue);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("activity", activity)
			.addExpectedValue("date", date)
			.addExpectedValue("value", newValue)
			.performAssertions(note);
	}
	
	/**
	 * Tests {@code SecurityThreatGroupActivityNoteExistsException} is thrown.
	 * 
	 * @throws SecurityThreatGroupActivityNoteExistsException 
	 * @throws SecurityThreatGroupActivityExistsException 
	 */
	@Test(expectedExceptions = {SecurityThreatGroupActivityNoteExistsException.class})
	public void testSecurityThreatGroupActivityNoteExistsException() 
			throws SecurityThreatGroupActivityNoteExistsException, 
			SecurityThreatGroupActivityExistsException {
		// Arrangements
		Person reportedBy = this.personDelegate.create("Doe", "John", null, 
				null);
		SecurityThreatGroupActivity activity = this
				.securityThreatGroupActivityDelegate.create(
						this.parseDateText("01/01/2017"), 
						reportedBy, "Summary");
		Date date = this.parseDateText("02/01/2017");
		String value = "Value";
		this.securityThreatGroupActivityNoteDelegate.addNote(activity, date, 
				value);
		Date secondDate = this.parseDateText("03/01/2017");
		SecurityThreatGroupActivityNote note = this
				.securityThreatGroupActivityNoteDelegate.addNote(activity, 
						secondDate, value);
		
		// Action
		note = this.securityThreatGroupActivityService.updateNote(note, date, 
				value);
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