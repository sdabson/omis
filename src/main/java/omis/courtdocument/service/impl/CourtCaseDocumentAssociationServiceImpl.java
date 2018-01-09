package omis.courtdocument.service.impl;

import java.util.Date;
import java.util.List;

import omis.courtcase.domain.CourtCase;
import omis.courtcase.service.delegate.CourtCaseDelegate;
import omis.courtdocument.domain.CourtCaseDocumentAssociation;
import omis.courtdocument.domain.CourtDocumentCategory;
import omis.courtdocument.report.CourtCaseDocumentAssociationSummary;
import omis.courtdocument.report.CourtCaseDocumentAssociationSummaryReportService;
import omis.courtdocument.service.CourtCaseDocumentAssociationService;
import omis.courtdocument.service.delegate.CourtCaseDocumentAssociationDelegate;
import omis.courtdocument.service.delegate.CourtDocumentCategoryDelegate;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;

/** Implementation of court case document service.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 18, 2015)
 * @since OMIS 3.0 */
public class CourtCaseDocumentAssociationServiceImpl 
	implements CourtCaseDocumentAssociationService {
	private final CourtCaseDocumentAssociationDelegate 
		courtCaseDocumentAssociationDelegate;
	private final CourtCaseDelegate courtCaseDelegate;
	private final CourtDocumentCategoryDelegate courtDocumentCategoryDelegate;
	private final DocumentDelegate documentDelegate;
	private final DocumentTagDelegate documentTagDelegate;
	private final CourtCaseDocumentAssociationSummaryReportService
		courtCaseDocumentAssociationSummaryReportService;
	
	/** Constructor.
	 * @param courtCaseDocumentAssociationDelegate -
	 *  court case document association delegate.
	 * @param courtDocumentCategoryDelegate - court document category delegate.
	 * @param documentDelegate - document delegate.
	 * @param documentTagDelegate - document tag delegate.
	 * @param courtCaseDelegate - court case delegate. 
	 * @param courtCaseDocumentAssociationSummaryReportService
	 * 	courtCaseDocumentAssociationSummaryReportService*/
	public CourtCaseDocumentAssociationServiceImpl(
			final CourtCaseDocumentAssociationDelegate 
				courtCaseDocumentAssociationDelegate, 
				final CourtDocumentCategoryDelegate 
				courtDocumentCategoryDelegate, 
				final DocumentDelegate documentDelegate, 
				final DocumentTagDelegate documentTagDelegate,
				final CourtCaseDelegate courtCaseDelegate,
				final CourtCaseDocumentAssociationSummaryReportService
					courtCaseDocumentAssociationSummaryReportService) {
		this.courtCaseDocumentAssociationDelegate = 
				courtCaseDocumentAssociationDelegate;
		this.courtDocumentCategoryDelegate = courtDocumentCategoryDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
		this.courtCaseDelegate = courtCaseDelegate;
		this.courtCaseDocumentAssociationSummaryReportService =
				courtCaseDocumentAssociationSummaryReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public CourtCaseDocumentAssociation createCourtCaseDocumentAssociation(
			final CourtCase courtCase, final Document document, final Date date,
			final CourtDocumentCategory category) 
					throws DuplicateEntityFoundException {
		return this.courtCaseDocumentAssociationDelegate.create(courtCase, 
				document, date, category);
	}
	
	/** {@inheritDoc} */
	@Override
	public void updateCourtCaseDocumentAssociation(
			final CourtCaseDocumentAssociation courtCaseDocumentAssociation, 
			final CourtCase courtCase, final Document document, 
			final Date date, final CourtDocumentCategory courtDocumentCategory) 
					throws DuplicateEntityFoundException { 
		this.courtCaseDocumentAssociationDelegate.update(
				courtCaseDocumentAssociation, courtCase, document, date, 
				courtDocumentCategory);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeCourtCaseDocumentAssociation(
			final CourtCaseDocumentAssociation courtCaseDocumentAssociation) {
		this.courtCaseDocumentAssociationDelegate.remove(
				courtCaseDocumentAssociation);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<DocumentTag> findDocumentTagsByDocument(
			final Document document) {
		return this.documentTagDelegate.findByDocument(document);
	}
	

	/** {@inheritDoc} */
	@Override
	public List<CourtCase> findCourtCasesByOffender(
			final Offender offender) {
		return this.courtCaseDelegate.findByDefendant(offender);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CourtDocumentCategory> findCategories() {
		return this.courtDocumentCategoryDelegate.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public Document createDocument(final Date date, final String filename,
			final String fileExtension, final String title)
					throws DuplicateEntityFoundException {
		return this.documentDelegate.create(date, filename,	fileExtension, 
				title);
	}
	
	/** {@inheritDoc} */
	@Override
	public void updateDocumentTag(final DocumentTag documentTag, 
			final String name) throws DuplicateEntityFoundException {
		this.documentTagDelegate.update(documentTag, name);
	}
	
	/** {@inheritDoc} 
	 * @throws DuplicateEntityFoundException */
	@Override
	public void updateDocument(final Document document, final String title,
			final Date date) throws DuplicateEntityFoundException {
		this.documentDelegate.update(document, title, date);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeTag(final DocumentTag documentTag) {
		this.documentTagDelegate.removeTag(documentTag);
	}
	
	/** {@inheritDoc} */
	@Override
	public DocumentTag createDocumentTag(final Document document, 
			final String name) throws DuplicateEntityFoundException {
		return this.documentTagDelegate.tagDocument(document, name);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CourtCaseDocumentAssociationSummary> 
		findCourtCaseDocumentAssociationSummariesByOffender(
				final Offender offender) { 
		return this.courtCaseDocumentAssociationSummaryReportService
				.findByOffender(offender); 
	}
}
