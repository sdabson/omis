package omis.offenderflag.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.offender.domain.Offender;
import omis.offenderflag.domain.OffenderFlag;
import omis.offenderflag.domain.OffenderFlagCategory;

/**
 * Implementation of offender flag.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 10, 2014)
 * @since OMIS 3.0
 */
public class OffenderFlagImpl
		implements OffenderFlag {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Offender offender;

	private CreationSignature creationSignature;

	private UpdateSignature updateSignature;

	private OffenderFlagCategory category;

	private Boolean value;

	/** Instantiates an implementation of offender flag. */
	public OffenderFlagImpl() {
		// Default instantiation
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
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
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
	public void setUpdateSignature(
			final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(
			final OffenderFlagCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderFlagCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public void setValue(final Boolean value) {
		this.value = value;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValue() {
		return this.value;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof OffenderFlag)) {
			return false;
		}
		OffenderFlag that = (OffenderFlag) obj;
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required");
		}
		if (!this.getCategory().equals(that.getCategory())) {
			return false;
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 14;
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required");
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		return hashCode;
	}
}