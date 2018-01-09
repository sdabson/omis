package omis.bedplacement.report;

import java.io.Serializable;

/** Bed placement summary.
 * @author Ryan Johns
 * @version 0.1.0 (Jul 27, 2015)
 * @since OMIS 3.0 */
public class BedPlacementSummary implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Long bedPlacementId;
	private final Integer bedNumber;
	private final String roomName;
	private final String sectionName;
	private final String unitName;
	private final String levelName;
	private final String complexName;
	private final String facilityName;
	//private final boolean confirmed;
	
	/** Constructor.
	 * @param bedPlacementId - bed placement id.
	 * @param bedNumber - bed number.
	 * @param roomName - room name. 
	 * @param sectionName - section name.
	 * @param unitName - unit name.
	 * @param levelName - level name. 
	 * @param complexName - complex name.
	 * @param facilityName - facility name. */
	public BedPlacementSummary(final Long bedPlacementId,
			final Integer bedNumber,
			final String roomName, final String sectionName,
			final String unitName, final String levelName,
			final String complexName, final String facilityName) {
		this.bedPlacementId = bedPlacementId;
		this.bedNumber = bedNumber;
		this.roomName = roomName;
		this.sectionName = sectionName;
		this.unitName = unitName;
		this.levelName = levelName;
		this.complexName = complexName;
		this.facilityName = facilityName;
	}
	
	/** Gets bed placement id. 
	 * @return bed placement id.*/
	public Long getBedPlacementId() { 
		return this.bedPlacementId; 
	}
	
	/** Gets bed number.
	 * @return bed number. */
	public Integer getBedNumber() { 
		return this.bedNumber; 
	}
	
	/** Gets room name.
	 * @return room name. */
	public String getRoomName() { 
		return this.roomName; 
	}
	
	/** Gets section name.
	 * @return section name. */
	public String getSectionName() { 
		return this.sectionName; 
	}
	
	/** Gets unit name.
	 * @return unit name. */
	public String getUnitName() { 
		return this.unitName; 
	}
	
	/** Gets level name.
	 * @return level name. */
	public String getLevelName() { 
		return this.levelName; 
	}
	 
	/** Gets complex name.
	 * @return complex name. */
	public String getComplexName() { 
		return this.complexName; 
	}
	 
	/** Gets facility name.
	 * @return facility name. */
	public String getFacilityName() { 
		return this.facilityName; 
	}
}
