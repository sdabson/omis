package omis.schedule.service.impl;

import java.util.List;

import omis.datatype.DateRange;
import omis.person.domain.Person;
import omis.schedule.dao.EventDao;
import omis.schedule.domain.AttendableEvent;
import omis.schedule.domain.Event;
import omis.schedule.service.EventService;

/**
 * Implementation of service for events.
 * 
 * @author Stephen Abson
 * @version 0.1.3 (April 6, 2013)
 * @since OMIS 3.0
 */
public class EventServiceImpl
		implements EventService {

	private final EventDao eventDao;
	
	/**
	 * Instantiates an implementation of service for events.
	 * 
	 * @param eventDao data access object for events
	 */
	public EventServiceImpl(final EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Event> findAll() {
		return this.eventDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<Event> findWithinDateRange(final DateRange dateRange) {
		return this.eventDao.findWithinDateRange(dateRange);
	}

	/** {@inheritDoc} */
	@Override
	public Event findById(final Long id) {
		return this.eventDao.findById(id, false);
	}

	/** {@inheritDoc} */
	@Override
	public List<AttendableEvent> findWithinDateRangeForAttendees(
			final DateRange dateRange, final Person... attendees) {
		return this.eventDao.findWithinDateRangeForAttendees(dateRange,
				attendees);
	}
}