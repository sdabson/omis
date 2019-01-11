package omis.violationevent.domain;

/**
 * Violation Event Category.
 * 
 *@author Annie Wahl
 *@version 0.1.1 (Dec 13, 2018)
 *@since OMIS 3.0
 *
 */
public enum ViolationEventCategory {
	
	/** 
	 * Supervision.
	 */
	SUPERVISION(false),
	
	/** 
	 * Disciplinary.
	 */
	DISCIPLINARY(true);
	
	private Boolean valid;
	
	/**
	 * Constructor for Violation Event Category.
	 * 
	 * @param valid - valid
	 */
	ViolationEventCategory(final Boolean valid) {
		this.valid = valid;
	}
	
	/**
	 * Returns the instance name.
	 * 
	 * @return instance name
	 */
	public String getName() {
		return this.name();
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	
	@Override
	public String toString() {
		return this.name();
	}

	/**
	 * Returns the valid.
	 *
	 * @return valid
	 */
	public Boolean getValid() {
		return this.valid;
	}
}
