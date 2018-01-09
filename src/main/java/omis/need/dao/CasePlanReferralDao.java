package omis.need.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.need.domain.CasePlanObjective;
import omis.need.domain.CasePlanReferral;
import omis.organization.domain.Organization;
import omis.organization.domain.OrganizationDepartment;

/**
 * Case plan referral data access object.
 * @author Kelly Churchill
 * @version 0.1.0
 * @since OMIS 3.0
 */
public interface CasePlanReferralDao extends GenericDao<CasePlanReferral>{
	/**
	 * Returns a list of referrals for the specified objective.
	 * @param objective
	 * @return List of Case plan referral
	 */
	List<CasePlanReferral> findByObjective(CasePlanObjective objective);
	
	/**
	 * Returns case plan referral for the specified parameters.
	 * @param objective case plan objective
	 * @param date date
	 * @param organization organization
	 * @param department organization department
	 * @return case plan referral
	 */
	CasePlanReferral find(CasePlanObjective objective, Date date,
			Organization organization, OrganizationDepartment department);
	/**
	 * Returns case plan referral for specified parameters excluding the
	 * specified referral.
	 * @param referral case plan referral
	 * @param objective case plan objective
	 * @param date date
	 * @param organization organization
	 * @param department organization department
	 * @return case plan referral
	 */
	CasePlanReferral findExcluding(CasePlanReferral referral,
			CasePlanObjective objective, Date date, Organization organization,
			OrganizationDepartment department);

}
