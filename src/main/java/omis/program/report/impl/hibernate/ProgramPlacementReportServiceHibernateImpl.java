package omis.program.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.program.report.ProgramPlacementReportService;
import omis.program.report.ProgramPlacementSummary;

/**
 * Hibernate implementation of report service for program placements.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class ProgramPlacementReportServiceHibernateImpl
		implements ProgramPlacementReportService {

	/* Query names. */
	
	private final static String SUMMARIZE_BY_OFFENDER_QUERY_NAME
		= "summarizeProgramPlacementsByOffender";
	
	/* Parameter names. */
	
	private final static String OFFENDER_PARAM_NAME = "offender";
	
	private final static String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;
	
	/**
	 * Instantiates Hibernate implementation of report service for program
	 * placements.
	 * 
	 * @param sessionFactory session factory
	 */
	public ProgramPlacementReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ProgramPlacementSummary> summarizeByOffender(
			final Offender offender, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<ProgramPlacementSummary> summaries
			= this.sessionFactory.getCurrentSession()
				.getNamedQuery(SUMMARIZE_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.list();
		return summaries;
	}
}