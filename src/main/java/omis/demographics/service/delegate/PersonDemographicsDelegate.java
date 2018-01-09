package omis.demographics.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.demographics.dao.PersonDemographicsDao;
import omis.demographics.domain.DominantSide;
import omis.demographics.domain.MaritalStatus;
import omis.demographics.domain.PersonDemographics;
import omis.demographics.domain.Race;
import omis.demographics.domain.Tribe;
import omis.demographics.domain.component.PersonAppearance;
import omis.demographics.domain.component.PersonPhysique;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;

/**
 * PersonDemographics.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 9, 2016)
 *@since OMIS 3.0
 *
 */
public class PersonDemographicsDelegate {
	private final PersonDemographicsDao personDemographicsDao;
	
	private final InstanceFactory<PersonDemographics> 
		personDemographicsInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for PersonDemographicsDelegate
	 * @param personDemographicsDao
	 * @param personDemographicsInstanceFactory
	 * @param auditComponentRetriever
	 */
	public PersonDemographicsDelegate(
			final PersonDemographicsDao personDemographicsDao,
			final InstanceFactory<PersonDemographics> 
				personDemographicsInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.personDemographicsDao = personDemographicsDao;
		this.personDemographicsInstanceFactory = 
				personDemographicsInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates personDemographics with specified properties
	 * @param person - Person
	 * @param appearance - PersonAppearance
	 * @param race - Race
	 * @param hispanicEthnicity - Boolean
	 * @param physique - PersonPhysique
	 * @param dominantSide - DominantSide
	 * @param maritalStatus - MaritalStatus
	 * @param tribe - Tribe
	 * @return Newly created PersonDemographics with specified properties
	 * @throws DuplicateEntityFoundException - when personDemographics already
	 * exists for specified person
	 */
	public PersonDemographics create(final Person person, 
			final PersonAppearance appearance, final Race race,
			final Boolean hispanicEthnicity,
			final PersonPhysique physique, final DominantSide dominantSide,
			final MaritalStatus maritalStatus, final Tribe tribe){
		PersonDemographics personDemographics = 
				this.personDemographicsInstanceFactory.createInstance();
		
		personDemographics.setAppearance(appearance);
		personDemographics.setDominantSide(dominantSide);
		personDemographics.setHispanicEthnicity(hispanicEthnicity);
		personDemographics.setMaritalStatus(maritalStatus);
		personDemographics.setPerson(person);
		personDemographics.setPhysique(physique);
		personDemographics.setRace(race);
		personDemographics.setTribe(tribe);
		personDemographics.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		personDemographics.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.personDemographicsDao.makePersistent(personDemographics);
	}
	
	/**
	 * Updates a personDemographics with the specified properties
	 * @param personDemographics - PersonDemographics to update
	 * @param appearance - PersonAppearance
	 * @param race - Race
	 * @param hispanicEthnicity - Boolean
	 * @param physique - PersonPhysique
	 * @param dominantSide - DominantSide
	 * @param maritalStatus - MaritalStatus
	 * @param tribe - Tribe
	 * @return Newly updated PersonDemographics with specified properties
	 */
	public PersonDemographics update(final PersonDemographics personDemographics,
			final PersonAppearance appearance, final Race race,
			final Boolean hispanicEthnicity, final PersonPhysique physique,
			final DominantSide dominantSide, final MaritalStatus maritalStatus,
			final Tribe tribe){
		personDemographics.setAppearance(appearance);
		personDemographics.setDominantSide(dominantSide);
		personDemographics.setHispanicEthnicity(hispanicEthnicity);
		personDemographics.setMaritalStatus(maritalStatus);
		personDemographics.setPhysique(physique);
		personDemographics.setRace(race);
		personDemographics.setTribe(tribe);
		personDemographics.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.personDemographicsDao.makePersistent(personDemographics);
	}
	
	/**
	 * Removes specified PersonDemographics
	 * @param personDemographics
	 */
	public void remove(final PersonDemographics personDemographics){
		this.personDemographicsDao.makeTransient(personDemographics);
	}
	
	/**
	 * Finds and returns PersonDemographics by PersonDemographics
	 * @param demographics - PersonDemographics
	 * @return PersonDemographics by PersonDemographics
	 */
	public PersonDemographics findByDemographics(
			final PersonDemographics demographics){
		return this.personDemographicsDao.findByDemographics(demographics);
	}
	
	/**
	 * Returns a PersonDemographics by specified person
	 * @param person
	 * @return PersonDemographics by specified person
	 */
	public PersonDemographics find(Person person){
		return this.personDemographicsDao.find(person);
	}
	
}
