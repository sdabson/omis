package omis.region.web.controller;

import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.country.domain.Country;
import omis.region.domain.State;
import omis.region.service.StateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for states.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Sep 9, 2013)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/state")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class StateController {

	/* View names. */
	
	private static final String STATE_OPTIONS_VIEW_NAME
			= "region/includes/stateOptions";
	
	/* Model keys. */
	
	private static final String DEFAULT_STATE_MODEL_KEY = "defaultState";
	
	private static final String STATES_MODEL_KEY = "states";
	
	/* Services. */
	
	@Autowired
	@Qualifier("stateService")
	private StateService stateService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("statePropertyEditorFactory")
	private PropertyEditorFactory statePropertyEditorFactory;
	
	@Autowired
	@Qualifier("countryPropertyEditorFactory")
	private PropertyEditorFactory countryPropertyEditorFactory;
	
	/** Instantiates a default controller for States. */
	public StateController() {
		// Default instantiation
	}
	
	/* AJAX requests. */
	
	/**
	 * Returns a partial page of State options per country.
	 * 
	 * @param country country
	 * @param state optional selected State
	 * @return partial page of State options per country
	 */
	@RequestMapping(value = "/findByCountry.html", method = RequestMethod.GET)
	public ModelAndView findByCountry(
			@RequestParam(value = "country", required = true)
				final Country country,
			@RequestParam(value = "state", required = false)
				final State state) {
		ModelAndView mav = new ModelAndView(STATE_OPTIONS_VIEW_NAME);
		mav.addObject(DEFAULT_STATE_MODEL_KEY, state);
		List<State> states = this.stateService.findByCountry(country);
		mav.addObject(STATES_MODEL_KEY, states);
		return mav;
	}
	
	/* Init binder. */
	
	/**
	 * Initializes and binds property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(State.class,
				this.statePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Country.class,
				this.countryPropertyEditorFactory.createPropertyEditor());
	}
}