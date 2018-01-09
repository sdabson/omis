package omis.hearing.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.HearingCategory;
import omis.location.domain.Location;
import omis.offender.domain.Offender;
import omis.staff.domain.StaffAssignment;

/**
 * HearingDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public interface HearingDao extends GenericDao<Hearing> {

	/**
	 * Returns a hearing with specified properties
	 * @param location - Location
	 * @param offender - Offender
	 * @param date - Date
	 * @param officer - StaffAssignment
	 * @param category - HearingCategory
	 * @return hearing with specified properties
	 */
	Hearing find(Location location, Offender offender, Date date,
			StaffAssignment officer, HearingCategory category);
	
	/**
	 * Returns a hearing with specified properties excluding specified hearing
	 * @param location - Location
	 * @param offender - Offender
	 * @param date - Date
	 * @param officer - StaffAssignment 
	 * @param category - HearingCategory
	 * @param hearing - Hearing to exclude
	 * @return hearing with specified properties excluding specified hearing
	 */
	Hearing findExcluding(Location location, Offender offender, Date date,
			StaffAssignment officer, HearingCategory category, Hearing hearing);
	
	/**
	 * Returns a list of Hearings with specified offender
	 * @param offender
	 * @return List of Hearings with specified offender
	 */
	List<Hearing> findByOffender(Offender offender);
	
}
