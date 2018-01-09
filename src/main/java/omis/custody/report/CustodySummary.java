package omis.custody.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Summary for custody.
 * @author Joel Norris 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Mar, 27 2013)
 * @since OMIS 3.0
 */
public class CustodySummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long reviewId;
	
	private final String custodyChangeReasonName;
	
	private final String custodyLevelName;
	
	private final Date actionDate;
	
	private final Date nextReviewDate;
	
	private final Long overrideId;
	
	private final String overrideCustodyLevelName;
	
	private final String authorizeName;
	
	private final boolean overriden;
	
	private final boolean overrideAuthorized;
	
	/**
	 * Instantiates a custody Summary.
	 * 
	 * @param reviewId review id
	 * @param custodyChangeReasonName custody change reason name
	 * @param custodyLevelName custody level name
	 * @param actionDate action date
	 * @param nextReviewDate next review date
	 * @param overrideId custody override id
	 * @param overrideCustodyLevelName custody override custody level name
	 * @param authorizeName authorize name
	 * @param overriden overriden
	 * @param overrideAuthorized override authorized
	 */
	public CustodySummary(final Long reviewId, 
			final String custodyChangeReasonName, final String custodyLevelName,
			final Date actionDate, final Date nextReviewDate, 
			final Long overrideId, final String overrideCustodyLevelName, 
			final String authorizeName, final boolean overriden, 
			final boolean overrideAuthorized) {
		this.reviewId = reviewId;
		this.custodyChangeReasonName = custodyChangeReasonName;
		this.custodyLevelName = custodyLevelName;
		this.actionDate = actionDate;
		this.nextReviewDate = nextReviewDate;
		this.overrideId = overrideId;
		this.overrideCustodyLevelName = overrideCustodyLevelName;
		this.authorizeName = authorizeName;
		this.overriden = overriden;
		this.overrideAuthorized = overrideAuthorized;
		System.out.println(String.valueOf(this.overriden) + " " 
				+ String.valueOf(this.overrideAuthorized));
	}
	

	public CustodySummary(
			final String custodyLevelName, 
			final Date actionDate) {
		this.reviewId = null;
		this.custodyChangeReasonName = null;
		this.overrideCustodyLevelName = null;
		this.custodyLevelName = custodyLevelName;
		this.actionDate = actionDate;
		this.nextReviewDate = null;
		this.overrideId = null;		
		this.authorizeName = null;
		this.overriden = null != null;
		this.overrideAuthorized = null != null;
	}

	/**
	 * Returns the custody review id.
	 * 
	 * @return the reviewId
	 */
	public Long getReviewId() {
		return this.reviewId;
	}

	/**
	 * Returns the custody change reason name.
	 * 
	 * @return the custodyChangeReasonName
	 */
	public String getCustodyChangeReasonName() {
		return this.custodyChangeReasonName;
	}

	/**
	 * Returns the custody level name.
	 * 
	 * @return the custodyLevelName
	 */
	public String getCustodyLevelName() {
		return this.custodyLevelName;
	}

	/**
	 * Returns the action date of the custody summary.
	 * 
	 * @return action date
	 */
	public Date getActionDate() {
		return this.actionDate;
	}

	/**
	 * Returns the next review date of the custody summary.
	 * 
	 * @return next review date
	 */
	public Date getNextReviewDate() {
		return this.nextReviewDate;
	}

	/**
	 * Returns the override id.
	 * 
	 * @return the overrideId
	 */
	public Long getOverrideId() {
		return this.overrideId;
	}

	/**
	 * Returns the override custody level name.
	 * 
	 * @return the overrideCustodyLevelName
	 */
	public String getOverrideCustodyLevelName() {
		return this.overrideCustodyLevelName;
	}

	/**
	 * Returns the authorizing user's name name.
	 * 
	 * @return the authorizeName
	 */
	public String getAuthorizeName() {
		return this.authorizeName;
	}

	/**
	 * Returns the value of overriden.
	 * 
	 * @return the overriden
	 */
	public boolean isOverriden() {
		return this.overriden;
	}

	/**
	 * Returns the value of authorized.
	 * 
	 * @return the authorized
	 */
	public boolean isOverrideAuthorized() {
		return this.overrideAuthorized;
	}
}