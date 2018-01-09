package omis.need.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.need.domain.CasePlanObjective;
import omis.need.domain.CasePlanReferral;
import omis.need.domain.CasePlanReferralResponse;
import omis.need.domain.NeedDomain;
import omis.need.domain.ObjectivePriority;
import omis.need.domain.ObjectiveSource;
import omis.need.domain.ReferralCategory;
import omis.offender.domain.Offender;
import omis.organization.domain.Organization;
import omis.organization.domain.OrganizationDepartment;
import omis.person.domain.Person;

/**
 * Case plan objective service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 23, 2015)
 * @since OMIS 3.0
 */
public interface CasePlanObjectiveService {

	/**
	 * Creates a case plan objective for the specified offender.
	 * 
	 * @param offender offender
	 * @param identifcationDate identification date
	 * @param name name
	 * @param comment comment
	 * @param source source
	 * @param priority priority
	 * @param domain need domain
	 * @param offenderAgrees offender agrees
	 * @param staffMember staff member
	 * @return newly created case plan objective
	 * @throws DuplicateEntityFoundException thrown when a duplicate case plan 
	 * objective is found
	 */
	CasePlanObjective create(Offender offender, Date identifcationDate, 
			String name, String comment, ObjectiveSource source, 
			ObjectivePriority priority, NeedDomain domain,
			Boolean offenderAgrees, Person staffMember) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified objective.
	 * 
	 * @param objective case plan objective
	 * @param identificationDate identification date
	 * @param name name
	 * @param comment comment
	 * @param source objective source
	 * @param priority objective priority
	 * @param domain need domain
	 * @param offenderAgrees offender agrees
	 * @param staffMember staff member
	 * @return updated create case plan objective
	 * @throws DuplicateEntityFoundException thrown when a duplicate case 
	 * plan objective is found
	 */
	CasePlanObjective update(CasePlanObjective objective, 
			Date identificationDate, String name, String comment, 
			ObjectiveSource source, ObjectivePriority priority,
			NeedDomain domain, Boolean offenderAgrees,
			Person staffMember) throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified case plan objective.
	 * 
	 * @param objective case plan objective
	 */
	void remove(CasePlanObjective objective);
	
	/**
	 * Creates a case plan referral.
	 * 
	 * @param objective case plan objective
	 * @param date date
	 * @param comment comment
	 * @param source objective source
	 * @param priority objective priority
	 * @param domain need domain
	 * @param organization organization
	 * @param department organization department
	 * @return newly created case plan referral
	 * @throws DuplicateEntityFoundException thrown when a duplicate case plan 
	 * referral is found
	 */
	CasePlanReferral createReferral(CasePlanObjective objective, Date date,
			String comment, ObjectiveSource source, ObjectivePriority priority,
			NeedDomain domain,Organization organization, 
			OrganizationDepartment department,ReferralCategory category, 
			CasePlanReferralResponse response) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified case plan referral.
	 * 
	 * @param referral case plan referral
	 * @param objective case plan objective
	 * @param date date
	 * @param comment comment
	 * @param source objective source
	 * @param priority objective priority
	 * @param domain need domain
	 * @return updated case plan referral
	 * @throws DuplicateEntityFoundException thrown when a duplicate case plan 
	 * referral is found
	 */
	CasePlanReferral updateReferral(CasePlanReferral referral, 
			CasePlanObjective objective, Date date, String comment,
			ObjectiveSource source, ObjectivePriority priority,
			NeedDomain domain) throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified case plan referral.
	 * 
	 * @param referral case plan referral
	 */
	void removeReferral(CasePlanReferral referral);
	
	/**
	 * Finds case plan objectives by offender.
	 * 
	 * @param offender offender
	 * @return list of case plan objectives
	 */
	List<CasePlanObjective> findByOffender(Offender offender);
	
	/**
	 * Find case plan referrals by case plan objective.
	 * 
	 * @param objective case plan objective
	 * @return list of case plan referrals
	 */
	List<CasePlanReferral> findReferralsByObjective(
			CasePlanObjective objective);
	
	/**
	 * Returns list of objectives.
	 * 
	 * @return list of objective priorities
	 */
	List<ObjectivePriority> findPriorities();
	
	/**
	 * Returns a list of need domains.
	 * 
	 * @return list of need domains
	 */
	List<NeedDomain> findNeedDomains();
	
	/**
	 * Returns a list of referral categories.
	 * 
	 * @return list of referral categories
	 */
	List<ReferralCategory> findReferralCategories();
}