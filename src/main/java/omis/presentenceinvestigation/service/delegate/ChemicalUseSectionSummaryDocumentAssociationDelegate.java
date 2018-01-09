
package omis.presentenceinvestigation.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.ChemicalUseSectionSummaryDocumentAssociationDao;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummary;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummaryDocumentAssociation;

/**
 * ChemicalUseSectionSummaryDocumentAssociationDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 6, 2017)
 *@since OMIS 3.0
 *
 */
public class ChemicalUseSectionSummaryDocumentAssociationDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"A Chemical Use Section Summary Document Association already exists "
			+ "with specified Document and Chemical Use Section Summary";
	
	private final ChemicalUseSectionSummaryDocumentAssociationDao chemicalUseSectionSummaryDocumentAssociationDao;
	
	private final InstanceFactory<ChemicalUseSectionSummaryDocumentAssociation> 
		chemicalUseSectionSummaryDocumentAssociationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for ChemicalUseSectionSummaryDocumentAssociationDelegate
	 * @param chemicalUseSectionSummaryDocumentAssociationDao
	 * @param chemicalUseSectionSummaryDocumentAssociationInstanceFactory
	 * @param auditComponentRetriever
	 */
	public ChemicalUseSectionSummaryDocumentAssociationDelegate(
			final ChemicalUseSectionSummaryDocumentAssociationDao chemicalUseSectionSummaryDocumentAssociationDao,
			final InstanceFactory<ChemicalUseSectionSummaryDocumentAssociation> 
				chemicalUseSectionSummaryDocumentAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.chemicalUseSectionSummaryDocumentAssociationDao = chemicalUseSectionSummaryDocumentAssociationDao;
		this.chemicalUseSectionSummaryDocumentAssociationInstanceFactory = chemicalUseSectionSummaryDocumentAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a ChemicalUseSectionSummaryDocumentAssociation with the specified
	 * properties
	 * @param document - Document
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary
	 * @return Newly created ChemicalUseSectionSummaryDocumentAssociation
	 * @throws DuplicateEntityFoundException - When a 
	 * ChemicalUseSectionSummaryDocumentAssociation already exists with specified
	 * Document and ChemicalUseSectionSummary
	 */
	public ChemicalUseSectionSummaryDocumentAssociation create(
			final Document document,
			final ChemicalUseSectionSummary chemicalUseSectionSummary)
					throws DuplicateEntityFoundException{
		if(this.chemicalUseSectionSummaryDocumentAssociationDao.find(document,
				chemicalUseSectionSummary) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		ChemicalUseSectionSummaryDocumentAssociation
			chemicalUseSectionSummaryDocumentAssociation = 
				this.chemicalUseSectionSummaryDocumentAssociationInstanceFactory
				.createInstance();
		
		chemicalUseSectionSummaryDocumentAssociation.setChemicalUseSectionSummary(
				chemicalUseSectionSummary);
		chemicalUseSectionSummaryDocumentAssociation.setDocument(document);
		chemicalUseSectionSummaryDocumentAssociation.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		chemicalUseSectionSummaryDocumentAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.chemicalUseSectionSummaryDocumentAssociationDao.makePersistent(
				chemicalUseSectionSummaryDocumentAssociation);
	}
	
	/**
	 * Updates a ChemicalUseSectionSummaryDocumentAssociation with the specified
	 * properties
	 * @param chemicalUseSectionSummaryDocumentAssociation -
	 * ChemicalUseSectionSummaryDocumentAssociation to update
	 * @param document - Document
	 * @return Updated ChemicalUseSectionSummaryDocumentAssociation
	 * @throws DuplicateEntityFoundException - when a
	 * ChemicalUseSectionSummaryDocumentAssociation already exists with specified
	 * Document for its ChemicalUseSectionSummary
	 */
	public ChemicalUseSectionSummaryDocumentAssociation update(
			final ChemicalUseSectionSummaryDocumentAssociation
				chemicalUseSectionSummaryDocumentAssociation,
			final Document document)
					throws DuplicateEntityFoundException{
		if(this.chemicalUseSectionSummaryDocumentAssociationDao.findExcluding(
				document, chemicalUseSectionSummaryDocumentAssociation
				.getChemicalUseSectionSummary(),
				chemicalUseSectionSummaryDocumentAssociation) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		chemicalUseSectionSummaryDocumentAssociation.setDocument(document);
		chemicalUseSectionSummaryDocumentAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.chemicalUseSectionSummaryDocumentAssociationDao.makePersistent(
				chemicalUseSectionSummaryDocumentAssociation);
	}
	
	/**
	 * Removes a ChemicalUseSectionSummaryDocumentAssociation
	 * @param chemicalUseSectionSummaryDocumentAssociation -
	 * ChemicalUseSectionSummaryDocumentAssociation to remove
	 */
	public void remove(final ChemicalUseSectionSummaryDocumentAssociation
			chemicalUseSectionSummaryDocumentAssociation){
		this.chemicalUseSectionSummaryDocumentAssociationDao.makeTransient(
				chemicalUseSectionSummaryDocumentAssociation);
	}
	
	/**
	 * Returns a list of ChemicalUseSectionSummaryDocumentAssociations found
	 * with specified ChemicalUseSectionSummary
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary
	 * @return List of ChemicalUseSectionSummaryDocumentAssociations found
	 * with specified ChemicalUseSectionSummary
	 */
	public List<ChemicalUseSectionSummaryDocumentAssociation>
		findByChemicalUseSectionSummary(
				final ChemicalUseSectionSummary chemicalUseSectionSummary){
		return this.chemicalUseSectionSummaryDocumentAssociationDao
				.findByChemicalUseSectionSummary(chemicalUseSectionSummary);
	}
	
}
