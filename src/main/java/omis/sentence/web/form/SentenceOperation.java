package omis.sentence.web.form;

/**
 * Sentence operation.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public enum SentenceOperation {

	/** Sentence is created. */
	CREATE,
	
	/** Sentence is updated. */
	UPDATE,
	
	/** Sentence is amended. */
	AMEND,
	
	/** Sentence is removed. */
	REMOVE;
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * <p>See {@link Enum#name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}