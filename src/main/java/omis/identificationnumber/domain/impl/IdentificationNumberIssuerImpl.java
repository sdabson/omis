package omis.identificationnumber.domain.impl;

import omis.identificationnumber.domain.IdentificationNumberIssuer;

/**
 * Implementation of identification number issuer.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class IdentificationNumberIssuerImpl
		implements IdentificationNumberIssuer {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private Boolean valid;

	/**
	 * Instantiates implementation of identification number issuer.
	 */
	public IdentificationNumberIssuerImpl() {
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
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof IdentificationNumberIssuer)) {
			return false;
		}
		IdentificationNumberIssuer that = (IdentificationNumberIssuer) obj;
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
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		return hashCode;
	}
	
	/**
	 * Returns string representation of {@code this} including name.
	 * 
	 * See {@link Object#toString()}.
	 * @return string representation of {@code this} including name
	 */
	@Override
	public String toString() {
		return String.format(
				"%d %s [%s]", this.getId(), this.getName(), this.getValid());
	}
}