package omis.web.controller;

import java.util.ArrayList;
import java.util.List;

import omis.user.domain.UserAccount;
import omis.user.service.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for managing user sessions.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 18, 2012)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/sessionManager")
@PreAuthorize("hasRole('USER')")
public class SessionManagerController {

	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Autowired
	private UserAccountService userAccountService;
	
	/** Instantiates a default controller for session management. */
	public SessionManagerController() {
		// Default instantiation
	}
	
	/**
	 * Returns a model and view to a page that lists currently logged in users.
	 * 
	 * @return model and view to a page that lists currently logged in users
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/list.html")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("sessionManager/list");
		List<Object> allPrincipals = this.sessionRegistry.getAllPrincipals();
		List<SessionInformation> allSessionsOfAllPrincipals =
				new ArrayList<SessionInformation>();
		for (Object principal : allPrincipals) {
			for (SessionInformation sessionInformation
					: this.sessionRegistry.getAllSessions(principal, true)) {
				allSessionsOfAllPrincipals.add(sessionInformation);
			}
		}
		mav.addObject("allSessionsOfAllPrincipals", allSessionsOfAllPrincipals);
		return mav;
	}
	
	/**
	 * Removes the session with the specified ID and redirect to the listing
	 * screen.
	 * 
	 * @param sessionId ID of session to expire 
	 * @return redirect to listing page
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/expire.html")
	public String expire(
			@RequestParam(value = "sessionId", required = true)
				final String sessionId) {
		SessionInformation sessionInformation =
				this.sessionRegistry.getSessionInformation(sessionId);
		if (sessionInformation == null) {
			throw new IllegalArgumentException(
					"No session exists with ID: " + sessionId);
		}
		sessionInformation.expireNow();
		return "redirect:list.html";
	}
	
	/**
	 * Removes the session with the specified ID, disables the associated
	 * account and redirects to the listing screen.
	 * 
	 * @param sessionId ID of session to expire
	 * @param username username of account to disable 
	 * @return redirect to listing page
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/expireAndDisable.html")
	public String expireAndDisable(
			@RequestParam(value = "sessionId", required = true)
				final String sessionId,
			@RequestParam(value = "username", required = true)
				final String username) {
		SessionInformation sessionInformation =
				this.sessionRegistry.getSessionInformation(sessionId);
		if (sessionInformation == null) {
			throw new IllegalArgumentException(
					"No session exists with ID: " + sessionId);
		}
		sessionInformation.expireNow();
		UserAccount account = this.userAccountService.findByUsername(username);
		if (account == null) {
			throw new IllegalArgumentException(
					"No account with username: " + username);
		}
		account.disable();
		return "redirect:list.html";
	}
}