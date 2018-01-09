package omis.person.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.person.domain.Person;
import omis.person.domain.PersonName;

/**
 * Data access object for person names.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Nov 6, 2013)
 * @since OMIS 3.0
 */
public interface PersonNameDao
		extends GenericDao<PersonName> {

	/**
	 * Returns alternative names for person.
	 * 
	 * <p>Alternative person names are names not equal to
	 * {@code person.getName()}.
	 * 
	 * @param person person
	 * @return alternative person names for person
	 */
	List<PersonName> findAlternativeNames(Person person);

	/**
	 * Returns whether a person name exists with the same specified properties
	 * for the specified person.
	 * 
	 * @param person person
	 * @param firstName first name
	 * @param lastName last name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @return whether matching name exists for person
	 */
	boolean find(Person person, String firstName, String lastName,
			String middleName, String suffix);

	/**
	 * Returns whether a person name exists with the same specified properties 
	 * excluding the specified name.
	 * 
	 * @param name person name
	 * @param firstName first name
	 * @param lastName last name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @return whether another matching name exists for person
	 */
	boolean findExcluding(PersonName name, String firstName, String lastName,
			String middleName, String suffix);
}