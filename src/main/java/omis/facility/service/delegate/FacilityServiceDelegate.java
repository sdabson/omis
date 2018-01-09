package omis.facility.service.delegate;

import java.util.List;

import omis.facility.dao.ComplexDao;
import omis.facility.dao.LevelDao;
import omis.facility.dao.SectionDao;
import omis.facility.dao.UnitDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Level;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;

/**
 * Facility service delegate.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 25, 2015)
 * @since OMIS 3.0
 */
public class FacilityServiceDelegate {

	/* Data access objects. */
	
	private UnitDao unitDao;
	
	private LevelDao levelDao;
	
	private SectionDao sectionDao;
	
	private ComplexDao complexDao;
	
	public FacilityServiceDelegate(
			final UnitDao unitDao, final LevelDao levelDao,
			final SectionDao sectionDao, final ComplexDao complexDao) {
		this.unitDao = unitDao;
		this.levelDao = levelDao;
		this.sectionDao = sectionDao;
		this.complexDao = complexDao;
	}
	
	/**
	 * Returns all complexes for the specified facility.
	 * 
	 * @param facility facility
	 * @return list of complexes
	 */
	public List<Complex> findComplexesByFacility(final Facility facility) {
		return this.complexDao.findByFacility(facility);
	}
	
	/**
	 * Returns all sections for the specified facility.
	 * 
	 * @param facility facility
	 * @return list of sections
	 */
	public List<Section> findSectionsByFacility(final Facility facility) {
		return this.sectionDao.findByFacility(facility);
	}
	
	/**
	 * Returns all units for the specified facility.
	 * 
	 * @param facility facility
	 * @return list of units
	 */
	public List<Unit> findUnitsByFacility(final Facility facility) {
		return this.unitDao.findByFacility(facility);
	}
	
	/**
	 * Returns all levels for the specified facility.
	 * 
	 * @param facility facility
	 * @return list of levels
	 */
	public List<Level> findLevelsByFacility(final Facility facility) {
		return this.levelDao.findByFacility(facility);
	}
}