package omis.presentenceinvestigation.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.JailAdjustmentSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummary;
import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummaryNote;

/**
 * JailAdjustmentSectionSummaryNoteDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 24, 2017)
 *@since OMIS 3.0
 *
 */
public class JailAdjustmentSectionSummaryNoteDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Jail Adjustment Section Summary Note Already Exists With Given "
			+ "Description and Date for Specified Jail Adjustment Section Summary";
	
	private final JailAdjustmentSectionSummaryNoteDao
			jailAdjustmentSectionSummaryNoteDao;
	
	private final InstanceFactory<JailAdjustmentSectionSummaryNote> 
			jailAdjustmentSectionSummaryNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for JailAdjustmentSectionSummaryNoteDelegate
	 * @param jailAdjustmentSectionSummaryNoteDao
	 * @param jailAdjustmentSectionSummaryNoteInstanceFactory
	 * @param auditComponentRetriever
	 */
	public JailAdjustmentSectionSummaryNoteDelegate(
			final JailAdjustmentSectionSummaryNoteDao
				jailAdjustmentSectionSummaryNoteDao,
			final InstanceFactory<JailAdjustmentSectionSummaryNote> 
				jailAdjustmentSectionSummaryNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.jailAdjustmentSectionSummaryNoteDao =
				jailAdjustmentSectionSummaryNoteDao;
		this.jailAdjustmentSectionSummaryNoteInstanceFactory =
				jailAdjustmentSectionSummaryNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a JailAdjustmentSectionSummaryNote with specified parameters
	 * @param description - String
	 * @param date - Date
	 * @param jailAdjustmentSectionSummary - JailAdjustmentSectionSummary
	 * @return Newly created JailAdjustmentSectionSummaryNote
	 * @throws DuplicateEntityFoundException - when JailAdjustmentSectionSummaryNote
	 * already exists for given JailAdjustmentSectionSummary with given 
	 * Description and Date
	 */
	public JailAdjustmentSectionSummaryNote create(final String description,
			final Date date, final JailAdjustmentSectionSummary
					jailAdjustmentSectionSummary)
							throws DuplicateEntityFoundException{
		if(this.jailAdjustmentSectionSummaryNoteDao.find(description, date,
				jailAdjustmentSectionSummary) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		JailAdjustmentSectionSummaryNote jailAdjustmentSectionSummaryNote = 
				this.jailAdjustmentSectionSummaryNoteInstanceFactory
				.createInstance();
		
		jailAdjustmentSectionSummaryNote.setDescription(description);
		jailAdjustmentSectionSummaryNote.setDate(date);
		jailAdjustmentSectionSummaryNote
				.setJailAdjustmentSectionSummary(jailAdjustmentSectionSummary);
		jailAdjustmentSectionSummaryNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		jailAdjustmentSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.jailAdjustmentSectionSummaryNoteDao
				.makePersistent(jailAdjustmentSectionSummaryNote);
	}
	
	/**
	 * Updates a JailAdjustmentSectionSummaryNote with specified parameters
	 * @param jailAdjustmentSectionSummaryNote - JailAdjustmentSectionSummaryNote
	 * to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated JailAdjustmentSectionSummaryNote
	 * @throws DuplicateEntityFoundException - when JailAdjustmentSectionSummaryNote
	 * already exists for given JailAdjustmentSectionSummary with given 
	 * Description and Date
	 */
	public JailAdjustmentSectionSummaryNote update(
			final JailAdjustmentSectionSummaryNote
			jailAdjustmentSectionSummaryNote, final String description,
			final Date date)
							throws DuplicateEntityFoundException{
		if(this.jailAdjustmentSectionSummaryNoteDao.findExcluding(description,
				date, 
				jailAdjustmentSectionSummaryNote.getJailAdjustmentSectionSummary(),
				jailAdjustmentSectionSummaryNote) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		jailAdjustmentSectionSummaryNote.setDescription(description);
		jailAdjustmentSectionSummaryNote.setDate(date);
		jailAdjustmentSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.jailAdjustmentSectionSummaryNoteDao
				.makePersistent(jailAdjustmentSectionSummaryNote);
	}
	
	/**
	 * Removes a JailAdjustmentSectionSummaryNote
	 * @param jailAdjustmentSectionSummaryNote - JailAdjustmentSectionSummaryNote
	 * to remove
	 */
	public void remove(final JailAdjustmentSectionSummaryNote
			jailAdjustmentSectionSummaryNote){
		this.jailAdjustmentSectionSummaryNoteDao
				.makeTransient(jailAdjustmentSectionSummaryNote);
	}
	
	/**
	 * Finds and returns a list of all JailAdjustmentSectionSummaryNotes
	 * by specified JailAdjustmentSectionSummary
	 * @param jailAdjustmentSectionSummary - JailAdjustmentSectionSummary
	 * @return List of all JailAdjustmentSectionSummaryNotes by specified
	 * JailAdjustmentSectionSummary
	 */
	public List<JailAdjustmentSectionSummaryNote>
		findByJailAdjustmentSectionSummary(
			final JailAdjustmentSectionSummary jailAdjustmentSectionSummary){
		return this.jailAdjustmentSectionSummaryNoteDao
				.findByJailAdjustmentSectionSummary(jailAdjustmentSectionSummary);
	}
	
	
}
