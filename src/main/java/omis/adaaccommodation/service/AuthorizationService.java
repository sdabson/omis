package omis.adaaccommodation.service;

import java.util.Date;
import java.util.List;

import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.Authorization;
import omis.adaaccommodation.domain.AuthorizationSourceCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.user.domain.UserAccount;

/**
 * Service for ADA authorization.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 17, 2015)
 * @since OMIS 3.0
 */
public interface AuthorizationService {

	/**
	 * Create a new authorization.
	 * 
	 * @param accommodation accommodation
	 * @param authorizationDate authorization date
	 * @param authorizationUser authorization user
	 * @param authorizationSourceCategory authorization source category
	 * @param comments comments
	 * @param endDate endDate
	 * @return a new authorization
	 * @throws DuplicateEntityFoundException
	 */
	Authorization create(Accommodation accommodation, Date authorizationDate, 
			UserAccount authorizationUser, 
			AuthorizationSourceCategory authorizationSourceCategory, 
			String comments, Date StartDate, Date endDate) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Update an authorization.
	 * 
	 * @param authorization authorization
	 * @param startDate start date
	 * @param authorizationUser authorization user
	 * @param authorizationSourceCategory authorization source category
	 * @param comments comments
	 * @param endDate end date
	 * @return an updated authorization
	 * @throws DuplicateEntityFoundException
	 */
	Authorization update(Authorization authorization, Date authorizationDate, 
			UserAccount authorizationUser, 
			AuthorizationSourceCategory authorizationSourceCategory, 
			String comments, Date startDate, Date endDate) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Remove an authorization.
	 * 
	 * @param authorization authorization
	 */
	void remove(Authorization authorization);
	
	/**
	 * Returns a list of all authorization source categories.
	 * 
	 * @return list of authorization source categories
	 */
	List<AuthorizationSourceCategory> findAllAuthorizationSourceCategories();
	
	/**
	 * Returns an authorization by accommodation.
	 * 
	 * @param accommodation accommodation
	 * @return list of authorizations
	 */
	Authorization
		findAuthorizationByAccommodation(Accommodation accommodation);
}