package omis.substancetest.report.impl.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.substancetest.report.SubstanceTestProfileItemReportService;

/** Hibernate implementation of substance test profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public class SubstanceTestProfileItemReportServiceHibernateImpl 
	implements SubstanceTestProfileItemReportService {
	private final String FIND_LAST_SUBSTANCE_TEST_DATE_BY_OFFENDER_QUERY_NAME 
		= "findLastSubstanceTestDateByOffender";
	private final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public SubstanceTestProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) { 
		this.sessionFactory = sessionFactory; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Date findLastSubstanceTestDateByOffender(final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_LAST_SUBSTANCE_TEST_DATE_BY_OFFENDER_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return (Date) q.uniqueResult();
	}

}
