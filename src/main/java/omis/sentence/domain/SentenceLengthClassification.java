package omis.sentence.domain;

/**
 * Sentence length classification.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 26, 2017)
 * @since OMIS 3.0
 */
public enum SentenceLengthClassification {

	/* Classification is not life */
	NOT_LIFE,
	/* Classification is death */
	DEATH,
	/* Classification is life without parole */
	LIFE_WITHOUT_PAROLE,
	/* Classification is life with parole */
	LIFE_WITH_PAROLE;
	
	/**
	 * Returns <code>this.name()</code>.
	 * 
	 * @return <code>this.name()</code>
	 */
	public String getName() {
		return this.name();
	}
}
