package omis.health.web.form;

/**
 * Provider Type.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 10, 2014)
 * @since OMIS 3.0
 */
public enum ProviderType {

	/** Internal. */
	INTERNAL,
	
	/** External. */
	EXTERNAL,
	
	/** Contracted. */
	CONTRACTED;
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name();
	}
}