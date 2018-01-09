package omis.specialneed.report.impl.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.specialneed.report.SpecialNeedProfileItemReportService;

/** Hibernate implementation of special need profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public class SpecialNeedProfileItemReportServiceHibernateImpl 
	implements SpecialNeedProfileItemReportService {
	private static final String 
		FIND_SPECIAL_NEED_COUNT_BY_OFFENDER_AND_DATE
		= "findSpecialNeedCountByOffenderAndDate";
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String EFFECTIVE_DATE_PARAM_NAME 
		= "effectiveDate";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public SpecialNeedProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** Find special need count by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effective date.
	 * @return count. */
	public Integer findSpecialNeedCountByOffenderAndDate(
			final Offender offender, final Date effectiveDate) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_SPECIAL_NEED_COUNT_BY_OFFENDER_AND_DATE);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate);
		return ((Long) q.uniqueResult()).intValue();
	}
}
