package omis.court.service.delegate;

import java.util.Date;
import java.util.List;

import omis.court.dao.CourtJudgeAssignmentDao;
import omis.court.domain.Court;
import omis.person.domain.Person;

/** Service delegate for court judge assignment operations.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 18, 2016)
 * @since OMIS 3.0 */
public class CourtJudgeAssignmentDelegate {
	private final CourtJudgeAssignmentDao courtJudgeAssignmentDao;
	/** Constructor.
	 * @param courtJudgeAssignmentDao - court judge assignment dao. */
	public CourtJudgeAssignmentDelegate(
			final CourtJudgeAssignmentDao courtJudgeAssignmentDao) {
		this.courtJudgeAssignmentDao = courtJudgeAssignmentDao;
	}

	/** Finds judges assigned to a court on a specified date.
	 * @param court - court.
	 * @param date - date. 
	 * @return list of judges. */
	public List<Person> findJudgesByCourtOnDate(final Court court, 
			final Date date) {
		return this.courtJudgeAssignmentDao.findJudgesByCourtOnDate(
				court, date);
	}
}
