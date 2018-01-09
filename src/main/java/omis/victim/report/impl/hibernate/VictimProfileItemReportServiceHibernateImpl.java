package omis.victim.report.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.victim.report.VictimProfileItemReportService;

/** Hibernate implementation of victim profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 21, 2016)
 * @since OMIS 3.0 */
public class VictimProfileItemReportServiceHibernateImpl 
	implements VictimProfileItemReportService {
	private static final String FIND_VICTIM_COUNT_BY_OFFENDER_QUERY_NAME 
		= "findVictimAssociationCountByOffender";
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactor - session factory. */
	public VictimProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) { 
		this.sessionFactory = sessionFactory; 
	}

	/** {@inheritDoc} */
	@Override
	public Integer findVictimCountByOffender(final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_VICTIM_COUNT_BY_OFFENDER_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return ((Long) q.uniqueResult()).intValue();
	}
}
