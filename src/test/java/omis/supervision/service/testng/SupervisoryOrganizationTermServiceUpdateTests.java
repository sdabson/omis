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
package omis.supervision.service.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.testng.annotations.Test;

import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.datatype.DateRange;
import omis.exception.DateRangeOutOfBoundsException;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.CorrectionalStatusTerm;
import omis.supervision.domain.PlacementTermChangeReason;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.domain.SupervisoryOrganizationTerm;
import omis.supervision.exception.SupervisoryOrganizationTermConflictException;
import omis.supervision.service.SupervisoryOrganizationTermService;
import omis.supervision.service.delegate.CorrectionalStatusDelegate;
import omis.supervision.service.delegate.CorrectionalStatusTermDelegate;
import omis.supervision.service.delegate.PlacementTermChangeReasonDelegate;
import omis.supervision.service.delegate.PlacementTermDelegate;
import omis.supervision.service.delegate.SupervisoryOrganizationDelegate;
import omis.supervision.service.delegate.SupervisoryOrganizationTermDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to update supervisory organization terms.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"placement", "service"})
public class SupervisoryOrganizationTermServiceUpdateTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Property editor factory. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Service delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;

	@Autowired
	@Qualifier("supervisoryOrganizationDelegate")
	private SupervisoryOrganizationDelegate supervisoryOrganizationDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationTermDelegate")
	private SupervisoryOrganizationTermDelegate 
			supervisoryOrganizationTermDelegate;
	
	@Autowired
	@Qualifier("placementTermDelegate")
	private PlacementTermDelegate placementTermDelegate;
	
	@Autowired
	@Qualifier("correctionalStatusDelegate")
	private CorrectionalStatusDelegate correctionalStatusDelegate;
	
	@Autowired
	@Qualifier("correctionalStatusTermDelegate")
	private CorrectionalStatusTermDelegate correctionalStatusTermDelegate;
	
	@Autowired
	@Qualifier("placementTermChangeReasonDelegate")
	private PlacementTermChangeReasonDelegate placementTermChangeReasonDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("supervisoryOrganizationTermService")
	private SupervisoryOrganizationTermService 
			supervisoryOrganizationTermService;
	
	/* Test methods. */
	
	/**
	 * Tests the update of a supervisory organization term.
	 * 
	 * @throws DuplicateEntityFoundException thrown if entity already exists
	 * @throws SupervisoryOrganizationTermConflictException thrown if two or 
	 * more supervisory organization terms conflict
	 * @throws DateRangeOutOfBoundsException thrown if the associated placement 
	 * terms are not within the date range
	 */
	@Test
	public void testUpdate() throws DuplicateEntityFoundException, 
			SupervisoryOrganizationTermConflictException, 
			DateRangeOutOfBoundsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Bob", null);
		SupervisoryOrganization supervisoryOrganization =
				this.supervisoryOrganizationDelegate.create("SuperOrg", "SO", 
						null);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("01/01/2018")); 
		SupervisoryOrganizationTerm supervisoryOrganizationTerm =
				this.supervisoryOrganizationTermDelegate.create(offender, 
						dateRange, supervisoryOrganization);
		dateRange = new DateRange(this.parseDateText("06/01/2017"), 
				this.parseDateText("01/01/2018"));
		
		// Action
		supervisoryOrganizationTerm = this.supervisoryOrganizationTermService
				.update(supervisoryOrganizationTerm, supervisoryOrganization, 
						dateRange);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("supervisoryOrganization", 
					supervisoryOrganization)
			.performAssertions(supervisoryOrganizationTerm);
	}
	
	/**
	 * Tests the {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException thrown if entity already exists
	 * @throws SupervisoryOrganizationTermConflictException thrown if two or 
	 * more supervisory organization terms conflict
	 * @throws DateRangeOutOfBoundsException thrown if the associated placement 
	 * terms are not within the date range
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException, 
			SupervisoryOrganizationTermConflictException,
			DateRangeOutOfBoundsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Bob", null);
		SupervisoryOrganization supervisoryOrganization =
				this.supervisoryOrganizationDelegate.create("SuperOrg", "SO", 
						null);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("01/01/2018"));
		this.supervisoryOrganizationTermDelegate.create(offender, dateRange, 
				supervisoryOrganization);
		dateRange = new DateRange(this.parseDateText("01/01/2018"), 
				this.parseDateText("01/01/2019"));
		SupervisoryOrganizationTerm supervisoryOrganizationTerm =
				this.supervisoryOrganizationTermDelegate.create(offender, 
						dateRange, supervisoryOrganization);
		dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("01/01/2018"));
		
		// Action
		supervisoryOrganizationTerm = this.supervisoryOrganizationTermService
				.update(supervisoryOrganizationTerm, supervisoryOrganization, 
						dateRange);
	}
	
	/**
	 * Tests the {@code SupervisoryOrganizationTermConflictException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException thrown if entity already exists
	 * @throws SupervisoryOrganizationTermConflictException thrown if two or 
	 * more supervisory organization terms conflict
	 * @throws DateRangeOutOfBoundsException thrown if the associated placement 
	 * terms are not within the date range
	 */
	@Test(expectedExceptions = 
			{SupervisoryOrganizationTermConflictException.class})
	public void testSupervisoryOrganizationTermConflictException() 
			throws DuplicateEntityFoundException, 
			SupervisoryOrganizationTermConflictException, 
			DateRangeOutOfBoundsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Bob", null);
		SupervisoryOrganization supervisoryOrganization =
				this.supervisoryOrganizationDelegate.create("SuperOrg", "SO", 
						null);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("01/01/2018")); 
		this.supervisoryOrganizationTermDelegate.create(offender, dateRange, 
				supervisoryOrganization);
		dateRange = new DateRange(this.parseDateText("01/01/2018"), 
				this.parseDateText("01/01/2019"));
		SupervisoryOrganizationTerm supervisoryOrganizationTerm =
				this.supervisoryOrganizationTermDelegate.create(offender, 
						dateRange, supervisoryOrganization);
		dateRange = new DateRange(this.parseDateText("06/01/2017"), 
				this.parseDateText("01/01/2018"));
		
		// Action
		supervisoryOrganizationTerm = this.supervisoryOrganizationTermService
				.update(supervisoryOrganizationTerm, supervisoryOrganization, 
						dateRange);
	}
	
	/**
	 * Tests the {@code DateRangeOutOfBoundsException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException thrown if entity already exists
	 * @throws SupervisoryOrganizationTermConflictException thrown if two or 
	 * more supervisory organization terms conflict
	 * @throws DateRangeOutOfBoundsException thrown if the associated placement 
	 * terms are not within the date range
	 */
	@Test(expectedExceptions = {DateRangeOutOfBoundsException.class})
	public void testDateRangeOutOfBoundsException() 
			throws DuplicateEntityFoundException, 
			SupervisoryOrganizationTermConflictException,
			DateRangeOutOfBoundsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Bob", null);
		SupervisoryOrganization supervisoryOrganization =
				this.supervisoryOrganizationDelegate.create("SuperOrg", "SO", 
						null);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("01/01/2018"));
		SupervisoryOrganizationTerm supervisoryOrganizationTerm =
				this.supervisoryOrganizationTermDelegate.create(offender, 
						dateRange, supervisoryOrganization);
		CorrectionalStatus correctionalStatus = this.correctionalStatusDelegate
				.create("Status", "S", false, (short) 1, true);
		CorrectionalStatusTerm correctionalStatusTerm = 
				this.correctionalStatusTermDelegate.create(offender, dateRange, 
				correctionalStatus);
		PlacementTermChangeReason startChangeReason = 
				this.placementTermChangeReasonDelegate.create("Reason", 
						(short) 1, true, true);
		this.placementTermDelegate.create(offender, dateRange, 
				supervisoryOrganizationTerm, correctionalStatusTerm, 
				startChangeReason, null, false);
		dateRange = new DateRange(this.parseDateText("06/01/2017"), 
				this.parseDateText("01/01/2018"));
		
		// Action
		supervisoryOrganizationTerm = this.supervisoryOrganizationTermService
				.update(supervisoryOrganizationTerm, supervisoryOrganization, 
						dateRange);
	}
	
	/* Helper methods. */
	
	// Parses date text
	private Date parseDateText(final String dateText) {
		CustomDateEditor customEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		customEditor.setAsText(dateText);
		return (Date) customEditor.getValue();
	}
}
