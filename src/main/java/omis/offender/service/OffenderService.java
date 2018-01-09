package omis.offender.service;

import omis.offender.domain.Offender;

/**
 * Service for offenders.
 * 
 * <p>This service will be removed.
 * 
 * @author Stephen Abson
 * @version 0.2.0 (Jan 7, 2016)
 * @since OMIS 3.0
 */
public interface OffenderService {
	
	/**
	 * Throws {@code UnsupportedOperationException}.
	 * 
	 * @param offenderNumber offender number of offender to return
	 * @return does not return
	 */
	Offender findByOffenderNumber(Integer offenderNumber);
}