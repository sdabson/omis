package omis.adaaccommodation.dao;

import java.util.Date;

import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.Authorization;
import omis.adaaccommodation.domain.AuthorizationSourceCategory;
import omis.dao.GenericDao;
import omis.datatype.DateRange;
import omis.user.domain.UserAccount;

/**
 * Data access object for authorization.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 20, 2015)
 * @since OMIS 3.0
 */
public interface AuthorizationDao 
	extends GenericDao<Authorization> {

	/**
	 * Gets the authorization.
	 * 
	 * @param accommodation accommodation
	 * @param authorizationDate authorization date
	 * @param authorizationUser authorization user
	 * @param authorizationSourceCategory authorization source category
	 * @param endDate end date
	 * @return authorization
	 */
	Authorization find(Accommodation accommodation, Date authorizationDate,
			UserAccount authorizationUser,
			AuthorizationSourceCategory authorizationSourceCategory,
			DateRange dateRange);
	
	/**
	 * Gets the authorization excluding the one in view.
	 * 
	 * @param authorization authorization
	 * @param accommodation accommodation
	 * @param authorizationDate authorization date
	 * @param authorizationUser authorization user
	 * @param authorizationSourceCategory authorization source category
	 * @param startDate start date
	 * @param endDate end date
	 * @return authorization excluding the one in view
	 */
	Authorization findExcluding(Authorization authorization,
			Accommodation accommodation, Date authorizationDate,
			UserAccount authorizationUser,
			AuthorizationSourceCategory authorizationSourceCategory,
			DateRange dateRange);
	
	/**
	 * List the authorizations by accommodation.
	 * 
	 * @param accommodation accommodation
	 * @return list of authorizations 
	 */
	Authorization findByAccommodation(Accommodation accommodation);
}