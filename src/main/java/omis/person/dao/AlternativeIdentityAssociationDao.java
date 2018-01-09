package omis.person.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.datatype.DateRange;
import omis.person.domain.AlternativeIdentityAssociation;
import omis.person.domain.AlternativeIdentityCategory;
import omis.person.domain.Person;
import omis.person.domain.PersonIdentity;

/**
 * Data access object for alternative identity associations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Nov 22, 2013)
 * @since OMIS 3.0
 */
public interface AlternativeIdentityAssociationDao
		extends GenericDao<AlternativeIdentityAssociation> {

	/**
	 * Returns alternative identity associations for person.
	 * 
	 * @param person person
	 * @return alternative identity associations for person
	 */
	List<AlternativeIdentityAssociation> findByPerson(Person person);
	
	/**
	 * Returns alternative identity associations for person active on date.
	 * 
	 * @param person person
	 * @param date date
	 * @return alternative identity associations for person active on date
	 */
	List<AlternativeIdentityAssociation> findByPersonOnDate(
			Person person, Date date);

	/**
	 * Returns the alternative identity association with the specified
	 * identity, category, and date range.
	 * 
	 * @param identity person identity
	 * @param category alternative identity category
	 * @param dateRange date range
	 * @return alternative identity association
	 */
	AlternativeIdentityAssociation find(PersonIdentity identity, 
			AlternativeIdentityCategory category, DateRange dateRange);
	
	/**
	 * Returns the alternative identity association with the specified
	 * identity, category, and date range, excluding the specified alternative
	 * identity association.
	 * 
	 * @param excluded alternative identity association
	 * @param identity person identity
	 * @param category alternative identity category
	 * @param dateRange date range
	 * @return alternative identity association
	 */
	AlternativeIdentityAssociation findExcluding(
			AlternativeIdentityAssociation association, PersonIdentity identity, 
			AlternativeIdentityCategory category, DateRange dateRange);
}