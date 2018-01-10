package omis.trackeddocument.web.form;

/**
 * Operation to be performed for tracked document.
 * 
 * @author Yidong Li
 * @version 0.1.2 (Sept 18, 2017)
 * @since OMIS 3.0
 */
public enum TrackedDocumentReceivalItemOperation {

	/** Create a new tracked document. */
	CREATE,
	
	/** Edit an existing tracked document. */
	EDIT,
	
	/** Remove an existing tracked document. */
	REMOVE;
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * <p>See {@link Enum#name()}.
	 * 
	 * @return {@code this.name}
	 */
	public String getName() {
		return this.name();
	}
}