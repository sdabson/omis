package omis.substancetest.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.substancetest.dao.SubstanceTestDao;
import omis.substancetest.domain.SubstanceTest;
import omis.substancetest.domain.SubstanceTestSample;

import org.hibernate.SessionFactory;

/**
 * Substance Test DAO Hibernate Impl.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 7, 2013)
 * @since OMIS 3.0
 */
public class SubstanceTestDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SubstanceTest>
	implements SubstanceTestDao {

	/* Query names. */
	
	private static final String FIND_SUBSTANCE_TEST_QUERY_NAME 
		= "findSubstanceTest";
	
	private static final String FIND_SUBSTANCE_TEST_EXCLUDING_QUERY_NAME
		= "findSubstanceTestExcluding";
	
	/* Parameter names. */
	
	private static final String SAMPLE_PARAM_NAME = "sample";
	
	private static final String SUBSTANCE_TEST_PARAM_NAME = "substanceTest";
	
	/**
	 * Instantiates a data access object for substance test with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SubstanceTestDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTest find(final SubstanceTestSample sample) {
		SubstanceTest test = (SubstanceTest) getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SUBSTANCE_TEST_QUERY_NAME)
				.setParameter(SAMPLE_PARAM_NAME, sample)
				.uniqueResult();
		return test;
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTest findExcluding(SubstanceTest substanceTest,
			SubstanceTestSample sample) {
		SubstanceTest test = (SubstanceTest) getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SUBSTANCE_TEST_EXCLUDING_QUERY_NAME)
				.setParameter(SAMPLE_PARAM_NAME, sample)
				.setParameter(SUBSTANCE_TEST_PARAM_NAME, substanceTest)
				.uniqueResult();
		return test;
	}
}