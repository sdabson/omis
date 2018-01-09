package omis.prisonterm.domain;

/**
 * Prison term status.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (March 15, 2017)
 * @since OMIS 3.0
 */

public enum PrisonTermStatus {

	/** Prison term is Active. */
	ACTIVE,
	
	/** Prison term is Historical. */
	HISTORICAL,
	
	/** Prison term is Terminated. */
	TERMINATED;
	
	/**
	 * Returns <code>this.name()</code>.
	 * 
	 * @return <code>this.name()</code>
	 */
	public String getName() {
		return this.name();
	}
}
