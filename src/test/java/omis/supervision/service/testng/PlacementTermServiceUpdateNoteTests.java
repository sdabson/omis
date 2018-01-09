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

import java.beans.PropertyEditor;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.CorrectionalStatusTerm;
import omis.supervision.domain.PlacementTerm;
import omis.supervision.domain.PlacementTermChangeReason;
import omis.supervision.domain.PlacementTermNote;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.domain.SupervisoryOrganizationTerm;
import omis.supervision.exception.PlacementTermExistsException;
import omis.supervision.exception.PlacementTermNoteExistsException;
import omis.supervision.service.PlacementTermService;
import omis.supervision.service.delegate.CorrectionalStatusDelegate;
import omis.supervision.service.delegate.CorrectionalStatusTermDelegate;
import omis.supervision.service.delegate.PlacementTermChangeReasonDelegate;
import omis.supervision.service.delegate.PlacementTermDelegate;
import omis.supervision.service.delegate.PlacementTermNoteDelegate;
import omis.supervision.service.delegate.SupervisoryOrganizationDelegate;
import omis.supervision.service.delegate.SupervisoryOrganizationTermDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests updating of notes from placement term service. 
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Dec 13, 2017)
 * @since OMIS 3.0
 */
@Test(groups = {"placement", "service"})
public class PlacementTermServiceUpdateNoteTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */
	
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
	@Qualifier("correctionalStatusDelegate")
	private CorrectionalStatusDelegate correctionalStatusDelegate;
	
	@Autowired
	@Qualifier("correctionalStatusTermDelegate")
	private CorrectionalStatusTermDelegate correctionalStatusTermDelegate;
	
	@Autowired
	@Qualifier("placementTermChangeReasonDelegate")
	private PlacementTermChangeReasonDelegate placementTermChangeReasonDelegate;
	
	@Autowired
	@Qualifier("placementTermDelegate")
	private PlacementTermDelegate placementTermDelegate;
	
	@Autowired
	@Qualifier("placementTermNoteDelegate")
	private PlacementTermNoteDelegate placementTermNoteDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("placementTermService")
	private PlacementTermService placementTermService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;

	/* Tests. */
	
	/**
	 * Tests update of note for placement term.
	 * 
	 * @throws DuplicateEntityFoundException if related entity exists
	 * @throws PlacementTermExistsException if placement term note exists
	 */
	@Test
	public void testUpdate()
			throws DuplicateEntityFoundException,
				PlacementTermNoteExistsException {
		
		// Arrangements
		DateRange dateRange = new DateRange(
				this.parseDateText("12/13/2017"), null);
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("No", "Julius", null, null);
		SupervisoryOrganization supervisoryOrganization
			= this.supervisoryOrganizationDelegate.create(
					"Prison", "PRSN", null);
		SupervisoryOrganizationTerm supervisoryOrganizationTerm
			= this.supervisoryOrganizationTermDelegate
				.create(offender, dateRange, supervisoryOrganization);
		CorrectionalStatus correctionalStatus = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		CorrectionalStatusTerm correctionalStatusTerm
			= this.correctionalStatusTermDelegate.create(
					offender, dateRange, correctionalStatus);
		PlacementTermChangeReason startChangeReason
			= this.placementTermChangeReasonDelegate
				.create("Initial Commit", (short) 1,
						true, false);
		PlacementTermChangeReason endChangeReason = null;
		Boolean locked = true;
		PlacementTerm placementTerm = this.placementTermDelegate
				.create(offender, dateRange, supervisoryOrganizationTerm,
						correctionalStatusTerm, startChangeReason,
						endChangeReason, locked);
		Date date = this.parseDateText("12/13/2017");
		String value = "An unimaginative note";
		PlacementTermNote note = this.placementTermNoteDelegate
				.create(placementTerm, date, value);
		
		// Action
		Date updatedDate = this.parseDateText("12/14/2017");
		String updatedValue = "Equally unimaginatively noted";
		note = this.placementTermService.updateNote(
				note, updatedDate, updatedValue);
		
		// Assertions
		assert note.getDate().equals(updatedDate)
			: String.format("Wrong date - %s expected; %s found",
					updatedDate, note.getDate());
		assert note.getValue().equals(updatedValue)
			: String.format("Wrong value - %s expected; %s found",
					updatedValue, note.getValue());
	}
	
	/**
	 * Tests duplicates are prevented when placement term notes are updated.
	 * 
	 * @throws DuplicateEntityFoundException if related entities exist
	 * @throws PlacementTermNoteExistsException if placement term note exists
	 * (asserted)
	 */
	@Test(expectedExceptions = {PlacementTermNoteExistsException.class})
	public void testPlacementTermNoteExistsOnUpdate()
			throws DuplicateEntityFoundException,
				PlacementTermNoteExistsException {
		
		// Arrangements
		DateRange dateRange = new DateRange(
				this.parseDateText("12/13/2017"), null);
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("No", "Julius", null, null);
		SupervisoryOrganization supervisoryOrganization
			= this.supervisoryOrganizationDelegate.create(
					"Prison", "PRSN", null);
		SupervisoryOrganizationTerm supervisoryOrganizationTerm
			= this.supervisoryOrganizationTermDelegate
				.create(offender, dateRange, supervisoryOrganization);
		CorrectionalStatus correctionalStatus = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		CorrectionalStatusTerm correctionalStatusTerm
			= this.correctionalStatusTermDelegate.create(
					offender, dateRange, correctionalStatus);
		PlacementTermChangeReason startChangeReason
			= this.placementTermChangeReasonDelegate
				.create("Initial Commit", (short) 1,
						true, false);
		PlacementTermChangeReason endChangeReason = null;
		Boolean locked = true;
		PlacementTerm placementTerm = this.placementTermDelegate
				.create(offender, dateRange, supervisoryOrganizationTerm,
						correctionalStatusTerm, startChangeReason,
						endChangeReason, locked);
		Date date = this.parseDateText("12/13/2017");
		String value = "An unimaginative note";
		PlacementTermNote note = this.placementTermNoteDelegate
				.create(placementTerm, date, value);
		Date duplicatedDate = this.parseDateText("12/14/2017");
		String duplicatedValue = "Equally unimaginatively noted";
		this.placementTermNoteDelegate
			.create(placementTerm, duplicatedDate, duplicatedValue);
		
		// Action
		note = this.placementTermService.updateNote(
				note, duplicatedDate, duplicatedValue);
	}
	
	/* Helper methods. */
	
	// Parses date text
	private Date parseDateText(final String dateText) {
		PropertyEditor propertyEditor = this.datePropertyEditorFactory
				.createCustomDateOnlyEditor(true);
		propertyEditor.setAsText(dateText);
		return (Date) propertyEditor.getValue();
	}
}