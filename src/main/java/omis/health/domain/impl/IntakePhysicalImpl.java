package omis.health.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.health.domain.HealthRequest;
import omis.health.domain.IntakePhysical;
import omis.health.domain.OffenderAppointmentAssociation;

/**
 * Intake Physical Implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 1, 2014)
 * @since OMIS 3.0
 */
public class IntakePhysicalImpl implements IntakePhysical {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private OffenderAppointmentAssociation offenderAppointmentAssociation;
	
	private Boolean kept;
	
	private Date timeKept;
	
	private Boolean rescheduleRequired;
	
	private String notes;
	
	private HealthRequest actionRequest;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default implementation of intake physical.
	 */
	public IntakePhysicalImpl() {
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
	public Boolean getKept() {
		return this.kept;
	}

	/** {@inheritDoc} */
	@Override
	public void setKept(final Boolean kept) {
		this.kept = kept;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getRescheduleRequired() {
		return this.rescheduleRequired;
	}

	/** {@inheritDoc} */
	@Override
	public void setRescheduleRequired(
			final Boolean rescheduleRequired) {
		this.rescheduleRequired = rescheduleRequired;
	}

	/** {@inheritDoc} */
	@Override
	public Date getTimeKept() {
		return this.timeKept;
	}

	/** {@inheritDoc} */
	@Override
	public void setTimeKept(final Date timeKept) {
		this.timeKept = timeKept;
	}

	/** {@inheritDoc} */
	@Override
	public String getNotes() {
		return this.notes;
	}

	/** {@inheritDoc} */
	@Override
	public void setNotes(final String notes) {
		this.notes = notes;
	}

	/** {@inheritDoc} */
	@Override
	public HealthRequest getActionRequest() {
		return this.actionRequest;
	}

	/** {@inheritDoc} */
	@Override
	public void setActionRequest(final HealthRequest actionRequest) {
		this.actionRequest = actionRequest;
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
		if (!(o instanceof IntakePhysical)) {
			return false;
		}
		
		IntakePhysical that = (IntakePhysical) o;
		
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