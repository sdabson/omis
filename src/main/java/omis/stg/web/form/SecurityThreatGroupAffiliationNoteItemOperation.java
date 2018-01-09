package omis.stg.web.form;

/**
 * Security threat group affiliation note item operation.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Nov 17, 2016)
 * @since OMIS 3.0
 */
public enum SecurityThreatGroupAffiliationNoteItemOperation {
	
	/** Create. */
	CREATE,
	
	/** Update. */
	UPDATE,
	
	/** Remove. */
	REMOVE;
	
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
