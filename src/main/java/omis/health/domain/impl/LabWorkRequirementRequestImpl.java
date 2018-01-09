package omis.health.domain.impl;

import java.util.Date;


import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.health.domain.HealthRequest;
import omis.health.domain.Lab;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkRequirementRequest;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkSampleRestrictions;

/**
 * Implementation of request for lab work requirement.
 * 
 * @author Sheronda Vaughn
 * @author Stephen Abson
 * @version 0.1.0 (June 4, 2014)
 * @since OMIS 3.0
 */
public class LabWorkRequirementRequestImpl
		implements LabWorkRequirementRequest {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private HealthRequest healthRequest;
	
	private LabWorkCategory category;
	
	private Date sampleDate;
	
	private Lab sampleLab;
	
	private LabWorkSampleRestrictions sampleRestrictions;
	
	private Lab resultsLab;
	
	private String schedulingNotes;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;

	private LabWorkOrder order;
	
	/**
	 * Instantiates an implementation of request for lab work requirement.
	 */
	public LabWorkRequirementRequestImpl() {
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
	public HealthRequest getHealthRequest() {
		return this.healthRequest;
	}

	/** {@inheritDoc} */
	@Override
	public void setHealthRequest(final HealthRequest healthRequest) {
		this.healthRequest = healthRequest;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setOrder(final LabWorkOrder order) {
		this.order = order;
	}

	/** {@inheritDoc} */
	@Override
	public LabWorkOrder getOrder() {
		return this.order;
	}
	
	/** {@inheritDoc} */
	@Override
	public LabWorkCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final LabWorkCategory category) {
		this.category = category;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getSampleDate() {
		return this.sampleDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setSampleDate(final Date sampleDate) {
		this.sampleDate = sampleDate;
	}

	/** {@inheritDoc} */
	@Override
	public Lab getSampleLab() {
		return this.sampleLab;
	}

	/** {@inheritDoc} */
	@Override
	public void setSampleLab(final Lab sampleLab) {
		this.sampleLab = sampleLab;
	}

	/** {@inheritDoc} */
	@Override
	public void setSampleRestrictions(
			final LabWorkSampleRestrictions sampleRestrictions) {
		this.sampleRestrictions = sampleRestrictions;
	}

	/** {@inheritDoc} */
	@Override
	public LabWorkSampleRestrictions getSampleRestrictions() {
		return this.sampleRestrictions;
	}
	
	/** {@inheritDoc} */
	@Override
	public Lab getResultsLab() {
		return this.resultsLab;
	}

	/** {@inheritDoc} */
	@Override
	public void setResultsLab(final Lab resultsLab) {
		this.resultsLab = resultsLab;
	}

	/** {@inheritDoc} */
	@Override
	public String getSchedulingNotes() {
		return this.schedulingNotes;
	}

	/** {@inheritDoc} */
	@Override
	public void setSchedulingNotes(final String schedulingNotes) {
		this.schedulingNotes = schedulingNotes;
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
	public void setUpdateSignature(
			final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof LabWorkRequirementRequest)) {
			return false;
		}
		LabWorkRequirementRequest that = (LabWorkRequirementRequest) obj;
		if (this.getHealthRequest() == null) {
			throw new IllegalStateException("Health request required");
		}
		if (!this.getHealthRequest().equals(that.getHealthRequest())) {
			return false;
		}
		if (this.getCategory() != null) {
			if (!this.getCategory().equals(that.getCategory())){
				return false;
			}
		} else if (that.getCategory() != null) {
			return false;
		}
		if (this.getSampleDate() != null) {
			if (!this.getSampleDate().equals(that.getSampleDate())) {
				return false;
			}
		} else if (that.getSampleDate() != null) {
			return false;
		}
		if (this.getSampleLab() != null) {
			if (!this.getSampleLab().equals(that.getSampleLab())) {
				return false;
			}
		} else if (that.getSampleLab() != null) {
			return false;
		}
		if (this.getResultsLab() != null) {
			if (!this.getResultsLab().equals(that.getResultsLab())) {
				return false;
			}
		} else if (that.getResultsLab() != null) {
			return false;
		}
		return true;
	} 
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getHealthRequest() == null) {
			throw new IllegalStateException("Health request required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getHealthRequest().hashCode();
		if (this.getCategory() != null) {
			hashCode = 29 * hashCode + this.getCategory().hashCode();
		}
		if (this.getSampleDate() != null) {
			hashCode = 29 * hashCode + this.getSampleDate().hashCode();
		}
		if (this.getSampleLab() != null) {
			hashCode = 31 * hashCode + this.getSampleLab().hashCode();
		}
		if (this.getResultsLab() != null) {
			hashCode = 29 * hashCode + this.getResultsLab().hashCode();
		}
		return hashCode;
	}
}