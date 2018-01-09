package omis.stg.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.domain.SecurityThreatGroupActivityInvolvement;
import omis.stg.domain.SecurityThreatGroupActivityNote;
import omis.stg.exception.InvolvedOffenderRequiredException;
import omis.stg.service.SecurityThreatGroupActivityService;
import omis.stg.service.delegate.SecurityThreatGroupActivityDelegate;
import omis.stg.service.delegate.SecurityThreatGroupActivityInvolvementDelegate;
import omis.stg.service.delegate.SecurityThreatGroupActivityNoteDelegate;

/**
 * Implementation of service for security threat group activity service.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Dec 1, 2016)
 * @since OMIS 3.0
 */

public class SecurityThreatGroupActivityServiceImpl 
		implements SecurityThreatGroupActivityService {

	private final SecurityThreatGroupActivityDelegate activityDelegate;
	
	private final SecurityThreatGroupActivityInvolvementDelegate involvementDelegate;
	
	private final SecurityThreatGroupActivityNoteDelegate noteDelegate;
	
	/**
	 * Instantiates an implementation of service for security threat group
	 * activities with the specified delegate.
	 * 
	 * @param activityDelegate delegate for security threat group activity.
	 * @param involvementDelegate delegate for security threat group activity
	 * involvements.
	 * @param noteDelegate delegate for security threat group activity notes.
	 */
	public SecurityThreatGroupActivityServiceImpl(
			final SecurityThreatGroupActivityDelegate activityDelegate,
			final SecurityThreatGroupActivityInvolvementDelegate 
				involvementDelegate,
			final SecurityThreatGroupActivityNoteDelegate noteDelegate) {
		this.activityDelegate = activityDelegate;
		this.involvementDelegate = involvementDelegate;
		this.noteDelegate = noteDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupActivity create(
			final Date reportDate, 
			final Person reportedBy, 
			final String summary) 
					throws DuplicateEntityFoundException {
		return this.activityDelegate.create(reportDate, reportedBy, summary);
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupActivity update(
			final SecurityThreatGroupActivity activity, 
			final Person reportedBy, 
			final Date reportDate,
			final String summary) 
					throws DuplicateEntityFoundException {
		return this.activityDelegate.update(activity, reportDate, reportedBy, 
				summary);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final SecurityThreatGroupActivity activity) {
		this.activityDelegate.remove(activity);		
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupActivityInvolvement involveOffender(
			final Offender offender, 
			final SecurityThreatGroupActivity activity,
			final String narrative)
			throws DuplicateEntityFoundException {
		return this.involvementDelegate.involveOffender(offender, activity, 
				narrative);
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupActivityInvolvement updateInvolvementNarrative(
			final SecurityThreatGroupActivityInvolvement involvement,
			final String narrative) 
					throws DuplicateEntityFoundException {
		return this.involvementDelegate.updateInvolvementNarrative(involvement, 
				narrative);
	}

	/** {@inheritDoc} */
	@Override
	public void removeInvolvement(
			final SecurityThreatGroupActivityInvolvement involvement)
					throws InvolvedOffenderRequiredException {
		this.involvementDelegate.removeInvolvement(involvement);
	}

	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupActivityInvolvement> findInvolvements(
			SecurityThreatGroupActivity activity) {
		return this.involvementDelegate.findInvolvements(activity);
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupActivityNote addNote(
			final SecurityThreatGroupActivity activity, 
			final Date date, 
			final String value)
					throws DuplicateEntityFoundException {
		return this.noteDelegate.addNote(activity, date, value);
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupActivityNote updateNote(
			final SecurityThreatGroupActivityNote note, 
			final Date date, 
			final String value)
					throws DuplicateEntityFoundException {
		return this.noteDelegate.updateNote(note, date, value);
	}

/*	*//** {@inheritDoc} *//*
	@Override
	public void SecurityThreatGroupActivityNote removeNote(
			final SecurityThreatGroupActivityNote note) {
		this.noteDelegate.remove(note);
	}*/
	
	/** {@inheritDoc} */
	@Override
	public void removeNote(
			final SecurityThreatGroupActivityNote note) {
		this.noteDelegate.remove(note);
	}

	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupActivityNote> findNotes(
			SecurityThreatGroupActivity activity) {
		return this.noteDelegate.findNotes(activity);
	}

}