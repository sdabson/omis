package omis.presentenceinvestigation.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.PresentenceInvestigationRequestNoteDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequestNote;

/**
 * PresentenceInvestigationRequestNoteDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 16, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationRequestNoteDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Presentence Investigation Request Note already exists with the "
			+ "given Date and Description for the specified Presentence "
			+ "Investigation Request.";
	
	private final PresentenceInvestigationRequestNoteDao
		presentenceInvestigationRequestNoteDao;
	
	private final InstanceFactory<PresentenceInvestigationRequestNote> 
		presentenceInvestigationRequestNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for PresentenceInvestigationRequestNoteDelegate
	 * @param presentenceInvestigationRequestNoteDao
	 * @param presentenceInvestigationRequestNoteInstanceFactory
	 * @param auditComponentRetriever
	 */
	public PresentenceInvestigationRequestNoteDelegate(
			final PresentenceInvestigationRequestNoteDao presentenceInvestigationRequestNoteDao,
			final InstanceFactory<PresentenceInvestigationRequestNote> 
				presentenceInvestigationRequestNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.presentenceInvestigationRequestNoteDao =
				presentenceInvestigationRequestNoteDao;
		this.presentenceInvestigationRequestNoteInstanceFactory =
				presentenceInvestigationRequestNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a PresentenceInvestigationRequestNote with the specified properties
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param description - String
	 * @param date - Date
	 * @return Newly created PresentenceInvestigationRequestNote
	 * @throws DuplicateEntityFoundException - When a
	 * PresentenceInvestigationRequestNote already exists with the given Date
	 * and Description for the specified PresentenceInvestigationRequest
	 */
	public PresentenceInvestigationRequestNote create(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String description, final Date date)
					throws DuplicateEntityFoundException{
		if(this.presentenceInvestigationRequestNoteDao.find(
				presentenceInvestigationRequest, description, date) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		PresentenceInvestigationRequestNote presentenceInvestigationRequestNote = 
				this.presentenceInvestigationRequestNoteInstanceFactory
				.createInstance();
		
		presentenceInvestigationRequestNote.setPresentenceInvestigationRequest(
				presentenceInvestigationRequest);
		presentenceInvestigationRequestNote.setDate(date);
		presentenceInvestigationRequestNote.setDescription(description);
		presentenceInvestigationRequestNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		presentenceInvestigationRequestNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.presentenceInvestigationRequestNoteDao.makePersistent(
				presentenceInvestigationRequestNote);
	}
	
	/**
	 * Updates a PresentenceInvestigationRequestNote with the specified properties
	 * @param presentenceInvestigationRequestNote -
	 * PresentenceInvestigationRequestNote to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated PresentenceInvestigationRequestNote
	 * @throws DuplicateEntityFoundException - When a
	 * PresentenceInvestigationRequestNote already exists with the given Date
	 * and Description for the specified PresentenceInvestigationRequest
	 */
	public PresentenceInvestigationRequestNote update(
			final PresentenceInvestigationRequestNote
				presentenceInvestigationRequestNote,
			final String description, final Date date)
					throws DuplicateEntityFoundException{
		if(this.presentenceInvestigationRequestNoteDao.findExcluding(
				presentenceInvestigationRequestNote
					.getPresentenceInvestigationRequest(), description, date,
				presentenceInvestigationRequestNote) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		presentenceInvestigationRequestNote.setDate(date);
		presentenceInvestigationRequestNote.setDescription(description);
		presentenceInvestigationRequestNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.presentenceInvestigationRequestNoteDao.makePersistent(
				presentenceInvestigationRequestNote);
	}
	
	/**
	 * Removes a PresentenceInvestigationRequestNote
	 * @param presentenceInvestigationRequestNote -
	 * PresentenceInvestigationRequestNote to remove
	 */
	public void remove(final PresentenceInvestigationRequestNote
			presentenceInvestigationRequestNote){
		this.presentenceInvestigationRequestNoteDao.makeTransient(
				presentenceInvestigationRequestNote);
	}
	
	/**
	 * Returns a list of PresentenceInvestigationRequestNotes found with the
	 * specified PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return List of PresentenceInvestigationRequestNotes found with the
	 * specified PresentenceInvestigationRequest
	 */
	public List<PresentenceInvestigationRequestNote>
		findByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest){
		return this.presentenceInvestigationRequestNoteDao
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}
	
}
