package omis.stg.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.region.domain.State;
import omis.stg.dao.SecurityThreatGroupDao;
import omis.stg.domain.SecurityThreatGroup;

/**
 * Delegate for security threat groups.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Dec 07, 2016)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupDelegate {

	private final InstanceFactory<SecurityThreatGroup>
		securityThreatGroupInstanceFactory;
	
	private final SecurityThreatGroupDao securityThreatGroupDao;
	
	/**
	 * Constructor
	 * @param securityThreatGroupInstanceFactory
	 * @param securityThreatGroupDao
	 */
	public SecurityThreatGroupDelegate (
			final InstanceFactory<SecurityThreatGroup> 
				securityThreatGroupInstanceFactory, 
				final SecurityThreatGroupDao securityThreatGroupDao) {
		this.securityThreatGroupInstanceFactory = securityThreatGroupInstanceFactory;
		this.securityThreatGroupDao = securityThreatGroupDao;
	}
	
	/**
	 * Creates a new security threat group
	 * @param name security threat group name
	 * @param state state
	 * @return security threat group
	 * @throws DuplicateEntityFoundException
	 */
	public SecurityThreatGroup create(String name, State state) 
			throws DuplicateEntityFoundException {
		if (this.securityThreatGroupDao.find(name, state) != null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		SecurityThreatGroup group 
			= this.securityThreatGroupInstanceFactory.createInstance();
		group.setName(name);
		group.setState(state);
		group.setValid(true);
		return this.securityThreatGroupDao.makePersistent(group);
		
	}
	
	/**
	 * Returns security threat groups.
	 * 
	 * @return security threat groups
	 */
	public List<SecurityThreatGroup> findGroups() {
		return this.securityThreatGroupDao.findAll();
	}
}
