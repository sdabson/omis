package omis.sentence.domain;

import java.io.Serializable;

/**
 * Sentence category.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 26, 2017)
 * @since OMIS 3.0
 */
public interface SentenceCategory extends Serializable {

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
	 * Sets the prison term requirement.
	 * 
	 * @param prisonRequirement prison term requirement
	 */
	void setPrisonRequirement(TermRequirement prisonRequirement);
	
	/**
	 * Returns the prison term requirement.
	 * 
	 * @return prison term requirement
	 */
	TermRequirement getPrisonRequirement();
	
	/**
	 * Sets the probation term requirement.
	 * 
	 * @param probationRequirement probation term requirement
	 */
	void setProbationRequirement(TermRequirement probationRequirement);
	
	/**
	 * Returns the probation term requirement.
	 * 
	 * @return probation term requirement
	 */
	TermRequirement getProbationRequirement();
	
	/**
	 * Sets the deferred term requirement.
	 * 
	 * @param deferredRequirement deferred term requirement
	 */
	void setDeferredRequirement(TermRequirement deferredRequirement);
	
	/**
	 * Returns the deferred term requirement.
	 * 
	 * @return deferred term requirement
	 */
	TermRequirement getDeferredRequirement();
	
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
