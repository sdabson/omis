package omis.grievance.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.grievance.dao.GrievanceLocationDao;
import omis.grievance.domain.GrievanceLocation;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for grievance locations.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Feb 25, 2016)
 * @since OMIS 3.0
 */
public class GrievanceLocationDelegate {
	
	/* Instance factories. */
	
	private final InstanceFactory<GrievanceLocation>
	grievanceLocationInstanceFactory;

	/* Data access objects. */
	
	private final GrievanceLocationDao grievanceLocationDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for grievance locations.
	 * 
	 * @param grievanceLocationInstanceFactory instance factory for grievance
	 * locations
	 * @param grievanceLocationDao data access object for grievance locations
	 */
	public GrievanceLocationDelegate(
			final InstanceFactory<GrievanceLocation>
				grievanceLocationInstanceFactory,
			final GrievanceLocationDao grievanceLocationDao) {
		this.grievanceLocationInstanceFactory
			= grievanceLocationInstanceFactory;
		this.grievanceLocationDao = grievanceLocationDao;
	}
	
	/* Methods. */
	
	/**
	 * Creates grievance location.
	 * 
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid whether valid
	 * @return grievance location
	 * @throws DuplicateEntityFoundException if grievance location exists
	 */
	public GrievanceLocation create(
			final String name, final Short sortOrder, final Boolean valid)
					throws DuplicateEntityFoundException {
		if (this.grievanceLocationDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Grievance location exists");
		}
		GrievanceLocation location = this.grievanceLocationInstanceFactory
				.createInstance();
		location.setName(name);
		location.setSortOrder(sortOrder);
		location.setValid(valid);
		return this.grievanceLocationDao.makePersistent(location);
	}
	
	/**
	 * Return grievance locations.
	 * 
	 * @return grievance locations
	 */
	public List<GrievanceLocation> findAll() {
		return this.grievanceLocationDao.findAll();
	}
}