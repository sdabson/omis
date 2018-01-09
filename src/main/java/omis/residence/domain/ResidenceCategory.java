package omis.residence.domain;

/**
 * Category of residence. 
 *
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.0.1 (Sep 18, 2014)
 * @since OMIS 3.0
 */
public enum ResidenceCategory {

	/** Primary. */
	PRIMARY,
	
	/** Secondary. */
	SECONDARY;
	
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