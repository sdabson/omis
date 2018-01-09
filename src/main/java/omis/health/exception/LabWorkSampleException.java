package omis.health.exception;

import omis.health.exception.LabWorkException;

/**
 * Thrown when the labWork.sampleTaken = true.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Nov 8, 2014)
 * @since OMIS 3.0
 */
public class LabWorkSampleException
		extends LabWorkException {

	private static final long serialVersionUID = -1L;

	/** Instantiates a default labWork Sample exception. */
	public LabWorkSampleException() {
		// Default instantiation
	}
	
	/**
	 * Instantiates with the specified message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public LabWorkSampleException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates with the specified message.
	 * 
	 * @param message message
	 */
	public LabWorkSampleException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates with the specified cause.
	 * 
	 * @param cause cause
	 */
	public LabWorkSampleException(final Throwable cause) {
		super(cause);
	}
}