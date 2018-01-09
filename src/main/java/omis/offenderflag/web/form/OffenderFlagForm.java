package omis.offenderflag.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Form for offender flags.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 10, 2014)
 * @since OMIS 3.0
 */
public class OffenderFlagForm
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<OffenderFlagItem> flagItems
		= new ArrayList<OffenderFlagItem>();
	
	/** Instantiates a form for offender flags. */
	public OffenderFlagForm() {
		// Default instantiation
	}

	/**
	 * Returns flag items.
	 * 
	 * @return flag items
	 */
	public List<OffenderFlagItem> getFlagItems() {
		return this.flagItems;
	}

	/**
	 * Sets flag items.
	 * 
	 * @param flagItems flag items
	 */
	public void setFlagItems(final List<OffenderFlagItem> flagItems) {
		this.flagItems = flagItems;
	}
}