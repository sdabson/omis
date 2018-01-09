package omis.custody.domain;

import omis.audit.domain.Authorizable;
import omis.audit.domain.AuthorizationSignature;
import omis.audit.domain.Creatable;
import omis.audit.domain.CreationSignature;

/**
 * Custody Override.
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 18 2013)
 * @since OMIS 3.0
 */
public interface CustodyOverride extends Creatable, Authorizable {

	/**
	 * Returns the id of the custody override.
	 * @return the id
	 */
	Long getId();

	/**
	 * Sets the id of the custody override.
	 * @param id the id to set
	 */
	void setId(final Long id);

	/**
	 * Returns the custody review that applies to the custody override.
	 * @return the custodyReview
	 */
	CustodyReview getCustodyReview();

	/**
	 * Sets the custody review that applies to the custody override.
	 * @param custodyReview the custodyReview to set
	 */
	void setCustodyReview(final CustodyReview custodyReview);

	/**
	 * Returns the custody level of the custody override.
	 * @return the custodyLevel
	 */
	CustodyLevel getCustodyLevel();

	/**
	 * Sets the custody level of the custody override.
	 * @param custodyLevel the custodyLevel to set
	 */
	void setCustodyLevel(final CustodyLevel custodyLevel);

	/**
	 * Returns the creation signature of the custody override.
	 * @return the creationSignature
	 */
	CreationSignature getCreationSignature();

	/**
	 * Sets the creation signature of the custody override.
	 * @param creationSignature the creationSignature to set
	 */
	void setCreationSignature(final CreationSignature creationSignature);

	/**
	 * Returns the authorization signature of the custody override.
	 * @return the authorizationSignature
	 */
	AuthorizationSignature getAuthorizationSignature();

	/**
	 * Sets the authorization signature of the custody override.
	 * @param authorizationSignature the authorizationSignature to set
	 */
	void setAuthorizationSignature(
			final AuthorizationSignature authorizationSignature);
	
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