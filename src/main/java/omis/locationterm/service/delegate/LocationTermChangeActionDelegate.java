package omis.locationterm.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.locationterm.dao.LocationTermChangeActionDao;
import omis.locationterm.domain.LocationTermChangeAction;

/**
 * Delegate for location term change actions.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class LocationTermChangeActionDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<LocationTermChangeAction>
	locationTermChangeActionInstanceFactory;
	
	/* Data access objects. */
	
	private final LocationTermChangeActionDao locationTermChangeActionDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for location term change actions.
	 * 
	 * @param locationTermChangeActionInstanceFactory instance factory for
	 * location term change actions
	 * @param locationTermChangeActionDao data access object for location term
	 * change actions
	 */
	public LocationTermChangeActionDelegate(
			final InstanceFactory<LocationTermChangeAction>
				locationTermChangeActionInstanceFactory,
			final LocationTermChangeActionDao locationTermChangeActionDao) {
		this.locationTermChangeActionInstanceFactory
			= locationTermChangeActionInstanceFactory;
		this.locationTermChangeActionDao = locationTermChangeActionDao;
	}
	
	/* Methods. */
	
	/**
	 * Creates location term change action.
	 * 
	 * @param name name
	 * @return newly created location term change action
	 * @throws DuplicateEntityFoundException if location term change action
	 * exists
	 */
	public LocationTermChangeAction create(final String name)
			throws DuplicateEntityFoundException {
		if (this.locationTermChangeActionDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Location term change action exists");
		}
		LocationTermChangeAction changeAction
			= this.locationTermChangeActionInstanceFactory.createInstance();
		changeAction.setName(name);
		return this.locationTermChangeActionDao.makePersistent(changeAction);
	}
	
	/**
	 * Returns location term change actions.
	 * 
	 * @return location term change actions
	 */
	public List<LocationTermChangeAction> findAll() {
		return this.locationTermChangeActionDao.findAll();
	}
}