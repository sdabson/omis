package omis.substancetest.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.substancetest.dao.TestKitDao;
import omis.substancetest.domain.SampleCollectionMethod;
import omis.substancetest.domain.TestKit;

import org.hibernate.SessionFactory;

/**
 * Test kit data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (April 20, 2015)
 * @since OMIS 3.0
 */
public class TestKitDaoHibernateImpl  
	extends GenericHibernateDaoImpl<TestKit>
	implements TestKitDao {

	/* Query names. */
	
	private static final String FIND_KITS_BY_METHOD_QUERY_NAME
		= "findTestKitBySampleCollectionMethod";
	
	/* Parameter names. */
	
	private static final String SAMPLE_COLLECTION_METHOD_PARAM_NAME
		= "sampleCollectionMethod";
	
	/**
	 * Instantiates a data access object for test kit with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public TestKitDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<TestKit> findBySampleCollectionMethod(
			final SampleCollectionMethod sampleCollectionMethod) {
		@SuppressWarnings("unchecked")
		List<TestKit> testKits = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_KITS_BY_METHOD_QUERY_NAME)
				.setParameter(SAMPLE_COLLECTION_METHOD_PARAM_NAME, 
						sampleCollectionMethod)
				.list();
		return testKits;
	}
}