package omis.criminalassociation.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
//import omis.criminalassociation.dao.CriminalAssociationCategoryDao;
import omis.criminalassociation.dao.CriminalAssociationDao;
import omis.criminalassociation.domain.CriminalAssociation;
import omis.criminalassociation.domain.CriminalAssociationCategory;
import omis.criminalassociation.domain.component.CriminalAssociationFlags;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.relationship.dao.RelationshipDao;
import omis.relationship.domain.Relationship;
import omis.relationship.exception.ReflexiveRelationshipException;

/**
 * Criminal association service delegate.
 * 
 * @author Yidong Li
 * @version 0.1.0 (April 14, 2015)
 * @since OMIS 3.0
 */
public class CriminalAssociationServiceDelegate {
	/* Data access objects. */
	private CriminalAssociationDao criminalAssociationDao;
	private RelationshipDao relationshipDao;
	
	/* Instance factories. */
	private InstanceFactory<CriminalAssociation> 
		criminalAssociationInstanceFactory;
	private InstanceFactory<Relationship> relationshipInstanceFactory; 
	
	/* Audit Component Retriever */
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Criminal association service delegate.
	 * 
	 * @param criminalAssociationDao criminal association DAO
	 * @param relationshipDao relationship DAO
	 * @param criminalAssociationInstanceFactory 
	 * criminal association instance factory
	 * @param relationshipInstanceFactory relationship instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public CriminalAssociationServiceDelegate(
		final CriminalAssociationDao criminalAssociationDao, 
		final RelationshipDao relationshipDao,
		final InstanceFactory<CriminalAssociation> 
			criminalAssociationInstanceFactory,
		final InstanceFactory<Relationship> relationshipInstanceFactory,
		final AuditComponentRetriever auditComponentRetriever) {
		this.criminalAssociationInstanceFactory
			= criminalAssociationInstanceFactory;
		this.criminalAssociationDao = criminalAssociationDao;
		this.relationshipDao = relationshipDao;
		this.relationshipInstanceFactory = relationshipInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Create new criminal association for specified offender.
	 * 
	 * @param offender offender
	 * @param associate offender associate
	 * @param dateRange date range
	 * @param category criminal association category
	 * @param criminalAssociationFlags witness - if there is witness or not 
	 * @return criminal association
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 * @throws ReflexiveRelationshipException reflexive relation ship exception
	 */
	public CriminalAssociation associateCriminally(final Offender offender, 
		final Person associate, final DateRange dateRange, 
		final CriminalAssociationCategory category, 
		final CriminalAssociationFlags criminalAssociationFlags) 
			throws DuplicateEntityFoundException, 
			ReflexiveRelationshipException {
		if (offender.equals(associate)) {
			throw new ReflexiveRelationshipException(
					"Second person cannot equal first person in realtionship");
		}
		Relationship relationship
			= this.relationshipDao.findByPeople(offender, associate);
		if (relationship == null) {
	        relationship = this.relationshipInstanceFactory.createInstance();
	        relationship.setFirstPerson(offender);
	        relationship.setSecondPerson(associate);
	        relationship.setCreationSignature(new CreationSignature(
                this.auditComponentRetriever.retrieveUserAccount(),
                this.auditComponentRetriever.retrieveDate()));
	        this.relationshipDao.makePersistent(relationship);
		}

		if (this.criminalAssociationDao.findCriminalAssociation(
			relationship, dateRange) != null) {
		    throw new DuplicateEntityFoundException("Duplicate Entity Found");
		}
		
		CriminalAssociation criminalAssociation 
			= this.criminalAssociationInstanceFactory.createInstance();
		criminalAssociation.setCriminalAssociationCategory(category);
		criminalAssociation.setCriminalAssociationFlags(
			criminalAssociationFlags);
		criminalAssociation.setDateRange(dateRange);
		criminalAssociation.setCreationSignature(new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		
		UpdateSignature updateSignature = new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate());
		criminalAssociation.setUpdateSignature(updateSignature);
		criminalAssociation.setRelationship(relationship);

		this.criminalAssociationDao.makePersistent(criminalAssociation);
		return  criminalAssociation;
	}
	
	/**
	 * Update a criminal association for specified offender.
	 * 
	 * @param criminalAssociation criminal association to be updated
	 * @param dateRange date range
	 * @param criminalAssociationCategory criminal association category 
	 * @param criminalAssociationFlags criminal association flags
	 * @return criminal association
	 */
	public CriminalAssociation update(
			final CriminalAssociation criminalAssociation, 
			final DateRange dateRange,
			final CriminalAssociationCategory criminalAssociationCategory, 
			final CriminalAssociationFlags criminalAssociationFlags) {
		UpdateSignature updateSignature = new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate());
		criminalAssociation.setUpdateSignature(updateSignature);
		criminalAssociation.setCriminalAssociationCategory(
			criminalAssociationCategory);
		criminalAssociation.setCriminalAssociationFlags(
				criminalAssociationFlags);
		criminalAssociation.setDateRange(dateRange);
		return this.criminalAssociationDao.makePersistent(criminalAssociation);
	}
	
	/**
	 * Remove a criminal association for specified offender.
	 * 
	 * @param criminalAssociation criminal association to be removed
	 */
	public void remove(final CriminalAssociation criminalAssociation) {
		this.criminalAssociationDao.makeTransient(criminalAssociation);		
	}
}