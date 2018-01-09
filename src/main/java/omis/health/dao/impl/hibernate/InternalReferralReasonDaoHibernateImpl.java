package omis.health.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.InternalReferralReasonDao;
import omis.health.domain.InternalReferralReason;

/**
 * Hibernate implementation of data access object for internal referral reasons.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 12, 2014)
 * @since OMIS 3.0
 */
public class InternalReferralReasonDaoHibernateImpl
		extends GenericHibernateDaoImpl<InternalReferralReason>
		implements InternalReferralReasonDao {

	/* Queries. */
	
	private static final String FIND_ALL_QUERY_NAME
		= "findAllInternalReferralReasons";

	/* Constructors. */
	
	/**
	 * Hibernate implementation of data access object for internal referral
	 * reasons.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public InternalReferralReasonDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<InternalReferralReason> findAll() {
		@SuppressWarnings("unchecked")
		List<InternalReferralReason> reasons = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return reasons;
	}
}