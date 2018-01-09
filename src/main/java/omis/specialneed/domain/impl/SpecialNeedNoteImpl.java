package omis.specialneed.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedNote;

/**
 * 
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 31, 2016)
 * @since OMIS 3.0
 */
public class SpecialNeedNoteImpl implements SpecialNeedNote {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private SpecialNeed specialNeed;
	
	private Date date;
	
	private String value;
	
	/** Instantiates an implementation of special need note. */
	public SpecialNeedNoteImpl() {
		// Default constructor.
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
	public void setSpecialNeed(final SpecialNeed specialNeed) {
		this.specialNeed = specialNeed;
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeed getSpecialNeed() {
		return this.specialNeed;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/** {@inheritDoc} */
	@Override
	public void setValue(final String value) {
		this.value = value;
	}

	/** {@inheritDoc} */
	@Override
	public String getValue() {
		return this.value;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SpecialNeedNote)) {
			return false;
		}
		SpecialNeedNote that = (SpecialNeedNote) obj;
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required");
		}
		if (!this.getValue().equals(that.getValue())) {
			return false;
		}
		if (this.getSpecialNeed() == null) {
			throw new IllegalStateException("Special need required");
		}
		if (!this.getSpecialNeed().equals(that.getSpecialNeed())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required");
		}
		if (this.getSpecialNeed() == null) {
			throw new IllegalStateException("Special need required");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getValue().hashCode();
		hashCode = 29 * hashCode + this.getSpecialNeed().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		
		return hashCode;
	}
}