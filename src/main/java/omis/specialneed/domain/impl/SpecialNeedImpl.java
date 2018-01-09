package omis.specialneed.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedCategory;
import omis.specialneed.domain.SpecialNeedClassification;
import omis.specialneed.domain.SpecialNeedSource;

/**
 * Implentation of special need.
 * 
 * @author Joel Norris 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb, 14 2013)
 * @since OMIS 3.0
 */
public class SpecialNeedImpl implements SpecialNeed {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String comment;
	
	private String sourceComment;
	
	private DateRange dateRange;
	
	private SpecialNeedCategory category;
	
	private SpecialNeedSource source;
	
	private Offender offender;
	
	private SpecialNeedClassification classification;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;

	/** Instantiates a special need. */
	public SpecialNeedImpl() {
		//empty constructor
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
	public String getComment() {
		return this.comment;
	}

	/** {@inheritDoc} */
	@Override
	public void setComment(final String comment) {
		this.comment = comment;
	}

	/** {@inheritDoc} */
	@Override
	public String getSourceComment() {
		return this.sourceComment;
	}

	/** {@inheritDoc} */
	@Override
	public void setSourceComment(final String sourceComment) {
		this.sourceComment = sourceComment;
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
	public SpecialNeedCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final SpecialNeedCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedSource getSource() {
		return this.source;
	}

	/** {@inheritDoc} */
	@Override
	public void setSource(final SpecialNeedSource source) {
		this.source = source;
	}
	
	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}
	

	/** {@inheritDoc} */
	@Override
	public SpecialNeedClassification getClassification() {
		return this.classification;
	}

	/** {@inheritDoc} */
	@Override
	public void setClassification(
			final SpecialNeedClassification classification) {
		this.classification = classification;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
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
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SpecialNeed)) {
			return false;
		}
		
		SpecialNeed that = (SpecialNeed) obj;
		
		if (this.getSource() == null) {
			throw new IllegalStateException("Source required");
		}

		if (!this.getSource().equals(that.getSource())) {
			return false;
		}

		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required");
		}

		if (!this.getCategory().equals(that.getCategory())) {
			return false;
		}

		if (this.getComment() != null) {
			if (!this.getComment().equals(that.getComment())) {
				return false;
			}
		} else if (that.getComment() != null) {
			return false;
		}
		
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Date range required");
		}
		
		if (!this.getDateRange().equals(that.getDateRange())) {
			return false;
		}
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!(this.getOffender().equals(that.getOffender()))) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {

		if (this.getSource() == null) {
			throw new IllegalStateException("Source required");
		}
		
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required");
		}
		
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Date range required");
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getSource().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		hashCode = 29 * hashCode + this.getDateRange().hashCode();
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		
		if (this.getComment() != null) {
			hashCode = 29 * hashCode + this.getComment().hashCode();
		}
		return hashCode;
	}
}