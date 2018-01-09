package omis.contact.dao.impl.hibernate;

import java.util.List;

import omis.contact.dao.TelephoneNumberDao;
import omis.contact.domain.Contact;
import omis.contact.domain.TelephoneNumber;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Telephone number data access object.
 * 
 * @author Yidong Li
 * @version 0.1.0 (April 2, 2014)
 * @since OMIS 3.0
 */

public class TelephoneNumberDaoHibernateImpl 
	extends GenericHibernateDaoImpl<TelephoneNumber> 
	implements TelephoneNumberDao {
	/* Query names. */
	private static final String FIND_TELEPHONE_NUMBER_QUERY_NAME 
		= "findTelephoneNumber";
	private static final String FIND_TELEPHONE_NUMBER_EXCLUDING_QUERY_NAME
		= "findTelephoneNumberExcluding";
	private static final String FIND_TELEPHONE_NUMBERS_BY_CONTACT_QUERY_NAME
		= "findTelephoneNumbersByContact";
	
	/* Parameter names. */
	private static final String CONTACT_PARAMETER_NAME = "contact";
	private static final String TELEPHONE_NUMBER_PARAMETER_NAME = "telephoneNumber";
	private static final String EXCLUDED_TELEPHONE_NUMBER_PARAMETER_NAME =
		"excludedTelephoneNumber";
	private static final String VALUE_PARAMETER_NAME = "value";
	
	/**
	 * Instantiates an instance of telephone number data access object with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public TelephoneNumberDaoHibernateImpl(final SessionFactory sessionFactory, 
		final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public TelephoneNumber find(final Contact contact,	
		final Long telephoneNumber){
		TelephoneNumber telephoneNumberVar;
		telephoneNumberVar = (TelephoneNumber) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_TELEPHONE_NUMBER_QUERY_NAME)
			.setParameter(CONTACT_PARAMETER_NAME, contact)
			.setParameter(TELEPHONE_NUMBER_PARAMETER_NAME, telephoneNumber)
			.uniqueResult();
		return telephoneNumberVar;
	}
	
	/** {@inheritDoc} */
	@Override
	public TelephoneNumber findTelephoneNumberExcluding(
		final TelephoneNumber excludedTelephoneNumber, 
		final Contact contact, final Long value){
		TelephoneNumber telephoneNumber;
		telephoneNumber = (TelephoneNumber) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_TELEPHONE_NUMBER_EXCLUDING_QUERY_NAME)
			.setParameter(EXCLUDED_TELEPHONE_NUMBER_PARAMETER_NAME, excludedTelephoneNumber)
			.setParameter(VALUE_PARAMETER_NAME, value)
			.setParameter(CONTACT_PARAMETER_NAME, contact)
			.uniqueResult();
		return telephoneNumber;
	}
	
	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<TelephoneNumber> findByContact(final Contact contact){
		List<TelephoneNumber> telephoneNumbers 
			= (List<TelephoneNumber>) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_TELEPHONE_NUMBERS_BY_CONTACT_QUERY_NAME)
			.setParameter(CONTACT_PARAMETER_NAME, contact)
			.list();
		return telephoneNumbers;
	}
}




