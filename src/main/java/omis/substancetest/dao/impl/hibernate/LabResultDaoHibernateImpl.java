package omis.substancetest.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.substance.domain.Substance;
import omis.substancetest.dao.LabResultDao;
import omis.substancetest.domain.LabResult;
import omis.substancetest.domain.SubstanceTest;

import org.hibernate.SessionFactory;

/**
 * Lab Result DAO Hibernate Impl.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jan 2, 2014)
 * @since OMIS 3.0
 */
public class LabResultDaoHibernateImpl 
	extends GenericHibernateDaoImpl<LabResult>
	implements LabResultDao {
	
	/* Query names. */
	
	private static final String FIND_RESULT_QUERY_NAME = "findLabResult";
	
	private static final String FIND_RESULT_EXCLUDING_QUERY_NAME
		= "findLabResultExcluding";
	
	private static final String FIND_RESULTS_BY_TEST_QUERY_NAME
		= "findLabResultBySubstanceTest";
	
	private static final String 
	REMOVE_LAB_RESULTS_BY_TEST_EXCLUDING_QUERY_NAME
		= "removeLabResultBySubstanceTestExcluding";
	
	private static final String REMOVE_LAB_RESULTS_BY_TEST_QUERY_NAME
		= "removeLabResultsBySubstanceTest";
	
	/* Parameter names. */
	
	private static final String SUBSTANCE_TEST_PARAM_NAME = "substanceTest";
	
	private static final String SUBSTANCE_PARAM_NAME = "substance";
	
	private static final String LAB_RESULT_PARAM_NAME = "labResult";
	
	private static final String LAB_RESULTS_PARAM_NAME = "results";

	/**
	 * Instantiates a data access object for crime lab result with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public LabResultDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<LabResult> findBySubstanceTest(
			final SubstanceTest substanceTest) {
		@SuppressWarnings("unchecked")
		List<LabResult> results = (List<LabResult>)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_RESULTS_BY_TEST_QUERY_NAME)
				.setParameter(SUBSTANCE_TEST_PARAM_NAME, substanceTest)
				.list();
		return results;
	}

	/** {@inheritDoc} */
	@Override
	public void removeCrimeLabResultBySubstanceTestExcluding(
			final SubstanceTest substanceTest, 
			final LabResult... excludingResults) {
		this.getSessionFactory().getCurrentSession()
		.getNamedQuery(REMOVE_LAB_RESULTS_BY_TEST_EXCLUDING_QUERY_NAME)
		.setParameter(SUBSTANCE_TEST_PARAM_NAME, substanceTest)
		.setParameterList(LAB_RESULTS_PARAM_NAME, excludingResults)
		.executeUpdate();
	}

	/** {@inheritDoc} */
	@Override
	public LabResult find(final SubstanceTest substanceTest, 
			final Substance substance) {
		LabResult result = (LabResult)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_RESULT_QUERY_NAME)
				.setParameter(SUBSTANCE_TEST_PARAM_NAME, substanceTest)
				.setParameter(SUBSTANCE_PARAM_NAME, substance)
				.uniqueResult();
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public LabResult findExcluding(LabResult result,
			Substance substance, SubstanceTest substanceTest) {
		LabResult crimeLabResult = (LabResult)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_RESULT_EXCLUDING_QUERY_NAME)
				.setParameter(SUBSTANCE_TEST_PARAM_NAME, substanceTest)
				.setParameter(SUBSTANCE_PARAM_NAME, substance)
				.setParameter(LAB_RESULT_PARAM_NAME, result)
				.uniqueResult();
		return crimeLabResult;
	}

	/** {@inheritDoc} */
	@Override
	public void removeResultsBySubstanceTest(
			final SubstanceTest substanceTest) {
		this.getSessionFactory().getCurrentSession()
		.getNamedQuery(REMOVE_LAB_RESULTS_BY_TEST_QUERY_NAME)
		.setParameter(SUBSTANCE_TEST_PARAM_NAME, substanceTest)
		.executeUpdate();
	}
}