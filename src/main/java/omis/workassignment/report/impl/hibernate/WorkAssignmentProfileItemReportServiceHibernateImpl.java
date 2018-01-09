package omis.workassignment.report.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.workassignment.report.WorkAssignmentProfileItemReportService;

/**
 * Hibernate implementation of report service for work assignment profile items.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class WorkAssignmentProfileItemReportServiceHibernateImpl
		implements WorkAssignmentProfileItemReportService {

	/* Query names. */
	
	private static final String COUNT_BY_OFFENDER_ON_DATE_QUERY_NAME
		= "countWorkAssignmentsByOffenderOnDate";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String DATE_PARAM_NAME = "date";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of report service for work
	 * assignment profile items.
	 * 
	 * @param sessionFactory session factory
	 */
	public WorkAssignmentProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public Integer findWorkAssignmentCountForOffenderOnDate(
			final Offender offender, final Date date) {
		Long count = (Long) this.sessionFactory.getCurrentSession()
				.getNamedQuery(COUNT_BY_OFFENDER_ON_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(DATE_PARAM_NAME, date)
				.uniqueResult();
		return count.intValue();
	}
}