package omis.grievance.report.impl.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.grievance.report.GrievanceProfileItemReportService;
import omis.offender.domain.Offender;

/** Hibernate implementation of grievance profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public class GrievanceProfileItemReportServiceHibernateImpl 
	implements GrievanceProfileItemReportService {
	private static final String FIND_COUNT_BY_OFFENDER_AND_DATE_QUERY_NAME 
		= "findGrievanceCountByOffenderAndDate";
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public GrievanceProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public Integer findGrievanceCountByOffenderAndDate(
			final Offender offender, final Date effectiveDate) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(FIND_COUNT_BY_OFFENDER_AND_DATE_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate);
		return ((Long) q.uniqueResult()).intValue();
	}
}
