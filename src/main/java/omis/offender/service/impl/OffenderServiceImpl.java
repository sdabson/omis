package omis.offender.service.impl;

import omis.offender.dao.OffenderDao;
import omis.offender.domain.Offender;
import omis.offender.service.OffenderService;

/**
 * Implementation of service for offenders.
 * 
 * <p>This service will be removed.
 * 
 * @see OffenderService
 * @author Stephen Abson
 * @version 0.1.3 (Jan 31, 2013)
 * @since OMIS 3.0
 */
public class OffenderServiceImpl
		implements OffenderService {

	// DAO should not be used - service does not perform data access operations
	@SuppressWarnings("unused")
	private final OffenderDao offenderDao;
	
	/**
	 * Instantiates an implementation of service for offenders with the
	 * specified resources.
	 * 
	 * @param offenderDao data access object for offenders
	 */
	public OffenderServiceImpl(
			final OffenderDao offenderDao) {
		this.offenderDao = offenderDao;
	}

	/** {@inheritDoc} */
	@Override
	public Offender findByOffenderNumber(final Integer offenderNumber) {
		throw new UnsupportedOperationException("This service will be removed");
	}
}