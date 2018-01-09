package omis.detainernotification.domain;

/**
 * Detainer interpretation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 25, 2017)
 * @since OMIS 3.0
 */
public enum DetainerInterpretation {
	
	/** Detainer. */
	DETAINER,
	
	/** NOTIFICATION */
	NOTIFICATION;
	
	/**
	 * 	Returns the name.
	 * 
	 * @return name
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
