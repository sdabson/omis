package omis.dna.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.dna.dao.DnaSampleDao;
import omis.dna.domain.DnaSample;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of offender dna sample data access object.
 * @author Jason Nelson
 * @author Ryan Johns
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.2 (February 23, 2015)
 * @since OMIS 3.0
 * @see DnaSampleDao
 */
public class DnaSampleHibernateDaoImpl  
	extends GenericHibernateDaoImpl<DnaSample> 
	implements DnaSampleDao {
	
	/* Query names. */
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME
			= "findDnaSamplesByOffender";
	
	private static final String FIND_DNA_SAMPLE_QUERY_NAME
		= "findDnaSample";
	
	private static final String FIND_DNA_SAMPLE_EXCLUDING_QUERY_NAME
		= "findDnaSampleExcluding";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String COLLECTION_EMPLOYEE_PARAM_NAME 
		= "collectionEmployee";
	
	private static final String LOCATION_PARAM_NAME = "location";
	
	private static final String DNA_SAMPLE_PARAM_NAME = "dnaSample";
	
	private static final String DATE_PARAM_NAME = "date";
	
	/**
	 * Instantiates a data access object for offender dna samples with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public DnaSampleHibernateDaoImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<DnaSample> findByOffender(Offender offender) {
		@SuppressWarnings("unchecked")
		List<DnaSample> offenderDnaSampleOlds = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender).list();
		return offenderDnaSampleOlds;
	}

	/** {@inheritDoc} */
	@Override
	public DnaSample find(final Offender offender,
			final String collectionEmployee, final Date date,
			final String location) {
		DnaSample sample = (DnaSample) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_DNA_SAMPLE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(COLLECTION_EMPLOYEE_PARAM_NAME, collectionEmployee)
				.setParameter(LOCATION_PARAM_NAME, location)
				.setDate(DATE_PARAM_NAME, date)
				.uniqueResult();
		return sample;
	}

	/** {@inheritDoc} */
	@Override
	public DnaSample findExcluding(final DnaSample dnaSample, 
			final Offender offender, final String collectionEmployee, 
			final Date date, final String location) {
		DnaSample sample = (DnaSample) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_DNA_SAMPLE_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(COLLECTION_EMPLOYEE_PARAM_NAME, collectionEmployee)
				.setParameter(LOCATION_PARAM_NAME, location)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(DNA_SAMPLE_PARAM_NAME, dnaSample)
				.uniqueResult();
		return sample;
	}
}