package omis.placement.report;

import java.io.Serializable;

/**
 * Summary of placement term change action.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class PlacementTermChangeActionSummary
		implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private final Long id;
	
	private final String name;
	
	/**
	 * Instantiates summary of placement term change action.
	 * 
	 * @param id ID
	 * @param name name
	 */
	public PlacementTermChangeActionSummary(
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
		return this.id;
	}
	
	/**
	 * Returns name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
}