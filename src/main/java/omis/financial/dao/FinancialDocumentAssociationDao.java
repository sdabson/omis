package omis.financial.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.financial.domain.FinancialDocumentAssociation;
import omis.offender.domain.Offender;

/**
 * FinancialAssociableDocumentDao
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (June 2, 2017)
 *@since OMIS 3.0
 *
 */
public interface FinancialDocumentAssociationDao 
		extends GenericDao<FinancialDocumentAssociation> {
	
	/**
	 * Finds an FinancialAssociableDocument with the specified parameters
	 * @param offender - Offender
	 * @param document - Document
	 * @return FinancialAssociableDocument
	 */
	FinancialDocumentAssociation findFinancialDocument(Offender offender, 
			Document document);
	
	/**
	 * Finds an FinancialAssociableDocument with the specified parameters
	 * excluding specified FinancialAssociableDocument
	 * @param offender - Offender 
	 * @param document - Document
	 * @param FinancialAssociableDocumentExcluded - FinancialAssociableDocument
	 * to exclude
	 * @return FinancialAssociableDocument
	 */
	FinancialDocumentAssociation findFinancialDocumentExcluding(
			Offender offender, Document document,
			FinancialDocumentAssociation financialAssociableDocumentExcluded);
	
	/**
	 * Finds all financial associable documents.
	 * @param offender
	 * @return
	 */
	List<FinancialDocumentAssociation> 
		findFinancialDocumentAssociationsByOffender(Offender offender);

}
