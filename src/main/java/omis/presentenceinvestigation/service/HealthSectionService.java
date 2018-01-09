package omis.presentenceinvestigation.service;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.HealthRating;
import omis.presentenceinvestigation.domain.HealthSectionSummary;
import omis.presentenceinvestigation.domain.HealthSectionSummaryDocumentAssociation;
import omis.presentenceinvestigation.domain.HealthSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Health section service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 4, 2017)
 * @since OMIS 3.0
 */
public interface HealthSectionService {
	
/**
 * Create a health section summary based on presentence investigation request.
 *
 * @param presentenceInvestigationRequest presentence investigation request
 * @return new health section summary
 * @throws DuplicateEntityFoundException
 */
HealthSectionSummary createHealthSectionSummary(
		PresentenceInvestigationRequest presentenceInvestigationRequest, 
		HealthRating rating)
throws DuplicateEntityFoundException;

/**
 * Create a health section summary note for current health section summary.
 *
 *
 * @param healthSectionSummary health section summary
 * @param description description
 * @param date date
 * @return new health section summary note
 * @throws DuplicateEntityFoundException
 */
HealthSectionSummaryNote createHealthSectionSummaryNote(
		HealthSectionSummary healthSectionSummary, String description, 
		Date date) throws DuplicateEntityFoundException;
/**
 * Update an existing health section summary.
 *
 *
 * @param healthSectionSummary health section summary
 * @param rating rating 
 * @return updated health section summary
 * @throws DuplicateEntityFoundException
 */
HealthSectionSummary updateHealthSectionSummary(
		HealthSectionSummary healthSectionSummary, HealthRating rating) 
				throws DuplicateEntityFoundException;

/**
 * Update an existing health section summary note.
 *
 *
 * @param healthSectionSummaryNote health section summary note
 * @param healthSectionSummary health section summary
 * @param description description
 * @param date date
 * @return updated health section summary note
 * @throws DuplicateEntityFoundException
 */
HealthSectionSummaryNote updateHealthSectionSummaryNote(
		HealthSectionSummaryNote healthSectionSummaryNote, 
		HealthSectionSummary healthSectionSummary, String description, 
		Date date) throws DuplicateEntityFoundException;

/**
 * Remove a health section summary note.
 *
 *
 * @param healthSectionSummaryNote health section summary
 */
void removeHealthSectionSummaryNote(
		HealthSectionSummaryNote healthSectionSummaryNote);

/**
 * Create a document for the health section.
 *
 *
 * @param documentDate document date
 * @param title title
 * @param fileName file name
 * @param fileExtension file extension
 * @return new document
 * @throws DuplicateEntityFoundException
 */
Document createDocument(Date documentDate, String title, String fileName, 
		String fileExtension) throws DuplicateEntityFoundException;

/**
 * Update an existing document for health section.
 *
 *
 * @param document document
 * @param documentDate document date
 * @param title title
 * @param fileName file name
 * @param fileExtension file extension
 * @return updated document
 * @throws DuplicateEntityFoundException
 */
Document updateDocument(Document document, Date documentDate, String title, 
		String fileExtension) 
				throws DuplicateEntityFoundException;

/**
 * Remove document from health section.
 *
 *
 * @param document document
 */
void removeDocument(Document document);

/**
 * Create new document tag for health section document.
 *
 *
 * @param document document
 * @param name name
 * @return new document tag
 * @throws DuplicateEntityFoundException
 */
DocumentTag createDocumentTag(Document document, String name) 
		throws DuplicateEntityFoundException;
/**
 * Update an existing document tag for the health section document.
 *
 *
 * @param documentTag document tag
 * @param document document
 * @param name name
 * @return updated document tag
 * @throws DuplicateEntityFoundException
 */
DocumentTag updateDocumentTag(DocumentTag documentTag, Document document, 
		String name) throws DuplicateEntityFoundException;

/**
 * Remove document tag.
 *
 *
 * @param documentTag document tag
 */
void removeDocumentTag(DocumentTag documentTag);

/**
 * Create health section summary document association.
 *
 *
 * @param healthSectionSummary health section summary
 * @param document document
 * @return new health section summary document association
 * @throws DuplicateEntityFoundException
 */
HealthSectionSummaryDocumentAssociation 
createHealthSectionSummaryDocumentAssociation(
		HealthSectionSummary healthSectionSummary, Document document) 
				throws DuplicateEntityFoundException;

/**
 * Update health section summary document association.
 *
 *
 * @param healthSectionSummaryDocumentAssociation health section summary 
 * document association.
 * @param healthSectionSummary health section summary
 * @param document document
 * @return updated health section summary document association
 * @throws DuplicateEntityFoundException
 */
HealthSectionSummaryDocumentAssociation 
updateHealthSectionSummaryDocumentAssociation(
		HealthSectionSummaryDocumentAssociation 
		healthSectionSummaryDocumentAssociation, 
		HealthSectionSummary healthSectionSummary, Document document) 
				throws DuplicateEntityFoundException;

/**
 * Remove heatlh section summary document association.
 *
 *
 * @param healthSectionSummaryDocumentAssociation health section summary
 * document association
 */
void removeHealthSectionSummaryDocumentAssociation(
		HealthSectionSummaryDocumentAssociation 
		healthSectionSummaryDocumentAssociation);

/**
 * Find health section summary by presentence investigation request.
 * 
 *
 * @param presentenceInvestigationRequest presentence investigation request
 * @return health section summary by presentence investigation request
 */
HealthSectionSummary findHealthSectionSummaryByPresentenceInvestigationRequest(
		PresentenceInvestigationRequest presentenceInvestigationRequest);

/**
 * Find list of health section summary notes by health section summary.
 *
 *
 * @param healthSectionSummary health section summary
 * @return list of health section summary notes
 */
List<HealthSectionSummaryNote> 
findHealthSectionSummaryNotesByHealthSectionSummary(
		HealthSectionSummary healthSectionSummary);

/**
 * Find list of health section summary
 *
 *
 * @param healthSectionSummary health section summary
 * @return list of health section summary document associations
 */
List<HealthSectionSummaryDocumentAssociation> 
findHealthSectionSummaryDocumentAssociationByHealthSummary(
		HealthSectionSummary healthSectionSummary);

/**
 * Find list of document tags by document.
 *
 *
 * @param document document
 * @return list of document tags
 */
List<DocumentTag> findDocumentTagsByDocument(Document document);
}