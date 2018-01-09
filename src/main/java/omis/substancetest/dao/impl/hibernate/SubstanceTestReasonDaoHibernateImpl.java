package omis.substancetest.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.substancetest.dao.SubstanceTestReasonDao;
import omis.substancetest.domain.SubstanceTestReason;

import org.hibernate.SessionFactory;

/**
 * Substance Test Reason DAO Hibernate Impl.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 7, 2013)
 * @since OMIS 3.0
 */
public class SubstanceTestReasonDaoHibernateImpl  
	extends GenericHibernateDaoImpl<SubstanceTestReason>
	implements SubstanceTestReasonDao {
	
	/* Query names. */
	
	private static final String FIND_VALID_REASONS_QUERY_NAME 
		= "findAllValidSubstanceTestReasons";

	/**
	 * Instantiates a data access object for substance test reason with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SubstanceTestReasonDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<SubstanceTestReason> findAll() {
		@SuppressWarnings("unchecked")
		List<SubstanceTestReason> reasons = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_VALID_REASONS_QUERY_NAME)
				.list();
		return reasons;
	}
}