package omis.military.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.military.domain.MilitaryBranch;
import omis.military.domain.MilitaryDischargeStatus;
import omis.military.domain.MilitaryServiceTerm;
import omis.offender.domain.Offender;

/**
 * Military service term implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 13, 2015)
 * @since OMIS 3.0
 */
public class MilitaryServiceTermImpl implements MilitaryServiceTerm {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Offender offender;
	
	private DateRange dateRange;
	
	private MilitaryBranch branch;
	
	private MilitaryDischargeStatus dischargeStatus;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default instance of military service term.
	 */
	public MilitaryServiceTermImpl() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public MilitaryBranch getBranch() {
		return branch;
	}

	/** {@inheritDoc} */
	@Override
	public void setBranch(MilitaryBranch branch) {
		this.branch = branch;
	}

	/** {@inheritDoc} */
	@Override
	public MilitaryDischargeStatus getDischargeStatus() {
		return dischargeStatus;
	}

	/** {@inheritDoc} */
	@Override
	public void setDischargeStatus(MilitaryDischargeStatus dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
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
		if (!(o instanceof MilitaryServiceTerm)) {
			return false;
		}
		
		MilitaryServiceTerm that = (MilitaryServiceTerm) o;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getBranch() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getBranch().equals(that.getBranch())) {
			return false;
		}
		if (this.getDateRange() != null && that.getDateRange() != null) {
			if (this.getDateRange().getStartDate() != null) {
				if (!this.getDateRange().getStartDate().equals(
						that.getDateRange().getStartDate())) {
					return false;
				}
			} else if (that.getDateRange().getStartDate() != null) {
				return false;
			}
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (this.getBranch() == null) {
			throw new IllegalStateException("Name required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getBranch().hashCode();
		if (this.getDateRange() != null) {
			if (this.getDateRange().getStartDate() != null) {
				hashCode = 29 * hashCode + this.getDateRange()
						.getStartDate().hashCode();
			}
		} else {
			throw new IllegalStateException("Date Range required");
		}
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format(
				"Offender: #%s, Branch: %s, Date range: %s, "
				+ "Discharge status: %s, Id: %s",
				this.getOffender(),
				this.getBranch(),
				this.getDateRange(),
				this.getDischargeStatus(),
				this.getId());
	}
}