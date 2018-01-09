package omis.region.web.controller;

import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.CityService;

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
 * Controller for cities.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Sep 10, 2013)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/city")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class CityController {

	/* Views. */
	
	private static final String CITY_OPTIONS_VIEW_NAME
			= "region/includes/cityOptions";
	
	/* Model keys. */
	
	private static final String DEFAULT_CITY_MODEL_KEY = "defaultCity";
	
	private static final String CITIES_MODEL_KEY = "cities";

	/* Services. */
	
	@Autowired
	@Qualifier("cityService")
	private CityService cityService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("statePropertyEditorFactory")
	private PropertyEditorFactory statePropertyEditorFactory;
	
	@Autowired
	@Qualifier("cityPropertyEditorFactory")
	private PropertyEditorFactory cityPropertyEditorFactory;
	
	/* Constructor. */
	
	/** Instantiates a default controller for cities. */
	public CityController() {
		// Default instantiation
	}
	
	/* AJAX requests. */
	
	/**
	 * Displays a partial page of city options by State.
	 * 
	 * @param state State the cities of which to display
	 * @param city optional selected city
	 * @return partial page of city options by State
	 */
	@RequestMapping(value = "/findByState.html", method = RequestMethod.GET)
	public ModelAndView findByState(
			@RequestParam(value = "state", required = true)
				final State state,
			@RequestParam(value = "city", required = false)
				final City city) {
		ModelAndView mav = new ModelAndView(CITY_OPTIONS_VIEW_NAME);
		mav.addObject(DEFAULT_CITY_MODEL_KEY, city);
		List<City> cities = this.cityService.findByState(state);
		mav.addObject(CITIES_MODEL_KEY, cities);
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
		binder.registerCustomEditor(City.class,
				this.cityPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(State.class,
				this.statePropertyEditorFactory.createPropertyEditor());
	}
}