package omis.victim.domain.impl;

import omis.victim.domain.VictimNoteCategory;

/**
 * Implementation of victim note category.
 *
 * @author Stephen Abson
 * @version 0.0.2 (Aug 14, 2017)
 * @since OMIS 3.0
 */
public class VictimNoteCategoryImpl
		implements VictimNoteCategory {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Short sortOrder;
	private Boolean contact;
	private Boolean valid;
	
	/** Instantiates default implementation of victim note category. */
	public VictimNoteCategoryImpl() {
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
	public Boolean getContact() {
		return this.contact;
	}

	/** {@inheritDoc} */
	@Override
	public void setContact(final Boolean contact) {
		this.contact = contact;
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
	
	/** {@inhertDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof VictimNoteCategory)) {
			return false;
		}
		VictimNoteCategory that = (VictimNoteCategory) obj;
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
}