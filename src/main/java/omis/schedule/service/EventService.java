package omis.schedule.service;

import java.util.List;

import omis.datatype.DateRange;
import omis.person.domain.Person;
import omis.schedule.domain.AttendableEvent;
import omis.schedule.domain.Event;


/**
 * Service for events.
 * 
 * @author Stephen Abson
 * @version 0.1.3 (April 6, 2013)
 * @since OMIS 3.0
 */
public interface EventService {
	
	/**
	 * Returns all events ordered by start date and then end date.
	 * 
	 * @return events ordered by start date and then end date
	 */
	List<Event> findAll();
	
	/**
	 * Returns events within the specified date range ordered by start date
	 * and then end date.
	 * 
	 * @param dateRange range of dates within which the events to return
	 * @return events within date range ordered by start then end date
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
	
	/**
	 * Finds and returns the event with the specified ID.
	 * 
	 * @param id ID of event to find
	 * @return event with specified ID
	 */
	Event findById(Long id);
}