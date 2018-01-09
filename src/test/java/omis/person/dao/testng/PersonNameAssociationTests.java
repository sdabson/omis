package omis.person.dao.testng;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.instance.factory.InstanceFactory;
import omis.person.dao.PersonDao;
import omis.person.dao.PersonNameDao;
import omis.person.domain.Person;
import omis.person.domain.PersonName;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests person to person name association.
 * @author Stephen Abson
 * @version 0.1.1 (Jan 16, 2013)
 * @since OMIS 3.0
 */
@Test(groups = { "person", "personName", "dao" })
public class PersonNameAssociationTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {

	@Autowired
	private InstanceFactory<Person> personInstanceFactory;
	
	@Autowired
	private InstanceFactory<PersonName> personNameInstanceFactory;
	
	@Autowired
	@Qualifier("personDao")
	private PersonDao personDao;
	
	@Autowired
	@Qualifier("personNameDao")
	private PersonNameDao personNameDao;

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private AuditComponentRetriever auditComponentRetriever;
	
	/** Persists and deletes a person. */
	public void persistAndDeletePerson() {
		Person person = this.personInstanceFactory.createInstance();
		person.setCreationSignature(createSampleCreationSignature());
		person.setUpdateSignature(createSampleUpdateSignature());
		PersonName name = this.personNameInstanceFactory.createInstance();
		person.setName(name);
		name.setPerson(person);
		name.setLastName("Sample");
		name.setFirstName("Sample");
		name.setCreationSignature(createSampleCreationSignature());
		name.setUpdateSignature(createSampleUpdateSignature());
		this.personDao.makePersistent(person);
		Long nameId = name.getId();
		Long personId = person.getId();
		this.sessionFactory.getCurrentSession().flush();
		this.sessionFactory.getCurrentSession().clear();
		name = this.personNameDao.findById(nameId, false);
		person = this.personDao.findById(personId, false);
		assert person.getName().equals(name);
		assert name.getPerson().equals(person);
	}
	
	// Returns a new creation signature
	private CreationSignature createSampleCreationSignature() {
		return new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate());
	}
	
	// Returns a new update signature
	private UpdateSignature createSampleUpdateSignature() {
		return new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate());
	}
}