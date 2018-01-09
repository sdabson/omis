package omis.courtcasecondition.service.delegate;


import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.condition.domain.Agreement;
import omis.courtcasecondition.dao.CourtCaseAgreementDao;
import omis.courtcasecondition.domain.CourtCaseAgreement;
import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.docket.domain.Docket;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Delegate class for the courtCaseAgreement
 * @author Jonny Santy
 * @author Annie Jacques
 * @version 0.1.2 (Aug 29, 2017)
 * @since OMIS 3.0
 */
public class CourtCaseAgreementDelegate {
	
	private final CourtCaseAgreementDao courtCaseAgreementDao;
	
	private final InstanceFactory<CourtCaseAgreement> 
		courtCaseAgreementInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	public CourtCaseAgreementDelegate(
			final CourtCaseAgreementDao courtCaseAgreementDao,
			final InstanceFactory<CourtCaseAgreement>
				courtCaseAgreementInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.courtCaseAgreementDao = courtCaseAgreementDao;
		this.courtCaseAgreementInstanceFactory = 
				courtCaseAgreementInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** Creates a court case agreement.
	 * @param agreement - agreement.
	 * @param docket - docket
	 * @param acceptedDate - accepted date
	 * @param courtCaseAgreementCategory - category .
	 * @return court case agreement after creation. 
	 * @throws DuplicateEntityFoundException - when court case exists. */
	public CourtCaseAgreement create (
			final Agreement agreement, final Docket docket,
			final Date acceptedDate,
			final CourtCaseAgreementCategory courtCaseAgreementCategory)
//					throws DuplicateEntityFoundException 
	{
//		if (this.courtCaseAgreementDao.find(agreement,
//				courtCaseAgreementCategory, docket) != null) {
//			throw new DuplicateEntityFoundException(
//					"Duplicate court case agreement found");
//		}
		CourtCaseAgreement courtCaseAgreement =this.
				courtCaseAgreementInstanceFactory.createInstance();
		courtCaseAgreement.setAgreement(agreement);
		courtCaseAgreement.setDocket(docket);
		courtCaseAgreement.setCourtCaseAgreementCategory(
				courtCaseAgreementCategory);
		courtCaseAgreement.setAcceptedDate(acceptedDate);
		courtCaseAgreement.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		courtCaseAgreement.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return this.courtCaseAgreementDao.makePersistent(courtCaseAgreement);
	}

	/** Updates court case agreement.
	 * @param courtCaseAgreement - court case agreement to be modified.
	 * @param docket - docket
	 * @param acceptedDate - accepted date
	 * @param courtCaseAgreementCategory - court case agreement category
	 * @return court case agreement after modification. 
	 * @throws DuplicateEntityFoundException - when court case exists. */
	public CourtCaseAgreement update(
			final CourtCaseAgreement courtCaseAgreement,
			final Docket docket, final Date acceptedDate,
			final CourtCaseAgreementCategory courtCaseAgreementCategory)
	
//		throws DuplicateEntityFoundException 
	{
//			if (this.courtCaseAgreementDao.findExcluding(courtCaseAgreement,
//					courtCaseAgreement.getAgreement(),
//					courtCaseAgreementCategory, docket) != null) {
//			throw new DuplicateEntityFoundException(
//					"Duplicate court case agreement found");
//		}
		
		courtCaseAgreement.setCourtCaseAgreementCategory(
				courtCaseAgreementCategory);
		courtCaseAgreement.setDocket(docket);
		courtCaseAgreement.setAcceptedDate(acceptedDate);
		courtCaseAgreement.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return this.courtCaseAgreementDao.makePersistent(courtCaseAgreement);
	}
	
	/** Removes a court case  Agreement.
	 * @param courtCaseAgreement - agreement to remove
	 */
	public void remove(final CourtCaseAgreement courtCaseAgreement) {
		this.courtCaseAgreementDao.makeTransient(courtCaseAgreement);
	}
	
	/**
	 * Returns a list of CourtCaseAgreements by specified Offender
	 * @param offender - Offender
	 * @return List of CourtCaseAgreements by specified Offender
	 */
	public List<CourtCaseAgreement> findByOffender(final Offender offender){
		return this.courtCaseAgreementDao.findByOffender(offender);
	}

}
