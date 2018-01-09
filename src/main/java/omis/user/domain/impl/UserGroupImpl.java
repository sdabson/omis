package omis.user.domain.impl;

import omis.user.domain.UserGroup;

/**
 * Implementation of user group.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Dec 11, 2012)
 * @since OMIS 3.0
 */
public class UserGroupImpl implements UserGroup {

	private static final long serialVersionUID = 1L;
	
	private static final String NAME_REQUIRED_MSG = "Name required";
	
	private static final String DESCRIPTION_REQUIRED_MSG = 
			"Description required";
	
	private Long id;
	
	private String name;
	
	private String description;

	private Short sortOrder;

	private Boolean valid;
	
	/** Instantiates a default user group. */
	public UserGroupImpl() {
		// Do nothing
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
	public String getDescription() {
		return this.description;
	}

	/** {@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}
	
	/** {@inheritDoc} */
	@Override
	public Short getSortOrder() {
		return this.sortOrder;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setSortOrder(final Short sortOrder) {
		this.sortOrder = sortOrder;
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
		if (!(obj instanceof UserGroup)) {
			return false;
		}
		UserGroup that = (UserGroup) obj;
		if (this.getName() == null) {
			throw new IllegalStateException(NAME_REQUIRED_MSG);
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException(DESCRIPTION_REQUIRED_MSG);
		}
		if (!this.getDescription().equals(that.getDescription())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException(NAME_REQUIRED_MSG);
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException(DESCRIPTION_REQUIRED_MSG);
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format("#%d: %s %s",
				this.getId(), this.getName(), this.getDescription());
	}
}