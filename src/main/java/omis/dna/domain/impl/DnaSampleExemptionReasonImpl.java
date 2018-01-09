package omis.dna.domain.impl;

import omis.dna.domain.DnaSampleExemptionReason;

/**
 * Implementation of reason for DNA sample exemption.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Oct 23, 2015)
 * @since OMIS 3.0
 */
public class DnaSampleExemptionReasonImpl
		implements DnaSampleExemptionReason {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String name;

	private Boolean valid;

	private Short sortOrder;

	/**
	 * Instantiates default implementation of reason for DNA sample exemption.
	 */
	public DnaSampleExemptionReasonImpl() {
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
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setSortOrder(final Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof DnaSampleExemptionReason)) {
			return false;
		}
		DnaSampleExemptionReason that = (DnaSampleExemptionReason) obj;
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		return 14 + (this.getName().hashCode() * 29);
	}
}