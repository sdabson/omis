package omis.alert.dao;

import java.util.Date;
import java.util.List;

import omis.alert.domain.OffenderAlert;
import omis.dao.GenericDao;
import omis.offender.domain.Offender;

/**
 * Data access object for offender alerts.
 * 
 * @author Jason Nelson
 * @author Stephen Abson
 * @version 0.1.0 (Sep 13, 2012)
 * @since OMIS 3.0
 */
public interface OffenderAlertDao
		extends GenericDao<OffenderAlert> {
	
	/**
	 * Finds and returns a list of offender alerts by offender.
	 * @param offender offender whose alerts to find
	 * @return alerts for specified offender
	 */
	List<OffenderAlert> findByOffender(Offender offender);

	/**
	 * Returns the offender alert with the specified properties.
	 * 
	 * @param offender offender
	 * @param expireDate expire date
	 * @param description description
	 * @return offender alert with specified properties
	 */
	OffenderAlert find(Offender offender, Date expireDate, String description);

	/**
	 * Returns the offender alert with the specified properties excluding
	 * specified alert.
	 * 
	 * @param offender offender
	 * @param expireDate expire date
	 * @param description description
	 * @param excludedOffenderAlert offender alert to exclude
	 * @return offender alert with specified properties
	 */
	OffenderAlert findExcluding(Offender offender, Date expireDate,
			String description, OffenderAlert excludedOffenderAlert);
}