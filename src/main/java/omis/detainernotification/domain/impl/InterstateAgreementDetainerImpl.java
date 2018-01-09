package omis.detainernotification.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.detainernotification.domain.Detainer;
import omis.detainernotification.domain.InterstateAgreementDetainer;
import omis.detainernotification.domain.InterstateAgreementInitiatedByCategory;

/**
 * Interstate agreement detainer implementation.
 *
 * @author Sheronda Vaughn
 * @author Annie Jacques
 * @version 0.1.1 (Mar 22, 2017)
 * @since OMIS 3.0
 */
public class InterstateAgreementDetainerImpl 
	implements InterstateAgreementDetainer {

	private static final long serialVersionUID = 1L;

	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private Detainer detainer;
	
	private Date prosecutorReceivedDate;
	
	private InterstateAgreementInitiatedByCategory initiatedBy;
	
	/** Instantiates an implementation of InterstateAgreementDetainerImpl */
	public InterstateAgreementDetainerImpl() {
		//Default constructor
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
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setDetainer(
			final Detainer detainer) {
		this.detainer= detainer;
	}

	/** {@inheritDoc} */
	@Override
	public Detainer getDetainer() {
		return this.detainer;
	}

	/** {@inheritDoc} */
	@Override
	public void setProsecutorReceivedDate(final Date prosecutorReceivedDate) {
		this.prosecutorReceivedDate = prosecutorReceivedDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getProsecutorReceivedDate() {
		return this.prosecutorReceivedDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setInitiatedBy(
			final InterstateAgreementInitiatedByCategory initiatedBy) {
		this.initiatedBy = initiatedBy;
	}

	/** {@inheritDoc} */
	@Override
	public InterstateAgreementInitiatedByCategory getInitiatedBy() {
		return this.initiatedBy;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof InterstateAgreementDetainer)) {
			return false;
		}
		InterstateAgreementDetainer that = (InterstateAgreementDetainer) obj;
		if (this.getDetainer()  == null) {
			throw new IllegalArgumentException("Detainer required");
		}
		if (this.getInitiatedBy()  == null) {
			throw new IllegalArgumentException("InitiatedBy required");
		}
		if (!this.getDetainer().equals(that.getDetainer())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getDetainer()  == null) {
			throw new IllegalArgumentException("Detainer required");
		}
		if (this.getInitiatedBy()  == null) {
			throw new IllegalArgumentException("InitiatedBy required");
		}
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getDetainer().hashCode();
		return hashCode;
	}
}