package omis.caseload.domain;

/**
 * Case work category.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 26, 2016)
 * @since OMIS 3.0
 */
public enum CaseworkCategory {

	/** Supervisory. */
	SUPERVISORY,
	
	/** Administrative. */
	ADMINISTRATIVE;


	/**
	 * Returns the instance name.
	 * 
	 * @return instance name
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