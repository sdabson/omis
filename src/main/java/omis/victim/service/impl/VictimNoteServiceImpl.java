package omis.victim.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.VictimNote;
import omis.victim.domain.VictimNoteCategory;
import omis.victim.service.VictimNoteService;
import omis.victim.service.delegate.VictimAssociationDelegate;
import omis.victim.service.delegate.VictimNoteCategoryDelegate;
import omis.victim.service.delegate.VictimNoteDelegate;

/**
 * Implementation of service for victim notes.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 22, 2015)
 * @since OMIS 3.0
 */
public class VictimNoteServiceImpl
		implements VictimNoteService {
	
	/* Delegates. */
	
	private final VictimNoteDelegate victimNoteDelegate;
	
	private final VictimAssociationDelegate victimAssociationDelegate;
	
	private final VictimNoteCategoryDelegate victimNoteCategoryDelegate;
	
	/* Constructors. */
	
	/**
	 * Instantiates implementation of service for victim notes.
	 * 
	 * @param victimNoteDelegate delegate for victim notes
	 * @param victimAssociationDelegate delegate for victim associations
	 * @param contactDelegate delegate for contacts
	 * @param victimNoteCategoryDelegate delegate for victim note categories
	 */
	public VictimNoteServiceImpl(
			final VictimNoteDelegate victimNoteDelegate,
			final VictimAssociationDelegate victimAssociationDelegate,
			final VictimNoteCategoryDelegate victimNoteCategoryDelegate) {
		this.victimNoteDelegate = victimNoteDelegate;
		this.victimAssociationDelegate = victimAssociationDelegate;
		this.victimNoteCategoryDelegate = victimNoteCategoryDelegate;
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public VictimNote create(final Person victim,
			final VictimNoteCategory category,
			final VictimAssociation association,
			final Date date, final String value)
					throws DuplicateEntityFoundException {
		return this.victimNoteDelegate.create(
				victim, category, association, date, value);
	}

	/** {@inheritDoc} */
	@Override
	public VictimNote update(final VictimNote victimNote,
			final VictimNoteCategory category,
			final VictimAssociation association,
			final Date date, final String value)
					throws DuplicateEntityFoundException {
		return this.victimNoteDelegate.update(
				victimNote, category, association, date, value);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<VictimNote> findByVictim(final Person victim) {
		return this.victimNoteDelegate.findByVictim(victim);
	}

	/** {@inheritDoc} */
	@Override
	public List<VictimAssociation> findAssociationsForVictim(
			final Person victim) {
		return this.victimAssociationDelegate.findByVictim(victim);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<VictimNoteCategory> findCategories() {
		return this.victimNoteCategoryDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final VictimNote victimNote) {
		this.victimNoteDelegate.remove(victimNote);
	}
}