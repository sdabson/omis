package omis.offense.web.form;

/**
 * Offense Form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 29, 2013)
 * @since OMIS 3.0
 */
public class OffenseForm {
	
	private String violationCode;
	
	private String name;
	
	/** Instantiates a default instance of offense form. */
	public OffenseForm() {
		//default constructor
	}

	/**
	 * Returns the violation code of the form.
	 * @return the violationCode
	 */
	public String getViolationCode() {
		return this.violationCode;
	}

	/**
	 * Sets the violation code of the form.
	 * 
	 * @param violationCode the violationCode to set
	 */
	public void setViolationCode(final String violationCode) {
		this.violationCode = violationCode;
	}

	/**
	 * Returns the name of the form.
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the form.
	 * 
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}	
}