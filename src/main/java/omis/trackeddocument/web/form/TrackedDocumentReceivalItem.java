package omis.trackeddocument.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.trackeddocument.domain.TrackedDocumentCategory;
import omis.trackeddocument.domain.TrackedDocumentReceival;
import omis.user.domain.UserAccount;

/**
 * Tracked document item.
 * 
 * @author Yidong Li
 * @version 0.1.1 (Dec 19, 2018)
 * @since OMIS 3.0
 */
public class TrackedDocumentReceivalItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private TrackedDocumentCategory category;
	private Date receivedDate;
	private UserAccount receivedByUserAccount;
	private TrackedDocumentReceivalItemOperation operation;
	private TrackedDocumentReceival trackedDocumentReceival;
	
	/**
	 * Instantiates a default instance of tracked document receival.
	 */
	public TrackedDocumentReceivalItem() {
		//Default constructor.
	}

	/**
	 * Gets the tracked document category.
	 *
	 * @return the tracked document category
	 */
	public TrackedDocumentCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets the tracked document category.
	 *
	 * @param category tracked document category
	 */
	public void setCategory(final TrackedDocumentCategory category) {
		this.category = category;
	}
	
	/**
	 * Gets the received date.
	 *
	 * @return the received date
	 */
	public Date getReceivedDate() {
		return this.receivedDate;
	}

	/**
	 * Sets the received date.
	 *
	 * @param receivedDate  received date
	 */
	public void setReceivedDate(final Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	
	/**
	 * Gets the received by user account.
	 *
	 * @return the received by user account
	 */
	public UserAccount getReceivedByUserAccount() {
		return this.receivedByUserAccount;
	}

	/**
	 * Sets the received by user account.
	 *
	 * @param receivedByUserAccount received by user account
	 */
	public void setReceivedByUserAccount(final UserAccount
		receivedByUserAccount) {
		this.receivedByUserAccount = receivedByUserAccount;
	}
	
	/**
	 * Gets the tracked document receival item operation.
	 *
	 * @return the tracked document receival item operation
	 */
	public TrackedDocumentReceivalItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the tracked document receival item operation.
	 *
	 * @param operation tracked document receival item operation
	 */
	public void setOperation(final TrackedDocumentReceivalItemOperation
		operation) {
		this.operation = operation;
	}
	
	/**
	 * Get tracked document receival.
	 * @return tracked document receival
	 */
	public TrackedDocumentReceival getTrackedDocumentReceival() {
		return this.trackedDocumentReceival;
	}
	
	/**
	 * Set tracked document receival.
	 * @param trackedDocumentReceival tracked document receival
	 */
	public void setTrackedDocumentReceival(final TrackedDocumentReceival
		trackedDocumentReceival) {
		this.trackedDocumentReceival = trackedDocumentReceival;
	}
	
	/**
	 * Gets the ID.
	 *
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the ID.
	 *
	 * @param id id
	 */
	public void setId(final Long id) {
		this.id = id;
	}
}