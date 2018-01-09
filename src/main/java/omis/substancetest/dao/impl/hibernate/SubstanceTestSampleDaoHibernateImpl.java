package omis.substancetest.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.substancetest.dao.SubstanceTestSampleDao;
import omis.substancetest.domain.SampleCollectionMethod;
import omis.substancetest.domain.SubstanceTestSample;

import org.hibernate.SessionFactory;

/**
 * Substance test sample hibernate data access object implementation.
 * 
 * @author Joel Norris
 * @version 0.1.1 (April 14, 2015)
 * @since OMIS 3.0
 */
public class SubstanceTestSampleDaoHibernateImpl  
	extends GenericHibernateDaoImpl<SubstanceTestSample>
	implements SubstanceTestSampleDao {

	/* Query names. */
	
	private static final String FIND_SAMPLES_BY_OFFENDER_QUERY_NAME
		= "findSamplesByoffender";
	
	private static final String FIND_SAMPLE_QUERY_NAME 
		= "findSubstanceTestSample";
	
	private static final String FIND_SAMPLE_EXCLUDING_QUERY_NAME
		= "findSubstanceTestSampleExcluding";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String COLLECTION_METHOD_PARAM_NAME 
		= "collectionMethod";
	
	private static final String COLLECTION_DATE_PARAM_NAME = "collectionDate";
	
	private static final String SUBSTANCE_TEST_SAMPLE_PARAM_NAME = "sample";
	
	/**
	 * Instantiates a data access object for substance test sample with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SubstanceTestSampleDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<SubstanceTestSample> findSamplesByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<SubstanceTestSample> samples = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SAMPLES_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return samples;
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTestSample find(final Offender offender,
			final SampleCollectionMethod collectionMethod, 
			final Date collectionDate) {
		SubstanceTestSample sample = (SubstanceTestSample) getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SAMPLE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(COLLECTION_METHOD_PARAM_NAME, collectionMethod)
				.setTimestamp(COLLECTION_DATE_PARAM_NAME, collectionDate)
				.uniqueResult();
		return sample;
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTestSample findExcluding(final SubstanceTestSample sample,
			final Offender offender, 
			final SampleCollectionMethod collectionMethod,
			final Date collectionDate) {
		SubstanceTestSample substanceTestSample = (SubstanceTestSample) 
				getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SAMPLE_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(COLLECTION_METHOD_PARAM_NAME, collectionMethod)
				.setTimestamp(COLLECTION_DATE_PARAM_NAME, collectionDate)
				.setParameter(SUBSTANCE_TEST_SAMPLE_PARAM_NAME, sample)
				.uniqueResult();
		return substanceTestSample;
	}
}