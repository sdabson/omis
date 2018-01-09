package omis.demographics.domain;

import java.io.Serializable;

/**
 * Sex.
 * 
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.1 (Sept 11, 2013)
 * @since OMIS 3.0
 */
public enum Sex implements Serializable {
	
	/** Male. */
	MALE,
	
	/** Female. */
	FEMALE;

	/**
	 * Returns {@code this.name()}.
	 * 
	 * <p>See {@link Enum#name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}