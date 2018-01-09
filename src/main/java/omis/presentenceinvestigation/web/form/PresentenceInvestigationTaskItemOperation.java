package omis.presentenceinvestigation.web.form;

/**
 * PresentenceInvestigationTaskItemOperation.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 26, 2017)
 *@since OMIS 3.0
 *
 */
public enum PresentenceInvestigationTaskItemOperation {
	
	/** When an Task is already completed */
	COMPLETE,
	
	/** When a Task is to be marked for completion via the form */
	SET_COMPLETE,
	
	/** When a task is neither already completed nor marked for completion */
	INCOMPLETE;
	
	/**
	 * Returns the name of the {@code this}.
	 * 
	 * @return name of {@code this}
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
