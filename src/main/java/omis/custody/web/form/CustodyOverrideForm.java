package omis.custody.web.form;

import omis.custody.domain.CustodyLevel;

/**
 * Custody Override Form.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 19 2013)
 * @since OMIS 3.0
 */
public class CustodyOverrideForm {

	private CustodyLevel custodyLevel;
	
	/** Instantiates a default instance of custody override form. */
	public CustodyOverrideForm() {
		//empty constructor
	}

	/**
	 * Returns the custody level of the custody override form.
	 * @return the custodyLevel
	 */
	public CustodyLevel getCustodyLevel() {
		return this.custodyLevel;
	}

	/**
	 * Sets the custody level of the custody override form.
	 * @param custodyLevel the custodyLevel to set
	 */
	public void setCustodyLevel(final CustodyLevel custodyLevel) {
		this.custodyLevel = custodyLevel;
	}
}