package omis.person.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.instance.factory.InstanceFactory;
import omis.person.dao.PersonNameDao;
import omis.person.domain.Person;
import omis.person.domain.PersonName;
import omis.person.exception.PersonNameExistsException;

/**
 * Delegate for person names.
 * 
 * @author Annie Jacques
 * @author Stephen Abson
 * @version 0.1.0 (Nov 9, 2016)
 * @since OMIS 3.0
 */
public class PersonNameDelegate {
	
	/* Messages. */
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Person Name already exists for specified Person";
	
	/* Data access object. */
	
	private final PersonNameDao personNameDao;
	
	/* Instance factory. */
	
	private final InstanceFactory<PersonName> 
		personNameInstanceFactory;
	
	/* Audit component retreiver. */
	
	private final AuditComponentRetriever auditComponentRetriever;

	/* Constructors. */
	
	/**
	 * Constructor for PersonNameDelegate
	 * @param personNameDao
	 * @param personNameInstanceFactory
	 * @param auditComponentRetriever
	 */
	public PersonNameDelegate(
			final PersonNameDao personNameDao,
			final InstanceFactory<PersonName> 
				personNameInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.personNameDao = personNameDao;
		this.personNameInstanceFactory = personNameInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Methods. */
	
	/**
	 * Creates person name.
	 * 
	 * @param person person
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @return person name
	 * @throws PersonNameExistsException if person name exists
	 */
	public PersonName create(final Person person, final String lastName, 
				final String firstName, final String middleName, 
				final String suffix)
			throws PersonNameExistsException {
		if(this.personNameDao.find(person, firstName, lastName, middleName, 
				suffix)){
			throw new PersonNameExistsException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		PersonName personName = 
				this.personNameInstanceFactory.createInstance();
		
		personName.setFirstName(firstName);
		personName.setLastName(lastName);
		personName.setMiddleName(middleName);
		personName.setSuffix(suffix);
		personName.setPerson(person);
		personName.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		personName.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.personNameDao.makePersistent(personName);
	}
	
	/**
	 * Updates person name.
	 * 
	 * @param personName person name to update
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @return update person name
	 * @throws PersonNameExistsException if person name exists
	 */
	public PersonName update(final PersonName personName,
			final String lastName, final String firstName,
			final String middleName, final String suffix)
			throws PersonNameExistsException {
		if(this.personNameDao.findExcluding(personName, firstName, lastName,
				middleName, suffix)){
			throw new PersonNameExistsException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		personName.setFirstName(firstName);
		personName.setLastName(lastName);
		personName.setMiddleName(middleName);
		personName.setSuffix(suffix);
		personName.setUpdateSignature(
		new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		
		return this.personNameDao.makePersistent(personName);
	}
	
	/**
	 * Removes a personName
	 * @param personName - person name to remove
	 */
	public void remove(final PersonName personName){
		this.personNameDao.makeTransient(personName);
	}
	
	/**
	 * Returns alternative names for person.
	 * 
	 * <p>Alternative person names are names not equal to
	 * {@code person.getName()}.
	 * 
	 * @param person person
	 * @return alternative person names for person
	 */
	public List<PersonName> findAlternativeNames(Person person){
		return this.personNameDao.findAlternativeNames(person);
	}	
}