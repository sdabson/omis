package omis.prisonterm.exception;

import omis.exception.BusinessException;

/**
 * Thrown when an active prison term already exists.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (June 1, 2017)
 * @since OMIS 3.0
 */
public class ActivePrisonTermExistsException extends BusinessException {
	
	private static final long serialVersionUID = -1L;
	
	/** Instantiates a default active prison term exception. */
	public ActivePrisonTermExistsException() {
		//Default instantiation
	}
	
	/**
	 * Instantiates with the specified message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public ActivePrisonTermExistsException(
			final String message, 
			final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates with the specified message.
	 * 
	 * @param message message
	 */
	public ActivePrisonTermExistsException(
			final String message) {
		super(message);
	}
	
	/**
	 * Instantiates with the specified cause.
	 * 
	 * @param cause cause
	 */
	public ActivePrisonTermExistsException(
			final Throwable cause) {
		super(cause);
	}

}
