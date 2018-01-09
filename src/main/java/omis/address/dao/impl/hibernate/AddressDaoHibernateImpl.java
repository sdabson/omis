package omis.address.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.address.dao.AddressDao;
import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Hibernate implementation of data access object for addresses.
 * 
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb 6, 2013)
 * @since OMIS 3.0
 */
public class AddressDaoHibernateImpl
		extends GenericHibernateDaoImpl<Address>
		implements AddressDao {

	/* Queries. */
	
	private static final String FIND_ADDRESS_QUERY_NAME = "findAddress";
	
	private static final String FIND_ADDRESS_EXCLUDING_QUERY_NAME 
		= "findAddressExcluding";
	private static final String FIND_BY_NUMBER_STREET_SUFFIX_QUERY_NAME 
		= "searchAddressByAddressNumberStreetAndSuffix";
	private static final String FIND_BY_ADDRESS_FIELDS_QUERY_NAME 
		= "searchAddressesByAddressFields";
	
	private static final String FIND_BY_VALUE_AND_FIELDS_QUERY_NAME = "searchAddressesByValueAndFields";
	
	private static final String FIND_ADDRESS_BY_VALUE_QUERY_NAME = "findAddressesByValue";
	
	/* Parameters. */
	private static final String CITY_NAME_PARAM_NAME = "cityName";
	
	private static final String STATE_NAME_PARAM_NAME = "stateName";
	
	private static final String STREET_NAME_PARAM_NAME = "streetName";
	
	private static final String NUMBER_PARAMETER_NAME = "streetNumber";
	
	private static final String STREET_SUFFIX_PARAM_NAME = "streetSuffix";
	
	private static final String ZIP_CODE_PARAMETER_NAME = "zipCode";

	private static final String ADDRESS_PARAMETER_NAME = "address";
	
	private static final String VALUE_PARAMETER_NAME = "value";
	
	private static final String CITY_PARAMETER_NAME = "city";
	
	private static final String STATE_PARAMETER_NAME = "state";
	
	/* Property names. */
	
	private static final String ZIP_CODE_PROPERTY_NAME = "zipCode";
	
	private static final String CITY_PROPERTY_NAME = "city";
	
	private static final String STATE_PROPERTY_NAME = "state";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * addresses with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AddressDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public Address find(final String value,
			final ZipCode zipCode) {
		Address address = (Address) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ADDRESS_QUERY_NAME)
				.setParameter(VALUE_PARAMETER_NAME, value)
				.setParameter(ZIP_CODE_PARAMETER_NAME, zipCode)
				.uniqueResult();
		return address;
	}

	/** {@inheritDoc} */
	@Override
	public Address findExcluding(final String value, 
			final ZipCode zipCode, final Address address) {
		Address existingAddress = (Address) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_ADDRESS_EXCLUDING_QUERY_NAME)
				.setParameter(VALUE_PARAMETER_NAME, value)
				.setParameter(ZIP_CODE_PARAMETER_NAME, zipCode)
				.setParameter(ADDRESS_PARAMETER_NAME, address)
				.uniqueResult();
		return existingAddress;
	}
	
	/** {@inheritDoc} */
	@Override
	@Deprecated
	public List<Address> findByAddressFields(
			final String streetNumber, final String streetName, 
			final String streetSuffix) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_NUMBER_STREET_SUFFIX_QUERY_NAME);
		q.setString(NUMBER_PARAMETER_NAME, streetNumber);
		q.setString(STREET_NAME_PARAM_NAME, streetName);
		q.setString(STREET_SUFFIX_PARAM_NAME, streetSuffix);
		return this.cast(q.list());
	}
	
	/** {@inheritDoc} */
	@Override
	@Deprecated
	public List<Address> findByAddressFields(
			final String streetNumber, final String streetName, 
			final String streetSuffix, final String cityName, 
			final String stateName, final String zipCode) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_ADDRESS_FIELDS_QUERY_NAME);
		q.setString(NUMBER_PARAMETER_NAME, streetNumber);
		q.setString(STREET_NAME_PARAM_NAME, streetName);
		q.setString(STREET_SUFFIX_PARAM_NAME, streetSuffix);
		q.setString(CITY_NAME_PARAM_NAME, cityName);
		q.setString(STATE_NAME_PARAM_NAME, stateName);
		q.setString(ZIP_CODE_PARAMETER_NAME, zipCode);
		return this.cast(q.list());
	}
	
	/* Casts to address. */
	private List<Address> cast(final List<?> list) {
		@SuppressWarnings("unchecked")
		List<Address> result = (List<Address>) list;
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public List<Address> findByAddressFields(final String value, final ZipCode zipCode, final City city,
			final State state) {
		@SuppressWarnings("unchecked")
		List<Address> addresses = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_VALUE_AND_FIELDS_QUERY_NAME)
				.setString(VALUE_PARAMETER_NAME, value)
				.setParameter(CITY_PARAMETER_NAME, city, this.getEntityPropertyType(CITY_PROPERTY_NAME))
				.setParameter(STATE_PARAMETER_NAME, state, this.getEntityPropertyType(STATE_PROPERTY_NAME))
				.setParameter(ZIP_CODE_PARAMETER_NAME, zipCode, this.getEntityPropertyType(ZIP_CODE_PROPERTY_NAME))
				.list();
		return addresses;
	}

	/** {@inheritDoc} */
	@Override
	public List<Address> findAddressesByValue(final String value) {
		@SuppressWarnings("unchecked")
		List<Address> addresses = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ADDRESS_BY_VALUE_QUERY_NAME)
				.setString(VALUE_PARAMETER_NAME, value)
				.setMaxResults(50)
				.list();
		return addresses;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Address> findAddressesByValue(final String value, 
			final int maxResults) {
		@SuppressWarnings("unchecked")
		List<Address> addresses = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ADDRESS_BY_VALUE_QUERY_NAME)
				.setString(VALUE_PARAMETER_NAME, value)
				.setMaxResults(maxResults)
				.list();
		return addresses;
	}
}