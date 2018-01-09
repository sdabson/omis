package omis.courtcasecondition.service.impl;

import java.util.Date;
import java.util.List;
import omis.condition.domain.Agreement;
import omis.condition.domain.AgreementAssociableDocument;
import omis.condition.domain.AgreementCategory;
import omis.condition.domain.AgreementNote;
import omis.condition.domain.Condition;
import omis.condition.service.delegate.AgreementAssociableDocumentDelegate;
import omis.condition.service.delegate.AgreementDelegate;
import omis.condition.service.delegate.AgreementNoteDelegate;
import omis.condition.service.delegate.ConditionDelegate;
import omis.courtcasecondition.domain.CourtCaseAgreement;
import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.courtcasecondition.service.CourtCaseAgreementService;
import omis.courtcasecondition.service.delegate.CourtCaseAgreementCategoryDelegate;
import omis.courtcasecondition.service.delegate.CourtCaseAgreementDelegate;
import omis.docket.domain.Docket;
import omis.docket.service.delegate.DocketDelegate;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;

/**
 * Service for Court Case Agreements Implementation.
 * 
 * @author Jonny Santy
 * @author Annie Wahl
 * @version 0.1.2 (Nov 28, 2017)
 * @since OMIS 3.0
 */
public class CourtCaseAgreementServiceImpl
		implements CourtCaseAgreementService {

	private final AgreementNoteDelegate agreementNoteDelegate;

	private final AgreementDelegate agreementDelegate;
	
	private final AgreementAssociableDocumentDelegate
			agreementAssociableDocumentDelegate;

	private final CourtCaseAgreementDelegate courtCaseAgreementDelegate;

	private final CourtCaseAgreementCategoryDelegate
			courtCaseAgreementCategoryDelegate;

	private final ConditionDelegate conditionDelegate;

	private final DocketDelegate docketDelegate;
	
	private final DocumentDelegate documentDelegate;
	
	private final DocumentTagDelegate documentTagDelegate;
	
	/* Constructor */
	
	/**
	 * @param agreementNoteDelegate - Agreement Note Delegate
	 * @param agreementDelegate - Agreement Delegate
	 * @param agreementAssociableDocumentDelegate - Agreement Associable
	 * Document Delegate
	 * @param courtCaseAgreementDelegate - Court Case Agreement Delegate
	 * @param courtCaseAgreementCategoryDelegate - Court Case Agreement Category
	 * Delegate
	 * @param conditionDelegate - Condition Delegate
	 * @param docketDelegate - Docket Delegate
	 * @param documentDelegate - Document Delegate
	 * @param documentTagDelegate - Document Tag Delegate
	 */
	public CourtCaseAgreementServiceImpl(
			final AgreementNoteDelegate agreementNoteDelegate,
			final AgreementDelegate agreementDelegate,
			final AgreementAssociableDocumentDelegate
				agreementAssociableDocumentDelegate,
			final CourtCaseAgreementDelegate courtCaseAgreementDelegate,
			final CourtCaseAgreementCategoryDelegate
				courtCaseAgreementCategoryDelegate,
			final ConditionDelegate conditionDelegate,
			final DocketDelegate docketDelegate,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate) {
		this.agreementNoteDelegate = agreementNoteDelegate;
		this.agreementDelegate = agreementDelegate;
		this.agreementAssociableDocumentDelegate =
				agreementAssociableDocumentDelegate;
		this.courtCaseAgreementDelegate = courtCaseAgreementDelegate;
		this.courtCaseAgreementCategoryDelegate =
				courtCaseAgreementCategoryDelegate;
		this.conditionDelegate = conditionDelegate;
		this.docketDelegate = docketDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public CourtCaseAgreement createCourtCaseAgreement(
			final Agreement agreement, final Docket docket,
			final Date acceptedDate,
			final CourtCaseAgreementCategory courtCaseAgreementCategory) {
		return this.courtCaseAgreementDelegate.create(agreement, docket,
				acceptedDate, courtCaseAgreementCategory);
	}
	
	/**{@inheritDoc} */
	@Override
	public CourtCaseAgreement updateCourtCaseAgreement(
			final CourtCaseAgreement courtCaseAgreement, final Docket docket,
			final Date acceptedDate,
			final CourtCaseAgreementCategory courtCaseAgreementCategory) {
		return this.courtCaseAgreementDelegate.update(courtCaseAgreement,
				docket, acceptedDate, courtCaseAgreementCategory);
	}

	/** {@inheritDoc} */
	@Override
	public void removeCourtCaseAgreement(
			final CourtCaseAgreement courtCaseAgreement) {
		this.courtCaseAgreementDelegate.remove(courtCaseAgreement);
	}
	
	/**{@inheritDoc} */
	@Override
	public Agreement createAgreement(final Offender offender,
			final Date startDate, final Date endDate, 
			final String description,
			final AgreementCategory category)
					throws DuplicateEntityFoundException {
		return this.agreementDelegate.create(offender, startDate, endDate,
				description, category);
	}
	
	/**{@inheritDoc} */
	@Override
	public Agreement updateAgreement(final Agreement agreement,
			final Date startDate, final Date endDate, 
			final String description,
			final AgreementCategory category)
					throws DuplicateEntityFoundException {
		return this.agreementDelegate.update(agreement, startDate, endDate,
				description, category);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeAgreement(final Agreement agreement) {
		this.agreementDelegate.remove(agreement);

	}

	/** {@inheritDoc} */
	@Override
	public AgreementNote createNote(final Agreement agreement,
			final String description, final Date date)
					throws DuplicateEntityFoundException {
		return this.agreementNoteDelegate.create(date, agreement, description);
	}

	/** {@inheritDoc} */
	@Override
	public AgreementNote updateNote(final AgreementNote agreementNote,
			final String description, final Date date)
					throws DuplicateEntityFoundException {
		return this.agreementNoteDelegate.update(agreementNote, description,
				date);
	}

	/** {@inheritDoc} */
	@Override
	public void removeNote(final AgreementNote agreementNote) {
		this.agreementNoteDelegate.remove(agreementNote);
	}

	/** {@inheritDoc} */
	@Override
	public List<CourtCaseAgreementCategory>
			findAllCourtCaseAgreementCategories() {
		return this.courtCaseAgreementCategoryDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public void removeCondition(final Condition condition) {
		this.conditionDelegate.remove(condition);
	}

	/** {@inheritDoc} */
	@Override
	public List<Condition>
		findConditionsByAgreement(final Agreement agreement) {
		return this.conditionDelegate.findByAgreement(agreement);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Agreement>
		findAgreementsByDocket(final Docket docket) {
		return this.agreementDelegate.findByDocket(docket);
	}

	/**{@inheritDoc} */
	@Override
	public List<Docket> findAllDocketsByOffender(final Offender offender) {
		return this.docketDelegate.findByPerson(offender);
	}

	/**{@inheritDoc} */
	@Override
	public Document createDocument(final Date documentDate,
			final String filename,
			final String fileExtension,
			final String title) throws DuplicateEntityFoundException {
		return this.documentDelegate.create(documentDate, filename,
				fileExtension, title);
	}

	/**{@inheritDoc} */
	@Override
	public Document updateDocument(final Document document,
			final Date documentDate,
			final String title) throws DuplicateEntityFoundException {
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
			final String name)
					throws DuplicateEntityFoundException {
		return this.documentTagDelegate.tagDocument(document, name);
	}

	/**{@inheritDoc} */
	@Override
	public DocumentTag updateDocumentTag(final DocumentTag documentTag,
			final String name)
					throws DuplicateEntityFoundException {
		return this.documentTagDelegate.update(documentTag, name);
	}

	/**{@inheritDoc} */
	@Override
	public void removeDocumentTag(final DocumentTag documentTag) {
		this.documentTagDelegate.removeTag(documentTag);
	}

	/**{@inheritDoc} */
	@Override
	public List<DocumentTag> findDocumentTagsByDocument(
			final Document document) {
		return this.documentTagDelegate.findByDocument(document);
	}

	/**{@inheritDoc} */
	@Override
	public AgreementAssociableDocument createAgreementAssociableDocument(
			final Agreement agreement, final Document document)
					throws DuplicateEntityFoundException {
		return this.agreementAssociableDocumentDelegate.create(
				agreement, document);
	}

	/**{@inheritDoc} */
	@Override
	public AgreementAssociableDocument updateAgreementAssociableDocument(
			final AgreementAssociableDocument agreementAssociableDocument,
			final Agreement agreement,
			final Document document) throws DuplicateEntityFoundException {
		return this.agreementAssociableDocumentDelegate.update(
				agreementAssociableDocument, agreement, document);
	}

	/**{@inheritDoc} */
	@Override
	public void removeAgreementAssociableDocument(
			final AgreementAssociableDocument agreementAssociableDocument) {
		this.agreementAssociableDocumentDelegate.remove(
				agreementAssociableDocument);
	}

	/**{@inheritDoc} */
	@Override
	public List<AgreementAssociableDocument>
		findAgreementAssociableDocumentsByAgreement(final Agreement agreement) {
		return this.agreementAssociableDocumentDelegate
				.findByAgreement(agreement);
	}


}
