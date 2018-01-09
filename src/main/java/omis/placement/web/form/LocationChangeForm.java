package omis.placement.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.location.domain.Location;
import omis.locationterm.domain.LocationReason;

/**
 * Form for location changes.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Mar 6, 2015)
 * @since OMIS 3.0
 */
public class LocationChangeForm
		implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Location location;
	
	private LocationReason reason;
	
	private Date effectiveDate;
	
	private Date effectiveTime;
	
	private Boolean endDateAllowed;
	
	private Date endDate;
	
	private Date endTime;
	
	/** Instantiates a default form for location changes. */
	public LocationChangeForm() {
		// Default instantiation
	}

	/**
	 * Returns location.
	 * 
	 * @return location
	 */
	public Location getLocation() {
		return this.location;
	}

	/**
	 * Sets location.
	 * 
	 * @param location location
	 */
	public void setLocation(final Location location) {
		this.location = location;
	}

	/**
	 * Returns reason.
	 * 
	 * @return reason
	 */
	public LocationReason getReason() {
		return this.reason;
	}
	
	/**
	 * Sets reason.
	 * 
	 * @param reason reason
	 */
	public void setReason(final LocationReason reason) {
		this.reason = reason;
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

	/**
	 * Returns whether end date is allowed.
	 * 
	 * @return whether end date is allowed
	 */
	public Boolean getEndDateAllowed() {
		return this.endDateAllowed;
	}

	/**
	 * Sets whether end date is allowed.
	 * 
	 * @param endDateAllowed whether end date is allowed
	 */
	public void setEndDateAllowed(final Boolean endDateAllowed) {
		this.endDateAllowed = endDateAllowed;
	}

	/**
	 * Returns end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets end date.
	 * 
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns end time.
	 * 
	 * @return end time
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * Sets end time.
	 * 
	 * @param endTime end time
	 */
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}
}