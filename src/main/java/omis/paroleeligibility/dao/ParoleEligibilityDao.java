package omis.paroleeligibility.dao;

import java.util.Date;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.paroleeligibility.domain.ParoleEligibility;

public interface ParoleEligibilityDao extends GenericDao<ParoleEligibility> {

	/**
	 * Finds a parole eligibility.
	 * 
	 * @param hearingEligibilityDate hearing eligibility date
	 * @param reviewDate review date
	 * @param appearanceCategory appearance category
	 * @return parole eligibility
	 */
	ParoleEligibility find(Offender offender, Date hearingEligibilityDate);

	/**
	 * Finds a parole eligibility, not including the specified parole 
	 * eligibility.
	 * @param paroleEligibility parole eligibility
	 * @param hearingEligibilityDate hearing eligibility date
	 * @param reviewDate review date
	 * @param appearanceCategory appearance category
	 * @return parole eligibility, not including the specified parole
	 * eligibility
	 */
	ParoleEligibility findExcluding(ParoleEligibility paroleEligibility, 
			Offender offender, Date hearingEligibilityDate);

}
