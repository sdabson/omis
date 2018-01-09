package omis.stg.dao;

import omis.dao.GenericDao;
import omis.region.domain.State;
import omis.stg.domain.SecurityThreatGroup;

/**
 * Data access object for security threat groups.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
public interface SecurityThreatGroupDao
		extends GenericDao<SecurityThreatGroup> {

	/**
	 * Finds a security threat group with the specified name and state
	 * @param name security threat group name
	 * @param state state
	 * @return security threat group
	 */
	SecurityThreatGroup find(String name, State state);
	
	/**
	 * Finds a security threat group with the specified name and state excluding
	 * the specified security threat group
	 * @param name security threat group name
	 * @param state state
	 * @param securityThreatGroup excluded security threat group
	 * @return security threat group
	 */
	SecurityThreatGroup findExcluding(String name, State state, 
			SecurityThreatGroup securityThreatGroup);
}