package omis.personphoto.service;

import omis.media.domain.Photo;
import omis.person.domain.Person;

/**
 * Service for person photos.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 8, 2013)
 * @since OMIS 3.0
 */
public interface PersonPhotoService {
	
	/**
	 * Returns the photo for the specified person.
	 * 
	 * @param offender offender whose photo to return
	 * @return photo for specified offender
	 */
	Photo findByPerson(Person person);
}