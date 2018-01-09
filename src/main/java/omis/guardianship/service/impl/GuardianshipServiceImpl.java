package omis.guardianship.service.impl;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.guardian.service.delegate.GuardianServiceDelegate;
import omis.guardianship.dao.GuardianshipDao;
import omis.guardianship.domain.Guardianship;
import omis.guardianship.service.GuardianshipService;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.relationship.dao.RelationshipDao;
import omis.relationship.domain.Relationship;

/**
 * Implementation of services for Guardianship.
 * @author Joel Norris
 * @version 0.1.0 (Sep 9, 2013)
 * @since OMIS 3.0
 */
public class GuardianshipServiceImpl implements GuardianshipService {

	private GuardianshipDao guardianshipDao;
	private RelationshipDao relationshipDao;
	private GuardianServiceDelegate guardianServiceDelegate;
	private AuditComponentRetriever auditComponentRetriever;
	private InstanceFactory<Relationship> relationshipInstanceFactory;
	
	/**
	 * Instantiates an instance of guardianship service implementation
	 * with the specified data access objects.
	 * 
	 * @param guardianshipDao guardianship data access object
	 * @param relationshipDao relationship data access object
	 * @param guardianServiceDelegate guardian service delegate
	 * @param auditComponentRetriever audit component retriever
	 * @param relationshipInstanceFactory relationship instance factory
	 */
	public GuardianshipServiceImpl(final GuardianshipDao guardianshipDao, 
		final RelationshipDao relationshipDao,
		final GuardianServiceDelegate guardianServiceDelegate,
		final AuditComponentRetriever auditComponentRetriever,
		final InstanceFactory<Relationship> relationshipInstanceFactory) {
		this.guardianshipDao = guardianshipDao;
		this.relationshipDao = relationshipDao;
		this.guardianServiceDelegate = guardianServiceDelegate;
		this.auditComponentRetriever = auditComponentRetriever;
		this.relationshipInstanceFactory = relationshipInstanceFactory;
	}
	
	/**{@inheritDoc}*/
	@Override
	public Guardianship associateAsGuardian(Offender offender, Person guardian)
		throws DuplicateEntityFoundException{
		Relationship relationship = this.relationshipInstanceFactory
			.createInstance();
		relationship.setFirstPerson(offender);
		relationship.setSecondPerson(guardian);
		relationship.setCreationSignature(new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		this.relationshipDao.makePersistent(relationship);
		return this.guardianServiceDelegate.create(relationship, null);
	}
	
	/**{@inheritDoc}*/
	@Override
	public Guardianship associateAsGuardianee(Person guardian, Offender offender)
		throws DuplicateEntityFoundException{
		Relationship relationship = this.relationshipInstanceFactory
			.createInstance();
		relationship.setFirstPerson(guardian);
		relationship.setSecondPerson(offender);
		relationship.setCreationSignature(new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		this.relationshipDao.makePersistent(relationship);
		return this.guardianServiceDelegate.create(relationship, null);
	}
	
	/**{@inheritDoc}*/
	@Override
	public void remove(final Guardianship guardianship) {
		this.guardianshipDao.makeTransient(guardianship);
	}
	
	/**{@inheritDoc}*/
	@Override
	public Guardianship save(final Guardianship guardianship) {
		return this.guardianshipDao.makePersistent(guardianship);
	}
}