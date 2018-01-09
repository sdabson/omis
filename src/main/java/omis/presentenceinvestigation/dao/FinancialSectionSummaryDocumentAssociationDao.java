package omis.presentenceinvestigation.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.presentenceinvestigation.domain.FinancialSectionSummary;
import omis.presentenceinvestigation.domain.FinancialSectionSummaryDocumentAssociation;

/**
 * FinancialSectionSummaryDocumentAssociationDao
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (July 17, 2017)
 *@since OMIS 3.0
 *
 */
public interface FinancialSectionSummaryDocumentAssociationDao
		extends GenericDao<FinancialSectionSummaryDocumentAssociation> {
	
	/**
	 * Finds and returns a FinancialSectionSummaryDocumentAssociation
	 * found with specified properties
	 * @param document - Document
	 * @param financialSectionSummary - FinancialSectionSummary
	 * @return FinancialSectionSummaryDocumentAssociation
	 * found with specified properties
	 */
	public FinancialSectionSummaryDocumentAssociation find(Document document,
			FinancialSectionSummary financialSectionSummary);
	
	/**
	 * Finds and returns a FinancialSectionSummaryDocumentAssociation found with
	 * specified properties excluding specified
	 * FinancialSectionSummaryDocumentAssociation
	 * @param document - Document
	 * @param financialSectionSummary - FinancialSectionSummary
	 * @param financialSectionSummaryDocumentAssociationExcluded -
	 * FinancialSectionSummaryDocumentAssociation to exclude from search
	 * @return FinancialSectionSummaryDocumentAssociation found with specified
	 * properties excluding specified
	 * FinancialSectionSummaryDocumentAssociation
	 */
	public FinancialSectionSummaryDocumentAssociation findExcluding(
			Document document, FinancialSectionSummary financialSectionSummary,
			FinancialSectionSummaryDocumentAssociation
				financialSectionSummaryDocumentAssociationExcluded);
	
	/**
	 * Returns a list of FinancialSectionSummaryDocumentAssociations found with
	 * specified FinancialSectionSummary
	 * @param financialSectionSummary - FinancialSectionSummary
	 * @return List of FinancialSectionSummaryDocumentAssociations found with
	 * specified FinancialSectionSummary
	 */
	public List<FinancialSectionSummaryDocumentAssociation>
		findByFinancialSectionSummary(
				FinancialSectionSummary financialSectionSummary);

}
