package omis.facility.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.facility.dao.SectionDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Level;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;
import omis.instance.factory.InstanceFactory;

/**
 * Section delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Aug 2, 2017)
 * @since OMIS 3.0
 */
public class SectionDelegate {
	
	private final SectionDao sectionDao;
	
	private final InstanceFactory<Section> sectionInstanceFactory;
	
	/**
	 * Constructor for section delegate.
	 * 
	 * @param sectionDao section data access object
	 * @param sectionInstanceFactory section instance factory
	 */
	public SectionDelegate(final SectionDao sectionDao,
			final InstanceFactory<Section> sectionInstanceFactory) {
		this.sectionDao = sectionDao;
		this.sectionInstanceFactory = sectionInstanceFactory;
	}

	
	/**
	 * Returns a list of all sections from the specified facility.
	 * 
	 * @param facility facility 
	 * @return list of sections
	 */
	public List<Section> findByFacility(final Facility facility) {
		return this.sectionDao.findByFacility(facility);
	}
	
	/**
	 * Creates a new section.
	 * 
	 * @param name name 
	 * @param facility facility
	 * @param complex complex
	 * @param unit unit
	 * @param level level
	 * @param abbreviation abbreviation
	 * @param active active
	 * @return section
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	public Section create(final String name, final Facility facility, 
			final Complex complex, final Unit unit, final Level level, 
			final String abbreviation, final Boolean active) 
					throws DuplicateEntityFoundException {
		if (this.sectionDao.find(name, facility) != null) {
			throw new DuplicateEntityFoundException(
					"The section already exists.");
		}
		Section section = this.sectionInstanceFactory.createInstance();
		populateSection(section, name, facility, complex, unit, level, 
				abbreviation, active);
		return this.sectionDao.makePersistent(section);
	}
	
	/**
	 * Updates an existing section.
	 * 
	 * @param section section
	 * @param name name 
	 * @param facility facility
	 * @param complex complex
	 * @param unit unit
	 * @param level level
	 * @param abbreviation abbreviation
	 * @param active active
	 * @return section
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	public Section update(final Section section, final String name, 
			final Facility facility, final Complex complex, final Unit unit, 
			final Level level, final String abbreviation, final Boolean active) 
					throws DuplicateEntityFoundException {
		if (this.sectionDao.findExcluding(name, facility, section) != null) {
			throw new DuplicateEntityFoundException(
					"The section already exists.");
		}
		populateSection(section, name, facility, complex, unit, level, 
				abbreviation, active);
		return this.sectionDao.makePersistent(section);
	}
	
	/**
	 * Removes the specified section.
	 * 
	 * @param section section
	 */
	public void remove(final Section section) {
		this.sectionDao.makeTransient(section);
	}
	
	/**
	 * Returns a list of sections with the specified properties.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @param unit unit
	 * @param level level
	 * @return list of sections
	 */
	public List<Section> findSections(final Facility facility, 
			final Complex complex, final Unit unit, final Level level) {
		return this.sectionDao.findSections(facility, complex, unit, level);
	}

	// Populates a section
	private void populateSection(final Section section, final String name, 
			final Facility facility, final Complex complex, final Unit unit, 
			final Level level, final String abbreviation, 
			final Boolean active) {
		section.setAbbreviation(abbreviation);
		section.setActive(active);
		section.setComplex(complex);
		section.setFacility(facility);
		section.setLevel(level);
		section.setName(name);
		section.setUnit(unit);
	}
}
