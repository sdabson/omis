package omis.health.exception;

import omis.health.exception.LabWorkException;

/**
 * Thrown when labWork.results.date != null.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Nov 8, 2014)
 * @since OMIS 3.0
 *
 */
public class LabWorkResultsException extends LabWorkException {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a default instance of lab work result exception.
	 */
	public LabWorkResultsException() {
		//Default constructor.
	}
	
	/**
	 * Instantiate a lab work result exception with the specified message
	 * and throwable cause.
	 * 
	 * @param message business exception message
	 * @param cause throwable business exception cause
	 */
	public LabWorkResultsException(final String message, 
			final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates a lab work result exception with the specified message.
	 * 
	 * @param message business exception message
	 */
	public LabWorkResultsException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates a lab work result exception with the specified throwable
	 * cause.
	 * 
	 * @param cause throwable business exception cause
	 */
	public LabWorkResultsException(final Throwable cause) {
		super(cause);
	}
}