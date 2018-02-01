package omis.victim.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.docket.domain.Docket;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;
import omis.victim.dao.VictimDocketAssociationDao;
import omis.victim.domain.VictimDocketAssociation;
import omis.victim.exception.VictimDocketAssociationExistsException;

/**
 * Victim docket association delegate.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 08, 2017)
 * @since OMIS 3.0
 */
public class VictimDocketAssociationDelegate {

	/* Resources. */
	private final VictimDocketAssociationDao victimDocketAssociationDao;
	private final InstanceFactory<VictimDocketAssociation> 
		victimDocketAssociationInstanceFactory;
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Exception messages */
	private final String DUPLICATE_ASSOCIATION_FOUND_EXCEPTION_MESSAGE 
		= "Duplicate victim docket association found";
	
	/* Constructor. */
	
	/**
	 * Instantiates an instance of victim docket association delegate 
	 * with the specified resources.
	 * @param victimDocketAssociationDao victim docket association data access 
	 * object
	 * @param victimDocketAssociationInstanceFactory victim docket association 
	 * instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public VictimDocketAssociationDelegate(
			final VictimDocketAssociationDao victimDocketAssociationDao,
			final InstanceFactory<VictimDocketAssociation> 
				victimDocketAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.victimDocketAssociationDao = victimDocketAssociationDao;
		this.victimDocketAssociationInstanceFactory 
			= victimDocketAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Delegate service methods. */
	
	/**
	 * Creates a victim docket association for the specified victim and docket.
	 * 
	 * @param victim person
	 * @param docket docket
	 * @param victimImpactSummary victim impact summary
	 * @return newly created victim docket association
	 * @throws VictimDocketAssociationExistsException thrown when a duplicate 
	 * victim 
	 * docket association is found
	 */
	public VictimDocketAssociation create(final Person victim, 
			final Docket docket, final String victimImpactSummary)
			throws VictimDocketAssociationExistsException {
		if (this.victimDocketAssociationDao.find(victim, docket) != null) {
			throw new VictimDocketAssociationExistsException(
					DUPLICATE_ASSOCIATION_FOUND_EXCEPTION_MESSAGE);
		}
		VictimDocketAssociation association 
			= this.victimDocketAssociationInstanceFactory.createInstance();
		association.setVictim(victim);
		association.setDocket(docket);
		association.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.victimDocketAssociationDao.makePersistent(association);
	}
	
	/**
	 * Updates the specified victim docket association.
	 * 
	 * @param association victim docket association
	 * @param victimImpactSummary victim impact summary
	 * @return updated victim docket association
	 * @throws VictimDocketAssociationExistsException thrown when a duplicate 
	 * victim docket association is found
	 */
	public VictimDocketAssociation update(
			final VictimDocketAssociation association,
			final String victimImpactSummary) 
					throws VictimDocketAssociationExistsException {
		if (this.victimDocketAssociationDao.findExcluding(association, 
				association.getVictim(),
				association.getDocket()) != null) {
			throw new VictimDocketAssociationExistsException(
					DUPLICATE_ASSOCIATION_FOUND_EXCEPTION_MESSAGE);
		}
		this.populateVictimDocketAssociation(association, victimImpactSummary);
		return association;
	}
	
	/**
	 * Removes the specified victim docket association.
	 * 
	 * @param association victim docket associaiton
	 */
	public void remove(final VictimDocketAssociation association) {
		this.victimDocketAssociationDao.makeTransient(association);
	}
	
	/**
	 * Returns victim docket associations for the specified docket.
	 * 
	 * @param docket docket
	 * @return list of victim docket associations
	 */
	public List<VictimDocketAssociation> findByDocket(final Docket docket) {
		return this.victimDocketAssociationDao.findByDocket(docket);
	}
	
	/**
	 * Returns victim docket associations for the specified victim.
	 *  
	 * @param victim victim
	 * @return list of victim docket associations
	 */
	public List<VictimDocketAssociation> findByVictim(final Person victim) {
		return this.victimDocketAssociationDao.findByVictim(victim);
	}
	
	/* Helper methods. */
	
	/*
	 * Populates the specified victim docket association with the specified 
	 * victim impact summary,
	 * and an update signature.
	 * 
	 * @param association victim docket association
	 * @param victimImpactSummary victim impact summary
	 * @return populated victim impact summary
	 */
	private VictimDocketAssociation populateVictimDocketAssociation(
			final VictimDocketAssociation association,
			final String victimImpactSummary) {
		association.setVictimImpactSummary(victimImpactSummary);
		association.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return association;
	}
}