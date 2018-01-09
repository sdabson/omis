package omis.paroleeligibility.web.form;

/**
 * Parole eligibility note item operation.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Dec 5, 2017)
 * @since OMIS 3.0
 */
public enum ParoleEligibilityNoteItemOperation {

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
