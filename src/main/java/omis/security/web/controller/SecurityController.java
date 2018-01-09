package omis.security.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.config.util.PropertyHolder;

/**
 * Controller to handle security requests.
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.1, (May 12, 2016)
 * @since OMIS 3.0
 */
@Controller
public class SecurityController {
	
	@Autowired
	@Qualifier("hostingEnvPropertyHolder")
	private PropertyHolder hostingEnvPropertyHolder;
	
	/** Instantiates a default controller for security. */
	public SecurityController() {
		// Default instantiation
	}
	
	/**
	 * Returns the view name of the login page.
	 * @return view name of login page
	 */
	@RequestMapping(value = "login.html", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "badCredentials", required = false)
				final Boolean badCredentals,
			@RequestParam(value = "accountDisabled", required = false)
				final Boolean accountDisabled) {
		ModelAndView mav = new ModelAndView("security/login");
		mav.addObject("badCredentials", badCredentals);
		mav.addObject("accountDisabled", accountDisabled);
		mav.addObject("hostingEnvPropertyHolder", hostingEnvPropertyHolder);
		return mav;
	}
	
	/**
	 * Returns the view name of the access denied page.
	 * @return view name of access denied page
	 */
	@RequestMapping("accessDenied.html")
	public String accessDenied() {
		return "security/accessDenied";
	}
	
	/**
	 * Returns the view name of the bad credentials page.
	 * @return view name of bad credentials page
	 */
	@RequestMapping("badCredentials.html")
	public String badCredentials() {
		return "security/badCredentials";
	}
	
	/**
	 * Returns the view name of the session expired page.
	 * @return view name of session expired page
	 */
	@RequestMapping("sessionExpired.html")
	public String sessionExpired() {
		return "security/sessionExpired";
	}
	
	/**
	 * Returns view name of no user account page.
	 * @return view name of no user account page
	 */
	@RequestMapping("noUserAccount.html")
	public String noUserAccount() {
		return "security/noUserAccount";
	}
	
	
	/**
	 * Handles WebApp root ("/") by forwarding to index page.
	 * @return redirect to index page
	 */
	@RequestMapping("/")
	public String rootHandler() {
		return ("redirect:index.html");
	}
}