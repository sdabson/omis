package omis.identificationnumber.domain;

/**
 * Multitude
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 1, 2017)
 *@since OMIS 3.0
 *
 */
public enum Multitude {
	
	/**
	 * Single use allowed
	 */
	SINGLE,
	
	/**
	 * Multiple uses allowed
	 */
	MULTIPLE;
	
	/**
	 * 	Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name();
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	@Override
	public String toString() {
		return this.name();
	}
}
