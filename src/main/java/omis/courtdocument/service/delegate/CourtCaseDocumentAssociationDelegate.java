package omis.courtdocument.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.courtcase.domain.CourtCase;
import omis.courtdocument.dao.CourtCaseDocumentAssociationDao;
import omis.courtdocument.domain.CourtCaseDocumentAssociation;
import omis.courtdocument.domain.CourtDocumentCategory;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/** Delegate for court case document service related operations.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 18, 2015)
 * @since OMIS 3.0 */
public class CourtCaseDocumentAssociationDelegate {
	/* Exception messages. */
	private static final String COURT_DOCUMENT_ASSOCIATION_EXISTS_MSG  = 
			"Court document exists.";
	
	private final CourtCaseDocumentAssociationDao 
		courtCaseDocumentAssociationDao;
	private final InstanceFactory<CourtCaseDocumentAssociation> 
		courtCaseDocumentAssociationInstanceFactory;
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Constructor.
	 * @param courtCaseDocumentAssociationInstanceFactory - court case document
	 * association instance factory. 
	 * @param courtCaseDocumentAssociationDao - court case document association
	 * dao.
	 * association instance factory. 
	 * @param auditComponentRetriever - audit component retriever. */
	public CourtCaseDocumentAssociationDelegate(
			final InstanceFactory<CourtCaseDocumentAssociation> 
			courtCaseDocumentAssociationInstanceFactory, 
			final CourtCaseDocumentAssociationDao 
			courtCaseDocumentAssociationDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.courtCaseDocumentAssociationInstanceFactory = 
				courtCaseDocumentAssociationInstanceFactory;
		this.courtCaseDocumentAssociationDao = courtCaseDocumentAssociationDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/** Create a court case document.
	 * @param courtCase - court case.
	 * @param document - document.
	 * @param date - date.
	 * @param category - court document category.
	 * @return court document association.
	 * @throws DuplicateEntityFoundException - when court case with given 
	 * document and court case exists. */
	public CourtCaseDocumentAssociation create(final CourtCase courtCase, 
			final Document document, final Date date, 
			final CourtDocumentCategory category) 
					throws DuplicateEntityFoundException {
		if (this.courtCaseDocumentAssociationDao
				.findByDocumentAndCourtCase(document, courtCase).size() > 0) {
			throw new DuplicateEntityFoundException(
					COURT_DOCUMENT_ASSOCIATION_EXISTS_MSG);
		}
		
		final CourtCaseDocumentAssociation courtCaseDocumentAssociation = 
				this.courtCaseDocumentAssociationInstanceFactory
				.createInstance();
		this.populate(courtCaseDocumentAssociation, courtCase, document, date, 
				category);
		courtCaseDocumentAssociation.setCreationSignature(
				this.retrieveCreationSignature());
		courtCaseDocumentAssociation.setUpdateSignature(
				this.retrieveUpdateSignature());
		return this.courtCaseDocumentAssociationDao.makePersistent(
				courtCaseDocumentAssociation);
	}
	
	/** Updates court case document association.
	 * @param courtCaseDocumentAssociation - court case document association.
	 * @param courtCase - court case.
	 * @param document - document.
	 * @param date - date. 
	 * @param courtDocumentCategory - court document category. 
	 * @throws DuplicateEntityFoundException - when court case with given 
	 * document and court case exists. */
	public void update(
			final CourtCaseDocumentAssociation courtCaseDocumentAssociation, 
			final CourtCase courtCase, final Document document, 
			final Date date, 
			final CourtDocumentCategory courtDocumentCategory) 
					throws DuplicateEntityFoundException {
		if (this.courtCaseDocumentAssociationDao
			.findByDocumentAndCourtCaseExcluding(document, courtCase, 
					courtCaseDocumentAssociation).size() > 0) {
			throw new DuplicateEntityFoundException(
					COURT_DOCUMENT_ASSOCIATION_EXISTS_MSG);
			
		}
		courtCaseDocumentAssociation.setCategory(courtDocumentCategory);
		courtCaseDocumentAssociation.setCourtCase(courtCase);
		courtCaseDocumentAssociation.setDate(date);
		courtCaseDocumentAssociation.setDocument(document);
		courtCaseDocumentAssociation.setUpdateSignature(this
				.retrieveUpdateSignature());
	}
	
	/** Removes court document association.
	 * @param courtCaseDocumentAssociation - court case document association. */
	public void remove(
			final CourtCaseDocumentAssociation courtCaseDocumentAssociation) {
		this.courtCaseDocumentAssociationDao.makeTransient(
				courtCaseDocumentAssociation);
	}
	
	/** Finds court document association count by offender.
	 * @param offender - offender.
	 * @return count. */
	public Integer findCourtCaseDocumentAssociationCountByOffender(
			final Offender offender) {
		return this.courtCaseDocumentAssociationDao.findCountByOffender(
				offender);
	}
		
	
	/* populate court document association. */
	private void populate(
			final CourtCaseDocumentAssociation courtCaseDocumentAssociation,
			final CourtCase courtCase, final Document document, final Date date,
			final CourtDocumentCategory category) {
		courtCaseDocumentAssociation.setCourtCase(courtCase);
		courtCaseDocumentAssociation.setDate(date);
		courtCaseDocumentAssociation.setDocument(document);
		courtCaseDocumentAssociation.setCategory(category);
	}
	
	/* Retrieve creation signature. */
	private CreationSignature retrieveCreationSignature() {
		return new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate());
	}
	
	/** Retrieve update signature. */
	private UpdateSignature retrieveUpdateSignature() {
		return new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate());
	}
}
