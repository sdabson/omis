package omis.health.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkResults;
import omis.health.domain.component.LabWorkSampleRestrictions;

/**
 * Lab Work Implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 1, 2014)
 * @since OMIS 3.0
 */
public class LabWorkImpl implements LabWork {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private OffenderAppointmentAssociation offenderAppointmentAssociation;
	
	private Boolean rescheduleRequired;
	
	private String schedulingNotes;
	
	private String sampleNotes;

	private LabWorkOrder order;
	
	private Lab sampleLab;
	
	private LabWorkCategory labWorkCategory;
	
	private LabWorkResults results;
	
	private LabWorkSampleRestrictions sampleRestrictions;
	
	private Boolean sampleTaken;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default implementation of lab work.
	 */
	public LabWorkImpl() {
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
	public OffenderAppointmentAssociation getOffenderAppointmentAssociation() {
		return this.offenderAppointmentAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffenderAppointmentAssociation(
			final OffenderAppointmentAssociation 
			offenderAppointmentAssociation) {
		this.offenderAppointmentAssociation = offenderAppointmentAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getRescheduleRequired() {
		return this.rescheduleRequired;
	}

	/** {@inheritDoc} */
	@Override
	public void setRescheduleRequired(final Boolean rescheduleRequired) {
		this.rescheduleRequired = rescheduleRequired;
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
	public String getSampleNotes() {
		return this.sampleNotes;
	}

	/** {@inheritDoc} */
	@Override
	public void setSampleNotes(final String sampleNotes) {
		this.sampleNotes = sampleNotes;
	}

	/** {@inheritDoc} */
	@Override
	public LabWorkOrder getOrder() {
		return this.order;
	}

	/** {@inheritDoc} */
	@Override
	public void setOrder(final LabWorkOrder order) {
		this.order = order;
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
	public LabWorkCategory getLabWorkCategory() {
		return this.labWorkCategory;
	}

	/** {@inheritDoc} */
	@Override
	public void setLabWorkCategory(final LabWorkCategory labWorkCategory) {
		this.labWorkCategory = labWorkCategory;
	}

	/** {@inheritDoc} */
	@Override
	public LabWorkResults getResults() {
		return this.results;
	}

	/** {@inheritDoc} */
	@Override
	public void setResults(final LabWorkResults results) {
		this.results = results;
	}

	/** {@inheritDoc} */
	@Override
	public LabWorkSampleRestrictions getSampleRestrictions() {
		return this.sampleRestrictions;
	}

	/** {@inheritDoc} */
	@Override
	public void setSampleRestrictions(
			final LabWorkSampleRestrictions sampleRestrictions) {
		this.sampleRestrictions = sampleRestrictions;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getSampleTaken() {
		return this.sampleTaken;
	}

	/** {@inheritDoc} */
	@Override
	public void setSampleTaken(final Boolean sampleTaken) {
		this.sampleTaken = sampleTaken;
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
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof LabWork)) {
			return false;
		}
		
		LabWork that = (LabWork) o;
		
		if (this.getOffenderAppointmentAssociation() == null) {
			throw new IllegalStateException("Offender Appointment Association" 
					+ " required.");
		}
		if (!this.getOffenderAppointmentAssociation().equals(
				that.getOffenderAppointmentAssociation())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffenderAppointmentAssociation() == null) {
			throw new IllegalStateException("Offender Appointment Association" 
					+ " required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getOffenderAppointmentAssociation()
				.hashCode();
		
		return hashCode;
	}
}