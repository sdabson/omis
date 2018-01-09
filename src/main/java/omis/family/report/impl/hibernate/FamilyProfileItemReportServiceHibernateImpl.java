package omis.family.report.impl.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.family.report.FamilyProfileItemReportService;
import omis.offender.domain.Offender;

/** Hibernate implementation of family profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 15, 2016)
 * @since OMIS 3.0 */
public class FamilyProfileItemReportServiceHibernateImpl 
	implements FamilyProfileItemReportService {
	private static final String FIND_COUNT_BY_OFFENDER_AND_DATE_QUERY_NAME 
		= "findFamilyCountByOffenderAndDate";
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	private static final String OFFENDER_PARAM_NAME = "offender";
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public FamilyProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findFamilyCountByOffenderAndDate(
			final Offender offender, final Date effectiveDate) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_COUNT_BY_OFFENDER_AND_DATE_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate);
		return ((Long) q.uniqueResult()).intValue();
	}

}
