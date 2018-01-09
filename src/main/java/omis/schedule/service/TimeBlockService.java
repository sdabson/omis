package omis.schedule.service;

import java.util.Date;
import java.util.List;

import omis.datatype.DateRange;
import omis.person.domain.Person;
import omis.schedule.domain.TimeBlock;

/**
 * Service for time blocks.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (April 6, 2013)
 * @since OMIS 3.0
 */
public interface TimeBlockService {
	
	/**
	 * Returns the time block with the specified ID.
	 * 
	 * @param id ID of time block to find
	 * @return time block with specified ID
	 */
	TimeBlock findById(Long id);
	
	/**
	 * Returns all valid time blocks ordered by sort order.
	 * 
	 * @return all valid time blocks ordered by sort order
	 */
	List<TimeBlock> findAll();
	
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