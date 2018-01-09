package omis.presentenceinvestigation.dao;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.VictimSectionSummary;

/**
 * VictimSectionSummaryDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public interface VictimSectionSummaryDao extends GenericDao<VictimSectionSummary> {
	
	/**
	 * Returns a VictimSectionSummary with the specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return VictimSectionSummary with the specified
	 * PresentenceInvestigationRequest
	 */
	public VictimSectionSummary find(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Returns a VictimSectionSummary with the specified
	 * PresentenceInvestigationRequest excluding specified VictimSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param victimSectionSummaryExcluded - VictimSectionSummary to exclude
	 * from search
	 * @return VictimSectionSummary with the specified
	 * PresentenceInvestigationRequest excluding specified VictimSectionSummary
	 */
	public VictimSectionSummary findExcluding(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			VictimSectionSummary victimSectionSummaryExcluded);
	
}
