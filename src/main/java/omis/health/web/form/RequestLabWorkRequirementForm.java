package omis.health.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Form to request lab work requirements.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 4, 2014)
 * @since OMIS 3.0
 */
public class RequestLabWorkRequirementForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<LabWorkRequirementRequestItem> labWorkRequirementRequestItems
		= new ArrayList<LabWorkRequirementRequestItem>();
	
	/** Instantiates a form to request lab work requirements. */
	public RequestLabWorkRequirementForm() {
		// Default instantiation
	}

	/**
	 * Returns the lab work requirement request items.
	 * 
	 * @return lab work requirement request items
	 */
	public List<LabWorkRequirementRequestItem>
	getLabWorkRequirementRequestItems() {
		return this.labWorkRequirementRequestItems;
	}

	/**
	 * Sets the lab work requirement request items.
	 * 
	 * @param labWorkRequirementRequestItems lab work requirement request items
	 */
	public void setLabWorkRequirementRequestItems(
			final List<LabWorkRequirementRequestItem>
			labWorkRequirementRequestItems) {
		this.labWorkRequirementRequestItems = labWorkRequirementRequestItems;
	}
}