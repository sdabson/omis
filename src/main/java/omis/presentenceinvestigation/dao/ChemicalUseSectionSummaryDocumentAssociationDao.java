package omis.presentenceinvestigation.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummary;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummaryDocumentAssociation;

/**
 * ChemicalUseSectionSummaryDocumentAssociationDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 6, 2017)
 *@since OMIS 3.0
 *
 */
public interface ChemicalUseSectionSummaryDocumentAssociationDao
		extends GenericDao<ChemicalUseSectionSummaryDocumentAssociation> {
	
	/**
	 * Finds and returns a ChemicalUseSectionSummaryDocumentAssociation
	 * found with specified properties
	 * @param document - Document
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary
	 * @return ChemicalUseSectionSummaryDocumentAssociation
	 * found with specified properties
	 */
	public ChemicalUseSectionSummaryDocumentAssociation find(Document document,
			ChemicalUseSectionSummary chemicalUseSectionSummary);
	
	/**
	 * Finds and returns a ChemicalUseSectionSummaryDocumentAssociation
	 * found with specified properties excluding specified
	 * ChemicalUseSectionSummaryDocumentAssociation
	 * @param document - Document
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary
	 * @param chemicalUseSectionSummaryDocumentAssociationExcluded -
	 * ChemicalUseSectionSummaryDocumentAssociation to exclude from search
	 * @return ChemicalUseSectionSummaryDocumentAssociation
	 * found with specified properties excluding specified
	 * ChemicalUseSectionSummaryDocumentAssociation
	 */
	public ChemicalUseSectionSummaryDocumentAssociation findExcluding(
			Document document, ChemicalUseSectionSummary chemicalUseSectionSummary,
			ChemicalUseSectionSummaryDocumentAssociation
				chemicalUseSectionSummaryDocumentAssociationExcluded);
	
	/**
	 * Returns a list of ChemicalUseSectionSummaryDocumentAssociations found
	 * with specified ChemicalUseSectionSummary
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary
	 * @return List of ChemicalUseSectionSummaryDocumentAssociations found
	 * with specified ChemicalUseSectionSummary
	 */
	public List<ChemicalUseSectionSummaryDocumentAssociation>
		findByChemicalUseSectionSummary(
				ChemicalUseSectionSummary chemicalUseSectionSummary);
	
}
