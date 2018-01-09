package omis.presentenceinvestigation.web.form;

/**
 * Victim Docket Document Association Item Operation
 *
 * @author Trevor Isles
 * @version 0.1.0 (Oct 24, 2017)
 * @since OMIS 3.0
 */

public enum VictimDocketDocumentAssociationItemOperation {

	/** Include. */
	INCLUDE,
	
	/** Exclude. */
	EXCLUDE,
	
	/** Remove. */
	REMOVE,
	
	/** Exists. */
	EXISTS;
	
	/**
	 * Returns the name of the {@code this}.
	 * 
	 * @return name of {@code this}
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
