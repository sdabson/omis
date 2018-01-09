package omis.hearing.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.StaffAttendance;
import omis.staff.domain.StaffAssignment;

/**
 * StaffAttendanceDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public interface StaffAttendanceDao extends GenericDao<StaffAttendance> {
	
	/**
	 * Returns a StaffAttendance with specified properties
	 * @param hearing - Hearing
	 * @param staff - StaffAssignment
	 * @return StaffAttendance with specified properties
	 */
	StaffAttendance find(Hearing hearing, StaffAssignment staff);
	
	/**
	 * Returns a StaffAttendance with specified properties excluding specified
	 * StaffAttendance
	 * @param hearing - Hearing
	 * @param staff - StaffAssignment
	 * @param staffAttendance - StaffAttendance to exclude
	 * @return StaffAttendance with specified properties excluding specified 
	 * StaffAttendance
	 */
	StaffAttendance findExcluding(Hearing hearing, StaffAssignment staff,
			StaffAttendance staffAttendance);
	
	/**
	 * Returns a list of StaffAttendance by specified Hearing
	 * @param hearing - Hearing
	 * @return list of StaffAttendance by specified Hearing
	 */
	List<StaffAttendance> findByHearing(Hearing hearing);
}
