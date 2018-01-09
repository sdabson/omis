package omis.person.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.datatype.DateRange;
import omis.person.domain.AlternativeNameAssociation;
import omis.person.domain.AlternativeNameCategory;
import omis.person.domain.Person;
import omis.person.domain.PersonName;

/**
 * Data access object for alternative name associations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Nov 22, 2013)
 * @since OMIS 3.0
 */
public interface AlternativeNameAssociationDao
		extends GenericDao<AlternativeNameAssociation> {

	/**
	 * Returns alternative name association for person.
	 * 
	 * @param person person
	 * @return alternative name association for person
	 */
	List<AlternativeNameAssociation> findByPerson(Person person);
	
	/**
	 * Returns alternative name association for person on date.
	 * 
	 * @param person person
	 * @param date date
	 * @return alternative name association for person on date
	 */
	List<AlternativeNameAssociation> findByPersonOnDate(
			Person person, Date date);

	/**
	 * Returns whether an alternative name association exists with the
	 * specified name, category, and person
	 * 
	 * @param name person name
	 * @param category alternative name category
	 * @param person person
	 * @param dateRange date range
	 * @return whether an alternative name exists with the specified properties
	 */
	boolean find(PersonName name, AlternativeNameCategory category,
			Person person, DateRange dateRange);

	/**
	 * Returns whether another alternative name association exists with the
	 * specified name, category, and person other than the specified 
	 * alternative name association.
	 * 
	 * @param association alternative name association
	 * @param name person name
	 * @param category alternative name category
	 * @param person person
	 * @param dateRange date range
	 * @return whether another alternative name exists with the 
	 * specified properties
	 */
	boolean findExcluding(AlternativeNameAssociation association,
			PersonName name, AlternativeNameCategory category, Person person,
			DateRange dateRange);
}