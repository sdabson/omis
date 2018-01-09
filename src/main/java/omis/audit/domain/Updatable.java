package omis.audit.domain;

import java.io.Serializable;

/**
 * Updatable entity.
 * <p>
 * An updatable entity track a signature of its last update. This is done
 * via support of the {@code updateSignature} property.
 * @author Stephen Abson
 * @version 0.1.0 (Sep 24, 2012)
 * @since OMIS 3.0
 */
public interface Updatable extends Serializable {

	/**
	 * Sets the signature of the last update.
	 * @param updateSignature signature of last update
	 */
	void setUpdateSignature(UpdateSignature updateSignature);
	
	/**
	 * Returns the signature of the last update.
	 * @return signature of last update
	 */
	UpdateSignature getUpdateSignature();
}