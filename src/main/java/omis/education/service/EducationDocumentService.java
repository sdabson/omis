package omis.education.service;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.education.domain.EducationAssociableDocument;
import omis.education.domain.EducationDocumentCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;

/**
 * EducationDocumentService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 13, 2017)
 *@since OMIS 3.0
 *
 */
public interface EducationDocumentService {
	
	/**
	 * Creates an EducationAssociableDocument with the specified properties
	 * @param offender - Offender
	 * @param document - Document
	 * @param category - EducationDocumentCategory
	 * @return Newly created EducationAssociableDocument
	 * @throws DuplicateEntityFoundException - when a EducationAssociableDocument
	 * already exists with given Offender and Document
	 */
	public EducationAssociableDocument create(Offender offender,
			Document document, EducationDocumentCategory category)
			throws DuplicateEntityFoundException;
	
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
			EducationAssociableDocument educationAssociableDocument,
			Document document, EducationDocumentCategory category)
			throws DuplicateEntityFoundException;
	
	/**
	 * Removes an EducationAssociableDocument
	 * @param educationAssociableDocument - EducationAssociableDocument to remove
	 */
	public void remove(
			final EducationAssociableDocument educationAssociableDocument);
	
	/** Create document.
	 * @param documentDate - date of document.
	 * @param filename - file name.
	 * @param fileExtension - file extension. 
	 * @param title - title. 
	 * @return document entity.
	 * @throws DuplicateEntityFoundException - when document with existing file
	 * name exists. */
	public Document createDocument(Date documentDate,
			String filename, String fileExtension, String title)
					throws DuplicateEntityFoundException;
	/** Updates document.
	 * @param document - document.
	 * @param title - title. 
	 * @param date - date. 
	 * @throws DuplicateEntityFoundException */
	public Document updateDocument(Document document, Date documentDate,
			String title)
					throws DuplicateEntityFoundException;

	/**
	 * Removes a specified document
	 * @param document - Document to remove
	 */
	public void removeDocument(Document document);

	/** Tag document.
	 * @param document - document.
	 * @param name - tag name. 
	 * @return document tag. 
	 * @throws DuplicateEntityFoundException - when document is already tagged
	 * with given name. */
	public DocumentTag createDocumentTag(Document document, String name)
			throws DuplicateEntityFoundException;

	/** Update tag.
	 * @param documentTag - document tag.
	 * @param name - name. 
	 * @throws DuplicateEntityFoundException - when document is already tagged
	 * with given name. */
	public DocumentTag updateDocumentTag(DocumentTag documentTag, String name)
			throws DuplicateEntityFoundException;

	/** Remove tag.
	 * @param documentTag - document tag. */
	public void removeDocumentTag(DocumentTag documentTag);
	

	/** Finds document tags by document.
	 * @param document - document.
	 * @return list of document tags. */
	public List<DocumentTag> findDocumentTagsByDocument(Document document);
	
	/**
	 * Finds and returns a list of all valid EducationDocumentCategories
	 * @return List of EducationDocumentCategories
	 * */
	public List<EducationDocumentCategory> findAllEducationDocumentCategories();
}
