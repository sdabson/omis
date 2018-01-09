package omis.health.service;

import java.util.Date;
import java.util.List;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.HealthRequest;
import omis.health.domain.HealthRequestCategory;
import omis.health.domain.InternalReferral;
import omis.health.domain.InternalReferralReason;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderLevel;
import omis.health.domain.ReferralLocationDesignator;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkResults;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.health.exception.FollowUpException;
import omis.health.exception.InternalReferralAssessmentException;
import omis.health.exception.ProviderScheduleException;

/**
 * Service to assess internal referrals.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.0 (Apr 30, 2014)
 * @since OMIS 3.0
 */
public interface InternalReferralAssessmentService {

	/**
	 * Assesses an inside referral.
	 * 
	 * @param referral referral
	 * @param time time of assessment
	 * @param notes notes
	 * @return assessed internal referral
	 * @throws InternalReferralAssessmentException if the internal referral
	 * has already been assessed
	 */
	InternalReferral assess(InternalReferral referral, Date time, String notes)
			throws InternalReferralAssessmentException;
	
	/**
	 * Updates assessment of internal referral.
	 * 
	 * @param internalReferral internal referral
	 * @param time time of assessment
	 * @param notes notes
	 * @return updated internal referral
	 */
	InternalReferral update(InternalReferral internalReferral, Date time,
			String notes);
	
	/**
	 * Updates the schedule of the specified internal referral with the 
	 * specified properties.
	 * 
	 * @param internalReferral internal referral
	 * @param date date
	 * @param reason reason
	 * @param providerLevel provider level
	 * @param providerAssignment provider assignment
	 * @param locationDesignator location designator
	 * @param notes notes
	 * @return updated internal referral
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 * @throws DateConflictException date conflict exception
	 * @throws ProviderScheduleException provider schedule exception
	 */
	InternalReferral updateSchedule(InternalReferral internalReferral, 
			Date date, InternalReferralReason reason, 
			ProviderLevel providerLevel, ProviderAssignment providerAssignment,
			ReferralLocationDesignator locationDesignator, String notes)
	throws DuplicateEntityFoundException, DateConflictException, 
	ProviderScheduleException;
	
	/**
	 * Requests a follow up to the referral.
	 * 
	 * @param referral referral
	 * @param date date
	 * @param category category
	 * @param labsRequired whether labs are required
	 * @param asap whether follow up is to be scheduled ASAP
	 * @param providerLevel provider level
	 * @param notes notes
	 * @return follow up request
	 * @throws DuplicateEntityFoundException if the request for a follow up
	 * exists
	 * @throws FollowUpException if the referral already has a follow up
	 */
	HealthRequest requestFollowUp(InternalReferral referral, Date date,
			HealthRequestCategory category, Boolean labsRequired, boolean asap,
			ProviderLevel providerLevel, String notes)
		throws DuplicateEntityFoundException, FollowUpException;
	
	/**
	 * Schedules a new lab work with the specified properties, related to the
	 * specified internal referral.
	 * 
	 * @param internalReferral internal referral
	 * @param category lab work category
	 * @param sampleLab sample lab
	 * @param sampleDate sample date
	 * @param sampleNotes sample notes
	 * @param sampleTaken sample taken
	 * @param results lab work results
	 * @param order lab work order
	 * @param sampleRestrictions lab work sample restrictions
	 * @param schedulingNotes scheduling notes
	 * @return lab work
	 */
	LabWork scheduleLabWork(InternalReferral internalReferral, 
			LabWorkCategory category, Lab sampleLab, Date sampleDate, 
			String sampleNotes, Boolean sampleTaken, LabWorkResults results, 
			LabWorkOrder order, LabWorkSampleRestrictions sampleRestrictions, 
			String schedulingNotes)
		throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified lab work with the specified properties, related 
	 * to the specified internal referral.
	 * 
	 * @param internalReferral internal referral
	 * @param labWork lab work
	 * @param category lab work category
	 * @param sampleLab sample lab
	 * @param sampleDate sample date
	 * @param sampleNotes sample notes
	 * @param sampleTaken sample taken
	 * @param results lab work results
	 * @param order lab work order
	 * @param sampleRestrictions lab work sample restrictions
	 * @param schedulingNotes scheduling notes
	 * @return lab work
	 */
	LabWork updateLabWork(InternalReferral internalReferral, LabWork labWork, 
			LabWorkCategory category, Lab sampleLab, Date sampleDate, 
			String sampleNotes, Boolean sampleTaken, LabWorkResults results, 
			LabWorkOrder order, LabWorkSampleRestrictions sampleRestrictions, 
			String schedulingNotes)
		throws DuplicateEntityFoundException;
	/**
	 * Requests a follow up lab work appointment.
	 * 
	 * @param referral referral
	 * @param date date
	 * @param asap whether follow up lab is to be scheduled ASAP
	 * @param notes notes
	 * @return lab follow up request
	 * @throws DuplicateEntityFoundException if the request for a lab follow
	 * up exists
	 * @throws FollowUpException if the referral already has a follow up
	 */
	HealthRequest requestLabFollowUp(InternalReferral referral, Date date,
			boolean asap, String notes)
		throws DuplicateEntityFoundException, FollowUpException;
	
	/**
	 * Returns a list of lab works related to the specified internal referral.
	 * 
	 * @param internalReferral internal referral
	 * @return list of lab works
	 */
	List<LabWork> findLabWorks(InternalReferral internalReferral);
	
	/**
	 * Returns a list of all valid lab work categories.
	 * 
	 * @return valid lab work categories
	 */
	List<LabWorkCategory> findLabWorkCategories();
	
	/**
	 * Returns a list of provider assignments for the specified facility, 
	 * whose date ranges cross the specified date.
	 * 
	 * @param facility facility
	 * @param date date
	 * @return list of provider assignments
	 */
	List<ProviderAssignment> findProviders(Facility facility, Date date);
	
	/**
	 * Returns the assignment of the primary provider for the internal referral.
	 * 
	 * @param internalReferral internal referral
	 * @return assignment of primary provider for internal referral
	 */
	ProviderAssignment findPrimaryProvider(InternalReferral internalReferral);
	
	/**
	 * Returns follow up provider levels.
	 * 
	 * @return follow up provider levels
	 */
	List<ProviderLevel> findFollowUpProviderLevels();
	
	/**
	 * Removes the specified lab work.
	 * 
	 * @param labWork lab work
	 */
	void removeLabWork(LabWork labWork);
	
	/**
	 * Returns a list of valid labs.
	 * 
	 * @return list of valid labs available for selection
	 */
	List<Lab> findLabs();
}