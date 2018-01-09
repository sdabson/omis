package omis.presentenceinvestigation.dao;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummary;

/**
 * PsychologicalSectionSummaryDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 1, 2017)
 *@since OMIS 3.0
 *
 */
public interface PsychologicalSectionSummaryDao
		extends GenericDao<PsychologicalSectionSummary> {
	
	/**
	 * Finds and returns a PsychologicalSectionSummary if it is found with
	 * specified parameters
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return PsychologicalSectionSummary
	 */
	PsychologicalSectionSummary find(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Finds and returns a PsychologicalSectionSummary if it is found with
	 * specified parameters excluding specified PsychologicalSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param psychologicalSectionSummaryExcluded - PsychologicalSectionSummary
	 * to be excluded
	 * @return PsychologicalSectionSummary
	 */
	PsychologicalSectionSummary findExcluding(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			PsychologicalSectionSummary psychologicalSectionSummaryExcluded);
	
}
