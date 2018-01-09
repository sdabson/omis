package omis.prerelease.service.delegate;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.location.domain.Location;
import omis.prerelease.dao.PreReleaseCenterDao;
import omis.prerelease.domain.PreReleaseCenter;

/**
 * Delegate for prerelease center.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 17, 2017)
 * @since OMIS 3.0
 */
public class PreReleaseCenterDelegate {
	
	/* Resources. */
	
	private final PreReleaseCenterDao preReleaseCenterDao;
	
	private final InstanceFactory<PreReleaseCenter> 
		preReleaseCenterInstanceFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for managing prerelease center.
	 * 
	 * @param preReleaseCenterDao data access object for prerelease center
	 * @param preReleaseCenterInstanceFactory instance factory for prerelease 
	 * center
	 */
	public PreReleaseCenterDelegate(
			final PreReleaseCenterDao preReleaseCenterDao,
			final InstanceFactory<PreReleaseCenter> 
				preReleaseCenterInstanceFactory) {
		this.preReleaseCenterDao = preReleaseCenterDao;
		this.preReleaseCenterInstanceFactory = preReleaseCenterInstanceFactory;
	}
	
	/**
	 * Creates a new prerelease center.
	 * 
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @return created community supervision office
	 * @throws DuplicateEntityFoundException thrown when a duplicate prerelease 
	 * center is found
	 */
	public PreReleaseCenter create(Location location, String name, 
			Long telephoneNumber) throws DuplicateEntityFoundException {
		if (this.preReleaseCenterDao.find(location, name, telephoneNumber) 
				!= null) {
			throw new DuplicateEntityFoundException("Duplicate prerelease "
					+ "center found");
		}
		PreReleaseCenter preReleaseCenter 
			= this.preReleaseCenterInstanceFactory.createInstance();
		preReleaseCenter.setLocation(location);
		preReleaseCenter.setName(name);
		preReleaseCenter.setTelephoneNumber(telephoneNumber);
		return this.preReleaseCenterDao.makePersistent(preReleaseCenter);
	}
	
	/**
	 * Updates the specified prerelease center.
	 * 
	 * @param preReleaseCenter prerelease center
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @return updated prerelease center
	 * @throws DuplicateEntityFoundException thrown when a duplicate prerelease 
	 * center is found
	 */
	public PreReleaseCenter update(PreReleaseCenter preReleaseCenter, 
			Location location, String name, Long telephoneNumber) 
				throws DuplicateEntityFoundException {
		if (this.preReleaseCenterDao.findExcluding(location, 
				name, telephoneNumber, preReleaseCenter) 
				!= null) {
			throw new DuplicateEntityFoundException("Duplicate prerelease "
					+ "center found");
		}
		preReleaseCenter.setLocation(location);
		preReleaseCenter.setName(name);
		preReleaseCenter.setTelephoneNumber(telephoneNumber);
		return this.preReleaseCenterDao.makePersistent(preReleaseCenter);
	}
	
	/**
	 * Removes the specified prerelease center.
	 * 
	 * @param preReleaseCenter prerelease center
	 */
	public void remove(PreReleaseCenter preReleaseCenter) {
		this.preReleaseCenterDao.makeTransient(preReleaseCenter);
	}
}
