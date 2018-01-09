package omis.offenderrelationship.report.impl.hibernate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.offender.domain.Offender;
import omis.offenderrelationship.report.OffenderRelationReportService;
import omis.offenderrelationship.report.OffenderRelationSummary;

import org.hibernate.SessionFactory;




/**
 * Offender relation report service implementation.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Jan 12, 2016)
 * @since OMIS 3.0
 */
public class OffenderRelationReportServiceHibernateImpl implements 
	OffenderRelationReportService {
	
	/* Queries */
	private static final String 
		FIND_OFFENDER_RELATION_SUMMARY_BY_OFFENDER_QUERY_NAME 
		= "findOffenderRelationSummaryByOffender";
	
	/* Parameters */
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final  String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	private SessionFactory sessionFactory;
	
	/* Constructors. */
	/**
	 * Instantiates an Hibernate implementation of offender relation. 
	 * report service
	 * @param sessionFactory session factory
	 */
	public OffenderRelationReportServiceHibernateImpl(final SessionFactory
		sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<OffenderRelationSummary> summarizeByOffender(
		final Offender offender, final Date effectiveDate) {
		List<OffenderRelationSummary> summaries
			= new ArrayList<OffenderRelationSummary>();
		@SuppressWarnings("unchecked")
		List<OffenderRelationSummary> internalSummaries = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(
				FIND_OFFENDER_RELATION_SUMMARY_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setParameter(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
			.list();
		summaries.addAll(internalSummaries); 
		return summaries;
	}
}

