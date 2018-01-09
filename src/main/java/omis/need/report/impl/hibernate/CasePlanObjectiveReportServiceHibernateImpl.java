package omis.need.report.impl.hibernate;

import java.util.List;

import omis.need.domain.NeedDomain;
import omis.need.report.CasePlanObjectiveReportService;
import omis.need.report.CasePlanObjectiveSummary;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Case plan objective report service hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 10, 2015)
 * @since OMIS 3.0
 */
public class CasePlanObjectiveReportServiceHibernateImpl
	implements CasePlanObjectiveReportService {
	
	/* Session factory. */
	
	private final SessionFactory sessionFactory;
	
	/* Parameter names. */
	
	private final String OFFENDER_PARAM_NAME = "offender";
	
	private final String NEED_DOMAIN_PARAM_NAME = "needDomain";
	
	/* Query names. */
	
	private static final String FIND_CASE_PLAN_OBJECTIVES_BY_DOMAIN_QUERY_NAME
		= "findCasePlanObjectivesByDomain";
	
	private static final String FIND_CASE_PLAN_OBJECTIVES_BY_OFFENDER_QUERY_NAME
		= "findCasePlanObjectivesByOffender";
	
	/**
	 * Instantiates a case plan objective report service with the specified
	 * session factory.
	 * 
	 * @param sessionFactory session factory
	 */
	public CasePlanObjectiveReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<CasePlanObjectiveSummary> findCasePlanObjectiveSummariesByDomain(
			final Offender offender, 
			final NeedDomain needDomain) {
		@SuppressWarnings("unchecked")
		List<CasePlanObjectiveSummary> summaries = 
				this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_CASE_PLAN_OBJECTIVES_BY_DOMAIN_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(
						NEED_DOMAIN_PARAM_NAME, needDomain)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<CasePlanObjectiveSummary> 
			findCasePlanObjectiveSummariesByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<CasePlanObjectiveSummary> summaries = 
				this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_CASE_PLAN_OBJECTIVES_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return summaries;
	}
}