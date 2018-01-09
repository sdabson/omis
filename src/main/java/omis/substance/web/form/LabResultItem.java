package omis.substance.web.form;

import omis.substance.domain.Substance;
import omis.substancetest.domain.SubstanceTestResultValue;

/**
 * Crime Lab Result Item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jan 2, 2014)
 * @since OMIS 3.0
 */
public class LabResultItem {

	private SubstanceTestResultValue substanceTestResultValue;
	
	private Substance substance;
	
	private Boolean process;
	
	/**
	 * Instantiates a default instance of lab result item.
	 */
	public LabResultItem() {
		//Default constructor.
	}
	
	/**
	 * Instantiates a lab result item with the specified value, substance,
	 * and whether process applies.
	 * 
	 * @param substanceTestResultValue substance test result value
	 * @param substance substance
	 * @param process process
	 */
	public LabResultItem(
			final SubstanceTestResultValue substanceTestResultValue,
			final Substance substance, final Boolean process) {
		this.substanceTestResultValue = substanceTestResultValue;
		this.substance = substance;
		this.process = process;
	}

	/**
	 * Returns the substance test result option of the crime lab result item.
	 * 
	 * @return substance test result value
	 */
	public SubstanceTestResultValue getSubstanceTestResultValue() {
		return this.substanceTestResultValue;
	}

	/**
	 * Sets the substance test result option of the crime lab result item.
	 * 
	 * @param substanceTestResultvalue substance test result value
	 */
	public void setSubstanceTestResultValue(
			final SubstanceTestResultValue substanceTestResultOption) {
		this.substanceTestResultValue = substanceTestResultOption;
	}

	/**
	 * Returns the substance of the crime lab result option.
	 * @return substance
	 */
	public Substance getSubstance() {
		return this.substance;
	}

	/**
	 * Sets the substance of the crime lab result option.
	 * @param substance substance
	 */
	public void setSubstance(final Substance substance) {
		this.substance = substance;
	}

	/**
	 * Returns the process value of the crime lab result option.
	 * @return process
	 */
	public Boolean getProcess() {
		return this.process;
	}

	/**
	 * Sets the process value of the crime lab result option.
	 * @param process process
	 */
	public void setProcess(final Boolean process) {
		this.process = process;
	}
}