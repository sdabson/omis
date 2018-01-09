
package omis.presentenceinvestigation.service;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummary;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummaryAssociableDocument;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * PleaAgreementSectionSummaryService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 13, 2017)
 *@since OMIS 3.0
 *
 */
public interface PleaAgreementSectionSummaryService {
	
	/**
	 * Creates a PleaAgreementSectionSummary with the specified properties
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param summary - String
	 * @return Newly created PleaAgreementSectionSummary
	 * @throws DuplicateEntityFoundException - when a PleaAgreementSectionSummary
	 * already exists with given PresentenceInvestigationRequest
	 */
	public PleaAgreementSectionSummary createPleaAgreementSectionSummary(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			String summary) throws DuplicateEntityFoundException;

	/**
	 * Updates an existing PleaAgreementSectionSummary with the specified
	 * properties
	 * @param pleaAgreementSectionSummary - PleaAgreementSectionSummary to update
	 * @param summary - String
	 * @return Updated PleaAgreementSectionSummary
	 * @throws DuplicateEntityFoundException - When another
	 * PleaAgreementSectionSummary
	 * already exists with specified PleaAgreementSectionSummary
	 */
	public PleaAgreementSectionSummary updatePleaAgreementSectionSummary(
			PleaAgreementSectionSummary pleaAgreementSectionSummary,
			String summary) throws DuplicateEntityFoundException;

	/**
	 * Removes a PleaAgreementSectionSummary
	 * @param pleaAgreementSectionSummary - PleaAgreementSectionSummary to remove
	 */
	public void removePleaAgreementSectionSummary(
			PleaAgreementSectionSummary pleaAgreementSectionSummary);

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
	public PleaAgreementSectionSummaryNote createPleaAgreementSectionSummaryNote(
			PleaAgreementSectionSummary pleaAgreementSectionSummary,
			String description, Date date) throws DuplicateEntityFoundException;

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
	public PleaAgreementSectionSummaryNote updatePleaAgreementSectionSummaryNote(
			PleaAgreementSectionSummaryNote pleaAgreementSectionSummaryNote,
			String description, Date date) throws DuplicateEntityFoundException;

	/**
	 * Removes a PleaAgreementSectionSummaryNote
	 * @param pleaAgreementSectionSummaryNote - PleaAgreementSectionSummaryNote 
	 * to remove
	 */
	public void removePleaAgreementSectionSummaryNote(
			PleaAgreementSectionSummaryNote pleaAgreementSectionSummaryNote);

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
	public PleaAgreementSectionSummaryAssociableDocument
		createPleaAgreementSectionSummaryAssociableDocument(
			PleaAgreementSectionSummary pleaAgreementSectionSummary,
			Document document) throws DuplicateEntityFoundException;

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
	public PleaAgreementSectionSummaryAssociableDocument
		updatePleaAgreementSectionSummaryAssociableDocument(
				PleaAgreementSectionSummaryAssociableDocument
					pleaAgreementSectionSummaryAssociableDocument,
				Document document) throws DuplicateEntityFoundException;

	/**
	 * Removes an PleaAgreementSectionSummaryAssociableDocument
	 * @param pleaAgreementSectionSummaryAssociableDocument -
	 * PleaAgreementSectionSummaryAssociableDocument to remove
	 */
	public void removePleaAgreementSectionSummaryAssociableDocument(
			PleaAgreementSectionSummaryAssociableDocument
				pleaAgreementSectionSummaryAssociableDocument);

	/**
	 * Finds and returns a PleaAgreementSectionSummary found with specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return Found PleaAgreementSectionSummary found with specified
	 * PresentenceInvestigationRequest
	 */
	public PleaAgreementSectionSummary
		findPleaAgreementSectionSummaryByPresentenceInvestigationRequest(
				PresentenceInvestigationRequest presentenceInvestigationRequest);

	/**
	 * Returns a list of all PleaAgreementSectionSummaryNotes with specified
	 * PleaAgreementSectionSummary
	 * @param pleaAgreementSectionSummary - PleaAgreementSectionSummary
	 * @return List of all PleaAgreementSectionSummaryNotes with specified
	 * PleaAgreementSectionSummary
	 */
	public List<PleaAgreementSectionSummaryNote>
		findPleaAgreementSectionSummaryNotesByPleaAgreementSectionSummary(
				PleaAgreementSectionSummary pleaAgreementSectionSummary);

	/**
	 * Returns a list of all PleaAgreementSectionSummaryAssociableDocuments
	 * found with specified PleaAgreementSectionSummary
	 * @param pleaAgreementSectionSummary - PleaAgreementSectionSummary
	 * @return List of all PleaAgreementSectionSummaryAssociableDocuments
	 * found with specified PleaAgreementSectionSummary
	 */
	public List<PleaAgreementSectionSummaryAssociableDocument>
		findPleaAgreementSectionSummaryAssociableDocumentsByPleaAgreementSectionSummary(
				PleaAgreementSectionSummary pleaAgreementSectionSummary);
	
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
	
}
