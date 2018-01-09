package omis.adaaccommodation.dao;

import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.AccommodationCategory;
import omis.adaaccommodation.domain.Disability;
import omis.dao.GenericDao;

/**
 * Data access object for accommodation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 20, 2015)
 * @since OMIS 3.0
 */
public interface AccommodationDao 
	extends GenericDao<Accommodation> {

	/**
	 * Returns the accommodation.
	 * 
	 * @param disability disability
	 * @param accommodationDescription accommodation description
	 * @param accommodationCategory accommodation category
	 * @return accommodation
	 */
	Accommodation find(Disability disability, String accommodationDescription,
			AccommodationCategory accommodationCategory);

	/**
	 * Returns the accommodation excluding the one in view.
	 * 
	 * @param disability disability
	 * @param accommodationDescription accommodation description
	 * @param accommodationCategory accommodation category
	 * @param accommodation
	 * @return accommodation
	 */
	Accommodation findExcluding(Disability disability,
			String accommodationDescription,
			AccommodationCategory accommodationCategory, 
			Accommodation accommodation);	
}