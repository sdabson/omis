package omis.message;

import java.io.Serializable;

/**
 * Business message.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 3, 2014)
 * @since OMIS 3.0
 */
public class BusinessMessage
		implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private final String basename;
	
	private final String key;
	
	/**
	 * Instantiates a business message.
	 * 
	 * @param basename basename
	 * @param key key
	 */
	public BusinessMessage(final String basename, final String key) {
		this.basename = basename;
		this.key = key;
	}
	
	/**
	 * Returns the basename.
	 * 
	 * @return basename
	 */
	public String getBasename() {
		return this.basename;
	}
	
	/**
	 * Returns the key.
	 * 
	 * @return key
	 */
	public String getKey() {
		return this.key;
	}
}