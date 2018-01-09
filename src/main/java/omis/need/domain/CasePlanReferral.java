package omis.need.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.organization.domain.Organization;
import omis.organization.domain.OrganizationDepartment;

/**
 * Case plan referral.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 23, 2015)
 * @since OMIS 3.0
 */
public interface CasePlanReferral extends Creatable, Updatable {

	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	Date getDate();
	
	/**
	 * Sets the date.
	 * 
	 * @param date date
	 */
	void setDate(Date date);
	
	/**
	 * Returns the comment.
	 * 
	 * @return comment
	 */
	String getComment();
	
	/**
	 * Sets the comment.
	 * 
	 * @param commentcomment
	 */
	void setComment(String comment);
	
	/**
	 * Returns the case plan objective.
	 * 
	 * @return case plan objective
	 */
	CasePlanObjective getObjective();
	
	/**
	 * Sets the case plan objective.
	 * 
	 * @param objective case plan objective
	 */
	void setObjective(CasePlanObjective objective);
	
	/**
	 * Returns the organization.
	 * 
	 * @return organization
	 */
	Organization getOrganization();
	
	/**
	 * Sets the organization.
	 * 
	 * @param organization organization
	 */
	void setOrganization(Organization organization);
	
	/**
	 * Returns the organization department.
	 * 
	 * @return organization department
	 */
	OrganizationDepartment getDepartment();
	
	/**
	 * Sets the organization department.
	 * 
	 * @param department organization department
	 */
	void setDepartment(OrganizationDepartment department);
	
	/**
	 * Returns the referral category.
	 * 
	 * @return referral category
	 */
	ReferralCategory getCategory();
	
	/**
	 * Sets the referral category.
	 * 
	 * @param category referral category
	 */
	void setCategory(ReferralCategory category);
	
	/**
	 * Returns the case plan referral response.
	 * 
	 * @return case plan referral response
	 */
	CasePlanReferralResponse getResponse();
	
	/**
	 * Sets the case plan referral response.
	 * 
	 * @param response case plan referral response
	 */
	void setResponse(CasePlanReferralResponse response);
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}