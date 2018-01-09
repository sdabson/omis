package omis.substancetest.dao.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.substancetest.dao.SampleRequestDao;
import omis.substancetest.domain.SampleRequest;
import omis.substancetest.domain.SubstanceTestSample;

/**
 * Sample request data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 8, 2016)
 * @since OMIS 3.0
 */
public class SampleRequestDaoHibernateImpl
	extends GenericHibernateDaoImpl<SampleRequest>
	implements SampleRequestDao {

	/* Query names. */
	
	private static final String FIND_BY_SAMPLE_QUERY_NAME
		= "findSampleRequestBySample";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findRequestExcluding";
	
	private static final String FIND_UNRESOLVED_BY_OFFENDER_QUERY_NAME
		= "findUnresolvedByOffender";
	
	/* Parameter names. */
	
	private static final String SAMPLE_PARAMETER_NAME = "sample";
	
	private static final String SAMPLE_REQUEST_PARAM_NAME = "request";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String DATE_PARAM_NAME = "date";
	
	/**
	 * Instantiates a sample request data access object with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SampleRequestDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);	
	}

	/** {@inheritDoc} */
	@Override
	public SampleRequest findBySample(final SubstanceTestSample sample) {
		SampleRequest request = (SampleRequest) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_SAMPLE_QUERY_NAME)
				.setParameter(SAMPLE_PARAMETER_NAME, sample)
				.uniqueResult();
		return request;
	}

	/** {@inheritDoc} */
	@Override
	public SampleRequest findExcluding(final SampleRequest request,
			final Offender offender, final Date date) {
		SampleRequest matchingRequest = (SampleRequest) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(SAMPLE_REQUEST_PARAM_NAME, request)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(DATE_PARAM_NAME, date)
				.uniqueResult();
		return matchingRequest;
	}

	/** {@inheritDoc} */
	@Override
	public SampleRequest findUnresolvedByOffender(Offender offender) {
		SampleRequest request = (SampleRequest) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_UNRESOLVED_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult();
		return request;
	}	
}