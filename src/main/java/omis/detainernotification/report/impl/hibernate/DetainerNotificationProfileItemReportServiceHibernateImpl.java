package omis.detainernotification.report.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.detainernotification.report.DetainerNotificationProfileItemReportService;
import omis.offender.domain.Offender;

/**
 * DetainerNotificationProfileItemReportServiceHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Dec 9, 2016)
 *@since OMIS 3.0
 *
 */
public class DetainerNotificationProfileItemReportServiceHibernateImpl
		implements DetainerNotificationProfileItemReportService {
	
	private static final String FIND_DETAINER_NOTIFICATION_COUNT_QUERY_NAME =
			"findDetainerNotificationCount";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory
	 */
	public DetainerNotificationProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**{@inheritDoc} */
	@Override
	public Integer findDetainerNotificationCountByOffender(
			final Offender offender) {
		Integer count = ((Long) this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_DETAINER_NOTIFICATION_COUNT_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult()).intValue();
		
		return count;
	}

}
