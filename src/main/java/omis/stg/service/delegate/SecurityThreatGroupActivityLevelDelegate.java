package omis.stg.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.stg.dao.SecurityThreatGroupActivityLevelDao;
import omis.stg.domain.SecurityThreatGroupActivityLevel;

/**
 * Delegate for security threat group activity levels.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Dec 07, 2016)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupActivityLevelDelegate {

	private final InstanceFactory<SecurityThreatGroupActivityLevel>
		securityThreatGroupActivityLevelInstanceFactory;
	
	private final SecurityThreatGroupActivityLevelDao 
		securityThreatGroupActivityLevelDao;
	
	/**
	 * Constructor
	 * @param securityThreatGroupActivityLevelInstanceFactory
	 * @param securityThreatGroupActivityLevelDao
	 */
	public SecurityThreatGroupActivityLevelDelegate (
			final InstanceFactory<SecurityThreatGroupActivityLevel> 
				securityThreatGroupActivityLevelInstanceFactory, 
			final SecurityThreatGroupActivityLevelDao 
				securityThreatGroupActivityLevelDao) {
		this.securityThreatGroupActivityLevelInstanceFactory = securityThreatGroupActivityLevelInstanceFactory;
		this.securityThreatGroupActivityLevelDao = securityThreatGroupActivityLevelDao;
	}
	
	/**
	 * Creates a new security threat group activity level
	 * @param name security threat group activity level name
	 * @return security threat group activity level
	 * @throws DuplicateEntityFoundException
	 */
	public SecurityThreatGroupActivityLevel create(final String name) 
			throws DuplicateEntityFoundException{
		if (this.securityThreatGroupActivityLevelDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		SecurityThreatGroupActivityLevel activityLevel 
			= this.securityThreatGroupActivityLevelInstanceFactory
			.createInstance();
		activityLevel.setName(name);
		activityLevel.setValid(true);
		return this.securityThreatGroupActivityLevelDao
				.makePersistent(activityLevel);
	}

	/**
	 * Returns security threat group activity levels.
	 * 
	 * @return security threat group activity levels
	 */
	public List<SecurityThreatGroupActivityLevel> findAll() {
		return this.securityThreatGroupActivityLevelDao.findAll();
	}
}
