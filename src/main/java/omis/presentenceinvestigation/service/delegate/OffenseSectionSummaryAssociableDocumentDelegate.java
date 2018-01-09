package omis.presentenceinvestigation.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.OffenseSectionSummaryAssociableDocumentDao;
import omis.presentenceinvestigation.domain.OffenseSectionSummary;
import omis.presentenceinvestigation.domain.OffenseSectionSummaryAssociableDocument;

/**
 * OffenseSectionSummaryAssociableDocumentDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 31, 2017)
 *@since OMIS 3.0
 *
 */
public class OffenseSectionSummaryAssociableDocumentDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Offense Section Summary Associable Document already exists with "
			+ "given Document for specified Offense Section Summary.";
	
	private final OffenseSectionSummaryAssociableDocumentDao
		offenseSectionSummaryAssociableDocumentDao;
	
	private final InstanceFactory<OffenseSectionSummaryAssociableDocument> 
		offenseSectionSummaryAssociableDocumentInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for OffenseSectionSummaryAssociableDocumentDelegate
	 * @param offenseSectionSummaryAssociableDocumentDao
	 * @param offenseSectionSummaryAssociableDocumentInstanceFactory
	 * @param auditComponentRetriever
	 */
	public OffenseSectionSummaryAssociableDocumentDelegate(
			final OffenseSectionSummaryAssociableDocumentDao
				offenseSectionSummaryAssociableDocumentDao,
			final InstanceFactory<OffenseSectionSummaryAssociableDocument> 
				offenseSectionSummaryAssociableDocumentInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.offenseSectionSummaryAssociableDocumentDao =
				offenseSectionSummaryAssociableDocumentDao;
		this.offenseSectionSummaryAssociableDocumentInstanceFactory =
				offenseSectionSummaryAssociableDocumentInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new OffenseSectionSummaryAssociableDocument with specified
	 * properties
	 * @param offenseSectionSummary - OffenseSectionSummary
	 * @param document - Document
	 * @return Newly created OffenseSectionSummaryAssociableDocument
	 * @throws DuplicateEntityFoundException - when a 
	 * OffenseSectionSummaryAssociableDocument already exists with given
	 * OffenseSectionSummary and Document
	 */
	public OffenseSectionSummaryAssociableDocument create(
			final OffenseSectionSummary offenseSectionSummary,
			final Document document)
					throws DuplicateEntityFoundException{
		if(this.offenseSectionSummaryAssociableDocumentDao.find(
				offenseSectionSummary, document) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		OffenseSectionSummaryAssociableDocument
				offenseSectionSummaryAssociableDocument = 
					this.offenseSectionSummaryAssociableDocumentInstanceFactory
					.createInstance();
		
		offenseSectionSummaryAssociableDocument.setDocument(document);
		offenseSectionSummaryAssociableDocument.setOffenseSectionSummary(
				offenseSectionSummary);
		offenseSectionSummaryAssociableDocument.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		offenseSectionSummaryAssociableDocument.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.offenseSectionSummaryAssociableDocumentDao.makePersistent(
				offenseSectionSummaryAssociableDocument);
	}
	
	/**
	 * Updates an OffenseSectionSummaryAssociableDocument with specified
	 * parameters
	 * @param offenseSectionSummaryAssociableDocument -
	 * OffenseSectionSummaryAssociableDocument to update
	 * @param document - Document
	 * @return Updated OffenseSectionSummaryAssociableDocument
	 * @throws DuplicateEntityFoundException - when a 
	 * OffenseSectionSummaryAssociableDocument already exists with given
	 * OffenseSectionSummary and Document
	 */
	public OffenseSectionSummaryAssociableDocument update(
			final OffenseSectionSummaryAssociableDocument
				offenseSectionSummaryAssociableDocument,
			final Document document)
			throws DuplicateEntityFoundException{
		if(this.offenseSectionSummaryAssociableDocumentDao.findExcluding(
				offenseSectionSummaryAssociableDocument.getOffenseSectionSummary(),
				document, offenseSectionSummaryAssociableDocument) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		offenseSectionSummaryAssociableDocument.setDocument(document);
		offenseSectionSummaryAssociableDocument.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.offenseSectionSummaryAssociableDocumentDao.makePersistent(
				offenseSectionSummaryAssociableDocument);
	}
	
	/**
	 * Removes the specified OffenseSectionSummaryAssociableDocument 
	 * @param offenseSectionSummaryAssociableDocument -
	 * OffenseSectionSummaryAssociableDocument to remove
	 */
	public void remove(final OffenseSectionSummaryAssociableDocument
			offenseSectionSummaryAssociableDocument){
		this.offenseSectionSummaryAssociableDocumentDao.makeTransient(
				offenseSectionSummaryAssociableDocument);
	}
	
	/**
	 * Returns a list of OffenseSectionSummaryAssociableDocuments found by
	 * specified OffenseSectionSummary
	 * @param offenseSectionSummary - OffenseSectionSummary
	 * @return List of OffenseSectionSummaryAssociableDocuments found by
	 * specified OffenseSectionSummary
	 */
	public List<OffenseSectionSummaryAssociableDocument>
			findByOffenseSectionSummary(
					final OffenseSectionSummary offenseSectionSummary){
		return this.offenseSectionSummaryAssociableDocumentDao
				.findByOffenseSectionSummary(offenseSectionSummary);
	}
	
}
