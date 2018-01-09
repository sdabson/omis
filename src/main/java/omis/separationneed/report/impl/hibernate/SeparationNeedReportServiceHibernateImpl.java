package omis.separationneed.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.separationneed.report.SeparationNeedReportService;
import omis.separationneed.report.SeparationNeedSummary;

/**
 * Separation need report service hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sept 26, 2016)
 * @since OMIS 3.0
 */
public class SeparationNeedReportServiceHibernateImpl 
	implements SeparationNeedReportService {
	
	private SessionFactory sessionFactory;
	
	/* Query names */
	
	private static final String
		SUMMARIZE_SEPARATION_NEEDS_BY_OFFENDER_ON_DATE_QUERY_NAME
		= "summarizeSeparationNeedsByOffenderOnDate";
	
	/* Parameter names */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String DATE_PARAM_NAME = "date";
	
	/* Constructors */
	
	/**
	 * Instantiates an instance of separation need report service.
	 * 
	 * @param sessionFactory session factory
	 */
	public SeparationNeedReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations */

	/** {@inheritDoc} */
	@Override
	public List<SeparationNeedSummary> summarizeByOffenderOnDate(
			final Offender offender, final Date date) {
		@SuppressWarnings("unchecked")
		List<SeparationNeedSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(
					SUMMARIZE_SEPARATION_NEEDS_BY_OFFENDER_ON_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(DATE_PARAM_NAME, date)
				.list();
		return summaries;
	}
}