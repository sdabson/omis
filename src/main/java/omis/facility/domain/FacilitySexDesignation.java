package omis.facility.domain;

import omis.demographics.domain.Sex;

/**
 * Sex designation for facility.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Mar 30, 2015)
 * @since OMIS 3.0
 */
public enum FacilitySexDesignation {

	/** Male only designation. */
	MALE (new Sex[] { Sex.MALE }),
	
	/** Female only designation. */
	FEMALE (new Sex[] { Sex.FEMALE }),
	
	/** Male and female designation. */
	BOTH (new Sex[] { Sex.MALE, Sex.FEMALE });
	
	private final Sex[] sexes;
	
	// Instantiates
	private FacilitySexDesignation(final Sex[] sexes) {
		this.sexes = sexes;
	}
	
	/**
	 * Returns sexes.
	 * 
	 * @return sexes
	 */
	public Sex[] getSexes() {
		return this.sexes;
	}
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}