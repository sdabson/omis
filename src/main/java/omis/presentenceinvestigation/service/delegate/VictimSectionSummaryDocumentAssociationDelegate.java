package omis.presentenceinvestigation.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.VictimSectionSummaryDocumentAssociationDao;
import omis.presentenceinvestigation.domain.VictimSectionSummary;
import omis.presentenceinvestigation.domain.VictimSectionSummaryDocumentAssociation;

/**
 * Victim Section Summary Document Association Delegate
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (August 29, 2017)
 *@since OMIS 3.0
 *
 */
public class VictimSectionSummaryDocumentAssociationDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG 
		= "A Victim Section Summary Document Association already exists with "
		+ "specified Document and Victim Section Summary";
	
	private final VictimSectionSummaryDocumentAssociationDao
		victimSectionSummaryDocumentAssociationDao;
	
	private final InstanceFactory<VictimSectionSummaryDocumentAssociation>
		victimSectionSummaryDocumentAssociationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Constructor for Victim Section Summary Document Association Delegate
	 * @param victimSectionSummaryDocumentAssociationDao
	 * @param victimSectionSummaryDocumentAssociationInstanceFactory
	 * @param auditComponentRetriever
	 */
	public VictimSectionSummaryDocumentAssociationDelegate(
			final VictimSectionSummaryDocumentAssociationDao
				victimSectionSummaryDocumentAssociationDao,
			final InstanceFactory<VictimSectionSummaryDocumentAssociation>
				victimSectionSummaryDocumentAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.victimSectionSummaryDocumentAssociationDao 
			= victimSectionSummaryDocumentAssociationDao;
		this.victimSectionSummaryDocumentAssociationInstanceFactory 
			= victimSectionSummaryDocumentAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a Victim Section Summary Document Association with the specified
	 * properties.
	 * @param document - Document
	 * @param victimSectionSummary - VictimSectionSummary
	 * @return Newly created VictimSectionSummaryDocumentAssociation
	 * @throws DuplicateEntityFoundException - When a 
	 * VictimSectionSummaryDocumentAssociation already exists with specified
	 * Document and VictimSectionSummary
	 */
	public VictimSectionSummaryDocumentAssociation create(final Document document,
			final VictimSectionSummary victimSectionSummary)
				throws DuplicateEntityFoundException {
		if(this.victimSectionSummaryDocumentAssociationDao.find(document, 
				victimSectionSummary) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		VictimSectionSummaryDocumentAssociation 
			victimSectionSummaryDocumentAssociation 
			= this.victimSectionSummaryDocumentAssociationInstanceFactory
			.createInstance();
		
		victimSectionSummaryDocumentAssociation.setVictimSectionSummary(
				victimSectionSummary);
		victimSectionSummaryDocumentAssociation.setDocument(document);
		victimSectionSummaryDocumentAssociation.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		victimSectionSummaryDocumentAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		
		return this.victimSectionSummaryDocumentAssociationDao.makePersistent(
				victimSectionSummaryDocumentAssociation);
	}
	
	/**
	 * Updates a Victim Section Summary Document Association with the specified
	 * properties.
	 * @param victimSectionSummaryDocumentAssociation - 
	 * VictimSectionSummaryDocumentAssociation to update
	 * @param document - Document
	 * @return Updated VictimSectionSummaryDocumentAssociation
	 * @throws DuplicateEntityFoundException - When a 
	 * VictimSectionSummaryDocumentAssociation already exists with specified
	 * Document for its VictimSectionSummary
	 */
	public VictimSectionSummaryDocumentAssociation update(
			final VictimSectionSummaryDocumentAssociation 
				victimSectionSummaryDocumentAssociation,
			final Document document)
					throws DuplicateEntityFoundException {
		if(this.victimSectionSummaryDocumentAssociationDao.findExcluding(
				document, victimSectionSummaryDocumentAssociation
				.getVictimSectionSummary(), 
				victimSectionSummaryDocumentAssociation) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		victimSectionSummaryDocumentAssociation.setDocument(document);
		victimSectionSummaryDocumentAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		
		return this.victimSectionSummaryDocumentAssociationDao.makePersistent(
				victimSectionSummaryDocumentAssociation);
	}
	
	/**
	 * Removes a VictimSectionSummaryDocumentAssociation
	 * @param victimSectionSummaryDocumentAssociation -
	 * VictimSectionSummaryDocumentAssociation to remove
	 */
	public void remove(final VictimSectionSummaryDocumentAssociation
			victimSectionSummaryDocumentAssociation) {
		this.victimSectionSummaryDocumentAssociationDao.makeTransient(
				victimSectionSummaryDocumentAssociation);
	}
	
	/**
	 * Returns a list of FinancialSectionSummaryDocumentAssociations found
	 * with specified FinancialSectionSummary
	 * @param financialSectionSummary - FinancialSectionSummary
	 * @return List of FinancialSectionSummaryDocumentAssociations found
	 * with specified FinancialSectionSummary
	 */
	public List<VictimSectionSummaryDocumentAssociation>
		findByVictimSectionSummary(
				final VictimSectionSummary victimSectionSummary) {
		return this.victimSectionSummaryDocumentAssociationDao
				.findByVictimSectionSummary(victimSectionSummary);
	}

}
