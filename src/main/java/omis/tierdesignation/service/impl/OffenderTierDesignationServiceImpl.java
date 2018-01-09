package omis.tierdesignation.service.impl;

import java.util.List;

import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.tierdesignation.domain.OffenderTierDesignation;
import omis.tierdesignation.domain.TierChangeReason;
import omis.tierdesignation.domain.TierLevel;
import omis.tierdesignation.domain.TierSource;
import omis.tierdesignation.service.OffenderTierDesignationService;
import omis.tierdesignation.service.delegate.OffenderTierDesignationDelegate;
import omis.tierdesignation.service.delegate.TierChangeReasonDelegate;
import omis.tierdesignation.service.delegate.TierLevelDelegate;
import omis.tierdesignation.service.delegate.TierSourceDelegate;

/**
 * Implementation of service for tier designations.
 * 
 * @author Jason Nelson
 * @author Stephen Abson
 * @version 0.1.2 (Dec 12, 2012)
 * @since OMIS 3.0
 */

public class OffenderTierDesignationServiceImpl
		implements OffenderTierDesignationService {

	private final OffenderTierDesignationDelegate offenderTierDesignationDelegate;
	
	private final TierChangeReasonDelegate tierChangeReasonDelegate;
	
	private final TierLevelDelegate tierLevelDelegate;
	
	private final TierSourceDelegate tierSourceDelegate;
	
	
	/**
	 * Instantiates an implementation of service for offender tier designations.
	 * 
	 * @param offenderTierDesignationDelegate data access object for offender
	 * tier designations
	 * @param offenderTierDesignationInstanceFactory instance factory for
	 * offender tier designations
	 * @param tierChangeReasonDelegate data access object for tier change reasons
	 * @param tierLevelDelegate data access object for tier levels
	 * @param tierSourceDelegate data access object for tier sources
	 * @param auditComponentRetriever audit component retriever
	 */
	public OffenderTierDesignationServiceImpl(
			final OffenderTierDesignationDelegate 
				offenderTierDesignationDelegate,
			final TierChangeReasonDelegate tierChangeReasonDelegate,
			final TierLevelDelegate tierLevelDelegate,
			final TierSourceDelegate tierSourceDelegate) {
		this.offenderTierDesignationDelegate = offenderTierDesignationDelegate;
		this.tierChangeReasonDelegate = tierChangeReasonDelegate;
		this.tierLevelDelegate = tierLevelDelegate;
		this.tierSourceDelegate = tierSourceDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderTierDesignation> findByOffender(
			final Offender offender) {
		return this.offenderTierDesignationDelegate.findByOffender(offender);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(
			final OffenderTierDesignation offenderTierDesignation) {
		this.offenderTierDesignationDelegate.remove(offenderTierDesignation);
	}

	/** {@inheritDoc} */
	@Override
	public List<TierChangeReason> findChangeReasons() {
		return this.tierChangeReasonDelegate.findChangeReasons();
	}

	/** {@inheritDoc} */
	@Override
	public List<TierLevel> findLevels() {
		return this.tierLevelDelegate.findLevels();
	}

	/** {@inheritDoc} */
	@Override
	public List<TierSource> findSources() {
		return this.tierSourceDelegate.findSources();
	}

	/** {@inheritDoc} */
	@Override
	public OffenderTierDesignation save(final Offender offender,
			final TierLevel level, final TierSource source,
			final TierChangeReason changeReason,
			final DateRange dateRange, final String comment)
					 throws DuplicateEntityFoundException {
		return this.offenderTierDesignationDelegate.create(offender, level, 
				source, changeReason, dateRange, comment);
	}

	/** {@inheritDoc} */
	@Override
	public OffenderTierDesignation update(
			final OffenderTierDesignation offenderTierDesignation,
			final TierLevel level, final TierSource source,
			final TierChangeReason changeReason,
			final DateRange dateRange, final String comment)
					 throws DuplicateEntityFoundException {
		return this.offenderTierDesignationDelegate.update(
				offenderTierDesignation, level, source, changeReason, dateRange, 
				comment);
	}
}