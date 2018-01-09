package omis.health.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.health.domain.ExternalReferral;
import omis.health.domain.HealthRequest;
import omis.health.domain.HealthRequestCategory;
import omis.health.domain.ProviderLevel;
import omis.health.exception.ExternalReferralAssessmentException;
import omis.health.exception.FollowUpException;

/**
 * Service for assessing external referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 8, 2014)
 * @since OMIS 3.0
 */
public interface ExternalReferralAssessmentService {

	/**
	 * Assesses an external referral.
	 * 
	 * @param externalReferral external referral
	 * @param timeKept time kept
	 * @param notes notes
	 * @param reportedDate date assessment was reported
	 * @param assessed external referral
	 * @throws ExternalReferralAssessmentException if the external referral
	 * is already assessed
	 */
	ExternalReferral assess(ExternalReferral externalReferral, Date timeKept,
			String notes, Date reportedDate)
					throws ExternalReferralAssessmentException;
	
	/**
	 * Updates the assessment of an external referral.
	 * 
	 * @param externalReferral external referral
	 * @param timeKept time kept
	 * @param notes notes
	 * @param reportedDate date assessment was reported
	 * @return updated external referral
	 */
	ExternalReferral update(ExternalReferral externalReferral, Date timeKept,
			String notes, Date reportedDate);
	
	/**
	 * Requests a follow up.
	 * 
	 * @param externalReferral external referral
	 * @param date date
	 * @param category category
	 * @param labsRequired labs required
	 * @param asap whether follow up is to be scheduled ASAP
	 * @param providerLevel provider level
	 * @param notes notes
	 * @return follow up health request
	 * @throws DuplicateEntityFoundException if the request exists
	 * @throws FollowUpException if the referral already has a follow up
	 */
	HealthRequest requestFollowUp(ExternalReferral externalReferral, Date date,
			HealthRequestCategory category, Boolean labsRequired, boolean asap,
			ProviderLevel providerLevel, String notes)
				throws DuplicateEntityFoundException, FollowUpException;
	
	/**
	 * Request a lab follow up.
	 * 
	 * @param externalReferral external referral
	 * @param date date
	 * @param asap whether follow up lab has to be scheduled ASAP
	 * @param notes notes
	 * @return follow up lab request
	 * @throws DuplicateEntityFoundException if lab request exists
	 * @throws FollowUpException if the referral already has a follow up
	 */
	HealthRequest requestLabFollowUp(ExternalReferral externalReferral,
			Date date, boolean asap, String notes)
				throws DuplicateEntityFoundException, FollowUpException;
	
	/**
	 * Returns whether the external referral is resolved.
	 * 
	 * @param externalReferral external referral
	 * @return whether external referral is resolved
	 */
	boolean isResolved(ExternalReferral externalReferral);
	
	/**
	 * Returns follow up provider levels.
	 * 
	 * @return follow up provider levels
	 */
	List<ProviderLevel> findFollowUpProviderLevels();
}