package omis.substance.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.substance.dao.SubstanceDao;
import omis.substance.domain.Substance;

import org.hibernate.SessionFactory;

/**
 * Substance data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 19, 2013)
 * @since OMIS 3.0
 */
public class SubstanceDaoHibernateImpl 
	extends GenericHibernateDaoImpl<Substance> 
	implements SubstanceDao {

	/* Query names. */
	
	private static final String FIND_VALID_QUERY_NAME = "findValidSubstances";
	
	private static final String FIND_TESTABLE_QUERY_NAME
		= "findTestableSubstances";
	
	/**
	 * Instantiates a data access object for substance with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	SubstanceDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<Substance> findValid() {
		@SuppressWarnings("unchecked")
		List<Substance> substances = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_VALID_QUERY_NAME)
				.list();
		return substances;
	}

	/** {@inheritDoc} */
	@Override
	public List<Substance> findTestable() {
		@SuppressWarnings("unchecked")
		List<Substance> substances = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_TESTABLE_QUERY_NAME)
				.list();
		return substances;
	}
}