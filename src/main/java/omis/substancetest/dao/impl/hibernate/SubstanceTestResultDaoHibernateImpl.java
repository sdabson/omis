package omis.substancetest.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.substance.domain.Substance;
import omis.substancetest.dao.SubstanceTestResultDao;
import omis.substancetest.domain.SubstanceTest;
import omis.substancetest.domain.SubstanceTestResult;

import org.hibernate.SessionFactory;

/**
 * Substance test result data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.1 (Apr 15, 2015)
 * @since OMIS 3.0
 */
public class SubstanceTestResultDaoHibernateImpl  
	extends GenericHibernateDaoImpl<SubstanceTestResult>
	implements SubstanceTestResultDao {
	 
	/* Query names. */
	
	private static final String FIND_SUBSTANCE_TEST_RESULT_EXCLUDING_QUERY_NAME
		= "findSubstanceTestResultExcluding";
	
	private static final String FIND_SUBSTANCE_TEST_RESULT_QUERY_NAME
		= "findSubstanceTestResult";
	
	private static final String FIND_RESULTS_BY_SUBSTANCE_TEST_QUERY_NAME 
		= "findSubstanceTestResultsBySubstanceTest";
	
	private static final String REMOVE_BY_TEST_EXCLUDING_QUERY_NAME
		= "removeBySubstanceTestExcluding";
	
	/* Parameter names. */
	
	private static final String SUBSTANCE_PARAM_NAME = "substance";
	
	private static final String SUBSTANCE_TEST_PARAM_NAME = "substanceTest";
	
	private static final String SUBSTANCE_TEST_RESULT_PARAM_NAME
		= "substanceTestResult";
	
	private static final String RESULTS_PARAM_NAME = "results";
	
	/**
	 * Instantiates a data access object for substance test result with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SubstanceTestResultDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTestResult find(final SubstanceTest substanceTest,
			final Substance substance) {
		SubstanceTestResult result = (SubstanceTestResult)
				getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_SUBSTANCE_TEST_RESULT_QUERY_NAME)
				.setParameter(SUBSTANCE_TEST_PARAM_NAME, substanceTest)
				.setParameter(SUBSTANCE_PARAM_NAME, substance)
				.uniqueResult();
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public void removeBySubstanceTestExcluding(
			final SubstanceTest substanceTest,
			final SubstanceTestResult... excludingResults) {
		this.getSessionFactory().getCurrentSession()
			.getNamedQuery(REMOVE_BY_TEST_EXCLUDING_QUERY_NAME)
			.setParameter(SUBSTANCE_TEST_PARAM_NAME, substanceTest)
			.setParameterList(RESULTS_PARAM_NAME, excludingResults)
			.executeUpdate();
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTestResult findExcluding(final SubstanceTestResult result,
			final Substance substance, final SubstanceTest substanceTest) {
		SubstanceTestResult substanceTestResult = (SubstanceTestResult)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SUBSTANCE_TEST_RESULT_EXCLUDING_QUERY_NAME)
				.setParameter(SUBSTANCE_TEST_PARAM_NAME, substanceTest)
				.setParameter(SUBSTANCE_PARAM_NAME, substance)
				.setParameter(SUBSTANCE_TEST_RESULT_PARAM_NAME, result)
				.uniqueResult();
		return substanceTestResult;
	}

	/** {@inheritDoc} */
	@Override
	public List<SubstanceTestResult> findBySubstanceTest(
			final SubstanceTest substanceTest) {
		@SuppressWarnings("unchecked")
		List<SubstanceTestResult> results = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_RESULTS_BY_SUBSTANCE_TEST_QUERY_NAME)
				.setParameter(SUBSTANCE_TEST_PARAM_NAME, substanceTest)
				.list();
		return results;
	}	
}