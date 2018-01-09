package omis.incident.domain.component;

import java.io.Serializable;

import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.FacilityArea;
import omis.facility.domain.Level;
import omis.facility.domain.Room;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;

/**
 * Incident scene.
 * 
 * @author Yidong Li
 * @author Joel Norris
 * @version: 0.1.1 (October 6, 2015)
 * @since: OMIS 3.0
 *
 */
public class IncidentScene implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String location;
	private Facility facility;
	private Complex complex;
	private Unit unit;
	private Room room;
	private Level level;
	private Section section;
	private FacilityArea facilityArea;
	
	/** Instantiate an incident scene */
	public IncidentScene(){
		// default constructor
	}
	
	/**
	 * Instantiates an incident scene with the specified location information.
	 * 
	 * @param location location
	 * @param facility facility
	 * @param complex complex
	 * @param unit unit
	 * @param level level
	 * @param section section
	 * @param room room
	 * @param facilityArea facility area
	 */
	public IncidentScene(final String location, final Facility facility,
			final Complex complex, final Unit unit, final Level level,
			final Section section, final Room room,
			final FacilityArea facilityArea) {
		this.location = location;
		this.facility = facility;
		this.complex = complex;
		this.unit = unit;
		this.level = level;
		this.section = section;
		this.room = room;
		this.facilityArea = facilityArea;
	}
	
	/**
	 * Instantiates an incident scene with the location.
	 * 
	 * @param location location
	 */
	public IncidentScene(final String location) {
		this.location = location;
	}
	
	/** 
	 * Set location.
	 * 
	 * @param location location
	 */
	public void setLocation(final String location){
		this.location = location;
	}
	
	/**
	 * Get the location.
	 * 
	 * @return location
	 */
	public String getLocation(){
		return this.location;
	}
	
	/** 
	 * Set facility.
	 * 
	 * @param facility facility
	 */
	public void setFacility(final Facility facility){
		this.facility = facility;
	}
	
	/**
	 * Get the facility.
	 * 
	 * @return facility
	 */
	public Facility getFacility(){
		return this.facility;
	}
	
	/** 
	 * Set complex.
	 * 
	 * @param complex complex
	 */
	public void setComplex(final Complex complex){
		this.complex = complex;
	}
	
	/**
	 * Get the complex.
	 * 
	 * @return complex
	 */
	public Complex getComplex(){
		return this.complex;
	}
	
	/** 
	 * Set unit.
	 * 
	 * @param unit unit
	 */
	public void setUnit(final Unit unit){
		this.unit = unit;
	}
	
	/**
	 * Get the unit.
	 * 
	 * @return unit
	 */
	public Unit getUnit(){
		return this.unit;
	}
	
	/** 
	 * Set room.
	 * 
	 * @param room room
	 */
	public void setRoom(final Room room){
		this.room = room;
	}
	
	/**
	 * Get the room.
	 * 
	 * @return room
	 */
	public Room getRoom(){
		return this.room;
	}
	
	/** 
	 * Set level.
	 * 
	 * @param level level
	 */
	public void setLevel(final Level level){
		this.level = level;
	}
	
	/**
	 * Get the level.
	 * 
	 * @return level
	 */
	public Level getLevel(){
		return this.level;
	}
	
	/** 
	 * Set section.
	 * 
	 * @param section section
	 */
	public void setSection(final Section section){
		this.section = section;
	}
	
	/**
	 * Get the section.
	 * 
	 * @return section
	 */
	public Section getSection(){
		return this.section;
	}

	/**
	 * Return the facility area.
	 * 
	 * @return facility area
	 */
	public FacilityArea getFacilityArea() {
		return this.facilityArea;
	}

	/**
	 * Sets the facility area.
	 * 
	 * @param facilityArea facility area
	 */
	public void setFacilityArea(final FacilityArea facilityArea) {
		this.facilityArea = facilityArea;
	}
}