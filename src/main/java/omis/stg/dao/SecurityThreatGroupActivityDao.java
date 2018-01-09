package omis.stg.dao;

import java.util.Date;

import omis.dao.GenericDao;
import omis.person.domain.Person;
import omis.stg.domain.SecurityThreatGroupActivity;

/**
 * Data access object for security threat group activity.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Nov 22, 2016)
 * @since OMIS 3.0
 */
public interface SecurityThreatGroupActivityDao
		extends GenericDao<SecurityThreatGroupActivity> {

	/**
	 * Finds a security threat group activity.
	 * 
	 * @param Date - date
	 * @param Person - reportedBy
	 * @param String - summary
	 * 
	 * @return Finds a security threat group activity.
	 */
	SecurityThreatGroupActivity find(Date date, Person reportedBy, 
			String summary);
	
	/**
	 * Finds security threat group activities excluding the specified activity.
	 * 
	 * @param Date - date
	 * @param Person - reportedBy
	 * @param String - summary
	 * 
	 * @return Finds a security threat group activity.
	 */
	SecurityThreatGroupActivity findExcluding(
			SecurityThreatGroupActivity excludedActivity, Date date, 
			Person reportedBy, String summary);
}