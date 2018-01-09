package omis.contact.dao.impl.hibernate;

import java.util.List;

import omis.contact.dao.OnlineAccountDao;
import omis.contact.domain.Contact;
import omis.contact.domain.OnlineAccount;
import omis.contact.domain.OnlineAccountHost;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Online account data access object.
 * 
 * @author Yidong Li
 * @version 0.1.0 (April 2, 2014)
 * @since OMIS 3.0
 */

public class OnlineAccountDaoHibernateImpl 
	extends GenericHibernateDaoImpl<OnlineAccount> implements OnlineAccountDao {
	/* Query names. */
	private static final String FIND_ONLINE_ACCOUNT_QUERY_NAME 
		= "findOnlineAccount";
	private static final String FIND_ONLINE_ACCOUNT_EXCLUDING_QUERY_NAME
		= "findOnlineAccountExcluding";
	private static final String FIND__BY_CONTACT_QUERY_NAME 
	= "findByContact";
	
	/* Parameter names. */
	private static final String CONTACT_PARAMETER_NAME = "contact";
	private static final String NAME_PARAMETER_NAME = "name";
	private static final String ONLINE_ACCOUNT_HOST = "host";
	private static final String EXCLUDED_ONLINE_ACCOUNT_PARAMETER_NAME
		= "excludedOnlineAccount";
	private static final String HOST_PARAMETER_NAME = "host";
	
	/**
	 * Instantiates an instance of online account data access object with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public OnlineAccountDaoHibernateImpl(final SessionFactory sessionFactory, 
		final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public OnlineAccount find(final Contact contact, final String name, 
		final OnlineAccountHost host){
		OnlineAccount onlineAccount;
		onlineAccount = (OnlineAccount) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_ONLINE_ACCOUNT_QUERY_NAME)
			.setParameter(CONTACT_PARAMETER_NAME, contact)
			.setParameter(NAME_PARAMETER_NAME, name)
			.setParameter(ONLINE_ACCOUNT_HOST, host)
			.uniqueResult();
		return onlineAccount;
	}
	
	/** {@inheritDoc} */
	@Override
	public OnlineAccount findOnlineAccountExcluding(
		final OnlineAccount excludedOnlineAccount, final String name, 
		final OnlineAccountHost host){
		Contact contact = excludedOnlineAccount.getContact();
		OnlineAccount onlineAccount;
		onlineAccount = (OnlineAccount) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_ONLINE_ACCOUNT_EXCLUDING_QUERY_NAME)
			.setParameter(EXCLUDED_ONLINE_ACCOUNT_PARAMETER_NAME, excludedOnlineAccount)
			.setParameter(NAME_PARAMETER_NAME, name)
			.setParameter(HOST_PARAMETER_NAME, host)
			.setParameter(CONTACT_PARAMETER_NAME, contact)
			.uniqueResult();
		return onlineAccount;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<OnlineAccount> findByContact(final Contact contact){
		@SuppressWarnings("unchecked")
		List<OnlineAccount> onlineAccounts = getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND__BY_CONTACT_QUERY_NAME)
			.setParameter(CONTACT_PARAMETER_NAME, contact)
			.list();
		return onlineAccounts;
	}
}



