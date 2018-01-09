package omis.audit.test;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;

/**
 * Test implementation of audit component retriever.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 16, 2014)
 * @since OMIS 3.0
 */
public class AuditComponentRetrieverTestImpl
		implements AuditComponentRetriever {

	private static final ThreadLocal<UserAccount> userAccountThreadLocal
		= new ThreadLocal<UserAccount>();
	
	private static final ThreadLocal<Date> dateThreadLocal
		= new ThreadLocal<Date>();
	
	private final UserAccountDelegate userAccountDelegate;
	
	private final String testAuditUsername;
	
	private final long testAuditDate;
	
	/**
	 * Instantiates a test implementation of audit component retriever.
	 * 
	 * @param userAccountDelegate delegate for user accounts
	 * @param testAuditUsername username for test audit in milliseconds since
	 * 12 AM Jan 1, 1970
	 */
	public AuditComponentRetrieverTestImpl(
			final UserAccountDelegate
			userAccountDelegate,
			final String testAuditUsername,
			final long testAuditDate) {
		this.userAccountDelegate = userAccountDelegate;
		this.testAuditUsername = testAuditUsername;
		this.testAuditDate = testAuditDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public UserAccount retrieveUserAccount() {
		UserAccount userAccount = AuditComponentRetrieverTestImpl
				.userAccountThreadLocal.get();
		if (userAccount == null) {
			userAccount = this.userAccountDelegate
					.findByUsername(testAuditUsername);
			AuditComponentRetrieverTestImpl
				.userAccountThreadLocal.set(userAccount);
		}
		return userAccount;
	}

	/** {@inheritDoc} */
	@Override
	public Date retrieveDate() {
		Date date = AuditComponentRetrieverTestImpl.dateThreadLocal.get();
		if (date == null) {
			date = new Date(testAuditDate);
			AuditComponentRetrieverTestImpl.dateThreadLocal.set(date);
		}
		return date;
	}
}