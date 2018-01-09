package omis.prisonterm.report.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.prisonterm.report.PrisonTermProfileItemReportService;

/**
 * Hibernate implementation of prison term profile item.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (March 18, 2017)
 * @since OMIS 3.0
 */

public class PrisonTermProfileItemReportServiceHibernateImpl 
	implements PrisonTermProfileItemReportService {
	
	private static final String 
	FIND_PRISON_TERM_COUNT_BY_PRISON_TERMS_QUERY_NAME 
		= "findPrisonTermCountByPrisonTerms";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor
	 * @param sessionFactory - session factory.
	 */
	public PrisonTermProfileItemReportServiceHibernateImpl (
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findPrisonTermCountByPrisonTerms(
			final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
			FIND_PRISON_TERM_COUNT_BY_PRISON_TERMS_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return ((Long) q.uniqueResult()).intValue();
	}

}
