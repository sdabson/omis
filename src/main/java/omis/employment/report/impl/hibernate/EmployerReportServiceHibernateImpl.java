package omis.employment.report.impl.hibernate;

import java.util.List;
import java.util.ArrayList;

import omis.employment.report.EmployerReportService;
import omis.employment.report.EmployerSummary;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of employer report service.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Aug 4, 2015)
 * @since OMIS 3.0
 */
public class EmployerReportServiceHibernateImpl
		implements EmployerReportService {
	/* Queries */
	private static final String FIND_EMPLOYER_SUMMARY_BY_NAME_QUERY_NAME 
		= "findEmployerSummaryByName";
		
	/* Parameters */
	private final static String EMPLOYER_NAME_PARAM_NAME = "employerName";
		
	private SessionFactory sessionFactory;
		
	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of employer report service
	 * @param sessionFactory session factory
	 */

	public EmployerReportServiceHibernateImpl(final SessionFactory
		sessionFactory) {
			this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations. */
	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override 
	public List<EmployerSummary> summarizeByName(final String employerName) {
		List<EmployerSummary> summaries
			= new ArrayList<EmployerSummary>();
		List<EmployerSummary> employerSummaries = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(FIND_EMPLOYER_SUMMARY_BY_NAME_QUERY_NAME)
			.setParameter(EMPLOYER_NAME_PARAM_NAME, employerName)
			.list();
		summaries.addAll(employerSummaries); 
		return summaries;
	}
}