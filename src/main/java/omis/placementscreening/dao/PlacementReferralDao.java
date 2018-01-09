package omis.placementscreening.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.placementscreening.domain.PlacementReferral;
import omis.placementscreening.domain.ProgramCategory;

/** Program referral data access object.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 13, 2014)
 * @since OMIS 3.0 */
public interface PlacementReferralDao extends
		GenericDao<PlacementReferral> {
	/** Finds placement referrals by offender and program category.
	 * @param offender offender.
	 * @param programCategory programCategory. 
	 * @return list of program referrals. */
	List<PlacementReferral> 
		findOpenPlacementReferralsByOffenderAndProgramCategory(
			Offender offender, ProgramCategory programCategory);
	
}
