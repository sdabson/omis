package omis.locationterm.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.locationterm.dao.LocationReasonDao;
import omis.locationterm.domain.LocationReason;

/**
 * Delegate for location reasons.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Nov 2, 2015)
 * @since OMIS 3.0
 */
public class LocationReasonDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<LocationReason>
	locationReasonInstanceFactory;
	
	/* DAOs. */
	
	private final LocationReasonDao locationReasonDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates data access object for location reasons.
	 * 
	 * @param locationReasonInstanceFactory instance factory for
	 * location reasons
	 * @param locationReasonDao data access object for location reasons
	 */
	public LocationReasonDelegate(
			final InstanceFactory<LocationReason>
				locationReasonInstanceFactory,
			final LocationReasonDao locationReasonDao) {
		this.locationReasonInstanceFactory
			= locationReasonInstanceFactory;
		this.locationReasonDao = locationReasonDao;
	}
	
	/* Methods. */
	
	/**
	 * Returns location reasons.
	 * 
	 * @return location reasons
	 */
	public List<LocationReason> findAll() {
		return this.locationReasonDao.findAll();
	}

	/**
	 * Creates location reason.
	 * 
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid whether valid
	 * @return creates location reason
	 * @throws DuplicateEntityFoundException if reason exists
	 */
	public LocationReason create(
			final String name, final Short sortOrder, final Boolean valid)
				throws DuplicateEntityFoundException {
		LocationReason reason = this.locationReasonInstanceFactory
				.createInstance();
		reason.setName(name);
		reason.setSortOrder(sortOrder);
		reason.setValid(valid);
		return this.locationReasonDao.makePersistent(reason);
	}
}