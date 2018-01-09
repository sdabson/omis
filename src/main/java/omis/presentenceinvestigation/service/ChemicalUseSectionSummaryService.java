package omis.presentenceinvestigation.service;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummary;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummaryDocumentAssociation;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * ChemicalUseSectionSummaryService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 6, 2017)
 *@since OMIS 3.0
 *
 */
public interface ChemicalUseSectionSummaryService {
	
	/**
	 * Creates a ChemicalUseSectionSummary with the specified properties
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param text - String
	 * @return Newly created ChemicalUseSectionSummary
	 * @throws DuplicateEntityFoundException - when a ChemicalUseSectionSummary
	 * already exists with the specified PresentenceInvestigationRequest
	 */
	public ChemicalUseSectionSummary createChemicalUseSectionSummary(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			String text)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a ChemicalUseSectionSummary with the specified properties
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary to update
	 * @param text - String
	 * @return Updated ChemicalUseSectionSummary
	 * @throws DuplicateEntityFoundException - When a ChemicalUseSectionSummary
	 * already exists with its PresentenceInvestigationRequest (should never
	 * happen on update) 
	 */
	public ChemicalUseSectionSummary updateChemicalUseSectionSummary(
			ChemicalUseSectionSummary chemicalUseSectionSummary,
			String text)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a ChemicalUseSectionSummary
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary to remove
	 */
	public void removeChemicalUseSectionSummary(
			ChemicalUseSectionSummary chemicalUseSectionSummary);
	
	/**
	 * Creates a ChemicalUseSectionSummaryNote with specified properties
	 * @param description - String
	 * @param date - Date
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary
	 * @return Newly created ChemicalUseSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When a ChemicalUseSectionSummaryNote
	 * already exists with all of the specified properties
	 */
	public ChemicalUseSectionSummaryNote createChemicalUseSectionSummaryNote(
			String description, Date date,
			ChemicalUseSectionSummary chemicalUseSectionSummary)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a ChemicalUseSectionSummaryNote with the specified properties
	 * @param chemicalUseSectionSummaryNote - ChemicalUseSectionSummaryNote
	 * to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated ChemicalUseSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When a ChemicalUseSectionSummaryNote
	 * already exists with specified Description and Date for its
	 * ChemicalUseSectionSummary
	 */
	public ChemicalUseSectionSummaryNote updateChemicalUseSectionSummaryNote(
			ChemicalUseSectionSummaryNote chemicalUseSectionSummaryNote,
			String description, Date date)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a ChemicalUseSectionSummaryNote
	 * @param chemicalUseSectionSummaryNote - ChemicalUseSectionSummaryNote to
	 * remove
	 */
	public void removeChemicalUseSectionSummaryNote(
			ChemicalUseSectionSummaryNote chemicalUseSectionSummaryNote);
	
	/**
	 * Creates a ChemicalUseSectionSummaryDocumentAssociation with the specified
	 * properties
	 * @param document - Document
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary
	 * @return Newly created ChemicalUseSectionSummaryDocumentAssociation
	 * @throws DuplicateEntityFoundException - When a 
	 * ChemicalUseSectionSummaryDocumentAssociation already exists with specified
	 * Document and ChemicalUseSectionSummary
	 */
	public ChemicalUseSectionSummaryDocumentAssociation
		createChemicalUseSectionSummaryDocumentAssociation(Document document,
			ChemicalUseSectionSummary chemicalUseSectionSummary)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a ChemicalUseSectionSummaryDocumentAssociation with the specified
	 * properties
	 * @param chemicalUseSectionSummaryDocumentAssociation -
	 * ChemicalUseSectionSummaryDocumentAssociation to update
	 * @param document - Document
	 * @return Updated ChemicalUseSectionSummaryDocumentAssociation
	 * @throws DuplicateEntityFoundException - when a
	 * ChemicalUseSectionSummaryDocumentAssociation already exists with specified
	 * Document for its ChemicalUseSectionSummary
	 */
	public ChemicalUseSectionSummaryDocumentAssociation
		updateChemicalUseSectionSummaryDocumentAssociation(
			ChemicalUseSectionSummaryDocumentAssociation
				chemicalUseSectionSummaryDocumentAssociation,
			Document document)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a ChemicalUseSectionSummaryDocumentAssociation
	 * @param chemicalUseSectionSummaryDocumentAssociation -
	 * ChemicalUseSectionSummaryDocumentAssociation to remove
	 */
	public void removeChemicalUseSectionSummaryDocumentAssociation(
			ChemicalUseSectionSummaryDocumentAssociation
				chemicalUseSectionSummaryDocumentAssociation);
	
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
	 * Finds and returns a ChemicalUseSectionSummary by specified 
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ChemicalUseSectionSummary found by specified 
	 * PresentenceInvestigationRequest
	 */
	public ChemicalUseSectionSummary
		findChemicalUseSectionSummaryByPresentenceInvestigationRequest(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Returns a list of all ChemicalUseSectionSummaryNotes found with
	 * specified ChemicalUseSectionSummary
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary
	 * @return List of all ChemicalUseSectionSummaryNotes found with
	 * specified ChemicalUseSectionSummary
	 */
	public List<ChemicalUseSectionSummaryNote>
		findChemicalUseSectionSummaryNotesByChemicalUseSectionSummary(
			ChemicalUseSectionSummary chemicalUseSectionSummary);
	
	/**
	 * Returns a list of ChemicalUseSectionSummaryDocumentAssociations found
	 * with specified ChemicalUseSectionSummary
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary
	 * @return List of ChemicalUseSectionSummaryDocumentAssociations found
	 * with specified ChemicalUseSectionSummary
	 */
	public List<ChemicalUseSectionSummaryDocumentAssociation>
		findChemicalUseSectionSummaryDocumentAssociationsByChemicalUseSectionSummary(
				ChemicalUseSectionSummary chemicalUseSectionSummary);
	
}
