package omis.health.service;

import java.util.Date;
import java.util.List;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.HealthAppointment;
import omis.health.domain.HealthRequest;
import omis.health.domain.InternalReferral;
import omis.health.domain.InternalReferralReason;
import omis.health.domain.InternalReferralStatusReason;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkRequirement;
import omis.health.domain.LabWorkRequirementRequest;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderInternalReferralAssociation;
import omis.health.domain.ProviderLevel;
import omis.health.domain.ReferralLocationDesignator;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkResults;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.health.exception.InternalReferralException;
import omis.health.exception.ProviderScheduleException;
import omis.offender.domain.Offender;

/**
 * Scheduler for inside referrals.
 *
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.0 (Apr 11, 2014)
 * @since OMIS 3.0
 */
public interface InternalReferralScheduler {

	/**
	 * Schedules inside referral.
	 *
	 * @param offender offender for whom to schedule referral.
	 * @param date date
	 * @param category category
	 * @param reason reason
	 * @param providerLevel provider level
	 * @param providerAssignment assignment of provider to assess referral
	 * @param facility facility
	 * @param locationDesignator referral location designator
	 * @param notes notes
	 * @return scheduled referral
	 * @throws DuplicateEntityFoundException if the referral is already
	 * scheduled.
	 * @throws ProviderScheduleException if the provider is not scheduled for
	 * for the designated date.
	 * @throws DateConflictException if the dates of the referral
	 * conflict with an existing referral
	 */
	InternalReferral schedule(Offender offender, Facility facility, Date date,
			InternalReferralReason reason, ProviderLevel providerLevel,
			ProviderAssignment providerAssignment,
			ReferralLocationDesignator locationDesignator, String notes)
		throws DuplicateEntityFoundException, ProviderScheduleException,
		DateConflictException;

	/**
	 * Schedules lab work with consideration of the specified internal referral.
	 * 
	 * @param internalReferral internal referral
	 * @param labWorkCategory lab work category
	 * @param sampleLab sample lab
	 * @param sampleDate sample date
	 * @param sampleTaken sample taken
	 * @param sampleNotes sample notes
	 * @param results lab work results
	 * @param order lab work order
	 * @param sampleRestrictions lab work sample restrictions
	 * @param schedulingNotes scheduling notes
	 * @return lab work
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	LabWork scheduleLabWork(InternalReferral internalReferral, 
			LabWorkCategory labWorkCategory, Lab sampleLab, Date sampleDate,
			Boolean sampleTaken, String sampleNotes, LabWorkResults results, 
			LabWorkOrder order, LabWorkSampleRestrictions sampleRestrictions, 
			String schedulingNotes)
		throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified lab work with the specified values. If no existing
	 * lab work requirement exists between the specified internal referral's
	 * offender appointment association and the specified lab work, a new one
	 * will be created.
	 * 
	 * @param internalReferral internal referral
	 * @param labWork lab work
	 * @param labWorkCategory lab work category
	 * @param sampleLab sample lab
	 * @param sampleDate sample date
	 * @param sampleTaken sample taken
	 * @param sampleNotes sample notes
	 * @param results lab work results
	 * @param order lab work order
	 * @param sampleRestrictions lab work sample restrictions
	 * @param schedulingNotes scheduling notes
	 * @return lab work
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	LabWork updateLabWork(InternalReferral internalReferral, LabWork labWork,
			LabWorkCategory labWorkCategory, Lab sampleLab, Date sampleDate,
			Boolean sampleTaken, String sampleNotes, LabWorkResults results, 
			LabWorkOrder order, LabWorkSampleRestrictions sampleRestrictions, 
			String schedulingNotes)
		throws DuplicateEntityFoundException;
	
	/**
	 * Schedules inside referral from request.
	 *
	 * @param offender offender for whom to schedule referral.
	 * @param date date
	 * @param reason reason
	 * @param providerLevel provider level
	 * @param providerAssignment assignment of provider to assess referral
	 * @param facility facility
	 * @param locationDesignator referral location designator
	 * @param notes notes
	 * @return scheduled referral
	 * @throws DuplicateEntityFoundException if the referral is already
	 * scheduled
	 * @throws ProviderScheduleException if the provider is not scheduled for
	 * for the designated date.
	 * @throws DateConflictException if the dates of the referral
	 * conflict with an existing referral
	 */
	InternalReferral scheduleFromRequest(HealthRequest request, Date date,
			InternalReferralReason reason, ProviderLevel providerLevel,
			ProviderAssignment providerAssignment,
			ReferralLocationDesignator locationDesignator, String notes)
		throws DuplicateEntityFoundException, ProviderScheduleException,
		DateConflictException;

