package omis.violationevent.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.violationevent.dao.ViolationEventDocumentDao;
import omis.violationevent.domain.ViolationEvent;
import omis.violationevent.domain.ViolationEventDocument;

/**
 * ViolationEventDocumentDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 18, 2017)
 *@since OMIS 3.0
 *
 */
public class ViolationEventDocumentDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Violation Event Document already exists with given document and"
			+ " violation event";
	
	private final ViolationEventDocumentDao violationEventDocumentDao;
	
	private final InstanceFactory<ViolationEventDocument> 
		violationEventDocumentInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for ViolationEventDocumentDelegate
	 * @param violationEventDocumentDao
	 * @param violationEventDocumentInstanceFactory
	 * @param auditComponentRetriever
	 */
	public ViolationEventDocumentDelegate(
			final ViolationEventDocumentDao violationEventDocumentDao,
			final InstanceFactory<ViolationEventDocument> 
				violationEventDocumentInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.violationEventDocumentDao = violationEventDocumentDao;
		this.violationEventDocumentInstanceFactory =
				violationEventDocumentInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/**
	 * Creates a ViolationEventDocument with specified properties
	 * @param document - Document
	 * @param violationEvent - ViolationEvent
	 * @return Newly Created ViolationEventDocument
	 * @throws DuplicateEntityFoundException - when a ViolationEventDocument
	 * already exists with specified Document for given ViolationEvent
	 */
	public ViolationEventDocument create(final Document document,
			final ViolationEvent violationEvent)
					throws DuplicateEntityFoundException{
		if(this.violationEventDocumentDao
				.find(document, violationEvent) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		ViolationEventDocument violationEventDocument = 
				this.violationEventDocumentInstanceFactory.createInstance();
		
		violationEventDocument.setDocument(document);
		violationEventDocument.setViolationEvent(violationEvent);
		violationEventDocument.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		violationEventDocument.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.violationEventDocumentDao
				.makePersistent(violationEventDocument);
	}
	
	/**
	 * Updates a ViolationEventDocument
	 * @param violationEventDocument - ViolationEventDocument to update
	 * @param document - Document
	 * @return Updated ViolationEventDocument
	 * @throws DuplicateEntityFoundException - When ViolationEventDocument 
	 * already exists with specified Document for its ViolationEvent
	 */
	public ViolationEventDocument update(
			final ViolationEventDocument violationEventDocument,
			final Document document)
					throws DuplicateEntityFoundException{
		if(this.violationEventDocumentDao.findExcluding(violationEventDocument,
				document, violationEventDocument.getViolationEvent()) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		violationEventDocument.setDocument(document);
		violationEventDocument.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.violationEventDocumentDao
				.makePersistent(violationEventDocument);
	}
	
	/**
	 * Removes a ViolationEventDocument
	 * @param violationEventDocument - ViolationEventDocument to remove
	 */
	public void remove(final ViolationEventDocument violationEventDocument){
		this.violationEventDocumentDao.makeTransient(violationEventDocument);
	}
	
	
	/**
	 * Finds and returns a list of ViolationEventDocuments found by specified
	 * ViolationEvent
	 * @param violationEvent - ViolationEvent
	 * @return list of ViolationEventDocuments found by specified
	 * ViolationEvent
	 */
	public List<ViolationEventDocument> findByViolationEvent(
			final ViolationEvent violationEvent){
		return this.violationEventDocumentDao
				.findByViolationEvent(violationEvent);
	}
	
	
}
