package omis.dna.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.dna.domain.DnaSampleExemption;
import omis.dna.domain.DnaSampleExemptionReason;
import omis.offender.domain.Offender;

/**
 * Implementation of DNA sample exemption.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Oct 23, 2015)
 * @since OMIS 3.0
 */
public class DnaSampleExemptionImpl
		implements DnaSampleExemption {

	private static final long serialVersionUID = 1L;
	
	private Offender offender;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private Date effectiveDate;

	private DnaSampleExemptionReason reason;

	/** Implementation of DNA sample exemption. */
	public DnaSampleExemptionImpl() {
		// Default instantiation
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
	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setReason(final DnaSampleExemptionReason reason) {
		this.reason = reason;
	}

	/** {@inheritDoc} */
	@Override
	public DnaSampleExemptionReason getReason() {
		return this.reason;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof DnaSampleExemption)) {
			return false;
		}
		DnaSampleExemption that = (DnaSampleExemption) obj;
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
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		return 14 + (this.getOffender().hashCode() * 29);
	}
}