package omis.bedplacement.web.form;

/**
 * Operation to decide on how to filter rooms.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 4, 2015)
 * @since OMIS 3.0
 */
public enum FacilityLayerFilterOperation {

	/**Filter by facility. */
	FACILITY,
	/**Filter by complex. */
	COMPLEX,	
	/**Filter by unit. */
	UNIT,
	/**Filter by section. */
	SECTION,
	/**Filter by level. */
	LEVEL;
	
	/**
	 * Returns the name of {@code this}.
	 * 
	 * <p>This is done by returning {@code this.name()}.
	 * 
	 * @return name of {@code this}
	 */
	public String getName() {
		return this.name();
	}
}