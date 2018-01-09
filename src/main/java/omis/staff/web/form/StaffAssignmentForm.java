package omis.staff.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.location.domain.Location;
import omis.organization.domain.Organization;
import omis.person.domain.Person;
import omis.staff.domain.StaffTitle;

/**
 * Form for staff assignments.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 5, 2014)
 * @since OMIS 3.0
 */
public class StaffAssignmentForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Person staffMember;
	
	private Organization supervisoryOrganization;
	
	private Location location;
	
	private StaffTitle title;
	
	private Boolean supervisory;
	
	private Date startDate;
	
	private Date endDate;
	
	/** Instantiates a form for staff assignments. */
	public StaffAssignmentForm() {
		// Default instantiation
	}

	/**
	 * Returns the staff member.
	 * 
	 * @return staff member
	 */
	public Person getStaffMember() {
		return this.staffMember;
	}

	/**
	 * Sets the staff member.
	 * 
	 * @param staffMember staff member
	 */
	public void setStaffMember(final Person staffMember) {
		this.staffMember = staffMember;
	}

	/**
	 * Returns the supevisory organization.
	 * 
	 * @return supervisory organization
	 */
	public Organization getSupervisoryOrganization() {
		return this.supervisoryOrganization;
	}

	/**
	 * Sets the supervisory organization.
	 * 
	 * @param supervisoryOrganization supervisory organization
	 */
	public void setSupervisoryOrganization(
			final Organization supervisoryOrganization) {
		this.supervisoryOrganization = supervisoryOrganization;
	}

	/**
	 * Returns the location.
	 * 
	 * @return location
	 */
	public Location getLocation() {
		return this.location;
	}

	/**
	 * Sets the location.
	 * 
	 * @param location location
	 */
	public void setLocation(final Location location) {
		this.location = location;
	}

	/**
	 * Returns the title.
	 * 
	 * @return title
	 */
	public StaffTitle getTitle() {
		return this.title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title title
	 */
	public void setTitle(final StaffTitle title) {
		this.title = title;
	}

	/**
	 * Returns whether the assignment is supervisory.
	 * 
	 * @return whether assignment is supervisory
	 */
	public Boolean getSupervisory() {
		return this.supervisory;
	}

	/**
	 * Sets whether the assignment is supervisory.
	 * 
	 * @param supervisory whether assignment is supervisory
	 */
	public void setSupervisory(final Boolean supervisory) {
		this.supervisory = supervisory;
	}

	/**
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
}