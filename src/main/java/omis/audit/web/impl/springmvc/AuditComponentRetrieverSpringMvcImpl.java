package omis.audit.web.impl.springmvc;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Spring MVC implementation of audit component retriever.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 12, 2014)
 * @since OMIS 3.0
 */
public class AuditComponentRetrieverSpringMvcImpl
		implements AuditComponentRetriever {
	
	private static final String USER_ACCOUNT_MODEL_KEY
		= "AuditComponentRetrieverSpringMvcImpl#auditUserAccount";

	private static final String DATE_MODEL_KEY
		= "AuditComponentRetrieverSpringMvcImpl#auditDate";
	
	private final UserAccountDelegate userAccountDelegate;
	
	/**
	 * Instantiates a Spring MVC implementation of factory for audit signatures.
	 * 
	 * @param userAccountDelegate delegate to look up user
	 * accounts by username
	 */
	public AuditComponentRetrieverSpringMvcImpl(
			final UserAccountDelegate userAccountDelegate) {
		this.userAccountDelegate
			= userAccountDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public UserAccount retrieveUserAccount() {
		UserAccount userAccount = (UserAccount) RequestContextHolder
				.getRequestAttributes()
					.getAttribute(USER_ACCOUNT_MODEL_KEY,
						RequestAttributes.SCOPE_REQUEST);
		if (userAccount == null) {
			String username = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			userAccount = 
					this.userAccountDelegate
					.findByUsername(username);
			RequestContextHolder.getRequestAttributes()
				.setAttribute(USER_ACCOUNT_MODEL_KEY, userAccount,
						RequestAttributes.SCOPE_REQUEST);
		}
		return userAccount;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date retrieveDate() {
		Date date = (Date) RequestContextHolder
				.getRequestAttributes()
				.getAttribute(DATE_MODEL_KEY,
					RequestAttributes.SCOPE_REQUEST);
		if (date == null) {
			date = new Date();
			RequestContextHolder.getRequestAttributes()
				.setAttribute(DATE_MODEL_KEY, date,
					RequestAttributes.SCOPE_REQUEST);
		}
		return date;
	}
}