package omis.placementscreening.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.demographics.domain.Sex;
import omis.placementscreening.domain.ProgramCategory;
import omis.placementscreening.domain.ReferralScreeningCenter;

/** Data access object for referral screening facility.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 17, 2014)
 * @since OMIS 3.0 */
public interface ReferralScreeningCenterDao extends
		GenericDao<ReferralScreeningCenter> {
	/** Finds referral screening center by program.
	 * @param programCategory program category.
	 * @param sex sex.
	 * @return list of referral screening centers. */
	List<ReferralScreeningCenter> findByProgramCategory(
			ProgramCategory programCategory, Sex sex);
	
	/** Orders referral screening center by program, sex, facility.
	 * @param referralScreeningCenter referral screening facility.
	 * @param sex sex.
	 * @return list of referral screening facilities. */
	List<ReferralScreeningCenter> orderReferralScreeningCenters(
			ReferralScreeningCenter referralScreeningCenter,
			Sex sex);
}
