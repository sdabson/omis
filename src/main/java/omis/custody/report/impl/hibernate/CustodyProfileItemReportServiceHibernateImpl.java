package omis.custody.report.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.custody.report.CustodyProfileItemReportService;
import omis.offender.domain.Offender;

/** Implementation of custody profile item service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 14, 2016)
 * @since OMIS 3.0 */
public class CustodyProfileItemReportServiceHibernateImpl 
	implements CustodyProfileItemReportService {
	private static final String FIND_COUNT_BY_OFFENDER_QUERY_NAME 
		= "findCustodyReviewCountByOffender";
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public CustodyProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findCustodyCountByOffender(final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_COUNT_BY_OFFENDER_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return ((Long) q.uniqueResult()).intValue();
	}

}
