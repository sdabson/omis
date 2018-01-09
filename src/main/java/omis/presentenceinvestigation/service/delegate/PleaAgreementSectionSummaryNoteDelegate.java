
package omis.presentenceinvestigation.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.PleaAgreementSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummary;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummaryNote;

/**
 * PleaAgreementSectionSummaryNoteDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 13, 2017)
 *@since OMIS 3.0
 *
 */
public class PleaAgreementSectionSummaryNoteDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"PleaAgreementSectionSummaryNote already exists with given "
			+ "PleaAgreementSectionSummary and Description";
	
	private final PleaAgreementSectionSummaryNoteDao
		pleaAgreementSectionSummaryNoteDao;
	
	private final InstanceFactory<PleaAgreementSectionSummaryNote> 
		pleaAgreementSectionSummaryNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for PleaAgreementSectionSummaryNoteDelegate
	 * @param pleaAgreementSectionSummaryNoteDao
	 * @param pleaAgreementSectionSummaryNoteInstanceFactory
	 * @param auditComponentRetriever
	 */
	public PleaAgreementSectionSummaryNoteDelegate(
			final PleaAgreementSectionSummaryNoteDao
				pleaAgreementSectionSummaryNoteDao,
			final InstanceFactory<PleaAgreementSectionSummaryNote> 
				pleaAgreementSectionSummaryNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.pleaAgreementSectionSummaryNoteDao = pleaAgreementSectionSummaryNoteDao;
		this.pleaAgreementSectionSummaryNoteInstanceFactory =
				pleaAgreementSectionSummaryNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a PleaAgreementSectionSummaryNote with the specified properties
	 * @param pleaAgreementSectionSummary - PleaAgreementSectionSummary
	 * @param description - String
	 * @param date - Date
	 * @return Newly created PleaAgreementSectionSummaryNote
	 * @throws DuplicateEntityFoundException - when a 
	 * PleaAgreementSectionSummaryNote already exists with given
	 * PleaAgreementSectionSummary and Description
	 */
	public PleaAgreementSectionSummaryNote create(
			final PleaAgreementSectionSummary pleaAgreementSectionSummary,
			final String description, final Date date)
					throws DuplicateEntityFoundException{
		if(this.pleaAgreementSectionSummaryNoteDao.find(
				pleaAgreementSectionSummary, description) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		PleaAgreementSectionSummaryNote pleaAgreementSectionSummaryNote = 
				this.pleaAgreementSectionSummaryNoteInstanceFactory.createInstance();
		
		pleaAgreementSectionSummaryNote.setPleaAgreementSectionSummary(
				pleaAgreementSectionSummary);
		pleaAgreementSectionSummaryNote.setDescription(description);
		pleaAgreementSectionSummaryNote.setDate(date);
		pleaAgreementSectionSummaryNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		pleaAgreementSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.pleaAgreementSectionSummaryNoteDao.makePersistent(
				pleaAgreementSectionSummaryNote);
	}
	
	/**
	 * Updates a PleaAgreementSectionSummaryNote with the specified properties
	 * @param pleaAgreementSectionSummaryNote - PleaAgreementSectionSummaryNote
	 * to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated PleaAgreementSectionSummaryNote
	 * @throws DuplicateEntityFoundException - when a 
	 * PleaAgreementSectionSummaryNote already exists with given
	 * PleaAgreementSectionSummary and Description
	 */
	public PleaAgreementSectionSummaryNote update(
			final PleaAgreementSectionSummaryNote pleaAgreementSectionSummaryNote,
			final String description, final Date date)
					throws DuplicateEntityFoundException{
		if(this.pleaAgreementSectionSummaryNoteDao.findExcluding(
				pleaAgreementSectionSummaryNote.getPleaAgreementSectionSummary(),
				description, pleaAgreementSectionSummaryNote) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		pleaAgreementSectionSummaryNote.setDescription(description);
		pleaAgreementSectionSummaryNote.setDate(date);
		pleaAgreementSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.pleaAgreementSectionSummaryNoteDao.makePersistent(
				pleaAgreementSectionSummaryNote);
	}
	
	/**
	 * Removes a PleaAgreementSectionSummaryNote
	 * @param pleaAgreementSectionSummaryNote - PleaAgreementSectionSummaryNote 
	 * to remove
	 */
	public void remove(
			final PleaAgreementSectionSummaryNote pleaAgreementSectionSummaryNote){
		this.pleaAgreementSectionSummaryNoteDao.makeTransient(
				pleaAgreementSectionSummaryNote);
	}
	
	/**
	 * Returns a list of all PleaAgreementSectionSummaryNotes with specified
	 * PleaAgreementSectionSummary
	 * @param pleaAgreementSectionSummary - PleaAgreementSectionSummary
	 * @return List of all PleaAgreementSectionSummaryNotes with specified
	 * PleaAgreementSectionSummary
	 */
	public List<PleaAgreementSectionSummaryNote>
		findByPleaAgreementSectionSummary(
				final PleaAgreementSectionSummary pleaAgreementSectionSummary){
		return this.pleaAgreementSectionSummaryNoteDao
				.findByPleaAgreementSectionSummary(pleaAgreementSectionSummary);
	}
	
}
