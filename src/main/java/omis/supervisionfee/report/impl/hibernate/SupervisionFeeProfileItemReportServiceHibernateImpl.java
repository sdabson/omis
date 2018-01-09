package omis.supervisionfee.report.impl.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.supervisionfee.report.SupervisionFeeProfileItemReportService;

/** Hibernate implementation of supervision fee profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public class SupervisionFeeProfileItemReportServiceHibernateImpl 
	implements SupervisionFeeProfileItemReportService {
	private static final String 
		FIND_SUPERVISION_FEE_COUNT_BY_OFFENDER_AND_DATE_QUERY_NAME 
			= "findSupervisionFeeCountByOffenderAndDate";
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String EFFECTIVE_DATE_PARAM_NAME 
		= "effectiveDate";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public SupervisionFeeProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findSupervisionFeeCountByOffenderAndDate(
				final Offender offender, final Date effectiveDate) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(FIND_SUPERVISION_FEE_COUNT_BY_OFFENDER_AND_DATE_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate);
		return ((Long) q.uniqueResult()).intValue();
	}

}
