package omis.relationship.report.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.relationship.report.RelationshipProfileItemReportService;

/** Hibernate implementation of relationship profile item service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public class RelationshipProfileItemReportServiceHibernateImpl 
	implements RelationshipProfileItemReportService {
	private final static String FIND_COUNT_BY_OFFENDER_QUERY_NAME 
		= "findRelationshipCountByOffender";
	private final static String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public RelationshipProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findRelationshipCountByOffender(final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_COUNT_BY_OFFENDER_QUERY_NAME);
		q.setParameter(OFFENDER_PARAM_NAME, offender);
		return ((Long) q.uniqueResult()).intValue();
	}

}
