package omis.demographics.dao;

import omis.dao.GenericDao;
import omis.demographics.domain.PersonDemographics;
import omis.person.domain.Person;

/**
 * Data access object for person demographics.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 20, 2014)
 * @since OMIS 3.0
 */
public interface PersonDemographicsDao
		extends GenericDao<PersonDemographics> {

	/**
	 * Returns demographics for person.
	 * 
	 * @param person person
	 * @return demographics for person
	 */
	PersonDemographics find(Person person);
	
	/**
	 * Returns demographics based on demographics passed in.
	 *
	 * @param demographics demographics
	 * @return demographics
	 */
	public PersonDemographics findByDemographics(
			final PersonDemographics demographics);
}