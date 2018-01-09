package omis.offenseterm.report;

import omis.person.domain.Person;

/** 
 * Service for offense term profile related operations.
 * 
 * @author Josh Divine
 * @version 0.1.0 (May 16, 2017)
 * @since OMIS 3.0 
 */
public interface OffenseTermProfileItemService {

	
	/**
	 * Returns the offense term count by person.
	 * 
	 * @param person person
	 * @return offense term count
	 */
	Integer findOffenseTermCountByPerson(Person person);
	
	/**
	 * Returns the current offense term count by person.
	 * 
	 * @param person person
	 * @return offense term count
	 */
	Integer findCurrentOffenseTermCountByPerson(Person person);
}
