package omis.adaaccommodation.service;

import java.util.Date;
import java.util.List;

import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.AccommodationIssuance;
import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;

/**
 * Service for ADA accommodation issuance.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sept 3, 2015)
 * @since OMIS 3.0
 */
public interface AccommodationIssuanceService {

	/**
	 * Create a new accommodation issuance.
	 * 
	 * @param accommodation accommodation
	 * @param timestamp time stamp
	 * @param text text
	 * @return a new accommodation issuance
	 * @throws DuplicateEntityFoundException
	 */
	AccommodationIssuance create(Accommodation accommodation, Date timestamp, 
			Person issuer, String text) throws DuplicateEntityFoundException;
	
	/**
	 * Update an accommodation issuance.
	 * 
	 * @param accommodationIssuance accommodation issuance
	 * @param timestamp time stamp
	 * @param text text
	 * @return an updated accommodation issuance
	 * @throws DuplicateEntityFoundException
	 */
	AccommodationIssuance update(AccommodationIssuance accommodationIssuance, 
			Date timestamp, String text) throws DuplicateEntityFoundException;
	
	/**
	 * Remove an accommodation issuance.
	 * 
	 * @param accommodationIssuance accommodation issuance
	 */
	void remove(AccommodationIssuance accommodationIssuance);
	
	/**
	 * Returns a list of all accommodation issuances by accommodation.
	 * 
	 * @param accommodation accommodation
	 * @return list of accommodation issuances
	 */
	List<AccommodationIssuance> findByAccommodation(
			Accommodation accommodation);
}