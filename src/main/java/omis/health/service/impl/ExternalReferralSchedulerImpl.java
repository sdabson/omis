package omis.health.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.dao.ExternalReferralDao;
import omis.health.dao.ExternalReferralRequestAuthorizationRequirementDao;
import omis.health.dao.ExternalReferralStatusReasonDao;
import omis.health.dao.HealthAppointmentDao;
import omis.health.dao.ProviderAssignmentDao;
import omis.health.dao.ProviderExternalReferralAssociationDao;
import omis.health.domain.ExternalReferral;
import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.ExternalReferralRequestAuthorizationRequirement;
import omis.health.domain.ExternalReferralStatusReason;
import omis.health.domain.HealthAppointment;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.HealthRequestStatus;
import omis.health.domain.MedicalFacility;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderExternalReferralAssociation;
import omis.health.exception.ProviderScheduleException;
import omis.health.exception.WrongExternalReferralStatusReasonException;
import omis.health.service.ExternalReferralScheduler;
import omis.health.service.delegate.OffenderAppointmentAssociationDelegate;
import omis.instance.factory.InstanceFactory;

/**
 * Implementation of scheduler for external referrals.
 * 
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 8, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralSchedulerImpl
		implements ExternalReferralScheduler {
	
	/* Data access objects. */
	
	private final ExternalReferralDao externalReferralDao;
		
	private final ProviderExternalReferralAssociationDao
	providerExternalReferralAssociationDao;
	
	private final ExternalReferralRequestAuthorizationRequirementDao
	externalReferralRequestAuthorizationRequirementDao;
	
	private final ExternalReferralStatusReasonDao
	externalReferralStatusReasonDao;
	
	private final ProviderAssignmentDao providerAssignmentDao;
	
	private final HealthAppointmentDao healthAppointmentDao;
		
	/* Instance factories. */
	
	private final InstanceFactory<ExternalReferral>
	externalReferralInstanceFactory;
	
	private final InstanceFactory<ProviderExternalReferralAssociation>
	providerExternalReferralAssociationInstanceFactory;
	
	/* Delegates. */
	
	private final OffenderAppointmentAssociationDelegate
	offenderAppointmentAssociationDelegate;
	
	/* Helpers. */
	
	private final AuditComponentRetriever auditComponentRetriever;

	/* Constructors. */
	
	/**
	 * Instantiates an implementation of scheduler for external referrals.
	 * 
	 * @param externalReferralDao data access object for external referrals
	 * @param externalReferralInstanceFactory instance factory for external
	 * referrals
	 * @param providerExternalReferralAssociationDao data access object for
	 * association of provider to external referral
	 * @param providerExternalReferralAssociationInstanceFactory instance
	 * factory for association of provider to external referral
	 * @param offenderAppointmentAssociationDelegate delegate for offender
	 * appointment associations
	 * @param externalReferralRequestAuthorizationRequirementDao
	 * data access object for external referral request authorization
	 * requirements
	 * @param externalReferralStatusReasonDao data access object for
	 * external referral status reasons
	 * @param providerAssignmentDao data access object for provider assignments
	 * @param healthAppointmentDao data access object for health appointments
	 * @param auditComponentRetriever retriever of audit components
	 */
	public ExternalReferralSchedulerImpl(
			final ExternalReferralDao externalReferralDao,
			final InstanceFactory<ExternalReferral>
				externalReferralInstanceFactory,
			final ProviderExternalReferralAssociationDao
				providerExternalReferralAssociationDao,
			final InstanceFactory<ProviderExternalReferralAssociation>
				providerExternalReferralAssociationInstanceFactory,
			final OffenderAppointmentAssociationDelegate
				offenderAppointmentAssociationDelegate,
			final ExternalReferralRequestAuthorizationRequirementDao
				externalReferralRequestAuthorizationRequirementDao,
			final ExternalReferralStatusReasonDao
				externalReferralStatusReasonDao,
			final ProviderAssignmentDao providerAssignmentDao,
			final HealthAppointmentDao healthAppointmentDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.externalReferralDao = externalReferralDao;
		this.externalReferralInstanceFactory = externalReferralInstanceFactory;
		this.providerExternalReferralAssociationDao
			= providerExternalReferralAssociationDao;
		this.providerExternalReferralAssociationInstanceFactory
			= providerExternalReferralAssociationInstanceFactory;
		this.offenderAppointmentAssociationDelegate
			= offenderAppointmentAssociationDelegate;
		this.externalReferralRequestAuthorizationRequirementDao
			= externalReferralRequestAuthorizationRequirementDao;
		this.externalReferralStatusReasonDao = externalReferralStatusReasonDao;
		this.providerAssignmentDao = providerAssignmentDao;
		this.healthAppointmentDao = healthAppointmentDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Methods implementations. */
	
	/** {@inheritDoc} */
	@Override
	public ExternalReferral schedule(
			final ExternalReferralAuthorization authorization,
			final ProviderAssignment providerAssignment, final Date date,
			final Date time, final String notes)
					throws DuplicateEntityFoundException {
		if (this.externalReferralDao
				.findByAuthorization(authorization) == null) {
			ExternalReferralRequestAuthorizationRequirement requirement
				= this.externalReferralRequestAuthorizationRequirementDao
					.findByAuthorizationRequest(authorization.getRequest());
			if (requirement != null) {
				requirement.getReferralRequest().setStatus(
						HealthRequestStatus.SCHEDULED);
				this.externalReferralRequestAuthorizationRequirementDao
					.makePersistent(requirement);
			}
			ExternalReferral externalReferral
				= this.externalReferralInstanceFactory.createInstance();
			externalReferral.setAuthorization(authorization);
			externalReferral.setCreationSignature(new CreationSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
			externalReferral.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
			externalReferral.setSchedulingNotes(notes);
			OffenderAppointmentAssociation offenderAppointmentAssociation
				= this.offenderAppointmentAssociationDelegate.create(
						authorization.getRequest().getOffender(),
						date, authorization.getRequest().getFacility(),
						time, null);
			externalReferral.setOffenderAppointmentAssociation(
					offenderAppointmentAssociation);
			externalReferral = this.externalReferralDao
					.makePersistent(externalReferral);
			if (providerAssignment != null) {
				ProviderExternalReferralAssociation association
					= this.providerExternalReferralAssociationInstanceFactory
						.createInstance();
				association.setExternalReferral(externalReferral);
				association.setProviderAssignment(
						providerAssignment);
				association.setPrimary(true);
				association = this.providerExternalReferralAssociationDao
					.makePersistent(association);
			}
			return externalReferral;
		} else {
			throw new DuplicateEntityFoundException(
					"Referral with authorization exists");
		}
	}

	/** {@inheritDoc} */
	@Override
	public void requestReschedule(final ExternalReferral referral) {
		referral.setRescheduleRequired(true);
	}

	/** {@inheritDoc} */
	@Override
	public ExternalReferral reschedule(final ExternalReferral referral,
			final ExternalReferralStatusReason statusReason,
			final ProviderAssignment providerAssignment, final Date date,
			final Date time, final String notes)
					throws DuplicateEntityFoundException,
					WrongExternalReferralStatusReasonException {
		if (statusReason == null) {
			throw new WrongExternalReferralStatusReasonException
			("Status reason required");
		}
		if (!HealthAppointmentStatus.RESCHEDULED.equals(
				statusReason.getAppointmentStatus())) {
			throw new WrongExternalReferralStatusReasonException
			("Status must be rescheduled");
		}
		referral.setStatusReason(statusReason);
		referral.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.externalReferralDao.makePersistent(referral);
		
		if (this.externalReferralDao.find(referral
				.getOffenderAppointmentAssociation().getOffender(), date, 
				time, providerAssignment) != null) {
			throw new DuplicateEntityFoundException(
					"Referral already rescheduled for this offender");
		}
						
		OffenderAppointmentAssociation association =
			referral.getOffenderAppointmentAssociation();
		association.getAppointment()
			.setStatus(HealthAppointmentStatus.RESCHEDULED);
		OffenderAppointmentAssociation offenderAppointmentAssociation = 
				this.offenderAppointmentAssociationDelegate.create(
						association.getOffender(), date, 
						association.getAppointment().getFacility(), time, null);
		
		ExternalReferral externalReferral = this
			.externalReferralInstanceFactory.createInstance();
				externalReferral.setAuthorization(referral.getAuthorization());
				externalReferral.setOffenderAppointmentAssociation(
						offenderAppointmentAssociation);
				externalReferral.setSchedulingNotes(notes);
				externalReferral.setCreationSignature(new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
				externalReferral.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
				externalReferral = this.externalReferralDao.makePersistent(
						externalReferral);
		if (providerAssignment != null) {
			ProviderExternalReferralAssociation providerAssociation
				= this.providerExternalReferralAssociationInstanceFactory
					.createInstance();
			providerAssociation.setExternalReferral(externalReferral);
			providerAssociation.setProviderAssignment(providerAssignment);
			providerAssociation.setPrimary(true);
			this.providerExternalReferralAssociationDao
				.makePersistent(providerAssociation);
		}
		return externalReferral;
	}
	
	/** {@inheritDoc} */
	@Override
	public ExternalReferral update(final ExternalReferral referral,
			final ProviderAssignment providerAssignment, final Date date,
			final Date time, final String notes)
					throws DuplicateEntityFoundException,
					ProviderScheduleException {
		if (this.externalReferralDao.findExcluding(
				referral, referral.getOffenderAppointmentAssociation()
					.getOffender(), date, time, providerAssignment) != null) {
			throw new DuplicateEntityFoundException(
					"Referral already exists for this offender");
		}
		ProviderExternalReferralAssociation association
			= this.providerExternalReferralAssociationDao
					.findPrimaryForReferral(referral);
		if (association != null) {
			if (providerAssignment != null) {
				if (!providerAssignment.equals(association
						.getProviderAssignment())) {
					association.setProviderAssignment(providerAssignment);
					association = this.providerExternalReferralAssociationDao
						.makePersistent(association);
				}
			} else {
				this.providerExternalReferralAssociationDao
					.makeTransient(association);
			}
		} else {
			if (providerAssignment != null) {
				association
					= this.providerExternalReferralAssociationInstanceFactory
					.createInstance();
				association.setExternalReferral(referral);
				association.setProviderAssignment(
						providerAssignment);
				association.setPrimary(true);
				association = this.providerExternalReferralAssociationDao
						.makePersistent(association);
			}
		}
		HealthAppointment appointment
			= referral.getOffenderAppointmentAssociation().getAppointment();
		appointment.setDate(date);
		appointment.setStartTime(time);
		appointment = this.healthAppointmentDao.makePersistent(appointment);
		referral.setSchedulingNotes(notes);
		return this.externalReferralDao.makePersistent(referral);
	}
	
	/** {@inheritDoc} */
	@Override
	public ProviderExternalReferralAssociation assignAdditionalProvider(
			final ExternalReferral externalReferral,
			final ProviderAssignment providerAssignment) {
		ProviderExternalReferralAssociation association
			= this.providerExternalReferralAssociationInstanceFactory
				.createInstance();
		association.setExternalReferral(externalReferral);
		association.setProviderAssignment(providerAssignment);
		association.setPrimary(false);
		return this.providerExternalReferralAssociationDao
				.makePersistent(association);
	}

	/** {@inheritDoc} */
	@Override
	public void removeProvider(
			final ProviderExternalReferralAssociation association) {
		this.providerExternalReferralAssociationDao.makeTransient(association);
	}

	/** {@inheritDoc} */
	@Override
	public List<ExternalReferralStatusReason> findRescheduleStatusReasons() {
		return this.externalReferralStatusReasonDao
				.findByAppointmentStatus(HealthAppointmentStatus.RESCHEDULED);
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderAssignment> findProviderAssignments(
			final Facility facility, final MedicalFacility medicalFacility,
			final Date date) {
		return this.providerAssignmentDao
				.findExternalForFacilityByMedicalFacility(
						facility, medicalFacility, date);
	}

	/** {@inheritDoc} */
	@Override
	public ProviderAssignment findProviderAssignment(
			final ExternalReferral externalReferral) {
		ProviderExternalReferralAssociation association
			= this.providerExternalReferralAssociationDao
				.findPrimaryForReferral(externalReferral);
		if (association != null) {
			return association.getProviderAssignment();
		} else {
			return null;
		}
	}	
}