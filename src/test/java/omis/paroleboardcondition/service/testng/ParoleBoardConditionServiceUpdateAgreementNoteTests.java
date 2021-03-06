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
package omis.paroleboardcondition.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.condition.domain.Agreement;
import omis.condition.domain.AgreementNote;
import omis.condition.service.delegate.AgreementDelegate;
import omis.condition.service.delegate.AgreementNoteDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.paroleboardcondition.service.ParoleBoardConditionService;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to update agreement notes.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test
public class ParoleBoardConditionServiceUpdateAgreementNoteTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	private AgreementDelegate agreementDelegate;
	
	@Autowired
	private AgreementNoteDelegate agreementNoteDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("paroleBoardConditionService")
	private ParoleBoardConditionService paroleBoardConditionService;

	/* Test methods. */

	/**
	 * Tests the update of the date for a agreement note.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateAgreementNoteDate() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Wayne", 
				"Bruce", "Alen", null);
		Agreement agreement = this.agreementDelegate.create(offender,
				this.parseDateText("05/12/2017"),
				this.parseDateText("05/15/2019"), null, null);
		String description = "Agreement Note Description";
		Date date = this.parseDateText("06/10/2017");
		AgreementNote agreementNote = this.agreementNoteDelegate.create(date, 
				agreement, description);
		Date newDate = this.parseDateText("06/11/2017");

		// Action
		agreementNote = this.paroleBoardConditionService.updateAgreementNote(
				agreementNote, description, newDate);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("date", newDate)
			.addExpectedValue("agreement", agreement)
			.addExpectedValue("description", description)
			.performAssertions(agreementNote);
	}

	/**
	 * Tests the update of the description for a agreement note.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateAgreementNoteDescription() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Wayne", 
				"Bruce", "Alen", null);
		Agreement agreement = this.agreementDelegate.create(offender,
				this.parseDateText("05/12/2017"),
				this.parseDateText("05/15/2019"), null, null);
		String description = "Agreement Note Description";
		Date date = this.parseDateText("06/10/2017");
		AgreementNote agreementNote = this.agreementNoteDelegate.create(date, 
				agreement, description);
		String newDescription = "New Agreement Note Description";

		// Action
		agreementNote = this.paroleBoardConditionService.updateAgreementNote(
				agreementNote, newDescription, date);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("date", date)
			.addExpectedValue("agreement", agreement)
			.addExpectedValue("description", newDescription)
			.performAssertions(agreementNote);
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
		Offender offender = this.offenderDelegate.createWithoutIdentity("Wayne", 
				"Bruce", "Alen", null);
		Agreement agreement = this.agreementDelegate.create(offender,
				this.parseDateText("05/12/2017"),
				this.parseDateText("05/15/2019"), null, null);
		String description = "Agreement Note Description";
		Date date = this.parseDateText("06/10/2017");
		this.agreementNoteDelegate.create(date, agreement, description);
		Date secondDate = this.parseDateText("06/11/2017");
		AgreementNote agreementNote = this.agreementNoteDelegate.create(
				secondDate, agreement, description);

		// Action
		agreementNote = this.paroleBoardConditionService.updateAgreementNote(
				agreementNote, description, date);
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