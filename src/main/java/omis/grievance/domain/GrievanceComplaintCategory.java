package omis.grievance.domain;

import java.io.Serializable;

/**
 * Grievance complaint category.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 7, 2015)
 * @since OMIS 3.0
 */
public interface GrievanceComplaintCategory
		extends Serializable {

	/**
	 * Sets ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets name.
	 * 
	 * @param name name
	 */
	void setName(String name);
	
	/**
	 * Returns name.
	 * 
	 * @return name
	 */
	String getName();
	
	/**
	 * Sets whether {@code this} is valid.
	 * 
	 * @param valid whether {@code this} is valid
	 */
	void setValid(Boolean valid);
	
	/**
	 * Returns whether {@code this} is valid.
	 * 
	 * @return whether {@code this} is valid
	 */
	Boolean getValid();
	
	/**
	 * Sets subject.
	 * 
	 * @param subject subject
	 */
	void setSubject(GrievanceSubject subject);
	
	/**
	 * Returns subject.
	 * 
	 * @return subject
	 */
	GrievanceSubject getSubject();
	
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