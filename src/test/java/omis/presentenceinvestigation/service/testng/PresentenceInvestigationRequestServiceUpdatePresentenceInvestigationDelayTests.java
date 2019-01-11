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
package omis.presentenceinvestigation.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.docket.exception.DocketExistsException;
import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.presentenceinvestigation.domain.PresentenceInvestigationCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationDelay;
import omis.presentenceinvestigation.domain.PresentenceInvestigationDelayCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.PresentenceInvestigationRequestService;
import omis.presentenceinvestigation.service.delegate.PresentenceInvestigationCategoryDelegate;
import omis.presentenceinvestigation.service.delegate.PresentenceInvestigationDelayCategoryDelegate;
import omis.presentenceinvestigation.service.delegate.PresentenceInvestigationDelayDelegate;
import omis.presentenceinvestigation.service.delegate.PresentenceInvestigationRequestDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to update presentence investigation delays.
 *
 * @author Josh Divine
 * @version 0.1.2 (Oct 24, 2018)
 * @since OMIS 3.0
 */
@Test
public class 
	PresentenceInvestigationRequestServiceUpdatePresentenceInvestigationDelayTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	private PresentenceInvestigationRequestDelegate 
			presentenceInvestigationRequestDelegate;

	@Autowired
	private PresentenceInvestigationDelayDelegate 
			presentenceInvestigationDelayDelegate;
	
	@Autowired
	private PresentenceInvestigationDelayCategoryDelegate 
			presentenceInvestigationDelayCategoryDelegate;

	@Autowired
	private PersonDelegate personDelegate;
	
	@Autowired
	private UserAccountDelegate userAccountDelegate;
	
	@Autowired
	private PresentenceInvestigationCategoryDelegate
		presentenceInvestigationCategoryDelegate;

	/* Services. */

	@Autowired
	@Qualifier("presentenceInvestigationRequestService")
	private PresentenceInvestigationRequestService presentenceInvestigationRequestService;

	/* Test methods. */

	/**
	 * Tests the update of the date for a presentence investigation delay.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if duplicate docket exists
	 */
	@Test
	public void testUpdatePresentenceInvestigationDelayDate() 
			throws DuplicateEntityFoundException, DocketExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Wayne", "Bruce", "Alen", 
				null);
		Person user = this.personDelegate.create("Grayson", "Richard", "J", 
				null);
		UserAccount userAccount = this.userAccountDelegate.create(user, 
				"ROBIN34", "password1", this.parseDateText("12/12/2299"), 0, 
				true);
		PresentenceInvestigationCategory category = this
				.presentenceInvestigationCategoryDelegate.create("PSI Category", 
						true);
		PresentenceInvestigationRequest presentenceInvestigationRequest = this
				.presentenceInvestigationRequestDelegate.create(
						userAccount, this.parseDateText("01/01/2016"),
						this.parseDateText("12/31/2017"), person,  
						this.parseDateText("03/25/2015"), category, 
						this.parseDateText("04/01/2017"));
		Date date = this.parseDateText("11/30/2017");
		PresentenceInvestigationDelayCategory reason = this
				.presentenceInvestigationDelayCategoryDelegate.create(
						"Category", true);
		PresentenceInvestigationDelay presentenceInvestigationDelay = this
				.presentenceInvestigationDelayDelegate.create(
						presentenceInvestigationRequest, date, reason);
		Date newDate = this.parseDateText("12/01/2017");
		
		// Action
		presentenceInvestigationDelay = this
				.presentenceInvestigationRequestService
				.updatePresentenceInvestigationDelay(
						presentenceInvestigationDelay, newDate, reason);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("presentenceInvestigationRequest", 
					presentenceInvestigationRequest)
			.addExpectedValue("date", newDate)
			.addExpectedValue("reason", reason)
			.performAssertions(presentenceInvestigationDelay);
	}
	
	/**
	 * Tests the update of the reason for a presentence investigation delay.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if duplicate docket exists
	 */
	@Test
	public void testUpdatePresentenceInvestigationDelayReason() 
			throws DuplicateEntityFoundException, DocketExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Wayne", "Bruce", "Alen", 
				null);
		Person user = this.personDelegate.create("Grayson", "Richard", "J", 
				null);
		UserAccount userAccount = this.userAccountDelegate.create(user, 
				"ROBIN34", "password1", this.parseDateText("12/12/2299"), 0, 
				true);
		PresentenceInvestigationCategory category = this
				.presentenceInvestigationCategoryDelegate.create("PSI Category", 
						true);
		PresentenceInvestigationRequest presentenceInvestigationRequest = this
				.presentenceInvestigationRequestDelegate.create(
						userAccount, this.parseDateText("01/01/2016"),
						this.parseDateText("12/31/2017"), person,  
						this.parseDateText("03/25/2015"), category, 
						this.parseDateText("04/01/2017"));
		Date date = this.parseDateText("11/30/2017");
		PresentenceInvestigationDelayCategory reason = this
				.presentenceInvestigationDelayCategoryDelegate.create(
						"Category", true);
		PresentenceInvestigationDelay presentenceInvestigationDelay = this
				.presentenceInvestigationDelayDelegate.create(
						presentenceInvestigationRequest, date, reason);
		PresentenceInvestigationDelayCategory newReason = this
				.presentenceInvestigationDelayCategoryDelegate.create(
						"Category 2", true);
		
		// Action
		presentenceInvestigationDelay = this
				.presentenceInvestigationRequestService
				.updatePresentenceInvestigationDelay(
						presentenceInvestigationDelay, date, newReason);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("presentenceInvestigationRequest", 
					presentenceInvestigationRequest)
			.addExpectedValue("date", date)
			.addExpectedValue("reason", newReason)
			.performAssertions(presentenceInvestigationDelay);
	}

	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if duplicate docket exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException, DocketExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Wayne", "Bruce", "Alen", 
				null);
		Person user = this.personDelegate.create("Grayson", "Richard", "J", 
				null);
		UserAccount userAccount = this.userAccountDelegate.create(user, 
				"ROBIN34", "password1", this.parseDateText("12/12/2299"), 0, 
				true);
		PresentenceInvestigationCategory category = this
				.presentenceInvestigationCategoryDelegate.create("PSI Category", 
						true);
		PresentenceInvestigationRequest presentenceInvestigationRequest = this
				.presentenceInvestigationRequestDelegate.create(
						userAccount, this.parseDateText("01/01/2016"),
						this.parseDateText("12/31/2017"), person,  
						this.parseDateText("03/25/2015"), category, 
						this.parseDateText("04/01/2017"));
		Date date = this.parseDateText("11/30/2017");
		PresentenceInvestigationDelayCategory reason = this
				.presentenceInvestigationDelayCategoryDelegate.create(
						"Category", true);
		this.presentenceInvestigationDelayDelegate.create(
				presentenceInvestigationRequest, date, reason);
		PresentenceInvestigationDelayCategory secondReason = this
				.presentenceInvestigationDelayCategoryDelegate.create(
						"Category 2", true);
		PresentenceInvestigationDelay presentenceInvestigationDelay = this
				.presentenceInvestigationDelayDelegate.create(
						presentenceInvestigationRequest, date, secondReason);

		// Action
		presentenceInvestigationDelay = this
				.presentenceInvestigationRequestService
				.updatePresentenceInvestigationDelay(
						presentenceInvestigationDelay, date, reason);
	}

	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
}