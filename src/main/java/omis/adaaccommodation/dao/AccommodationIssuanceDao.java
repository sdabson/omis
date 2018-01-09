package omis.adaaccommodation.dao;

import java.util.Date;
import java.util.List;

import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.AccommodationIssuance;
import omis.adaaccommodation.domain.Authorization;
import omis.dao.GenericDao;

/**
 * Data access object for accommodation issuance.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 20, 2015)
 * @since OMIS 3.0
 */
public interface AccommodationIssuanceDao 
	extends GenericDao<AccommodationIssuance> {

	/**
	 * Returns the accommodation issuance.
	 * 
	 * @param accommodation accommodation
	 * @param description description
	 * @param timestamp time stamp
	 * @return accommodation issuance
	 */
	AccommodationIssuance find(Accommodation accommodation, 
			Date timestamp);

	/**
	 * Returns the accommodation issuance excluding the one in view.
	 * 
	 * @param accommodationIssuance accommodation issuance
	 * @param accommodation accommodation
	 * @param description description
	 * @param timestamp time stamp
	 * @return accommodation issuance
	 */
	AccommodationIssuance findExcluding(
			AccommodationIssuance accommodationIssuance, 
			Accommodation accommodation, Date timestamp);

	/**
	 * Returns a list of accommodation issuances.
	 * 
	 * @param accommodation accommodation
	 * @return list of accommodation issuance
	 */
	List<AccommodationIssuance> findByAccommodation(
			Accommodation accommodation);

	/**
	 * Returns the authorization associated with the accommodation
	 * being passed in.
	 * 
	 * @param accommodation accommodation
	 * @return authorization
	 */
	Authorization findAssociated(Accommodation accommodation);	
}