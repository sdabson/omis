package omis.specialneed.service.impl;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedAssociableDocument;
import omis.specialneed.domain.SpecialNeedAssociableDocumentCategory;
import omis.specialneed.domain.SpecialNeedCategory;
import omis.specialneed.domain.SpecialNeedClassification;
import omis.specialneed.domain.SpecialNeedNote;
import omis.specialneed.domain.SpecialNeedSource;
import omis.specialneed.service.SpecialNeedService;
import omis.specialneed.service.delegate.SpecialNeedAssociableDocumentCategoryDelegate;
import omis.specialneed.service.delegate.SpecialNeedAssociableDocumentDelegate;
import omis.specialneed.service.delegate.SpecialNeedCategoryDelegate;
import omis.specialneed.service.delegate.SpecialNeedClassificationDelegate;
import omis.specialneed.service.delegate.SpecialNeedDelegate;
import omis.specialneed.service.delegate.SpecialNeedNoteDelegate;
import omis.specialneed.service.delegate.SpecialNeedSourceDelegate;

/**
 * Implementation of service for special need.
 * 
 * @author Joel Norris 
 * @author Sheronda Vaughn
 * @author Josh Divine
 * @version 0.1.3 (Nov 2, 2017)
 * @since OMIS 3.0
 */
public class SpecialNeedServiceImpl implements SpecialNeedService {

	/* Delegates. */
	
	private final SpecialNeedDelegate specialNeedDelegate;
	private final SpecialNeedCategoryDelegate specialNeedCategoryDelegate;
	private final SpecialNeedClassificationDelegate 
			specialNeedClassificationDelegate;
	private final SpecialNeedNoteDelegate specialNeedNoteDelegate;
	private final SpecialNeedSourceDelegate specialNeedSourceDelegate;
	private final DocumentDelegate documentDelegate;
	private final DocumentTagDelegate documentTagDelegate;
	private final SpecialNeedAssociableDocumentDelegate 
			specialNeedAssociableDocumentDelegate;
	private final SpecialNeedAssociableDocumentCategoryDelegate 
			specialNeedAssociableDocumentCategoryDelegate;
	
