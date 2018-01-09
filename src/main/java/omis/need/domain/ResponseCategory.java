package omis.need.domain;

/**
 * Response category.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 23, 2015)
 * @since OMIS 3.0
 */
public enum ResponseCategory {

	/** Approved. */
	APPROVED,
	
	/** Pending. */
	PENDING,
	
	/** Denied. */
	DENIED;
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name();
	}
}