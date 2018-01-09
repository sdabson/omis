package omis.need.report.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.need.report.NeedProfileItemReportService;
import omis.offender.domain.Offender;

/** Hibernate implementation of need profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public class NeedProfileItemReportServiceHibernateImpl 
	implements NeedProfileItemReportService {
	private static final String FIND_COUNT_BY_OFFENDER_QUERY_NAME 
		= "findCasePlanObjectiveCountByOffender";
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public NeedProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findCasePlanObjectiveCountByOffender(
			final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(FIND_COUNT_BY_OFFENDER_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return ((Long) q.uniqueResult()).intValue();
	}

}
