package omis.person.web.summary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Registry for person summary items. Upon successful registry of an item,
 * items are sorted according to their {@link Comparable Natural Ordering}.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 10, 2016)
 * @since OMIS 3.0
 */
public class PersonSummaryItemRegistry {

	/* Summary item list. */
	
	private final List<PersonSummaryItem> personSummaryItems;
	
	/* Constructors. */
	
	/**
	 * Instantiates an instance of person summary item registry, initializing 
	 * {@code this} collection of person summary items.
	 */
	public PersonSummaryItemRegistry() {
		this.personSummaryItems = new ArrayList<PersonSummaryItem>();
	}
	
	/**
	 * Registers the specified summary item, and sorts {@code this} collection
	 * of person summary items.
	 *  
	 * @param personSummaryItem person summary item
	 * @return result of attempt to change registry
	 */
	public boolean register(final PersonSummaryItem personSummaryItem) {
		boolean result = this.personSummaryItems.add(personSummaryItem);
		if (result) {
			Collections.sort(this.personSummaryItems);
		}
		return result;
	}
	
	/**
	 * Returns person summary items.
	 * 
	 * @return list of person summary items
	 */
	public List<PersonSummaryItem> getItems() {
		return this.personSummaryItems;
	}
}