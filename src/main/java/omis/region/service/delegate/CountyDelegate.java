package omis.region.service.delegate;

import java.util.List;

import omis.instance.factory.InstanceFactory;
import omis.region.dao.CountyDao;
import omis.region.domain.County;
import omis.region.domain.State;

/**
 * CountyDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
public class CountyDelegate {
	
	public final CountyDao countyDao;
	
	public final InstanceFactory<County> countyInstanceFactory;

	/**
	 * Contructor for CountyDelegate
	 * @param countyDao
	 */
	public CountyDelegate(final CountyDao countyDao,
			final InstanceFactory<County> countyInstanceFactory) {
		this.countyDao = countyDao;
		this.countyInstanceFactory = countyInstanceFactory;
	}
	
	/**
	 * Returns a list of all Countys
	 * @return List of all Countys
	 */
	public List<County> findAll() {
		return this.countyDao.findAll();
	}
	
	/**
	 * Returns all counties for the specified State.
	 * 
	 * @param state State whose counties to return
	 * @return counties for specified State
	 */
	public List<County> findByState(final State state){
		return this.countyDao.findByState(state);
	}
	
	/**
	 * Creates a County (for Unit Testing, does not perform
	 * duplicate entity checks)
	 * @param name - String
	 * @param state - State
	 * @param valid - Boolean
	 * @return Newly Created County
	 */
	public County create(final String name, final State state,
			final Boolean valid){
		County county = this.countyInstanceFactory.createInstance();
		
		county.setName(name);
		county.setState(state);
		county.setValid(valid);
		
		return this.countyDao.makePersistent(county);
	}
}
