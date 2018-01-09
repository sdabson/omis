package omis.security.handler;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ldap.UncategorizedLdapException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import omis.security.service.AccessAttemptLogger;

/**
 * Handles failed login attempts.
 * 
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.2 (May 12, 2016)
 * @since OMIS 3.0
 */
public class OmisUrlAuthenticationFailureHandler
		extends SimpleUrlAuthenticationFailureHandler {
	private static final String LDAP_ERROR_CAPTURE_GROUP = "code";
	private static final String DISABLED_USER_LDAP_ERROR_REGEX 
		= "\\[LDAP:.*comment:.*(data\\s(?<"
			+ LDAP_ERROR_CAPTURE_GROUP
			+ ">[\\d]{1,3})).*\\]";
	
	private static final String LDAP_USER_DISABLED = "775";
	
	private AccessAttemptLogger accessAttemptLogger;

	/** Instantiates a default URL authentication failure handler. */
	public OmisUrlAuthenticationFailureHandler() {
		// Default instantiation
	}
	
	/**
	 * Sets the service for logging access attempts.
	 * 
	 * @param accessAttemptLogger service for logging access attempts
	 */
	public void setAccessAttemptLogger(
			final AccessAttemptLogger accessAttemptLogger) {
		this.accessAttemptLogger = accessAttemptLogger;
	}
	
	/** {@inheritDoc} */
	@Override
	public void onAuthenticationFailure(final HttpServletRequest request,
			final HttpServletResponse response,
			final AuthenticationException exception)
		throws IOException, ServletException {
		Boolean disabled = false;
		
		/* As far as I can tell based on the message an 
		 * ActiveDirectoryAuthenticationException or something similar is 
		 * translated and the property the code value used to determine the 
		 * NamingException is lost. Until a better solution exists the 
		 * following uses REGEX to determine if the code for a disabled 
		 * account is returned within the authentication exception. -RJ */  
		if (exception.getCause() instanceof UncategorizedLdapException) {
			UncategorizedLdapException uldapException 
				= (UncategorizedLdapException) exception.getCause();
			if (uldapException.getCause() instanceof NamingException) {
				NamingException namingException 
					= (NamingException) uldapException.getCause();
				if (namingException.getCause() instanceof 
						javax.naming.AuthenticationException) {
					javax.naming.AuthenticationException 
						authenticationException 
						= (javax.naming.AuthenticationException) 
							namingException.getCause();
					Pattern pattern 
						= Pattern.compile(DISABLED_USER_LDAP_ERROR_REGEX);
					Matcher matcher = pattern.matcher(
							authenticationException.getExplanation());
					if (matcher.find() && LDAP_USER_DISABLED.equals(
							matcher.group(LDAP_ERROR_CAPTURE_GROUP))) {
						disabled = true;
					}
				}
			}
		}
		
		String username = (String) request.getParameter(
					UsernamePasswordAuthenticationFilter
						.SPRING_SECURITY_FORM_USERNAME_KEY);
		Date date = new Date();
		if (username != null && !"".equals(username)) {
				this.accessAttemptLogger.logFailure(
					username, date, request.getRemoteAddr(),
					request.getRemoteHost(), request.getHeader("User-Agent"));
		}

		this.accessAttemptLogger.logFailure(
				"<not specified>", date, request.getRemoteAddr(),
				request.getRemoteHost(), request.getHeader("User-Agent"));
		response.sendRedirect(String.format(
			"%s/login.html?badCredentials=true&accountDisabled=%b",
			request.getContextPath(),
			disabled));
	}
}