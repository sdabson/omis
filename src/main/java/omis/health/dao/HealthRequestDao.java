package omis.health.dao;

import java.util.Date;

import omis.dao.GenericDao;
import omis.facility.domain.Facility;
import omis.health.domain.HealthRequest;
import omis.health.domain.HealthRequestCategory;
import omis.health.domain.HealthRequestStatus;
import omis.offender.domain.Offender;

/**
 * Data access object for health requests.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 14, 2014)
 * @since OMIS 3.0
 */
public interface HealthRequestDao
		extends GenericDao<HealthRequest> {

	/**
	 * Returns the health request.
	 * 
	 * @param offender offender
	 * @param facility facility
	 * @param date date
	 * @param category category
	 * @param status status
	 * @return health request; {@code null} if no such health request exists
	 */
	HealthRequest find(Offender offender, Facility facility, Date date,
			HealthRequestCategory category, HealthRequestStatus status);
	
	/**
	 * Returns the health request if not the excluded health request.
	 * 
	 * @param offender offender
	 * @param facility facility
	 * @param date date
	 * @param category category
	 * @param status status
	 * @param excludedHealthRequest health request to exclude
	 * @return health request; {@code null} if no such health request exists
	 */
	HealthRequest findExcluding(Offender offender, Facility facility, Date date,
			HealthRequestCategory category, HealthRequestStatus status,
			HealthRequest excludedHealthRequest);
}