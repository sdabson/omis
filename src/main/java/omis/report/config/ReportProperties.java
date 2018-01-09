package omis.report.config;

/**
 * Report properties.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Oct 25, 2017)
 * @since OMIS 3.0
 */
public final class ReportProperties {

	private final String baseUrl;
	
	private final String customUrl;
	
	private final String username;
	
	private final String password;
	
	/**
	 * Instantiates report properties.
	 * 
	 * @param baseUrl base URL
	 * @param customUrl custom URL
	 * @param username username
	 * @param password password
	 */
	public ReportProperties(
			final String baseUrl,
			final String customUrl,
			final String username,
			final String password) {
		this.baseUrl = baseUrl;
		this.customUrl = customUrl;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Returns base URL.
	 * 
	 * @return base URL
	 */
	public String getBaseUrl() {
		return this.baseUrl;
	}
	
	/**
	 * Returns custom URL.
	 * 
	 * @return custom URL
	 */
	public String getCustomUrl() {
		return this.customUrl;
	}
	
	/**
	 * Returns username.
	 * 
	 * @return username
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * Returns password.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return this.password;
	}
}