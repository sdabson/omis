package omis.bedplacement.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.bedplacement.domain.BedPlacement;
import omis.bedplacement.domain.BedPlacementReason;
import omis.facility.domain.Bed;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Level;
import omis.facility.domain.Room;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;

/**
 * Bed placement form.
 * 
 * @author Joel Norris
 * @version 0.1.1 (February 08, 2015)
 * @since OMIS 3.0
 */
public class BedPlacementForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Facility facility;
	
	private Complex complex;
	
	private Unit unit;
	
	private Level level;
	
	private Section section;
	
	private Room room;
	
	private Bed bed;
	
	private Date startDate;
	
	private Date startTime;
	
	private Date endDate;
	
	private Date endTime;
	
	private BedPlacementReason bedPlacementReason;
	
	private Boolean confirmed;
	
	private String description;
	
	private BedPlacement activeBedPlacement;
	
	private Boolean endActiveBedPlacement;
	
	/** Instantiates a default bed placement form. */
	public BedPlacementForm() {
		//Default constructor
	}
	
	/**
	 * Returns the facility.
	 * 
	 * @return facility
	 */
	public Facility getFacility() {
		return this.facility;
	}

	/**
	 * Sets the facility.
	 * 
	 * @param facility facility
	 */
	public void setFacility(final Facility facility) {
		this.facility = facility;
	}

	/**
	 * Returns the complex.
	 * 
	 * @return complex
	 */
	public Complex getComplex() {
		return this.complex;
	}

	/**
	 * Sets the complex.
	 * 
	 * @param complex complex
	 */
	public void setComplex(final Complex complex) {
		this.complex = complex;
	}

	/**
	 * Returns the unit.
	 * 
	 * @return unit
	 */
	public Unit getUnit() {
		return this.unit;
	}

	/**
	 * Sets the unit.
	 * 
	 * @param unit unit
	 */
	public void setUnit(final Unit unit) {
		this.unit = unit;
	}

	/**
	 * Returns the level.
	 * 
	 * @return level
	 */
	public Level getLevel() {
		return this.level;
	}

	/**
	 * Sets the level.
	 * 
	 * @param level level
	 */
	public void setLevel(final Level level) {
		this.level = level;
	}

	/**
	 * Returns the section.
	 * 
	 * @return section
	 */
	public Section getSection() {
		return this.section;
	}

	/**
	 * Sets the section.
	 * 
	 * @param section section
	 */
	public void setSection(final Section section) {
		this.section = section;
	}

	/**
	 * Returns the room.
	 * 
	 * @return room
	 */
	public Room getRoom() {
		return this.room;
	}

	/**
	 * Sets the room.
	 * 
	 * @param room room
	 */
	public void setRoom(final Room room) {
		this.room = room;
	}

	/**
	 * Returns the bed.
	 * 
	 * @return bed
	 */
	public Bed getBed() {
		return this.bed;
	}

	/**
	 * Sets the bed.
	 * 
	 * @param bed bed
	 */
	public void setBed(final Bed bed) {
		this.bed = bed;
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
	 * Returns the start time.
	 * 
	 * @return start time
	 */
	public Date getStartTime() {
		return this.startTime;
	}

	/**
	 * Sets the start time.
	 * 
	 * @param startTime start time
	 */
	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
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

	/**
	 * Returns the end time.
	 * 
	 * @return end time
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * Sets the end time.
	 * 
	 * @param endTime end time
	 */
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Returns the bed placement.
	 * 
	 * @return bed placement reason
	 */
	public BedPlacementReason getBedPlacementReason() {
		return this.bedPlacementReason;
	}

	/**
	 * Sets the bed placement reason.
	 * 
	 * @param bedPlacementReason bed placement reason
	 */
	public void setBedPlacementReason(
			final BedPlacementReason bedPlacementReason) {
		this.bedPlacementReason = bedPlacementReason;
	}

	/**
	 * Returns whether confirmed applies.
	 * 
	 * @return confirmed value
	 */
	public Boolean getConfirmed() {
		return this.confirmed;
	}

	/**
	 * Sets whether confirmed applies.
	 * 
	 * @param confirmed confirmed 
	 */
	public void setConfirmed(final Boolean confirmed) {
		this.confirmed = confirmed;
	}

	/**
	 * Returns the description for the form.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description for the form.
	 * 
	 * @param description description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Returns active bed placement.
	 * 
	 * @return active bed placement
	 */
	public BedPlacement getActiveBedPlacement() {
		return this.activeBedPlacement;
	}

	/**
	 * Sets active bed placement.
	 * 
	 * @param activeBedPlacement active bed placement
	 */
	public void setActiveBedPlacement(final BedPlacement activeBedPlacement) {
		this.activeBedPlacement = activeBedPlacement;
	}

	/**
	 * Returns whether end active bed placement applies.
	 * 
	 * @return end active bed placement
	 */
	public Boolean getEndActiveBedPlacement() {
		return this.endActiveBedPlacement;
	}

	/**
	 * Sets whether end active bed placement applies.
	 * 
	 * @param endActiveBedPlacement end active bed placement
	 */
	public void setEndActiveBedPlacement(final Boolean endActiveBedPlacement) {
		this.endActiveBedPlacement = endActiveBedPlacement;
	}
}