	/**
	 * Updates an existing internal referral.
	 *
	 * @param internalReferral internal referral to update
	 * @param date date
	 * @param reason reason
	 * @param providerLevel provider level
	 * @param providerAssignment provider assignment
	 * @param locationDesignator location designator
	 * @param notes notes
	 * @return updated internal referral
	 * @throws DuplicateEntityFoundException if the updated referral is already
	 * scheduled
	 * @throws DateConflictException if the dates of the referral
	 * conflict with an existing referral
	 */
	InternalReferral updateSchedule(InternalReferral internalReferral,
			Date date, InternalReferralReason reason,
			ProviderLevel providerLevel,
			ProviderAssignment providerAssignment,
			ReferralLocationDesignator locationDesignator, String notes)
				throws DateConflictException, DuplicateEntityFoundException;

	/**
	 * Requests that the referral be rescheduled.
	 *
	 * @param referral referral
	 */
	void requestReschedule(InternalReferral referral);

	/** Reschedules internal referral.
	 * @param internalReferral internal referral.
	 * @param statusReason reschedule status reason.
	 * @param date date.
	 * @param reason reason.
	 * @param providerLevel provider level.
	 * @param providerAssignment provider assignment.
	 * @param notes notes.
	 * @param referralLocationDesignator referral location designator.
	 * @throws DuplicateEntityFoundException if the referral is already
	 * rescheduled.
	 * @throws ProviderScheduleException if the provider is not scheduled for
	 * for the designated date.
	 * @return internal referral. */
	InternalReferral reschedule(InternalReferral internalReferral,
			InternalReferralStatusReason statusReason, Date date,
			InternalReferralReason reason, ProviderLevel providerLevel,
			ProviderAssignment providerAssignment, String notes,
			ReferralLocationDesignator referralLocationDesignator)
		throws DuplicateEntityFoundException, ProviderScheduleException,
		DateConflictException, InternalReferralException;
	
	/**
	 * Assigns an additional provider to a referral.
	 *
	 * @param referral referral to which to assign provider
	 * @param providerAssignment assignment of provider to assign to referral
	 * @return association of provider to referral
	 * @throws DuplicateEntityFoundException if the provider is already assigned
	 * to the referral during the provider assignment
	 */
	ProviderInternalReferralAssociation assignAdditionalProviders(
			InternalReferral referral, ProviderAssignment providerAssignment)
		throws DuplicateEntityFoundException;

	/**
	 * Removes a provider from a scheduled inside referral.
	 *
	 * @param providerInsideReferralAssociation association of provider to
	 * inside referral
	 */
	void removeProvider(
			ProviderInternalReferralAssociation
				providerInsideReferralAssociation);
	
	/**
	 * Removes the specified lab work from an internal referral and, if it
	 * there are no other requirements to other appointments, removes the lab
	 * work completely.
	 * 
	 * @param internalReferral internal referral
	 * @param labWork lab work
	 */
	void removeLabWork(InternalReferral internalReferral, LabWork labWork);

	/** Returns provider by inside referral.
	 * @param internalReferral internal referral.
	 * @return providerAssignment provider assignment. */
	ProviderAssignment findProviderByInternalReferral(
			InternalReferral internalReferral);

	/** Returns health appointment by internal referral.
	 * @param internalReferral internal referral.
	 * @return health appointment. */
	HealthAppointment findHealthAppointmentByInternalReferral(InternalReferral
			internalReferral);

	/**
	 * Returns reasons.
	 *
	 * @return reasons
	 */
	List<InternalReferralReason> findReasons();

	/**
	 * Returns provider levels.
	 *
	 * @return provider levels
	 */
	List<ProviderLevel> findProviderLevels();
	
	/**
	 * Returns internal provider assignments for facility on date.
	 * 
	 * @param facility facility
	 * @param date date
	 * @return internal provider assignments for facility on date 
	 */
	List<ProviderAssignment> findInternalProviderAssignmentsForFacility(
			Facility facility, Date date);
	
	/**
	 * Returns status reasons for rescheduling an internal referral.
	 * 
	 * @return status reasons for rescheduling an internal referral
	 */
	List<InternalReferralStatusReason> findRescheduleStatusReasons();
	
	/**
	 * Returns a list of lab work for the specified internal referral.
	 * 
	 * @param internalReferral internal referral
	 * @return list of lab works
	 */
	List<LabWork> findLabWorks(InternalReferral internalReferral);
	
	/**
	 * Returns lab work requirements for the specified internal referral.
	 * 
	 * @param internalReferral internal referral
	 * @return list of required lab work requirements
	 */
	List<LabWorkRequirement> findLabWorkRequirements(
			InternalReferral internalReferral);
	
	/**
	 * Returns a list of lab work requirement requests with the specified
	 * health request.
	 * 
	 * @param healthRequest health request
	 * @return list of lab work requirement requests
	 */
	List<LabWorkRequirementRequest> findLabWorkRequirementRequests(
			HealthRequest healthRequest);
	
	/**
	 * Returns a list of labs
	 * 
	 * @return list of labs
	 */
	List<Lab> findLabs();
	
	/**
	 * Returns a list of lab work categories.
	 * 
	 * @return list of lab work categories
	 */
	List<LabWorkCategory> findLabWorkCategories();
}