package omis.presentenceinvestigation.service.impl;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.FinancialSectionSummary;
import omis.presentenceinvestigation.domain.FinancialSectionSummaryDocumentAssociation;
import omis.presentenceinvestigation.domain.FinancialSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.FinancialSectionSummaryService;
import omis.presentenceinvestigation.service.delegate.FinancialSectionSummaryDelegate;
import omis.presentenceinvestigation.service.delegate.FinancialSectionSummaryDocumentAssociationDelegate;
import omis.presentenceinvestigation.service.delegate.FinancialSectionSummaryNoteDelegate;

/**
 * FinancialSectionSummaryServiceImpl
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (July 18, 2017)
 *@since OMIS 3.0
 *
 */
public class FinancialSectionSummaryServiceImpl 
	implements FinancialSectionSummaryService {

	private final FinancialSectionSummaryDelegate
		financialSectionSummaryDelegate;
	
	private final FinancialSectionSummaryNoteDelegate
		financialSectionSummaryNoteDelegate;
	
	private final FinancialSectionSummaryDocumentAssociationDelegate
		financialSectionSummaryDocumentAssociationDelegate;
	
	private final DocumentDelegate documentDelegate;
	
	private final DocumentTagDelegate documentTagDelegate;
	
	/**
	 * @param financialSectionSummaryDelegate
	 * @param financialSectionSummaryDocumentAssociationDelegate
	 * @param documentDelegate
	 * @param documentTagDelegate
	 */
	public FinancialSectionSummaryServiceImpl(
			final FinancialSectionSummaryDelegate 
				financialSectionSummaryDelegate,
			final FinancialSectionSummaryNoteDelegate 
				financialSectionSummaryNoteDelegate,
			final FinancialSectionSummaryDocumentAssociationDelegate 
				financialSectionSummaryDocumentAssociationDelegate,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate) {
		this.financialSectionSummaryDelegate = financialSectionSummaryDelegate;
		this.financialSectionSummaryNoteDelegate 
			= financialSectionSummaryNoteDelegate;
		this.financialSectionSummaryDocumentAssociationDelegate 
			= financialSectionSummaryDocumentAssociationDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
	}
	
	/**{@inheritDoc} */
	@Override
	public FinancialSectionSummary createFinancialSectionSummary(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String text)
				throws DuplicateEntityFoundException {
		return this.financialSectionSummaryDelegate.create(
				presentenceInvestigationRequest, text);
	}
	
	/**{@inheritDoc} */
	@Override
	public FinancialSectionSummary updateFinancialSectionSummary(
			final FinancialSectionSummary financialSectionSummary,
			final String text)
				throws DuplicateEntityFoundException {
		return this.financialSectionSummaryDelegate.update(
				financialSectionSummary, text);
	}
	
	/**{@inheritDoc} */
	@Override
	public void removeFinancialSectionSummary(
			final FinancialSectionSummary financialSectionSummary) {
		this.financialSectionSummaryDelegate.remove(financialSectionSummary);
	}
	
	/**{@inheritDoc} */
	@Override
	public FinancialSectionSummaryNote createFinancialSectionSummaryNote(
			final String description, final Date date,
			final FinancialSectionSummary financialSectionSummary)
					throws DuplicateEntityFoundException {
		return this.financialSectionSummaryNoteDelegate.create(
				description, date, financialSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public FinancialSectionSummaryNote updateFinancialSectionSummaryNote(
			final FinancialSectionSummaryNote financialSectionSummaryNote,
			final String description, final Date date)
					throws DuplicateEntityFoundException {
		return this.financialSectionSummaryNoteDelegate.update(
				financialSectionSummaryNote, description, date);
	}

	/**{@inheritDoc} */
	@Override
	public void removeFinancialSectionSummaryNote(
			final FinancialSectionSummaryNote financialSectionSummaryNote) {
		this.financialSectionSummaryNoteDelegate.remove(
				financialSectionSummaryNote);
	}
	
	/**{@inheritDoc} */
	@Override
	public FinancialSectionSummaryDocumentAssociation 
		createFinancialSectionSummaryDocumentAssociation(
				final Document document,
				final FinancialSectionSummary financialSectionSummary)
					throws DuplicateEntityFoundException {
		return this.financialSectionSummaryDocumentAssociationDelegate
				.create(document, financialSectionSummary);
	}
	
	/**{@inheritDoc} */
	@Override
	public FinancialSectionSummaryDocumentAssociation
		updateFinancialSectionSummaryDocumentAssociation(
				final FinancialSectionSummaryDocumentAssociation
					financialSectionSummaryDocumentAssociation,
				final Document document)
						throws DuplicateEntityFoundException {
		return this.financialSectionSummaryDocumentAssociationDelegate
				.update(financialSectionSummaryDocumentAssociation, document);
	}
	
	/**{@inheritDoc} */
	@Override
	public void removeFinancialSectionSummaryDocumentAssociation(
			final FinancialSectionSummaryDocumentAssociation 
				financialSectionSummaryDocumentAssociation) {
		this.financialSectionSummaryDocumentAssociationDelegate
			.remove(financialSectionSummaryDocumentAssociation);
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
	public FinancialSectionSummary 
		findFinancialSectionSummaryByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		return this.financialSectionSummaryDelegate
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}

	/**{@inheritDoc} */
	@Override
	public List<DocumentTag> findDocumentTagsByDocument(final Document document) {
		return this.documentTagDelegate.findByDocument(document);
	}
	
	/**{@inheritDoc} */
	@Override
	public List<FinancialSectionSummaryDocumentAssociation>
		findFinancialSectionSummaryDocumentAssociationsByFinancialSectionSummary(
				final FinancialSectionSummary financialSectionSummary) {
		return this.financialSectionSummaryDocumentAssociationDelegate
				.findByFinancialSectionSummary(financialSectionSummary);
	}
	
	/**{@inheritDoc} */
	@Override
	public List<FinancialSectionSummaryNote>
		findFinancialSectionSummaryNotesByFinancialSectionSummary(
			final FinancialSectionSummary financialSectionSummary) {
		return this.financialSectionSummaryNoteDelegate
				.findByFinancialSectionSummary(financialSectionSummary);
	}
	
}
