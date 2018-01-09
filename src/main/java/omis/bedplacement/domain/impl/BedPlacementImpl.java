/**
 * 
 */
package omis.bedplacement.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.bedplacement.domain.BedPlacement;
import omis.bedplacement.domain.BedPlacementReason;
import omis.datatype.DateRange;
import omis.facility.domain.Bed;
import omis.offender.domain.Offender;

/**
 * Implementation of bed placement.
 * 
 * @author Joel Norris (Feb, 06 2013)
 * @version 0.1.0
 * @since OMIS 3.0
 */
public class BedPlacementImpl implements BedPlacement {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Bed bed;
	
	private Offender offender;
	
	private BedPlacementReason bedPlacementReason;
	
	private Boolean confirmed;

	private String description;
	
	private DateRange dateRange;

	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/** Instantiates a default instance of bed placement implementation. */
	public BedPlacementImpl() {
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
	public Bed getBed() {
		return this.bed;
	}

	/** {@inheritDoc} */
	@Override
	public void setBed(final Bed bed) {
		this.bed = bed;
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
	public BedPlacementReason getBedPlacementReason() {
		return this.bedPlacementReason;
	}

	/** {@inheritDoc} */
	@Override
	public void setBedPlacementReason(
			final BedPlacementReason bedPlacementReason) {
		this.bedPlacementReason = bedPlacementReason;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getConfirmed() {
		return this.confirmed;
	}

	/** {@inheritDoc} */
	@Override
	public void setConfirmed(final Boolean confirmed) {
		this.confirmed = confirmed;
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
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof BedPlacement)) {
			return false;
		}
		
		BedPlacement that = (BedPlacement) o;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getBed() == null) {
			throw new IllegalStateException("Bed required");
		}
		if (!this.getBed().equals(that.getBed())) {
			return false;
		}
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Date range required");
		}
		if (!this.getDateRange().equals(that.getDateRange())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (this.getBed() == null) {
			throw new IllegalStateException("Bed required");
		}
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Date range required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getBed().hashCode();
		hashCode = 29 * hashCode + this.getDateRange().hashCode();
		
		return hashCode;
	}
}