package omis.incident.domain;

import java.io.Serializable;

import omis.audit.domain.VerificationSignature;
import omis.person.domain.Person;

/**
 * Involved Party.
 * 
 * @author: Yidong Li
 * @author: Joel Norris
 * @version: 0.1.0 (June 20, 2015)
 * @since: OMIS 3.0
 */

public interface InvolvedParty extends Serializable{
	/**
	 * Returns the involved party id.
	 * 
	 * @return involved party id
	 */
	Long getId();
	
	/**
	 * Sets the involved party id.
	 * @param involved party id
	 */
	void setId(Long id);
	
	/**
	 * Returns the person.
	 * 
	 * @return person
	 */
	Person getPerson();
	
	/**
	 * Sets the person.
	 * @param person
	 */
	void setPerson(Person person);
	
	/**
	 * Returns the verification.
	 * 
	 * @return verification
	 */
	VerificationSignature getVerificationSignature();
	
	/**
	 * Sets the verification.
	 * @param verification
	 */
	void setVerificationSignature(VerificationSignature verification);
	
	/**
	 * Returns the incident statement.
	 * 
	 * @return incident statement
	 */
	IncidentStatement getStatement();
	
	/**
	 * Sets the statement.
	 * @param statement incident statement
	 */
	void setStatement(IncidentStatement statement);
	
	/**
	 * Returns the narrative.
	 * 
	 * @return narrative
	 */
	String getNarrative();
	
	/**
	 * Sets the narrative.
	 * 
	 * @param narrative narrative
	 */
	void setNarrative(String narrative);
	
	/**
	 * Returns the involved party category.
	 * 
	 * @return involved party category
	 */
	InvolvedPartyCategory getCategory();
	
	/**
	 * Sets the involved party category.
	 * 
	 * @param involved party category
	 */
	void setCategory(InvolvedPartyCategory category);
	
	/**
	 * Returns name.
	 * 
	 * @return name
	 */
	String getName();
	
	/**
	 * Sets the name.
	 * 
	 * @param name name
	 */
	void setName(String name);
	
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