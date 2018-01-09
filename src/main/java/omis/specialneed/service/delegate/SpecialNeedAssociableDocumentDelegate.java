package omis.specialneed.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.specialneed.dao.SpecialNeedAssociableDocumentDao;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedAssociableDocument;
import omis.specialneed.domain.SpecialNeedAssociableDocumentCategory;

/**
 * Special need associable document delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 1, 2017)
 * @since OMIS 3.0
 */
public class SpecialNeedAssociableDocumentDelegate {

	/* Data access objects. */
	
	private final SpecialNeedAssociableDocumentDao 
			specialNeedAssociableDocumentDao;

	/* Instance factories. */
	
	private final InstanceFactory<SpecialNeedAssociableDocument> 
			SpecialNeedAssociableDocumentInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Instantiates an implementation of special need associable document 
	 * delegate with the specified date access object and instance factory.
	 * 
	 * @param specialNeedAssociableDocumentDao special need associable document 
	 * data access object
	 * @param specialNeedAssociableDocumentInstanceFactory special need 
	 * associable document instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public SpecialNeedAssociableDocumentDelegate(
			final SpecialNeedAssociableDocumentDao 
				specialNeedAssociableDocumentDao,
			final InstanceFactory<SpecialNeedAssociableDocument> 
				specialNeedAssociableDocumentInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.specialNeedAssociableDocumentDao = specialNeedAssociableDocumentDao;
		this.SpecialNeedAssociableDocumentInstanceFactory = 
				specialNeedAssociableDocumentInstanceFactory; 
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Returns the special need associable document for this special need.
	 *
	 *
	 * @param specialNeed special need
	 * @return list of special need notes
	 */
	public SpecialNeedAssociableDocument findAssociableDocument(
			final SpecialNeed specialNeed) {
		return this.specialNeedAssociableDocumentDao.findBySpecialNeed(
				specialNeed);
	}	
	
	/**
	 * Returns a new special need associable document.
	 *
	 * @param specialNeed special need
	 * @param document document
	 * @param category special need associable document category
	 * @return special need associable document
	 * @throws DuplicateEntityFoundException
	 */
	public SpecialNeedAssociableDocument create(final SpecialNeed specialNeed, 
			final Document document, 
			final SpecialNeedAssociableDocumentCategory category)
			throws DuplicateEntityFoundException {
		if (this.specialNeedAssociableDocumentDao.find(specialNeed) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate special need associable document found");
		}
		SpecialNeedAssociableDocument specialNeedAssociableDocument = this
				.SpecialNeedAssociableDocumentInstanceFactory.createInstance();
		
		specialNeedAssociableDocument.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		populateSpecialNeedAssociableDocument(specialNeedAssociableDocument, 
				specialNeed, document, category);
		return this.specialNeedAssociableDocumentDao.makePersistent(
				specialNeedAssociableDocument);
	}
	
	/**
	 * Returns an updated special need associable document.
	 *
	 * @param specialNeedAssociableDocument special need associable document
	 * @param specialNeed special need
	 * @param document document
	 * @param category special need associable document category
	 * @return special need associable document
	 * @throws DuplicateEntityFoundException
	 */
	public SpecialNeedAssociableDocument update(
			final SpecialNeedAssociableDocument specialNeedAssociableDocument, 
			final SpecialNeed specialNeed, final Document document, 
			final SpecialNeedAssociableDocumentCategory category)
			throws DuplicateEntityFoundException {
		if (this.specialNeedAssociableDocumentDao.findExcluding(specialNeed, 
				specialNeedAssociableDocument) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate special need note found");
		}
		populateSpecialNeedAssociableDocument(specialNeedAssociableDocument, 
				specialNeed, document, category);
		return this.specialNeedAssociableDocumentDao.makePersistent(
				specialNeedAssociableDocument);
	}

	/**
	 * Removes a special need associable document.
	 *
	 * @param specialNeedAssociableDocument special need associable document
	 */
	public void remove(
			final SpecialNeedAssociableDocument specialNeedAssociableDocument) {
		this.specialNeedAssociableDocumentDao.makeTransient(
				specialNeedAssociableDocument);
	}

	// Populates a special need note
	private void populateSpecialNeedAssociableDocument(
			final SpecialNeedAssociableDocument specialNeedAssociableDocument, 
			final SpecialNeed specialNeed, final Document document, 
			final SpecialNeedAssociableDocumentCategory category) {
		specialNeedAssociableDocument.setSpecialNeed(specialNeed);
		specialNeedAssociableDocument.setDocument(document);
		specialNeedAssociableDocument.setCategory(category);
		specialNeedAssociableDocument.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	}
}
