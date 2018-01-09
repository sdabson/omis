package omis.health.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.ExternalReferral;
import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.ExternalReferralStatusReason;
import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderExternalReferralAssociation;
import omis.health.exception.ProviderScheduleException;
import omis.health.exception.WrongExternalReferralStatusReasonException;

/**
 * Scheduler for external referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 8, 2014)
 * @since OMIS 3.0
 */
public interface ExternalReferralScheduler {

	/**
	 * Schedules an external referral.
	 * 
	 * <p>In order to schedule an external referral, it must first be
	 * authorized.
	 * 
	 * <p>If a health request is associated with the authorization, the status
	 * of the request will be set to scheduled.
	 * 
	 * @param authorization authorization
	 * @param providerAssignment assignment of provider
	 * @param date date
	 * @param time time
	 * @param notes notes
	 * @return scheduled referral
	 * @throws DuplicateEntityFoundException if the referrals exists
	 */
	ExternalReferral schedule(ExternalReferralAuthorization authorization,
			ProviderAssignment providerAssignment, Date date, Date time,
			String notes)
					throws DuplicateEntityFoundException;
	
	/**
	 * Request that the external referral be rescheduled.
	 * 
	 * @param referral referral
	 */
	void requestReschedule(ExternalReferral referral);
	
	/**
	 * Reschedules an external referral.
	 * 
	 * @param referral referral to reschedule
	 * @param statusReason reschedule status reason
	 * @param providerAssignment assignment of provider
	 * @param date date
	 * @param time time
	 * @param notes scheduling notes
	 * @return newly scheduled external referral
	 * @throws DuplicateEntityFoundException if external referral already exists
	 * @throws WrongExternalReferralStatusReasonException if a wrong status
	 * reason is provided
	 * @throws ProviderScheduleException if provider is not schedule on date
	 */
	ExternalReferral reschedule(ExternalReferral referral,
			ExternalReferralStatusReason statusReason,
			ProviderAssignment providerAssignment, Date date, Date time,
			String notes)
					throws DuplicateEntityFoundException,
					WrongExternalReferralStatusReasonException,
					ProviderScheduleException;
	
	/**
	 * Updates a scheduled external referral.
	 * 
	 * @param referral referral
	 * @param providerAssignment assignment of provider
	 * @param date date
	 * @param time time
	 * @param notes notes
	 * @return updated external referral
	 * @throws DuplicateEntityFoundException if external referral already exists
	 * @throws ProviderScheduleException if provider is not schedule on date
	 */
	ExternalReferral update(ExternalReferral referral,
					ProviderAssignment providerAssignment, Date date, Date time,
					String notes)
					throws DuplicateEntityFoundException,
						ProviderScheduleException;
	
	/**
	 * Assigns an additional provider to an external referral.
	 * 
	 * @param externalReferral external referral
	 * @param providerAssignment provider assignment
	 * @return association of additional provider to external referral
	 */
	ProviderExternalReferralAssociation assignAdditionalProvider(
			ExternalReferral externalReferral,
			ProviderAssignment providerAssignment);
	
	/**
	 * Removes the provider from the referral.
	 * 
	 * @param association association of assignment of provider to  referral
	 */
	void removeProvider(ProviderExternalReferralAssociation association);

	/**
	 * Returns status reasons for rescheduling.
	 * 
	 * @return status reasons for rescheduling
	 */
	List<ExternalReferralStatusReason> findRescheduleStatusReasons();
	
	/**
	 * Returns providers at medical facility assigned to facility.
	 * 
	 * @param facility facility
	 * @param medicalFacility medical facility
	 * @param date date
	 * @return providers at medical facility assigned to facility
	 */
	List<ProviderAssignment> findProviderAssignments(Facility facility,
			MedicalFacility medicalFacility, Date date);
	
	/**
	 * Returns provider assignment for external referral.
	 * 
	 * If no provider is assignment to the referral, returns {@code null}.
	 * 
	 * @param externalReferral external referral
	 * @return provider assignment
	 */
	ProviderAssignment findProviderAssignment(
			ExternalReferral externalReferral);
}