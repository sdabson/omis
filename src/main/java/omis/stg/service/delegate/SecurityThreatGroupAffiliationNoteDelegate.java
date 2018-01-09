package omis.stg.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.stg.dao.SecurityThreatGroupAffiliationNoteDao;
import omis.stg.domain.SecurityThreatGroupAffiliation;
import omis.stg.domain.SecurityThreatGroupAffiliationNote;

/**
 * Security threat group affiliation note.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (October 20, 2016)
 * @since OMIS 3.0
 */

public class SecurityThreatGroupAffiliationNoteDelegate {

	/* Instance factories. */
	private final InstanceFactory<SecurityThreatGroupAffiliationNote> 
	affiliationNoteInstanceFactory;
	
	/* DAOs. */
	private final SecurityThreatGroupAffiliationNoteDao affiliationNoteDao;
	
	/* Audit component retriever. */
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructor. */
	
	/** Instantiates delegate for security threat group affiliation notes.
	 * @param securityThreatGroupAffiliationNoteDao data access object for
	 * affiliation notes.
	 */
	public SecurityThreatGroupAffiliationNoteDelegate(
			final InstanceFactory<SecurityThreatGroupAffiliationNote> 
				affiliationNoteInstanceFactory,
			final SecurityThreatGroupAffiliationNoteDao affiliationNoteDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.affiliationNoteInstanceFactory = affiliationNoteInstanceFactory;
		this.affiliationNoteDao = affiliationNoteDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** Creates a security threat group affiliation note.
	 * @param SecurityThreatGroupAffiliation - affiliation
	 * @param Date - date
	 * @param String - note
	 * @return Creates security threat group note.
	 * @throws DuplicateEntityFoundException - when security threat group
	 * affiliation note already exists.
	 */
	public SecurityThreatGroupAffiliationNote addNote(
			final SecurityThreatGroupAffiliation affiliation,
			final Date date, String note)
	throws DuplicateEntityFoundException {
		if (this.affiliationNoteDao.findAffiliationNote(affiliation, date, note) != null) {
			throw new DuplicateEntityFoundException("Duplicate security threat "
					+ "group affiliation note found.");
		}
		SecurityThreatGroupAffiliationNote affiliationNote = 
			this.affiliationNoteInstanceFactory.createInstance();
				affiliationNote.setCreationSignature(new CreationSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
				affiliationNote.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
				affiliationNote.setDate(date);
				affiliationNote.setNote(note);
				affiliationNote.setAffiliation(affiliation);
			return this.affiliationNoteDao.makePersistent(affiliationNote);
	}
	
	/** Updates a security threat group affiliation note.
	 * @param SecurityThreatGroupAffiliation - affiliation
	 * @param Date - date
	 * @param String - note
	 * @return Updates security threat group note.
	 * @throws DuplicateEntityFoundException - when security threat group
	 * affiliation note already exists.
	 */
	public SecurityThreatGroupAffiliationNote update(
			SecurityThreatGroupAffiliationNote affiliationNote, Date date, 
			String note)
		throws DuplicateEntityFoundException {
		if (this.affiliationNoteDao.findExcluding( 
				affiliationNote, affiliationNote.getAffiliation(), date, note) 
				!= null) {
			throw new DuplicateEntityFoundException(
					"Duplicate security threat group note found.");
		}
		this.populateSecurityThreatGroupAffiliationNote(affiliationNote, date, 
				note);
		return this.affiliationNoteDao.makePersistent(affiliationNote);
	}
	
	/** Removes a security threat group affiliation note.
	 * @param SecurityThreatGroupAffiliation - affiliation
	 * @param Date - date
	 * @param String - note
	 * @return Removes security threat group note.
	 * @throws DuplicateEntityFoundException - when security threat group
	 * affiliation note already exists.
	 */
	public void remove(final SecurityThreatGroupAffiliationNote affiliationNote) {
		this.affiliationNoteDao.makeTransient(affiliationNote);
	}
	
	/** Populates the specified security threat group affiliation note.
	 * 
	 * @param date - date
	 * @param note - note
	 * @return populated security threat group affiliation note.
	 */
	private SecurityThreatGroupAffiliationNote 
		populateSecurityThreatGroupAffiliationNote(
				final SecurityThreatGroupAffiliationNote stgNote,
				final Date date, final String note) {
		stgNote.setDate(date);
		stgNote.setNote(note);
		stgNote.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return stgNote;
	}
	
	/**
	 * Returns a list of security threat group affiliation notes for the 
	 * specified security threat group affiliation.
	 * 
	 * @param affiliation security threat group
	 * @return list of security threat group notes
	 */
	public List<SecurityThreatGroupAffiliationNote> 
	findAffiliationNotesByAffiliation(
			SecurityThreatGroupAffiliation affiliation) {
		return this.affiliationNoteDao.findNotes(affiliation);
	}
}
