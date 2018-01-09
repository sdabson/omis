package omis.identificationnumber.domain.impl;

import omis.identificationnumber.domain.IdentificationNumberCategory;
import omis.identificationnumber.domain.Multitude;

/**
 * Implementation of identification number category.
 *
 * @author Stephen Abson
 * @author Annie Jacques
 * @version 0.0.2 (Nov 1, 2017)
 * @since OMIS 3.0
 */
public class IdentificationNumberCategoryImpl
		implements IdentificationNumberCategory {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private Boolean valid;
	
	private Multitude multitude;
	
	/**
	 * Instantiates implementation of identification number category.
	 */
	public IdentificationNumberCategoryImpl() {
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
	
	/**{@inheritDoc} */
	@Override
	public void setMultitude(final Multitude multitude) {
		this.multitude = multitude;
	}
	
	/**{@inheritDoc} */
	@Override
	public Multitude getMultitude() {
		return this.multitude;
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
		if (!(obj instanceof IdentificationNumberCategory)) {
			return false;
		}
		IdentificationNumberCategory that = (IdentificationNumberCategory) obj;
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