package omis.audit.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.Creatable;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.Updatable;
import omis.audit.domain.UpdateSignature;

/**
 * Delegate for audit component functionality.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Feb 4, 2015)
 * @since OMIS 3.0
 */
public class AuditComponentDelegate {

	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates delegate for audit component functionality.
	 * 
	 * @param auditComponentRetriever audit component retriever
	 */
	public AuditComponentDelegate(
			final AuditComponentRetriever auditComponentRetriever) {
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/**
	 * Sets creation signature of creatable object.
	 * 
	 * @param creatable creatable object the creation signature of which to set
	 */
	public void setCreationSignature(final Creatable creatable) {
		creatable.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	}
	
	/**
	 * Sets update signature of updateable object.
	 * 
	 * @param updateable updateable object the update signature of which to set
	 */
	public void setUpdateSignature(final Updatable updateable) {
		updateable.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	}
}