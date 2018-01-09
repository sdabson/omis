package omis.placement.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.supervision.domain.PlacementTermChangeReason;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Form for supervisory organization changes.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class SupervisoryOrganizationChangeForm
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private SupervisoryOrganization supervisoryOrganization;
	
	private PlacementTermChangeReason changeReason;
	
	private Date effectiveDate;
	
	private Date effectiveTime;
	
	/** Instantiates form for supervisory organization changes. */
	public SupervisoryOrganizationChangeForm() {
		// Default instantiation
	}

	/**
	 * Returns supervisory organization.
	 * 
	 * @return supervisory organization
	 */
	public SupervisoryOrganization getSupervisoryOrganization() {
		return this.supervisoryOrganization;
	}

	/**
	 * Sets supervisory organization.
	 * 
	 * @param supervisoryOrganization supervisory organization
	 */
	public void setSupervisoryOrganization(
			final SupervisoryOrganization supervisoryOrganization) {
		this.supervisoryOrganization = supervisoryOrganization;
	}

	/**
	 * Returns change reason.
	 * 
	 * @return change reason
	 */
	public PlacementTermChangeReason getChangeReason() {
		return this.changeReason;
	}
	
	/**
	 * Sets placement term change reason.
	 * 
	 * @param changeReason change reason
	 */
	public void setChangeReason(final PlacementTermChangeReason changeReason) {
		this.changeReason = changeReason;
	}
	
	/**
	 * Returns effective date.
	 * 
	 * @return effective date
	 */
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	/**
	 * Sets effective date.
	 * 
	 * @param effectiveDate effective date
	 */
	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Returns effective time.
	 * 
	 * @return effective time
	 */
	public Date getEffectiveTime() {
		return this.effectiveTime;
	}

	/**
	 * Sets effective time.
	 * 
	 * @param effectiveTime effective time
	 */
	public void setEffectiveTime(final Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
}