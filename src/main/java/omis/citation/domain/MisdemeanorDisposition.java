package omis.citation.domain;

/**
 * Misdemeanor citation.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 5, 2016)
 * @since OMIS 3.0
 */

public enum MisdemeanorDisposition {
	
	/** Disposition is Convicted.*/
	CONVICTED,
	
	/** Disposition is Amended.*/
	AMENDED,
	
	/** Disposition is Dropped.*/
	DROPPED,
	
	/** Disposition is Pending. */
	PENDING;
	
	/**
	 * Returns <code>this.name()</code>.
	 * 
	 * @return <code>this.name()</code>
	 */
	public String getName() {
		return this.name();
	}
	
}
