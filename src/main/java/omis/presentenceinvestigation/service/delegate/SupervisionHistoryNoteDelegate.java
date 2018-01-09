package omis.presentenceinvestigation.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.SupervisionHistoryNoteDao;
import omis.presentenceinvestigation.domain.SupervisionHistoryNote;
import omis.presentenceinvestigation.domain.SupervisionHistorySectionSummary;

/**
 * Supervision history note delegate.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 9, 2017)
 * @since OMIS 3.0
 */
public class SupervisionHistoryNoteDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG
	= "Supervision history note already exists for specified "
		+ "supervision history section summary";
	
	private final SupervisionHistoryNoteDao supervisionHistoryNoteDao;
	
	private final InstanceFactory<SupervisionHistoryNote> 
		supervisionHistoryNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/** Instantiates an implementation of SupervisionHistoryNoteDelegate */
	public SupervisionHistoryNoteDelegate(
			final SupervisionHistoryNoteDao supervisionHistoryNoteDao,
			final InstanceFactory<SupervisionHistoryNote> 
			supervisionHistoryNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.supervisionHistoryNoteDao = supervisionHistoryNoteDao;
		this.supervisionHistoryNoteInstanceFactory 
			= supervisionHistoryNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/**
	 * Creates a new supervision history note, for this summary.
	 *
	 *
	 * @param description description
	 * @param date date
	 * @param supervisionHistorySectionSummary supervision history 
	 * section summary
	 * @return new supervision history note
	 * @throws DuplicateEntityFoundException
	 */
	public SupervisionHistoryNote createSupervisionHistoryNote(
			final String description, final Date date,
			final SupervisionHistorySectionSummary 
			supervisionHistorySectionSummary) 
					throws DuplicateEntityFoundException {
		if (this.supervisionHistoryNoteDao.find(
				supervisionHistorySectionSummary, description, date) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		SupervisionHistoryNote note 
			= this.supervisionHistoryNoteInstanceFactory.createInstance();
		note.setDate(date);
		note.setDescription(description);		
		note.setSupervisionHistorySectionSummary(
				supervisionHistorySectionSummary);
		note.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		note.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.supervisionHistoryNoteDao.makePersistent(note);
		
	}
	
	/**
	 * Updates this supervision history note.
	 *
	 *
	 * @param supervisionHistoryNote supervision history note
	 * @param description description
	 * @param date date
	 * @return updated supervision history note
	 * @throws DuplicateEntityFoundException
	 */
	public SupervisionHistoryNote updateSupervisionHistoryNote(
			final SupervisionHistoryNote supervisionHistoryNote,
			final SupervisionHistorySectionSummary 
			supervisionHistorySectionSummary,
			final String description, final Date date) 
					throws DuplicateEntityFoundException {		
		if (this.supervisionHistoryNoteDao.findExcluding(supervisionHistoryNote,
				supervisionHistorySectionSummary, description, date) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}

		supervisionHistoryNote.setSupervisionHistorySectionSummary(
				supervisionHistorySectionSummary);
		supervisionHistoryNote.setDate(date);
		supervisionHistoryNote.setDescription(description);
		supervisionHistoryNote.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.supervisionHistoryNoteDao.makePersistent(
				supervisionHistoryNote);
	}
	
	/**
	 * Remove this supervision history note.
	 *
	 *
	 * @param supervisionHistoryNote supervision history note
	 */
	public void removeSupervisionHistoryNote(
			final SupervisionHistoryNote supervisionHistoryNote) {
		this.supervisionHistoryNoteDao.makeTransient(supervisionHistoryNote);
	}
	
	/**
	 * Finds a list of supervision history notes by supervision history 
	 * section summary.
	 *
	 *
	 * @param supervisionHistorySectionSummary supervision history 
	 * section summary
	 * @return list of supervision history notes
	 */
	public List<SupervisionHistoryNote> 
	findSupervisionHistoryNotesBySupervisionHistorySectionSummary(
			final SupervisionHistorySectionSummary 
			supervisionHistorySectionSummary) {
		return this.supervisionHistoryNoteDao
				.findSupervisionHistoryNotesBySupervisionHistorySectionSummary(
						supervisionHistorySectionSummary);
	}
}