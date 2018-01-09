package omis.audit.domain;

import java.io.Serializable;

/**
 * Authorizable entity.
 * <p>
 * A authorizable entity tracks a signature of its authorization. 
 * This is done accomplished support of the {@code authorizationSignature}
 * property.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (Mar 14, 2013)
 * @since OMIS 3.0
 */
public interface Authorizable
		extends Serializable {
	
	/**
	 * Sets the authorization signature.
	 * @param authorization signature
	 */
	void setAuthorizationSignature(
			AuthorizationSignature authorizationSignature);

	/**
	 * Returns the authorization signature
	 * @return authorization signature 
	 */
	AuthorizationSignature getAuthorizationSignature();
}
