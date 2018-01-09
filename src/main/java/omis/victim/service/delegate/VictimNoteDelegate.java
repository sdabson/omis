package omis.victim.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;
import omis.victim.dao.VictimNoteDao;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.VictimNote;
import omis.victim.domain.VictimNoteCategory;

/**
 * Delegate for victim notes.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 22, 2015)
 * @since OMIS 3.0
 */
public class VictimNoteDelegate {
	
	/* Data access objects. */
	
	private final VictimNoteDao victimNoteDao;
	
	private final InstanceFactory<VictimNote> victimNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Instance factories. */

	/**
	 * Instantiates delegate for victim notes.
	 * 
	 * @param victimNoteDao data access object for victim notes
	 * @param victimNoteInstanceFactory instance factory for victim notes
	 * @param auditComponentRetriever audit component retriever
	 */
	public VictimNoteDelegate(
			final VictimNoteDao victimNoteDao,
			final InstanceFactory<VictimNote> victimNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.victimNoteDao = victimNoteDao;
		this.victimNoteInstanceFactory = victimNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Methods. */
	
	/**
	 * Creates victim note.
	 * 
	 * @param victim victim
	 * @param category category
	 * @param association association
	 * @param contact contact
	 * @param date date
	 * @param value value
	 * @return created victim note
	 * @throws DuplicateEntityFoundException if victim note exists
	 */
	public VictimNote create(
			final Person victim, final VictimNoteCategory category,
			final VictimAssociation association, final Date date,
			final String value)
				throws DuplicateEntityFoundException {
		
		// If association is provided, checks that victim and second person
		// of relationship are equal; otherwise, association is illegal
		if (association != null) {
			if (!victim.equals(association.getRelationship()
					.getSecondPerson())) {
				throw new IllegalArgumentException(
						"Second person of relationship does not equal victim");
			}
		}
		
		// Checks whether victim note exists
		if (this.victimNoteDao.find(victim, category, date) != null) {
			throw new DuplicateEntityFoundException("Victim note exists");
		}
		
		// Creates, persists and updates victim note
		VictimNote victimNote = this.victimNoteInstanceFactory.createInstance();
		victimNote.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		victimNote.setVictim(victim);
		this.populate(victimNote, category, association, date, value);
		return this.victimNoteDao.makePersistent(victimNote);
	}
	
	/**
	 * Updates victim note.
	 * 
	 * @param victimNote victim note to update
	 * @param category category
	 * @param association association
	 * @param date date
	 * @param value value
	 * @return updated victim note
	 * @throws DuplicateEntityFoundException if victim note exists
	 */
	public VictimNote update(final VictimNote victimNote,
			final VictimNoteCategory category,
			final VictimAssociation association,
			final Date date, final String value)
				throws DuplicateEntityFoundException {
		if (association != null) {
			// If association is provided, checks that victim of note and second
			// person of relationship are equal; otherwise, association is illegal
			if (association.getRelationship().getSecondPerson() != null) {
				if (!victimNote.getVictim().equals(association.getRelationship()
						.getSecondPerson())) {
					throw new IllegalArgumentException(
							"Second person of relationship does not equal victim");
				}
			}
		}
		
		// Checks whether victim note exists
		if (this.victimNoteDao.findExcluding(victimNote.getVictim(), category,
				date, victimNote) != null) {
			throw new DuplicateEntityFoundException("Victim note exists");
		}
		
		// Updates, persists and returns victim note
		this.populate(victimNote, category, association, date, value);
		return this.victimNoteDao.makePersistent(victimNote);
	}
	
	/**
	 * Returns victim notes by victim.
	 * 
	 * @param victim victim
	 * @return victim notes by victim
	 */
	public List<VictimNote> findByVictim(final Person victim) {
		return this.victimNoteDao.findByVictim(victim);
	}
	
	/**
	 * Returns victim notes by association.
	 * 
	 * @param association association
	 * @return victim notes by association
	 */
	public List<VictimNote> findByAssociation(
			final VictimAssociation association) {
		return this.victimNoteDao.findByAssociation(association);
	}
	
	/**
	 * Removes victim note.
	 * 
	 * @param victimNote victim note
	 */
	public void remove(final VictimNote victimNote) {
		this.victimNoteDao.makeTransient(victimNote);
	}
	
	/**
	 * Disassociates victim note from victim association.
	 * 
	 * @param victimNote victim note to disassociate
	 * @return victim note
	 */
	public VictimNote disassociateFromVictimAssociation(
			final VictimNote victimNote) {
		victimNote.setAssociation(null);
		return this.victimNoteDao.makePersistent(victimNote);
	}
	
	/* Helper methods. */
	
	// Populates note
	private void populate(final VictimNote victimNote,
			final VictimNoteCategory category,
			final VictimAssociation association,
			final Date date, final String value) {
		victimNote.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		victimNote.setCategory(category);
		victimNote.setAssociation(association);
		victimNote.setDate(date);
		victimNote.setValue(value);
	}

	/**
	 * Returns count of notes by association.
	 * 
	 * @param association association
	 * @return count of notes by association
	 */
	public long countByAssociation(VictimAssociation association) {
		return this.victimNoteDao.countNotes(association);
	}
}