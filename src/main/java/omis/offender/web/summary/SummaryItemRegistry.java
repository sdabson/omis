package omis.offender.web.summary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Registry for summary items. This registry guarantees items are sorted 
 * according to their {@link Comparable Natural Ordering}.
 * @author Ryan Johns
 * @version 0.1.0 (Jul 27, 2015)
 * @since OMIS 3.0 */
public class SummaryItemRegistry {
	private final List<SummaryItem> summaryItems;
	
	/** Constructor. */
	public SummaryItemRegistry() {
		this.summaryItems = new ArrayList<SummaryItem>(1);
	}
	
	/** Register summary item.
	 * @param summaryItem - summary item. 
	 * @return true if registry has changed as a result.*/
	public boolean register(final SummaryItem summaryItem) {
		boolean result = this.summaryItems.
				add(summaryItem);
		Collections.sort(summaryItems);
		return result;
	}

	/** Gets list of summary items. 
	 * @return list of summary items. */
	public List<SummaryItem> getItems() {
		return this.summaryItems;
	}
}
