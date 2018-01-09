package omis.content.web.controller;

import java.util.List;

import omis.content.RequestContent;
import omis.content.RequestContentMapping;
import omis.content.RequestContentMappingScanner;
import omis.content.RequestContentType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for automatic screen scanning.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (June 15, 2012)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping(value = "/content")
@PreAuthorize(
		"hasRole('ADMIN') or (hasRole('CONTENT_LIST') and hasRole('USER'))")
public class RequestContentMappingScannerController {
	
	@Autowired
	private RequestContentMappingScanner requestContentMappingScanner;

	/**
	 * Instantiates a default controller for request content mapping scanning.
	 */
	public RequestContentMappingScannerController() {
		// Default instantiation
	}
	
	/**
	 * Returns a model and view of a screen listing scanned screens.
	 * 
	 * <p>This equates to a listing of screen index.
	 * 
	 * @return model and view to screen index
	 */
	@RequestContentMapping(nameKey = "contentScreenName",
			descriptionKey = "contentScreenDescription",
			messageBundle = "omis.content.msgs.content",
			screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping(value = "/list.html")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("content/list");
		List<RequestContent> contents = this.requestContentMappingScanner
				.getRequestContents();
		mav.addObject("contents", contents);
		return mav;
	}
}