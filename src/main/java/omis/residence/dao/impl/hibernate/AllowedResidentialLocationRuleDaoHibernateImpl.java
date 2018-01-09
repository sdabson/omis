package omis.residence.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.location.domain.Location;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.residence.dao.AllowedResidentialLocationRuleDao;
import omis.residence.domain.AllowedResidentialLocationRule;
import omis.residence.domain.ResidenceStatus;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of allowed residential rule data access object.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb 24, 2015)
 * @since  OMIS 3.0
 */
public class AllowedResidentialLocationRuleDaoHibernateImpl 
				extends GenericHibernateDaoImpl<AllowedResidentialLocationRule>
				implements AllowedResidentialLocationRuleDao {

	/* Query names. */
	
	private static final String FIND_ALLOWED_LOCATION_RULE_QUERY_NAME
		= "findAllowedResidentialLocationRule";
	
	private static final String FIND_LOCATIONS_RULE_QUERY_NAME
		= "findAllowedLocations";
	
	private static final String FIND_ALLOWED_LOCATIONS_QUERY_NAME 
		= "findAllowedLocationsByResidenceStatus";

	private static final String FIND_ALLOWED_LOCATIONS_IN_STATE_QUERY_NAME
		= "findAllowedLocationsWithinState";
	
	private static final String FIND_ALLOWED_LOCATIONS_IN_CITY_QUERY_NAME
		= "findAllowedLocationsWithinCity";
	
	private static final String FIND_ALLOWED_LOCATION_RULE_EXCLUDING_QUERY_NAME
		= "findAllowedResidentialLocationRuleExcluding";
	
	/* Parameter names. */
	
	private static final String RESIDENCE_STATUS_PARAM_NAME = "status";
	
	private static final String STATE_PARAM_NAME = "state";

	private static final String CITY_PARAM_NAME = "city";
	
	private static final String LOCATION_PARAM_NAME = "location";

	private static final String EXCLUDED_RULE_PARAM_NAME = "excludedRule";
	
	/**
	 * Instantiates a hibernate implementation of the data access object for 
	 * allowed residential location rule.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AllowedResidentialLocationRuleDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public AllowedResidentialLocationRule find(
			final Location location, final ResidenceStatus residenceStatus) {
		AllowedResidentialLocationRule rule = (AllowedResidentialLocationRule) 
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_ALLOWED_LOCATION_RULE_QUERY_NAME)
				.setParameter(LOCATION_PARAM_NAME, location)
				.setParameter(RESIDENCE_STATUS_PARAM_NAME, residenceStatus)
				.uniqueResult();
		return rule;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Location> findLocations(final Location location, 
			final ResidenceStatus residenceStatus) {
		@SuppressWarnings("unchecked")
		List<Location> locations = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_LOCATIONS_RULE_QUERY_NAME)
				.setParameter(LOCATION_PARAM_NAME, location)
				.setParameter(RESIDENCE_STATUS_PARAM_NAME, residenceStatus)
				.list();
		return locations;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Location> findAllowedLocations(
			final ResidenceStatus residenceStatus) {
		@SuppressWarnings("unchecked")
		List<Location> locations = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALLOWED_LOCATIONS_QUERY_NAME)
				.setParameter(RESIDENCE_STATUS_PARAM_NAME, residenceStatus)
				.list();
		return locations;
	}

	/** {@inheritDoc} */
	@Override
	public List<Location> findAllowedLocationsInState(final State state, 
			final ResidenceStatus residenceStatus) {
		@SuppressWarnings("unchecked")
		List<Location> locations = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALLOWED_LOCATIONS_IN_STATE_QUERY_NAME)
				.setParameter(STATE_PARAM_NAME, state)
				.setParameter(RESIDENCE_STATUS_PARAM_NAME, residenceStatus)
				.list();
		return locations;
	}

	/** {@inheritDoc} */
	@Override
	public List<Location> findAllowedLocationsInCity(final City city,
			final ResidenceStatus residenceStatus) {
		@SuppressWarnings("unchecked")
		List<Location> locations = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALLOWED_LOCATIONS_IN_CITY_QUERY_NAME)
				.setParameter(CITY_PARAM_NAME, city)
				.setParameter(RESIDENCE_STATUS_PARAM_NAME, residenceStatus)
				.list();
		return locations;
	}

	@Override
	public AllowedResidentialLocationRule findExcluding(final Location location, 
			final ResidenceStatus residenceStatus,
			final AllowedResidentialLocationRule excludedRule) {
		AllowedResidentialLocationRule rule = (AllowedResidentialLocationRule) 
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_ALLOWED_LOCATION_RULE_EXCLUDING_QUERY_NAME)
				.setParameter(LOCATION_PARAM_NAME, location)
				.setParameter(RESIDENCE_STATUS_PARAM_NAME, residenceStatus)
				.setParameter(EXCLUDED_RULE_PARAM_NAME, excludedRule)
				.uniqueResult();
		return rule;
	}
}
