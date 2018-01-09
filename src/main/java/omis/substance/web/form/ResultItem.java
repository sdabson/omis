package omis.substance.web.form;

import java.io.Serializable;

import omis.substance.domain.Substance;
import omis.substancetest.domain.SubstanceTestResultValue;

/**
 * Result item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 10, 2013)
 * @since OMIS 3.0
 */
public class ResultItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private SubstanceTestResultValue substanceTestResultValue;
	
	private Substance substance;
	
	private Boolean admit;
	
	private Boolean admitPrior;
	
	private SubstanceTestResultValue defaultResultValue;
	
	private Boolean process;

	/**
	 * Return the substance test result value of the result item.
	 * 
	 * @return substance test result value
	 */
	public SubstanceTestResultValue getSubstanceTestResultValue() {
		return this.substanceTestResultValue;
	}

	/**
	 * Set the substance test result option of the result item.
	 * 
	 * @param substanceTestResultValue substance test result option
	 */
	public void setSubstanceTestResultValue(
			final SubstanceTestResultValue substanceTestResultValue) {
		this.substanceTestResultValue = substanceTestResultValue;
	}

	/**
	 * Return the substance of the result item.
	 * @return substance
	 */
	public Substance getSubstance() {
		return this.substance;
	}

	/**
	 * Set the substance of the result item.
	 * @param substance substance
	 */
	public void setSubstance(final Substance substance) {
		this.substance = substance;
	}

	/**
	 * Return the admit of the result item.
	 * @return admit
	 */
	public Boolean getAdmit() {
		return this.admit;
	}

	/**
	 * Set the admit of the result item.
	 * @param admit admit
	 */
	public void setAdmit(final Boolean admit) {
		this.admit = admit;
	}

	/**
	 * Sets the admit prior value of the result item.
	 * @return admit prior
	 */
	public Boolean getAdmitPrior() {
		return this.admitPrior;
	}

	/**
	 * Returns the admit prior value of the result item.
	 * @param admitPrior admit prior
	 */
	public void setAdmitPrior(final Boolean admitPrior) {
		this.admitPrior = admitPrior;
	}

	/**
	 * Returns the default result value.
	 * 
	 * @return default result value
	 */
	public SubstanceTestResultValue getDefaultResultValue() {
		return this.defaultResultValue;
	}

	/**
	 * Sets the default result value.
	 * 
	 * @param defaultResultValue default result value
	 */
	public void setDefaultResultValue(
			final SubstanceTestResultValue defaultResultValue) {
		this.defaultResultValue = defaultResultValue;
	}

	/**
	 * Return the process value of the result item.
	 * @return process
	 */
	public Boolean getProcess() {
		return this.process;
	}

	/**
	 * Set the process value of the result item.
	 * @param process process
	 */
	public void setProcess(final Boolean process) {
		this.process = process;
	}
}