package omis.health.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.health.domain.IrregularScheduleDay;
import omis.health.domain.ProviderAssignment;

/**
 * Irregular Schedule Day Data Access Object.
 *
 * @author Joel Norris
 * @author Ryan Johns
 * @version 0.1.1 (Jun 5, 2014)
 * @since OMIS 3.0
 */
public interface IrregularScheduleDayDao
	extends GenericDao<IrregularScheduleDay> {

	/**
	 * Returns the irregular schedule day with the specified provider assignment
	 * and specified day.
	 *
	 * @param assignment provider assignment
	 * @param day date day
	 * @return irregular schedule day; {@code null} if no irregular schedule day
	 * found.
	 */
	IrregularScheduleDay find(ProviderAssignment assignment, Date day);

	/** Returns the irregular schedule days with the specified provider
	 * assignment and specified date range.
	 * @param providerAssignment provider assignment.
	 * @param startDate start date.
	 * @param endDate end date.
	 * @return irregular schedules day. */
	List<IrregularScheduleDay> findByProviderAndDateRange(
			ProviderAssignment providerAssignment, Date startDate,
			Date endDate);

}