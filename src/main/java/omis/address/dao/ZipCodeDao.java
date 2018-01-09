package omis.address.dao;

import java.util.List;

import omis.address.domain.ZipCode;
import omis.dao.GenericDao;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Data access object for ZIP codes.
 * 
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb 6, 2013)
 * @since OMIS 3.0
 */
public interface ZipCodeDao
		extends GenericDao<ZipCode> {

	/**
	 * Returns available zip codes in a city.
	 * 
	 * @param city city
	 * @return zip code
	 */
	List<ZipCode> findInCity(City city);

	/**
	 * Returns available zip codes in a state.
	 * 
	 * @param state state
	 * @return zip code
	 */
	List<ZipCode> findInStates(State state);

	/**
	 * Finds ZIP code.
	 * 
	 * @param city city
	 * @param value value
	 * @param extension extension
	 * @return ZIP code
	 */
	ZipCode find(City city, String value, String extension);
	
	/**
	 * Finds ZIP code with exclusions omitted.
	 * 
	 * @param city city
	 * @param value value
	 * @param extension extension
	 * @param excludedZipCodes exclusions
	 * @return ZIP code
	 */
	ZipCode findExcluding(City city, String value, String extension,
			ZipCode... excludedZipCodes);
}