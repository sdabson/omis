package omis.schedule.dao.impl.hibernate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.datatype.DateRange;
import omis.person.domain.Person;
import omis.schedule.dao.TimeBlockDao;
import omis.schedule.domain.TimeBlock;
import omis.util.DateManipulator;

/**
 * Hibernate entity configurable implementation of data access object for
 * time blocks.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (June 25, 2013)
 * @since OMIS 3.0
 */
public class TimeBlockDaoHibernateImpl
		extends GenericHibernateDaoImpl<TimeBlock>
		implements TimeBlockDao {

	/* Queries. */
	
	private static final String FIND_FOR_TIMES_QUERY_NAME
		= "findTimeBlockForTimes";
	
	private static final String FIND_ALL_QUERY_NAME
		= "findAllValidTimeBlocks";
	
	private static final String FIND_FREE_FOR_ATTENDEES_ON_DATE_QUERY_NAME
		= "findAllValidTimeBlocksFreeForAttendeesOnDate";

	/* Parameters. */
	
	private static final String ATTENDEES_PARAM_NAME = "attendees";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String START_TIME_PARAM_NAME = "startTime";

	private static final String END_TIME_PARAM_NAME = "endTime";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * time blocks.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public TimeBlockDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<TimeBlock> findAll() {
		@SuppressWarnings("unchecked")
		List<TimeBlock> timeBlocks = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return timeBlocks;
	}

	/** {@inheritDoc} */
	@Override
	public List<TimeBlock> findFreeTimeBlocksForAttendeesOnDate(
			final Date date, final Person... attendees) {
		DateManipulator manipulator = new DateManipulator(date);
		manipulator.setToEarliestTimeInDay();
		@SuppressWarnings("unchecked")
		List<TimeBlock> timeBlocks = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_FREE_FOR_ATTENDEES_ON_DATE_QUERY_NAME)
				.setParameterList(ATTENDEES_PARAM_NAME,
						Arrays.asList(attendees))
				.setLong(DATE_PARAM_NAME, manipulator.getDate().getTime())
				.list();
		return timeBlocks;
	}

	/** {@inheritDoc} */
	@Override
	public TimeBlock findForTimeRange(final DateRange timeRange) {
		DateManipulator startMan = new DateManipulator(
				timeRange.getStartDate());
		startMan.setToEarliestTimeInDay();
		long startTime
			= timeRange.getStartDate().getTime() - startMan.getDate().getTime();
		DateManipulator endMan = new DateManipulator(
				timeRange.getEndDate());
		endMan.setToEarliestTimeInDay();
		long endTime
			= timeRange.getEndDate().getTime() - endMan.getDate().getTime();
		TimeBlock timeBlock = (TimeBlock) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_FOR_TIMES_QUERY_NAME)
				.setParameter(START_TIME_PARAM_NAME, startTime)
				.setParameter(END_TIME_PARAM_NAME, endTime).uniqueResult();
		return timeBlock;
	}
}