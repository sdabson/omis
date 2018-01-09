package omis.immigration.dao;

import omis.dao.GenericDao;
import omis.immigration.domain.AlienResidence;
import omis.offender.domain.Offender;

/**
 * Data access object for alien residences.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 28, 2014)
 * @since OMIS 3.0
 */
public interface AlienResidenceDao
		extends GenericDao<AlienResidence> {

	/**
	 * Returns alien residence for offender.
	 * 
	 * @param offender offender
	 * @return alien residence for offender
	 */
	AlienResidence findByOffender(Offender offender);
}