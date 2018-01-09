package omis.presentenceinvestigation.service.impl;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummary;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummaryDocument;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummaryNote;
import omis.presentenceinvestigation.service.PsychologicalSectionSummaryService;
import omis.presentenceinvestigation.service.delegate.PsychologicalSectionSummaryDelegate;
import omis.presentenceinvestigation.service.delegate.PsychologicalSectionSummaryDocumentDelegate;
import omis.presentenceinvestigation.service.delegate.PsychologicalSectionSummaryNoteDelegate;

/**
 * PsychologicalSectionSummaryServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 2, 2017)
 *@since OMIS 3.0
 *
 */
public class PsychologicalSectionSummaryServiceImpl
		implements PsychologicalSectionSummaryService {
	
	private final PsychologicalSectionSummaryDelegate 
			psychologicalSectionSummaryDelegate;
	
	private final PsychologicalSectionSummaryNoteDelegate
			psychologicalSectionSummaryNoteDelegate;
	
	private final PsychologicalSectionSummaryDocumentDelegate
			psychologicalSectionSummaryDocumentDelegate;
	
	private final DocumentDelegate documentDelegate;
	
	private final DocumentTagDelegate documentTagDelegate;
	
	/**
	 * Constructor for PsychologicalSectionSummaryServiceImpl
	 * @param psychologicalSectionSummaryDelegate
	 * @param psychologicalSectionSummaryNoteDelegate
	 * @param psychologicalSectionSummaryDocumentDelegate
	 * @param documentDelegate
	 * @param documentTagDelegate
	 */
	public PsychologicalSectionSummaryServiceImpl(
			final PsychologicalSectionSummaryDelegate
					psychologicalSectionSummaryDelegate,
			final PsychologicalSectionSummaryNoteDelegate
					psychologicalSectionSummaryNoteDelegate,
			final PsychologicalSectionSummaryDocumentDelegate
					psychologicalSectionSummaryDocumentDelegate,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate) {
		this.psychologicalSectionSummaryDelegate =
				psychologicalSectionSummaryDelegate;
		this.psychologicalSectionSummaryNoteDelegate =
				psychologicalSectionSummaryNoteDelegate;
		this.psychologicalSectionSummaryDocumentDelegate =
				psychologicalSectionSummaryDocumentDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public PsychologicalSectionSummary createPsychologicalSectionSummary(
			final PresentenceInvestigationRequest presentenceInvestigationRequest)
					throws DuplicateEntityFoundException {
		return this.psychologicalSectionSummaryDelegate.create(
				presentenceInvestigationRequest);
	}

	/**{@inheritDoc} */
	@Override
	public PsychologicalSectionSummaryNote createPsychologicalSectionSummaryNote(
			final String description, final Date date,
			final PsychologicalSectionSummary psychologicalSectionSummary)
					throws DuplicateEntityFoundException {
		return this.psychologicalSectionSummaryNoteDelegate.create(description,
				date, psychologicalSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public PsychologicalSectionSummaryNote updatePsychologicalSectionSummaryNote(
			final PsychologicalSectionSummaryNote psychologicalSectionSummaryNote,
			final String description, final Date date)
					throws DuplicateEntityFoundException {
		return this.psychologicalSectionSummaryNoteDelegate.update(
				psychologicalSectionSummaryNote, description, date);
	}

	/**{@inheritDoc} */
	@Override
	public void removePsychologicalSectionSummaryNote(
			final PsychologicalSectionSummaryNote psychologicalSectionSummaryNote) {
		this.psychologicalSectionSummaryNoteDelegate.remove(
				psychologicalSectionSummaryNote);
	}

	/**{@inheritDoc} */
	@Override
	public PsychologicalSectionSummaryDocument
			createPsychologicalSectionSummaryDocument(final Document document,
					final PsychologicalSectionSummary psychologicalSectionSummary)
							throws DuplicateEntityFoundException {
		return this.psychologicalSectionSummaryDocumentDelegate.create(
				document, psychologicalSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public PsychologicalSectionSummaryDocument
			updatePsychologicalSectionSummaryDocument(
					final PsychologicalSectionSummaryDocument
						psychologicalSectionSummaryDocument,
					final Document document)
							throws DuplicateEntityFoundException {
		return this.psychologicalSectionSummaryDocumentDelegate.update(
				psychologicalSectionSummaryDocument, document);
	}

	/**{@inheritDoc} */
	@Override
	public void removePsychologicalSectionSummaryDocument(
			final PsychologicalSectionSummaryDocument
					psychologicalSectionSummaryDocument) {
		this.psychologicalSectionSummaryDocumentDelegate.remove(
				psychologicalSectionSummaryDocument);
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
	public PsychologicalSectionSummary
			findPsychologicalSectionSummaryByPresentenceInvestigation(
					final PresentenceInvestigationRequest
							presentenceInvestigationRequest) {
		return this.psychologicalSectionSummaryDelegate
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}

	/**{@inheritDoc} */
	@Override
	public List<PsychologicalSectionSummaryNote>
			findPsychologicalSectionSummaryNotesByPsychologicalSectionSummary(
					final PsychologicalSectionSummary psychologicalSectionSummary) {
		return this.psychologicalSectionSummaryNoteDelegate
				.findByPsychologicalSectionSummary(psychologicalSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public List<PsychologicalSectionSummaryDocument>
			findPsychologicalSectionSummaryDocumentsByPsychologicalSectionSummary(
					final PsychologicalSectionSummary psychologicalSectionSummary) {
		return this.psychologicalSectionSummaryDocumentDelegate
				.findByPsychologicalSectionSummary(psychologicalSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public List<DocumentTag> findDocumentTagsByDocument(final Document document) {
		return this.documentTagDelegate.findByDocument(document);
	}

}
