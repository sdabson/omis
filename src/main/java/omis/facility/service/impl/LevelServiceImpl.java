/**
 * 
 */
package omis.facility.service.impl;

import java.util.List;

import omis.facility.dao.LevelDao;
import omis.facility.domain.Facility;
import omis.facility.domain.Level;
import omis.facility.service.LevelService;

/**
 * Level service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.1 (Feb 25, 2015)
 * @since OMIS 3.0
 */
public class LevelServiceImpl implements LevelService {

	private LevelDao levelDao;
	
	/**
	 * Instantiates a level service implementation with the specified data
	 * access object.
	 * 
	 * @param levelDao level DAO
	 */
	public LevelServiceImpl(final LevelDao levelDao) {
		this.levelDao = levelDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public Level save(final Level level) {
		return this.levelDao.makePersistent(level);
	}

	/** {@inheritDoc} */
	@Override
	public List<Level> findByFacility(final Facility facility) {
		return this.levelDao.findByFacility(facility);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final Level level) {
		this.levelDao.makeTransient(level);
	}
}