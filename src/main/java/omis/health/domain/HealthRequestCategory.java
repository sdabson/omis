package omis.health.domain;

/**
 * Health request category.
 *
 * @author Joel Norris
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public enum HealthRequestCategory {
	
	/** Internal Medical. */
	INTERNAL_MEDICAL,
	
	/** Internal Mental. */
	INTERNAL_MENTAL,
	
	/** Internal Optical. */
	INTERNAL_OPTICAL,
	
	/** Internal Dental. */
	INTERNAL_DENTAL,
	
	/** Lab. */
	LAB,
	
	/** External Medical. */
	EXTERNAL_MEDICAL,
	
	/** External Optical. */
	EXTERNAL_OPTICAL,
	
	/** External Dental. */
	EXTERNAL_DENTAL;

	/**
	 * Returns the name.
	 *
	 * @return name
	 */
	public String getName() {
		return this.name();
	}
	
	/**
	 * Returns supported values.
	 * 
	 * @return supported values
	 */
	public static HealthRequestCategory[] supportedValues() {
		return new HealthRequestCategory[] {
				INTERNAL_MEDICAL, LAB, EXTERNAL_MEDICAL };
	}
}