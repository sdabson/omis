package omis.custody.service.impl;

import java.util.Date;
import java.util.List;

import omis.custody.domain.CustodyChangeReason;
import omis.custody.domain.CustodyLevel;
import omis.custody.domain.CustodyOverride;
import omis.custody.domain.CustodyReview;
import omis.custody.service.CustodyReviewService;
import omis.custody.service.delegate.CustodyChangeReasonDelegate;
import omis.custody.service.delegate.CustodyLevelDelegate;
import omis.custody.service.delegate.CustodyOverrideDelegate;
import omis.custody.service.delegate.CustodyReviewDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;

/**
 * Custody Review Service Implementation.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 08 2013)
 * @since OMIS 3.0
 */
public class CustodyReviewServiceImpl implements CustodyReviewService {

	/* Delegates */
	
	private CustodyReviewDelegate custodyReviewDelegate;
	private CustodyOverrideDelegate custodyOverrideDelegate;
	private CustodyChangeReasonDelegate custodyChangeReasonDelegate;
	private CustodyLevelDelegate custodyLevelDelegate;
	
	/**
	 * Instantiates a custody review service implementation with the specified
	 * delegate.
	 * 
	 * @param custodyReviewDelegate custody review delegate
	 */
	public CustodyReviewServiceImpl(final CustodyReviewDelegate custodyReviewDelegate,
			final CustodyOverrideDelegate custodyOverrideDelegate,
			final CustodyChangeReasonDelegate custodyChangeReasonDelegate,
			final CustodyLevelDelegate custodyLevelDelegate) {
		this.custodyReviewDelegate = custodyReviewDelegate;
		this.custodyOverrideDelegate = custodyOverrideDelegate;
		this.custodyChangeReasonDelegate = custodyChangeReasonDelegate;
		this.custodyLevelDelegate = custodyLevelDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final CustodyReview custodyReview) {
		this.custodyReviewDelegate.remove(custodyReview);
	}

	/** {@inheritDoc} */
	@Override
	public List<CustodyReview> findByOffender(final Offender offender) {
		return this.custodyReviewDelegate.findByOffender(offender);
	}

	/** {@inheritDoc} */
	@Override
	public CustodyReview findByOffenderOnDate(final Offender offender,
			final Date date) {
		return this.custodyReviewDelegate.findByOffenderOnDate(offender, date);
	}

	/** {@inheritDoc} */
	@Override
	public CustodyReview create(final Offender offender, 
			final CustodyLevel custodyLevel,
			final CustodyChangeReason changeReason, final Date actionDate,
			final Date nextReviewDate)
		throws DuplicateEntityFoundException {
		return this.custodyReviewDelegate.create(offender, custodyLevel, 
				changeReason, actionDate, nextReviewDate);
	}

	/** {@inheritDoc} */
	@Override
	public CustodyReview update(final CustodyReview custodyReview, 
			final Offender offender, final CustodyLevel custodyLevel, 
			final CustodyChangeReason changeReason, final Date actionDate, 
			final Date nextReviewDate)
		throws DuplicateEntityFoundException {
		return this.custodyReviewDelegate.update(custodyReview, offender, custodyLevel, 
				changeReason, actionDate, nextReviewDate);
	}
	
	/** {@inheritDoc} */
	@Override
	public CustodyOverride overrideReview(final CustodyReview custodyReview, 
			final CustodyLevel custodyLevel) 
					throws DuplicateEntityFoundException {
		return this.custodyOverrideDelegate.create(custodyReview, custodyLevel);
	}
	
	/** {@inheritDoc} */
	@Override
	public CustodyOverride authorizeOverride(final CustodyOverride custodyOverride) {
		return this.custodyOverrideDelegate.authorize(custodyOverride);
	}

	/** {@inheritDoc} */
	@Override
	public void removeOverride(final CustodyOverride custodyOverride) {
		this.custodyOverrideDelegate.remove(custodyOverride);
	}
	
	/** {@inheritDoc} */
	@Override
	public CustodyOverride findOverrideByReview(final CustodyReview custodyReview) {
		return this.custodyOverrideDelegate.findByReview(custodyReview);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CustodyChangeReason> findCustodyChangeReasons() {
		return this.custodyChangeReasonDelegate.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CustodyLevel> findCustodyLevels() {
		return this.custodyLevelDelegate.findAll();
	}
}