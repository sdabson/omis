package omis.workrestriction.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.workrestriction.report.WorkRestrictionReportService;
import omis.workrestriction.report.WorkRestrictionSummary;

/**
 * WorkRestrictionReportServiceHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
public class WorkRestrictionReportServiceHibernateImpl 
	implements WorkRestrictionReportService {
	
	private static final String 
		FIND_WORK_RESTRICTION_SUMMARIES_BY_OFFENDER_QUERY_NAME
			= "findWorkRestrictionSummariesByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	
	/**
	 * Constructor for work restriction report service hibernate implementation
	 * @param sessionFactory
	 */
	public WorkRestrictionReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**{@inheritDoc} */
	@Override
	public List<WorkRestrictionSummary> summariesByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<WorkRestrictionSummary> summaries = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(FIND_WORK_RESTRICTION_SUMMARIES_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.list();
		return summaries;
	}

}
