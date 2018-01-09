package omis.region.dao.impl.hibernate;

import java.util.List;

import omis.country.domain.Country;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.region.dao.StateDao;
import omis.region.domain.State;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for States.
 * 
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb 6, 2013)
 * @since OMIS 3.0
 */
public class StateDaoHibernateImpl
		extends GenericHibernateDaoImpl<State>
		implements StateDao {

	/* Query names. */
	
	private static final String FIND_BY_COUNTRY_QUERY_NAME
		= "findStatesByCountry";
	
	private static final String FIND_ALL_QUERY_NAME = "findStates";
	
	private static final String FIND_HOME_STATE_QUERY_NAME = "findHomeState";
	
	private static final String FIND_IN_HOME_COUNTRY_QUERY_NAME
		= "findStatesInHomeCountry";
	
	private static final String COUNT_BY_COUNTRY_QUERY_NAME
		= "countStatesByCountry";
	
	private static final String FIND_QUERY_NAME = "findState";
	
	/* Parameter names. */
	
	private static final String COUNTRY_PARAM_NAME = "country";

	private static final String NAME_PARAM_NAME = "name";
	
	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * States with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public StateDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<State> findAll() {
		@SuppressWarnings("unchecked")
		List<State> states = getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return states;
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<State> findByCountry(final Country country) {
		@SuppressWarnings("unchecked")
		List<State> states = getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_COUNTRY_QUERY_NAME)
				.setParameter(COUNTRY_PARAM_NAME, country)
				.list();
		return states;
	}

	/** {@inheritDoc} */
	@Override
	public State findHomeState() {
		State state = (State) getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_HOME_STATE_QUERY_NAME)
				.uniqueResult();
		return state;
	}

	/** {@inheritDoc} */
	@Override
	public List<State> findInHomeCountry() {
		@SuppressWarnings("unchecked")
		List<State> homeCountries = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_IN_HOME_COUNTRY_QUERY_NAME)
				.list();
		return homeCountries;
	}

	/** {@inheritDoc} */
	@Override
	public long countByCountry(final Country country) {
		long count = (Long) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(COUNT_BY_COUNTRY_QUERY_NAME)
				.setParameter(COUNTRY_PARAM_NAME, country)
				.uniqueResult();
		return count;
	}

	/** {@inheritDoc} */
	@Override
	public State find(final String name, final Country country) {
		State state = (State) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(COUNTRY_PARAM_NAME, country)
				.uniqueResult();
		return state;
	}
}