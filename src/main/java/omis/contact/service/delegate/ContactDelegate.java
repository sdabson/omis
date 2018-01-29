/* 
* OMIS - Offender Management Information System 
* Copyright (C) 2011 - 2017 State of Montana 
* 
* This program is free software: you can redistribute it and/or modify 
* it under the terms of the GNU General Public License as published by 
* the Free Software Foundation, either version 3 of the License, or 
* (at your option) any later version. 
* 
* This program is distributed in the hope that it will be useful, 
* but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
* GNU General Public License for more details. 
* 
* You should have received a copy of the GNU General Public License 
* along with this program.  If not, see <http://www.gnu.org/licenses/>. 
*/
package omis.contact.service.delegate;

import omis.address.domain.Address;
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.contact.dao.ContactDao;
import omis.contact.domain.Contact;
import omis.instance.factory.InstanceFactory;
import omis.contact.domain.component.PoBox;
import omis.contact.exception.ContactExistsException;
import omis.person.domain.Person;

/**
 * Contact service delegate.
 * 
 * @author Yidong Li
 * @version 0.1.1 (June 3, 2015)
 * @since OMIS 3.0
 */
public class ContactDelegate {

	/* Data access objects. */
	private ContactDao contactDao;
	
	/* Instance factories. */
	private InstanceFactory<Contact> contactInstanceFactory;
	
	/* Audit Component Retriever */
	private final AuditComponentRetriever auditComponentRetriever; 
	
	public ContactDelegate(
		final ContactDao contactDao,  
		final InstanceFactory<Contact> contactInstanceFactory,
		final AuditComponentRetriever auditComponentRetriever) {
		this.contactDao = contactDao;
		this.contactInstanceFactory	= contactInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Find contact by person.
	 * 
	 * @param person person
	 * @return contact	
	 */
	public Contact find(final Person person) {
		Contact contact = this.contactDao.find(person);
		return contact;
	}
	
	/**
	 * Create contact.
	 * 
	 * @param person person
	 * @param mailingAddress mailing address 
	 * @param poBox P.O.Box
	 * @return newly created contact	
	 * @throws ContactExistsException exception thrown if duplicate contact
	 * found
	 */
	public Contact create(final Person person, final Address mailingAddress, 
		final PoBox poBox)throws ContactExistsException{
		if (this.contactDao.find(person)!=null){
			throw new ContactExistsException("Contact Already Exist.");
		}
		Contact contact;
		contact = this.contactInstanceFactory.createInstance();
		contact.setPerson(person);
		contact.setPoBox(poBox);
		contact.setMailingAddress(mailingAddress);
		CreationSignature creationSignature = new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate());
		contact.setCreationSignature(creationSignature);
		UpdateSignature updateSignature = new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate());
		contact.setUpdateSignature(updateSignature);
		return contactDao.makePersistent(contact);
	}
	
	/**
	 * Update contact.
	 * 
	 * @param person person
	 * @param mailingAddress mailing address 
	 * @param poBox P.O.Box
	 * @return newly created contact	
	 * @throws ContactExistsException exception thrown if duplicate contact is 
	 * found
	 */
	public Contact update(final Contact contact, final Address mailingAddress, 
		final PoBox poBox)throws ContactExistsException{
		if(this.contactDao.findContactExcluding(contact, contact.getPerson())
			!=null){
			throw new ContactExistsException("Same Contact Already Exist.");
		} 
		contact.setPoBox(poBox);
		contact.setMailingAddress(mailingAddress);
		UpdateSignature updateSignature = new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate());
		contact.setUpdateSignature(updateSignature);
		return contactDao.makePersistent(contact);
	}
	
	/**
	 * Update contact.
	 * 
	 * @param person person
	 * @param mailingAddress mailing address 
	 * @param poBox P.O.Box
	 * @return newly created contact	
	 */
	public void remove(final Contact contact){
		contactDao.makeTransient(contact);
	}
}