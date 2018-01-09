package omis.contact.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.contact.dao.TelephoneNumberDao;
import omis.contact.domain.Contact;
import omis.contact.domain.TelephoneNumber;
import omis.contact.domain.TelephoneNumberCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Telephone number delegate.
 * 
 * @author Yidong Li
 * @version 0.1.1 (July 24, 2015)
 * @since OMIS 3.0
 */
public class TelephoneNumberDelegate {

	/* Data access objects. */
	private final TelephoneNumberDao telephoneNumberDao;
	
	/* Instance factories. */
	private final InstanceFactory<TelephoneNumber> telephoneNumberInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Audit Component Retriever */
	
	public TelephoneNumberDelegate(
		final TelephoneNumberDao telephoneNumberDao, 
		final InstanceFactory<TelephoneNumber> telephoneNumberInstanceFactory,
		final AuditComponentRetriever auditComponentRetriever) {
		this.telephoneNumberDao = telephoneNumberDao;
		this.telephoneNumberInstanceFactory = telephoneNumberInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Create telephone number.
	 * 
	 * @param contact contact
	 * @param value phone number 
	 * @param poBox P.O.Box
	 * @return newly created contact	
	 */
	public TelephoneNumber create(final Contact contact, final Long value, 
		final Integer extension, final Boolean primary, final Boolean active, 
		final TelephoneNumberCategory category)throws DuplicateEntityFoundException{
		if (this.telephoneNumberDao.find(contact, value)!=null){
			throw new DuplicateEntityFoundException("Telephone Number Already Exist.");
		}
		TelephoneNumber telephoneNumber 
			= this.telephoneNumberInstanceFactory.createInstance();
		telephoneNumber.setContact(contact);
		telephoneNumber.setValue(value);
		telephoneNumber.setExtension(extension);
		telephoneNumber.setPrimary(primary);
		telephoneNumber.setActive(active);
		telephoneNumber.setCategory(category);
		CreationSignature creationSignature = new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate());
		telephoneNumber.setCreationSignature(creationSignature);
		UpdateSignature updateSignature = new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate());
		telephoneNumber.setUpdateSignature(updateSignature);
		telephoneNumberDao.makePersistent(telephoneNumber);
		return telephoneNumber;
	}
	
	/**
	 * Update telephone number.
	 * 
	 * @param person person
	 * @param mailingAddress mailing address 
	 * @param poBox P.O.Box
	 * @return newly created contact	
	 */
	public TelephoneNumber update(final TelephoneNumber telephoneNumber, 
		final Long value, final Integer extension, final Boolean primary, 
		final Boolean active, final TelephoneNumberCategory category)
			throws DuplicateEntityFoundException{
		if(this.telephoneNumberDao.findTelephoneNumberExcluding(telephoneNumber,
			telephoneNumber.getContact(), value)!=null){
			throw new 
				DuplicateEntityFoundException("Telephone Number Already Exist.");
		}
		telephoneNumber.setValue(value);
		telephoneNumber.setExtension(extension);
		telephoneNumber.setPrimary(primary);
		telephoneNumber.setActive(active);
		telephoneNumber.setCategory(category);
		UpdateSignature updateSignature = new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate());
		telephoneNumber.setUpdateSignature(updateSignature);
		this.telephoneNumberDao.makePersistent(telephoneNumber);
		return telephoneNumber;
	}
	
	/**
	 * Remove telephone number.
	 * 
	 * @param telephoneNumber telephone number	
	 */
	public void remove(final TelephoneNumber telephoneNumber){
		telephoneNumberDao.makeTransient(telephoneNumber);
	}
	
	/**
	 * Find telephone numbers by contact.
	 * 
	 * @param Contact contact
	 * @return list of telephone numbers	
	 */
	public List<TelephoneNumber> findByContact(final Contact contact){
		return telephoneNumberDao.findByContact(contact);
	}
}