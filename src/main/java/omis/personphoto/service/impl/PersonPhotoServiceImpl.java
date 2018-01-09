package omis.personphoto.service.impl;

import omis.media.domain.Photo;
import omis.person.domain.Person;
import omis.personphoto.dao.PersonPhotoAssociationDao;
import omis.personphoto.service.PersonPhotoService;

/**
 * Implementation of service for person photos.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 8, 2013)
 * @since OMIS 3.0
 */
public class PersonPhotoServiceImpl
		implements PersonPhotoService {

	private final PersonPhotoAssociationDao personPhotoAssociationDao;

	/**
	 * Instantiates an implementation of service for person photos.
	 * 
	 * @param personPhotoAssociationDao data access object for associations
	 * of person to photo
	 */
	public PersonPhotoServiceImpl(
			final PersonPhotoAssociationDao personPhotoAssociationDao) {
		this.personPhotoAssociationDao = personPhotoAssociationDao;
	}

	/** {@inheritDoc} */
	@Override
	public Photo findByPerson(final Person person) {
		return this.personPhotoAssociationDao.findByPerson(person);
	}
}