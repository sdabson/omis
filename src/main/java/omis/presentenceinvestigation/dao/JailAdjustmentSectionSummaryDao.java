package omis.presentenceinvestigation.dao;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * JailAdjustmentSectionSummaryDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 24, 2017)
 *@since OMIS 3.0
 *
 */
public interface JailAdjustmentSectionSummaryDao 
	extends GenericDao<JailAdjustmentSectionSummary> {
	
	/**
	 * Finds and returns a JailAdjustmentSectionSummary if it exists with
	 * given parameters
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return JailAdjustmentSectionSummary
	 */
	JailAdjustmentSectionSummary find(PresentenceInvestigationRequest
			presentenceInvestigationRequest);
	
	
	/**
	 * Finds and returns a JailAdjustmentSectionSummary if it exists with
	 * given parameters excluding specified JailAdjustmentSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param jailAdjustmentSectionSummaryExcluded - JailAdjustmentSectionSummary
	 * to exclude
	 * @return JailAdjustmentSectionSummary
	 */
	JailAdjustmentSectionSummary findExcluding(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			JailAdjustmentSectionSummary jailAdjustmentSectionSummaryExcluded);
	
}
