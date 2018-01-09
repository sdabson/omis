package omis.citizenship.service;

import java.util.Date;
import java.util.List;

import omis.country.domain.Country;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;

/**
 * Service for citizenship.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 24, 2014)
 * @since OMIS 3.0
 */
public interface CitizenshipService {

	/**
	 * Sets country of citizenship for offender.
	 * 
	 * @param offender offender
	 * @param country country
	 * @param userAccount user account
	 * @param date date
	 */
	void setCountryOfCitizenship(Offender offender, Country country,
			UserAccount userAccount, Date date);
	
	/**
	 * Returns country of citizenship for offender.
	 * 
	 * @param offender offender
	 * @return country of citizenship for offender
	 */
	Country getCountryOfCitizenship(Offender offender);
	
	/**
	 * Removes country of citizenship for offender.
	 * 
	 * @param offender offender
	 */
	void removeCountryOfCitizenship(Offender offender);
	
	/**
	 * Returns countries.
	 * 
	 * @return countries
	 */
	List<Country> findCountries();
}