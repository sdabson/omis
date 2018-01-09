package omis.schedule.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.datatype.DateRange;
import omis.person.domain.Person;
import omis.schedule.domain.TimeBlock;

/**
 * Data access object for time blocks.
 * 
 * @author Stephen Abson
 * @version 0.1.3 (June 25, 2013)
 * @since OMIS 3.0
 */
public interface TimeBlockDao
		extends GenericDao<TimeBlock> {

	/**
	 * Returns all time blocks which are free for the specified attendees
	 * on the date.
	 * 
	 * @param date date
	 * @param attendees attendees
	 * @return free time blocks for attendees on date
	 */
	List<TimeBlock> findFreeTimeBlocksForAttendeesOnDate(
			Date date, Person... attendees);
	
	/**
	 * Returns a the time block exactly matching the time part of the specified
	 * date range.
	 * 
	 * <p>The date parts of the time range will be ignored.
	 * 
	 * @param timeRange time range
	 * @return time block matching exactly time range
	 */
	TimeBlock findForTimeRange(DateRange timeRange);
}