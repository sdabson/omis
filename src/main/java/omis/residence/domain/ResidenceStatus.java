package omis.residence.domain;

/**
 * Residence status.
 *
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.0.1 (Sep 18, 2014)
 * @since OMIS 3.0
 */
public enum ResidenceStatus {

	/** Resident. */
	RESIDENT,
	
	/** Foster care. */
	FOSTER_CARE,
	
	/** Group home. */
	GROUP_HOME,
	
	/** Homeless. */
	HOMELESS,
	
	/** Hotel. */
	HOTEL;
	
	/**
	 * Returns the instance name.
	 * 
	 * @return instance name
	 */
	public String getName() {
		return this.name();
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	
	@Override
	public String toString() {
		return this.name();
	}
}