package omis.grievance.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.grievance.dao.GrievanceUnitDao;
import omis.grievance.domain.GrievanceUnit;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for grievance units.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Feb 25, 2016)
 * @since OMIS 3.0
 */
public class GrievanceUnitDelegate {

	/* Instance factory. */
	
	private final InstanceFactory<GrievanceUnit> grievanceUnitInstanceFactory;
	
	/* Data access objects. */
	
	private final GrievanceUnitDao grievanceUnitDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for grievance units.
	 * 
	 * @param grievanceUnitInstanceFactory instance factory for grievance units
	 * @param grievanceUnitDao data access object for grievance units
	 */
	public GrievanceUnitDelegate(
			final InstanceFactory<GrievanceUnit> grievanceUnitInstanceFactory,
			final GrievanceUnitDao grievanceUnitDao) {
		this.grievanceUnitInstanceFactory = grievanceUnitInstanceFactory;
		this.grievanceUnitDao = grievanceUnitDao;
	}
	
	/* Methods. */
	
	public GrievanceUnit create(
			final String name, final Boolean valid, final Short sortOrder)
				throws DuplicateEntityFoundException {
		if (this.grievanceUnitDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Grievance unit exists");
		}
		GrievanceUnit unit = this.grievanceUnitInstanceFactory
				.createInstance();
		unit.setName(name);
		unit.setValid(valid);
		unit.setSortOrder(sortOrder);
		return this.grievanceUnitDao.makePersistent(unit);
	}
	
	/**
	 * Returns grievance units.
	 * 
	 * @return grievance units
	 */
	public List<GrievanceUnit> findAll() {
		return this.grievanceUnitDao.findAll();
	}
}