package omis.security.handler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import omis.security.service.AccessAttemptLogger;
import omis.user.domain.UserAccount;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * Handles successful login attempts.
 * 
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.2.1 (May 12, 2016)
 * @since OMIS 3.0
 */
public class OmisUrlAuthenticationSuccessHandler
		extends SimpleUrlAuthenticationSuccessHandler {

	private AccessAttemptLogger accessAttemptLogger;

	/** Instantiates a default URL authentication success handler. */
	public OmisUrlAuthenticationSuccessHandler() {
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
	public void onAuthenticationSuccess(final HttpServletRequest request,
			final HttpServletResponse response,
			final Authentication authentication) 
		throws IOException, ServletException {
		UserAccount userAccount = this.accessAttemptLogger.findByUsername(
													authentication.getName());
		
		// Only allow logins if user exists in application
		if (userAccount != null) {
			this.accessAttemptLogger.logSuccess(authentication.getName(),
					new Date(), request.getRemoteAddr(),
					request.getRemoteHost(), request.getHeader("User-Agent"));
			String multiScreenMode = request.getParameter("multiScreenMode");
			if ("false".equals(multiScreenMode)) {
				response.sendRedirect(String.format(
					"%s%s?multiScreenMode=false", request.getContextPath(),
					super.getDefaultTargetUrl()));
			}
			super.onAuthenticationSuccess(request, response, authentication);
		} else {
			request.getSession().invalidate();
			this.accessAttemptLogger.logFailure(authentication.getName(),
					new Date(), request.getRemoteAddr(),
					request.getRemoteHost(), request.getHeader("User-Agent"));
			response.sendRedirect(String.format(
					"%s/noUserAccount.html", request.getContextPath()));
		}
	}
}