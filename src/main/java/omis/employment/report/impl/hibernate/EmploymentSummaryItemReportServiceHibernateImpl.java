package omis.employment.report.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.employment.report.EmploymentSummary;
import omis.employment.report.EmploymentSummaryItemReportService;
import omis.offender.domain.Offender;

/**
 * Employment Summary Item Report Service Hibernate Implementation.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 26, 2017)
 *@since OMIS 3.0
 *
 */
public class EmploymentSummaryItemReportServiceHibernateImpl
		implements EmploymentSummaryItemReportService {
	
	private static final String FIND_LATEST_BY_OFFENDER_QUERY_NAME =
			"findLatestEmploymentSummaryByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;

	/**
	 * @param sessionFactory - Session Factory
	 */
	public EmploymentSummaryItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**{@inheritDoc} */
	@Override
	public EmploymentSummary findLatestEmploymentSummaryByOffender(
			final Offender offender) {
		EmploymentSummary employmentSummary = (EmploymentSummary)
				this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_LATEST_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setMaxResults(1)
				.uniqueResult();
		
		return employmentSummary;
	}

}
