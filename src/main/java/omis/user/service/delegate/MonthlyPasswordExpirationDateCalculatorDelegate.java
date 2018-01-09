package omis.user.service.delegate;

import java.util.Date;

import omis.util.DateManipulator;

/**
 * Monthly implementation of password expiration date calculator.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 2, 2014)
 * @since OMIS 3.0
 */
public class MonthlyPasswordExpirationDateCalculatorDelegate
		implements PasswordExpirationDateCalculatorDelegate {

	private final int monthsUntilExpiration;
	
	/**
	 * Instantiates a monthly implementation of password expiration date
	 * calculator.
	 * 
	 * @param monthsUntilExpiration months until expiration
	 */
	public MonthlyPasswordExpirationDateCalculatorDelegate(
			final int monthsUntilExpiration) {
		this.monthsUntilExpiration = monthsUntilExpiration;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date calculate(final Date date) {
		DateManipulator dateManipulator = new DateManipulator(date);
		dateManipulator.changeMonth(this.monthsUntilExpiration);
		return dateManipulator.getDate();
	}
}