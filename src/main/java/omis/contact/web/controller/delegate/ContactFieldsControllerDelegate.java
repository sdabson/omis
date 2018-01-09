package omis.contact.web.controller.delegate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import omis.address.domain.Address;
import omis.address.domain.AddressUnitDesignator;
import omis.address.domain.StreetSuffix;
import omis.address.domain.ZipCode;
import omis.address.web.form.AddressFields;
import omis.contact.web.form.PoBoxFields;
import omis.contact.domain.component.PoBox;
import omis.country.domain.Country;
import omis.region.domain.City;
import omis.region.domain.State;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

/**
 * Contact fields controller delegate.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (Dec 23, 2015)
 * @since OMIS 3.0
 */
public class ContactFieldsControllerDelegate {

	/* View names. */
	
	private static final String STATE_OPTIONS_VIEW_NAME 
		= "contact/includes/stateOptions";
	private static final String CITY_OPTIONS_VIEW_NAME 
		= "contact/includes/cityOptions";
	private static final String ZIP_CODE_OPTIONS_VIEW_NAME
		= "contact/includes/zipCodeOptions";
	
	/* Model keys. */
	
	private static final String FIELDS_PROPERTY_NAME 
		= "fieldsPropertyName";
	
	private static final String COUNTRIES_MAP_MODEL_KEY = "countries";
	
	private static final String STATES_MAP_MODEL_KEY = "states";
	
	private static final String CITIES_MAP_MODEL_KEY = "cities";
	
	private static final String ZIP_CODES_MAP_MODEL_KEY = "zipCodes";
	
	private static final String ADDRESS_UNIT_DESIGNATORS_MODEL_KEY 
	= "addressUnitDesignators";

	private static final String STREET_SUFFIX_MODEL_KEY = "streetSuffixes";
	
	/* Property name formats. */
	
	private static final String ADDRESS_FIELDS_PROPERTY_NAME_FORMAT
		= "%s.mailingAddressFields";
	
	private static final String PO_BOX_FIELDS_PROPERTY_NAME_FORMAT
		= "%s.poBoxFields";
	
	/* Constructors. */
	
	/**
	 * Instantiates a default instance of contact fields controller delegate.
	 */
	public ContactFieldsControllerDelegate() {
		//Default constructor.
	}
	
	/* Delegate methods. */
	
