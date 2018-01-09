package omis.userpreference.service;

import omis.user.domain.UserAccount;
import omis.userpreference.domain.UserPreference;

/**
 * User preference service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 20, 2015)
 * @since OMIS 3.0
 */
public interface UserPreferenceService {

	/**
	 * Create a new user preference for the specified user account.
	 * 
	 * @param foregroundHue foreground hue
	 * @param foregroundSaturation foreground saturation
	 * @param backgroundHue background hue
	 * @param backgroundSaturation background saturation
	 * @param accentHue accent hue
	 * @param accentSaturation accent saturation
	 * @param whiteBackground white background
	 * @param userAccount user account
	 * @return newly creates user preference
	 */
	UserPreference create(Short foregroundHue, Short foregroundSaturation,
			Short backgroundHue, Short backgroundSaturation, Short accentHue,
			Short accentSaturation, Boolean whiteBackground,
			UserAccount userAccount);
	
	/**
	 * Edits the specified user preference.
	 * 
	 * @param userPreference user preference
	 * @param foregroundHue foreground hue
	 * @param foregroundSaturation foreground saturation
	 * @param backgroundHue background hue
	 * @param backgroundSaturation background saturation
	 * @param accentHue accent hue
	 * @param accentSaturation accent saturation
	 * @param whiteBackground white background
	 * @return updated user preference
	 */
	UserPreference update(UserPreference userPreference, Short foregroundHue,
			Short foregroundSaturation, Short backgroundHue,
			Short backgroundSaturation, Short accentHue,
			Short accentSaturation, Boolean whiteBackground);

	/**
	 * Returns user preference by user account.
	 * 
	 * @return user preference
	 */
	UserPreference findByUserAccount();
}