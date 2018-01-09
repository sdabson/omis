package omis.userpreference.service.impl;

import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;
import omis.userpreference.dao.UserPreferenceDao;
import omis.userpreference.domain.ColorValue;
import omis.userpreference.domain.UserPreference;
import omis.userpreference.service.UserPreferenceService;
import omis.userpreference.service.delegate.UserPreferenceDelegate;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * User preference service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 20, 2015)
 * @since OMIS 3.0
 */
public class UserPreferenceServiceImpl implements UserPreferenceService {

	/* Data access objects. */
	
	private final UserPreferenceDao userPreferenceDao;
	
	private final UserAccountDelegate userAccountDelegate;
	
	/* Delegates. */
	
	private final UserPreferenceDelegate userPreferenceDelegate;
	
	/**
	 * Instantiates an instance of user preference service with the specified
	 * data access object.
	 * 
	 * @param userPreferenceDao user preference data access object
	 * @param userAccountDelegate user account delegate
	 * @param userPreferenceDelegate user preference delegate
	 * @param userPreferenceInstanceFactory user preference instance factory
	 */
	public UserPreferenceServiceImpl(
			final UserPreferenceDao userPreferenceDao,
			final UserAccountDelegate userAccountDelegate,
			final UserPreferenceDelegate userPreferenceDelegate) {
		this.userPreferenceDao = userPreferenceDao;
		this.userAccountDelegate = userAccountDelegate;
		this.userPreferenceDelegate = userPreferenceDelegate;
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public UserPreference create(final Short foregroundHue,
			final Short foregroundSaturation, final Short backgroundHue,
			final Short backgroundSaturation,
			final Short accentHue, final Short accentSaturation,
			final Boolean whiteBackground, final UserAccount userAccount) {
		return this.userPreferenceDelegate.create(
				new ColorValue(foregroundHue, foregroundSaturation),
				new ColorValue(backgroundHue, backgroundSaturation),
				new ColorValue(accentHue, accentSaturation),
				whiteBackground, userAccount);
	}

	/** {@inheritDoc} */
	@Override
	public UserPreference update(final UserPreference userPreference,
			final Short foregroundHue, final Short foregroundSaturation,
			final Short backgroundHue, final Short backgroundSaturation,
			final Short accentHue, final Short accentSaturation,
			final Boolean whiteBackground) {
		return this.userPreferenceDelegate.update(userPreference,
				new ColorValue(foregroundHue, foregroundSaturation),
				new ColorValue(backgroundHue, backgroundSaturation),
				new ColorValue(accentHue, accentSaturation),
				whiteBackground);
	}
	
	/** {@inheritDoc} */
	@Override
	public UserPreference findByUserAccount() {
		return this.userPreferenceDao.findByAccount(
				this.userAccountDelegate.findByUsername(
						SecurityContextHolder.getContext().getAuthentication()
					.getName()));
	}
}