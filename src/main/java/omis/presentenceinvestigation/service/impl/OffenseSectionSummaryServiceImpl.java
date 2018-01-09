package omis.presentenceinvestigation.service.impl;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.CircumstanceOfOffense;
import omis.presentenceinvestigation.domain.OffenseSectionSummary;
import omis.presentenceinvestigation.domain.OffenseSectionSummaryAssociableDocument;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.OffenseSectionSummaryService;
import omis.presentenceinvestigation.service.delegate.CircumstanceOfOffenseDelegate;
import omis.presentenceinvestigation.service.delegate.OffenseSectionSummaryAssociableDocumentDelegate;
import omis.presentenceinvestigation.service.delegate.OffenseSectionSummaryDelegate;

/**
 * OffenseSectionSummaryServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 31, 2017)
 *@since OMIS 3.0
 *
 */
public class OffenseSectionSummaryServiceImpl
	implements OffenseSectionSummaryService {
	
	private final OffenseSectionSummaryDelegate offenseSectionSummaryDelegate;
	
	private final OffenseSectionSummaryAssociableDocumentDelegate
		offenseSectionSummaryAssociableDocumentDelegate;
	
	private final CircumstanceOfOffenseDelegate circumstanceOfOffenseDelegate;
	
	private final DocumentDelegate documentDelegate;
	
	private final DocumentTagDelegate documentTagDelegate;
	
	
	/**
	 * @param offenseSectionSummaryDelegate
	 * @param offenseSectionSummaryAssociableDocumentDelegate
	 * @param circumstanceOfOffenseDelegate
	 * @param documentDelegate
	 * @param documentTagDelegate
	 */
	public OffenseSectionSummaryServiceImpl(
			final OffenseSectionSummaryDelegate offenseSectionSummaryDelegate,
			final OffenseSectionSummaryAssociableDocumentDelegate
				offenseSectionSummaryAssociableDocumentDelegate,
			final CircumstanceOfOffenseDelegate circumstanceOfOffenseDelegate,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate) {
		this.offenseSectionSummaryDelegate = offenseSectionSummaryDelegate;
		this.offenseSectionSummaryAssociableDocumentDelegate =
				offenseSectionSummaryAssociableDocumentDelegate;
		this.circumstanceOfOffenseDelegate = circumstanceOfOffenseDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public OffenseSectionSummary createOffenseSectionSummary(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String text)
					throws DuplicateEntityFoundException {
		return this.offenseSectionSummaryDelegate.create(
				presentenceInvestigationRequest, text);
	}

	/**{@inheritDoc} */
	@Override
	public OffenseSectionSummary updateOffenseSectionSummary(
			final OffenseSectionSummary offenseSectionSummary, final String text)
					throws DuplicateEntityFoundException {
		return this.offenseSectionSummaryDelegate.update(offenseSectionSummary,
				text);
	}

	/**{@inheritDoc} */
	@Override
	public void removeOffenseSectionSummary(
			final OffenseSectionSummary offenseSectionSummary) {
		this.offenseSectionSummaryDelegate.remove(offenseSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public OffenseSectionSummaryAssociableDocument
		createOffenseSectionSummaryAssociableDocument(
			final OffenseSectionSummary offenseSectionSummary,
			final Document document)
					throws DuplicateEntityFoundException {
		return this.offenseSectionSummaryAssociableDocumentDelegate.create(
				offenseSectionSummary, document);
	}

	/**{@inheritDoc} */
	@Override
	public OffenseSectionSummaryAssociableDocument
		updateOffenseSectionSummaryAssociableDocument(
				final OffenseSectionSummaryAssociableDocument
					offenseSectionSummaryAssociableDocument,
				final Document document)
						throws DuplicateEntityFoundException {
		return this.offenseSectionSummaryAssociableDocumentDelegate.update(
				offenseSectionSummaryAssociableDocument, document);
	}

	/**{@inheritDoc} */
	@Override
	public void removeOffenseSectionSummaryAssociableDocument(
			final OffenseSectionSummaryAssociableDocument
				offenseSectionSummaryAssociableDocument) {
		this.offenseSectionSummaryAssociableDocumentDelegate.remove(
				offenseSectionSummaryAssociableDocument);
	}

	/**{@inheritDoc} */
	@Override
	public CircumstanceOfOffense createCircumstanceOfOffense(
			final OffenseSectionSummary offenseSectionSummary,
			final Document document,
			final String defendantsStatementChargeReason,
			final String defendantsStatementInvolvementReason,
			final String defendantsStatementCourtRecommendation)
					throws DuplicateEntityFoundException {
		return this.circumstanceOfOffenseDelegate.create(offenseSectionSummary,
				document, defendantsStatementChargeReason,
				defendantsStatementInvolvementReason,
				defendantsStatementCourtRecommendation);
	}

	/**{@inheritDoc} */
	@Override
	public CircumstanceOfOffense updateCircumstanceOfOffense(
			final CircumstanceOfOffense circumstanceOfOffense,
			final Document document,
			final String defendantsStatementChargeReason,
			final String defendantsStatementInvolvementReason,
			final String defendantsStatementCourtRecommendation)
					throws DuplicateEntityFoundException {
		return this.circumstanceOfOffenseDelegate.update(circumstanceOfOffense,
				document, defendantsStatementChargeReason,
				defendantsStatementInvolvementReason,
				defendantsStatementCourtRecommendation);
	}

	/**{@inheritDoc} */
	@Override
	public void removeCircumstanceOfOffense(
			final CircumstanceOfOffense circumstanceOfOffense) {
		this.circumstanceOfOffenseDelegate.remove(circumstanceOfOffense);
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

	/**{@inheritDoc} */
	@Override
	public List<OffenseSectionSummaryAssociableDocument>
		findOffenseSectionSummaryAssociableDocumentsByOffenseSectionSummary(
				final OffenseSectionSummary offenseSectionSummary) {
		return this.offenseSectionSummaryAssociableDocumentDelegate
				.findByOffenseSectionSummary(offenseSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public CircumstanceOfOffense findCircumstanceOfOffenseByOffenseSectionSummary(
			final OffenseSectionSummary offenseSectionSummary) {
		return this.circumstanceOfOffenseDelegate.findByOffenseSectionSummary(
				offenseSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public OffenseSectionSummary
		findOffenseSectionSummaryByPresentenceInvestigationRequest(
				final PresentenceInvestigationRequest
					presentenceInvestigationRequest) {
		return this.offenseSectionSummaryDelegate
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}

}
