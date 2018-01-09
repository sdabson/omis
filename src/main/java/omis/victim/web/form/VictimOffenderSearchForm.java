package omis.victim.web.form;

import java.io.Serializable;

import omis.offender.web.form.OffenderSearchFields;

/**
 * Form to search for offenders.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 11, 2015)
 * @since OMIS 3.0
 */
public class VictimOffenderSearchForm
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private OffenderSearchFields offenderSearchFields;

	/** Instantiates form to search for offenders. */
	public VictimOffenderSearchForm() {
		// Default instantiation
	}

	/**
	 * Returns fields to search for offenders.
	 * 
	 * @return fields to search for offenders
	 */
	public OffenderSearchFields getOffenderSearchFields() {
		return this.offenderSearchFields;
	}

	/**
	 * Sets fields to search for offenders.
	 * 
	 * @param offenderSearchFields fields to search for offenders
	 */
	public void setOffenderSearchFields(
			final OffenderSearchFields offenderSearchFields) {
		this.offenderSearchFields = offenderSearchFields;
	}
}