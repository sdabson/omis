package omis.health.web.form;

import java.io.Serializable;

import omis.health.domain.HealthRequestCategory;
import omis.health.domain.ProviderLevel;
import omis.offender.domain.Offender;

/**
 * Form for health requests.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 16, 2014)
 * @since OMIS 3.0
 */
public class HealthRequestForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Offender offender;
	
	private Boolean offenderRequired;
	
	private HealthRequestCategory category;
	
	private Boolean labsRequired;
	
	private Boolean asap;
	
	private ProviderLevel providerLevel;
	
	private String notes;
	
	/** Instantiates a form for health requests. */
	public HealthRequestForm() {
		// Default instantiation
	}

	/**
	 * Returns offender.
	 * 
	 * @return offender
	 */
	public Offender getOffender() {
		return this.offender;
	}

	/**
	 * Sets offender.
	 * 
	 * @param offender offender
	 */
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/**
	 * Returns whether offender is required.
	 * 
	 * @return whether offender is required
	 */
	public Boolean getOffenderRequired() {
		return this.offenderRequired;
	}

	/**
	 * Sets whether offender is required.
	 * 
	 * @param offenderRequired whether offender is required
	 */
	public void setOffenderRequired(final Boolean offenderRequired) {
		this.offenderRequired = offenderRequired;
	}

	/**
	 * Returns the category.
	 * 
	 * @return category
	 */
	public HealthRequestCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets the category.
	 * 
	 * @param category category
	 */
	public void setCategory(final HealthRequestCategory category) {
		this.category = category;
	}

	/**
	 * Returns whether labs are required.
	 * 
	 * @return whether labs are required
	 */
	public Boolean getLabsRequired() {
		return this.labsRequired;
	}

	/**
	 * Sets whether labs are required.
	 * 
	 * @param labsRequired whether labs are required
	 */
	public void setLabsRequired(final Boolean labsRequired) {
		this.labsRequired = labsRequired;
	}

	/**
	 * Returns whether the referral is to be scheduled ASAP.
	 * 
	 * @return whether referral is to be scheduled ASAP
	 */
	public Boolean getAsap() {
		return this.asap;
	}

	/**
	 * Sets whether the referral is to be scheduled ASAP.
	 * 
	 * @param asap whether referral is to be scheduled ASAP
	 */
	public void setAsap(final Boolean asap) {
		this.asap = asap;
	}

	/**
	 * Returns the provider level.
	 * 
	 * @return provider level
	 */
	public ProviderLevel getProviderLevel() {
		return this.providerLevel;
	}

	/**
	 * Sets the provider level.
	 * 
	 * @param providerLevel provider level
	 */
	public void setProviderLevel(final ProviderLevel providerLevel) {
		this.providerLevel = providerLevel;
	}

	/**
	 * Returns the notes.
	 * 
	 * @return notes
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * Sets the notes.
	 * 
	 * @param notes notes
	 */
	public void setNotes(final String notes) {
		this.notes = notes;
	}
}