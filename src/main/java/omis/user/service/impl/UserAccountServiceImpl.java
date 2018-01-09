package omis.user.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;
import omis.person.domain.Suffix;
import omis.person.service.delegate.PersonDelegate;
import omis.person.service.delegate.SuffixDelegate;
import omis.security.domain.AccessAttempt;
import omis.security.service.delegate.AccessAttemptDelegate;
import omis.user.domain.UserAccount;
import omis.user.service.UserAccountService;
import omis.user.service.delegate.UserAccountDelegate;

/**
 * Implementation of service for user account operations.
 * 
 * @author Stephen Abson
 * @version 0.1.4 (April 2, 2013)
 * @since OMIS 3.0
 */
public class UserAccountServiceImpl
		implements UserAccountService {
	
	private final UserAccountDelegate userAccountDelegate;
	
	private final PersonDelegate personDelegate;
	
	private final SuffixDelegate suffixDelegate;
	
	private final AccessAttemptDelegate accessAttemptDelegate;
	
	/**
	 * Instantiates implementation of service for user accounts.
	 * 
	 * @param userAccountDelegate delegate for user accounts
	 * @param personDelegate delegate for persons
	 * @param suffixDelegate delegate for suffixes
	 * @param accessAttemptDelegate delegate for access attempts
	 */
	public UserAccountServiceImpl(
			final UserAccountDelegate userAccountDelegate,
			final PersonDelegate personDelegate,
			final SuffixDelegate suffixDelegate,
			final AccessAttemptDelegate accessAttemptDelegate) {
		this.userAccountDelegate = userAccountDelegate;
		this.personDelegate = personDelegate;
		this.suffixDelegate = suffixDelegate;
		this.accessAttemptDelegate = accessAttemptDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public UserAccount findByUsername(final String username) {
		return this.userAccountDelegate
				.findByUsername(username);
	}

	/** {@inheritDoc} */
	@Override
	public List<UserAccount> findAll() {
		return this.userAccountDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<UserAccount> findByUser(final Person user) {
		return this.userAccountDelegate.findByUser(user);
	}

	/** {@inheritDoc} */
	@Override
	public boolean userAccountExists(final String username) {
		return this.userAccountDelegate.exists(username);
	}

	/** {@inheritDoc} */
	@Override
	public UserAccount create(final Person user, final String username,
			final String password, final Date passwordExpirationDate,
			final Boolean enabled)
					throws DuplicateEntityFoundException {
		return this.userAccountDelegate.create(user, username, password,
				passwordExpirationDate, 0, enabled);
	}

	/** {@inheritDoc} */
	@Override
	public UserAccount update(final UserAccount userAccount,
			final String password, final Date passwordExpirationDate,
			final Boolean enabled) {
		int attempt;
		if (enabled) {
			attempt = 0;
		} else {
			attempt = userAccount.getAttempt();
		}
		return this.userAccountDelegate.update(userAccount, password,
				passwordExpirationDate, attempt, enabled);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final UserAccount userAccount) {
		this.userAccountDelegate.remove(userAccount);
	}

	/** {@inheritDoc} */
	@Override
	public List<Suffix> findSuffixes() {
		return this.suffixDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<AccessAttempt> findByUserAccount(final UserAccount account) {
		return this.accessAttemptDelegate.findByUsername(account.getUsername());
	}

	/** {@inheritDoc} */
	@Override
	public Person createPerson(final String lastName, final String firstName,
			final String middleName, final String suffix)
					throws DuplicateEntityFoundException {
		return this.personDelegate.create(
				lastName, firstName, middleName, suffix);
	}
}