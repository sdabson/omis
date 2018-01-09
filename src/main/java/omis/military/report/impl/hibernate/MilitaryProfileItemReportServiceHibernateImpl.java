package omis.military.report.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.military.report.MilitaryProfileItemReportService;
import omis.offender.domain.Offender;

/** Hibernate implementation of military profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public class MilitaryProfileItemReportServiceHibernateImpl 
	implements MilitaryProfileItemReportService {
	private static final String 
		FIND_MILITARY_SERVICE_TERM_BY_OFFENDER_QUERY_NAME 
			= "findMilitaryServiceTermsByOffender";
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public MilitaryProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public boolean findMilitaryServiceTermExistenceByOffender(
			final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_MILITARY_SERVICE_TERM_BY_OFFENDER_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return !q.list().isEmpty();
	}
}
