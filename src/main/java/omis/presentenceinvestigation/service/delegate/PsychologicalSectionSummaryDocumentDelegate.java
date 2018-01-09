package omis.presentenceinvestigation.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.PsychologicalSectionSummaryDocumentDao;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummary;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummaryDocument;

/**
 * PsychologicalSectionSummaryDocumentDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 2, 2017)
 *@since OMIS 3.0
 *
 */
public class PsychologicalSectionSummaryDocumentDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Psychological Section Summary Document already exists with given"
			+ " document and Psychological Section Summary";
	
	private final PsychologicalSectionSummaryDocumentDao
			psychologicalSectionSummaryDocumentDao;
	
	private final InstanceFactory<PsychologicalSectionSummaryDocument> 
		psychologicalSectionSummaryDocumentInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for PsychologicalSectionSummaryDocumentDelegate
	 * @param psychologicalSectionSummaryDocumentDao
	 * @param psychologicalSectionSummaryDocumentInstanceFactory
	 * @param auditComponentRetriever
	 */
	public PsychologicalSectionSummaryDocumentDelegate(
			final PsychologicalSectionSummaryDocumentDao
				psychologicalSectionSummaryDocumentDao,
			final InstanceFactory<PsychologicalSectionSummaryDocument> 
				psychologicalSectionSummaryDocumentInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.psychologicalSectionSummaryDocumentDao =
				psychologicalSectionSummaryDocumentDao;
		this.psychologicalSectionSummaryDocumentInstanceFactory =
				psychologicalSectionSummaryDocumentInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a PsychologicalSectionSummaryDocument with specified parameters
	 * @param document - Document
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary
	 * @return Newly created PsychologicalSectionSummaryDocument
	 * @throws DuplicateEntityFoundException - when a
	 * PsychologicalSectionSummaryDocument already exists with specified
	 * Document and PsychologicalSectionSummary
	 */
	public PsychologicalSectionSummaryDocument create(final Document document,
			final PsychologicalSectionSummary psychologicalSectionSummary)
			throws DuplicateEntityFoundException{
		if(this.psychologicalSectionSummaryDocumentDao.find(document,
				psychologicalSectionSummary) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		PsychologicalSectionSummaryDocument psychologicalSectionSummaryDocument = 
				this.psychologicalSectionSummaryDocumentInstanceFactory
					.createInstance();
		
		psychologicalSectionSummaryDocument.setDocument(document);
		psychologicalSectionSummaryDocument
			.setPsychologicalSectionSummary(psychologicalSectionSummary);
		psychologicalSectionSummaryDocument.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		psychologicalSectionSummaryDocument.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.psychologicalSectionSummaryDocumentDao
					.makePersistent(psychologicalSectionSummaryDocument);
	}
	
	/**
	 * Updates a specified PsychologicalSectionSummaryDocument with given
	 * parameters
	 * @param psychologicalSectionSummaryDocument -
	 * PsychologicalSectionSummaryDocument to update
	 * @param document - Document
	 * @return Updated PsychologicalSectionSummaryDocument
	 * @throws DuplicateEntityFoundException - when another
	 * PsychologicalSectionSummaryDocument already exists with specified
	 * Document and PsychologicalSectionSummary
	 */
	public PsychologicalSectionSummaryDocument update(
			final PsychologicalSectionSummaryDocument
					psychologicalSectionSummaryDocument,
			final Document document)
			throws DuplicateEntityFoundException{
		if(this.psychologicalSectionSummaryDocumentDao.findExcluding(document,
				psychologicalSectionSummaryDocument
						.getPsychologicalSectionSummary(),
				psychologicalSectionSummaryDocument) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		psychologicalSectionSummaryDocument.setDocument(document);
		psychologicalSectionSummaryDocument.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.psychologicalSectionSummaryDocumentDao
				.makePersistent(psychologicalSectionSummaryDocument);
	}
	
	/**
	 * Removes a PsychologicalSectionSummaryDocument
	 * @param psychologicalSectionSummaryDocument -
	 * PsychologicalSectionSummaryDocument to remove
	 */
	public void remove(final PsychologicalSectionSummaryDocument
			psychologicalSectionSummaryDocument){
		this.psychologicalSectionSummaryDocumentDao
			.makeTransient(psychologicalSectionSummaryDocument);
	}
	
	/**
	 * Returns a list of all PsychologicalSectionSummaryDocuments by
	 * specified PsychologicalSectionSummary
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary
	 * @return List of PsychologicalSectionSummaryDocuments
	 */
	public List<PsychologicalSectionSummaryDocument>
			findByPsychologicalSectionSummary(
					final PsychologicalSectionSummary psychologicalSectionSummary){
		return this.psychologicalSectionSummaryDocumentDao
				.findByPsychologicalSectionSummary(psychologicalSectionSummary);
	}
	
	
	
	
}
