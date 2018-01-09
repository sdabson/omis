package omis.specialneed.service;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedAssociableDocument;
import omis.specialneed.domain.SpecialNeedAssociableDocumentCategory;
import omis.specialneed.domain.SpecialNeedCategory;
import omis.specialneed.domain.SpecialNeedClassification;
import omis.specialneed.domain.SpecialNeedNote;
import omis.specialneed.domain.SpecialNeedSource;

/**
 * Service for special need.
 * 
 * @author Joel Norris 
 * @author Sheronda Vaughn
 * @author Josh Divine
 * @version 0.1.2 (Nov 2, 2017)
 * @since OMIS 3.0
 */
public interface SpecialNeedService {
		
	/**
	 * Creates a new special need.
	 * 
	 * @param comment comment
	 * @param date startDate
	 * @param date endDate
	 * @param category special need category
	 * @param classification special need classification
	 * @param source special need source
	 * @param offender offender
	 * @return newly created special need
	 * @throws DuplicateEntityFoundException thrown when a duplicate special
	 * need is found
	 */
	SpecialNeed create(String comment, Date startDate, Date endDate,
			SpecialNeedClassification classification, 
			SpecialNeedCategory category, SpecialNeedSource source,
			String sourceComment, Offender offender) 
		throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified special need.
	 * 
	 * @param specialNeed special need
	 * @param comment comment
	 * @param date startDate
	 * @param date endDate
	 * @param category special need category
	 * @param source special need source
	 * @param sourceComment source comment
	 * @return updates special need
	 * @throws DuplicateEntityFoundException thrown when a duplicate special
	 * need, other than the specified special need, is found
	 */
	SpecialNeed update(SpecialNeed specialNeed, String comment,
			Date startDate, Date endDate, SpecialNeedCategory category,
			SpecialNeedSource source, String sourceComment)
		throws DuplicateEntityFoundException;
	
	/**
	 * Find all special need classifications.
	 *
	 *
	 * @return list of special need classifications
	 */
	List<SpecialNeedClassification> findClassifications();

	/**
	 * Removes the specified special need.
	 * 
	 * @param specialNeed speical need
	 */
	void remove(SpecialNeed specialNeed);
	
	/**
	 * Returns a list of special need sources.
	 * 
	 * @return list of special need sources
	 */
	List<SpecialNeedSource> findSources();
	
	/**
	 * Returns a list of special need categories.
	 * 
	 * @return list of special need categories
	 */
	List<SpecialNeedCategory> findCategories(
			SpecialNeedClassification classification);
	
	/**
	 * Returns a new special need note.
	 *
	 *
	 * @param specialNeed special need
	 * @param date date
	 * @param value value
	 * @return special need note
	 * @throws DuplicateEntityFoundException
	 */
	SpecialNeedNote createNote(SpecialNeed specialNeed, Date date, String value) 
		throws DuplicateEntityFoundException;
	
	/**
	 * Returns an updated special need note.
	 *
	 *
	 * @param note note
	 * @param date date
	 * @param value value
	 * @return special need note
	 * @throws DuplicateEntityFoundException
	 */
	SpecialNeedNote updateNote(SpecialNeedNote note, Date date, 
			SpecialNeed specialNeed, String value)
		throws DuplicateEntityFoundException;
	
	/**
	 * Removes a special need note.
	 *
	 *
	 * @param note note
	 */
	void removeNote(SpecialNeedNote note);
	
	/**
	 * Returns a list of special need notes for this special need.
	 *
	 *
	 * @param specialNeed special need
	 * @return list of special need notes
	 */
	List<SpecialNeedNote> findNotes(SpecialNeed specialNeed);
	
	/**
	 * Creates a new document.
	 * 
	 * @param date date
	 * @param title title
	 * @param filename filename
	 * @param fileExtension file extension
	 * @return document
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	Document createDocument(Date date, String title, String filename, 
			String fileExtension) throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing document.
	 * 
	 * @param document document
	 * @param date date
	 * @param title title
	 * @return document
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	Document updateDocument(Document document, Date date, String title) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified document.
	 * 
	 * @param document document
	 */
	void removeDocument(Document document);
	
	/**
	 * Creates a new document tag.
	 * 
	 * @param name name
	 * @param document document
	 * @return document tag
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	DocumentTag createDocumentTag(String name, Document document) 
			throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing document tag.
	 * 
	 * @param documentTag document tag
	 * @param name name
	 * @return document tag
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	DocumentTag updateDocumentTag(DocumentTag documentTag, String name) 
			throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified document tag.
	 * 
	 * @param documentTag document tag
	 */
	void removeDocumentTag(DocumentTag documentTag);
	
	/**
	 * Creates a new special need associable document.
	 * 
	 * @param specialNeed special need
	 * @param document document
	 * @param category special need associable document category
	 * @return special need associable document
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	SpecialNeedAssociableDocument createSpecialNeedAssociableDocument(
			SpecialNeed specialNeed, Document document, 
			SpecialNeedAssociableDocumentCategory category)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing special need associable document.
	 * 
	 * @param specialNeedAssociableDocument special need associable document
	 * @param specialNeed special need
	 * @param document document
	 * @param category special need associable document category
	 * @return special need associable document
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	SpecialNeedAssociableDocument updateSpecialNeedAssociableDocument(
			SpecialNeedAssociableDocument specialNeedAssociableDocument, 
			SpecialNeed specialNeed, Document document, 
			SpecialNeedAssociableDocumentCategory category) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified special need associable document.
	 * 
	 * @param specialNeedAssociableDocument special need associable document
	 */
	void removeSpecialNeedAssociableDocument(
			SpecialNeedAssociableDocument specialNeedAssociableDocument);
	
	/**
	 * Returns a list of document tags for the specified document.
	 * 
	 * @param document document
	 * @return list of document tags
	 */
	List<DocumentTag> findDocumentTagsByDocument(Document document);
	
	/**
	 * Returns the special need associable document for the specified special 
	 * need.
	 * 
	 * @param specialNeed special need
	 * @return special need associable document
	 */
	SpecialNeedAssociableDocument 
		findSpecialNeedAssociableDocumentBySpecialNeed(SpecialNeed specialNeed);
	
	/**
	 * Returns a list of special need associable document categories.
	 * 
	 * @return list of special need associable document categories
	 */
	List<SpecialNeedAssociableDocumentCategory> 
		findSpecialNeedAssociableDocumentCategories();
	
}