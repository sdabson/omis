package omis.communitysupervision.service.delegate;

import omis.communitysupervision.dao.CommunitySupervisionOfficeDao;
import omis.communitysupervision.domain.CommunitySupervisionOffice;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.location.domain.Location;

/**
 * Delegate for community supervision office.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 17, 2017)
 * @since OMIS 3.0
 */
public class CommunitySupervisionOfficeDelegate {

	/* Resources. */
	
	private final CommunitySupervisionOfficeDao communitySupervisionOfficeDao;
	
	private final InstanceFactory<CommunitySupervisionOffice> 
		communitySupervisionOfficeInstanceFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for managing community supervision office.
	 * 
	 * @param communitySupervisionOfficeDao data access object for 
	 * community supervision office
	 * @param communitySupervisionOfficeInstanceFactory instance factory
	 *  for community supervision offices
	 */
	public CommunitySupervisionOfficeDelegate(
			final CommunitySupervisionOfficeDao 
				communitySupervisionOfficeDao,
			final InstanceFactory<CommunitySupervisionOffice> 
				communitySupervisionOfficeInstanceFactory) {
		this.communitySupervisionOfficeDao = communitySupervisionOfficeDao;
		this.communitySupervisionOfficeInstanceFactory 
			= communitySupervisionOfficeInstanceFactory;
	}

	/**
	 * Creates a new community supervision office.
	 * 
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @return created community supervision office
	 * @throws DuplicateEntityFoundException thrown when a duplicate community 
	 * supervision office is found
	 */
	public CommunitySupervisionOffice create(Location location, 
			String name, Long telephoneNumber) 
					throws DuplicateEntityFoundException {
		if (this.communitySupervisionOfficeDao.find(location, name, 
				telephoneNumber) != null) {
			throw new DuplicateEntityFoundException("Duplicate community "
					+ "supervision office found");
		}
		CommunitySupervisionOffice communitySupervisionOffice 
			= this.communitySupervisionOfficeInstanceFactory.createInstance();
		communitySupervisionOffice.setLocation(location);
		communitySupervisionOffice.setName(name);
		communitySupervisionOffice.setTelephoneNumber(telephoneNumber);
		return this.communitySupervisionOfficeDao.makePersistent(
				communitySupervisionOffice);
	}
	
	/**
	 * Updates the specified community supervision office.
	 * 
	 * @param communitySupervisionOffice community supervision office
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @return updated community supervision office
	 * @throws DuplicateEntityFoundException thrown when a duplicate community 
	 * supervision office is found
	 */
	public CommunitySupervisionOffice update(
			CommunitySupervisionOffice communitySupervisionOffice,
		Location location, String name, Long telephoneNumber) 
				throws DuplicateEntityFoundException {
		if (this.communitySupervisionOfficeDao.findExcluding(location, 
				name, telephoneNumber, communitySupervisionOffice) 
				!= null) {
			throw new DuplicateEntityFoundException("Duplicate community "
					+ "supervision office found");
		}
		communitySupervisionOffice.setLocation(location);
		communitySupervisionOffice.setName(name);
		communitySupervisionOffice.setTelephoneNumber(telephoneNumber);
		return this.communitySupervisionOfficeDao.makePersistent(
				communitySupervisionOffice);
	}
	
	/**
	 * Removes the specified community supervision office
	 * 
	 * @param communitySupervisionOffice community supervision office
	 */
	public void remove(CommunitySupervisionOffice 
			communitySupervisionOffice) {
		this.communitySupervisionOfficeDao.makeTransient(
				communitySupervisionOffice);
	}
}
