package omis.offenseterm.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import omis.courtcase.web.form.CourtCaseFields;

/**
 * Form for offense term.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class OffenseTermForm
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private CourtCaseFields fields = new CourtCaseFields();
	
	private List<OffenseItem> offenseItems
		= new ArrayList<OffenseItem>();
	
	/** Instantiates form for offense term. */
	public OffenseTermForm() {
		// Default instantiation
	}

	/**
	 * Sets fields for court case.
	 * 
	 * @param fields fields for court case
	 */
	public void setFields(final CourtCaseFields fields) {
		this.fields = fields;
	}
	
	/**
	 * Returns fields for court case.
	 * 
	 * @return fields for court case
	 */
	public CourtCaseFields getFields() {
		return this.fields;
	}

	/**
	 * Returns offense items.
	 * 
	 * @return offense items
	 */
	public List<OffenseItem> getOffenseItems() {
		return this.offenseItems;
	}

	/**
	 * Sets offense items.
	 * 
	 * @param offenseItems offense items
	 */
	public void setOffenseItems(
			final List<OffenseItem> offenseItems) {
		this.offenseItems = offenseItems;
	}
}