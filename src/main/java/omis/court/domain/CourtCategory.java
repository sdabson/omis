package omis.court.domain;

import java.io.Serializable;

/**
 * Category of court.
 * 
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.0 (Mar 27, 2013)
 * @since OMIS 3.0
 */
public enum CourtCategory implements Serializable {
	
	/** City. */
	CITY,
	
	/** County. */
	COUNTY,
	
	/** Federal. */
	FEDERAL;
		
	/**
	 * Returns the instance name.
	 * 
	 * @return instance name
	 */
	public final String getName() {
		return this.name();
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String toString() {
		return this.name();
	}
}