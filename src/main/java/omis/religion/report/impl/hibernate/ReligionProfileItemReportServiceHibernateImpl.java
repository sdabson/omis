package omis.religion.report.impl.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.religion.report.ReligionProfileItemReportService;

/** Hibernate implementation of religion profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public class ReligionProfileItemReportServiceHibernateImpl 
	implements ReligionProfileItemReportService {
	private final static String 
		FIND_RELIGIOUS_PREFERENCE_COUNT_BY_OFFENDER_AND_DATE 
			= "findReligiousPreferenceCountByOffenderOnDate";
	private final static String OFFENDER_PARAM_NAME = "offender";
	private final static String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public ReligionProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public Integer findReligiousPreferenceCountByOffenderAndDate(
			final Offender offender, final Date effectiveDate) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_RELIGIOUS_PREFERENCE_COUNT_BY_OFFENDER_AND_DATE);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate);
		return ((Long) q.uniqueResult()).intValue();
	}
}
