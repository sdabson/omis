package omis.sentence.domain;

/**
 * Term requirement.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Apr 18, 2017)
 * @since OMIS 3.0
 */
public enum TermRequirement {

	/* Term requirement is not allowed */
	NOT_ALLOWED,
	/* Term requirement is required */
	REQUIRED,
	/* Term requirement is optional */
	OPTIONAL;
	
	/**
	 * Returns <code>this.name()</code>.
	 * 
	 * @return <code>this.name()</code>
	 */
	public String getName() {
		return this.name();
	}
}
