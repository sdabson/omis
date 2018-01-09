package omis.visitation.web.form;

import omis.visitation.domain.VisitMethod;

/**
 * Badge Number Form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 19, 2013)
 * @since OMIS 3.0
 */
public class VisitorCheckInForm {

	private String badgeNumber;
	private String notes;
	private VisitMethod method;
	
	/**
	 * Instantiates a default instance of badge number form.
	 */
	public VisitorCheckInForm() {
		//Default constructor.
	}

	/**
	 * Return the badge number of the badge number form.
	 * @return badge number
	 */
	public String getBadgeNumber() {
		return this.badgeNumber;
	}

	/**
	 * Set the badge number of the badge number form.
	 * @param badgeNumber badge number
	 */
	public void setBadgeNumber(final String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}
	
	/**
	 * Returns the visit method.
	 * 
	 * @return visit method
	 */
	public VisitMethod getMethod() {
		return this.method;
	}
	
	/**
	 * Sets the visit method.
	 * 
	 * @param method visit method
	 */
	public void setMethod(final VisitMethod method) {
		this.method = method;
	}

	/**
	 * Returns notes.
	 * 
	 * @return notes
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * Sets notes.
	 * 
	 * @param notes notes
	 */
	public void setNotes(final String notes) {
		this.notes = notes;
	}
}