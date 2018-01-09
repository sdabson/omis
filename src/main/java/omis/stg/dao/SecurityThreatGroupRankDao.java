package omis.stg.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupRank;

/**
 * Data access object for security threat group ranks.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
public interface SecurityThreatGroupRankDao
		extends GenericDao<SecurityThreatGroupRank> {

	/**
	 * Returns the security threat group rank with the matching name
	 * @param name security threat group rank name
	 * @return security threat group
	 */
	SecurityThreatGroupRank find(String name, SecurityThreatGroup securityThreateGroup);
	
	/**
	 * Returns the security threat group rank with the matching name
	 * @param name security threat group rank name
	 * @param securityThreatGroupRank excluded security threat group rank
	 * @return security threat group rank
	 */
	SecurityThreatGroupRank findExcluding(String name, 
			SecurityThreatGroup securityThreatGroup,
			SecurityThreatGroupRank securityThreatGroupRank);
	
	/**
	 * Returns the security threat group ranks by the specified group
	 * @param securityThreatGroup security threat group
	 * @return list of security threat group ranks
	 */
	List<SecurityThreatGroupRank> findRanksByGroup(
			SecurityThreatGroup securityThreatGroup);
}