package omis.health.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.health.domain.ExternalReferral;
import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.ExternalReferralStatusReason;
import omis.health.domain.HealthRequest;
import omis.health.domain.OffenderAppointmentAssociation;

/**
 * External Referral Implementation.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralImpl implements ExternalReferral {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private OffenderAppointmentAssociation offenderAppointmentAssociation;
	
	private ExternalReferralStatusReason statusReason;
	
	private Boolean rescheduleRequired;
	
	private String schedulingNotes;
	
	private String assessmentNotes;
	
	private Date reportedDate;
	
	private HealthRequest actionRequest;
	
	private ExternalReferralAuthorization authorization;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default instance of external referral.
	 */
	public ExternalReferralImpl() {
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
	public ExternalReferralStatusReason getStatusReason() {
		return this.statusReason;
	}

	/** {@inheritDoc} */
	@Override
	public void setStatusReason(
			final ExternalReferralStatusReason statusReason) {
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
	public void setSchedulingNotes(final String assessmentNotes) {
		this.schedulingNotes = assessmentNotes;
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
	public Date getReportedDate() {
		 return this.reportedDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setReportedDate(final Date reportedDate) {
		this.reportedDate = reportedDate;
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
	public ExternalReferralAuthorization getAuthorization() {
		return this.authorization;
	}

	/** {@inheritDoc} */
	@Override
	public void setAuthorization(
			final ExternalReferralAuthorization authorization) {
		this.authorization = authorization;
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
		if (!(o instanceof ExternalReferral)) {
			return false;
		}
		
		ExternalReferral that = (ExternalReferral) o;
		
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