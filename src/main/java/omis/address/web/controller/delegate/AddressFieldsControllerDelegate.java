package omis.address.web.controller.delegate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import omis.address.domain.AddressUnitDesignator;
import omis.address.domain.StreetSuffix;
import omis.address.domain.ZipCode;
import omis.address.web.form.AddressFields;
import omis.country.domain.Country;
import omis.region.domain.City;
import omis.region.domain.State;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

/**
 * Address controller delegate.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 6, 2015)
 * @since OMIS 3.0
 */
public class AddressFieldsControllerDelegate {

	/* View names. */
	
	private static final String STATE_OPTIONS_VIEW_NAME 
		= "address/includes/addressFieldsStateOptions";
	
	private static final String CITY_OPTIONS_VIEW_NAME 
		= "address/includes/addressFieldsCityOptions";
	
	private static final String ZIP_CODE_OPTIONS_VIEW_NAME
		= "address/includes/addressFieldsZipCodeOptions";
	
	/* Model keys. */
	
	private static final String COUNTRIES_MAP_MODEL_KEY = "countries";
	
	private static final String STATES_MAP_MODEL_KEY = "states";
	
	private static final String CITIES_MAP_MODEL_KEY = "cities";
	
	private static final String ZIP_CODES_MAP_MODEL_KEY = "zipCodes";
	
	private static final String ADDRESS_UNIT_DESIGNATORS_MODEL_KEY 
		= "addressUnitDesignators";
	
	private static final String STREET_SUFFIX_MODEL_KEY = "streetSuffixes";
	
	private static final String ADDRESS_FIELDS_PROPERTY_NAME 
		= "addressFieldsPropertyName";
	
	/**
	 * Instantiates an instance of address controller delegate.
	 */
	public AddressFieldsControllerDelegate() {
		//Default constructor.
	}
	
	/* Delegate methods. */
	
