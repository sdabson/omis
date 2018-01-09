package omis.need.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Case plan referral response.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 23, 2015)
 * @since OMIS 3.0
 */
public class CasePlanReferralResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date date;
	
	private ResponseCategory category;
	
	/**
	 * Instantiates a default instance of case plan referral response
	 */
	public CasePlanReferralResponse() {
		//Default constructor.
	}
	
	/**
	 * Instantiates a case plan referral response with the specified date and
	 * response category.
	 * 
	 * @param date date
	 * @param category response category
	 */
	public CasePlanReferralResponse(Date date, ResponseCategory category) {
		this.date = date;
		this.category = category;
	}

	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the response category.
	 * 
	 * @return response category
	 */
	public ResponseCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets the response category.
	 * 
	 * @param category category
	 */
	public void setCategory(final ResponseCategory category) {
		this.category = category;
	}
}