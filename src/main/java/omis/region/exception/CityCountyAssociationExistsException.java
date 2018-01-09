package omis.region.exception;

import omis.exception.DuplicateEntityFoundException;

/**
 * Thrown when city and county are associated.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 15, 2017)
 * @since OMIS 3.0
 */
public class CityCountyAssociationExistsException
		extends DuplicateEntityFoundException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates exception thrown when city and county are associated.
	 */
	public CityCountyAssociationExistsException() {
		// Default instantiation
	}
	
	/**
	 * Instantiates exception thrown when city and county are associated with
	 * message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public CityCountyAssociationExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates exception thrown when city and county are associated with
	 * message.
	 * 
	 * @param message message
	 */
	public CityCountyAssociationExistsException(
			final String message) {
		super(message);
	}
	
	/**
	 * Instantiates exception thrown when city and county are associated with
	 * cause.
	 * 
	 * @param cause cause
	 */
	public CityCountyAssociationExistsException(
			final Throwable cause) {
		super(cause);
	}
}