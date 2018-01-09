package omis.schedule.service.impl;

import java.util.Date;
import java.util.List;

import omis.datatype.DateRange;
import omis.person.domain.Person;
import omis.schedule.dao.TimeBlockDao;
import omis.schedule.domain.TimeBlock;
import omis.schedule.service.TimeBlockService;

/**
 * Implementation of service for time blocks.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (April 6, 2013)
 * @since OMIS 3.0
 */
public class TimeBlockServiceImpl
		implements TimeBlockService {

	private final TimeBlockDao timeBlockDao;
	
	/**
	 * Instantiates an implementation of service for time blocks.
	 * 
	 * @param timeBlockDao data access object for time blocks
	 */
	public TimeBlockServiceImpl(final TimeBlockDao timeBlockDao) {
		this.timeBlockDao = timeBlockDao;
	}

	/** {@inheritDoc} */
	@Override
	public TimeBlock findById(final Long id) {
		return this.timeBlockDao.findById(id, false);
	}

	/** {@inheritDoc} */
	@Override
	public List<TimeBlock> findAll() {
		return this.timeBlockDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<TimeBlock> findFreeTimeBlocksForAttendeesOnDate(
			final Date date, final Person... attendees) {
		return timeBlockDao.findFreeTimeBlocksForAttendeesOnDate(
				date, attendees);
	}

	/** {@inheritDoc} */
	@Override
	public TimeBlock findForTimeRange(final DateRange timeRange) {
		return this.timeBlockDao.findForTimeRange(timeRange);
	}
}