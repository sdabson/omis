package omis.workrestriction.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.domain.AuthorizationSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;
import omis.workrestriction.domain.WorkRestriction;
import omis.workrestriction.domain.WorkRestrictionCategory;
import omis.workrestriction.domain.WorkRestrictionNote;
import omis.workrestriction.service.WorkRestrictionService;
import omis.workrestriction.service.delegate.WorkRestrictionCategoryDelegate;
import omis.workrestriction.service.delegate.WorkRestrictionDelegate;
import omis.workrestriction.service.delegate.WorkRestrictionNoteDelegate;

/**
 * WorkRestrictionServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
public class WorkRestrictionServiceImpl implements WorkRestrictionService {
	
	private final WorkRestrictionDelegate workRestrictionDelegate;
	
	private final WorkRestrictionCategoryDelegate categoryDelegate;
	
	private final UserAccountDelegate userAccountDelegate;
	
	private final WorkRestrictionNoteDelegate workRestrictionNoteDelegate;
	
	/**
	 * Constructor for Work Restriction Service Implementation
	 * @param workRestrictionDelegate
	 * @param categoryDelegate
	 * @param userAccountDelegate
	 */
	public WorkRestrictionServiceImpl(
			final WorkRestrictionDelegate workRestrictionDelegate,
			final WorkRestrictionCategoryDelegate categoryDelegate, 
			final UserAccountDelegate userAccountDelegate,
			final WorkRestrictionNoteDelegate workRestrictionNoteDelegate) {
		this.workRestrictionDelegate = workRestrictionDelegate;
		this.categoryDelegate = categoryDelegate;
		this.userAccountDelegate = userAccountDelegate;
		this.workRestrictionNoteDelegate = workRestrictionNoteDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public WorkRestriction create(final Offender offender,
			final WorkRestrictionCategory category,
			final AuthorizationSignature authorizationSignature, 
			final String notes) throws DuplicateEntityFoundException {
		return this.workRestrictionDelegate.create(offender, category, 
				authorizationSignature, notes);
	}

	/**{@inheritDoc} */
	@Override
	public WorkRestriction update(final WorkRestriction workRestriction, 
			final WorkRestrictionCategory category,
			final AuthorizationSignature authorizationSignature, 
			final String notes) throws DuplicateEntityFoundException {
		return this.workRestrictionDelegate.update(workRestriction, category, 
				authorizationSignature, notes);
	}

	/**{@inheritDoc} */
	@Override
	public void remove(final WorkRestriction workRestriction) {
		this.workRestrictionDelegate.remove(workRestriction);
	}

	/**{@inheritDoc} */
	@Override
	public List<WorkRestriction> findByOffender(final Offender offender) {
		return this.workRestrictionDelegate.findByOffender(offender);
	}

	/**{@inheritDoc} */
	@Override
	public List<UserAccount> findUserAccounts(final String query) {
		return this.userAccountDelegate.search(query);
	}

	/**{@inheritDoc} */
	@Override
	public List<WorkRestrictionCategory> findAllCategories() {
		return this.categoryDelegate.findAll();
	}

	/**{@inheritDoc} */
	@Override
	public WorkRestrictionNote createNote(final WorkRestriction workRestriction, 
			final String value, final Date date)
					throws DuplicateEntityFoundException{
		return this.workRestrictionNoteDelegate
				.create(workRestriction, value, date);
	}

	/**{@inheritDoc} */
	@Override
	public WorkRestrictionNote updateNote(
			final WorkRestrictionNote workRestrictionNote, final String value, 
			final Date date) throws DuplicateEntityFoundException{
		return this.workRestrictionNoteDelegate
				.update(workRestrictionNote, value, date);
	}

	/**{@inheritDoc} */
	@Override
	public void removeNote(final WorkRestrictionNote workRestrictionNote) {
		this.workRestrictionNoteDelegate.remove(workRestrictionNote);
	}

	/**{@inheritDoc} */
	@Override
	public List<WorkRestrictionNote> findNotes(
			final WorkRestriction workRestriction) {
		return this.workRestrictionNoteDelegate
				.findByWorkRestriction(workRestriction);
	}

}
