package omis.courtcasecondition.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.condition.report.ConditionSummary;
import omis.courtcasecondition.report.CourtCaseConditionReportService;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderSummary;
import omis.offender.report.delegate.hibernate.OffenderReportDelegate;
import omis.util.StringUtility;

/**
 * Hibernate implementation of report service for court case conditions.
 *
 * @author Trevor Isles
 * @version 0.1.0 (August 3, 2017)
 * @since OMIS 3.0
 */
public class CourtCaseConditionReportServiceHibernateImpl 
		implements CourtCaseConditionReportService {

	/* Query names. */
	
	private static final String SUMMARIZE_BY_OFFENDER_QUERY_NAME 
		= "summarizeCourtCaseConditionsByOffender";
	
	/* Parameters */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	/* Delegates. */
	
	private final OffenderReportDelegate offenderReportDelegate;
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of report service for court case
	 * conditions.
	 * 
	 * @param sessionFactory session factory
	 * @param offenderReportDelegate delegate for offender reports
	 */
	public CourtCaseConditionReportServiceHibernateImpl(
			final SessionFactory sessionFactory,
			final OffenderReportDelegate offenderReportDelegate) {
		this.sessionFactory = sessionFactory;
		this.offenderReportDelegate = offenderReportDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ConditionSummary> summarizeByOffender(
			final Offender offender, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<ConditionSummary> summaries
			= this.sessionFactory.getCurrentSession()
				.getNamedQuery(SUMMARIZE_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderSummary> searchOffenders(final String query) {
		if (StringUtility.isIntegral(query)) {
			return this.offenderReportDelegate.summarizeByOffenderNumber(
					Integer.valueOf(query));
		} else {
			return this.offenderReportDelegate.summarizeByNameQuery(query);
		}
	}

}
