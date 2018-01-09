package omis.substanceuse.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.substanceuse.domain.SubstanceUse;
import omis.substanceuse.domain.UseAllotment;
import omis.substanceuse.domain.UseFrequency;
import omis.substanceuse.domain.UseTerm;
import omis.substanceuse.domain.UseTermSource;

/**
 * Use term implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0
 * @since OMIS 3.0
 */
public class UseTermImpl implements UseTerm {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private DateRange dateRange;
	private UseFrequency frequency;
	private UseAllotment allotment;
	private UseTermSource source;
	private SubstanceUse use;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default instance of use term.
	 */
	public UseTermImpl() {
		//Default constructor
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
	public DateRange getDateRange() {
		return this.dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setDateRange(final DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public UseFrequency getFrequency() {
		return this.frequency;
	}

	/** {@inheritDoc} */
	@Override
	public void setFrequency(final UseFrequency frequency) {
		this.frequency = frequency;
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
	public UseTermSource getSource() {
		return this.source;
	}

	/** {@inheritDoc} */
	@Override
	public void setSource(final UseTermSource source) {
		this.source = source;
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
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof UseTerm)) {
			return false;
		}
		
		UseTerm that = (UseTerm) o;
		
		if (this.getUse() == null) {
			throw new IllegalStateException("Use required");
		}
		if (!this.getUse().equals(that.getUse())) {
			return false;
		}
		if (this.getSource() == null) {
			throw new IllegalStateException("Source required");
		}
		if (!this.getSource().equals(that.getSource())) {
			return false;
		}
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Date range required");
		} else {
			if (this.getDateRange().getStartDate() == null) {
				throw new IllegalStateException("Start date required");
			}
			if (this.getDateRange().getStartDate()
					.equals(that.getDateRange().getStartDate()));
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getUse() == null) {
			throw new IllegalStateException("Use required");
		}
		if (this.getSource() == null) {
			throw new IllegalStateException("Source required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getUse().hashCode();
		hashCode = 29 * hashCode + this.getSource().hashCode();
		if (this.getDateRange() != null) {
			if (this.getDateRange().getStartDate() != null) {
				hashCode = 31 * hashCode + this.getDateRange()
						.getStartDate().hashCode();
			}
		}
		return hashCode;
	}
}