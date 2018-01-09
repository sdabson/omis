package omis.health.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.health.domain.HealthRequest;
import omis.health.domain.HealthRequestCategory;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkReferral;
import omis.health.domain.ProviderLevel;
import omis.health.domain.component.LabWorkResults;
import omis.health.exception.FollowUpException;
import omis.health.exception.LabWorkReferralAssessmentException;
import omis.health.exception.ProviderException;
import omis.location.domain.Location;

/**
 * Lab work referral assessment service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 14, 2014)
 * @since OMIS 3.0
 */
public interface LabWorkReferralAssessmentService {

	//TODO: Provide Exception?? -JNorris
	/**
	 * Assess the specified lab work referral with and assigns the specified
	 * status reason.
	 * 
	 * @param labWorkReferral lab work referral
	 * @param assessmentNotes notes about the assessment
	 * @return lab work referral
	 * @throws DuplicateEntityFoundException if an identical lab work referral
	 * exists
	 * @throws ProviderException ???
	 * @throws LabWorkReferralAssessmentException if the lab work referral has
	 * already been assessed
	 */
	LabWorkReferral assess(LabWorkReferral labWorkReferral, 
			String notes)
		throws DuplicateEntityFoundException, ProviderException, 
		LabWorkReferralAssessmentException;
	
	/**
	 * Updates the specified lab work with the specified lab work results as
	 * part of the specified lab work referral.
	 * 
	 * @param labWorkReferral lab work referral
	 * @param labWork lab work
	 * @param sampleTaken sample taken
	 * @param labWorkResults lab work results
	 * @return lab work
	 */
	LabWork updateLabWork(LabWorkReferral labWorkReferral, LabWork labWork,
			Boolean sampleTaken, LabWorkResults labWorkResults);
	
	/**
	 * Returns a list of labs for the specified location.
	 * 
	 * @param location location
	 * @return list of labs at the location
	 */
	List<Lab> findLabsAtLocations(Location location);
	
	/**
	 * Returns a list of all labs.
	 * 
	 * @return list of all labs
	 */
	List<Lab> findLabs();
	
	/**
	 * Returns a list of lab work that are associated with the specified lab
	 * work referral.
	 * 
	 * @param labWorkReferral lab work referral
	 * @return list of lab works
	 */
	List<LabWork> findLabWorks(LabWorkReferral labWorkReferral);

	/**
	 * Returns follow up provider levels.
	 * 
	 * @return provider levels
	 */
	List<ProviderLevel> findFollowUpProviderLevels();

	/**
	 * Requests a follow up lab work appointment.
	 * 
	 * @param labWorkReferral lab work referral
	 * @param date
	 * @param asap whether the follow up lab is to be scheduled ASAP
	 * @param notes notes
	 * @return lab follow up (health) request
	 * @throws DuplicateEntityFoundException if the request for a lab follow
	 * up already exists
	 * @throws FollowUpExcpetion if the referral already has a follow up
	 */
	HealthRequest requestLabFollowUp(LabWorkReferral labWorkReferral,
			Date date, boolean asap, String notes)
		throws DuplicateEntityFoundException, FollowUpException;

	/**
	 * Requests a follow up internal referral.
	 * 
	 * @param labWorkReferral lab work referral
	 * @param date date
	 * @param category category
	 * @param labsRequired whether labs are required
	 * @param asap whether the follow should be scheduled ASAP
	 * @param providerLevel provider level
	 * @param notes notes
	 * @return internal follow up (health) request
	 * @throws DuplicateEntityFoundException if the request for a follow up
	 * already exists
	 * @throws FollowUpException if the referral already has a follow up 
	 */
	HealthRequest requestInternalFollowUp(LabWorkReferral labWorkReferral,
			Date date, HealthRequestCategory category, Boolean labsRequired,
			boolean asap, ProviderLevel providerLevel, String notes)
		throws DuplicateEntityFoundException, FollowUpException;
	
	/**
	 * Removes the specified lab work, and it's association with the specified
	 * lab work referral.
	 * 
	 * @param labWorkReferral lab work referral
	 * @param labWork lab work
	 */
	void removeLabWork(LabWorkReferral labWorkReferral, LabWork labWork);
}