package omis.adaaccommodation.service;

import java.util.Date;
import java.util.List;

import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.AccommodationCategory;
import omis.adaaccommodation.domain.AccommodationNote;
import omis.adaaccommodation.domain.Disability;
import omis.exception.DuplicateEntityFoundException;

/**
 * Service for an ADA accommodation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 17, 2015)
 * @since OMIS 3.0
 */
public interface AccommodationService {
	
	/**
	 * Creates a new ADA accommodation.
	 * 
	 * @param disability disability
	 * @param accommodationDescription accommodation description
	 * @param accommodationCategory accommodation category
	 * @return a new accommodation
	 * @throws DuplicateEntityFoundException
	 */
	Accommodation create(Disability disability, String accommodationDescription,
			AccommodationCategory accommodationCategory) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates and ADA accommodation.
	 * 
	 * @param accommodation accommodation
	 * @param accommodationDescription accommodation description
	 * @param accommodationCategory accommodation category
	 * @return updated accommodation
	 * @throws DuplicateEntityFoundException
	 */
	Accommodation update(Accommodation accommodation, 
			String accommodationDescription, 
			AccommodationCategory accommodationCategory) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes an accommodation.
	 * 
	 * @param accommodation accommodation
	 */
	void remove(Accommodation accommodation);
	
	/**
	 * Adds an accommodation note.
	 * 
	 * @param accommodation accommodation
	 * @param date date
	 * @param text text 
	 * @return add accommodation note
	 */
	AccommodationNote addAccommodationNote(Accommodation accommodation, 
			Date date, String text);
	
	/**
	 * Updates an accommodation note.
	 * 
	 * @param accommodationNote accommodation note
	 * @param date date
	 * @param text text 
	 * @return an accommodation note
	 */
	AccommodationNote updateAccommmodationNote(
			AccommodationNote accommodationNote, Date date, String text);
	
	/**
	 * Removes an accommodation note.
	 * 
	 * @param accommodationNote accommodation note
	 */
	void removeAccommodationNote(AccommodationNote accommodationNote);
	
	/**
	 * Returns a list of all the accommodation categories.
	 * 
	 * @return a list of accommodation categories
	 */
	List<AccommodationCategory> findAllAccommodationCategories();
	
	/**
	 * Returns a list of all the accommodation notes 
	 * for specified accommodation.
	 * 
	 * @param accommodation accommodation
	 * @return a list of accommodation notes
	 */
	List<AccommodationNote> findAccommodationNotesByAccommodation(
			Accommodation accommodation);
}