
package omis.presentenceinvestigation.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.PleaAgreementSectionSummaryAssociableDocumentDao;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummary;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummaryAssociableDocument;

/**
 * PleaAgreementSectionSummaryAssociableDocumentDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 13, 2017)
 *@since OMIS 3.0
 *
 */
public class PleaAgreementSectionSummaryAssociableDocumentDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"PleaAgreementSectionSummaryAssociableDocument already exists with "
			+ "given PleaAgreementSectionSummary and Document";
	
	private final PleaAgreementSectionSummaryAssociableDocumentDao
		pleaAgreementSectionSummaryAssociableDocumentDao;
	
	private final InstanceFactory<PleaAgreementSectionSummaryAssociableDocument> 
		pleaAgreementSectionSummaryAssociableDocumentInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for PleaAgreementSectionSummaryAssociableDocumentDelegate
	 * @param pleaAgreementSectionSummaryAssociableDocumentDao
	 * @param pleaAgreementSectionSummaryAssociableDocumentInstanceFactory
	 * @param auditComponentRetriever
	 */
	public PleaAgreementSectionSummaryAssociableDocumentDelegate(
			final PleaAgreementSectionSummaryAssociableDocumentDao
				pleaAgreementSectionSummaryAssociableDocumentDao,
			final InstanceFactory<PleaAgreementSectionSummaryAssociableDocument> 
				pleaAgreementSectionSummaryAssociableDocumentInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.pleaAgreementSectionSummaryAssociableDocumentDao =
				pleaAgreementSectionSummaryAssociableDocumentDao;
		this.pleaAgreementSectionSummaryAssociableDocumentInstanceFactory =
				pleaAgreementSectionSummaryAssociableDocumentInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a PleaAgreementSectionSummaryAssociableDocument with the
	 * specified properties
	 * @param pleaAgreementSectionSummary - PleaAgreementSectionSummary
	 * @param document - Document
	 * @return Newly created PleaAgreementSectionSummaryAssociableDocument
	 * @throws DuplicateEntityFoundException - When a 
	 * PleaAgreementSectionSummaryAssociableDocument already exists with
	 * provided PleaAgreementSectionSummary and Document
	 */
	public PleaAgreementSectionSummaryAssociableDocument create(
			final PleaAgreementSectionSummary pleaAgreementSectionSummary,
			final Document document)
					throws DuplicateEntityFoundException{
		if(this.pleaAgreementSectionSummaryAssociableDocumentDao.find(
				pleaAgreementSectionSummary, document) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		PleaAgreementSectionSummaryAssociableDocument
			pleaAgreementSectionSummaryAssociableDocument = 
				this.pleaAgreementSectionSummaryAssociableDocumentInstanceFactory
				.createInstance();
		
		pleaAgreementSectionSummaryAssociableDocument
			.setPleaAgreementSectionSummary(pleaAgreementSectionSummary);
		pleaAgreementSectionSummaryAssociableDocument.setDocument(document);
		pleaAgreementSectionSummaryAssociableDocument.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		pleaAgreementSectionSummaryAssociableDocument.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.pleaAgreementSectionSummaryAssociableDocumentDao
				.makePersistent(pleaAgreementSectionSummaryAssociableDocument);
	}
	
	/**
	 * Updates a PleaAgreementSectionSummaryAssociableDocument with the
	 * specified properties
	 * @param pleaAgreementSectionSummaryAssociableDocument -
	 * PleaAgreementSectionSummaryAssociableDocument to update
	 * @param document - Document
	 * @return Updated PleaAgreementSectionSummaryAssociableDocument
	 * @throws DuplicateEntityFoundException - When a 
	 * PleaAgreementSectionSummaryAssociableDocument already exists with
	 * provided PleaAgreementSectionSummary and Document
	 */
	public PleaAgreementSectionSummaryAssociableDocument update(
			final PleaAgreementSectionSummaryAssociableDocument
				pleaAgreementSectionSummaryAssociableDocument,
			final Document document)
			throws DuplicateEntityFoundException{
		if(this.pleaAgreementSectionSummaryAssociableDocumentDao.findExcluding(
				pleaAgreementSectionSummaryAssociableDocument
					.getPleaAgreementSectionSummary(), document,
				pleaAgreementSectionSummaryAssociableDocument) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		pleaAgreementSectionSummaryAssociableDocument.setDocument(document);
		pleaAgreementSectionSummaryAssociableDocument.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.pleaAgreementSectionSummaryAssociableDocumentDao
				.makePersistent(pleaAgreementSectionSummaryAssociableDocument);
	}
	
	/**
	 * Removes an PleaAgreementSectionSummaryAssociableDocument
	 * @param pleaAgreementSectionSummaryAssociableDocument -
	 * PleaAgreementSectionSummaryAssociableDocument to remove
	 */
	public void remove(final PleaAgreementSectionSummaryAssociableDocument
			pleaAgreementSectionSummaryAssociableDocument){
		this.pleaAgreementSectionSummaryAssociableDocumentDao.makeTransient(
				pleaAgreementSectionSummaryAssociableDocument);
	}
	
	/**
	 * Returns a list of all PleaAgreementSectionSummaryAssociableDocuments
	 * found with specified PleaAgreementSectionSummary
	 * @param pleaAgreementSectionSummary - PleaAgreementSectionSummary
	 * @return List of all PleaAgreementSectionSummaryAssociableDocuments
	 * found with specified PleaAgreementSectionSummary
	 */
	public List<PleaAgreementSectionSummaryAssociableDocument>
		findByPleaAgreementSectionSummary(
			final PleaAgreementSectionSummary pleaAgreementSectionSummary){
		return this.pleaAgreementSectionSummaryAssociableDocumentDao
				.findByPleaAgreementSectionSummary(pleaAgreementSectionSummary);
	}
	
}
