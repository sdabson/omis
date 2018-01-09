package omis.substancetest.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.substancetest.dao.SubstanceTestResultValueDao;
import omis.substancetest.domain.SubstanceTestResultValue;

import org.hibernate.SessionFactory;

/**
 * Substance Test Result Option DAO Hibernate Impl.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 2, 2013)
 * @since OMIS 3.0
 */
public class SubstanceTestResultValueDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SubstanceTestResultValue> 
	implements SubstanceTestResultValueDao {

	/* Query names. */
	
	private static final String FIND_VALID_RESULT_VALUES_QUERY_NAME
		= "findValidResultValues";
	
	/**
	 * Instantiates a data access object for substance test result with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SubstanceTestResultValueDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<SubstanceTestResultValue> findAll() {
		@SuppressWarnings("unchecked")
		List<SubstanceTestResultValue> options = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_VALID_RESULT_VALUES_QUERY_NAME)
				.list();
		return options;
	}
}