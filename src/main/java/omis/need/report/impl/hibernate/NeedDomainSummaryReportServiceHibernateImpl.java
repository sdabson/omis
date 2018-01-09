package omis.need.report.impl.hibernate;

import java.util.List;

import omis.need.report.NeedDomainSummary;
import omis.need.report.NeedDomainSummaryReportService;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Criminogenic domain summary report service hibernate implementation.
 * @author Joel Norris
 * @version 0.1.0 (Jul 13, 2015)
 * @since OMIS 3.0
 */
public class NeedDomainSummaryReportServiceHibernateImpl
	implements NeedDomainSummaryReportService {

	/* Query names. */
	
	private static final String SUMMARIUZE_DOMAINS_BY_OFFENDER_QUERY_NAME
	= "summarizeNeedDomainsByOffender";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	/* Session factory. */
	 
	private SessionFactory sessionFactory;
	
	/**
	 * Instantiates an instance of need domain summary report
	 * service with the specified session factory.
	 * 
	 * @param sessionFactory session factory
	 */
	public NeedDomainSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<NeedDomainSummary> summarizeDomainsByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<NeedDomainSummary> summaries = 
				this.sessionFactory.getCurrentSession()
				.getNamedQuery(SUMMARIUZE_DOMAINS_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return summaries;
	}
}