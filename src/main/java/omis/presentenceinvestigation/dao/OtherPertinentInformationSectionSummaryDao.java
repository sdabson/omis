package omis.presentenceinvestigation.dao;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.OtherPertinentInformationSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * OtherPertinentInformationSectionSummaryDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public interface OtherPertinentInformationSectionSummaryDao
		extends GenericDao<OtherPertinentInformationSectionSummary> {
	
	/**
	 * Finds an OtherPertinentInformationSectionSummary with the specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return OtherPertinentInformationSectionSummary with the specified
	 * PresentenceInvestigationRequest
	 */
	public OtherPertinentInformationSectionSummary find(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Finds an OtherPertinentInformationSectionSummary with the specified
	 * PresentenceInvestigationRequest excluding specified
	 * OtherPertinentInformationSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param otherPertinentInformationSectionSummaryExcluding -
	 * OtherPertinentInformationSectionSummary to exclude from search
	 * @return OtherPertinentInformationSectionSummary with the specified
	 * PresentenceInvestigationRequest excluding specified
	 * OtherPertinentInformationSectionSummary
	 */
	public OtherPertinentInformationSectionSummary findExcluding(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummaryExcluding);
	
}
