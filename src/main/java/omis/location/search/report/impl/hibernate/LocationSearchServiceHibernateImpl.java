package omis.location.search.report.impl.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import omis.address.dao.StreetSuffixDao;
import omis.address.domain.StreetSuffix;
import omis.address.util.AddressMatcher;
import omis.location.search.report.LocationSearchResult;
import omis.location.search.report.LocationSearchService;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** Hibernate implementation of location search service.
 * @author Ryan Johns
 * @version 0.1.0 (Aug 18, 2015)
 * @since OMIS 3.0 */
public class LocationSearchServiceHibernateImpl implements
		LocationSearchService {
	private static final String 
			FIND_BY_ADDRESS_NUMBER_STREET_SUFFIX_QUERY_NAME =
			"findLocationSearchResultsByAddressNumberStreetAndSuffix";
	private static final String FIND_BY_ADDRESS_FIELDS_QUERY_NAME =
			"findLocationSearchResultsByAddressFields";
	private static final String FIND_BY_ORGANIZATION_NAME_QUERY_NAME =
			"findLocationSearchResultsByOrganizationName";
	private static final String STREET_NUMBER_PARAM_NAME = "streetNumber";
	private static final String STREET_NAME_PARAM_NAME = "streetName";
	private static final String STREET_SUFFIX_PARAM_NAME = "streetSuffix";
	private static final String CITY_NAME_PARAM_NAME = "cityName";
	private static final String STATE_NAME_PARAM_NAME = "stateName";
	private static final String ZIP_CODE_PARAM_NAME = "zipCode";
	private static final String ORGANIZATION_NAME_PARAM_NAME = 
			"organizationName";
	private final SessionFactory sessionFactory;
	private final StreetSuffixDao streetSuffixDao;
	
	/** Constructor.
	 * @param sessionFactory - session factory. 
	 * @param streetSuffixDao - street suffix dao. */
	public LocationSearchServiceHibernateImpl(
			final SessionFactory sessionFactory, 
			final StreetSuffixDao streetSuffixDao) {
		this.sessionFactory = sessionFactory;
		this.streetSuffixDao = streetSuffixDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LocationSearchResult> findByAddressFields(
			final String streetNumber, final String streetName, 
			final String streetSuffix) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_BY_ADDRESS_NUMBER_STREET_SUFFIX_QUERY_NAME);
		q.setParameter(STREET_NUMBER_PARAM_NAME, streetNumber);
		q.setParameter(STREET_NAME_PARAM_NAME, streetName);
		q.setParameter(STREET_SUFFIX_PARAM_NAME, streetSuffix);
		
		return this.cast(q.list());
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LocationSearchResult> findByAddressFields(
			final String streetNumber, final String streetName,
			final String streetSuffix, final String cityName,
			final String stateName, final String zipCode) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_BY_ADDRESS_FIELDS_QUERY_NAME);
		q.setParameter(STREET_NUMBER_PARAM_NAME, streetNumber);
		q.setParameter(STREET_NAME_PARAM_NAME, streetName);
		q.setParameter(STREET_SUFFIX_PARAM_NAME, streetSuffix);
		q.setParameter(CITY_NAME_PARAM_NAME, cityName);
		q.setParameter(STATE_NAME_PARAM_NAME, stateName);
		q.setParameter(ZIP_CODE_PARAM_NAME, zipCode);
		
		return this.cast(q.list());
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LocationSearchResult> findByOrganizationName(
			final String organizationName) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_BY_ORGANIZATION_NAME_QUERY_NAME);
		q.setParameter(ORGANIZATION_NAME_PARAM_NAME, organizationName);
		return this.cast(q.list());
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LocationSearchResult> findByUnspecified(
			final String unspecified) {
		AddressMatcher addressMatcher = new AddressMatcher(
				this.suffixSearchTerms(this.streetSuffixDao.findAll()));
		final List<LocationSearchResult> results;		
		if (addressMatcher.find(unspecified)) {
			final String streetNumber = 
					addressMatcher.parse(AddressMatcher.STREET_NUMBER);
			final String streetName =
					addressMatcher.parse(AddressMatcher.STREET_NAME);
			final String streetSuffix =
					addressMatcher.parse(AddressMatcher.STREET_SUFFIX);
			final String cityName =
					addressMatcher.parse(AddressMatcher.CITY);
			final String stateName =
					addressMatcher.parse(AddressMatcher.STATE);
			final String zipCode =
					addressMatcher.parse(AddressMatcher.ZIP_CODE);
			if ("".equals(
					 addressMatcher.parse(AddressMatcher.LINE_TWO))) {
				results = this.findByAddressFields(streetNumber, streetName, 
						streetSuffix);
			} else {
				results = this.findByAddressFields(streetNumber, streetName,
						streetSuffix, cityName, stateName, zipCode);
			}
		} else {
			results = this.findByOrganizationName(unspecified);
		}
		return results;
	}
	
	/* Cast list. */
	private List<LocationSearchResult> cast(final List<?> list) {
		@SuppressWarnings("unchecked")
		List<LocationSearchResult> result = (List<LocationSearchResult>) list;
		return result;
	}
	
	private List<String> suffixSearchTerms(
			final List<StreetSuffix> streetSuffixList) {
		final List<String> result = new ArrayList<String>();
		Iterator<StreetSuffix> suffixIterator = 
				streetSuffixList.iterator();
		while (suffixIterator.hasNext()) {
			StreetSuffix ss = suffixIterator.next();
			result.add(ss.getAbbreviation());
			result.add(ss.getName());
		}
		return result;
	}

}
