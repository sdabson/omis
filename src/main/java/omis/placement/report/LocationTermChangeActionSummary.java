package omis.placement.report;

import java.io.Serializable;

/**
 * Summary of location term change action.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Mar 6, 2015)
 * @since OMIS 3.0
 */
public class LocationTermChangeActionSummary
		implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private final Long id;
	
	private final String name;
	
	/**
	 * Instantiates summary of location term change action.
	 * 
	 * @param id ID
	 * @param name name
	 */
	public LocationTermChangeActionSummary(
			final Long id, final String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Returns name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
}