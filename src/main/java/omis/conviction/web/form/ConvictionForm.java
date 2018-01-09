package omis.conviction.web.form;

import java.util.ArrayList;
import java.util.List;

/**
 * Form for sentences.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 17, 2013)
 * @since OMIS 3.0
 */
public class ConvictionForm {

	private List<ConvictionItem> convictionItems
		= new ArrayList<ConvictionItem>();
	
	/** Instantiates a default form for sentences. */
	public ConvictionForm() {
		// Default instantiation
	}

	/**
	 * Returns the conviction items.
	 * 
	 * @return conviction items
	 */
	public List<ConvictionItem> getConvictionItems() {
		return this.convictionItems;
	}

	/**
	 * Sets the conviction items.
	 * 
	 * @param convictionItems conviction items
	 */
	public void setConvictionItems(
			final List<ConvictionItem> convictionItems) {
		this.convictionItems = convictionItems;
	}
}