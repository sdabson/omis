package omis.substance.web.form;

import java.util.Date;

import omis.substance.domain.Substance;

/**
 * Substance use admission form.
 * @author Joel Norris
 * @version 0.1.0 (Jun 24, 2013)
 * @since OMIS 3.0
 */
public class SubstanceUseAdmissionForm {

	private Substance substance;
	
	private Boolean admission;
	
	private Date admissionDate;
	
	/**
	 * Instantiates a default instance of substance use admission form.
	 */
	public SubstanceUseAdmissionForm() {
		//Default constructor.
	}

	/**
	 * Return the substance of the substance use admission form.
	 * @return the substance
	 */
	public Substance getSubstance() {
		return this.substance;
	}

	/**
	 * Set the substance of the substance use admission form.
	 * @param substance the substance to set
	 */
	public void setSubstance(final Substance substance) {
		this.substance = substance;
	}

	/**
	 * Return the admission of the substance use admission form.
	 * @return the admission
	 */
	public Boolean getAdmission() {
		return this.admission;
	}

	/**
	 * Set the admission of the substance use admission form.
	 * @param admission the admission to set
	 */
	public void setAdmission(final Boolean admission) {
		this.admission = admission;
	}

	/**
	 * Return the admission date of the substance use admission form.
	 * @return the admissionDate
	 */
	public Date getAdmissionDate() {
		return this.admissionDate;
	}

	/**
	 * Set the admission date of the substance use admission form.
	 * @param admissionDate the admissionDate to set
	 */
	public void setAdmissionDate(final Date admissionDate) {
		this.admissionDate = admissionDate;
	}	
}