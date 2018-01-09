package omis.adaaccommodation.dao;

import java.util.Date;
import java.util.List;

import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.AccommodationNote;
import omis.dao.GenericDao;

/**
 * Data access object for accommodation note.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 20, 2015)
 * @since OMIS 3.0
 */
public interface AccommodationNoteDao 
	extends GenericDao<AccommodationNote> {

	/**
	 * Returns the accommodation note.
	 * 
	 * @param text text
	 * @param date date
	 * @param accommodation accommodation
	 * @return accommodation note
	 */
	AccommodationNote find(String text, Date date, Accommodation accommodation);

	/**
	 * Returns the accommodation note excluding the one in view.
	 * 
	 * @param text text
	 * @param date date
	 * @param accommodation accommodation
	 * @param note note
	 * @return accommodation note
	 */
	AccommodationNote findExcluding(String text, Date date,
			Accommodation accommodation, AccommodationNote note);	
	
	List<AccommodationNote> findByAccommodation(Accommodation accommodation);
}