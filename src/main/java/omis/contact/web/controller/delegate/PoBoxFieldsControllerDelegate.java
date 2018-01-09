package omis.contact.web.controller.delegate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import omis.address.domain.ZipCode;
import omis.country.domain.Country;
import omis.region.domain.City;
import omis.region.domain.State;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

/**
 * PoBox controller delegate.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Oct. 21, 2015)
 * @since OMIS 3.0
 */
public class PoBoxFieldsControllerDelegate {
	/* View names. */
	private static final String STATE_OPTIONS_VIEW_NAME 
		= "contact/includes/poBoxFieldsStateOptions";
	private static final String CITY_OPTIONS_VIEW_NAME 
		= "contact/includes/poBoxFieldsCityOptions";
	private static final String ZIP_CODE_OPTIONS_VIEW_NAME
		= "contact/includes/poBoxFieldsZipCodeOptions";
	
	/* Model keys. */
	private static final String PO_BOX_FIELDS_PROPERTY_NAME 
		= "poBoxFieldsPropertyName";
	private static final String STATES_MAP_MODEL_KEY = "poBoxStates";
	private static final String CITIES_MAP_MODEL_KEY = "poBoxCities";
	private static final String ZIP_CODES_MAP_MODEL_KEY = "poBoxZipCodes";
	private static final String COUNTRIES_MAP_MODEL_KEY = "poBoxCountries";
	
	/**
	 * Instantiates an instance of address controller delegate.
	 */
	public PoBoxFieldsControllerDelegate() {
		//Default constructor.
	}
	
	/* Delegate methods. */
	/**
	 * Returns state options view, with the specified list of states.
	 * 
	 * @param states list of states
	 * @param poBoxFieldsPropertyName po box fields property name
	 * @return state options
	 */
	public ModelAndView showStateOptions(List<State> states,
			final String poBoxFieldsPropertyName) {
		ModelMap map = new ModelMap();
		Map<String, List<State>> statesMap = new HashMap<String, List<State>>();
		statesMap.put(poBoxFieldsPropertyName, states);
		map.put(STATES_MAP_MODEL_KEY, statesMap);
		map.put(PO_BOX_FIELDS_PROPERTY_NAME, poBoxFieldsPropertyName);
		return new ModelAndView(STATE_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns city options view, with the specified list of cities.
	 * 
	 * @param cities list of cities
	 * @param poBoxFieldsPropertyName po box fields property name
	 * @return city options
	 */
	public ModelAndView showCityOptions(final List<City> cities,
			final String poBoxFieldsPropertyName) {
		ModelMap map = new ModelMap();
		Map<String, List<City>> citiesMap = new HashMap<String, List<City>>();
		citiesMap.put(poBoxFieldsPropertyName,  cities);
		map.put(CITIES_MAP_MODEL_KEY, citiesMap);
		map.put(PO_BOX_FIELDS_PROPERTY_NAME, poBoxFieldsPropertyName);
		return new ModelAndView(CITY_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns zip code options view, with the specified list of zip codes,
	 *  
	 * @param zipCodes zip codes
	 * @param poBoxFieldsPropertyName po box fields property name
	 * @return zip code options
	 */
	public ModelAndView showZipCodeOptions(final List<ZipCode> zipCodes,
			final String poBoxFieldsPropertyName) {
		ModelMap map = new ModelMap();
		Map<String, List<ZipCode>> zipCodesMap 
			= new HashMap<String, List<ZipCode>>();
		zipCodesMap.put(poBoxFieldsPropertyName, zipCodes);
		map.put(ZIP_CODES_MAP_MODEL_KEY, zipCodesMap);
		map.put(PO_BOX_FIELDS_PROPERTY_NAME, poBoxFieldsPropertyName);
		return new ModelAndView(ZIP_CODE_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns a {@link ModelMap}, populated with {@link Country}, 
	 * {@link State}, {@link City} lists.
	 * 
	 * @param map model map
	 * @param countries list of countries
	 * @param states list of states
	 * @param cities list of cities
	 * @param zipCodes list of zip codes
	 * @param poBoxFieldsPropertyName po box fields property name
	 * @return populated model map
	 */
	@SuppressWarnings("unchecked")
	public ModelMap prepareEditPoBoxFields(final ModelMap map, 
			final List<Country> countries, final List<State> states,
			final List<City> cities, final List<ZipCode> zipCodes,
			final String poBoxFieldsPropertyName) {
		if (countries != null && countries.size() > 0) {
			final Map<String, List<Country>> countriesMap;
			if (map.get(COUNTRIES_MAP_MODEL_KEY) instanceof HashMap<?,?>) {
				countriesMap =
						(HashMap<String, List<Country>>) 
							map.get(COUNTRIES_MAP_MODEL_KEY);
			} else {
				countriesMap = new HashMap<String, List<Country>>();
			}
			countriesMap.put(poBoxFieldsPropertyName, countries);
			map.put(COUNTRIES_MAP_MODEL_KEY, countriesMap);
		}
		if (states != null && states.size() > 0) {
			final Map<String, List<State>> statesMap;
			if (map.get(STATES_MAP_MODEL_KEY) instanceof HashMap<?,?>) {
				statesMap =
						(HashMap<String, List<State>>) 
							map.get(STATES_MAP_MODEL_KEY);
			} else {
				statesMap = new HashMap<String, List<State>>();
			}
			statesMap.put(poBoxFieldsPropertyName, states);
			map.put(STATES_MAP_MODEL_KEY, statesMap);
		}
		if(cities != null && cities.size() > 0) {
			final Map<String, List<City>>  citiesMap;
			if (map.get(CITIES_MAP_MODEL_KEY) instanceof HashMap<?,?>) {
				citiesMap =
						(HashMap<String, List<City>>) 
							map.get(CITIES_MAP_MODEL_KEY);
			} else {
				citiesMap = new HashMap<String, List<City>>();
			}
			citiesMap.put(poBoxFieldsPropertyName,  cities);
			map.put(CITIES_MAP_MODEL_KEY, citiesMap);
		}
		if(zipCodes != null && zipCodes.size() > 0) {
			final Map<String, List<ZipCode>> zipCodesMap;
			if (map.get(ZIP_CODES_MAP_MODEL_KEY) instanceof HashMap<?,?>) {
				zipCodesMap = (HashMap<String, List<ZipCode>>)
						map.get(ZIP_CODES_MAP_MODEL_KEY);
			} else {
				zipCodesMap = new HashMap<String, List<ZipCode>>();
			}
			zipCodesMap.put(poBoxFieldsPropertyName, zipCodes);
			map.put(ZIP_CODES_MAP_MODEL_KEY, zipCodesMap);
		}
		return map;
	}
}