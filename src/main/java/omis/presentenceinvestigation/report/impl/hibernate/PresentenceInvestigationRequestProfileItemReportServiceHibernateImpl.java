package omis.presentenceinvestigation.report.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.presentenceinvestigation.report.PresentenceInvestigationRequestProfileItemReportService;

/**
 * PresentenceInvestigationRequestProfileItemReportServiceHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Dec 12, 2016)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationRequestProfileItemReportServiceHibernateImpl
		implements PresentenceInvestigationRequestProfileItemReportService {
	
	private static final String 
		FIND_PRESENTENCE_INVESTIGATION_REQUEST_COUNT_QUERY_NAME =
			"findPresentenceInvestigationRequestCount";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	
	
	
	/**
	 * @param sessionFactory
	 */
	public PresentenceInvestigationRequestProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}




	/**{@inheritDoc} */
	@Override
	public Integer findPresentenceInvestigationRequestCountByOffender(
			final Offender offender) {
		Integer count = ((Long) this.sessionFactory.getCurrentSession()
				.getNamedQuery(
						FIND_PRESENTENCE_INVESTIGATION_REQUEST_COUNT_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult()).intValue();
		
		return count;
	}

}
