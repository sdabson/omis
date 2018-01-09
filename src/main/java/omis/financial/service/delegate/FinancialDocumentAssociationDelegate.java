package omis.financial.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.financial.dao.FinancialDocumentAssociationDao;
import omis.financial.domain.FinancialDocumentAssociation;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * FinancialAssociableDocumentDelegate
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (June 2, 2017)
 *@since OMIS 3.0
 *
 */
public class FinancialDocumentAssociationDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Financial Associable Document already exists"
			+ " with given Offender and Document";
	
	private final FinancialDocumentAssociationDao financialDocumentAssociationDao;
	
	private final InstanceFactory<FinancialDocumentAssociation> 
	financialDocumentAssociationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Constructor for FinancialAssociableDocumentDelegate
	 * @param financialDocumentAssociationDao
	 * @param financialDocumentAssociationInstanceFactory
	 * @param auditComponentRetriever
	 */
	public FinancialDocumentAssociationDelegate(
			final FinancialDocumentAssociationDao financialDocumentAssociationDao,
			final InstanceFactory<FinancialDocumentAssociation> 
				financialDocumentAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.financialDocumentAssociationDao = financialDocumentAssociationDao;
		this.financialDocumentAssociationInstanceFactory =
				financialDocumentAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an FinancialAssociableDocument with the specified properties
	 * @param offender - Offender
	 * @param document - Document
	 * @return Newly created FinancialAssociableDocument
	 * @throws DuplicateEntityFoundException - when a FinancialAssociableDocument
	 * already exists with given Offender and Document
	 */
	public FinancialDocumentAssociation createDocument(final Offender offender,
			final Document document)
			throws DuplicateEntityFoundException{
		if(this.financialDocumentAssociationDao.findFinancialDocument(offender, 
				document) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		FinancialDocumentAssociation financialAssociableDocument 
			= this.financialDocumentAssociationInstanceFactory.createInstance();
		
		financialAssociableDocument.setOffender(offender);
		financialAssociableDocument.setDocument(document);
		financialAssociableDocument.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		financialAssociableDocument.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.financialDocumentAssociationDao.makePersistent(
				financialAssociableDocument);
	}
	
	/**
	 * Updates an FinancialAssociableDocument with the specified properties
	 * @param financialAssociableDocument - FinancialAssociableDocument to update
	 * @param document - Document
	 * @param category - FinancialDocumentCategory
	 * @return Updated FinancialAssociableDocument
	 * @throws DuplicateEntityFoundException - when a FinancialAssociableDocument
	 * already exists with given Offender and Document
	 */
	public FinancialDocumentAssociation updateDocument(
			final FinancialDocumentAssociation financialAssociableDocument,
			final Document document)
			throws DuplicateEntityFoundException{
		if(this.financialDocumentAssociationDao.findFinancialDocumentExcluding(
				financialAssociableDocument.getOffender(), document,
				financialAssociableDocument) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		financialAssociableDocument.setDocument(document);
		financialAssociableDocument.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.financialDocumentAssociationDao.makePersistent(
				financialAssociableDocument);
	}
	
	public List<FinancialDocumentAssociation> 
		findFinancialDocumentAssociationsByOffender(Offender offender) {
		return this.financialDocumentAssociationDao
				.findFinancialDocumentAssociationsByOffender(offender);
	}
	
	/**
	 * Removes an FinancialAssociableDocument
	 * @param financialAssociableDocument - FinancialAssociableDocument to 
	 * remove
	 */
	public void removeDocument(
			final FinancialDocumentAssociation financialAssociableDocument){
		this.financialDocumentAssociationDao.makeTransient(
				financialAssociableDocument);
	}

}
