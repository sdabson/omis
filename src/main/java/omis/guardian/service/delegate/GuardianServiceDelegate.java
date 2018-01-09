package omis.guardian.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.guardianship.dao.GuardianshipDao;
import omis.guardianship.domain.Guardianship;
import omis.instance.factory.InstanceFactory;
import omis.relationship.domain.Relationship;

/**
 * Guardianship service implementation delegate.
 * 
 * @author Yidong Li
 * @version 0.1.0 (June 8, 2015)
 * @since OMIS 3.0
 */
public class GuardianServiceDelegate {

	/* Data access objects. */
	
	private GuardianshipDao guardianshipDao;
	
	/* Instance factories. */
	
	private InstanceFactory<Guardianship> guardianshipInstanceFactory;
	
	/* Component retrievers. */
	
	private AuditComponentRetriever auditComponentRetriever;
	
	/* Constructor. */
	
	/**
	 * Instantiates a guardianship service implementation delegate with
	 * the specified data access object and instance factory.
	 * 
	 * @param guardianshipDao guardianship data access object
	 * @param guardianshipInstanceFactory guardianshipInstanceFactory guardianship
	 * instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public GuardianServiceDelegate(
			final GuardianshipDao guardianshipDao,
			final InstanceFactory<Guardianship> guardianshipInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.guardianshipDao = guardianshipDao;
		this.guardianshipInstanceFactory = guardianshipInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Management methods. */
	
	/**
	 * Creates a family association for the specified relationship.
	 * 
	 * @param relationship relationship
	 * @param dateRange date range
	 * @return created guardianship
	 */
	public Guardianship create(final Relationship relationship,
		final DateRange dateRange)
		throws DuplicateEntityFoundException {
		if(dateRange!=null){
			if (this.guardianshipDao.findGuardianship(relationship, 
				dateRange.getStartDate(), dateRange.getEndDate())!= null) {
				throw new DuplicateEntityFoundException(
						"Duplicate guardianship found");
			}
		}
		else {
			if (this.guardianshipDao.findGuardianship(relationship, null, null)
				!= null) {
				throw new DuplicateEntityFoundException(
					"Duplicate guardianship found");
			}
		}
		
		Guardianship guardianship = this.guardianshipInstanceFactory
			.createInstance();
		guardianship.setRelationship(relationship);
		guardianship.setDateRange(dateRange);
		guardianship.setCreationSignature(new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		guardianship.setUpdateSignature(new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		return this.guardianshipDao.makePersistent(guardianship);
	}
	
	/**
	 * Updates a guardianship for the specified guardianship.
	 * 
	 * @param guardianship guardianship
	 * @param dateRange date range
	 * @return updated guardianship
	 */
	public Guardianship update(final Guardianship guardianship,	
		final DateRange dateRange)
		throws DuplicateEntityFoundException {
		if(dateRange!=null){
			if (this.guardianshipDao.findGuardianship(guardianship.getRelationship(), 
				dateRange.getStartDate(), dateRange.getEndDate())!= null) {
				throw new DuplicateEntityFoundException(
				"Duplicate guardianship found");
			}
		}
		else {
			if (this.guardianshipDao.findGuardianship(
				guardianship.getRelationship(), null, null)!= null) {
				throw new DuplicateEntityFoundException(
					"Duplicate guardianship found");
			}
		}
		
		guardianship.setDateRange(dateRange);
		guardianship.setUpdateSignature(new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		return this.guardianshipDao.makePersistent(guardianship);
	}
	
	/**
	 * Removes the specified relationship.
	 * 
	 * @param relationship
	 */
	public void remove(final Guardianship guardianship) {
		this.guardianshipDao.makeTransient(guardianship);  
	}
}