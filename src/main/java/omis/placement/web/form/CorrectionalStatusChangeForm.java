package omis.placement.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.location.domain.Location;
import omis.locationterm.domain.LocationReason;
import omis.program.domain.Program;
import omis.supervision.domain.PlacementTermChangeReason;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Form to change correctional status.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 19, 2014)
 * @since OMIS 3.0
 */
public class CorrectionalStatusChangeForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date effectiveDate;
	
	private Date effectiveTime;
	
	private boolean supervisoryOrganizationRequired;
	
	private SupervisoryOrganization supervisoryOrganization;
	
	private boolean locationRequired;
	
	private Location location;
	
	private LocationReason locationReason;
	
	private PlacementTermChangeReason changeReason;
	
	private boolean programAllowed;
	
	private Program program;
	
	private boolean endDateAllowed;
	
	private Date endDate;
	
	private Date endTime;
	
	private Boolean endLocationAllowed;
	
	private Boolean endLocation;
	
	/** Form to change correctional status. */
	public CorrectionalStatusChangeForm() {
		// Default instantiation
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
	 * Returns effective time.
	 * 
	 * @param effectiveTime effective time
	 */
	public void setEffectiveTime(final Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	/**
	 * Returns whether supervisory organization is required.
	 * 
	 * @return whether supervisory organization is required
	 */
	public boolean getSupervisoryOrganizationRequired() {
		return this.supervisoryOrganizationRequired;
	}

	/**
	 * Sets whether supervisory organization is required.
	 * 
	 * @param supervisoryOrganizationRequired whether supervisory organization
	 * is required
	 */
	public void setSupervisoryOrganizationRequired(
			final boolean supervisoryOrganizationRequired) {
		this.supervisoryOrganizationRequired = supervisoryOrganizationRequired;
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
	 * Returns whether location is required.
	 * 
	 * @return whether location is required
	 */
	public boolean getLocationRequired() {
		return this.locationRequired;
	}

	/**
	 * Sets whether location is required.
	 * 
	 * @param locationRequired whether location is required
	 */
	public void setLocationRequired(final boolean locationRequired) {
		this.locationRequired = locationRequired;
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
	 * @param location facility
	 */
	public void setLocation(final Location location) {
		this.location = location;
	}

	/**
	 * Returns location reason.
	 * 
	 * @return location reason
	 */
	public LocationReason getLocationReason() {
		return locationReason;
	}

	/**
	 * Sets location reason.
	 * 
	 * @param locationReason location reason
	 */
	public void setFacilityReason(final LocationReason locationReason) {
		this.locationReason = locationReason;
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
	 * Sets change reason.
	 * 
	 * @param changeReason change reason
	 */
	public void setChangeReason(
			final PlacementTermChangeReason changeReason) {
		this.changeReason = changeReason;
	}
	
	/**
	 * Returns whether program is allowed.
	 * 
	 * @return whether program is allowed
	 */
	public boolean getProgramAllowed() {
		return this.programAllowed;
	}

	/**
	 * Sets whether program is allowed.
	 * 
	 * @param programAllowed whether program is allowed
	 */
	public void setProgramAllowed(final boolean programAllowed) {
		this.programAllowed = programAllowed;
	}

	/**
	 * Returns program.
	 * 
	 * @return program
	 */
	public Program getProgram() {
		return this.program;
	}
	
	/**
	 * Sets program.
	 * 
	 * @param program program
	 */
	public void setProgram(final Program program) {
		this.program = program;
	}
	
	/**
	 * Returns whether end date is allowed.
	 * 
	 * @return whether end date is allowed
	 */
	public boolean getEndDateAllowed() {
		return this.endDateAllowed;
	}

	/**
	 * Sets whether end date is allowed.
	 * 
	 * @param endDateAllowed whether end date is allowed
	 */
	public void setEndDateAllowed(final boolean endDateAllowed) {
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

	/**
	 * Returns whether ending location is allowed.
	 * 
	 * @return whether ending location is allowed
	 */
	public Boolean getEndLocationAllowed() {
		return this.endLocationAllowed;
	}

	/**
	 * Sets whether ending location is allowed.
	 * 
	 * @param endLocationAllowed whether ending location is allowed
	 */
	public void setEndLocationAllowed(final Boolean endLocationAllowed) {
		this.endLocationAllowed = endLocationAllowed;
	}

	/**
	 * Returns whether to end location.
	 * 
	 * @return whether to end location 
	 */
	public Boolean getEndLocation() {
		return this.endLocation;
	}

	/**
	 * Sets whether to end location.
	 * 
	 * @param endLocation whether to end location
	 */
	public void setEndLocation(final Boolean endLocation) {
		this.endLocation = endLocation;
	}
}