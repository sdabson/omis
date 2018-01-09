package omis.courtcasecondition.service;

import java.util.Date;
import java.util.List;

import omis.condition.domain.Agreement;
import omis.condition.domain.AgreementAssociableDocument;
import omis.condition.domain.AgreementCategory;
import omis.condition.domain.AgreementNote;
import omis.condition.domain.Condition;
import omis.courtcasecondition.domain.CourtCaseAgreement;
import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.docket.domain.Docket;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;

/**
 * Service for Agreements.
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @author Annie Wahl
 * @version 0.1.2 (Nov 28, 2017)
 * @since OMIS 3.0
 */
public interface CourtCaseAgreementService {

	/**
	 * Creates a new CourtCaseAgreement.
	 * @param agreement - Agreement
	 * @param courtCaseAgreementCategory - CourtCaseAgreementCategory
	 * @param docket - docket
	 * @param acceptedDate - accepted date
	 * @return the newly created CourtCaseAgreement
	 */
	CourtCaseAgreement createCourtCaseAgreement(
			Agreement agreement, Docket docket, Date acceptedDate,
			CourtCaseAgreementCategory courtCaseAgreementCategory);

	/**
	 * Updates a CourtCaseAgreement.
	 * @param courtCaseAgreement - CourtCaseAgreement to update
	 * @param courtCaseAgreementCategory - CourtCaseAgreementCategory
	 * @param docket - docket
	 * @param acceptedDate - accepted date
	 * @return the newly updated CourtCaseAgreement
	 */
	CourtCaseAgreement updateCourtCaseAgreement(
			CourtCaseAgreement courtCaseAgreement,
			Docket docket, Date acceptedDate,
			CourtCaseAgreementCategory courtCaseAgreementCategory);

	/**
	 * Remove a CourtCaseAgreement.
	 * @param courtCaseAgreement - Court Case Agreement to be removed
	 */
	void removeCourtCaseAgreement(CourtCaseAgreement courtCaseAgreement);
	
	/**
	 * Creates a Agreement.
	 * @param offender - offender.
	 * @param startDate - startDate.
	 * @param endDate - endDate.
	 * @param description - String description
	 * @param category - AgreementCategory
	 * @return Agreement. 
	 * @throws DuplicateEntityFoundException - When a duplicate Agreement is 
	 * found. */
	Agreement createAgreement(Offender offender, Date startDate,
			Date endDate, String description, AgreementCategory category)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing Agreement.
	 * @param agreement - agreement.
	 * @param startDate - startDate.
	 * @param endDate - endDate.
	 * @param description - String description
	 * @param category - Agreement Category 
	 * @return Agreement - updated agreement 
	 * @throws DuplicateEntityFoundException - When a duplicate Agreement is
	 * found */
	Agreement updateAgreement(Agreement agreement, Date startDate,
			Date endDate,  String description, AgreementCategory category)
					throws DuplicateEntityFoundException;
	
	/**
	 * Remove an Agreement.
	 * @param agreement -- Agreement to be removed
	 */
	void removeAgreement(Agreement agreement);

	/**
	 * Creates an Agreement Note.
	 * @param agreement - Agreement which is to have a new note.
	 * @param description - the new note as a string description.
	 * @param date - date the Note is relevant to
	 * @return Newly created Agreement Note
	 * @throws DuplicateEntityFoundException - When a duplicate Agreement Note
	 * is found for the specified Agreement.
	 */
	AgreementNote createNote(Agreement agreement, String description, Date date)
			throws DuplicateEntityFoundException;

	/**
	 * Updates an AgreementNote.
	 * @param agreementNote - AgreementNote to be changed
	 * @param description - String description
	 * @param date - Date
	 * @return The updated AgreementNote
	 * @throws DuplicateEntityFoundException - When a duplicate Agreement Note
	 * is found for the specified Agreement.
	 */
	AgreementNote updateNote(AgreementNote agreementNote,
			String description,
			Date date) throws DuplicateEntityFoundException;

	/**
	 * Removes an AgreementNote.
	 * @param agreementNote - Note to be removed
	 */
	void removeNote(AgreementNote agreementNote);
	
