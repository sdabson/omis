package omis.separationneed.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.relationship.domain.Relationship;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.relationship.service.delegate.RelationshipDelegate;
import omis.separationneed.domain.SeparationNeed;
import omis.separationneed.domain.SeparationNeedNote;
import omis.separationneed.domain.SeparationNeedReason;
import omis.separationneed.domain.SeparationNeedReasonAssociation;
import omis.separationneed.domain.SeparationNeedRemovalReason;
import omis.separationneed.service.SeparationNeedService;
import omis.separationneed.service.delegate.SeparationNeedDelegate;
import omis.separationneed.service.delegate.SeparationNeedNoteDelegate;
import omis.separationneed.service.delegate.SeparationNeedReasonAssociationDelegate;
import omis.separationneed.service.delegate.SeparationNeedReasonDelegate;
import omis.separationneed.service.delegate.SeparationNeedRemovalReasonDelegate;

/**
 * Separation need service implementation.
 * 
 * @author Joel Norris 
 * @version 0.1.1 (May 5, 2015)
 * @since OMIS 3.0
 */
public class SeparationNeedServiceImpl implements SeparationNeedService {

	/* Data access objects. */
	
	private SeparationNeedDelegate separationNeedDelegate;
	
	private RelationshipDelegate relationshipDelegate;
	
	private SeparationNeedReasonDelegate separationNeedReasonDelegate;
	
	private SeparationNeedReasonAssociationDelegate
		separationNeedReasonAssociationDelegate;
	
	private SeparationNeedNoteDelegate separationNeedNoteDelegate;
	
	private SeparationNeedRemovalReasonDelegate 
		separationNeedRemovalReasonDelegate;
	
