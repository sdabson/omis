package omis.presentenceinvestigation.service;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.FinancialSectionSummary;
import omis.presentenceinvestigation.domain.FinancialSectionSummaryDocumentAssociation;
import omis.presentenceinvestigation.domain.FinancialSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * FinancialSectionSummaryService
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (May 31, 2017)
 *@since OMIS 3.0
 *
 */
public interface FinancialSectionSummaryService {
	
	/**
	 * Creates a FinancialSectionSummary with the specified properties
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return Newly created FinancialSectionSummary
	 * @throws DuplicateEntityFoundException - when a financialSectionSummary
	 * already exists with specified PresentenceInvestigationRequest
	 */
	public FinancialSectionSummary createFinancialSectionSummary(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			String text)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a FinancialSectionSummary with the specified properties
	 * @param financialSectionSummary - FinancialSectionSummary to update
	 * @param text - String
	 * @return Updated FinancialSectionSummary
	 * @throws DuplicateEntityFoundException - When a FinancialSectionSummary
	 * already exists with its PresentenceInvestigationRequest (should never
	 * happen on update) 
	 */
	public FinancialSectionSummary updateFinancialSectionSummary(
			FinancialSectionSummary financialSectionSummary,
			String text)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a FinancialSectionSummary
	 * @param financialSectionSummary - FinancialSectionSummary to remove
	 */
	public void removeFinancialSectionSummary(
			FinancialSectionSummary financialSectionSummary);
	
	/**
	 * Creates a FinancialSectionSummaryNote with specified properties
	 * @param description - String
	 * @param date - Date
	 * @param financialSectionSummary - FinancialSectionSummary
	 * @return Newly created FinancialSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When a FinancialSectionSummaryNote
	 * already exists with all of the specified properties
	 */
	public FinancialSectionSummaryNote createFinancialSectionSummaryNote(
			String description, Date date,
			FinancialSectionSummary financialSectionSummary)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a FinancialSectionSummaryNote with the specified properties
	 * @param financialSectionSummaryNote - FinancialSectionSummaryNote
	 * to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated FinancialSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When a FinancialSectionSummaryNote
	 * already exists with specified Description and Date for its
	 * FinancialSectionSummary
	 */
	public FinancialSectionSummaryNote updateFinancialSectionSummaryNote(
			FinancialSectionSummaryNote financialSectionSummaryNote,
			String description, Date date)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a FinancialSectionSummaryNote
	 * @param financialSectionSummaryNote - FinancialSectionSummaryNote to
	 * remove
	 */
	public void removeFinancialSectionSummaryNote(
			FinancialSectionSummaryNote financialSectionSummaryNote);
	
	/**
	 * Creates a FinancialSectionSummaryDocumentAssociation with the specified
	 * properties
	 * @param document - Document
	 * @param financialSectionSummary - FinancialSectionSummary
	 * @return Newly created FinancialSectionSummaryDocumentAssociation
	 * @throws DuplicateEntityFoundException - When a 
	 * FinancialSectionSummaryDocumentAssociation already exists with specified
	 * Document and FinancialSectionSummary
	 */
	public FinancialSectionSummaryDocumentAssociation
		createFinancialSectionSummaryDocumentAssociation(Document document,
				FinancialSectionSummary financialSectionSummary)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a FinancialSectionSummaryDocumentAssociation with the specified
	 * properties
	 * @param FinancialSectionSummaryDocumentAssociationImpl -
	 * financialSectionSummaryDocumentAssociation to update
	 * @param document - Document
	 * @return Updated FinancialSectionSummaryDocumentAssociation
	 * @throws DuplicateEntityFoundException - when a
	 * FinancialSectionSummaryDocumentAssociation already exists with specified
	 * Document for its FinancialSectionSummary
	 */
	public FinancialSectionSummaryDocumentAssociation
		updateFinancialSectionSummaryDocumentAssociation(
				FinancialSectionSummaryDocumentAssociation
				financialSectionSummaryDocumentAssociation,
			Document document)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a FinancialSectionSummaryDocumentAssociation
	 * @param financialSectionSummaryDocumentAssociation -
	 * FinancialSectionSummaryDocumentAssociation to remove
	 */
	public void removeFinancialSectionSummaryDocumentAssociation(
			FinancialSectionSummaryDocumentAssociation
				financialSectionSummaryDocumentAssociation);
	
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
	 * Finds and returns a FinancialSectionSummary by specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return FinancialSectionSummary
	 */
	public FinancialSectionSummary
		findFinancialSectionSummaryByPresentenceInvestigationRequest(
				PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/** Finds document tags by document.
	 * @param document - document.
	 * @return list of document tags. */
	public List<DocumentTag> findDocumentTagsByDocument(Document document);
	
	/**
	 * Returns a list of FinancialSectionSummaryDocumentAssociation found
	 * with specified FinancialSectionSummary
	 * @param financialSectionSummary - FinancialSectionSummary
	 * @return List of FinancialSectionSummaryDocumentAssociation found
	 * with specified FinancialSectionSummary
	 */
	public List<FinancialSectionSummaryDocumentAssociation>
		findFinancialSectionSummaryDocumentAssociationsByFinancialSectionSummary(
				FinancialSectionSummary financialSectionSummary);
	
	/**
	 * Returns a list of all FinancialSectionSummaryNotes found with
	 * specified FinancialSectionSummary
	 * @param financialSectionSummary - FinancialSectionSummary
	 * @return List of all FinancialSectionSummaryNotes found with
	 * specified FinancialSectionSummary
	 */
	public List<FinancialSectionSummaryNote>
		findFinancialSectionSummaryNotesByFinancialSectionSummary(
			FinancialSectionSummary financialSectionSummary);

}