package omis.offense.domain;

import java.io.Serializable;

/**
 * Offense.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1 (Apr 25, 2017)
 * @since OMIS 3.0
 */
public interface Offense
		extends Serializable {

	/**
	 * Sets the ID.
	 *
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 *
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the violation code.
	 * 
	 * @param violationCode violation code
	 */
	void setViolationCode(String violationCode);
	
	/**
	 * Returns the violation code.
	 * 
	 * @return violation code
	 */
	String getViolationCode();
	
	/**
	 * Sets the name.
	 * 
	 * @param name name
	 */
	void setName(String name);
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	String getName();

	/**
	 * Sets the offense classification.
	 * 
	 * @param classification offense classification
	 */
	void setClassification(OffenseClassification classification);

	/**
	 * Returns the offense classification.
	 * 
	 * @return offense classification
	 */
	OffenseClassification getClassification();
		
	
	/**
	 * Sets the short name.
	 * 
	 * @param shortName short name
	 */
	void setShortName(String shortName);
	
	/**
	 * Returns the short name.
	 * 
	 * @return short name
	 */
	String getShortName();
	
	/**
	 * Sets the url.
	 * 
	 * @param url url
	 */
	void setUrl(String url);
	
	/**
	 * Returns the url.
	 * 
	 * @return url
	 */
	String getUrl();
	
	/**
	 * Sets whether the offense is valid.
	 * 
	 * @param valid valid
	 */
	void setValid(Boolean valid);
	
	/**
	 * Returns whether the offense is valid.
	 * 
	 * @return valid
	 */
	Boolean getValid();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}