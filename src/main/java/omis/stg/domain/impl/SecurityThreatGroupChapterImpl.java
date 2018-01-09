package omis.stg.domain.impl;

import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupChapter;

/**
 * Implementation of security threat group.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupChapterImpl
		implements SecurityThreatGroupChapter {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private SecurityThreatGroup group;
	
	private Boolean valid;

	/** Instantiates an implementation of security threat group chapter. */
	public SecurityThreatGroupChapterImpl() {
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
	public void setGroup(final SecurityThreatGroup group) {
		this.group = group;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroup getGroup() {
		return this.group;
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
		if (!(obj instanceof SecurityThreatGroupChapter)) {
			return false;
		}
		SecurityThreatGroupChapter that = (SecurityThreatGroupChapter) obj;
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		if (this.getGroup() == null) {
			throw new IllegalStateException("Group required");
		}
		if (!this.getGroup().equals(that.getGroup())) {
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
		if (this.getGroup() == null) {
			throw new IllegalStateException("Group required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		hashCode = 29 * hashCode + this.getGroup().hashCode();
		return hashCode;
	}
}
