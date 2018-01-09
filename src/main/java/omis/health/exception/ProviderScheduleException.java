package omis.health.exception;

import omis.exception.BusinessException;

/** Provider schedule exception.
 * @author Ryan Johns
 * @version 0.1.0 (May 14, 2014)
 * @since OMIS 3.0 */
public class ProviderScheduleException extends BusinessException {
	private static final long serialVersionUID = 1L;

	/** Constructor.
	 * @param message message.
	 * @param cause cause. */
	public ProviderScheduleException(final String message,
			final Throwable cause) {
		super(message, cause);
	}

	/** Constructor.
	 * @param message message. */
	public ProviderScheduleException(final String message) {
		super(message);
	}

	/** Constructor.
	 * @param cause cause. */
	public ProviderScheduleException(final Throwable cause) {
		super(cause);
	}

}
