package omis.caseload.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.caseload.dao.CaseloadContactDao;
import omis.caseload.dao.ContactCategoryDao;
import omis.caseload.domain.CaseloadContact;
import omis.caseload.domain.ContactCategory;
import omis.caseload.domain.OffenderCaseAssignment;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;

/** Delegate for caseload contact related operations.
 * @author Ryan Johns
 * @version 0.1.0 (May 17, 2017)
 * @since OMIS 3.0 */
public class CaseloadContactDelegate {
	/* DAOs */
	private final CaseloadContactDao caseloadContactDao;
	private final ContactCategoryDao contactCategoryDao;
	
	/* Instance factory. */
	private final InstanceFactory<CaseloadContact> 
					caseloadContactInstanceFactory;
	
	/* Helpers. */
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Constructor.
	 * @param caseloadContactDao - caseload contact dao.
	 * @param contactCategoryDao - contact category dao. 
	 * @param caseloadContactInstanceFactory - caseload contact instance
	 * factory. 
	 * @param auditComponentRetriever - audit component retriever. */
	public CaseloadContactDelegate(
					final CaseloadContactDao caseloadContactDao,
					final ContactCategoryDao contactCategoryDao,
					final InstanceFactory<CaseloadContact> 
						caseloadContactInstanceFactory,
					final AuditComponentRetriever auditComponentRetriever) {
		this.caseloadContactDao = caseloadContactDao;
		this.contactCategoryDao = contactCategoryDao;
		this.caseloadContactInstanceFactory = caseloadContactInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/**
	 * Returns a new contact.
	 * @param offenderCaseAssignment offender case assignment
	 * @param contactDate - contact date
	 * @param contactBy contact by
	 * @param category category
	 * @param caseNote - case note.
	 * @return new contact
	 * @throws DuplicateEntityFoundException - caseload contact exists.
	 */
	public CaseloadContact create(
					final OffenderCaseAssignment offenderCaseAssignment, 
					final Date contactDate, final Person contactBy, 
					final ContactCategory category, final String caseNote) 
					throws DuplicateEntityFoundException {
		if (this.caseloadContactDao.find(
						offenderCaseAssignment, contactDate, contactBy, 
						category) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate contact found.");
		}
		CaseloadContact contact 
						= this.caseloadContactInstanceFactory.createInstance();
		contact.setCaseAssignment(offenderCaseAssignment);
		contact.setContactDate(contactDate);
		contact.setContactBy(contactBy);
		contact.setCategory(category);
		contact.setCaseNote(caseNote);
		contact.setCreationSignature(new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		contact.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.caseloadContactDao.makePersistent(contact);
	}
	
	/**
	 * Returns an updated contact.
	 * @param contact contact
	 * @param offenderCaseAssignment offender case assignment
	 * @param contactDate - contact date
	 * @param contactBy contact by
	 * @param category category
	 * @return updated contact
	 * @throws DuplicateEntityFoundException - when caseload contact exists.
	 */
	public CaseloadContact update(
					final CaseloadContact contact,
					final OffenderCaseAssignment offenderCaseAssignment, 
					final Date contactDate, final Person contactBy, 
					final ContactCategory category) 
					throws DuplicateEntityFoundException {
		if (this.caseloadContactDao.findExcluding(offenderCaseAssignment, 
						contactDate, contactBy, category, contact) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate contact found.");
		}
		contact.setCaseAssignment(offenderCaseAssignment);
		contact.setContactDate(contactDate);
		contact.setContactBy(contactBy);
		contact.setCategory(category);
		contact.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.caseloadContactDao.makePersistent(contact);
	}
	
	/**
	 * Removes a contact.
	 * @param contact contact
	 */
	public void remove(final CaseloadContact contact) {
		this.caseloadContactDao.makeTransient(contact);
	}
	
	/**
	 * Returns a list of contact categories.
	 * @return list of categories
	 */
	public List<ContactCategory> findAllContactCategories() {
		return this.contactCategoryDao.findAllContactCategories();
	}
}
