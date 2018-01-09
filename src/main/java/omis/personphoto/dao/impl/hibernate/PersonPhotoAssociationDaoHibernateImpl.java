package omis.personphoto.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.media.domain.Photo;
import omis.person.domain.Person;
import omis.personphoto.dao.PersonPhotoAssociationDao;
import omis.personphoto.domain.PersonPhotoAssociation;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for person photo associations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 8, 2013)
 * @since OMIS 3.0
 */
public class PersonPhotoAssociationDaoHibernateImpl
		extends GenericHibernateDaoImpl<PersonPhotoAssociation>
		implements PersonPhotoAssociationDao {

	private static final String FIND_PHOTO_BY_PERSON_QUERY_NAME
			= "findPhotoByPerson";
	
	private static final String PERSON_PARAM_NAME = "person";

	/** Instantiates a default data access object for offender photos. */
	public PersonPhotoAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public Photo findByPerson(final Person person) {
		Photo photo = (Photo) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_PHOTO_BY_PERSON_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.uniqueResult();
		return photo;
	}
}