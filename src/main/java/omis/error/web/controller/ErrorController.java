package omis.error.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for handling errors (including exceptions).
 * @author Stephen Abson
 * @version 0.1.0 (Mar 1, 2013)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/error")
public class ErrorController {
	
	/**
	 * Shows error page for throwables.
	 * @return model and view to error page for throwables
	 */
	@RequestMapping("/throwable.html")
	public ModelAndView showThrowable() {
		return new ModelAndView("error/throwable");
	}
}