package omis.web.form;

/**
 * Operation to perform when a form is submitted.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 25, 2013)
 * @since OMIS 3.0
 */
public enum FormOperation {

	/**
	 * Save and redirect to next, previous or default screen depending upon
	 * which is specified.
	 */
	SAVE,
	
	/**
	 * Save and redirect to the next screen in a process.
	 */
	SAVE_AND_CONTINUE,
	
	/**
	 * Cancel and redirect to the previous or default screen depending upon
	 * which is specified.
	 */
	CANCEL;
}
