package omis.presentenceinvestigation.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.HealthSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.HealthSectionSummary;
import omis.presentenceinvestigation.domain.HealthSectionSummaryNote;

/**
 * Health section summary note delegate.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 8, 2017)
 * @since OMIS 3.0
 */
public class HealthSectionSummaryNoteDelegate {

	private final HealthSectionSummaryNoteDao 
		healthSectionSummaryNoteDao;

	private final InstanceFactory<HealthSectionSummaryNote> 
		healthSectionSummaryNoteInstanceFactory;

	private final AuditComponentRetriever auditComponentRetriever;
	
	
	/** Instantiates an implementation of HealthSectionSummaryNoteDelegate */
	public HealthSectionSummaryNoteDelegate(
			final HealthSectionSummaryNoteDao
				healthSectionSummaryNoteDao,
			final InstanceFactory<HealthSectionSummaryNote> 
				healthSectionSummaryNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {

		this.healthSectionSummaryNoteDao = healthSectionSummaryNoteDao;
		this.healthSectionSummaryNoteInstanceFactory 
			= healthSectionSummaryNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
		
	/**
	 * Create a health section summary note.
	 *
	 *
	 * @param healthSectionSummary health section summary
	 * @param description description
	 * @param date date
	 * @return new health section summary
	 * @throws DuplicateEntityFoundException
	 */
	public HealthSectionSummaryNote createHealthSectionSummaryNote(
			HealthSectionSummary healthSectionSummary, String description, 
			Date date) throws DuplicateEntityFoundException {		
		if (this.healthSectionSummaryNoteDao.find(
				healthSectionSummary, description, date) != null) {
			throw new DuplicateEntityFoundException(
					"Health section summary note with this "
					+ "description and date exist.");
		}
		
		HealthSectionSummaryNote note 
		= this.healthSectionSummaryNoteInstanceFactory.createInstance();
		note.setHealthSectionSummary(healthSectionSummary);
		note.setDescription(description);
		note.setDate(date);
		note.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		note.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.healthSectionSummaryNoteDao.makePersistent(note);
	}
	
	/**
	 * Updates an existing health section summary note.
	 *
	 *
	 * @param healthSectionSummaryNote health section summary note
	 * @param healthSectionSummary health section summary
	 * @param description description
	 * @param date date
	 * @return updated health section summary note
	 * @throws DuplicateEntityFoundException
	 */
	public HealthSectionSummaryNote updateHealthSectionSummaryNote(
			HealthSectionSummaryNote healthSectionSummaryNote, 
			HealthSectionSummary healthSectionSummary, String description, 
			Date date) throws DuplicateEntityFoundException {
		if (this.healthSectionSummaryNoteDao.findExcluding(
				healthSectionSummaryNote, healthSectionSummary, 
				description, date) != null) {
			throw new DuplicateEntityFoundException(
					"Health section summary note with this "
					+ "description and date exist.");
		}
		
		healthSectionSummaryNote.setHealthSectionSummary(healthSectionSummary);
		healthSectionSummaryNote.setDescription(description);
		healthSectionSummaryNote.setDate(date);
		healthSectionSummaryNote.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.healthSectionSummaryNoteDao.makePersistent(
				healthSectionSummaryNote);
	}	

	/**
	 * Remove health section summary note.
	 *
	 *
	 * @param healthSectionSummaryNote health section summary note
	 */
	public void removeHealthSectionSummaryNote(
			HealthSectionSummaryNote healthSectionSummaryNote) {
		this.healthSectionSummaryNoteDao.makeTransient(
				healthSectionSummaryNote);
	}

	/**
	 * List health section summary notes by health section summary.
	 *
	 * @param healthSectionSummary health section summary
	 * @return list of health section summary notes
	 */
	public List<HealthSectionSummaryNote> 
	findHealthSectionSummaryNotesByHealthSectionSummary(
			HealthSectionSummary healthSectionSummary) {
		return this.healthSectionSummaryNoteDao
				.findHealthSectionSummaryNotesByHealthSectionSummary(
						healthSectionSummary);
	}
}