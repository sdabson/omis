package omis.health.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.IrregularScheduleDayDao;
import omis.health.domain.IrregularScheduleDay;
import omis.health.domain.ProviderAssignment;

import org.hibernate.SessionFactory;

/**
 * Irregular schedule day data access object hibernate implementation.
 *
 * @author Joel Norris
 * @author Ryan Johns
 * @version 0.1.1 (Jun 5, 2014)
 * @since OMIS 3.0
 */
public class IrregularScheduleDayDaoHibernateImpl
	extends GenericHibernateDaoImpl<IrregularScheduleDay>
	implements IrregularScheduleDayDao {

	/* Query names. */

	private static final String FIND_IRREGULAR_SCHEDULE_DAY_QUERY_NAME =
			"findIrregularScheduleDay";

	private static final String
	FIND_IRREGULAR_SCHEDULE_DAYS_BY_PROVIDER_ASSIGNMENT_AND_DATERANGE =
		"findIrregularScheduleDayByProviderAndDateRange";

	/* Parameter names. */

	private static final String DAY_PARAMETER_NAME = "day";

	private static final String END_DATE_PARAM_NAME = "endDate";

	private static final String PROVIDER_ASSIGNMENT_PARAMETER_NAME =
			"providerAssignment";

	private static final String START_DATE_PARAM_NAME = "startDate";

	/**
	 * Instantiates an instance of irregular schedule day data access object
	 * hibernate implementation with the specified session factory and
	 * entity name.
	 *
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public IrregularScheduleDayDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public IrregularScheduleDay find(final ProviderAssignment assignment,
			final Date day) {
		final IrregularScheduleDay irregularScheduleDay = (IrregularScheduleDay)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_IRREGULAR_SCHEDULE_DAY_QUERY_NAME)
				.setDate(DAY_PARAMETER_NAME, day)
				.setParameter(PROVIDER_ASSIGNMENT_PARAMETER_NAME, assignment)
				.uniqueResult();
		return irregularScheduleDay;
	}

	/** {@inheritDoc} */
	@Override
	public List<IrregularScheduleDay> findByProviderAndDateRange(
			final ProviderAssignment providerAssignment,
			final Date startDate, final Date endDate) {
		@SuppressWarnings("unchecked")
		final List<IrregularScheduleDay> irregularScheduleDays =
			(List<IrregularScheduleDay>) this.getSessionFactory()
			.getCurrentSession().getNamedQuery(
			FIND_IRREGULAR_SCHEDULE_DAYS_BY_PROVIDER_ASSIGNMENT_AND_DATERANGE)
			.setParameter(PROVIDER_ASSIGNMENT_PARAMETER_NAME,
					providerAssignment).setDate(START_DATE_PARAM_NAME,
							startDate).setDate(END_DATE_PARAM_NAME, endDate)
							.list();

		return irregularScheduleDays;
	}
}