	/**
	 * Returns state options view, with the specified list of states.
	 * 
	 * @param states list of states
	 * @param addressFieldsPropertyName address fields property name
	 * @return state options
	 */
	public ModelAndView showStateOptions(final List<State> states,
			final String addressFieldsPropertyName) {
		ModelMap map = new ModelMap();
		Map<String, List<State>> statesMap = new HashMap<String, List<State>>();
		statesMap.put(addressFieldsPropertyName, states);
		map.put(STATES_MAP_MODEL_KEY, statesMap);
		map.put(ADDRESS_FIELDS_PROPERTY_NAME, addressFieldsPropertyName);
		return new ModelAndView(STATE_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns city options view, with the specified list of cities.
	 * 
	 * @param cities list of cities
	 * @param addressFieldsPropertyName address fields property name
	 * @return city options
	 */
	public ModelAndView showCityOptions(final List<City> cities,
			final String addressFieldsPropertyName) {
		ModelMap map = new ModelMap();
		Map<String, List<City>> citiesMap = new HashMap<String, List<City>>();
		citiesMap.put(addressFieldsPropertyName,  cities);
		map.put(CITIES_MAP_MODEL_KEY, citiesMap);
		map.put(ADDRESS_FIELDS_PROPERTY_NAME, addressFieldsPropertyName);
		return new ModelAndView(CITY_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns zip code options view, with the specified list of zip codes,
	 * within the address fields inclusion of the specified address fields
	 * property name.
	 *  
	 * @param zipCodes zip codes
	 * @param addressFieldsPropertyName address fields property name
	 * @return zip code options
	 */
	public ModelAndView showZipCodeOptions(final List<ZipCode> zipCodes,
			final String addressFieldsPropertyName) {
		ModelMap map = new ModelMap();
		Map<String, List<ZipCode>> zipCodesMap 
			= new HashMap<String, List<ZipCode>>();
		zipCodesMap.put(addressFieldsPropertyName, zipCodes);
		map.put(ZIP_CODES_MAP_MODEL_KEY, zipCodesMap);
		map.put(ADDRESS_FIELDS_PROPERTY_NAME, addressFieldsPropertyName);
		return new ModelAndView(ZIP_CODE_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns a {@link ModelMap}, populated with {@link Country}, 
	 * {@link State}, {@link City}, along with Unit designators and Street
	 * Suffixes lists.
	 * 
	 * <p>
	 * This method has been deprecated in favor of of a new
	 * prepareEditAddressFields that reflects design without unit designators
	 * and street suffixes.
	 * 
	 * @param map model map
	 * @param countries list of countries
	 * @param states list of states
	 * @param cities list of cities
	 * @param zipCodes list of zip codes
	 * @param addressUnitDesignators list of address unit designators
	 * @param streetSuffixes list of street suffixes
	 * @param addressFieldsPropertyName address fields property name
	 * @return populated model map
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public ModelMap prepareEditAddressFields(final ModelMap map, 
			final List<Country> countries, final List<State> states,
			final List<City> cities, final List<ZipCode> zipCodes,
			final List<AddressUnitDesignator> addressUnitDesignators,
			final List<StreetSuffix> streetSuffixes,
			final String addressFieldsPropertyName) {
		if (!map.containsKey(ADDRESS_UNIT_DESIGNATORS_MODEL_KEY)) {
			map.addAttribute(ADDRESS_UNIT_DESIGNATORS_MODEL_KEY,
					addressUnitDesignators);
		}
		if (!map.containsKey(STREET_SUFFIX_MODEL_KEY)) {
			map.addAttribute(STREET_SUFFIX_MODEL_KEY, streetSuffixes);
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
			countriesMap.put(addressFieldsPropertyName, countries);
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
			statesMap.put(addressFieldsPropertyName, states);
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
			citiesMap.put(addressFieldsPropertyName,  cities);
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
			zipCodesMap.put(addressFieldsPropertyName, zipCodes);
			map.put(ZIP_CODES_MAP_MODEL_KEY, zipCodesMap);
		}
		return map;
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
	 * @param addressUnitDesignators list of address unit designators
	 * @param streetSuffixes list of street suffixes
	 * @param addressFieldsPropertyName address fields property name
	 * @return populated model map
	 */
	@SuppressWarnings("unchecked")
	public ModelMap prepareEditAddressFields(final ModelMap map, 
			final List<Country> countries, final List<State> states,
			final List<City> cities, final List<ZipCode> zipCodes,
			final String addressFieldsPropertyName) {
		if (countries != null && countries.size() > 0) {
			final Map<String, List<Country>> countriesMap;
			if (map.get(COUNTRIES_MAP_MODEL_KEY) instanceof HashMap<?,?>) {
				countriesMap =
						(HashMap<String, List<Country>>) 
							map.get(COUNTRIES_MAP_MODEL_KEY);
			} else {
				countriesMap = new HashMap<String, List<Country>>();
			}
			countriesMap.put(addressFieldsPropertyName, countries);
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
			statesMap.put(addressFieldsPropertyName, states);
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
			citiesMap.put(addressFieldsPropertyName,  cities);
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
			zipCodesMap.put(addressFieldsPropertyName, zipCodes);
			map.put(ZIP_CODES_MAP_MODEL_KEY, zipCodesMap);
		}
		return map;
	}
	
	
	/**
	 * Populates the specified address fields with the specified parameters.
	 * 
	 * @param addressFields address fields
	 * @param value value
	 * @param country country
	 * @param state state
	 * @param city city
	 * @param zipCode zip code
	 * @param newCity new city
	 * @param cityName city name
	 * @param newZipCode new zip code
	 * @param zipCodeValue zip code value
	 * @param zipCodeExtension zip code extension
	 * @return populated address fields
	 */
	public AddressFields populateAddressFields(
			final AddressFields addressFields, final String value,
			final Country country, final State state,
			final City city, final ZipCode zipCode, final Boolean newCity, 
			final String cityName, final Boolean newZipCode, 
			final String zipCodeValue, final String zipCodeExtension) {
		addressFields.setValue(value);
		addressFields.setCountry(country);
		addressFields.setState(state);
		addressFields.setCity(city);
		addressFields.setZipCode(zipCode);
		addressFields.setNewCity(newCity);
		addressFields.setCityName(cityName);
		addressFields.setNewZipCode(newZipCode);
		addressFields.setZipCodeValue(zipCodeValue);
		addressFields.setZipCodeExtension(zipCodeExtension);
		return addressFields;
	}
}