package omis.victim.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;
import omis.victim.dao.VictimDocketDocumentAssociationDao;
import omis.victim.domain.VictimDocketAssociation;
import omis.victim.domain.VictimDocketDocumentAssociation;

/**
 * Victim docket document association delegate.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 08, 2017)
 * @since OMIS 3.0
 */
public class VictimDocketDocumentAssociationDelegate {

	/* Resources. */
	
	private final VictimDocketDocumentAssociationDao victimDocketDocumentAssociationDao;
	private final InstanceFactory<VictimDocketDocumentAssociation> victimDocketDocumentAssociationInstanceFactory;
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Exception messages. */
	
	private static final String DUPLICATE_DOCUMENT_ASSOCIATION_FOUND_MESSAGE 
	= "Duplicate victim docket document association found";
	
	/* Constructor. */
	
	/**
	 * Instantiates an instance of victim docket docuent association delegate with the specified resources.
	 * 
	 * @param victimDocketDocumentAssociationDao victim docket document association data access object
	 * @param victimDocketDocumentAssociationInstanceFactory victim docket document association instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public VictimDocketDocumentAssociationDelegate(
			final VictimDocketDocumentAssociationDao victimDocketDocumentAssociationDao,
			final InstanceFactory<VictimDocketDocumentAssociation> victimDocketDocumentAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.victimDocketDocumentAssociationDao = victimDocketDocumentAssociationDao;
		this.victimDocketDocumentAssociationInstanceFactory = victimDocketDocumentAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Delegate service methods. */
	
	/**
	 * Creates a victim docket document association.
	 * @param victimDocketAssociation victim docket association
	 * @param document document
	 * @return newly created victim docket document association
	 * @throws DuplicateEntityFoundException thrown when a duplicate victim docket document association
	 * is found.
	 */
	public VictimDocketDocumentAssociation create(final VictimDocketAssociation victimDocketAssociation,
			final Document document) throws DuplicateEntityFoundException {
		if (this.victimDocketDocumentAssociationDao.find(victimDocketAssociation, document) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_DOCUMENT_ASSOCIATION_FOUND_MESSAGE);
		}
		VictimDocketDocumentAssociation victimDocketDocumentAssociation 
			= this.victimDocketDocumentAssociationInstanceFactory.createInstance();
		victimDocketDocumentAssociation.setVictimDocketAssociation(victimDocketAssociation);
		victimDocketDocumentAssociation.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		this.populateVictimDocketDocumentAssociation(victimDocketDocumentAssociation, document);
		return this.victimDocketDocumentAssociationDao.makePersistent(victimDocketDocumentAssociation);
	}
	
	/**
	 * Updates the specified victim docket document association.
	 * 
	 * @param association victim docket document association
	 * @param document document
	 * @return updated victim docket document association
	 * @throws DuplicateEntityFoundException thrown when a duplicate victim docket document association
	 * is found.
	 */
	public VictimDocketDocumentAssociation update(final VictimDocketDocumentAssociation association,
			final Document document) throws DuplicateEntityFoundException {
		if (this.victimDocketDocumentAssociationDao.findExcluding(association, association.getVictimDocketAssociation(),
				document) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_DOCUMENT_ASSOCIATION_FOUND_MESSAGE);
		}
		this.populateVictimDocketDocumentAssociation(association, document);
		return this.victimDocketDocumentAssociationDao.makePersistent(association);
	}
	
	/**
	 * Removes the specified victim docket document association.
	 * 
	 * @param associaiton victim docket document association
	 */
	public void remove(final VictimDocketDocumentAssociation associaiton) {
		this.victimDocketDocumentAssociationDao.makeTransient(associaiton);
	}
	
	/**
	 * Returns all victim docket document associations for the specified victim.
	 * 
	 * @param victim person
	 * @return list of victim docket document associations
	 */
	public List<VictimDocketDocumentAssociation> findByVictim(final Person victim) {
		return this.victimDocketDocumentAssociationDao.findByVictim(victim);
	}
	
	/**
	 * Returns all victim docket document associations for the specified victim docket association.
	 * 
	 * @param victimDocketAssociation victim docket association
	 * @return list of victim docket document associations
	 */
	public List<VictimDocketDocumentAssociation> findByVictimDocketAssociation(
			final VictimDocketAssociation victimDocketAssociation) {
		return this.victimDocketDocumentAssociationDao.findByVictimDocketAssociation(victimDocketAssociation);
	}
	
	/* Helper methods. */
	
	/*
	 * Populates the specified victim docket document association with the specified document, and an update
	 * signature.
	 * 
	 * @param victimDocketDocumentAssociation victim docket document association
	 * @param document documet
	 * @return populated victim docket document association
	 */
	private VictimDocketDocumentAssociation populateVictimDocketDocumentAssociation(
			final VictimDocketDocumentAssociation victimDocketDocumentAssociation,
			final Document document) {
		victimDocketDocumentAssociation.setDocument(document);
		victimDocketDocumentAssociation.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return victimDocketDocumentAssociation;
	}
}