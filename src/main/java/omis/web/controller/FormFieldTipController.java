package omis.web.controller;

import java.util.List;

import omis.web.domain.FormFieldTip;
import omis.web.service.FormFieldTipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for form field tips.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 18, 2015)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping(value = "/web")
@PreAuthorize("hasRole('USER')")
public class FormFieldTipController {
	
	/* View names. */
	
	private static final String FIND_VIEW_NAME = "web/json/formFieldTips";

	/* Model keys. */
	
	private static final String TIPS_MODEL_KEY = "formFieldTips";
	
	/* Services. */
	
	@Autowired
	@Qualifier("formFieldTipService")
	private FormFieldTipService formFieldTipService;

	/* Constructors. */
	
	/** Instantiates controller for form field tips. */
	public FormFieldTipController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Returns form field tips.
	 * 
	 * @param form form
	 * @return form field tips
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(
			value = "/findFormFieldTips.json", method = RequestMethod.GET)
	public ModelAndView findFormFieldTips(
			@RequestParam(value = "form", required = true)
				final String form) {
		List<FormFieldTip> formFieldTips = this.formFieldTipService
				.findByForm(form);
		ModelAndView mav = new ModelAndView(FIND_VIEW_NAME);
		mav.addObject(TIPS_MODEL_KEY, formFieldTips);
		return mav;
	}
}