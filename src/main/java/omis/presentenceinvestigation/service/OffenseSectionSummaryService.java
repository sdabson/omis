package omis.presentenceinvestigation.service;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.CircumstanceOfOffense;
import omis.presentenceinvestigation.domain.OffenseSectionSummary;
import omis.presentenceinvestigation.domain.OffenseSectionSummaryAssociableDocument;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * OffenseSectionSummaryService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 31, 2017)
 *@since OMIS 3.0
 *
 */
public interface OffenseSectionSummaryService {
	
	/**
	 * Creates an OffenseSectionSummary with specified parameters
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param text - String
	 * @return Newly Created OffenseSectionSummary
	 * @throws DuplicateEntityFoundException - When a OffenseSectionSummary
	 * already exists with given PresentenceInvestigationRequest
	 */
	public OffenseSectionSummary createOffenseSectionSummary(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			String text) throws DuplicateEntityFoundException;
	
	/**
	 * Updates an OffenseSectionSummary with specified properties
	 * @param offenseSectionSummary - OffenseSectionSummary to update
	 * @param text - String
	 * @return Updated OffenseSectionSummary
	 * @throws DuplicateEntityFoundException - When a OffenseSectionSummary
	 * already exists with given PresentenceInvestigationRequest
	 */
	public OffenseSectionSummary updateOffenseSectionSummary(
			OffenseSectionSummary offenseSectionSummary, String text)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes specified OffenseSectionSummary
	 * @param offenseSectionSummary - OffenseSectionSummary to remove
	 */
	public void removeOffenseSectionSummary(
			OffenseSectionSummary offenseSectionSummary);
	
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
	public OffenseSectionSummaryAssociableDocument
		createOffenseSectionSummaryAssociableDocument(
				OffenseSectionSummary offenseSectionSummary, Document document)
						throws DuplicateEntityFoundException;
	
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
	public OffenseSectionSummaryAssociableDocument
		updateOffenseSectionSummaryAssociableDocument(
				OffenseSectionSummaryAssociableDocument
					offenseSectionSummaryAssociableDocument, Document document)
							throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified OffenseSectionSummaryAssociableDocument 
	 * @param offenseSectionSummaryAssociableDocument -
	 * OffenseSectionSummaryAssociableDocument to remove
	 */
	public void removeOffenseSectionSummaryAssociableDocument(
			OffenseSectionSummaryAssociableDocument
				offenseSectionSummaryAssociableDocument);
	
	/**
	 * Creates a CircumstanceOfOffense with specified properties
	 * @param offenseSectionSummary - OffenseSectionSummary
	 * @param document - Document
	 * @param defendantsStatementChargeReason - String
	 * @param defendantsStatementInvolvementReason - String
	 * @param defendantsStatementCourtRecommendation - String
	 * @return Newly created CircumstanceOfOffense
	 * @throws DuplicateEntityFoundException - when a CircumstanceOfOffense
	 * already exists with given OffenseSectionSummary
	 */
	public CircumstanceOfOffense createCircumstanceOfOffense(
			OffenseSectionSummary offenseSectionSummary, Document document,
			String defendantsStatementChargeReason,
			String defendantsStatementInvolvementReason,
			String defendantsStatementCourtRecommendation)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates specified CircumstanceOfOffense with given parameters
	 * @param circumstanceOfOffense - CircumstanceOfOffense to update
	 * @param document - Document
	 * @param defendantsStatementChargeReason - String
	 * @param defendantsStatementInvolvementReason - String
	 * @param defendantsStatementCourtRecommendation - String
	 * @return Updated CircumstanceOfOffense
	 * @throws DuplicateEntityFoundException - when a CircumstanceOfOffense
	 * already exists with given OffenseSectionSummary
	 */
	public CircumstanceOfOffense updateCircumstanceOfOffense(
			CircumstanceOfOffense circumstanceOfOffense, Document document,
			String defendantsStatementChargeReason,
			String defendantsStatementInvolvementReason,
			String defendantsStatementCourtRecommendation)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes specified CircumstanceOfOffense
	 * @param circumstanceOfOffense - CircumstanceOfOffense to remove
	 */
	public void removeCircumstanceOfOffense(
			CircumstanceOfOffense circumstanceOfOffense);
	
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
	 * Returns a list of OffenseSectionSummaryAssociableDocuments found by
	 * specified OffenseSectionSummary
	 * @param offenseSectionSummary - OffenseSectionSummary
	 * @return List of OffenseSectionSummaryAssociableDocuments found by
	 * specified OffenseSectionSummary
	 */
	public List<OffenseSectionSummaryAssociableDocument>
		findOffenseSectionSummaryAssociableDocumentsByOffenseSectionSummary(
				OffenseSectionSummary offenseSectionSummary);
	
	/**
	 * Returns the CircumstanceOfOffense for specified OffenseSectionSummary
	 * @param offenseSectionSummary - OffenseSectionSummary
	 * @return CircumstanceOfOffense for specified OffenseSectionSummary
	 */
	public CircumstanceOfOffense findCircumstanceOfOffenseByOffenseSectionSummary(
			OffenseSectionSummary offenseSectionSummary);
	
	/**
	 * Returns the OffenseSectionSummary for specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return OffenseSectionSummary for specified
	 * PresentenceInvestigationRequest
	 */
	public OffenseSectionSummary
		findOffenseSectionSummaryByPresentenceInvestigationRequest(
				PresentenceInvestigationRequest presentenceInvestigationRequest);
}
