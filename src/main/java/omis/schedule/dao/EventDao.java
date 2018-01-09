package omis.schedule.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.datatype.DateRange;
import omis.person.domain.Person;
import omis.schedule.domain.AttendableEvent;
import omis.schedule.domain.Event;


/**
 * Data access object for events.
 * 
 * @author Stephen Abson
 * @version 0.1.4 (Dec 17, 2012)
 * @since OMIS 3.0
 */
public interface EventDao extends GenericDao<Event> {
	
	/**
	 * Returns all events ordered by start date then end date ordered by start
	 * and then end date.
	 * 
	 * @return all events
	 */
	@Override
	List<Event> findAll();
	
	/**
	 * Returns a list of events scheduled for within the specified date range
	 * ordered by start and then end date.
	 * 
	 * @param dateRange date range the events within which to return
	 * @return events scheduled within date range
	 */
	List<Event> findWithinDateRange(DateRange dateRange);
	
	/**
	 * Returns a list of events scheduled for within the specified date range
	 * attended by the specified attendees ordered by start and then end date.
	 * 
	 * @param dateRange date range the events within which to return
	 * @param attendees attendees of the event
	 * @return events scheduled for within specified date range and attended
	 * by the specified attendees
	 */
	List<AttendableEvent> findWithinDateRangeForAttendees(DateRange dateRange,
			Person... attendees);
}