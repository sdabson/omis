package omis.contact.domain;

/**
 * Wage Payment Frequency. 
 *
 * @author Yidong Li
 * @version 0.0.1 (Jan 30, 2015)
 * @since OMIS 3.0
 */
public enum OnlineAccountCategory {
	/** Online account is email. */
	EMAIL,
	/** Online account is social media. */
	SOCIAL_MEDIA,
	/** Online account is web site. */
	WEBSITE;
	
	/**
	 * Returns {@code this.name()}
	 * 
	 * <p>See {@link Enum#name()}
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}