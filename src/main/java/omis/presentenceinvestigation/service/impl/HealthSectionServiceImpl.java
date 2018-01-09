package omis.presentenceinvestigation.service.impl;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.HealthRating;
import omis.presentenceinvestigation.domain.HealthSectionSummary;
import omis.presentenceinvestigation.domain.HealthSectionSummaryDocumentAssociation;
import omis.presentenceinvestigation.domain.HealthSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.HealthSectionService;
import omis.presentenceinvestigation.service.delegate.HealthSectionSummaryDelegate;
import omis.presentenceinvestigation.service.delegate.HealthSectionSummaryDocumentAssociationDelegate;
import omis.presentenceinvestigation.service.delegate.HealthSectionSummaryNoteDelegate;

/**
 * Health section service implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 8, 2017)
 * @since OMIS 3.0
 */
public class HealthSectionServiceImpl implements HealthSectionService {
	
	private final HealthSectionSummaryDelegate healthSectionSummaryDelegate;
	
	private final HealthSectionSummaryNoteDelegate 
		healthSectionSummaryNoteDelegate;
	
	private final HealthSectionSummaryDocumentAssociationDelegate 
		healthSectionSummaryDocumentAssociationDelegate;
	
	private final DocumentDelegate documentDelegate;
	
	private final DocumentTagDelegate documentTagDelegate;
	
	public HealthSectionServiceImpl(
			final HealthSectionSummaryDelegate healthSectionSummaryDelegate,
			final HealthSectionSummaryNoteDelegate 
				healthSectionSummaryNoteDelegate,
			final HealthSectionSummaryDocumentAssociationDelegate 
				healthSectionSummaryDocumentAssociationDelegate,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate) {
		this.healthSectionSummaryDelegate = healthSectionSummaryDelegate;
		this.healthSectionSummaryNoteDelegate = healthSectionSummaryNoteDelegate;
		this.healthSectionSummaryDocumentAssociationDelegate 
			= healthSectionSummaryDocumentAssociationDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public HealthSectionSummary createHealthSectionSummary(
			final PresentenceInvestigationRequest 
			presentenceInvestigationRequest, final HealthRating rating) 
					throws DuplicateEntityFoundException {
		return this.healthSectionSummaryDelegate.createHealthSectionSummary(
				presentenceInvestigationRequest, rating);
	}


	/** {@inheritDoc} */
	@Override
	public HealthSectionSummaryNote createHealthSectionSummaryNote(
			final HealthSectionSummary healthSectionSummary,
			final String description, final Date date) 
					throws DuplicateEntityFoundException {
		return this.healthSectionSummaryNoteDelegate
				.createHealthSectionSummaryNote(healthSectionSummary, 
						description, date);
	}

	/** {@inheritDoc} */
	@Override
	public HealthSectionSummary updateHealthSectionSummary(
			final HealthSectionSummary healthSectionSummary,
			final HealthRating rating) throws DuplicateEntityFoundException {
		return this.healthSectionSummaryDelegate.updateHealthSectionSummary(
				healthSectionSummary, rating);
	}
	
	/** {@inheritDoc} */
	@Override
	public HealthSectionSummaryNote updateHealthSectionSummaryNote(
			final HealthSectionSummaryNote healthSectionSummaryNote,
			final HealthSectionSummary healthSectionSummary, 
			final String description, final Date date)
			throws DuplicateEntityFoundException {
		return this.healthSectionSummaryNoteDelegate
				.updateHealthSectionSummaryNote(healthSectionSummaryNote, 
						healthSectionSummary, description, date);
	}


	/** {@inheritDoc} */
	@Override
	public void removeHealthSectionSummaryNote(
			final HealthSectionSummaryNote healthSectionSummaryNote) {
		this.healthSectionSummaryNoteDelegate.removeHealthSectionSummaryNote(
				healthSectionSummaryNote);
	}

	/** {@inheritDoc} */
	@Override
	public Document createDocument(final Date documentDate, final String title, 
			final String fileName, final String fileExtension)
			throws DuplicateEntityFoundException {
		return this.documentDelegate.create(documentDate, fileName, 
				fileExtension, title);
	}


	/** {@inheritDoc} */
	@Override
	public Document updateDocument(final Document document, 
			final Date documentDate, final String title, 
			final String fileExtension) throws DuplicateEntityFoundException {
		document.setFileExtension(fileExtension);
		return this.documentDelegate.update(document, title, documentDate);
	}

	/** {@inheritDoc} */
	@Override
	public void removeDocument(final Document document) {
		this.documentDelegate.remove(document);
	}

	/** {@inheritDoc} */
	@Override
	public DocumentTag createDocumentTag(final Document document, 
			final String name) throws DuplicateEntityFoundException {
		return this.documentTagDelegate.tagDocument(document, name);
	}

	/** {@inheritDoc} */
	@Override
	public DocumentTag updateDocumentTag(final DocumentTag documentTag, 
			final Document document, final String name)
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
	public HealthSectionSummaryDocumentAssociation 
	createHealthSectionSummaryDocumentAssociation(
			final HealthSectionSummary healthSectionSummary, 
			final Document document) throws DuplicateEntityFoundException {
		return this.healthSectionSummaryDocumentAssociationDelegate
				.createHealthSectionSummaryDocumentAssociation(
						healthSectionSummary, document);
	}
	
	/** {@inheritDoc} */
	@Override
	public HealthSectionSummaryDocumentAssociation 
		updateHealthSectionSummaryDocumentAssociation(
			final HealthSectionSummaryDocumentAssociation 
			healthSectionSummaryDocumentAssociation,
			final HealthSectionSummary healthSectionSummary, 
			final Document document) throws DuplicateEntityFoundException {
		return this.healthSectionSummaryDocumentAssociationDelegate
				.updateHealthSectionSummaryDocumentAssociation(
						healthSectionSummaryDocumentAssociation, 
						healthSectionSummary, document);
	}

	/** {@inheritDoc} */
	@Override
	public void removeHealthSectionSummaryDocumentAssociation(
			final HealthSectionSummaryDocumentAssociation 
			healthSectionSummaryDocumentAssociation) {
			this.healthSectionSummaryDocumentAssociationDelegate
			.removeHealthSectionSummaryDocumentAssociation(
					healthSectionSummaryDocumentAssociation);
	}

	/** {@inheritDoc} */
	@Override
	public HealthSectionSummary 
	findHealthSectionSummaryByPresentenceInvestigationRequest(
			PresentenceInvestigationRequest presentenceInvestigationRequest) {
		return this.healthSectionSummaryDelegate
				.findHealthSectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}

	/** {@inheritDoc} */
	@Override
	public List<HealthSectionSummaryNote> 
	findHealthSectionSummaryNotesByHealthSectionSummary(
			HealthSectionSummary healthSectionSummary) {
		return this.healthSectionSummaryNoteDelegate
				.findHealthSectionSummaryNotesByHealthSectionSummary(
				healthSectionSummary);
	}

	/** {@inheritDoc} */
	@Override
	public List<HealthSectionSummaryDocumentAssociation> 
		findHealthSectionSummaryDocumentAssociationByHealthSummary(
			final HealthSectionSummary healthSectionSummary) {
		return this.healthSectionSummaryDocumentAssociationDelegate
				.findHealthSectionSummaryDocumentAssociation(
						healthSectionSummary);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<DocumentTag> findDocumentTagsByDocument(
			final Document document) {
		return this.documentTagDelegate.findByDocument(document);
	}	
}