package omis.stg.dao;

import omis.dao.GenericDao;
import omis.stg.domain.SecurityThreatGroupActivityLevel;

/**
 * Data access object for security threat group activity levels.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
public interface SecurityThreatGroupActivityLevelDao
		extends GenericDao<SecurityThreatGroupActivityLevel> {

	/**
	 * Returns the security threat group activity level with the specified name
	 * @param name security threat group activity level name
	 * @return security threat group activity level
	 */
	SecurityThreatGroupActivityLevel find(String name);
	
	/**
	 * Returns the security threat group activity level with the specified name 
	 * excluding the specified security threat group activity level
	 * @param name security threat group activity level name
	 * @param excludedActivityLevel excluded activity level
	 * @return security threat group activity level
	 */
	SecurityThreatGroupActivityLevel findExcluding(String name, 
			SecurityThreatGroupActivityLevel excludedActivityLevel);
}