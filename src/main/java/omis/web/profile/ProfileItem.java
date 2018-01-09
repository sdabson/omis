package omis.web.profile;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;

/**
 * Profile item.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public interface ProfileItem
		extends Serializable, Comparable<ProfileItem> {

	/**
	 * Returns the single required authorization.
	 * 
	 * @return single required authorization
	 * @throws UnsupportedOperationException if single required authorizations
	 * are not supported
	 */
	String getRequiredAuthorization();
	
	/**
	 * Returns required authorizations.
	 * 
	 * @return required authorizations
	 */
	Set<String> getRequiredAuthorizations();
	
	/**
	 * Return the include page.
	 * 
	 * @return include page
	 */
	String getIncludePage();
	
	/**
	 * Builds the profile item.
	 * 
	 * @param modelMap model map
	 * @param offender offender
	 * @param userAccount user account
	 * @param date date
	 */
	void build(final Map<String, Object> modelMap, Offender offender,
			UserAccount userAccount, Date date);
	
	/** Gets name.
	 * @return name. */
	String getName();

	/**
	 * Returns whether enabled.
	 * 
	 * @return whether enabled
	 */
	Boolean getEnabled();
}