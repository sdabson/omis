package omis.residence.web.form;

/**
 * Residence status option.
 * 
 * @author Sheronda Vaughn
 * @version 0.0.1 (Mar 27, 2015)
 * @since OMIS 3.0
 *
 */
public enum ResidenceStatusOption {

	/** Primary residence. */
	PRIMARY_RESIDENCE,
	
	/** Secondary residence. */
	SECONDARY_RESIDENCE,
	
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
