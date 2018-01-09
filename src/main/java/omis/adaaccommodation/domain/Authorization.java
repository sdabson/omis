package omis.adaaccommodation.domain;

import omis.audit.domain.AuthorizationSignature;
import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;

/**
 * ADA authorization.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 14, 2015)
 * @since OMIS 3.0
 */
public interface Authorization 
	extends Creatable, Updatable {

	/**
	 * Sets the ADA authorization ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Return the ADA authorization ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the ADA authorization source category.
	 * 
	 * @param authorizationSource authorization source
	 */
	void setAuthorizationSource(
			AuthorizationSourceCategory authorizationSource);
	
	/**
	 * Return the ADA authorization source category.
	 * 
	 * @return authorization source
	 */
	AuthorizationSourceCategory getAuthorizationSource();
	
	/**
	 * Sets the authorization for the ADA accommodation.
	 * 
	 * @param accommodation accommodation
	 */
	void setAccommodation(Accommodation accommodation);
	
	/**
	 * Return the authorization for the ADA accommodation.
	 * @return accommodation
	 */
	Accommodation getAccommodation();
	
	/**
	 * Sets the ADA authorization comments.
	 * 
	 * @param comments comments
	 */
	void setComments(String comments);
	
	/**
	 * Return the ADA authorization comments.
	 * 
	 * @return comments
	 */
	String getComments();
	
	/**
	 * Sets the ADA authorization signature.
	 * 
	 * @param authorizationSignature authorization signature
	 */
	void setAuthorizationSignature(
			AuthorizationSignature authorizationSignature);
	
	/**
	 * Return the ADA authorization signature.
	 * 
	 * @return authorization signature
	 */
	AuthorizationSignature getAuthorizationSignature();
	
	/**
	 * Sets the ADA authorization term.
	 * 
	 * @param authorizationTerm authorization term
	 */
	void setAuthorizationTerm(DateRange authorizationTerm);
	
	/**
	 * Return the ADA authorization term. 
	 * 
	 * @return authorization term
	 */
	DateRange getAuthorizationTerm();
	
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
}