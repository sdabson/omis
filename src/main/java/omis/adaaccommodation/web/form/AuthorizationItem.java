package omis.adaaccommodation.web.form;

import java.util.Date;

import omis.adaaccommodation.domain.Authorization;
import omis.adaaccommodation.domain.AuthorizationSourceCategory;
import omis.person.domain.Person;

/**
 * Authorization for ADA relatated items.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sep 15, 2015)
 * @since OMIS 3.0
 */

public class AuthorizationItem {
	
	private Date startDate;
	
	private Date endDate;
	
	private Date authorizationDate;
	
	private Person authorizationUser;
	
	private AuthorizationSourceCategory authorizationSourceCategory;
	
	private String authorizationComments;
	
	private Authorization authorization;
	
	private Long authorizationId;
	
	/**
  	 * Instantiates a default instance of the authorization form items.
	 */
	public AuthorizationItem() {
		//Default constructor.
	}

	/**
	 * Returns the authorization start date.
	 * 
	 * @return the startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the authorization start date.
	 * 
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the authorization end date.
	 * 
	 * @return the endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the authorization end date.
	 * 
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the authorization date.
	 * 
	 * @return the authorizationDate
	 */
	public Date getAuthorizationDate() {
		return this.authorizationDate;
	}

	/**
	 * Sets the authorization date.
	 * 
	 * @param authorizationDate the authorizationDate to set
	 */
	public void setAuthorizationDate(Date authorizationDate) {
		this.authorizationDate = authorizationDate;
	}

	/**
	 * Returns the authorization user.
	 * 
	 * @return the authorizationUser
	 */
	public Person getAuthorizationUser() {
		return this.authorizationUser;
	}

	/**
	 * Sets the authorization user.
	 * 
	 * @param authorizationUser the authorizationUser to set
	 */
	public void setAuthorizationUser(Person authorizationUser) {
		this.authorizationUser = authorizationUser;
	}

	/**
	 * Returns the authorization source category.
	 * 
	 * @return the authorizationSourceCategory
	 */
	public AuthorizationSourceCategory getAuthorizationSourceCategory() {
		return this.authorizationSourceCategory;
	}

	/**
	 * Sets the authorization source category.
	 * 
	 * @param authorizationSourceCategory the authorizationSourceCategory to set
	 */
	public void setAuthorizationSourceCategory(
			AuthorizationSourceCategory authorizationSourceCategory) {
		this.authorizationSourceCategory = authorizationSourceCategory;
	}

	/**
	 * Returns the authorization comments.
	 * 
	 * @return the authorizationComments
	 */
	public String getAuthorizationComments() {
		return this.authorizationComments;
	}

	/**
	 * Sets the authorization comments.
	 * 
	 * @param authorizationComments the authorizationComments to set
	 */
	public void setAuthorizationComments(String authorizationComments) {
		this.authorizationComments = authorizationComments;
	}

	/**
	 * Returns the authorization.
	 * 
	 * @return the authorization
	 */
	public Authorization getAuthorization() {
		return this.authorization;
	}

	/**
	 * Sets the authorization.
	 * 
	 * @param authorization the authorization to set
	 */
	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

	/**
	 * Returns the authorization id.
	 * 
	 * @return the authorizationId
	 */
	public Long getAuthorizationId() {
		return this.authorizationId;
	}

	/**
	 * Sets the authorization id.
	 * 
	 * @param authorizationId the authorizationId to set
	 */
	public void setAuthorizationId(Long authorizationId) {
		this.authorizationId = authorizationId;
	}
}