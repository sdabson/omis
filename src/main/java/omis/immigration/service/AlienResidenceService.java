package omis.immigration.service;

import java.util.Date;

import omis.immigration.domain.AlienResidence;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;

/**
 * Service for alien residences.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 3, 2014)
 * @since OMIS 3.0
 */
public interface AlienResidenceService {

	/**
	 * Sets the legality of the residence of the offender.
	 * 
	 * @param offender offender
	 * @param legal whether residence of offender is legal
	 * @param userAccount user account
	 * @param date date
	 */
	void setLegalResidenceStatus(Offender offender, Boolean legal,
			UserAccount userAccount, Date date);
	
	/**
	 * Returns alien residence for offender.
	 * 
	 * @param offender offender
	 * @return alien residence for offender
	 */
	AlienResidence getAlienResidence(Offender offender);
	
	/**
	 * Removes alien residence.
	 * 
	 * @param alienResidence alien residence to remove
	 */
	void remove(AlienResidence alienResidence);
}