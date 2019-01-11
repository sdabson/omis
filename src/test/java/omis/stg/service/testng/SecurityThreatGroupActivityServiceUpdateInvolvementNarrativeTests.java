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

import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.domain.SecurityThreatGroupActivityInvolvement;
import omis.stg.exception.SecurityThreatGroupActivityExistsException;
import omis.stg.exception.SecurityThreatGroupActivityInvolvementExistsException;
import omis.stg.service.SecurityThreatGroupActivityService;
import omis.stg.service.delegate.SecurityThreatGroupActivityDelegate;
import omis.stg.service.delegate.SecurityThreatGroupActivityInvolvementDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

public class SecurityThreatGroupActivityServiceUpdateInvolvementNarrativeTests
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
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupActivityInvolvementDelegate")
	private SecurityThreatGroupActivityInvolvementDelegate 
		securityThreatGroupActivityInvolvementDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("securityThreatGroupActivityService")
	private SecurityThreatGroupActivityService 
		securityThreatGroupActivityService;

	/* Test methods. */
	
	@Test
	public void testUpdateInvolvementNarrative() throws SecurityThreatGroupActivityExistsException, 
		SecurityThreatGroupActivityInvolvementExistsException {	
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date reportDate = this.parseDateText("01/01/2017");
		Person reportedBy = this.personDelegate.create("Doe", "John", null, 
				null);
		String summary = "Summary";
		SecurityThreatGroupActivity activity = 
				this.securityThreatGroupActivityDelegate.create(reportDate, 
						reportedBy, summary);
		String narrative = "Narrative";
		SecurityThreatGroupActivityInvolvement involvement = 
				this.securityThreatGroupActivityInvolvementDelegate
						.involveOffender(offender, activity, narrative);
		String newNarrative = "New narrative";
		
		// Action
		involvement = this.securityThreatGroupActivityService
				.updateInvolvementNarrative(involvement, newNarrative);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("narrative", newNarrative)
			.performAssertions(involvement);
	}

	@Test(expectedExceptions = {SecurityThreatGroupActivityInvolvementExistsException.class})
	public void testSecurityThreatGroupActivityInvolvementExistsException () 
			throws SecurityThreatGroupActivityExistsException, 
			SecurityThreatGroupActivityInvolvementExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date reportDate = this.parseDateText("01/01/2017");
		Person reportedBy = this.personDelegate.create("Doe", "John", null, 
				null);
		String summary = "Summary";
		SecurityThreatGroupActivity activity = 
				this.securityThreatGroupActivityDelegate.create(reportDate, 
						reportedBy, summary);
		String narrative = "Narrative";
		this.securityThreatGroupActivityInvolvementDelegate.involveOffender(
				offender, activity, narrative);
		String secondNarrative = "Second Narrative";
		SecurityThreatGroupActivityInvolvement involvement = 
				this.securityThreatGroupActivityInvolvementDelegate
						.involveOffender(offender, activity, secondNarrative);
						
		// Action
		involvement = this.securityThreatGroupActivityService
				.updateInvolvementNarrative(involvement, narrative);
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