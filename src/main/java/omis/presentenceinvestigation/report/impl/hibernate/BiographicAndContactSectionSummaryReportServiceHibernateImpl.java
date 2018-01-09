package omis.presentenceinvestigation.report.impl.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.presentenceinvestigation.report.BiographicAndContactSectionSummaryReportService;
import omis.presentenceinvestigation.report.BiographicAndContactSummary;
import omis.offender.domain.Offender;

public class 
	BiographicAndContactSectionSummaryReportServiceHibernateImpl 
	implements BiographicAndContactSectionSummaryReportService {
	private static final String FIND_BY_OFFENDER_QUERY_NAME 
		= "findBiographicAndContactSectionSummaryByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor. 
	 * @param sessionFactory - session factory. */
	public BiographicAndContactSectionSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	


	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<BiographicAndContactSummary> findBiographicAndContactSectionSummaryByOffender(Offender offender) {

		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_BY_OFFENDER_QUERY_NAME);
		q.setParameter(OFFENDER_PARAM_NAME, offender);
		
		return (List<BiographicAndContactSummary>) q.list();
	}
}
