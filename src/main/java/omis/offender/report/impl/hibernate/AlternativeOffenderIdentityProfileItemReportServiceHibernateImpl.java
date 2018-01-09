package omis.offender.report.impl.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.offender.report.AlternativeOffenderIdentityProfileItemReportService;

/** Hibernate implementation of alternative offender identity profile item 
 * report service.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 12, 2016)
 * @since OMIS 3.0 */
public class AlternativeOffenderIdentityProfileItemReportServiceHibernateImpl
		implements AlternativeOffenderIdentityProfileItemReportService {
	private final static String 
	FIND_ALTERNATIVE_OFFENDER_IDENTITIES_BY_OFFENDER_AND_DATE_QUERY_NAME
	= "findAlternativeOffenderIdentityCountByOffenderAndDate";
	private final static String OFFENDER_PARAM_NAME = "offender";
	private final static String EFFECTIVE_DATE_PARAM_NAME 
	= "effectiveDate";
	
	private final SessionFactory sessionFactory;

	/** Constructor.
	 * @param sessionFactory - session factory. */
	public AlternativeOffenderIdentityProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findAlternativeOffenderIdentityCountByOffenderAndDate(
			Offender offender, Date effectiveDate) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_ALTERNATIVE_OFFENDER_IDENTITIES_BY_OFFENDER_AND_DATE_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate);
		return ((Long) q.uniqueResult()).intValue();
	}

}
