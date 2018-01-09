package omis.paroleeligibility.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.paroleeligibility.domain.EligibilityStatusReason;

/**
 * Eligibility status reason data access object.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public interface EligibilityStatusReasonDao 
	extends GenericDao<EligibilityStatusReason> {
	
	/**
	 * Finds eligibility status reasons.
	 * 
	 * @return Finds eligibility status reasons.
	 */
	List<EligibilityStatusReason> findEligibilityStatusReasons();

	/**
	 * Finds an eligibility status reason
	 * 
	 * @param name name of the eligibility status reason
	 * @param valid whether an eligibility status reason is valid
	 * @return eligibility status reason
	 */
	EligibilityStatusReason findEligibilityStatusReason(
			String name);

	/**
	 * Finds an eligibility status reason, not including the specified
	 * eligibility status reason.
	 * @param eligibilityStatusReason eligibility status reason
	 * @param name name of the eligibility status reason
	 * @param valid whether an eligibility status reason is valid
	 * @return eligibility status reason, not including th specified
	 * eligibility status reason
	 */
	EligibilityStatusReason findEligibilityStatusReasonExcluding(
			EligibilityStatusReason eligibilityStatusReason, String name);

}
