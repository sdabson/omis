package omis.offender.service.impl;

import omis.offender.domain.Offender;
import omis.offender.service.ExpungeOffenderService;

/**
 * Implementation of service to expunge offenders. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 9, 2014)
 * @since OMIS 3.0
 */
public class ExpungeOffenderServiceImpl
		implements ExpungeOffenderService {

	/**  Instantiates an implementation of service to expunge offenders. */
	public ExpungeOffenderServiceImpl() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public void expunge(final Offender offender) {
		throw new UnsupportedOperationException("Expunging not supported");
	}
}