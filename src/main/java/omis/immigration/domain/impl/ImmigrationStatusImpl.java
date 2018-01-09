package omis.immigration.domain.impl;

import omis.immigration.domain.ImmigrationStatus;

/**
 * Implementation of immigration status.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Jan 11, 2013)
 * @since OMIS 3.0
 */
public class ImmigrationStatusImpl implements ImmigrationStatus {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String description;

	private Boolean valid;
	
	/** Instantiates a default immigration status. */
	public ImmigrationStatusImpl() {
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
	public void setDescription(final String description) {
		this.description = description;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
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
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ImmigrationStatus)) {
			return false;
		}
		ImmigrationStatus that = (ImmigrationStatus) obj;
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
		int hashCode = 14;
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		hashCode = 29 * hashCode + this.getName().hashCode();
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format("%d: %s, %s", this.getId(), this.getName(),
				this.getDescription());
	}
}