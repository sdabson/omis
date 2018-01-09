package omis.citation.report.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.citation.report.MisdemeanorCitationProfileItemReportService;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of misdemeanor citation profile item.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 17, 2016)
 * @since OMIS 3.0
 */
public class MisdemeanorCitationProfileItemReportServiceHibernateImpl
	implements MisdemeanorCitationProfileItemReportService {
	
	private static final String 
	FIND_MISDEMEANOR_CITATION_COUNT_BY_CITATIONS_QUERY_NAME 
		= "findMisdemeanorCitationCountByCitations";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor
	 * @param sessionFactory - session factory.
	 */
	public MisdemeanorCitationProfileItemReportServiceHibernateImpl (
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findMisdemeanorCitationCountByCitations(
			final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
			FIND_MISDEMEANOR_CITATION_COUNT_BY_CITATIONS_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return ((Long) q.uniqueResult()).intValue();
	}
}
