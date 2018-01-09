package omis.address.domain.component;

import java.io.Serializable;

import omis.address.domain.StreetSuffix;

/**
 * Street.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.1 (May 27, 2015)
 * @since OMIS 3.0
 */
public class Street
		implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String name;

	private StreetSuffix suffix;

	/** Instantiates a street. */
	public Street() {
		// Default instantiation
	}
	
	/**
	 * Instantiates a street with the specified name and street suffix.
	 * 
	 * @param name name
	 * @param suffix street suffix
	 */
	public Street(final String name, final StreetSuffix suffix) {
		this.name = name;
		this.suffix = suffix;
	}
	
	/**
	 * Sets the name.
	 * 
	 * @param name name
	 */
	public void setName(final String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the suffix.
	 * 
	 * @param suffix suffix
	 */
	public void setSuffix(final StreetSuffix suffix) {
		this.suffix = suffix;
	}
	
	/**
	 * Returns the suffix.
	 * 
	 * @return suffix
	 */
	public StreetSuffix getSuffix() {
		return this.suffix;
	}
}