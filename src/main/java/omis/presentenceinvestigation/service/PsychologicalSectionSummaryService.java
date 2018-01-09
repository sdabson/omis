package omis.presentenceinvestigation.service;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummary;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummaryDocument;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummaryNote;

/**
 * PsychologicalSectionSummaryService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 2, 2017)
 *@since OMIS 3.0
 *
 */
public interface PsychologicalSectionSummaryService {
	
	/**
	 * Creates a PsychologicalSectionSummary with the specified parameters
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return Newly created PsychologicalSectionSummary
	 * @throws DuplicateEntityFoundException - when a PsychologicalSectionSummary
	 * already exists for the specified PresentenceInvestigationRequest
	 */
	public PsychologicalSectionSummary createPsychologicalSectionSummary(
			PresentenceInvestigationRequest presentenceInvestigationRequest)
					throws DuplicateEntityFoundException;

	/**
	 * Creates a PsychologicalSectionSummaryNote with specified parameters
	 * @param description - String
	 * @param date - Date
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary
	 * @return Newly Created PsychologicalSectionSummaryNote
	 * @throws DuplicateEntityFoundException - when a 
	 * PsychologicalSectionSummaryNote already exists with given description,
	 * date, and PsychologicalSectionSummary
	 */
	public PsychologicalSectionSummaryNote createPsychologicalSectionSummaryNote(
			String description, Date date,
			PsychologicalSectionSummary psychologicalSectionSummary)
					throws DuplicateEntityFoundException;

	/**
	 * Updates a PsychologicalSectionSummaryNote with specified parameters
	 * @param psychologicalSectionSummaryNote - PsychologicalSectionSummaryNote
	 * to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated PsychologicalSectionSummaryNote
	 * @throws DuplicateEntityFoundException - when another
	 * PsychologicalSectionSummaryNote already exists with given description,
	 * date, and PsychologicalSectionSummary
	 */
	public PsychologicalSectionSummaryNote updatePsychologicalSectionSummaryNote(
			PsychologicalSectionSummaryNote psychologicalSectionSummaryNote,
			String description, Date date)
					throws DuplicateEntityFoundException;

	/**
	 * Removes specified PsychologicalSectionSummaryNote
	 * @param psychologicalSectionSummaryNote - PsychologicalSectionSummaryNote 
	 * to remove
	 */
	public void removePsychologicalSectionSummaryNote(
			PsychologicalSectionSummaryNote psychologicalSectionSummaryNote);

	/**
	 * Creates a PsychologicalSectionSummaryDocument with specified parameters
	 * @param document - Document
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary
	 * @return Newly created PsychologicalSectionSummaryDocument
	 * @throws DuplicateEntityFoundException - when a
	 * PsychologicalSectionSummaryDocument already exists with specified
	 * Document and PsychologicalSectionSummary
	 */
	public PsychologicalSectionSummaryDocument
			createPsychologicalSectionSummaryDocument(Document document,
					PsychologicalSectionSummary psychologicalSectionSummary)
							throws DuplicateEntityFoundException;

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
	public PsychologicalSectionSummaryDocument
			updatePsychologicalSectionSummaryDocument(
					PsychologicalSectionSummaryDocument
						psychologicalSectionSummaryDocument, Document document)
								throws DuplicateEntityFoundException;

	/**
	 * Removes a PsychologicalSectionSummaryDocument
	 * @param psychologicalSectionSummaryDocument -
	 * PsychologicalSectionSummaryDocument to remove
	 */
	public void removePsychologicalSectionSummaryDocument(
			PsychologicalSectionSummaryDocument
					psychologicalSectionSummaryDocument);

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

	/**
	 * Returns a PsychologicalSectionSummary found by specified
	 * PresentenceInvestigationRequest 
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return PsychologicalSectionSummary
	 */
	public PsychologicalSectionSummary
			findPsychologicalSectionSummaryByPresentenceInvestigation(
					PresentenceInvestigationRequest
							presentenceInvestigationRequest);

	/**
	 * Returns a list of PsychologicalSectionSummaryNotes found by specified
	 * PsychologicalSectionSummary
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary
	 * @return List of PsychologicalSectionSummaryNotes
	 */
	public List<PsychologicalSectionSummaryNote>
			findPsychologicalSectionSummaryNotesByPsychologicalSectionSummary(
					PsychologicalSectionSummary psychologicalSectionSummary);

	/**
	 * Returns a list of all PsychologicalSectionSummaryDocuments by
	 * specified PsychologicalSectionSummary
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary
	 * @return List of PsychologicalSectionSummaryDocuments
	 */
	public List<PsychologicalSectionSummaryDocument>
			findPsychologicalSectionSummaryDocumentsByPsychologicalSectionSummary(
					PsychologicalSectionSummary psychologicalSectionSummary);

	/** Finds document tags by document.
	 * @param document - document.
	 * @return list of document tags. */
	public List<DocumentTag> findDocumentTagsByDocument(Document document);
	
}
