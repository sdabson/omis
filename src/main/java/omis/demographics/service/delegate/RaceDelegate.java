package omis.demographics.service.delegate;

import java.util.List;

import omis.demographics.dao.RaceDao;
import omis.demographics.domain.Race;

/**
 * RaceDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 10, 2016)
 *@since OMIS 3.0
 *
 */
public class RaceDelegate {
	
	public final RaceDao raceDao;

	/**
	 * Contructor for RaceDelegate
	 * @param raceDao
	 */
	public RaceDelegate(RaceDao raceDao) {
		this.raceDao = raceDao;
	}
	
	/**
	 * Returns a list of all Races
	 * @return List of all Races
	 */
	public List<Race> findAll() {
		return this.raceDao.findAll();
	}
	
	
}
