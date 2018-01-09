package omis.address.domain;

/**
 * Category of building.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Feb 10, 2015)
 * @since OMIS 3.0
 */
public enum BuildingCategory {

	/** House. */
	HOUSE,
	
	/** Condo. */
	CONDO,
	
	/** Duplex. */
	DUPLEX,
	
	/** Apartment. */
	APARTMENT,
	
	/** Trailer. */
	TRAILER;
	
	/**
	 * Returns instance name ({@code this.name()}).
	 * 
	 * @return instance name ({@code this.name()})
	 */
	public String getName() {
		return this.name();
	}
}