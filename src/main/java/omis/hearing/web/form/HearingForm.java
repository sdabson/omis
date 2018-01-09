package omis.hearing.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.hearing.domain.HearingCategory;
import omis.hearing.domain.HearingStatusCategory;
import omis.hearing.domain.LocationType;
import omis.location.domain.Location;
import omis.staff.domain.StaffAssignment;

/**
 * HearingForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Apr 18, 2017)
 *@since OMIS 3.0
 *
 */
public class HearingForm {
	
	private HearingCategory category;
	
	private LocationType locationType;
	
	private Location location;
	
	private Date date;
	
	private HearingStatusCategory status;
	
	private StaffAssignment officer;
	
	private Boolean inAttendance;
	
	private List<StaffAttendanceItem> staffAttendanceItems =
			new ArrayList<StaffAttendanceItem>();
	
	private List<HearingNoteItem> hearingNoteItems =
			new ArrayList<HearingNoteItem>();
	
	private List<InfractionItem> infractionItems =
			new ArrayList<InfractionItem>();
	
	/**
	 * 
	 */
	public HearingForm() {
	}

	/**
	 * @return the category
	 */
	public HearingCategory getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(HearingCategory category) {
		this.category = category;
	}
	
	/**
	 * @return the locationType
	 */
	public LocationType getLocationType() {
		return locationType;
	}

	/**
	 * @param locationType the locationType to set
	 */
	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the status
	 */
	public HearingStatusCategory getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(HearingStatusCategory status) {
		this.status = status;
	}

	/**
	 * @return the officer
	 */
	public StaffAssignment getOfficer() {
		return officer;
	}

	/**
	 * @param officer the officer to set
	 */
	public void setOfficer(StaffAssignment officer) {
		this.officer = officer;
	}

	/**
	 * @return the inAttendance
	 */
	public Boolean getInAttendance() {
		return inAttendance;
	}

	/**
	 * @param inAttendance the inAttendance to set
	 */
	public void setInAttendance(Boolean inAttendance) {
		this.inAttendance = inAttendance;
	}

	/**
	 * @return the staffAttendanceItems
	 */
	public List<StaffAttendanceItem> getStaffAttendanceItems() {
		return staffAttendanceItems;
	}

	/**
	 * @param staffAttendanceItems the staffAttendanceItems to set
	 */
	public void setStaffAttendanceItems(List<StaffAttendanceItem> staffAttendanceItems) {
		this.staffAttendanceItems = staffAttendanceItems;
	}

	/**
	 * @return the hearingNoteItems
	 */
	public List<HearingNoteItem> getHearingNoteItems() {
		return hearingNoteItems;
	}

	/**
	 * @param hearingNoteItems the hearingNoteItems to set
	 */
	public void setHearingNoteItems(List<HearingNoteItem> hearingNoteItems) {
		this.hearingNoteItems = hearingNoteItems;
	}

	/**
	 * Returns the infractionItems
	 * @return infractionItems - List<InfractionItem>
	 */
	public List<InfractionItem> getInfractionItems() {
		return infractionItems;
	}

	/**
	 * Sets the infractionItems
	 * @param infractionItems - List<InfractionItem>
	 */
	public void setInfractionItems(List<InfractionItem> infractionItems) {
		this.infractionItems = infractionItems;
	}
	
	
}
