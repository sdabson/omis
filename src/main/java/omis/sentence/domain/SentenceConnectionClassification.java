package omis.sentence.domain;

/**
 * Sentence connection classification.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Apr 18, 2017)
 * @since OMIS 3.0
 */
public enum SentenceConnectionClassification {

	/* Classification is initial */
	INITIAL,
	/* Classification is concurrent */
	CONCURRENT,
	/* Classification is consecutive */
	CONSECUTIVE;
	
	/**
	 * Returns <code>this.name()</code>.
	 * 
	 * @return <code>this.name()</code>
	 */
	public String getName() {
		return this.name();
	}
}
