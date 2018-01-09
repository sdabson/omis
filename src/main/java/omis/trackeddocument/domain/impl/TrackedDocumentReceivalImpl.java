package omis.trackeddocument.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.docket.domain.Docket;
import omis.trackeddocument.domain.TrackedDocumentCategory;
import omis.trackeddocument.domain.TrackedDocumentReceival;
import omis.user.domain.UserAccount;

/** Implementation of offender tracked document receival.
 * @author Yidong Li
 * @version 0.1.0 (Dec 11, 2017)
 * @since OMIS 3.0 */
public class TrackedDocumentReceivalImpl implements TrackedDocumentReceival {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Docket docket;
	private TrackedDocumentCategory category;
	private UserAccount receivedByUserAccount;
	private Date receivedDate;
	private UpdateSignature updateSignature;
	private CreationSignature creationSignature;
	
	/** Constructor. */
	public TrackedDocumentReceivalImpl() {
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public TrackedDocumentCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(
		final TrackedDocumentCategory category) {
		this.category = category;
	}
	
	/** {@inheritDoc} */
	@Override
	public Docket getDocket() {
		return this.docket;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDocket(final Docket docket) {
		this.docket = docket;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getReceivedDate() {
		return this.receivedDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setReceivedDate(final Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public UserAccount getReceivedByUserAccount() {
		return this.receivedByUserAccount;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setReceivedByUserAccount(final UserAccount 
		receivedByUserAccount) {
		this.receivedByUserAccount = receivedByUserAccount;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TrackedDocumentCategory)) {
			return false;
		}
		TrackedDocumentReceival that = (TrackedDocumentReceival) obj;
		if (this.getDocket() == null) {
			throw new IllegalStateException("Docket required");
		}
		if (!this.getDocket().equals(that.getDocket())) {
			return false;
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required");
		}
		if (!this.getCategory().equals(
			that.getCategory())) {
			return false;
		}
		if (this.getReceivedByUserAccount() == null) {
			throw new IllegalStateException("User account required");
		}
		if (this.getReceivedDate() == null) {
			throw new IllegalStateException("Received date required");
		}
		if (!this.getReceivedDate().equals(that.getReceivedDate())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int number = 7;
		int hashCode = number;
		
		if (this.getDocket() == null) {
			throw new IllegalStateException("Docket required");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required");
		}
		if (this.getReceivedByUserAccount() == null) {
			throw new IllegalStateException("Received user account required");
		}
		if (this.getReceivedDate() == null) {
			throw new IllegalStateException("Received date required");
		}
		hashCode += 29 * this.getDocket().hashCode();
		hashCode += 29 * this.getCategory().hashCode();
		hashCode += 29 * this.getReceivedByUserAccount().hashCode(); 
		hashCode += 29 * this.getReceivedDate().hashCode();
		return hashCode;
	}
}
