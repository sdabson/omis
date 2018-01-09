package omis.dna.report.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.dna.report.DnaProfileItemReportService;
import omis.offender.domain.Offender;

/** Implementation of dna profile item service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 15, 2016)
 * @since OMIS 3.0 */
public class DnaProfileItemReportServiceHibernateImpl 
	implements DnaProfileItemReportService {
	private static final String FIND_COUNT_BY_OFFENDER_QUERY_NAME
		= "findDnaSampleCountByOffender";
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public DnaProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public Integer findDnaCountByOffender(final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_COUNT_BY_OFFENDER_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return ((Long) q.uniqueResult()).intValue();
	}
}
