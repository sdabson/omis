package omis.placementscreening.service;

import java.util.Date;
import java.util.List;

import omis.demographics.domain.Sex;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.placementscreening.domain.PlacementReferral;
import omis.placementscreening.domain.ProgramCategory;
import omis.placementscreening.domain.ReferralScreening;
import omis.placementscreening.domain.ReferralScreeningCenter;
import omis.placementscreening.exception.ReferralDateCategoryConflictException;
import omis.user.domain.UserAccount;

/** Service for referring to a program related operations.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 12, 2014)
 * @since OMIS 3.0 */
public interface PlacementReferralService {
	/** Refers offender to program.
	 * @param offender offender.
	 * @param date referral date.
	 * @param facility facility.
	 * @param referringFacility referral source facility.
	 * @param referringPerson referral source person.
	 * @param programCategory program category.
	 * @return placement referral.
	 * @throws DuplicateEntityFoundException duplicate entity found. 
	 * @throws ReferralDateCategoryConflictException indicating that an existing
	 * referral with overlapping placement categories exist in the configured 
	 * date range.*/
	PlacementReferral referToPlacement(Offender offender, Date date, 
			Facility facility, Facility referringFacility, 
			Person referringPerson, ProgramCategory programCategory) 
		throws DuplicateEntityFoundException, 
		ReferralDateCategoryConflictException;
	
	/** Queues a local screening.
	 * @param screeningIndex order of screening.
	 * @param placementReferral program referral considered.
	 * @param referralScreeningCenter referral screening center.
	 * @return referral screening.
	 * @throws DuplicateEntityFoundException duplicate entity found. */
	ReferralScreening queueLocalScreening(Integer screeningIndex, 
			PlacementReferral placementReferral, 
			ReferralScreeningCenter referralScreeningCenter) 
		throws DuplicateEntityFoundException;
	
	/** Order screenings by order and facility.
	 * @param referralScreeningCenter referral screening center.
	 * @param sex sex. 
	 * @return list of referral screening centers. */
	List<ReferralScreeningCenter> queueScreeningCenters(
			ReferralScreeningCenter referralScreeningCenter, Sex sex);

	/** Update local screening.
	 * @param referralScreening referral screening.
	 * @param screeningIndex order of screening.
	 * @return referral screening. 
	 * @throws DuplicateEntityFoundException when an existing referral 
	 * screening exists for the associated placement referral. */
	ReferralScreening updateOrderOfScreening(
			ReferralScreening referralScreening,
			Integer screeningIndex) 
		throws DuplicateEntityFoundException;
	
	/** Removes local screening.
	 * @param referralScreening referral screening. */
	void dequeueLocalScreening(ReferralScreening referralScreening);
	
	/** finds all screening facilities by program category.
	 * @param programCategory program category.
	 * @param sex sex.
	 * @return list of referral screening centers. */
	List<ReferralScreeningCenter> findAllReferralScreeningCentersByProgram(
			ProgramCategory programCategory, Sex sex);
	
	/** finds user by user name.
	 * @param username user name.
	 * @return user. */
	UserAccount findUserByUsername(String username);
}
