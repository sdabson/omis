package omis.presentenceinvestigation.dao;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.FinancialSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * FinancialSectionSummaryDao
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (May 31, 2017)
 *@since OMIS 3.0
 *
 */
public interface FinancialSectionSummaryDao 
	extends GenericDao<FinancialSectionSummary> {
	
	/**
	 * Finds and returns a FinancialSectionSummary if it exists with given
	 * parameters
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return FinancialSectionSummary
	 */
	FinancialSectionSummary find(PresentenceInvestigationRequest 
			presentenceInvestigationRequest);
	
	/**
	 * Finds and returns a FinancialSectionSummary if it exists with given
	 * parameters excluding specified FinancialSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param financialSectionSummaryExcluded - FinancialSectionSummary to
	 * exclude
	 * @return FinancialSectionSummary
	 */
	FinancialSectionSummary findExcluding(PresentenceInvestigationRequest 
			presentenceInvestigationRequest, FinancialSectionSummary 
			financialSectionSummaryExcluded);

}
