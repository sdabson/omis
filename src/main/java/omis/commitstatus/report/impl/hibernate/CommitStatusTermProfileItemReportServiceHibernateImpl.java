package omis.commitstatus.report.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.commitstatus.domain.CommitStatusTerm;
import omis.commitstatus.report.CommitStatusTermProfileItemReportService;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of report service for commit status term profile 
 * items.
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class CommitStatusTermProfileItemReportServiceHibernateImpl
		implements CommitStatusTermProfileItemReportService {
	
	/* Query names. */
	
	private static final String
	COUNT_BY_PERSON_AND_EFFECTIVE_DATE_QUERY_NAME
		= "countCommitStatusTermForPersonOnDate";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "person";
	
	private static final String DATE_PARAM_NAME = "date";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of report service for
	 * commit status term profile items.
	 * 
	 * @param sessionFactory session factory
	 */
	public CommitStatusTermProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/* Method implementation. */
	
	/** {@inheritDoc} */
	@Override
	public CommitStatusTerm findCommitStatusTermCountByOffenderAndDate(
			final Offender offender, final Date effectiveDate) {
		CommitStatusTerm term = (CommitStatusTerm) this.sessionFactory.getCurrentSession()
				.getNamedQuery(COUNT_BY_PERSON_AND_EFFECTIVE_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(DATE_PARAM_NAME, effectiveDate)
				.uniqueResult();
		return term;
	}
}