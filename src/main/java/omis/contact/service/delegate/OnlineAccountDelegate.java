package omis.contact.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.contact.dao.OnlineAccountDao;
import omis.contact.domain.Contact;
import omis.contact.domain.OnlineAccount;
import omis.contact.domain.OnlineAccountHost;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Online account delegate.
 * 
 * @author Yidong Li
 * @version 0.1.1 (July 27, 2015)
 * @since OMIS 3.0
 */
public class OnlineAccountDelegate {

	/* Data access objects. */
	private final OnlineAccountDao onlineAccountDao;
	
	/* Instance factories. */
	private final InstanceFactory<OnlineAccount> onlineAccountInstanceFactory;

	/* Audit Component Retriever */
	private final AuditComponentRetriever auditComponentRetriever;
	
	public OnlineAccountDelegate(
		final OnlineAccountDao onlineAccountDao, 
		final InstanceFactory<OnlineAccount> onlineAccountInstanceFactory,
		final AuditComponentRetriever auditComponentRetriever) {
		this.onlineAccountDao = onlineAccountDao;
		this.onlineAccountInstanceFactory = onlineAccountInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Create online account.
	 * 
	 * @param contact contact
	 * @param name name 
	 * @param active active
	 * @param host online account host
	 * @return newly created contact	
	 */
	@Deprecated
	public OnlineAccount create(final Contact contact, final String name, 
		final Boolean active, final OnlineAccountHost host)
			throws DuplicateEntityFoundException{
		if (this.onlineAccountDao.find(contact, name, host)!=null){
			throw new DuplicateEntityFoundException("Online Account Already Exist.");
		}
		OnlineAccount onlineAccount 
			= this.onlineAccountInstanceFactory.createInstance();
		onlineAccount.setName(name);
		onlineAccount.setActive(active);
		onlineAccount.setHost(host);
		onlineAccount.setContact(contact);
		CreationSignature creationSignature = new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate());
		onlineAccount.setCreationSignature(creationSignature);
		UpdateSignature updateSignature = new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate());
		onlineAccount.setUpdateSignature(updateSignature);
		return this.onlineAccountDao.makePersistent(onlineAccount);
	}
	
	/**
	 * Create online account.
	 * 
	 * @param contact contact
	 * @param name name 
	 * @param active active
	 * @param primary primary
	 * @param host online account host
	 * @return newly created contact	
	 */
	public OnlineAccount create(final Contact contact, final String name, 
		final Boolean active, final Boolean primary, 
		final OnlineAccountHost host)
			throws DuplicateEntityFoundException{
		if (this.onlineAccountDao.find(contact, name, host)!=null){
			throw new DuplicateEntityFoundException("Online Account Already "
					+ "Exist.");
		}
		OnlineAccount onlineAccount 
			= this.onlineAccountInstanceFactory.createInstance();
		onlineAccount.setName(name);
		onlineAccount.setActive(active);
		onlineAccount.setHost(host);
		onlineAccount.setContact(contact);
		onlineAccount.setPrimary(primary);
		CreationSignature creationSignature = new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate());
		onlineAccount.setCreationSignature(creationSignature);
		UpdateSignature updateSignature = new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate());
		onlineAccount.setUpdateSignature(updateSignature);
		return this.onlineAccountDao.makePersistent(onlineAccount);
	}
	
	/**
	 * Update online account.
	 * 
	 * @param onlineAccount online account
	 * @param name name
	 * @param active active
	 * @param host online account host
	 * @return updated existing online contact	
	 */
	@Deprecated
	public OnlineAccount update(final Contact contact, 
		final OnlineAccount onlineAccount, final String name, 
		final OnlineAccountHost host)
			throws DuplicateEntityFoundException{
		if(this.onlineAccountDao.findOnlineAccountExcluding(
			onlineAccount, name, host)!=null){
			throw new 
				DuplicateEntityFoundException("Online Account Already Exist.");
		}

		onlineAccount.setName(name);
		onlineAccount.setHost(host);
		UpdateSignature updateSignature = new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate());
		onlineAccount.setUpdateSignature(updateSignature);
		return this.onlineAccountDao.makePersistent(onlineAccount);
	}
	
	/**
	 * Update online account.
	 * 
	 * @param onlineAccount online account
	 * @param name name
	 * @param active active
	 * @param primary primary
	 * @param host online account host
	 * @return updated existing online contact	
	 */
	public OnlineAccount update(final OnlineAccount onlineAccount,
		final String name, final Boolean active, final Boolean primary,
		final OnlineAccountHost host)
			throws DuplicateEntityFoundException{
		if(this.onlineAccountDao.findOnlineAccountExcluding(onlineAccount, name, 
			host)!=null){
			throw new 
				DuplicateEntityFoundException("Online Account Already Exist.");
		}

		onlineAccount.setName(name);
		onlineAccount.setActive(active);
		onlineAccount.setHost(host);
		onlineAccount.setPrimary(primary);
		UpdateSignature updateSignature = new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate());
		onlineAccount.setUpdateSignature(updateSignature);
		return this.onlineAccountDao.makePersistent(onlineAccount);
	}
	
	/**
	 * Remove online account.
	 * 
	 * @param onlineAccount online account	
	 */
	public void remove(final OnlineAccount onlineAccount){
		onlineAccountDao.makeTransient(onlineAccount);
	}
	
	/**
	 * Find online account by contact.
	 * 
	 * @param contact contact	
	 * @return a list of online accounts
	 */
	public List<OnlineAccount> findByContact(final Contact contact){
		return onlineAccountDao.findByContact(contact);
	}
}