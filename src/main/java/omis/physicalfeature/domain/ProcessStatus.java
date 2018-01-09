package omis.physicalfeature.domain;

/**
 * Enumeration object for Process Status.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 6, 2014)
 * @since OMIS 3.0
 */
public enum ProcessStatus {
	
	/** Associate. */
	ASSOCIATE, 
	/** Remove. */
	REMOVE;
	
	/**
	 * Instantiates a Process Status with the specified name.
	 */
	private ProcessStatus() {
		//Default constructor
	}
	
	/**
	 * Returns the name of the process status.
	 * @return name
	 */
	public String getName() {
		return this.name();
	}
}