package omis.workassignment.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.workassignment.domain.FenceRestriction;
import omis.workassignment.domain.WorkAssignment;
import omis.workassignment.domain.WorkAssignmentCategory;
import omis.workassignment.domain.WorkAssignmentChangeReason;
import omis.workassignment.domain.WorkAssignmentNote;
import omis.workassignment.service.WorkAssignmentService;
import omis.workassignment.service.delegate.FenceRestrictionDelegate;
import omis.workassignment.service.delegate.WorkAssignmentCategoryDelegate;
import omis.workassignment.service.delegate.WorkAssignmentChangeReasonDelegate;
import omis.workassignment.service.delegate.WorkAssignmentDelegate;
import omis.workassignment.service.delegate.WorkAssignmentNoteDelegate;

/**
 * Implementation of service for work assignment.
 * 
 * @author Yidong Li
 * @version 0.1.1 (Aug 18, 2016)
 * @since OMIS 3.0
 */
public class WorkAssignmentServiceImpl implements WorkAssignmentService {
	private final WorkAssignmentDelegate workAssignmentDelegate;
	private final FenceRestrictionDelegate fenceRestrictionDelegate;
	private final WorkAssignmentCategoryDelegate workAssignmentCategoryDelegate;
	private final WorkAssignmentChangeReasonDelegate 
		workAssignmentChangeReasonDelegate;
	private final WorkAssignmentNoteDelegate workAssignmentNoteDelegate;
	
	/**
	 * Instantiates an implementation of service for work assignment.
	 * 
	 * @param workAssignmentDelegate delegate for work assignment
	 * @param fenceRestrictionDelegate delegate for fence restriction
	 */
	public WorkAssignmentServiceImpl(
		final WorkAssignmentDelegate workAssignmentDelegate,
		final FenceRestrictionDelegate fenceRestrictionDelegate,
		final WorkAssignmentCategoryDelegate workAssignmentCategoryDelegate,
		final WorkAssignmentChangeReasonDelegate 
			workAssignmentChangeReasonDelegate,
		final WorkAssignmentNoteDelegate workAssignmentNoteDelegate) {
			this.workAssignmentDelegate = workAssignmentDelegate;
			this.fenceRestrictionDelegate = fenceRestrictionDelegate;
			this.workAssignmentCategoryDelegate = workAssignmentCategoryDelegate;
			this.workAssignmentChangeReasonDelegate 
				= workAssignmentChangeReasonDelegate;
			this.workAssignmentNoteDelegate = workAssignmentNoteDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public WorkAssignment create(final Offender offender, final FenceRestriction 
		fenceRestriction, final WorkAssignmentCategory category, 
		final WorkAssignmentChangeReason changeReason, final Date assignedDate, 
		final Date terminationDate, final String notes, final Boolean endExisting)
			throws DuplicateEntityFoundException {
		return this.workAssignmentDelegate.create(offender, fenceRestriction,
			category, changeReason, assignedDate, terminationDate, notes,
			endExisting);
	}

	/** {@inheritDoc} */
	@Override
	public WorkAssignment update(final WorkAssignment workAssignment, 
		final FenceRestriction fenceRestriction, final WorkAssignmentCategory 
		category, final WorkAssignmentChangeReason changeReason, final Date 
		assignedDate, final Date terminationDate, final String notes, 
		final Boolean endExisting)
			throws DuplicateEntityFoundException {
		return this.workAssignmentDelegate.update(workAssignment, fenceRestriction,
			category, changeReason, assignedDate, terminationDate, notes, 
			endExisting);
	}
			
	/** {@inheritDoc} */
	@Override
	public List<WorkAssignment> findByOffender(final Offender offender) {
		return this.workAssignmentDelegate.findByOffender(offender);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final WorkAssignment workAssignment) {
		this.workAssignmentDelegate.remove(workAssignment);
	}
		
	/** {@inheritDoc} */
	@Override
	public List<FenceRestriction> findFenceRestrictions() {
		return this.fenceRestrictionDelegate.find();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<WorkAssignmentCategory> findCategories() {
		return this.workAssignmentCategoryDelegate.find();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<WorkAssignmentChangeReason> findChangeReasons() {
		return this.workAssignmentChangeReasonDelegate.find();
	}
	
	/** {@inheritDoc} */
	@Override
	public WorkAssignmentNote addNote(final WorkAssignment workAssignment, 
		final Date date, final String value)
		throws DuplicateEntityFoundException {
			return this.workAssignmentNoteDelegate.addNote(workAssignment, date, 
				value);
	}
	
	/** {@inheritDoc} */
	@Override
	public WorkAssignmentNote updateNote(final WorkAssignmentNote note, 
		final Date date, final String value)throws DuplicateEntityFoundException {
		return this.workAssignmentNoteDelegate.updateNote(note, date, value);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeNote(final WorkAssignmentNote note) {
		this.workAssignmentNoteDelegate.removeNote(note);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<WorkAssignmentNote> findNotes(WorkAssignment workAssignment) {
		return this.workAssignmentNoteDelegate.findNotes(workAssignment);
	}
}