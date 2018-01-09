package omis.person.web.delegate;

import java.util.Date;
import java.util.List;
import java.util.Map;

import omis.person.domain.Person;
import omis.person.web.summary.PersonSummaryItem;
import omis.person.web.summary.PersonSummaryItemRegistry;

/**
 * Delegate to manage person summary items in a model.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 9, 2016)
 * @since OMIS 3.0
 */
public class PersonSummaryModelDelegate {

	/* Model keys. */
	
	private static final String PERSON_SUMMARY_ITEMS_MODEL_KEY
		= "personSummaryItems";
	
	/* Summary item registry. */
	
	private final PersonSummaryItemRegistry personSummaryItemRegistry;
	
	/* Constructor. */
	
	/**
	 * Instantiates a delegate to manage person summary items in a model.
	 */
	public PersonSummaryModelDelegate (
			final PersonSummaryItemRegistry personSummaryItemRegistry) {
		this.personSummaryItemRegistry = personSummaryItemRegistry;
	}
	
	/* Model management methods. */
	
	/**
	 * Adds person summary to model.
	 * 
	 * @param map map
	 * @param person person
	 * @param date date
	 */
	public void add(final Map<String, Object> map, final Person person,
			final Date date) {
		List<PersonSummaryItem> personSummaryItems 
			= this.personSummaryItemRegistry.getItems();
		map.put(PERSON_SUMMARY_ITEMS_MODEL_KEY, personSummaryItems);
		for (PersonSummaryItem item : personSummaryItems) {
			item.build(map, person, date);
		}
	}
}