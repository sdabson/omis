package omis.audit.domain;

import java.io.Serializable;

/**
 * Creatable entity.
 * <p>
 * A creatable entity tracks a signature of its creation. This is done via
 * support of the {@code creationSignature} property. 
 * @author Stephen Abson
 * @version 0.1.0 (Sep 24, 2012)
 * @since OMIS 3.0
 */
public interface Creatable extends Serializable {

	/**
	 * Sets the creation signature.
	 * @param creationSignature creation signature
	 */
	void setCreationSignature(CreationSignature creationSignature);
	
	/**
	 * Returns the creation signature.
	 * @return creation signature
	 */
	CreationSignature getCreationSignature();
}