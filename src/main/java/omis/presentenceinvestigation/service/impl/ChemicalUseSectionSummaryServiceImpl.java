package omis.presentenceinvestigation.service.impl;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummary;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummaryDocumentAssociation;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.ChemicalUseSectionSummaryService;
import omis.presentenceinvestigation.service.delegate.ChemicalUseSectionSummaryDelegate;
import omis.presentenceinvestigation.service.delegate.ChemicalUseSectionSummaryDocumentAssociationDelegate;
import omis.presentenceinvestigation.service.delegate.ChemicalUseSectionSummaryNoteDelegate;

/**
 * ChemicalUseSectionSummaryServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 6, 2017)
 *@since OMIS 3.0
 *
 */
public class ChemicalUseSectionSummaryServiceImpl
	implements ChemicalUseSectionSummaryService {
	
	private final ChemicalUseSectionSummaryDelegate
		chemicalUseSectionSummaryDelegate;
	
	private final ChemicalUseSectionSummaryNoteDelegate
		chemicalUseSectionSummaryNoteDelegate;
	
	private final ChemicalUseSectionSummaryDocumentAssociationDelegate
		chemicalUseSectionSummaryDocumentAssociationDelegate;
	
	private final DocumentDelegate documentDelegate;
	
	private final DocumentTagDelegate documentTagDelegate;
	
	/**
	 * @param chemicalUseSectionSummaryDelegate
	 * @param chemicalUseSectionSummaryNoteDelegate
	 * @param chemicalUseSectionSummaryDocumentAssociationDelegate
	 * @param documentDelegate
	 * @param documentTagDelegate
	 */
	public ChemicalUseSectionSummaryServiceImpl(
			final ChemicalUseSectionSummaryDelegate
				chemicalUseSectionSummaryDelegate,
			final ChemicalUseSectionSummaryNoteDelegate
				chemicalUseSectionSummaryNoteDelegate,
			final ChemicalUseSectionSummaryDocumentAssociationDelegate
				chemicalUseSectionSummaryDocumentAssociationDelegate,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate) {
		this.chemicalUseSectionSummaryDelegate =
				chemicalUseSectionSummaryDelegate;
		this.chemicalUseSectionSummaryNoteDelegate =
				chemicalUseSectionSummaryNoteDelegate;
		this.chemicalUseSectionSummaryDocumentAssociationDelegate =
				chemicalUseSectionSummaryDocumentAssociationDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public ChemicalUseSectionSummary createChemicalUseSectionSummary(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String text)
					throws DuplicateEntityFoundException {
		return this.chemicalUseSectionSummaryDelegate.create(
				presentenceInvestigationRequest, text);
	}

	/**{@inheritDoc} */
	@Override
	public ChemicalUseSectionSummary updateChemicalUseSectionSummary(
			final ChemicalUseSectionSummary chemicalUseSectionSummary,
			final String text)
					throws DuplicateEntityFoundException {
		return this.chemicalUseSectionSummaryDelegate.update(
				chemicalUseSectionSummary, text);
	}

	/**{@inheritDoc} */
	@Override
	public void removeChemicalUseSectionSummary(
			final ChemicalUseSectionSummary chemicalUseSectionSummary) {
		this.chemicalUseSectionSummaryDelegate.remove(chemicalUseSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public ChemicalUseSectionSummaryNote createChemicalUseSectionSummaryNote(
			final String description, final Date date,
			final ChemicalUseSectionSummary chemicalUseSectionSummary)
					throws DuplicateEntityFoundException {
		return this.chemicalUseSectionSummaryNoteDelegate.create(
				description, date, chemicalUseSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public ChemicalUseSectionSummaryNote updateChemicalUseSectionSummaryNote(
			final ChemicalUseSectionSummaryNote chemicalUseSectionSummaryNote,
			final String description, final Date date)
					throws DuplicateEntityFoundException {
		return this.chemicalUseSectionSummaryNoteDelegate.update(
				chemicalUseSectionSummaryNote, description, date);
	}

	/**{@inheritDoc} */
	@Override
	public void removeChemicalUseSectionSummaryNote(
			final ChemicalUseSectionSummaryNote chemicalUseSectionSummaryNote) {
		this.chemicalUseSectionSummaryNoteDelegate.remove(
				chemicalUseSectionSummaryNote);
	}

	/**{@inheritDoc} */
	@Override
	public ChemicalUseSectionSummaryDocumentAssociation
		createChemicalUseSectionSummaryDocumentAssociation(
			final Document document,
			final ChemicalUseSectionSummary chemicalUseSectionSummary)
					throws DuplicateEntityFoundException {
		return this.chemicalUseSectionSummaryDocumentAssociationDelegate
				.create(document, chemicalUseSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public ChemicalUseSectionSummaryDocumentAssociation
		updateChemicalUseSectionSummaryDocumentAssociation(
			final ChemicalUseSectionSummaryDocumentAssociation 
				chemicalUseSectionSummaryDocumentAssociation,
			final Document document)
					throws DuplicateEntityFoundException {
		return this.chemicalUseSectionSummaryDocumentAssociationDelegate
				.update(chemicalUseSectionSummaryDocumentAssociation, document);
	}

	/**{@inheritDoc} */
	@Override
	public void removeChemicalUseSectionSummaryDocumentAssociation(
			final ChemicalUseSectionSummaryDocumentAssociation 
				chemicalUseSectionSummaryDocumentAssociation) {
		this.chemicalUseSectionSummaryDocumentAssociationDelegate
				.remove(chemicalUseSectionSummaryDocumentAssociation);
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
	public ChemicalUseSectionSummary
		findChemicalUseSectionSummaryByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		return this.chemicalUseSectionSummaryDelegate
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}

	/**{@inheritDoc} */
	@Override
	public List<ChemicalUseSectionSummaryNote>
		findChemicalUseSectionSummaryNotesByChemicalUseSectionSummary(
			final ChemicalUseSectionSummary chemicalUseSectionSummary) {
		return this.chemicalUseSectionSummaryNoteDelegate
				.findByChemicalUseSectionSummary(chemicalUseSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public List<ChemicalUseSectionSummaryDocumentAssociation>
		findChemicalUseSectionSummaryDocumentAssociationsByChemicalUseSectionSummary(
			final ChemicalUseSectionSummary chemicalUseSectionSummary) {
		return this.chemicalUseSectionSummaryDocumentAssociationDelegate
				.findByChemicalUseSectionSummary(chemicalUseSectionSummary);
	}

}
