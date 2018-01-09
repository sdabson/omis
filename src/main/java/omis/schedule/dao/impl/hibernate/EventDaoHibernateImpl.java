package omis.schedule.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.datatype.DateRange;
import omis.person.domain.Person;
import omis.schedule.dao.EventDao;
import omis.schedule.domain.AttendableEvent;
import omis.schedule.domain.Event;

/**
 * Entity configurable Hibernate implementation of data access object for
 * events.
 * 
 * @author Stephen Abson
 * @version 0.1.3 (Feb 5, 2013)
 * @since OMIS 3.0
 * @see EventDao
 */
public class EventDaoHibernateImpl
		extends GenericHibernateDaoImpl<Event>
		implements EventDao {
	
	private static final String START_DATE_PARAM = "startDate";
	
	private static final String END_DATE_PARAM = "endDate";

	/**
	 * Instantiates an Hibernate implementation of data access object for events
	 * with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public EventDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Event> findAll() {
		@SuppressWarnings("unchecked")
		List<Event> events = getSessionFactory().getCurrentSession()
				.getNamedQuery("findOrderedListOfEvents").list();
		return events;
	}

	/** {@inheritDoc} */
	@Override
	public List<Event> findWithinDateRange(final DateRange dateRange) {
		@SuppressWarnings("unchecked")
		List<Event> events = getSessionFactory().getCurrentSession()
				.getNamedQuery("findAttendableEventsWithinDateRange")
				.setParameter(START_DATE_PARAM,
						dateRange.getStartDate().getTime())
				.setParameter(END_DATE_PARAM,
						dateRange.getEndDate().getTime())
				.list();
		return events;
	}

	/** {@inheritDoc} */
	@Override
	public List<AttendableEvent> findWithinDateRangeForAttendees(
			final DateRange dateRange, final Person... attendees) {
		@SuppressWarnings("unchecked")
		List<AttendableEvent> attendableEvents =
				getSessionFactory().getCurrentSession().getNamedQuery(
						"findAttendableEventsForAttendeesWithinDateRange")
				.setParameter(START_DATE_PARAM,
						dateRange.getStartDate().getTime())
				.setParameter(END_DATE_PARAM,
						dateRange.getEndDate().getTime())
				.setParameterList("attendees", attendees)
				.list();
		return attendableEvents;
	}
}