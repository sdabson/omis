package omis.court.service.impl;

import java.util.Date;
import java.util.List;

import omis.court.dao.CourtJudgeAssignmentDao;
import omis.court.domain.Court;
import omis.court.service.JudgeService;
import omis.person.domain.Person;

/**
 * Implementation of service for judges.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 12, 2013)
 * @since OMIS 3.0
 */
public class JudgeServiceImpl
		implements JudgeService {

	private final CourtJudgeAssignmentDao courtJudgeAssignmentDao;
	
	/**
	 * Instantiates a service for judges with the specified resources.
	 * 
	 * @param courtJudgeAssignmentDao data access object for assignment
	 * of judge to court
	 */
	public JudgeServiceImpl(
			final CourtJudgeAssignmentDao courtJudgeAssignmentDao) {
		this.courtJudgeAssignmentDao = courtJudgeAssignmentDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Person> findByCourtOnDate(final Court court, final Date date) {
		return this.courtJudgeAssignmentDao
				.findJudgesByCourtOnDate(court, date);
	}
}