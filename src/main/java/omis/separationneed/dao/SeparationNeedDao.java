/**
 * 
 */
package omis.separationneed.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.relationship.domain.Relationship;
import omis.separationneed.domain.SeparationNeed;

/**
 * Data access object for separation need.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 21 2013)
 * @since OMIS 3.0
 */
public interface SeparationNeedDao extends GenericDao<SeparationNeed> {

	/**
	 * Returns a list of separation needs with the specified offender.
	 * @param offender offender
	 * @return list of separation needs
	 */
	List<SeparationNeed> findByOffender(Offender offender);

	/**
	 * Returns a list of separation needs with the specified target offender.
	 * @param offender offender
	 * @return list of separation needs
	 */
	List<SeparationNeed> findByTargetOffender(Offender offender);

	/**
	 * Retrieves the offender from the relationship object of the separation
	 * need.
	 * @param person person
	 * @return the offender
	 */
	Offender retreiveOffenderFromRelationship(Person person);

	/**
	 * Returns the matching separation need.
	 * 
	 * @param relationship relationship
	 * @param date date
	 * @return separation need
	 */
	SeparationNeed find(Relationship relationship, Date date);
	
	/**
	 * Returns the matching separation need excluding the specified separation
	 * need.
	 * 
	 * @param relationship relationship
	 * @param date date
	 * @param separationNeed separation need
	 * @return separation need
	 */
	SeparationNeed findExcluding(Relationship relationship, Date date,
			SeparationNeed separationNeed);
	
	/**
	 * Returns all separation needs for the specified relationship within
	 * the specified date range.
	 * 
	 * @param relationship relationship
	 * @param startDate start date
	 * @param endDate end date
	 * @return list of separation needs
	 */
	List<SeparationNeed> findInDateRange(Relationship relationship,
			Date startDate, Date endDate);
	
	/**
	 * Returns all separation needs for the specified relationship within the
	 * specified date range, excluding the specified separation need.
	 * 
	 * @param relationship relationship
	 * @param startDate start date
	 * @param endDate end date
	 * @param separationNeed separation need
	 * @return list of separation needs
	 */
	List<SeparationNeed> findInDateRangeExcluding(Relationship relationship,
			Date startDate, Date endDate, SeparationNeed separationNeed);
}