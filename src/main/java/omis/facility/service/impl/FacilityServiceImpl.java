/**
 * 
 */
package omis.facility.service.impl;

import java.util.List;

import omis.facility.dao.FacilityDao;
import omis.facility.domain.Facility;
import omis.facility.service.FacilityService;
import omis.location.domain.Location;

/**
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 08 2013)
 * @since OMIS 3.0
 */
public class FacilityServiceImpl implements FacilityService {

	private FacilityDao facilityDao;
	
	/**
	 * Instantiates a facility service implementation with the specified data
	 * access object.
	 * 
	 * @param facilityDao facility DAO
	 */
	public FacilityServiceImpl(final FacilityDao facilityDao) {
		this.facilityDao = facilityDao;
	}

	/** {@inheritDoc} */
	@Override
	public Facility findById(final Long id) {
		return this.facilityDao.findById(id, false);
	}

	/** {@inheritDoc} */
	@Override
	public Facility save(final Facility facility) {
		return this.facilityDao.makePersistent(facility);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Facility> findAll() {
		return this.facilityDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final Facility facility) {
		this.facilityDao.makeTransient(facility);
	}

	@Override
	public Facility findFacilityByLocation(final Location location) {
		return this.facilityDao.findFacilityByLocation(location);
	}
}