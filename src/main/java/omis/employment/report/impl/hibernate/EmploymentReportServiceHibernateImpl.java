package omis.employment.report.impl.hibernate;

import java.util.List;
import java.util.ArrayList;

import omis.employment.report.EmploymentSummary;
import omis.employment.report.EmploymentReportService;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of offender employment history report service.
 * 
 * @author Yidong Li
 * @version 0.1.0 (March 12, 2014)
 * @since OMIS 3.0
 */
public class EmploymentReportServiceHibernateImpl
		implements EmploymentReportService {

	/* Queries */
	private static final String FIND_EMPLOYMENT_SUMMARY_BY_OFFENDER_QUERY_NAME 
		= "findEmploymentSummaryByOffender";
	
	/* Parameters */
	private final static String OFFENDER_PARAM_NAME = "offender";
	
	private SessionFactory sessionFactory;
		
	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of offender employment history 
	 * report service
	 * @param sessionFactory session factory
	 */

	public EmploymentReportServiceHibernateImpl(final SessionFactory
		sessionFactory) {
			this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations. */
	/** {@inheritDoc} */
	@Override 
	public List<EmploymentSummary> findByOffender(
		final Offender offender) {
		List<EmploymentSummary> summaries
			= new ArrayList<EmploymentSummary>();
		@SuppressWarnings("unchecked")
		List<EmploymentSummary> internalSummaries = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(FIND_EMPLOYMENT_SUMMARY_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.list();
		summaries.addAll(internalSummaries); 
		return summaries;
	}

}