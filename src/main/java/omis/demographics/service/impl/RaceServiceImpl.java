package omis.demographics.service.impl;

import java.util.List;

import omis.demographics.dao.RaceDao;
import omis.demographics.domain.Race;
import omis.demographics.service.RaceService;

/**
 * Implementation of service for races.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 7, 2014)
 * @since OMIS 3.0
 */
public class RaceServiceImpl
		implements RaceService {

	private final RaceDao raceDao;
	
	/**
	 * Instantiates an implementation of service for races.
	 * 
	 * @param raceDao data access object for races
	 */
	public RaceServiceImpl(final RaceDao raceDao) {
		this.raceDao = raceDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Race> findAll() {
		return this.raceDao.findAll();
	}
}