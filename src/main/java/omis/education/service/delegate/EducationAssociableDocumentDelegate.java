package omis.education.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.education.dao.EducationAssociableDocumentDao;
import omis.education.domain.EducationAssociableDocument;
import omis.education.domain.EducationDocumentCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * EducationAssociableDocumentDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 13, 2017)
 *@since OMIS 3.0
 *
 */
public class EducationAssociableDocumentDelegate {
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Education Associable Document already exists"
			+ " with given Offender and Document";
	
	private final EducationAssociableDocumentDao educationAssociableDocumentDao;
	
	private final InstanceFactory<EducationAssociableDocument> 
		educationAssociableDocumentInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for EducationAssociableDocumentDelegate
	 * @param educationAssociableDocumentDao
	 * @param educationAssociableDocumentInstanceFactory
	 * @param auditComponentRetriever
	 */
	public EducationAssociableDocumentDelegate(
			final EducationAssociableDocumentDao educationAssociableDocumentDao,
			final InstanceFactory<EducationAssociableDocument> 
				educationAssociableDocumentInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.educationAssociableDocumentDao = educationAssociableDocumentDao;
		this.educationAssociableDocumentInstanceFactory =
				educationAssociableDocumentInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an EducationAssociableDocument with the specified properties
	 * @param offender - Offender
	 * @param document - Document
	 * @param category - EducationDocumentCategory
	 * @return Newly created EducationAssociableDocument
	 * @throws DuplicateEntityFoundException - when a EducationAssociableDocument
	 * already exists with given Offender and Document
	 */
	public EducationAssociableDocument create(final Offender offender,
			final Document document, final EducationDocumentCategory category)
			throws DuplicateEntityFoundException{
		if(this.educationAssociableDocumentDao.find(offender, document) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		EducationAssociableDocument educationAssociableDocument = 
				this.educationAssociableDocumentInstanceFactory.createInstance();
		
		educationAssociableDocument.setOffender(offender);
		educationAssociableDocument.setDocument(document);
		educationAssociableDocument.setCategory(category);
		educationAssociableDocument.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		educationAssociableDocument.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.educationAssociableDocumentDao.makePersistent(
				educationAssociableDocument);
	}
	
	/**
	 * Updates an EducationAssociableDocument with the specified properties
	 * @param educationAssociableDocument - EducationAssociableDocument to update
	 * @param document - Document
	 * @param category - EducationDocumentCategory
	 * @return Updated EducationAssociableDocument
	 * @throws DuplicateEntityFoundException - when a EducationAssociableDocument
	 * already exists with given Offender and Document
	 */
	public EducationAssociableDocument update(
			final EducationAssociableDocument educationAssociableDocument,
			final Document document, final EducationDocumentCategory category)
			throws DuplicateEntityFoundException{
		if(this.educationAssociableDocumentDao.findExcluding(
				educationAssociableDocument.getOffender(), document,
				educationAssociableDocument) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		educationAssociableDocument.setDocument(document);
		educationAssociableDocument.setCategory(category);
		educationAssociableDocument.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.educationAssociableDocumentDao.makePersistent(
				educationAssociableDocument);
	}
	
	/**
	 * Removes an EducationAssociableDocument
	 * @param educationAssociableDocument - EducationAssociableDocument to remove
	 */
	public void remove(
			final EducationAssociableDocument educationAssociableDocument){
		this.educationAssociableDocumentDao.makeTransient(
				educationAssociableDocument);
	}
	
	
}
