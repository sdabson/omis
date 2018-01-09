package omis.substanceuse.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.offender.domain.Offender;
import omis.substanceuse.service.SubstanceUseService;
import omis.substanceuse.web.form.SubstanceUseForm;

/**
 * Substance use controller.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 29, 2016)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/substanceUse")
@PreAuthorize("hasRole('USER')")
public class SubstanceUseController {

	/* Redirect URLs. */
	
	private static final String LIST_REDIRECT_URL 
	= "redirect:/substanceUse/list.html";
	
	/* View names. */
	
	//private static final String LIST_VIEW_NAME = "/substanceUse/list";
	private static final String EDIT_VIEW_NAME = "/substanceUse/edit";
	
	/* Model Keys. */
	
	private static final String EDIT_FORM_MODEL_KEY = "substanceUseForm";
	private static final String MEASURMENTS_MODEL_KEY = "useMeasurements";
	private static final String USE_FREQUENCIES_MODEL_KEY = "useFrequencies";
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	/* Services. */
	
	@Autowired
	@Qualifier("substanceUseService")
	private SubstanceUseService substanceUseService;
	
	/* Constructors. */
	
	/**
	 * Instantiates a default instance of 
	 */
	public SubstanceUseController() {
		//Default controller.
	}
	
	/* URL mapped methods. */
	
	/**
	 * Returns model and view to create a new substance use.
	 * 
	 * @param offender offender
	 * @return model and view for substance use creaiton
	 */
	@RequestMapping(value = "create.html", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(value = "offender",
			required = true)final Offender offender) {
		ModelMap map = new ModelMap();
		SubstanceUseForm form = new SubstanceUseForm();
		return this.prepareEditMav(form, map);
	}
	
	@RequestMapping(value = "create.html", method = RequestMethod.POST)
	public ModelAndView save(@RequestParam(value = "offender", required = true)
			final Offender offender, SubstanceUseForm form, 
			BindingResult result) {
		if(result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(OFFENDER_MODEL_KEY, offender);
		}
		//Save new substance use
		return new ModelAndView(LIST_REDIRECT_URL);
	}
	
	/* Helper methods. */
	
	/*
	 * Prepares a model and view to present the edit screen for substance use.
	 * 
	 * @param form substance use form
	 * @param map model map
	 * @return edit model and view
	 */
	private ModelAndView prepareEditMav(final SubstanceUseForm form, 
			final ModelMap map) {
		map.addAttribute(EDIT_FORM_MODEL_KEY, form);
		map.addAttribute(MEASURMENTS_MODEL_KEY,
				this.substanceUseService.findUseMeasurements());
		map.addAttribute(USE_FREQUENCIES_MODEL_KEY,
				this.substanceUseService.findUseFrequencies());
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
}