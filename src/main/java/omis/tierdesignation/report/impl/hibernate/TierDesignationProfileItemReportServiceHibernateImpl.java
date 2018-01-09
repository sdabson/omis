package omis.tierdesignation.report.impl.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.tierdesignation.report.TierDesignationProfileItemReportService;

/** Hibernate implementation of tier designation profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 18, 2016)
 * @since OMIS 3.0 */
public class TierDesignationProfileItemReportServiceHibernateImpl 
	implements TierDesignationProfileItemReportService {
	private static final String 
		FIND_TIER_DESIGNATION_COUNT_BY_OFFENDER_AND_DATE_QUERY_NAME
		= "findTierDesignationCountByOffenderAndDate";
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public TierDesignationProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findTierDesignationCountByOffenderAndDate(
			final Offender offender, final Date effectiveDate) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(FIND_TIER_DESIGNATION_COUNT_BY_OFFENDER_AND_DATE_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate);
		return ((Long) q.uniqueResult()).intValue();
	}
}
