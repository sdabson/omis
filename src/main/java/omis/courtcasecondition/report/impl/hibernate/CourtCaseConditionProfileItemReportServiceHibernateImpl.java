package omis.courtcasecondition.report.impl.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.courtcasecondition.report.CourtCaseConditionProfileItemReportService;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of court case condition profile item report service.
 * @author Trevor Isles
 * @author Annie Jacques
 * @version 0.1.0 (August 14, 2017)
 * @since OMIS 3.0
 */
public class CourtCaseConditionProfileItemReportServiceHibernateImpl 
	implements CourtCaseConditionProfileItemReportService {
	
	private static final String FIND_COUNT_BY_OFFENDER_QUERY_NAME 
		= "findCourtCaseConditionsCountByOffenderAndEffectiveDate";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * Constructor
	 * @param sessionFactory - session factory.
	 */
	public CourtCaseConditionProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findCourtCaseConditionsByOffenderAndEffectiveDate(
			final Offender offender, final Date date) {
		Query q = this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_COUNT_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(EFFECTIVE_DATE_PARAM_NAME, date);
		return Integer.valueOf(((Long) q.uniqueResult()).intValue());
	}

}
