package omis.user.service.delegate;

import java.util.Date;

/**
 * Calculates password expiration date.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 2, 2014)
 * @since OMIS 3.0
 */
public interface PasswordExpirationDateCalculatorDelegate {

	/**
	 * Calculates password expiration date from date provided.
	 * 
	 * @param date date from which to calculate password expiration date
	 * @return calculated password expiration date
	 */
	Date calculate(Date date);
}