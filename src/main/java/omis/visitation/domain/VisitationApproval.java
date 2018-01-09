package omis.visitation.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Visitation approval.
 * 
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.1 (Aug 29, 2017)
 * @since OMIS 3.0
 */
public class VisitationApproval implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean approved;
	
	private Date decisionDate;
	
	/**
	 * Instantiates a default instance of visitation approval.
	 */
	public VisitationApproval() {
		//Default constructor.
	}
	
	/**
	 * Instantiates a visitation approval with the specified decision date
	 * and whether approval applies.
	 * 
	 * @param approved approved
	 * @param decisionDate decision date
	 */
	public VisitationApproval(final Boolean approved, final Date decisionDate) {
		this.approved = approved;
		this.decisionDate = decisionDate;
	}

	/**
	 * Returns whether approved applies.
	 * 
	 * @return approved
	 */
	public Boolean getApproved() {
		return this.approved;
	}

	/**
	 * Sets whether approved applies.
	 * 
	 * @param approved approved
	 */
	public void setApproved(final Boolean approved) {
		this.approved = approved;
	}

	/**
	 * Returns decision date.
	 * 
	 * @return decision date
	 */
	public Date getDecisionDate() {
		return this.decisionDate;
	}

	/**
	 * Sets decision date.
	 * 
	 * @param decisionDate
	 */
	public void setDecisionDate(final Date decisionDate) {
		this.decisionDate = decisionDate;
	}
}