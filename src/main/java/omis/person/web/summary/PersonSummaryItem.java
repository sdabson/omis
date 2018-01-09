package omis.person.web.summary;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import omis.person.domain.Person;

/**
 * Summary item for person information.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 9, 2016)
 * @since OMIS 3.0
 */
public interface PersonSummaryItem
	extends Serializable, Comparable<PersonSummaryItem> {
	
	/**
	 * Returns included page name.
	 * 
	 * @return included page name
	 */
	String getIncludedPageName();
	
	/**
	 * Returns the order.
	 * 
	 * @return order
	 */
	int getOrder();
	
	/**
	 * Builds the person summary item.
	 * 
	 * @param map map
	 * @param person person
	 * @param date date
	 */
	void build(Map<String, Object> map, Person person, Date date);
}