	/**
	 * Instantiates a separation need service implementation with the specified
	 * data access objects and instance factory.
	 * 
	 * @param separationNeedDelegate separation need data access object
	 * @param relationshipDelegate relationship data access object
	 * @param separationNeedReasonDelegate separation need reason data access object
	 * @param separationNeedReasonAssociationDelegate separation need reason
	 * association data access object
	 * @param separationNeedNoteDelegate separation need note data access object
	 * @param userAccountDao user account data access object
	 * @param separationNeedRemovalReasonDelegate separation need removal reason
	 * data access object
	 * @param relationshipInstanceFactory relationship instance factory
	 * @param separationNeedInstanceFactory separation need instance factory
	 * @param separationNeedReasonAssociationInstanceFactory separation need
	 * reason association instance factory
	 * @param separationNeedNoteInstanceFactory separation need note instance
	 * factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public SeparationNeedServiceImpl(
			final SeparationNeedDelegate separationNeedDelegate,
			final RelationshipDelegate relationshipDelegate,
			final SeparationNeedReasonDelegate separationNeedReasonDelegate,
			final SeparationNeedReasonAssociationDelegate
				separationNeedReasonAssociationDelegate,
			final SeparationNeedNoteDelegate separationNeedNoteDelegate,
			final SeparationNeedRemovalReasonDelegate 
				separationNeedRemovalReasonDelegate) {
		this.separationNeedDelegate = separationNeedDelegate;
		this.relationshipDelegate = relationshipDelegate;
		this.separationNeedReasonDelegate = separationNeedReasonDelegate;
		this.separationNeedReasonAssociationDelegate
			= separationNeedReasonAssociationDelegate;
		this.separationNeedNoteDelegate = separationNeedNoteDelegate;
		this.separationNeedRemovalReasonDelegate = separationNeedRemovalReasonDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public SeparationNeed create(final Relationship relationship, 
			final Date date, final String creationComment, 
			final Person reportingStaff, final Date removalDate,
			final SeparationNeedRemovalReason removalReason,
			final String removalComment)
			throws DuplicateEntityFoundException, DateConflictException {
		return this.separationNeedDelegate.create(relationship, date, 
				creationComment, reportingStaff, removalDate, removalReason, 
				removalComment);
	}
	
	/** {@inheritDoc} */
	@Override
	public SeparationNeed update(final SeparationNeed separationNeed,
			final Relationship relationship, 
			final Date date, final String creationComment, 
			final Person reportingStaff, final Date removalDate,
			final SeparationNeedRemovalReason removalReason,
			final String removalComment)
		throws DuplicateEntityFoundException, DateConflictException {
		return this.separationNeedDelegate.update(separationNeed, relationship, 
				date, creationComment, reportingStaff, removalDate, 
				removalReason, removalComment);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SeparationNeed> findAll() {
		return this.separationNeedDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<SeparationNeed> findByOffender(final Offender offender) {
		return this.separationNeedDelegate.findByOffender(offender);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SeparationNeed> findByTargetOffender(final Offender offender) {
		return this.separationNeedDelegate.findByTargetOffender(offender);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final SeparationNeed separationNeed) {
		this.separationNeedDelegate.remove(separationNeed);
	}
	
	/** {@inheritDoc} */
	@Override
	public SeparationNeed associate(final SeparationNeed separationNeed,
			final Offender offender, final Offender targetOffender) 
					throws ReflexiveRelationshipException {
		Relationship relationship = this.relationshipDelegate.findOrCreate(
				offender, targetOffender);
		return this.separationNeedDelegate.associate(separationNeed, 
				relationship);
	}

	/** {@inheritDoc} */
	@Override
	public List<SeparationNeedReason> findReasons() {
		return this.separationNeedReasonDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<SeparationNeedReasonAssociation> 
		findReasonAssociationsBySeparationNeed(
				final SeparationNeed separationNeed) {
		return this.separationNeedReasonAssociationDelegate
				.findBySeparationNeed(separationNeed);
	}

	/** {@inheritDoc} */
	@Override
	public List<SeparationNeedNote> findNotesBySeparationNeed(
			SeparationNeed separationNeed) {
		return this.separationNeedNoteDelegate.findBySeparationNeed(
				separationNeed);
	}

	
	
	/** {@inheritDoc} */
	@Override
	public Relationship findRelationship(final Offender offender,
			final Offender targetOffender) {
		return this.relationshipDelegate.find(offender, targetOffender);
		
	}

	/** {@inheritDoc} */
	@Override
	public Relationship createRelationship(final Offender offender,
			final Offender targetOffender) 
					throws ReflexiveRelationshipException {
		return this.relationshipDelegate.findOrCreate(offender, targetOffender);
	}

	/** {@inheritDoc} */
	@Override
	public SeparationNeedReasonAssociation associateReason(
			final SeparationNeed separationNeed, 
			final SeparationNeedReason reason)
		throws DuplicateEntityFoundException {
		return this.separationNeedReasonAssociationDelegate.create(
				separationNeed, reason);
	}

	/** {@inheritDoc} */
	@Override
	public SeparationNeedReasonAssociation updateAssociatedReason(
			final SeparationNeedReasonAssociation association,
			final SeparationNeedReason reason)
			throws DuplicateEntityFoundException {
		return this.separationNeedReasonAssociationDelegate.update(association, 
				reason);
	}

	/** {@inheritDoc} */
	@Override
	public void removeAssociatedReason(
			final SeparationNeedReasonAssociation association) {
		this.separationNeedReasonAssociationDelegate.remove(association);
	}

	/** {@inheritDoc} */
	@Override
	public SeparationNeedNote addNote(final SeparationNeed separationNeed,
			final Date date, final String value)
		throws DuplicateEntityFoundException {
		return this.separationNeedNoteDelegate.create(separationNeed, date, 
				value);
	}

	/** {@inheritDoc} */
	@Override
	public SeparationNeedNote updateNote(final SeparationNeedNote note,
			final Date date, final String value)
		throws DuplicateEntityFoundException {
		return this.separationNeedNoteDelegate.update(note, date, value);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeNote(final SeparationNeedNote note) {
		this.separationNeedNoteDelegate.remove(note);
	}

	/** {@inheritDoc} */
	@Override
	public List<SeparationNeedRemovalReason> findRemovalReasons() {
		return this.separationNeedRemovalReasonDelegate.findAll();
	}
}