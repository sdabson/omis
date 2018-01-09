package omis.court.dao;

import java.util.Date;
import java.util.List;

import omis.court.domain.Court;
import omis.court.domain.CourtJudgeAssignment;
import omis.dao.GenericDao;
import omis.person.domain.Person;

/**
 * Data access object for assignment of judge to court.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 3, 2013)
 * @since OMIS 3.0
 */
public interface CourtJudgeAssignmentDao
		extends GenericDao<CourtJudgeAssignment> {

	/**
	 * Returns judges assigned to a court on the specified date.
	 * 
	 * @param court court the assigned judges of which to return
	 * @param date date
	 * @return judges assigned to court on date
	 */
	List<Person> findJudgesByCourtOnDate(Court court, Date date);
}