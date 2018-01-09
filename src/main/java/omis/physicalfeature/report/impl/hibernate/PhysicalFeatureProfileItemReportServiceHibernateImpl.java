package omis.physicalfeature.report.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.physicalfeature.report.PhysicalFeatureProfileItemReportService;

/** Hibernate implementation of physical feature profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public class PhysicalFeatureProfileItemReportServiceHibernateImpl 
	implements PhysicalFeatureProfileItemReportService {
	private static final String 
		FIND_PHYSICAL_FEATURE_COUNT_BY_OFFENDER_QUERY_NAME
		= "findPhysicalFeatureCountByOffender";
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public PhysicalFeatureProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public Integer findPhysicalFeatureCountByOffender(final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_PHYSICAL_FEATURE_COUNT_BY_OFFENDER_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return ((Long) q.uniqueResult()).intValue();
	}
}
