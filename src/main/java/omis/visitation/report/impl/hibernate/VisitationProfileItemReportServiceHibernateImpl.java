package omis.visitation.report.impl.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.visitation.report.VisitationProfileItemReportService;

/** Hibernate implementation of visitation profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 21, 2016)
 * @since OMIS 3.0 */
public class VisitationProfileItemReportServiceHibernateImpl 
	implements VisitationProfileItemReportService {
	private static final String 
		FIND_VISITOR_COUNT_BY_OFFENDER_AND_DATE_QUERY_NAME 
			= "findVisitationAssociationCountByOffenderAndDate";
	private static final String 
		FIND_VISIT_COUNT_BY_OFFENDER_QUERY_NAME 
			= "findVisitCountByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String EFFECTIVE_DATE_PARAM_NAME 
		= "effectiveDate";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public VisitationProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findVisitorCountByOffenderAndDate(final Offender offender,
			final Date effectiveDate) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_VISITOR_COUNT_BY_OFFENDER_AND_DATE_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate);
		return ((Long) q.uniqueResult()).intValue();
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findVisitCountByOffender(final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_VISIT_COUNT_BY_OFFENDER_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return ((Long) q.uniqueResult()).intValue();
	}

}
