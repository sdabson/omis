package omis.employment.web.form;

import java.io.Serializable;

import omis.offender.domain.Offender;

/**
 * Form to search for employer.
 *
 * @author Yidong Li
 * @version 0.0.1 (July 30, 2015)
 * @since OMIS 3.0
 */
public class EmployerSearchForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String employerName;
	
	private Offender offender;
	
	/** Instantiates employer search form. */
	public EmployerSearchForm() {
		// Default instantiation
	}

	/**
	 * Returns employer name.
	 * 
	 * @return employer name
	 */
	public String getEmployerName() {
		return this.employerName;
	}
	
	/**
	 * Sets employer name.
	 * 
	 * @param employer name
	 */
	public void setEmployerName(final String employerName) {
		this.employerName = employerName;
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
	 * @param offender
	 */
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}
}