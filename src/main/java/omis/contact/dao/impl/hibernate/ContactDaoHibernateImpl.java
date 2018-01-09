package omis.contact.dao.impl.hibernate;

import omis.contact.dao.ContactDao;
import omis.contact.domain.Contact;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.person.domain.Person;

import org.hibernate.SessionFactory;

/**
 * Contact data access object.
 * 
 * @author Yidong Li
 * @version 0.1.0 (April 2, 2014)
 * @since OMIS 3.0
 */

public class ContactDaoHibernateImpl 
	extends GenericHibernateDaoImpl<Contact> implements ContactDao  {
	/* Query names. */
	private static final String FIND_CONTACT_QUERY_NAME 
		= "findContact";
	private static final String FIND_CONTACTS_BY_PERSON_QUERY_NAME 
		= "findContactsByPerson";
	private static final String FIND_CONTACT_EXCLUDING_QUERY_NAME 
	= "findContactExcluding";
	
	/* Parameter names. */
	private static final String PERSON_PARAMETER_NAME = "person";
	private static final String EXCLUDED_CONTACT_PARAMETER_NAME 
		= "excludedContact";

	/**
	 * Instantiates an instance of contact data access object with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ContactDaoHibernateImpl(final SessionFactory sessionFactory, 
		final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public Contact find(final Person person){
		Contact contact;
		contact = (Contact) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_CONTACT_QUERY_NAME)
			.setParameter(PERSON_PARAMETER_NAME, person)
			.uniqueResult();
		return contact;
	}
	
	/** {@inheritDoc} */
	@Override
	public Contact findByPerson(final Person person){
		Contact contact;
		contact = (Contact)getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_CONTACTS_BY_PERSON_QUERY_NAME)
			.setParameter(PERSON_PARAMETER_NAME, person)
			.uniqueResult();
		return contact;
	}
	
	/** {@inheritDoc} */
	@Override
	public Contact findContactExcluding(final Contact excludedContact,
			final Person person) {
		Contact contact;
		contact = (Contact) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_CONTACT_EXCLUDING_QUERY_NAME)
			.setParameter(EXCLUDED_CONTACT_PARAMETER_NAME, excludedContact)
			.setParameter(PERSON_PARAMETER_NAME, person)
			.uniqueResult();
		return contact;
	}
}


