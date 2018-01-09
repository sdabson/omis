package omis.person.web.delegate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import omis.country.domain.Country;
import omis.demographics.domain.Sex;
import omis.region.domain.City;
import omis.region.domain.State;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

/**
 * Person fields controller delegate.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 16, 2015)
 * @since OMIS 3.0
 */
public class PersonFieldsControllerDelegate {

	/* View names. */
	
	private static final String STATE_OPTIONS_VIEW_NAME 
		= "person/includes/birthStateOptions";
	
	private static final String CITY_OPTIONS_VIEW_NAME 
		= "person/includes/birthCityOptions";
	
	private static final String COUNTRIES_OPTIONS_VIEW_NAME
		= "person/includes/birthCountryOptions";
	
	/* Model keys. */
	
	private static final String STATES_MAP_MODEL_KEY = "birthStates";
	
	private static final String CITIES_MAP_MODEL_KEY = "birthCities";
	
	private static final String SEXES_MODEL_KEY = "sexes";
	
	private static final String COUNTRIES_MAP_MODEL_KEY = "birthCountries";
	
	private static final String SUFFIXES_MODEL_KEY = "suffixes";
	
	private static final String PERSON_FIELDS_PROPERTY_NAME_MAP_KEY
		= "personFieldsPropertyName";
	
	/* Constructor. */
	
	/**
	 * Instantiates an instance of person fields controller delegate.
	 */
	public PersonFieldsControllerDelegate() {
		//Default constructor.
	}
	
	/* Delegate methods. */
	
	/**
	 * Returns country options view, with the specified list of countries,
	 * within the person fields inclusion of the specified person fields
	 * property name.
	 * 
	 * @param countries list of countries
	 * @param personFieldsPropertyName person fields property name
	 * @return model and view for country options
	 */
	public ModelAndView showCountryOptions(final List<Country> countries,
			final String personFieldsPropertyName) {
		ModelMap map = new ModelMap();
		Map<String, List<Country>> countriesMap 
			= new HashMap<String, List<Country>>();
		countriesMap.put(personFieldsPropertyName, countries);
		map.put(COUNTRIES_MAP_MODEL_KEY, countriesMap);
		map.put(PERSON_FIELDS_PROPERTY_NAME_MAP_KEY, personFieldsPropertyName);
		return new ModelAndView(COUNTRIES_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns state options view, with the specified  list of states,
	 * within the person fields inclusion of the specified person fields
	 * property name.
	 * 
	 * @param states list of states
	 * @param personFieldsPropertyName person fields property name
	 * @return state options
	 */
	public ModelAndView showStateOptions(final List<State> states,
			final String personFieldsPropertyName) {
		ModelMap map = new ModelMap();
		Map<String, List<State>> statesMap = new HashMap<String, List<State>>();
		statesMap.put(personFieldsPropertyName, states);
		map.put(STATES_MAP_MODEL_KEY, statesMap);
		map.put(PERSON_FIELDS_PROPERTY_NAME_MAP_KEY, personFieldsPropertyName);
		return new ModelAndView(STATE_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns city options view, with the specified list of cities,
	 * within the person fields inclusion of the specified person fields
	 * property name.
	 * 
	 * @param cities list of cities
	 * @param personFieldsPropertyName person fields property name
	 * @return city options
	 */
	public ModelAndView showCityOptions(final List<City> cities,
			final String personFieldsPropertyName) {
		ModelMap map = new ModelMap();
		Map<String, List<City>> citiesMap = new HashMap<String, List<City>>();
		citiesMap.put(personFieldsPropertyName,  cities);
		map.put(CITIES_MAP_MODEL_KEY, citiesMap);
		map.put(PERSON_FIELDS_PROPERTY_NAME_MAP_KEY, personFieldsPropertyName);
		return new ModelAndView(CITY_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns a {@link ModelMap}, populated with map that contain collections 
	 * of {@link Country}, {@link State}, and {@link City} types, pertinent to 
	 * the person fields with the specified person fields property name.
	 * 
	 * @param map model map
	 * @param personFieldsPropertyName person fields property name
	 * @return populated model map
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public ModelMap prepareEditPersonFields(final ModelMap map, 
			final List<Country> countries, final List<State> states, 
			final List<City> cities, final String personFieldsPropertyName) {
		if (!map.containsKey(SEXES_MODEL_KEY)) {
			map.addAttribute(SEXES_MODEL_KEY, Sex.values());
		}
		if (countries != null && countries.size() > 0) {
			final Map<String, List<Country>> countriesMap;
			if (map.get(COUNTRIES_MAP_MODEL_KEY) instanceof HashMap<?,?>) {
				countriesMap =
						(HashMap<String, List<Country>>) 
							map.get(COUNTRIES_MAP_MODEL_KEY);
			} else {
				countriesMap = new HashMap<String, List<Country>>();
			}
			countriesMap.put(personFieldsPropertyName, countries);
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
			statesMap.put(personFieldsPropertyName, states);
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
			citiesMap.put(personFieldsPropertyName,  cities);
			map.put(CITIES_MAP_MODEL_KEY, citiesMap);
		}
		return map;
	}
	
	/**
	 * Returns a {@link ModelMap}, populated with map that contain collections 
	 * of {@link Country}, {@link State}, and {@link City} types, pertinent to 
	 * the person fields with the specified person fields property name.
	 * 
	 * <p>
	 * If multiple person fields are prepared using this method, non conditional
	 * closed text value options such as {@code sex} and {@code suffix} are
	 * set by the first call to the method. Subsequent calls will not override
	 * the already supplied values.
	 * 
	 * @param map model map
	 * @param suffixes list of suffixes
	 * @param countries list of countries
	 * @param states list of states
	 * @param cities list of cities
	 * @param personFieldsPropertyName property name of the person fields to be
	 * prepared
	 * @return populated model map
	 */
	@SuppressWarnings("unchecked")
	public ModelMap prepareEditPersonFields(final ModelMap map,
			final List<String> suffixes, final List<Country> countries,
			final List<State> states, final List<City> cities,
			final String personFieldsPropertyName) {
		if (!map.containsKey(SEXES_MODEL_KEY)) {
			map.addAttribute(SEXES_MODEL_KEY, Sex.values());
		}
		if (!map.containsKey(SUFFIXES_MODEL_KEY)) {
			map.addAttribute(SUFFIXES_MODEL_KEY, suffixes);
		}
		if (countries != null && countries.size() > 0) {
			final Map<String, List<Country>> countriesMap;
			if (map.get(COUNTRIES_MAP_MODEL_KEY) instanceof HashMap<?,?>) {
				countriesMap =
						(HashMap<String, List<Country>>) 
							map.get(COUNTRIES_MAP_MODEL_KEY);
			} else {
				countriesMap = new HashMap<String, List<Country>>();
			}
			countriesMap.put(personFieldsPropertyName, countries);
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
			statesMap.put(personFieldsPropertyName, states);
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
			citiesMap.put(personFieldsPropertyName,  cities);
			map.put(CITIES_MAP_MODEL_KEY, citiesMap);
		}
		return map;
	}
}