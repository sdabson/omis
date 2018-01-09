package omis.employment.report.impl.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.employment.report.EmploymentProfileItemReportService;
import omis.offender.domain.Offender;

/** Implementation for employment profile item service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 15, 2016)
 * @since OMIS 3.0 */
public class EmploymentProfileItemReportServiceHibernateImpl 
	implements EmploymentProfileItemReportService {
	private static final String FIND_COUNT_BY_OFFENDER_AND_DATE 
		= "findEmploymentTermCountByOffenderAndDate";
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public EmploymentProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findEmploymentCountByOffenderAndDate(
			final Offender offender, final Date effectiveDate) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_COUNT_BY_OFFENDER_AND_DATE);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate);
		return ((Long) q.uniqueResult()).intValue();
	}
}
