package omis.offender.service;

import omis.offender.domain.Offender;

/**
 * Service to expunge offenders.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Sep 9, 2014)
 * @since OMIS 3.0
 */
public interface ExpungeOffenderService {

	/**
	 * Expunges an offender.
	 * 
	 * @param offender offender to expunge
	 * @throws UnsupportedOperationException if expunging is not supported
	 */
	void expunge(Offender offender);
}