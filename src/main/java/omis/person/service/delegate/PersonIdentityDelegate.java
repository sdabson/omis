package omis.person.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.country.domain.Country;
import omis.demographics.domain.Sex;
import omis.instance.factory.InstanceFactory;
import omis.person.dao.PersonIdentityDao;
import omis.person.domain.Person;
import omis.person.domain.PersonIdentity;
import omis.person.exception.PersonIdentityExistsException;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Person identity delegate.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (Jun 1, 2015)
 * @since OMIS 3.0
 */
public class PersonIdentityDelegate {

	/* Data access objects. */
	
	private PersonIdentityDao personIdentityDao;
	
	/* Instance factories. */
	
	private InstanceFactory<PersonIdentity> personIdentityInstanceFactory;
	
	/* Component retrievers. */
	
	private AuditComponentRetriever auditComponentRetriever;
	
	/* Constructors. */
	
	/**
	 * Instantiates a person identity delegate with the specified properties.
	 * 
	 * @param personIdentityDao person identity data access object
	 * @param personIdentityInstanceFactory person identity instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public PersonIdentityDelegate(final PersonIdentityDao personIdentityDao,
			final InstanceFactory<PersonIdentity> personIdentityInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.personIdentityDao = personIdentityDao;
		this.personIdentityInstanceFactory = personIdentityInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Method implementations. */
	
	/**
	 * Creates a person identity for the specified person.
	 * 
	 * @param person person
	 * @param sex sex
	 * @param birthDate date of birth
	 * @param birthCountry birth country
	 * @param birthState birth state
	 * @param birthCity birth city
	 * @param socialSecurityNumber social security number
	 * @param stateId state identification 
	 * @param deceased whether deceased applies
	 * @param deathDate date of death
	 * @return newly created person identity
	 * @throws PersonIdentityExistsException if person identity exists
	 */
	public PersonIdentity create(final Person person, final Sex sex, 
			final Date birthDate, final Country birthCountry,
			final State birthState, final City birthCity,
			final Integer socialSecurityNumber, final String stateId, 
			final Boolean deceased, final Date deathDate)
		throws PersonIdentityExistsException {
		if (this.personIdentityDao.find(person, birthDate, birthCity,
				birthCountry, socialSecurityNumber, sex) != null) {
			throw new PersonIdentityExistsException(
					"Duplicate person identity found");
		}
		if ((deceased == null || !deceased) && deathDate != null) {
			throw new IllegalArgumentException(
					"Death date not allowed if deceased is not true");
		}
		PersonIdentity personIdentity = this.personIdentityInstanceFactory
				.createInstance();
		personIdentity.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		personIdentity.setPerson(person);
		this.populatePersonIdentity(personIdentity, sex, birthDate, birthState,
				birthCity, socialSecurityNumber, stateId, deceased, deathDate, 
				birthCountry);
		return this.personIdentityDao.makePersistent(personIdentity);
	}
	
	/**
	 * Updates the specified person identity with the specified properties.
	 * 
	 * @param personIdentity person identity
	 * @param sex sex
	 * @param birthDate birth date
	 * @param birthCountry birth country
	 * @param birthState birth state
	 * @param birthCity birth city
	 * @param socialSecurityNumber social security number
	 * @param stateId state id
	 * @param deceased deceased
	 * @param deathDate death date
	 * @return updated person identity
	 * @throws PersonIdentityExistsException if person identity exists
	 */
	public PersonIdentity update(final PersonIdentity personIdentity,
			final Sex sex, final Date birthDate, final Country birthCountry,
			final State birthState, final City birthCity,
			final Integer socialSecurityNumber,
			final String stateId, final Boolean deceased,
			final Date deathDate)
		throws PersonIdentityExistsException {
		if (birthState != null && 
				!birthCountry.equals(birthState.getCountry())) {
			throw new IllegalArgumentException("Country to state mismatch.");
		}
		if (birthCity != null) {
			if(birthState!=null){
				if (!birthState.equals(birthCity.getState())) {
					throw new IllegalArgumentException("State to city mismatch.");
				}
			} else {
				if(birthCity.getState()!=null){
					throw new IllegalArgumentException("State to city mismatch.");
				}
			}
			if (!birthCountry.equals(birthCity.getCountry())) {
				throw new IllegalArgumentException("Country to city mismatch.");
			}
		}
		if (this.personIdentityDao.findExcluding(personIdentity, 
			socialSecurityNumber, birthDate, birthCity, birthCountry, sex) 
			!= null) {
			throw new PersonIdentityExistsException(
				"Duplicate person identity found.");
		}
		if ((deceased == null || !deceased) && deathDate != null) {
			throw new IllegalArgumentException(
					"Death date not allowed if deceased is not true");
		}
		this.populatePersonIdentity(personIdentity, sex, birthDate, birthState,
				birthCity, socialSecurityNumber, stateId, deceased, deathDate, 
				birthCountry);
		return this.personIdentityDao.makePersistent(personIdentity);
	}
	
	/**
	 * Removes the specified person identity.
	 * 
	 * @param personIdentity person identity
	 */
	public void remove(final PersonIdentity personIdentity) {
		this.personIdentityDao.makeTransient(personIdentity);
	}
	
	/**
	 * Returns alternative identities for person.
	 * 
	 * <p>Alternative person identities are not equal to
	 * {@code person.getIdentity()}.
	 * 
	 * @param person person
	 * @return alternative identities for person
	 */
	public List<PersonIdentity> findAlternativeIdentities(final Person person) {
		return this.personIdentityDao.findAlternativeIdentities(person);
	}
	
	/**
	 * Returns one person identity if exists.
	 * 
	 * @param person person
	 * @return alternative identities for person
	 */
	public PersonIdentity findByPerson(final Person person) {
		return this.personIdentityDao.findByPerson(person);
	}
	
	/* Helper methods. */
	
	private PersonIdentity populatePersonIdentity(
			final PersonIdentity personIdentity, final Sex sex,
			final Date birthDate, final State birthState, 
			final City birthCity, final Integer socialSecurityNumber,
			final String stateId,  final Boolean deceased,
			final Date deathDate, final Country birthCountry) {
		personIdentity.setSex(sex);
		personIdentity.setBirthDate(birthDate);
		personIdentity.setBirthState(birthState);
		personIdentity.setBirthPlace(birthCity);
		personIdentity.setSocialSecurityNumber(socialSecurityNumber);
		personIdentity.setStateIdNumber(stateId);
		personIdentity.setDeceased(deceased);
		personIdentity.setDeathDate(deathDate);
		personIdentity.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		personIdentity.setBirthCountry(birthCountry);
		return personIdentity;
	}
	
	
}