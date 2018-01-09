package omis.presentenceinvestigation.dao;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.EducationSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * EducationSectionSummaryDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 16, 2017)
 *@since OMIS 3.0
 *
 */
public interface EducationSectionSummaryDao
		extends GenericDao<EducationSectionSummary> {
	
	
	/**
	 * Finds and returns an EducationSectionSummary with the specified
	 * parameter
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return EducationSectionSummary
	 */
	EducationSectionSummary find(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Finds and returns an EducationSectionSummary with the specified
	 * parameter excluding specified EducationSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param educationSectionSummaryExcluding - EducationSectionSummary to 
	 * exclude from search
	 * @return EducationSectionSummary
	 */
	EducationSectionSummary findExcluding(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			EducationSectionSummary educationSectionSummaryExcluding);
	
}
