package omis.specialneed.web.form;

/**
 * Special need associable document item operation.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 6, 2017)
 * @since OMIS 3.0
 */
public enum SpecialNeedAssociableDocumentItemOperation {

	/**
	 * Creates a special need associable document.
	 */
	CREATE,
	
	/**
	 * Updates a special need associable document.
	 */
	UPDATE,
	
	/**
	 * Removes a special need associable document.
	 */
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