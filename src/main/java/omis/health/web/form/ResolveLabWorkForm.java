package omis.health.web.form;

import java.util.ArrayList;
import java.util.List;

/**
 * Form for lab works.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 2, 2014)
 * @since OMIS 3.0
 */
public class ResolveLabWorkForm {

	private List<ResolveLabWorkItem> resolveLabWorkItems
		= new ArrayList<ResolveLabWorkItem>();
	
	/** Instantiates a default form for lab works. */
	public ResolveLabWorkForm() {
		// Default instantiation
	}

	/**
	 * Returns the resolve lab work items.
	 * 
	 * @return resolve lab work items
	 */
	public List<ResolveLabWorkItem> getLabWorkItems() {
		return this.resolveLabWorkItems;
	}

	/**
	 * Sets the lab work items.
	 * 
	 * @param resolveLabWorkItem resolveLabWorkItems
	 */
	public void setLabWorkItems(
			final List<ResolveLabWorkItem> resolveLabWorkItems) {
		this.resolveLabWorkItems = resolveLabWorkItems;
	}
	
}