	/**
	 * Removes an condition.
	 * @param condition - condition to remove
	 */
	void removeCondition(Condition condition);

	
	/**
	 * Returns all Court Case Agreement Categories.
	 * @return All Court case Agreement Categories
	 */
	List<CourtCaseAgreementCategory> findAllCourtCaseAgreementCategories();

	/**
	 * returns Condition Clauses by a given Agreement.
	 * @param agreement - Agreement
	 * @return Condition Clauses relevant to the Agreement
	 */
	List<Condition> findConditionsByAgreement(Agreement agreement);

	/**
	 * Returns Agreements by a given Docket.
	 * @param docket - docket
	 * @return Agreements relevant to the Docket
	 */
	List<Agreement> findAgreementsByDocket(Docket docket);
	
	/**
	 * Returns a list of all Dockets for specified Offender.
	 * @param offender - Offender
	 * @return List of all Dockets for the specified Offender
	 */
	List<Docket> findAllDocketsByOffender(Offender offender);
	
	/** Create document.
	 * @param documentDate - date of document.
	 * @param filename - file name.
	 * @param fileExtension - file extension. 
	 * @param title - title. 
	 * @return document entity.
	 * @throws DuplicateEntityFoundException - when document with existing file
	 * name exists. */
	Document createDocument(Date documentDate,
			String filename, String fileExtension, String title)
					throws DuplicateEntityFoundException;
	
	/** Updates document.
	 * @param document - Document
	 * @param documentDate - Date
	 * @param title - String Title
	 * @return Document - updated document
	 * @throws DuplicateEntityFoundException */
	Document updateDocument(Document document, Date documentDate,
			String title)
					throws DuplicateEntityFoundException;

	/**
	 * Removes a specified document.
	 * @param document - Document to remove
	 */
	void removeDocument(Document document);

	/** Tag document.
	 * @param document - document.
	 * @param name - tag name. 
	 * @return document tag. 
	 * @throws DuplicateEntityFoundException - when document is already tagged
	 * with given name. */
	DocumentTag createDocumentTag(Document document, String name)
			throws DuplicateEntityFoundException;

	/** Update tag.
	 * @param documentTag - document tag.
	 * @param name - name. 
	 * @return DocumentTag - updated Document Tag.
	 * @throws DuplicateEntityFoundException - when document is already tagged
	 * with given name. */
	DocumentTag updateDocumentTag(DocumentTag documentTag, String name)
			throws DuplicateEntityFoundException;

	/** Remove tag.
	 * @param documentTag - document tag. */
	void removeDocumentTag(DocumentTag documentTag);
	

	/** Finds document tags by document.
	 * @param document - document.
	 * @return list of document tags. */
	List<DocumentTag> findDocumentTagsByDocument(Document document);
	
	/**
	 * Creates an Agreement Associable Document with the given properties.
	 * 
	 * @param agreement - Agreement
	 * @param document - Document
	 * @return Newly created Agreement
	 * @throws DuplicateEntityFoundException - When an
	 * AgreementAssociableDocument already exists with the given properties
	 */
	AgreementAssociableDocument createAgreementAssociableDocument(
			Agreement agreement, Document document)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified AgreementAssociableDocument with the specified
	 * properties.
	 * 
	 * @param agreementAssociableDocument - AgreementAssociableDocument to
	 * update
	 * @param agreement - Agreement
	 * @param document - Document
	 * @return Updated AgreementAssociableDocument
	 * @throws DuplicateEntityFoundException - When another
	 * AgreementAssociableDocument already exists with the specified properties
	 */
	AgreementAssociableDocument updateAgreementAssociableDocument(
			AgreementAssociableDocument agreementAssociableDocument,
			Agreement agreement, Document document)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified Agreement Associable Document.
	 * 
	 * @param agreementAssociableDocument - AgreementAssociableDocument
	 * to remove
	 */
	void removeAgreementAssociableDocument(
			AgreementAssociableDocument agreementAssociableDocument);
	
	/**
	 * Returns a list of Agreement Associable Documents by the specified 
	 * Agreement.
	 * 
	 * @param agreement - Agreement
	 * @return List of Agreement Associable Documents by the specified 
	 * Agreement
	 */
	List<AgreementAssociableDocument>
		findAgreementAssociableDocumentsByAgreement(Agreement agreement);
}
