package omis.presentenceinvestigation.service.impl;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummary;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummaryAssociableDocument;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.PleaAgreementSectionSummaryService;
import omis.presentenceinvestigation.service.delegate.PleaAgreementSectionSummaryAssociableDocumentDelegate;
import omis.presentenceinvestigation.service.delegate.PleaAgreementSectionSummaryDelegate;
import omis.presentenceinvestigation.service.delegate.PleaAgreementSectionSummaryNoteDelegate;

/**
 * PleaAgreementSectionSummaryServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 13, 2017)
 *@since OMIS 3.0
 *
 */
public class PleaAgreementSectionSummaryServiceImpl
	implements PleaAgreementSectionSummaryService {
	
	private final PleaAgreementSectionSummaryDelegate
		pleaAgreementSectionSummaryDelegate;
	
	private final PleaAgreementSectionSummaryNoteDelegate
		pleaAgreementSectionSummaryNoteDelegate;
	
	private final PleaAgreementSectionSummaryAssociableDocumentDelegate
		pleaAgreementSectionSummaryAssociableDocumentDelegate;
	
	private final DocumentDelegate documentDelegate;
	
	private final DocumentTagDelegate documentTagDelegate;
	
	
	
	/**
	 * @param pleaAgreementSectionSummaryDelegate
	 * @param pleaAgreementSectionSummaryNoteDelegate
	 * @param pleaAgreementSectionSummaryAssociableDocumentDelegate
	 * @param documentDelegate
	 * @param documentTagDelegate
	 */
	public PleaAgreementSectionSummaryServiceImpl(
			final PleaAgreementSectionSummaryDelegate
				pleaAgreementSectionSummaryDelegate,
			final PleaAgreementSectionSummaryNoteDelegate
				pleaAgreementSectionSummaryNoteDelegate,
			final PleaAgreementSectionSummaryAssociableDocumentDelegate
				pleaAgreementSectionSummaryAssociableDocumentDelegate,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate) {
		this.pleaAgreementSectionSummaryDelegate =
				pleaAgreementSectionSummaryDelegate;
		this.pleaAgreementSectionSummaryNoteDelegate =
				pleaAgreementSectionSummaryNoteDelegate;
		this.pleaAgreementSectionSummaryAssociableDocumentDelegate =
				pleaAgreementSectionSummaryAssociableDocumentDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public PleaAgreementSectionSummary createPleaAgreementSectionSummary(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String summary)
					throws DuplicateEntityFoundException {
		return this.pleaAgreementSectionSummaryDelegate.create(
				presentenceInvestigationRequest, summary);
	}

	/**{@inheritDoc} */
	@Override
	public PleaAgreementSectionSummary updatePleaAgreementSectionSummary(
			final PleaAgreementSectionSummary pleaAgreementSectionSummary,
			final String summary)
					throws DuplicateEntityFoundException {
		return this.pleaAgreementSectionSummaryDelegate.update(
				pleaAgreementSectionSummary, summary);
	}

	/**{@inheritDoc} */
	@Override
	public void removePleaAgreementSectionSummary(
			final PleaAgreementSectionSummary pleaAgreementSectionSummary) {
		this.pleaAgreementSectionSummaryDelegate.remove(
				pleaAgreementSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public PleaAgreementSectionSummaryNote createPleaAgreementSectionSummaryNote(
			final PleaAgreementSectionSummary pleaAgreementSectionSummary,
			final String description, final Date date)
					throws DuplicateEntityFoundException {
		return this.pleaAgreementSectionSummaryNoteDelegate.create(
				pleaAgreementSectionSummary, description, date);
	}

	/**{@inheritDoc} */
	@Override
	public PleaAgreementSectionSummaryNote updatePleaAgreementSectionSummaryNote(
			final PleaAgreementSectionSummaryNote pleaAgreementSectionSummaryNote,
			final String description, final Date date)
					throws DuplicateEntityFoundException {
		return this.pleaAgreementSectionSummaryNoteDelegate.update(
				pleaAgreementSectionSummaryNote, description, date);
	}

	/**{@inheritDoc} */
	@Override
	public void removePleaAgreementSectionSummaryNote(
			final PleaAgreementSectionSummaryNote pleaAgreementSectionSummaryNote) {
		this.pleaAgreementSectionSummaryNoteDelegate.remove(
				pleaAgreementSectionSummaryNote);
	}

	/**{@inheritDoc} */
	@Override
	public PleaAgreementSectionSummaryAssociableDocument
		createPleaAgreementSectionSummaryAssociableDocument(
			final PleaAgreementSectionSummary pleaAgreementSectionSummary,
			final Document document)
					throws DuplicateEntityFoundException {
		return this.pleaAgreementSectionSummaryAssociableDocumentDelegate.create(
				pleaAgreementSectionSummary, document);
	}

	/**{@inheritDoc} */
	@Override
	public PleaAgreementSectionSummaryAssociableDocument
		updatePleaAgreementSectionSummaryAssociableDocument(
			final PleaAgreementSectionSummaryAssociableDocument
				pleaAgreementSectionSummaryAssociableDocument,
			final Document document) throws DuplicateEntityFoundException {
		return this.pleaAgreementSectionSummaryAssociableDocumentDelegate.update(
				pleaAgreementSectionSummaryAssociableDocument, document);
	}

	/**{@inheritDoc} */
	@Override
	public void removePleaAgreementSectionSummaryAssociableDocument(
			final PleaAgreementSectionSummaryAssociableDocument
				pleaAgreementSectionSummaryAssociableDocument) {
		this.pleaAgreementSectionSummaryAssociableDocumentDelegate.remove(
				pleaAgreementSectionSummaryAssociableDocument);
	}

	/**{@inheritDoc} */
	@Override
	public PleaAgreementSectionSummary
		findPleaAgreementSectionSummaryByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		return this.pleaAgreementSectionSummaryDelegate
				.findByPresentenceInvestigationRequest(presentenceInvestigationRequest);
	}

	/**{@inheritDoc} */
	@Override
	public List<PleaAgreementSectionSummaryNote>
		findPleaAgreementSectionSummaryNotesByPleaAgreementSectionSummary(
			final PleaAgreementSectionSummary pleaAgreementSectionSummary) {
		return this.pleaAgreementSectionSummaryNoteDelegate
				.findByPleaAgreementSectionSummary(pleaAgreementSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public List<PleaAgreementSectionSummaryAssociableDocument>
	findPleaAgreementSectionSummaryAssociableDocumentsByPleaAgreementSectionSummary(
			final PleaAgreementSectionSummary pleaAgreementSectionSummary) {
		return this.pleaAgreementSectionSummaryAssociableDocumentDelegate
				.findByPleaAgreementSectionSummary(pleaAgreementSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public Document createDocument(final Date documentDate, final String filename,
				final String fileExtension, final String title)
						throws DuplicateEntityFoundException {
		return this.documentDelegate.create(documentDate, filename,
				fileExtension, title);
	}

	/**{@inheritDoc} */
	@Override
	public Document updateDocument(final Document document,
			final Date documentDate, final String title)
					throws DuplicateEntityFoundException {
		return this.documentDelegate.update(document, title, documentDate);
	}

	/**{@inheritDoc} */
	@Override
	public void removeDocument(final Document document) {
		this.documentDelegate.remove(document);
	}

	/**{@inheritDoc} */
	@Override
	public DocumentTag createDocumentTag(final Document document,
			final String name) throws DuplicateEntityFoundException {
		return this.documentTagDelegate.tagDocument(document, name);
	}

	/**{@inheritDoc} */
	@Override
	public DocumentTag updateDocumentTag(final DocumentTag documentTag,
			final String name) throws DuplicateEntityFoundException {
		return this.documentTagDelegate.update(documentTag, name);
	}

	/**{@inheritDoc} */
	@Override
	public void removeDocumentTag(final DocumentTag documentTag) {
		this.documentTagDelegate.removeTag(documentTag);
	}

	/**{@inheritDoc} */
	@Override
	public List<DocumentTag> findDocumentTagsByDocument(final Document document) {
		return this.documentTagDelegate.findByDocument(document);
	}

}
