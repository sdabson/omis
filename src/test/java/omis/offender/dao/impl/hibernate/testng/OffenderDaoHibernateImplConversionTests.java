package omis.offender.dao.impl.hibernate.testng;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.instance.factory.InstanceFactory;
import omis.offender.dao.OffenderDao;
import omis.person.domain.Person;
import omis.person.domain.PersonName;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests conversion of person to offender in Hibernate data access object.
 *
 * <p>Hibernate specific unit tests for DAOs are not usually required; that
 * people can be converted to offenders using direct SQL manipulation makes
 * offender an exception to this rule.
 *
 * <p>Many of the practices in this unit test - such as the injection of the
 * session factory - are discouraged in usual tests.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"offender", "daoHibernateImpl"})
public class OffenderDaoHibernateImplConversionTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	private static final String PERSON_ENTITY_NAME
		= "omis.person.domain.Person";

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	@Qualifier("offenderDao")
	private OffenderDao offenderDao;
	
	@Autowired
	@Qualifier("personInstanceFactory")
	private InstanceFactory<Person> personInstanceFactory;

	@Autowired
	@Qualifier("personNameInstanceFactory")
	private InstanceFactory<PersonName> personNameInstanceFactory;
	
	@Autowired
	@Qualifier("auditComponentRetriever")
	private AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Tests update to a person after conversion to an offender.
	 * 
	 * <p>In Hibernate, the update is successful.
	 */
	public void testUpdateToPersonAfterConvert() {
		
		// Arrangements
		Person person = this.createPerson("Blofeld", "Ernst", null, null);
		Integer offenderNumber = this.offenderDao.findNextOffenderNumber();
		Long id = this.offenderDao.convertPerson(
				person, offenderNumber).getId();
		
		// Action
		final String newLastName = "Carver";
		final String newFirstName = "Elliot";
		person.getName().setFirstName(newFirstName);
		person.getName().setLastName(newLastName);
		this.sessionFactory.getCurrentSession().update(
				PERSON_ENTITY_NAME, person);
		this.sessionFactory.getCurrentSession().flush();
		this.sessionFactory.getCurrentSession().clear();
		
		// Assertion
		Person lookUp = (Person) this.sessionFactory.getCurrentSession().load(
				PERSON_ENTITY_NAME, id);
		assert lookUp.getName().getFirstName().equals(newFirstName)
			: String.format("Wrong first name - %s expected; %s found",
					newFirstName, lookUp.getName().getFirstName());
		assert lookUp.getName().getLastName().equals(newLastName)
			: String.format("Wrong last name - %s expected; %s found",
					newLastName, lookUp.getName().getLastName());
	}

	/* Helper methods. */
	
	// Creates person
	private Person createPerson(
			final String lastName, final String firstName,
			final String middleName, final String suffix) {
		Person person = this.personInstanceFactory.createInstance();
		person.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		person.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		PersonName personName = this.personNameInstanceFactory
				.createInstance();
		personName.setLastName(lastName);
		personName.setFirstName(firstName);
		personName.setMiddleName(middleName);
		personName.setSuffix(suffix);
		personName.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		personName.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		person.setName(personName);
		personName.setPerson(person);
		this.sessionFactory.getCurrentSession().save(
				PERSON_ENTITY_NAME, person);
		return person;
	}
}