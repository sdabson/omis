package omis.courtdocument.service;

import java.util.Date;
import java.util.List;

import omis.courtcase.domain.CourtCase;
import omis.courtdocument.domain.CourtCaseDocumentAssociation;
import omis.courtdocument.domain.CourtDocumentCategory;
import omis.courtdocument.report.CourtCaseDocumentAssociationSummary;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;

/** Service for court case document related operations.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 18, 2015)
 * @since OMIS 3.0 */
public interface CourtCaseDocumentAssociationService {
	/** Create a court case document.
	 * @param courtCase - court case.
	 * @param document - document.
	 * @param date - date.
	 * @param category - court document category.
	 * @return court document association.
	 * @throws DuplicateEntityFoundException - when court case with given 
	 * document and court case exists. */
	CourtCaseDocumentAssociation createCourtCaseDocumentAssociation(
			CourtCase courtCase, Document document, Date date, 
			CourtDocumentCategory category) 
					throws DuplicateEntityFoundException;
	
	/** Updates a court case document.
	 * @param courtCaseDocumentAssociation - court case document association.
	 * @param courtCase - court case.
	 * @param document - document.
	 * @param date - date.
	 * @param courtDocumentCategory - court case document category.
	 * @throws DuplicateEntityFoundException - when court case with given
	 * document and court case exists. */
	void updateCourtCaseDocumentAssociation(
			CourtCaseDocumentAssociation courtCaseDocumentAssociation, 
			CourtCase courtCase, Document document, Date date, 
			CourtDocumentCategory courtDocumentCategory) 
					throws DuplicateEntityFoundException;
	
	/** Removes court case association.
	 * @param courtCaseDocumentAssociation - court case document association. */
	void removeCourtCaseDocumentAssociation(
			CourtCaseDocumentAssociation courtCaseDocumentAssociation);
	
	/** Creates document.
	 * @param date - date.
	 * @param filename - filename.
	 * @param fileExtension - file extension.
	 * @param title - title. 
	 * @return document.
	 * @throws DuplicateEntityFoundException - when document with given title
	 * exists. */
	Document createDocument(
			Date date, String filename, String fileExtension, String title)
				throws DuplicateEntityFoundException;

	/** Updates document.
	 * @param document - document.
	 * @param title - title.
	 * @param date - date. 
	 * @throws DuplicateEntityFoundException */
	void updateDocument(Document document, String title, Date date)
			throws DuplicateEntityFoundException;
	
	/** Creates document tag.
	 * @param document - document.
	 * @param name - name.
	 * @return document tag.
	 * @throws DuplicateEntityFoundException - when document exists with given
	 * tag name. */
	DocumentTag createDocumentTag(Document document, String name) 
		throws DuplicateEntityFoundException;
	
	/** Updates document tag.
	 * @param documentTag - document tag.
	 * @param name - name.
	 * @throws DuplicateEntityFoundException - when document is already 
	 * tagged with given name. */
	void updateDocumentTag(DocumentTag documentTag, String name)
		throws DuplicateEntityFoundException;
	
	/** Removes document tag.
	 * @param documentTag - document tag. */
	void removeTag(DocumentTag documentTag);
	
	/** Finds document tag by document.
	 * @param document - document.
	 * @return document tags. */
	List<DocumentTag> findDocumentTagsByDocument(final Document document);
	
	/** Finds court document categories.
	 * @return list of court document categories. */
	List<CourtDocumentCategory> findCategories();
	
	/** Finds court cases by offender.
	 * @param offender - offender.
	 * @return list court cases. */
	List<CourtCase> findCourtCasesByOffender(Offender offender);
	
	/** Finds court case document association summaries.
	 * @param offender - offender.
	 * @return list of court case documents. */
	List<CourtCaseDocumentAssociationSummary> 
		findCourtCaseDocumentAssociationSummariesByOffender(Offender offender);
}
