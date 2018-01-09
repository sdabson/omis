package omis.health.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.health.domain.HealthRequest;
import omis.health.domain.InternalReferral;
import omis.health.domain.InternalReferralReason;
import omis.health.domain.InternalReferralStatusReason;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.ProviderLevel;
import omis.health.domain.ReferralLocationDesignator;

/**
 * Inside referral implementation.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public class InternalReferralImpl implements InternalReferral {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private OffenderAppointmentAssociation offenderAppointmentAssociation;
	
	private ProviderLevel providerLevel;
	
	private InternalReferralReason reason;
	
	private InternalReferralStatusReason statusReason;
	
	private Boolean rescheduleRequired;
	
	private String schedulingNotes;
	
	private String assessmentNotes;
	
	private HealthRequest actionRequest;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private ReferralLocationDesignator locationDesignator;
	
	/**
	 * Instantiates a default instance of inside referral implementation.
	 */
	public InternalReferralImpl() {
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
	public void setProviderLevel(final ProviderLevel providerLevel) {
		this.providerLevel = providerLevel;
	}

	/** {@inheritDoc} */
	@Override
	public ProviderLevel getProviderLevel() {
		return this.providerLevel;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setReason(final InternalReferralReason reason) {
		this.reason = reason;
	}

	/** {@inheritDoc} */
	@Override
	public InternalReferralReason getReason() {
		return this.reason;
	}
	
	/** {@inheritDoc} */
	@Override
	public InternalReferralStatusReason getStatusReason() {
		return this.statusReason;
	}

	/** {@inheritDoc} */
	@Override
	public void setStatusReason(
			final InternalReferralStatusReason statusReason) {
		this.statusReason = statusReason;
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
	public String getAssessmentNotes() {
		return this.assessmentNotes;
	}

	@Override
	public void setAssessmentNotes(final String assessmentNotes) {
		this.assessmentNotes = assessmentNotes;
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
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public ReferralLocationDesignator getLocationDesignator() {
		return this.locationDesignator;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setLocationDesignator(final ReferralLocationDesignator 
			locationDesignator) {
		this.locationDesignator = locationDesignator;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof InternalReferral)) {
			return false;
		}
		
		InternalReferral that = (InternalReferral) o;
		
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