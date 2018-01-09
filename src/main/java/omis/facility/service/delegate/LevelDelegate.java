package omis.facility.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.facility.dao.LevelDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Level;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;
import omis.instance.factory.InstanceFactory;

/**
 * Level delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Aug 2, 2017)
 * @since OMIS 3.0
 */
public class LevelDelegate {

	private final LevelDao levelDao;
	
	private final InstanceFactory<Level> levelInstanceFactory;

	/**
	 * Constructor for level delegate.
	 * 
	 * @param levelDao level data access object
	 * @param levelInstanceFactory level instance factory
	 */
	public LevelDelegate(final LevelDao levelDao,
			final InstanceFactory<Level> levelInstanceFactory) {
		this.levelDao = levelDao;
		this.levelInstanceFactory = levelInstanceFactory;
	}
	
	/**
	 * Finds all levels in the specified facility.
	 * 
	 * @param facility facility
	 * @return list of levels
	 */
	public List<Level> findByFacility(final Facility facility) {
		return this.levelDao.findByFacility(facility);
	}

	/**
	 * Creates a new level.
	 * 
	 * @param name name
	 * @param facility facility
	 * @param complex complex
	 * @param unit unit
	 * @param section section
	 * @param abbreviation abbreviation
	 * @param active active
	 * @return level
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public Level create(final String name, final Facility facility, 
			final Complex complex, final Unit unit, final Section section, 
			final String abbreviation, final Boolean active) 
					throws DuplicateEntityFoundException {
		if (this.levelDao.find(name, facility) != null) {
			throw new DuplicateEntityFoundException("The level already exists");
		}
		Level level = this.levelInstanceFactory.createInstance();
		populateLevel(level, name, facility, complex, unit, section, 
				abbreviation, active);
		return this.levelDao.makePersistent(level);
	}
	
	/**
	 * Updates an existing level.
	 * 
	 * @param level level
	 * @param name name
	 * @param facility facility
	 * @param complex complex
	 * @param unit unit
	 * @param section section
	 * @param abbreviation abbreviation
	 * @param active active
	 * @return level
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public Level update(final Level level, final String name, 
			final Facility facility, final Complex complex, final Unit unit, 
			final Section section, final String abbreviation, 
			final Boolean active) throws DuplicateEntityFoundException {
		if (this.levelDao.findExcluding(name, facility, level) != null) {
			throw new DuplicateEntityFoundException("The level already exists");
		}
		populateLevel(level, name, facility, complex, unit, section, 
				abbreviation, active);
		return this.levelDao.makePersistent(level);
	}

	/**
	 * Removes the specified level.
	 * 
	 * @param level level
	 */
	public void remove(final Level level) {
		this.levelDao.makeTransient(level);
	}
	
	/**
	 * Returns a list of levels that contain the specified properties.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @param unit unit
	 * @param section section
	 * @return list of matching levels
	 */
	public List<Level> findLevels(final Facility facility, 
			final Complex complex, final Unit unit, final Section section) {
		return this.levelDao.findLevels(facility, complex, unit, section);
	}

	// Populates a level
	private void populateLevel(final Level level, final String name, 
			final Facility facility, final Complex complex, final Unit unit, 
			final Section section, final String abbreviation, 
			final Boolean active) {
		level.setAbbreviation(abbreviation);
		level.setActive(active);
		level.setComplex(complex);
		level.setFacility(facility);
		level.setName(name);
		level.setSection(section);
		level.setUnit(unit);
	}
	
}