	/**
	 * Returns state options view, with the specified list of states.
	 * 
	 * @param states list of states
	 * @param contactFieldsPropertyName contact fields property name
	 * @return state options
	 */
	public ModelAndView showStateOptions(List<State> states,
			final String contactFieldsPropertyName) {
		ModelMap map = new ModelMap();
		Map<String, List<State>> statesMap = new HashMap<String, List<State>>();
		statesMap.put(contactFieldsPropertyName, states);
		map.put(STATES_MAP_MODEL_KEY, statesMap);
		map.put(FIELDS_PROPERTY_NAME, contactFieldsPropertyName);
		return new ModelAndView(STATE_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns city options view, with the specified list of cities.
	 * 
	 * @param cities list of cities
	 * @param contactFieldsPropertyName contact fields property name
	 * @return city options
	 */
	public ModelAndView showCityOptions(final List<City> cities,
			final String contactFieldsSubFieldsPropertyName) {
		ModelMap map = new ModelMap();
		Map<String, List<City>> citiesMap = new HashMap<String, List<City>>();
		citiesMap.put(contactFieldsSubFieldsPropertyName,  cities);
		map.put(CITIES_MAP_MODEL_KEY, citiesMap);
		map.put(FIELDS_PROPERTY_NAME, contactFieldsSubFieldsPropertyName);
		return new ModelAndView(CITY_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns zip code options view, with the specified list of zip codes,
	 *  
	 * @param zipCodes zip codes
	 * @param contactFieldsPropertyName contact fields property name
	 * @return zip code options
	 */
	public ModelAndView showZipCodeOptions(final List<ZipCode> zipCodes,
			final String contactFieldsPropertyName) {
		ModelMap map = new ModelMap();
		Map<String, List<ZipCode>> zipCodesMap 
			= new HashMap<String, List<ZipCode>>();
		zipCodesMap.put(contactFieldsPropertyName, zipCodes);
		map.put(ZIP_CODES_MAP_MODEL_KEY, zipCodesMap);
		map.put(FIELDS_PROPERTY_NAME, contactFieldsPropertyName);
		return new ModelAndView(ZIP_CODE_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Populates the specified address fields with values form the 
	 * specified address.
	 * 
	 * @param addressFields address fields
	 * @param address address
	 * @return populated address fields
	 */
	public AddressFields populateAddressFields(
			final AddressFields addressFields, final Address address) {
		addressFields.setValue(address.getValue());
		addressFields.setCountry(address.getZipCode().getCity().getCountry());
		addressFields.setState(address.getZipCode().getCity().getState());
		addressFields.setCity(address.getZipCode().getCity());
		addressFields.setZipCode(address.getZipCode());
		addressFields.setNewCity(false);
		addressFields.setNewZipCode(false);
		addressFields.setZipCodeExtension(address.getZipCode().getExtension());
		return addressFields;
	}
	
	/**
	 * Populates the specified Post Office Box Fields with values from the
	 * specified Post Office Box.
	 * 
	 * @param poBoxFields Post Office Box Fields
	 * @param poBox Post Office Box
	 * @return populated Post Office Box Fields
	 */
	public PoBoxFields populatePoBoxFields(final PoBoxFields poBoxFields,
			final PoBox poBox) {
		poBoxFields.setCity(poBox.getZipCode().getCity());
		poBoxFields.setCountry(poBox.getZipCode().getCity().getCountry());
		poBoxFields.setPoBoxValue(poBox.getValue());
		poBoxFields.setState(poBox.getZipCode().getCity().getState());
		poBoxFields.setZipCode(poBox.getZipCode());
		poBoxFields.setNewZipCode(false);
		return poBoxFields;
	}
	
	/**
	 * Prepares an instance of contact fields, with the specified property name,
	 * for editing.
	 * 
	 * Returns a {@link ModelMap}, populated with lists for use in options
	 * for sets of subfields within contact fields
	 * (AddressFields and PoBoxFields).
	 *  
	 * @param map model map
	 * @param addressFieldsCountries address fields countries
	 * @param addressFieldsStates address fields states
	 * @param addressFieldsCities address fields cities
	 * @param addressFieldsZipCodes address fields zip codes
	 * @param addressFieldsAddressUnitDesignators address fields address unit
	 * designators
	 * @param addressFieldsStreetSuffixes address fields street suffixes
	 * @param poBoxFieldsCountries po box fields countries
	 * @param poBoxFieldsStates po box fields state
	 * @param poBoxFieldsCities po box fields cities
	 * @param poBoxFieldsZipCodes po fields zip codes
	 * @param contactFieldsPropertyName contact fields property name
	 * @return populated model map for editing contact fields
	 */
	public ModelMap prepareEditContactFields(final ModelMap map, 
			final List<Country> addressFieldsCountries,
			final List<State> addressFieldsStates,
			final List<City> addressFieldsCities,
			final List<ZipCode> addressFieldsZipCodes,
			final List<AddressUnitDesignator>
				addressFieldsAddressUnitDesignators,
			final List<StreetSuffix> addressFieldsStreetSuffixes,
			final List<Country> poBoxFieldsCountries,
			final List<State> poBoxFieldsStates,
			final List<City> poBoxFieldsCities,
			final List<ZipCode> poBoxFieldsZipCodes,
			final String contactFieldsPropertyName) {
		this.prepareEditAddressFields(map, addressFieldsCountries,
				addressFieldsStates, addressFieldsCities, addressFieldsZipCodes,
				addressFieldsAddressUnitDesignators,
				addressFieldsStreetSuffixes,
				String.format(ADDRESS_FIELDS_PROPERTY_NAME_FORMAT, 
						contactFieldsPropertyName));
		this.prepareEditPoBoxFields(map, poBoxFieldsCountries,
				poBoxFieldsStates, poBoxFieldsCities, poBoxFieldsZipCodes,
				String.format(PO_BOX_FIELDS_PROPERTY_NAME_FORMAT,
						contactFieldsPropertyName));
		return map;
	}
	
	/* Helper methods. */
	
	/*
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
	private ModelMap prepareEditAddressFields(final ModelMap map, 
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
	
	/*
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
	private ModelMap prepareEditPoBoxFields(final ModelMap map, 
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