	/**
	 * Instantiates a special need service implementation with the specified
	 * data access object.
	 * 
	 * @param specialNeedDelegate special need delegate	
	 */
	public SpecialNeedServiceImpl(
			final SpecialNeedDelegate specialNeedDelegate,
			final SpecialNeedCategoryDelegate specialNeedCategoryDelegate,
			final SpecialNeedClassificationDelegate 
					specialNeedClassificationDelegate,
			final SpecialNeedNoteDelegate specialNeedNoteDelegate,
			final SpecialNeedSourceDelegate specialNeedSourceDelegate,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate,
			final SpecialNeedAssociableDocumentDelegate 
					specialNeedAssociableDocumentDelegate,
			final SpecialNeedAssociableDocumentCategoryDelegate 
					specialNeedAssociableDocumentCategoryDelegate) {
		this.specialNeedDelegate = specialNeedDelegate;
		this.specialNeedCategoryDelegate = specialNeedCategoryDelegate;
		this.specialNeedClassificationDelegate = 
				specialNeedClassificationDelegate;
		this.specialNeedNoteDelegate = specialNeedNoteDelegate;
		this.specialNeedSourceDelegate = specialNeedSourceDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
		this.specialNeedAssociableDocumentCategoryDelegate = 
				specialNeedAssociableDocumentCategoryDelegate;
		this.specialNeedAssociableDocumentDelegate = 
				specialNeedAssociableDocumentDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final SpecialNeed specialNeed) {
		this.specialNeedDelegate.remove(specialNeed);
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeed create(final String comment, final Date startDate,
			final Date endDate, final SpecialNeedClassification classification, 
			final SpecialNeedCategory category, final SpecialNeedSource source, 
			final String sourceComment, final Offender offender)
		throws DuplicateEntityFoundException {
		return this.specialNeedDelegate.create(
				comment, startDate, endDate, classification, 
				category, source, sourceComment, offender);
				
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeed update(final SpecialNeed specialNeed,
			final String comment, final Date startDate, final Date endDate,
			final SpecialNeedCategory category,
			final SpecialNeedSource source, final String sourceComment)
			throws DuplicateEntityFoundException {		
		return this.specialNeedDelegate.update(specialNeed, comment, startDate, 
				endDate, category, source, sourceComment);
	}

	/** {@inheritDoc} */
	@Override
	public List<SpecialNeedSource> findSources() {
		return this.specialNeedSourceDelegate.findSources();
	}

	/** {@inheritDoc} */
	@Override
	public List<SpecialNeedClassification> findClassifications() {
		return this.specialNeedClassificationDelegate.findClassifications();				
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SpecialNeedCategory> findCategories(
			final SpecialNeedClassification classification) {
		return this.specialNeedCategoryDelegate.findCategories(classification);
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedNote createNote(final SpecialNeed specialNeed, 
			final Date date, final String value)
			throws DuplicateEntityFoundException {
		return this.specialNeedNoteDelegate.createNote(specialNeed, date, value);
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedNote updateNote(final SpecialNeedNote note, 
			final Date date, final SpecialNeed specialNeed, final String value)
			throws DuplicateEntityFoundException {
		return this.specialNeedNoteDelegate.updateNote(note, date, specialNeed, 
				value);
	}

	/** {@inheritDoc} */
	@Override
	public void removeNote(final SpecialNeedNote note) {
		this.specialNeedNoteDelegate.removeNote(note);
	}

	/** {@inheritDoc} */
	@Override
	public List<SpecialNeedNote> findNotes(SpecialNeed specialNeed) {
		return this.specialNeedNoteDelegate.findNotes(specialNeed);
	}

	/** {@inheritDoc} */
	@Override
	public Document createDocument(final Date date, final String title, 
			final String filename, final String fileExtension) 
					throws DuplicateEntityFoundException {
		return this.documentDelegate.create(date, filename, fileExtension, 
				title);
	}

	/** {@inheritDoc} */
	@Override
	public Document updateDocument(final Document document, final Date date, 
			final String title) throws DuplicateEntityFoundException {
		return this.documentDelegate.update(document, title, date);
	}

	/** {@inheritDoc} */
	@Override
	public void removeDocument(final Document document) {
		this.documentDelegate.remove(document);
	}

	/** {@inheritDoc} */
	@Override
	public DocumentTag createDocumentTag(final String name, 
			final Document document) throws DuplicateEntityFoundException {
		return this.documentTagDelegate.tagDocument(document, name);
	}

	/** {@inheritDoc} */
	@Override
	public DocumentTag updateDocumentTag(final DocumentTag documentTag, 
			final String name)
			throws DuplicateEntityFoundException {
		return this.documentTagDelegate.update(documentTag, name);
	}

	/** {@inheritDoc} */
	@Override
	public void removeDocumentTag(final DocumentTag documentTag) {
		this.documentTagDelegate.removeTag(documentTag);
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedAssociableDocument createSpecialNeedAssociableDocument(
			final SpecialNeed specialNeed, final Document document,
			final SpecialNeedAssociableDocumentCategory category) 
					throws DuplicateEntityFoundException {
		return this.specialNeedAssociableDocumentDelegate.create(specialNeed, 
				document, category);
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedAssociableDocument updateSpecialNeedAssociableDocument(
			final SpecialNeedAssociableDocument specialNeedAssociableDocument, 
			final SpecialNeed specialNeed, final Document document,
			final SpecialNeedAssociableDocumentCategory category) 
					throws DuplicateEntityFoundException {
		return this.specialNeedAssociableDocumentDelegate.update(
				specialNeedAssociableDocument, specialNeed, document, category);
	}

	/** {@inheritDoc} */
	@Override
	public void removeSpecialNeedAssociableDocument(
			final SpecialNeedAssociableDocument specialNeedAssociableDocument) {
		this.specialNeedAssociableDocumentDelegate.remove(
				specialNeedAssociableDocument);
	}

	/** {@inheritDoc} */
	@Override
	public List<DocumentTag> findDocumentTagsByDocument(final Document document) {
		return this.documentTagDelegate.findByDocument(document);
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedAssociableDocument 
		findSpecialNeedAssociableDocumentBySpecialNeed(
				final SpecialNeed specialNeed) {
		return this.specialNeedAssociableDocumentDelegate
				.findAssociableDocument(specialNeed);
	}

	@Override
	public List<SpecialNeedAssociableDocumentCategory> 
		findSpecialNeedAssociableDocumentCategories() {
		return this.specialNeedAssociableDocumentCategoryDelegate
				.findSpecialNeedAssociableDocumentCategories();
	}	
}