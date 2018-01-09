package omis.health.web.form;

import java.util.ArrayList;
import java.util.List;

import omis.facility.domain.Facility;
import omis.offender.domain.Offender;

/**
 * Create lab works form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sep 9, 2014)
 * @since OMIS 3.0
 */
public class CreateLabWorksForm {

	private Offender offender;
	
	private Facility facility;
	
	private List<LabWorkItem> labWorkItems = new ArrayList<LabWorkItem>();
	
	private Boolean offenderRequired;

	/**
	 * Instantiates a default instance of create lab works form.
	 */
	public CreateLabWorksForm() {
		//Default constructor.
	}
	
	/**
	 * Returns the offender.
	 * 
	 * @return offender
	 */
	public Offender getOffender() {
		return this.offender;
	}

	/**
	 * Sets the offender.
	 * 
	 * @param offender offender
	 */
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/**
	 * Returns the facility.
	 * 
	 * @return facility
	 */
	public Facility getFacility() {
		return this.facility;
	}

	/**
	 * Sets the facility.
	 * 
	 * @param facility facility
	 */
	public void setFacility(final Facility facility) {
		this.facility = facility;
	}

	/**
	 * Returns a list lab work items.
	 * 
	 * @return list of lab work items
	 */
	public List<LabWorkItem> getLabWorkItems() {
		return this.labWorkItems;
	}

	/**
	 * Sets a list of lab work items.
	 * 
	 * @param labWorkItems lab work items
	 */
	public void setLabWorkItems(final List<LabWorkItem> labWorkItems) {
		this.labWorkItems = labWorkItems;
	}

	/**
	 * Returns whether the offender information is required.
	 * 
	 * @return offender required
	 */
	public Boolean getOffenderRequired() {
		return this.offenderRequired;
	}

	/**
	 * Sets whether the offender information is required.
	 * 
	 * @param offenderRequired offender required
	 */
	public void setOffenderRequired(final Boolean offenderRequired) {
		this.offenderRequired = offenderRequired;
	}
}