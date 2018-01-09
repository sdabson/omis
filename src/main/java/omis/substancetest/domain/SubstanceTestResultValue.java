package omis.substancetest.domain;

import java.io.Serializable;

/**
 * Substance Test Result Option.
 * @author Joel Norris
 * @version 0.1.0 (Jul 2, 2013)
 * @since OMIS 3.0
 */
public interface SubstanceTestResultValue 
	extends Serializable {

	/**
	 * Return the id of the substance test result option.
	 * @return the id
	 */
	Long getId();

	/**
	 * Set the id of the substance test result option.
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * Return the name of the substance test result option.
	 * @return the name
	 */
	String getName();

	/**
	 * Set the name of the substance test result option.
	 * @param name the name to set
	 */
	void setName(String name);
		
	/**
	 * Return the importance number of the test result option.
	 * @return the importance
	 */
	Integer getImportance();

	/**
	 * Set the importance number of the test result option.
	 * @param importance the importance to set
	 */
	void setImportance(Integer importance);
	
	/**
	 * Returns the valid value of the test result option.
	 * @return valid
	 */
	Boolean getValid();

	/**
	 * Sets the valid value of the test result option.
	 * @param valid valid
	 */
	void setValid(Boolean valid);
	
	/**
	 * Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
}
