package omis.presentenceinvestigation.dao;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.OffenseSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * OffenseSectionSummaryDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 31, 2017)
 *@since OMIS 3.0
 *
 */
public interface OffenseSectionSummaryDao
		extends GenericDao<OffenseSectionSummary> {
	
	/**
	 * Returns an OffenseSectionSummary found with specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return OffenseSectionSummary found with specified
	 * PresentenceInvestigationRequest
	 */
	public OffenseSectionSummary find(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Returns an OffenseSectionSummary found with specified
	 * PresentenceInvestigationRequest excluding specified OffenseSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param offenseSectionSummaryExcluded - OffenseSectionSummary to exclude
	 * @return OffenseSectionSummary found with specified
	 * PresentenceInvestigationRequest excluding specified OffenseSectionSummary
	 */
	public OffenseSectionSummary findExcluding(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			OffenseSectionSummary offenseSectionSummaryExcluded);
	
}
