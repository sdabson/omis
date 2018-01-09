package omis.paroleboardmember.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.paroleboardmember.report.ParoleBoardMemberSummary;
import omis.paroleboardmember.report.ParoleBoardMemberSummaryReportService;

/**
 * Hibernate implementation of the parole board member summary report service.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 9, 2017)
 * @since OMIS 3.0
 */
public class ParoleBoardMemberSummaryReportServiceHibernateImpl 
		implements ParoleBoardMemberSummaryReportService {

	/* Queries. */
	private static final String FIND_PAROLE_BOARD_MEMBERS_BY_DATE_QUERY_NAME = 
			"findParoleBoardMembersByDate";
	
	private static final String FIND_PAROLE_BOARD_MEMBERS_BY_DATES_QUERY_NAME = 
			"findParoleBoardMembersByDates";
	
	/* Parameters.*/ 
	private static final String EFFECTIVE_PARAMETER_NAME = "effectiveDate";
	
	private static final String START_PARAMETER_NAME = "startDate";
	
	private static final String END_PARAMETER_NAME = "endDate";
	
	/* Members. */
	private final SessionFactory sessionFactory;
	
	/**
	 * Constructor.
	 *
	 * @param sessionFactory session factory
	 */
	public ParoleBoardMemberSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ParoleBoardMemberSummary> findByDate(final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<ParoleBoardMemberSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_PAROLE_BOARD_MEMBERS_BY_DATE_QUERY_NAME)
				.setTimestamp(EFFECTIVE_PARAMETER_NAME, effectiveDate)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<ParoleBoardMemberSummary> findByDates(final Date startDate, 
			final Date endDate) {
		@SuppressWarnings("unchecked")
		List<ParoleBoardMemberSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_PAROLE_BOARD_MEMBERS_BY_DATES_QUERY_NAME)
				.setTimestamp(START_PARAMETER_NAME, startDate)
				.setTimestamp(END_PARAMETER_NAME, endDate)
				.list();
		return summaries;
	}

}
