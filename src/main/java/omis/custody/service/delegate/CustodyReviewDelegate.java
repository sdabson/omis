package omis.custody.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.custody.dao.CustodyReviewDao;
import omis.custody.domain.CustodyChangeReason;
import omis.custody.domain.CustodyLevel;
import omis.custody.domain.CustodyReview;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Custody Review Delegate
 * 
 * @author Josh Divine 
 * @version 0.1.0 (December 2, 2016)
 * @since OMIS 3.0
 */
public class CustodyReviewDelegate {

	/* Data Access Objects */
	
	private CustodyReviewDao custodyReviewDao;
	
	/* Instance Factories */
	
	private final InstanceFactory<CustodyReview> custodyReviewInstanceFactory;
	
	/* Audit Component Retriever */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates a custody review service implementation with the specified
	 * data access object.
	 * 
	 * @param custodyReviewDao custody review DAO
	 * @param custodyReviewInstanceFactory custody review instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public CustodyReviewDelegate(final CustodyReviewDao custodyReviewDao,
			final InstanceFactory<CustodyReview> custodyReviewInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.custodyReviewDao = custodyReviewDao;
		this.custodyReviewInstanceFactory = custodyReviewInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Removes the specified custody review
	 * @param custodyReview custody review
	 */
	public void remove(final CustodyReview custodyReview) {
		this.custodyReviewDao.makeTransient(custodyReview);
	}

	/**
	 * Find all custody reviews
	 * @return list of custody reviews
	 */
	public List<CustodyReview> findAll() {
		return this.custodyReviewDao.findAll();
	}

	/**
	 * Find all custody reviews for the specified offender
	 * @param offender offender
	 * @return list of custody reviews
	 */
	public List<CustodyReview> findByOffender(final Offender offender) {
		return this.custodyReviewDao.findByOffender(offender);
	}

	/**
	 * Find custody review for the specified offender and date
	 * @param offender offender
	 * @param date date
	 * @return custody review
	 */
	public CustodyReview findByOffenderOnDate(final Offender offender,
			final Date date) {
		return this.custodyReviewDao.findByOffenderOnDate(offender, date);
	}

	/**
	 * Creates a new custody review
	 * @param offender offender
	 * @param custodyLevel custody level
	 * @param changeReason custody change reason
	 * @param actionDate action date
	 * @param nextReviewDate next review date
	 * @return custody review
	 * @throws DuplicateEntityFoundException
	 */
	public CustodyReview create(final Offender offender, 
			final CustodyLevel custodyLevel,
			final CustodyChangeReason changeReason, final Date actionDate,
			final Date nextReviewDate)
		throws DuplicateEntityFoundException {
		if (this.custodyReviewDao.find(offender, 
				actionDate) != null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		CustodyReview custodyReview = this.custodyReviewInstanceFactory
				.createInstance();
		custodyReview.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		populateCustodyReview(custodyReview, offender, custodyLevel, 
				changeReason, actionDate, nextReviewDate);
		return this.custodyReviewDao.makePersistent(custodyReview);
	}

	/**
	 * Updates the specified custody review
	 * @param custodyReview custody review
	 * @param offender offender
	 * @param custodyLevel custody level
	 * @param changeReason custody change reason
	 * @param actionDate action date
	 * @param nextReviewDate next review date
	 * @return custody review
	 * @throws DuplicateEntityFoundException
	 */
	public CustodyReview update(final CustodyReview custodyReview, 
			final Offender offender, final CustodyLevel custodyLevel, 
			final CustodyChangeReason changeReason, final Date actionDate, 
			final Date nextReviewDate)
		throws DuplicateEntityFoundException {
		if (this.custodyReviewDao.findExcluding(offender, actionDate, 
				custodyReview) != null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		populateCustodyReview(custodyReview, offender, custodyLevel, 
				changeReason, actionDate, nextReviewDate);
		return this.custodyReviewDao.makePersistent(custodyReview);
	}
	
	/**
	 * Finds the custody review for the specified offender, custody level, 
	 * change reason and action date
	 * 
	 * @param offender offender
	 * @param custodyLevel custody level
	 * @param changeReason custody change reason
	 * @param actionDate action date
	 * @return custody review
	 */
	public CustodyReview find(final Offender offender, 
			final CustodyLevel custodyLevel,
			final CustodyChangeReason changeReason, final Date actionDate) {
		return this.custodyReviewDao.find(offender, actionDate);
	}
	
	/*
	 * Populates a custody review with all of the values expected for a 
	 * complete entity except creation signature.
	 */
	private void populateCustodyReview(final CustodyReview custodyReview,
			final Offender offender, final CustodyLevel custodyLevel, 
			final CustodyChangeReason changeReason, final Date actionDate,
			final Date nextReviewDate) {
		custodyReview.setOffender(offender);
		custodyReview.setCustodyLevel(custodyLevel);
		custodyReview.setChangeReason(changeReason);
		custodyReview.setActionDate(actionDate);
		custodyReview.setNextReviewDate(nextReviewDate);
		custodyReview.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
	}
}
