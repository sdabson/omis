package omis.substanceuse.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.substanceuse.domain.SubstanceUse;
import omis.substanceuse.domain.UseAffirmation;
import omis.substanceuse.domain.UseAffirmationSource;
import omis.substanceuse.domain.UseAllotment;

/**
 * Use affirmation implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0
 * @since OMIS 3.0
 */
public class UseAffirmationImpl implements UseAffirmation {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date date;
	private UseAffirmationSource source;
	private UseAllotment allotment;
	private SubstanceUse use;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default instance of use affirmation.
	 */
	public UseAffirmationImpl() {
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
	public Date getDate() {
		return date;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public UseAffirmationSource getSource() {
		return source;
	}

	/** {@inheritDoc} */
	@Override
	public void setSource(final UseAffirmationSource source) {
		this.source = source;
	}

	/** {@inheritDoc} */
	@Override
	public UseAllotment getAllotment() {
		return this.allotment;
	}

	/** {@inheritDoc} */
	@Override
	public void setAllotment(final UseAllotment allotment) {
		this.allotment = allotment;
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceUse getUse() {
		return this.use;
	}

	/** {@inheritDoc} */
	@Override
	public void setUse(final SubstanceUse use) {
		this.use = use;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
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
		if (!(o instanceof UseAffirmation)) {
			return false;
		}
		
		UseAffirmation that = (UseAffirmation) o;
		
		if (this.getUse() == null) {
			throw new IllegalStateException("Use required.");
		}
		if (!this.getUse().equals(that.getUse())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (this.getSource()  == null) {
			throw new IllegalStateException("Source required.");
		}
		if (!this.getSource().equals(that.getSource())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getUse() == null) {
			throw new IllegalStateException("Use required.");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (this.getSource() == null) {
			throw new IllegalStateException("Source required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getUse().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getSource().hashCode();
		
		return hashCode;
	}
}