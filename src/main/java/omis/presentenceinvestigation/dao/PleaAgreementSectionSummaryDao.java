package omis.presentenceinvestigation.dao;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * PleaAgreementSectionSummaryDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 13, 2017)
 *@since OMIS 3.0
 *
 */
public interface PleaAgreementSectionSummaryDao
	extends GenericDao<PleaAgreementSectionSummary> {

	/**
	 * Finds and returns a PleaAgreementSectionSummary found with specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return Found PleaAgreementSectionSummary found with specified
	 * PresentenceInvestigationRequest
	 */
	public PleaAgreementSectionSummary find(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	

	/**
	 * Finds and returns a PleaAgreementSectionSummary found with specified
	 * PresentenceInvestigationRequest excluding specified
	 * PleaAgreementSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param pleaAgreementSectionSummaryExcluded -
	 * PleaAgreementSectionSummary to exclude from search
	 * @return Found PleaAgreementSectionSummary found with specified
	 * PresentenceInvestigationRequest excluding specified
	 * PleaAgreementSectionSummary
	 */
	public PleaAgreementSectionSummary findExcluding(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			PleaAgreementSectionSummary pleaAgreementSectionSummaryExcluded);
	
	
}
