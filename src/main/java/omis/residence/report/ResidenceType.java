/**
 * 
 */
package omis.residence.report;

/**
 * Residence type.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Mar 10, 2015)
 * @since OMIS 3.0
 */
public enum ResidenceType {
	
	/** Designates a residence term. */
	RESIDENCE,
	
	/** Designates a non residence term. */
	NONRESIDENCE;
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name();
	}
		
	@Override
	public String toString() {
		return this.name();
	}
}
