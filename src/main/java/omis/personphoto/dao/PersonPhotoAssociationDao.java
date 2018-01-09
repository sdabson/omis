package omis.personphoto.dao;

import omis.dao.GenericDao;
import omis.media.domain.Photo;
import omis.person.domain.Person;
import omis.personphoto.domain.PersonPhotoAssociation;

/**
 * Data access object for person photo associations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 8, 2013)
 * @since OMIS 3.0
 */
public interface PersonPhotoAssociationDao
		extends GenericDao<PersonPhotoAssociation> {

	/**
	 * Returns the photo for the specified person.
	 * 
	 * @param person person whose photo to return
	 * @return photo for person
	 */
	Photo findByPerson(Person person);
}