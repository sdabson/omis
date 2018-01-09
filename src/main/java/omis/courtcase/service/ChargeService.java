package omis.courtcase.service;

import java.util.Date;
import java.util.List;

import omis.courtcase.domain.Charge;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.exception.ChargeExistsException;
import omis.offense.domain.Offense;


/**
 * Service for charges.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.4 (Aug 15, 2017)
 * @since OMIS 3.0
 */
public interface ChargeService {
	
	/**
	 * Creates a charge.
	 * 
	 * @param courtCase court case
	 * @param offense offense
	 * @param date date
	 * @param fileDate file date
	 * @param counts counts
	 * @return charge new charge
	 * @throws ChargeExistsException if charge already exists
	 */
	Charge create(CourtCase courtCase, Offense offense, Date date,
			Date fileDate, Integer counts)
				throws ChargeExistsException;
	
	/**
	 * Removes charge.
	 * 
	 * @param charge charge to remove
	 */
	void remove(Charge charge);
	
	/**
	 * Returns offenses.
	 * 
	 * @return offenses
	 */
	List<Offense> findOffenses();
	
	/**
	 * Updates a charge.
	 * 
	 * @param charge charge
	 * @param offense offense
	 * @param date date
	 * @param fileDate file date
	 * @param counts counts
	 * @return charge 
	 * @throws ChargeExistsException if charge already exists
	 */
	Charge update(Charge charge, Offense offense, Date date, Date fileDate, 
			Integer counts) throws ChargeExistsException;
}