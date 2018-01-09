package omis.relationship.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;
import omis.relationship.dao.RelationshipDao;
import omis.relationship.domain.Relationship;
import omis.relationship.exception.ReflexiveRelationshipException;

/**
 * Delegate for relationships.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 1, 2015)
 * @since OMIS 3.0
 */
public class RelationshipDelegate {
	
	/* Resources. */
	
	private final RelationshipDao relationshipDao;
	
	private final InstanceFactory<Relationship> relationshipInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Instantiates delegate for relationships.
	 * 
	 * @param relationshipDao data access object for relationships
	 * @param relationshipInstanceFactory instance factory for relationships
	 * @param auditComponentRetriever audit component retriever
	 */
	public RelationshipDelegate(
			final RelationshipDao relationshipDao,
			final InstanceFactory<Relationship> relationshipInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.relationshipDao = relationshipDao;
		this.relationshipInstanceFactory = relationshipInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates relationship.
	 * 
	 * @param firstPerson first person
	 * @param secondPerson second person
	 * @return relationship
	 * @throws DuplicateEntityFoundException if relationship exists
	 * @param ReflexiveRelationshipException if first and second person are
	 * equal
	 */
	public Relationship create(
			final Person firstPerson, final Person secondPerson)
				throws DuplicateEntityFoundException,
					ReflexiveRelationshipException {
		if (this.relationshipDao.findByPeople(
				firstPerson, secondPerson) != null) {
			throw new DuplicateEntityFoundException("Relationship exists");
		}
		return this.createWithoutDuplicateCheck(firstPerson, secondPerson);
	}

	/**
	 * Returns relationship.
	 * 
	 * @param firstPerson first person
	 * @param secondPerson second person
	 * @return relationship
	 */
	public Relationship find(
			final Person firstPerson, final Person secondPerson) {
		return this.relationshipDao.findByPeople(firstPerson, secondPerson);
	}
	
	/**
	 * Returns relationship.
	 * 
	 * <p>If relationship does not exist, create and return new relationship.
	 * 
	 * <p>The method is provided to avoid redundant look ups of relationships
	 * when creating a relationship that does not exist for use as a property
	 * of an new instance of an associated entity (typically extending
	 * {@code RelationshipAssociable). 
	 * 
	 * @param firstPerson first person
	 * @param secondPerson second person
	 * @return relationship; never {@code null}
	 * @param ReflexiveRelationshipException if first and second person are
	 * equal
	 */
	public Relationship findOrCreate(
			final Person firstPerson, final Person secondPerson)
				throws ReflexiveRelationshipException {
		Relationship relationship = this.relationshipDao.findByPeople(
				firstPerson, secondPerson);
		if (relationship != null) {
			return relationship;
		} else {
			return this.createWithoutDuplicateCheck(firstPerson, secondPerson);
		}
	}
	
	/* Helper methods. */
	
	// Creates relationship without duplicate check
	private Relationship createWithoutDuplicateCheck(
			final Person firstPerson, final Person secondPerson)
				throws ReflexiveRelationshipException {
		if (firstPerson.equals(secondPerson)) {
			throw new ReflexiveRelationshipException(
					"Persons in relationship are equal");
		}
		Relationship relationship = this.relationshipInstanceFactory
				.createInstance();
		relationship.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		relationship.setFirstPerson(firstPerson);
		relationship.setSecondPerson(secondPerson);
		return this.relationshipDao.makePersistent(relationship);
	}
	
	/**
	 * Remove relationship.
	 * 
	 * @param relationship relationship	
	 */
	public void remove(final Relationship relationship){
		this.relationshipDao.makeTransient(relationship);
	}
}