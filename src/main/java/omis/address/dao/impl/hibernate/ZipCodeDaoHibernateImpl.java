package omis.address.dao.impl.hibernate;

import java.util.List;

import omis.address.dao.ZipCodeDao;
import omis.address.domain.ZipCode;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.region.domain.City;
import omis.region.domain.State;

import org.hibernate.SessionFactory;

/**
 * Hibernate of implementation of data access object for ZIP codes.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 6, 2013)
 * @since OMIS 3.0
 */
public class ZipCodeDaoHibernateImpl
		extends GenericHibernateDaoImpl<ZipCode>
		implements ZipCodeDao {

	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME = "findAllZipCodes";
	
	private static final String FIND_ZIP_CODES_IN_CITY_QUERY_NAME
					= "findZipCodesInCity";

	private static final String FIND_ZIP_CODES_IN_STATE_QUERY_NAME
					= "findZipCodesInState";
	
	private static final String FIND_QUERY_NAME = "findZipCode";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findZipCodeExcluding";
		
	/* Parameter names. */
	
	private static final String CITY_PARAMETER_NAME = "city";
	
	private static final String STATE_PARAMETER_NAME = "state";
	
	private static final String VALUE_PARAMETER_NAME = "value";
	
	private static final String EXTENSION_PARAMETER_NAME = "extension";
	
	private static final String EXCLUDED_PARAMETER_NAME
		= "excludedZipCodes";
	
	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for ZIP
	 * codes with specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ZipCodeDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ZipCode> findAll() {
		@SuppressWarnings("unchecked")
		List<ZipCode> zipCodes = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return zipCodes;
	}

	/** {@inheritDoc} */
	@Override
	public List<ZipCode> findInCity(City city) {
		@SuppressWarnings("unchecked")
		List<ZipCode> zipCodes = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ZIP_CODES_IN_CITY_QUERY_NAME)
				.setParameter(CITY_PARAMETER_NAME, city).list();
		return zipCodes;
	}

	/** {@inheritDoc} */
	@Override
	public List<ZipCode> findInStates(State state) {
		@SuppressWarnings("unchecked")
		List<ZipCode> zipCodes = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ZIP_CODES_IN_STATE_QUERY_NAME)
				.setParameter(STATE_PARAMETER_NAME, state).list();
		return zipCodes;
	}

	/** {@inheritDoc} */
	@Override
	public ZipCode find(
			final City city,
			final String value,
			final String extension) {
		ZipCode zipCode = (ZipCode) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(CITY_PARAMETER_NAME, city)
				.setParameter(VALUE_PARAMETER_NAME, value)
				.setParameter(EXTENSION_PARAMETER_NAME, extension)
				.uniqueResult();
		return zipCode;
	}

	/** {@inheritDoc} */
	@Override
	public ZipCode findExcluding(
			final City city,
			final String value,
			final String extension,
			final ZipCode... excludedZipCodes) {
		ZipCode zipCode = (ZipCode) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(CITY_PARAMETER_NAME, city)
				.setParameter(VALUE_PARAMETER_NAME, value)
				.setParameter(EXTENSION_PARAMETER_NAME, extension)
				.setParameterList(EXCLUDED_PARAMETER_NAME, excludedZipCodes)
				.uniqueResult();
		return zipCode;
	}
}