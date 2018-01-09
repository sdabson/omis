/**
 * 
 */
package omis.facility.service.impl;

import java.util.List;

import omis.facility.dao.UnitDao;
import omis.facility.domain.Facility;
import omis.facility.domain.Unit;
import omis.facility.service.UnitService;

/**
 * Unit service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.1 (Feb 25, 2015)
 * @since OMIS 3.0
 */
public class UnitServiceImpl implements UnitService {

	private UnitDao unitDao;
	
	/**
	 * Instantiates a unit service implementation with the specified data
	 * access object.
	 * 
	 * @param unitDao unit DAO
	 */
	public UnitServiceImpl(final UnitDao unitDao) {
		this.unitDao = unitDao;
	}

	/** {@inheritDoc} */
	@Override
	public Unit save(final Unit unit) {
		return this.unitDao.makePersistent(unit);
	}

	/** {@inheritDoc} */
	@Override
	public List<Unit> findByFacility(final Facility facility) {
		return this.unitDao.findByFacility(facility);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final Unit unit) {
		this.unitDao.makeTransient(unit);
	}
}