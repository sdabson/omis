package omis.caseload.web.form;

/**
 * 
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 11, 2017)
 * @since OMIS 3.0
 */
public enum OffenderTransferingItemBoolean {
	/**Transferring indicator. */
	TRANSFERING,

	/** Not transferring indicator. */ 
	NOT_TRANSFERING;

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