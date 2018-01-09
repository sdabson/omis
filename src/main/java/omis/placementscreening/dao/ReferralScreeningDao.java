package omis.placementscreening.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.placementscreening.domain.PlacementReferral;
import omis.placementscreening.domain.ProgramCategory;
import omis.placementscreening.domain.ReferralScreening;
import omis.placementscreening.domain.ReferralScreeningCenter;

/** Data access object for Referral screenings. 
 * @author Ryan Johns
 * @version 0.1.0 (Nov 14, 2014)
 * @since OMIS 3.0 */
public interface ReferralScreeningDao extends GenericDao<ReferralScreening> {
	/** Finds referral screenings by placement referral and facility.
	 * @param placementReferral placement referral.
	 * @param referralScreeningCenter referral screening center.
	 * @return list of referral screenings. */
	List<ReferralScreening> 
		findByPlacementReferralAndReferralScreeningCenter(
				PlacementReferral placementReferral, 
				ReferralScreeningCenter referralScreeningCenter);
	
	/** Find referral screenings by placement referral and 
	 * referral screening center excluding referral screening.
	 * @param placementReferral placement referral.
	 * @param referralScreeningCenter referral screening center.
	 * @param referralScreenings referral screenings to exclude. 
	 * @return list of referral screenings. */
	List<ReferralScreening>
		findByPlacementAndScreeningCenterExcludingReferralScreening(
			final PlacementReferral placementReferral,
			final ReferralScreeningCenter referralScreeningCenter,
			final ReferralScreening... referralScreenings);
	
	
	
	/** Find referral screenings by offender program category and date.
	 * @param offender offender.
	 * @param programCategory program category.
	 * @param date date.
	 * @return list of referral screenings. */
	List<ReferralScreening> 
		findByOffenderScreeningProgramCategoryAndDate(
			Offender offender,
			ProgramCategory programCategory, Date date);
}
