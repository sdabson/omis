package omis.separationneed.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.separationneed.domain.SeparationNeed;
import omis.separationneed.domain.SeparationNeedNote;

/**
 * Separation need note implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 18, 2015)
 * @since OMIS 3.0
 */
public class SeparationNeedNoteImpl implements SeparationNeedNote {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String value;
	
	private Date date;
	
	private SeparationNeed separationNeed;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default instance of separation need note implementation.
	 */
	public SeparationNeedNoteImpl() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public String getValue() {
		return this.value;
	}

	/** {@inheritDoc} */
	@Override
	public void setValue(final String value) {
		this.value = value;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}
	
	/** {@inheritDoc} */
	@Override
	public SeparationNeed getSeparationNeed() {
		return this.separationNeed;
	}

	/** {@inheritDoc} */
	@Override
	public void setSeparationNeed(final SeparationNeed separationNeed) {
		this.separationNeed = separationNeed;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof SeparationNeedNote)) {
			return false;
		}
		
		SeparationNeedNote that = (SeparationNeedNote) o;
		
		if (this.getValue() == null) {
			throw new IllegalStateException("Note required.");
		}
		if (!this.getValue().equals(that.getValue())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (this.getSeparationNeed() == null) {
			throw new IllegalStateException("Separation need required");
		}
		if (!this.getSeparationNeed().equals(that.getSeparationNeed())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getValue() == null) {
			throw new IllegalStateException("Note required.");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (this.getSeparationNeed() == null) {
			throw new IllegalStateException("Separation need required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getValue().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getSeparationNeed().hashCode();
		
		return hashCode;
	}
}