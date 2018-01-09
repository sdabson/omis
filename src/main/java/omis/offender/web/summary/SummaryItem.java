package omis.offender.web.summary;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import omis.offender.domain.Offender;

/** Summarize-able offender information.
 * @author Ryan Johns
 * @author Stephen Abson
 * @version 0.1.0 (Jul 27, 2015)
 * @since OMIS 3 */
public interface SummaryItem extends Serializable, Comparable<SummaryItem> {
		
	/** Gets included page.
	 * @return included page. */
	String getIncludedPageName();
	
	/** Gets order for comparison.
	 * @return order - order. */
	int getOrder();
	
	/**
	 * Returns whether enabled.
	 * @return whether enabled
	 */
	boolean getEnabled();
	
	/** Build.
	 * @param modelMap - model map.
	 * @param offender - offender.
	 * @param date - date. */
	void build(Map<String, Object> modelMap, Offender offender, Date date); 
	
}
