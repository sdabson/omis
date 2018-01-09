package omis.region.service.delegate;

import java.util.List;

import omis.country.domain.Country;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.region.dao.StateDao;
import omis.region.domain.State;

/**
 * Delegate for States.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 14, 2015)
 * @since OMIS 3.0
 */
public class StateDelegate {
	
	/* Instance factories. */
	
	private final InstanceFactory<State> stateInstanceFactory;
	
	/* Data access objects. */
	
	private final StateDao stateDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for States.
	 * 
	 * @param stateInstanceFactory instance factory for States
	 * @param stateDao data access object for States
	 */
	public StateDelegate(
			final InstanceFactory<State> stateInstanceFactory,
			final StateDao stateDao) {
		this.stateInstanceFactory = stateInstanceFactory;
		this.stateDao = stateDao;
	}
	
	/* Methods. */
	
	/**
	 * Counts States by country.
	 * 
	 * @param country country
	 * @return number of States in country
	 */
	public long countByCountry(final Country country) {
		return this.stateDao.countByCountry(country);
	}

	/**
	 * Returns States by country.
	 * 
	 * @param country country
	 * @return States by country
	 */
	public List<State> findByCountry(final Country country) {
		return this.stateDao.findByCountry(country);
	}
	
	/**
	 * Returns home State.
	 * 
	 * @return State
	 */
	public State findHomeState() {
		return this.stateDao.findHomeState();
	}
	
	/**
	 * Returns State.
	 * 
	 * <p>If State does not exist, creates and returns it.
	 * 
	 * @param name name
	 * @param abbreviation abbreviation
	 * @param country country
	 * @param home whether home State
	 * @param valid whether valid
	 * @return State
	 */
	public State findOrCreate(
			final String name, final String abbreviation, final Country country,
			final Boolean home, final Boolean valid) {
		State state = this.stateDao.find(name, country);
		if (state != null) {
			return state;
		} else {
			return this.createImpl(name, abbreviation, country, home, valid);
		}
	}
	
	/**
	 * Creates country.
	 * 
	 * @param name name
	 * @param abbreviation abbreviation
	 * @param country country
	 * @param home home
	 * @param valid whether valid
	 * @return created country
	 * @throws DuplicateEntityFoundException if country exists
	 */
	public State create(final String name, final String abbreviation,
			final Country country, final Boolean home, final Boolean valid)
				throws DuplicateEntityFoundException {
		if (this.stateDao.find(name, country) != null) {
			throw new DuplicateEntityFoundException("Country exists");
		}
		return this.createImpl(name, abbreviation, country, home, valid);
	}
	
	/**
	 * Returns all states
	 * @return list of states
	 */
	public List<State> findAll() {
		return this.stateDao.findAll();
	}
	
	// Creates State
	private State createImpl(
			final String name, final String abbreviation, final Country country,
			final Boolean home, final Boolean valid) {
		State state = this.stateInstanceFactory.createInstance();
		state.setName(name);
		state.setAbbreviation(abbreviation);
		state.setCountry(country);
		state.setHome(home);
		state.setValid(valid);
		return this.stateDao.makePersistent(state);
	}

	/**
	 * Returns States in home country.
	 * 
	 * @return States in home country
	 */
	public List<State> findInHomeCountry() {
		return this.stateDao.findInHomeCountry();
	}
}