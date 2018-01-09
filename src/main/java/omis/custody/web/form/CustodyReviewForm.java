package omis.custody.web.form;

import java.util.Date;

import omis.custody.domain.CustodyChangeReason;
import omis.custody.domain.CustodyLevel;

/**
 * Custody Review Form.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 19 2013)
 * @since OMIS 3.0
 */
public class CustodyReviewForm {
	
	private Date actionDate;
	
	private Date nextReviewDate;
	
	private CustodyChangeReason changeReason;
	
	private CustodyLevel custodyLevel;

	/** Instantiates a default instance custody review form. */
	public CustodyReviewForm() {
		//empty constructor
	}
	
	/**
	 * Returns the action date of the custody review form.
	 * @return action date
	 */
	public Date getActionDate() {
		return this.actionDate;
	}

	/**
	 * Sets the action date of the custody review form.
	 * @param actionDate action date
	 */
	public void setActionDate(final Date actionDate) {
		this.actionDate = actionDate;
	}

	/**
	 * Returns the next review date of the custody review form.
	 * @return next review date
	 */
	public Date getNextReviewDate() {
		return this.nextReviewDate;
	}

	/**
	 * Sets the next review date of the custody review form.
	 * @param nextReviewDate next review date
	 */
	public void setNextReviewDate(final Date nextReviewDate) {
		this.nextReviewDate = nextReviewDate;
	}

	/**
	 * Returns the change reason for the custody review form.
	 * @return the changeReason
	 */
	public CustodyChangeReason getChangeReason() {
		return this.changeReason;
	}

	/**
	 * Sets the change reason for the custody review form.
	 * @param changeReason the changeReason to set
	 */
	public void setChangeReason(final CustodyChangeReason changeReason) {
		this.changeReason = changeReason;
	}

	/**
	 * Returns the custody level for the custody review form.
	 * @return the level
	 */
	public CustodyLevel getCustodyLevel() {
		return this.custodyLevel;
	}

	/**
	 * Sets the custody level for the custody review form.
	 * @param custodyLevel the level to set
	 */
	public void setCustodyLevel(final CustodyLevel custodyLevel) {
		this.custodyLevel = custodyLevel;
	}
}