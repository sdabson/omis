package omis.docket.dao;

import java.util.List;

import omis.court.domain.Court;
import omis.dao.GenericDao;
import omis.docket.domain.Docket;
import omis.person.domain.Person;

/**
 * Data access object for dockets.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface DocketDao
		extends GenericDao<Docket> {

	/**
	 * Returns docket or {@code null} if not found.
	 * 
	 * @param person person
	 * @param court court
	 * @param value value
	 * @return docket or {@code null} if not found
	 */
	Docket find(Person person, Court court, String value);
	
	/**
	 * Returns docket or {@code null} if not found or excluded.
	 * 
	 * @param person person
	 * @param court court
	 * @param value value
	 * @param excludedDockets dockets to exclude
	 * @return docket or {@code null} if not found or excluded
	 */
	Docket findExcluding(Person person, Court court, String value,
			Docket... excludedDockets);
	
	/**
	 * Returns a list of dockets for the specified Person
	 * @param person - person
	 * @return List of dockets for the specified person
	 */
	List<Docket> findByPerson(Person person);
}