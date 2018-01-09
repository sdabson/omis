package omis.condition.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.condition.dao.AgreementAssociableDocumentDao;
import omis.condition.domain.Agreement;
import omis.condition.domain.AgreementAssociableDocument;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Agreement Associable Document Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Nov 28, 2017)
 *@since OMIS 3.0
 *
 */
public class AgreementAssociableDocumentDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Agreement Associable Document already exists with the given " 
					+ "Agreement and Document.";
	
	private final AgreementAssociableDocumentDao agreementAssociableDocumentDao;
	
	private final InstanceFactory<AgreementAssociableDocument> 
		agreementAssociableDocumentInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for AgreementAssociableDocumentDelegate.
	 * @param agreementAssociableDocumentDao - Agreement Associable Document Dao
	 * @param agreementAssociableDocumentInstanceFactory - Agreement Associable
	 * Document Instance Factory
	 * @param auditComponentRetriever - Audit Component Retriever
	 */
	public AgreementAssociableDocumentDelegate(
			final AgreementAssociableDocumentDao agreementAssociableDocumentDao,
			final InstanceFactory<AgreementAssociableDocument> 
				agreementAssociableDocumentInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.agreementAssociableDocumentDao = agreementAssociableDocumentDao;
		this.agreementAssociableDocumentInstanceFactory =
				agreementAssociableDocumentInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an Agreement Associable Document with the given properties.
	 * 
	 * @param agreement - Agreement
	 * @param document - Document
	 * @return Newly created Agreement
	 * @throws DuplicateEntityFoundException - When an Agreement Associable
	 * Document already exists with the given properties
	 */
	public AgreementAssociableDocument create(
			final Agreement agreement, final Document document)
					throws DuplicateEntityFoundException {
		if (this.agreementAssociableDocumentDao.find(agreement, document)
				!= null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		AgreementAssociableDocument agreementAssociableDocument = 
				this.agreementAssociableDocumentInstanceFactory
				.createInstance();
		
		agreementAssociableDocument.setAgreement(agreement);
		agreementAssociableDocument.setDocument(document);
		agreementAssociableDocument.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		agreementAssociableDocument.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.agreementAssociableDocumentDao.makePersistent(
				agreementAssociableDocument);
	}
	
	/**
	 * Updates the specified AgreementAssociableDocument with the specified
	 * properties.
	 * 
	 * @param agreementAssociableDocument - AgreementAssociableDocument to
	 * update
	 * @param agreement - Agreement
	 * @param document - Document
	 * @return Updated AgreementAssociableDocument
	 * @throws DuplicateEntityFoundException - When another
	 * AgreementAssociableDocument already exists with the specified properties
	 */
	public AgreementAssociableDocument update(
			final AgreementAssociableDocument agreementAssociableDocument,
			final Agreement agreement, final Document document)
					throws DuplicateEntityFoundException {
		if (this.agreementAssociableDocumentDao.findExcluding(
				agreement, document, agreementAssociableDocument) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		agreementAssociableDocument.setAgreement(agreement);
		agreementAssociableDocument.setDocument(document);
		agreementAssociableDocument.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.agreementAssociableDocumentDao.makePersistent(
				agreementAssociableDocument);
	}
	
	/**
	 * Removes the specified Agreement Associable Document.
	 * 
	 * @param agreementAssociableDocument - AgreementAssociableDocument to
	 * remove
	 */
	public void remove(
			final AgreementAssociableDocument agreementAssociableDocument) {
		this.agreementAssociableDocumentDao.makeTransient(
				agreementAssociableDocument);
	}
	
	/**
	 * Returns a list of Agreement Associable Documents by the specified 
	 * Agreement.
	 * 
	 * @param agreement - Agreement
	 * @return List of Agreement Associable Documents by the specified 
	 * Agreement
	 */
	public List<AgreementAssociableDocument> findByAgreement(
			final Agreement agreement) {
		return this.agreementAssociableDocumentDao.findByAgreement(agreement);
	}
}
