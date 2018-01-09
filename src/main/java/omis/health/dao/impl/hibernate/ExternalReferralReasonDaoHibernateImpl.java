package omis.health.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.ExternalReferralReasonDao;
import omis.health.domain.ExternalReferralReason;

/**
 * Hibernate implementation of data access object for external referral reasons.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 7, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralReasonDaoHibernateImpl
		extends GenericHibernateDaoImpl<ExternalReferralReason>
		implements ExternalReferralReasonDao {

	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME
		= "findExternalReferralReasons";

	/* Constructor. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * external referral reasons.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ExternalReferralReasonDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<ExternalReferralReason> findAll() {
		@SuppressWarnings("unchecked")
		List<ExternalReferralReason> reasons = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return reasons;
	}
}
