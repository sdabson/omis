package omis.presentenceinvestigation.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.FinancialSectionSummaryDocumentAssociationDao;
import omis.presentenceinvestigation.domain.FinancialSectionSummary;
import omis.presentenceinvestigation.domain.FinancialSectionSummaryDocumentAssociation;

/**
 * FinancialSectionSummaryDocumentAssociationDelegate
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (July 18, 2017)
 *@since OMIS 3.0
 *
 */
public class FinancialSectionSummaryDocumentAssociationDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"A Financial Section Summary Document Association already exists "
			+ "with specified Document and Financial Section Summary";
	
	private final FinancialSectionSummaryDocumentAssociationDao 
		financialSectionSummaryDocumentAssociationDao;
	
	private final InstanceFactory<FinancialSectionSummaryDocumentAssociation> 
		financialSectionSummaryDocumentAssociationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Constructor for FinancialSectionSummaryDocumentAssociationDelegate
	 * @param financialSectionSummaryDocumentAssociationDao
	 * @param financialSectionSummaryDocumentAssociationInstanceFactory
	 * @param auditComponentRetriever
	 */
	public FinancialSectionSummaryDocumentAssociationDelegate(
			final FinancialSectionSummaryDocumentAssociationDao
				financialSectionSummaryDocumentAssociationDao,
			final InstanceFactory<FinancialSectionSummaryDocumentAssociation>
				financialSectionSummaryDocumentAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.financialSectionSummaryDocumentAssociationDao 
			= financialSectionSummaryDocumentAssociationDao;
		this.financialSectionSummaryDocumentAssociationInstanceFactory 
			= financialSectionSummaryDocumentAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
		
	}
	
	/**
	 * Creates a FinancialSectionSummaryDocumentAssociation with the specified
	 * properties
	 * @param document - Document
	 * @param financialSectionSummary - FinancialSectionSummary
	 * @return Newly created FinancialSectionSummaryDocumentAssociation
	 * @throws DuplicateEntityFoundException - When a 
	 * FinancialSectionSummaryDocumentAssociation already exists with specified
	 * Document and FinancialSectionSummary
	 */
	public FinancialSectionSummaryDocumentAssociation create(
			final Document document,
			final FinancialSectionSummary financialSectionSummary)
				throws DuplicateEntityFoundException {
		if(this.financialSectionSummaryDocumentAssociationDao.find(document, 
				financialSectionSummary) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
			
		}
		
		FinancialSectionSummaryDocumentAssociation 
			 financialSectionSummaryDocumentAssociation		
				= this.financialSectionSummaryDocumentAssociationInstanceFactory
				.createInstance();
		
		financialSectionSummaryDocumentAssociation.setFinancialSectionSummary(
				financialSectionSummary);
		financialSectionSummaryDocumentAssociation.setDocument(document);
		financialSectionSummaryDocumentAssociation.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		financialSectionSummaryDocumentAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.financialSectionSummaryDocumentAssociationDao.makePersistent(
				financialSectionSummaryDocumentAssociation);
		
	}
	
	/**
	 * Updates a FinancialSectionSummaryDocumentAssociation with the specified
	 * properties
	 * @param financialSectionSummaryDocumentAssociation -
	 * FinancialSectionSummaryDocumentAssociation to update
	 * @param document - Document
	 * @return Updated FinancialSectionSummaryDocumentAssociation
	 * @throws DuplicateEntityFoundException - when a
	 * FinancialSectionSummaryDocumentAssociation already exists with specified
	 * Document for its FinancialSectionSummary
	 */
	public FinancialSectionSummaryDocumentAssociation update(
			final FinancialSectionSummaryDocumentAssociation 
				financialSectionSummaryDocumentAssociation,
			final Document document)
					throws DuplicateEntityFoundException {
		if(this.financialSectionSummaryDocumentAssociationDao.findExcluding(
				document, financialSectionSummaryDocumentAssociation
				.getFinancialSectionSummary(), 
				financialSectionSummaryDocumentAssociation) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		financialSectionSummaryDocumentAssociation.setDocument(document);
		financialSectionSummaryDocumentAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.financialSectionSummaryDocumentAssociationDao.makePersistent(
				financialSectionSummaryDocumentAssociation);
	}
	
	/**
	 * Removes a FinancialSectionSummaryDocumentAssociation
	 * @param financialSectionSummaryDocumentAssociation -
	 * FinancialSectionSummaryDocumentAssociation to remove
	 */
	public void remove(final FinancialSectionSummaryDocumentAssociation
			financialSectionSummaryDocumentAssociation) {
		this.financialSectionSummaryDocumentAssociationDao.makeTransient(
				financialSectionSummaryDocumentAssociation);
	}
	
	/**
	 * Returns a list of FinancialSectionSummaryDocumentAssociations found
	 * with specified FinancialSectionSummary
	 * @param financialSectionSummary - FinancialSectionSummary
	 * @return List of FinancialSectionSummaryDocumentAssociations found
	 * with specified FinancialSectionSummary
	 */
	public List<FinancialSectionSummaryDocumentAssociation>
		findByFinancialSectionSummary(
				final FinancialSectionSummary financialSectionSummary) {
		return this.financialSectionSummaryDocumentAssociationDao
				.findByFinancialSectionSummary(financialSectionSummary);
	}
	
}
