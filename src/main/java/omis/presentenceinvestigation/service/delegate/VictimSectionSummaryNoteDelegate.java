package omis.presentenceinvestigation.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.VictimSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.VictimSectionSummary;
import omis.presentenceinvestigation.domain.VictimSectionSummaryNote;

/**
 * VictimSectionSummaryNoteDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 20, 2017)
 *@since OMIS 3.0
 *
 */
public class VictimSectionSummaryNoteDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"VictimSectionSummaryNote already exists with given Date and "
			+ "Description for the specified VictimSectionSummary.";
	
	private final VictimSectionSummaryNoteDao victimSectionSummaryNoteDao;
	
	private final InstanceFactory<VictimSectionSummaryNote> 
		victimSectionSummaryNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for VictimSectionSummaryNoteDelegate
	 * @param victimSectionSummaryNoteDao
	 * @param victimSectionSummaryNoteInstanceFactory
	 * @param auditComponentRetriever
	 */
	public VictimSectionSummaryNoteDelegate(
			final VictimSectionSummaryNoteDao victimSectionSummaryNoteDao,
			final InstanceFactory<VictimSectionSummaryNote> 
				victimSectionSummaryNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.victimSectionSummaryNoteDao = victimSectionSummaryNoteDao;
		this.victimSectionSummaryNoteInstanceFactory =
				victimSectionSummaryNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a VictimSectionSummaryNote with the specified properties
	 * @param victimSectionSummary - VictimSectionSummary
	 * @param description - String
	 * @param date - Date
	 * @return Newly created VictimSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When a VictimSectionSummaryNote
	 * already exists with the given Date and Description for the specified
	 * VictimSectionSummary
	 */
	public VictimSectionSummaryNote create(
			final VictimSectionSummary victimSectionSummary,
			final String description, final Date date)
					throws DuplicateEntityFoundException{
		if(this.victimSectionSummaryNoteDao.find(
				victimSectionSummary, description, date) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		VictimSectionSummaryNote victimSectionSummaryNote = 
				this.victimSectionSummaryNoteInstanceFactory.createInstance();
		
		victimSectionSummaryNote.setDescription(description);
		victimSectionSummaryNote.setDate(date);
		victimSectionSummaryNote.setVictimSectionSummary(victimSectionSummary);
		victimSectionSummaryNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		victimSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.victimSectionSummaryNoteDao.makePersistent(
				victimSectionSummaryNote);
	}
	
	/**
	 * Updates a VictimSectionSummaryNote with the specified properties
	 * @param victimSectionSummaryNote - VictimSectionSummaryNote to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated VictimSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When another
	 * VictimSectionSummaryNote already exists with the specified Date and
	 * Description for the specified VictimSectionSummaryNote's 
	 * VictimSectionSummary
	 */
	public VictimSectionSummaryNote update(
			final VictimSectionSummaryNote victimSectionSummaryNote,
			final String description, final Date date)
					throws DuplicateEntityFoundException{
		if(this.victimSectionSummaryNoteDao.findExcluding(
				victimSectionSummaryNote.getVictimSectionSummary(),
				description, date, victimSectionSummaryNote) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		victimSectionSummaryNote.setDescription(description);
		victimSectionSummaryNote.setDate(date);
		victimSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.victimSectionSummaryNoteDao.makePersistent(
				victimSectionSummaryNote);
	}
	
	/**
	 * Removes a VictimSectionSummaryNote
	 * @param victimSectionSummaryNote - VictimSectionSummaryNote to remove
	 */
	public void remove(final VictimSectionSummaryNote victimSectionSummaryNote){
		this.victimSectionSummaryNoteDao.makeTransient(victimSectionSummaryNote);
	}
	
	/**
	 * Returns a List of VictimSectionSummaryNotes found by specified
	 * VictimSectionSummary
	 * @param victimSectionSummary - VictimSectionSummary
	 * @return List of VictimSectionSummaryNotes found by specified
	 * VictimSectionSummary
	 */
	public List<VictimSectionSummaryNote> findByVictimSectionSummary(
			final VictimSectionSummary victimSectionSummary){
		return this.victimSectionSummaryNoteDao.findByVictimSectionSummary(
				victimSectionSummary);
	}
	
}
