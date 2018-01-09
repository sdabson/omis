package omis.user.service.impl;

import java.util.Date;

import omis.instance.factory.InstanceFactory;
import omis.user.dao.UsedPasswordDao;
import omis.user.dao.UserAccountDao;
import omis.user.domain.UsedPassword;
import omis.user.domain.UserAccount;
import omis.user.exception.IdenticalPasswordException;
import omis.user.exception.PasswordUsedException;
import omis.user.service.ChangePasswordService;
import omis.user.service.delegate.PasswordExpirationDateCalculatorDelegate;

/**
 * Implementation of service to change password.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 2, 2014)
 * @since OMIS 3.0
 */
public class ChangePasswordServiceImpl
		implements ChangePasswordService {

	private UserAccountDao userAccountDao;
	
	private UsedPasswordDao usedPasswordDao;
	
	private InstanceFactory<UsedPassword> usedPasswordInstanceFactory;
	
	private final PasswordExpirationDateCalculatorDelegate
	passwordExpirationDateCalculatorDelegate;

	/**
	 * Instantiates an implementation of service to change password.
	 * 
	 * @param userAccountDao data access object for user accounts
	 * @param usedPasswordDao data access object for used passwords
	 * @param usedPasswordInstanceFactory instance factory for used passwords
	 * @param passwordExpirationDateCalculatorDelegate delegate to calculate
	 * password expiration date
	 */
	public ChangePasswordServiceImpl(
			final UserAccountDao userAccountDao,
			final UsedPasswordDao usedPasswordDao,
			final InstanceFactory<UsedPassword> usedPasswordInstanceFactory,
			final PasswordExpirationDateCalculatorDelegate
			passwordExpirationDateCalculatorDelegate) {
		this.userAccountDao = userAccountDao;
		this.usedPasswordDao = usedPasswordDao;
		this.usedPasswordInstanceFactory = usedPasswordInstanceFactory;
		this.passwordExpirationDateCalculatorDelegate
		= passwordExpirationDateCalculatorDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public UserAccount change(final UserAccount userAccount,
			final String password, final boolean neverExpires,
			final Date changeDate)
					throws PasswordUsedException,
						IdenticalPasswordException {
		if (userAccount.getPassword().equals(password)) {
			throw new IdenticalPasswordException("Different password required");
		}
		if (this.usedPasswordDao
				.find(userAccount, password, changeDate) != null) {
			throw new PasswordUsedException("Unused password required");
		}
		userAccount.setPassword(password);
		if (neverExpires) {
			userAccount.setPasswordExpirationDate(null);
		} else {
			userAccount.setPasswordExpirationDate(
					this.passwordExpirationDateCalculatorDelegate
						.calculate(changeDate));
		}
		UserAccount persisted = this.userAccountDao.makePersistent(userAccount);
		if (!neverExpires) {
			
			// userAccount cannot be transient to create used password
			UsedPassword usedPassword
				= this.usedPasswordInstanceFactory.createInstance();
			usedPassword.setUserAccount(userAccount);
			usedPassword.setValue(password);
			usedPassword.setDateChanged(changeDate);
			usedPassword = this.usedPasswordDao.makePersistent(usedPassword);
		}
		return persisted;
	}

	/** {@inheritDoc} */
	@Override
	public boolean passwordUsed(
			final UserAccount userAccount, final String password,
			final Date changeDate) {
		if (this.usedPasswordDao
				.find(userAccount, password, changeDate) != null) {
			return true;
		} else {
			return false;
		}
	}
}