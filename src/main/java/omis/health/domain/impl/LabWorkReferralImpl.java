package omis.health.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.health.domain.HealthRequest;
import omis.health.domain.LabWorkReferral;
import omis.health.domain.LabWorkReferralStatusReason;
import omis.health.domain.OffenderAppointmentAssociation;

/**
 * @author Sheronda Vaughn
 * @author Joel Norris
 * @version 0.1.0 (Jun 30, 2014)
 * @since OMIS 3.0
 */
public class LabWorkReferralImpl implements LabWorkReferral {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private OffenderAppointmentAssociation offenderAppointmentAssociation;
	
	private HealthRequest actionRequest;
	
	private LabWorkReferralStatusReason statusReason;
	
	private String assessmentNotes;
	
	private String schedulingNotes;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;

	/**
	 * Instantiates a default instance of lab work referral.
	 */
	public LabWorkReferralImpl() {
		// Default constructor.
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
			final OffenderAppointmentAssociation offenderAppointmentAssociation) {
		this.offenderAppointmentAssociation = offenderAppointmentAssociation;
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
	public LabWorkReferralStatusReason getStatusReason() {
		return this.statusReason;
	}

	/** {@inheritDoc} */
	@Override
	public void setStatusReason(
			final LabWorkReferralStatusReason statusReason) {
		this.statusReason = statusReason;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getAssessmentNotes() {
		return this.assessmentNotes;
	}

	/** {@inheritDoc} */
	@Override
	public void setAssessmentNotes(final String assessmentNotes) {
		this.assessmentNotes = assessmentNotes;
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
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof LabWorkReferral)) {
			return false;
		}
		
		LabWorkReferral that = (LabWorkReferral) obj;
		
		if (this.getOffenderAppointmentAssociation() == null) {
			throw new IllegalStateException(
					"Offender appointment association required.");
		}
		if (this.getOffenderAppointmentAssociation().equals(
				that.getOffenderAppointmentAssociation())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffenderAppointmentAssociation() == null) {
			throw new IllegalStateException(
					"Offender appointment association required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode 
				+ this.getOffenderAppointmentAssociation().hashCode();
		return hashCode;
	}
}
