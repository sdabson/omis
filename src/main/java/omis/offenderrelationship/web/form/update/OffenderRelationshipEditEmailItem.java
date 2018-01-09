package omis.offenderrelationship.web.form.update;

import java.io.Serializable;

import omis.contact.domain.OnlineAccountHost;

/**
 * Offender relationship edit email item.
 * 
 * @author Yidong Li
 * @version 0.1.0 (FEb 9,, 2016)
 * @since OMIS 3.0
 */
public class OffenderRelationshipEditEmailItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String eMail;
	private OnlineAccountHost onlineAccountHost;
	private OffenderRelationshipEditEmailItemOperation operation;
	
	/**
	 * Instantiates a default instance of offender relationship edit email item.
	 */
	public OffenderRelationshipEditEmailItem() {
		//Default constructor.
	}

	/**
	 * Returns eMail.
	 * 
	 * @return eMail
	 */
	public String geteMail() {
		return eMail;
	}

	/**
	 * Sets eMail.
	 * 
	 * @param eMail eMail
	 */
	public void seteMail(final String eMail) {
		this.eMail = eMail;
	}

	/**
	 * Returns online accoutn host.
	 * 
	 * @return onlineAccountHost
	 */
	public OnlineAccountHost getOnlineAccountHost() {
		return onlineAccountHost;
	}

	/**
	 * Sets onlineAccountHost.
	 * 
	 * @param onlineAccountHost online account host
	 */
	public void setOnlineAccountHost(
		final OnlineAccountHost onlineAccountHost) {
		this.onlineAccountHost = onlineAccountHost;
	}
	
	/**
	 * Returns operation.
	 * 
	 * @return operation
	 */
	public OffenderRelationshipEditEmailItemOperation getOperation() {
		return operation;
	}

	/**
	 * Sets operation.
	 * 
	 * @param operation operation
	 */
	public void setOperation(final OffenderRelationshipEditEmailItemOperation 
		operation) {
		this.operation = operation;
	}
}