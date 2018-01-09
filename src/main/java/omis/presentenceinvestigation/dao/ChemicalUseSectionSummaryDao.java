package omis.presentenceinvestigation.dao;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * ChemicalUseSectionSummaryDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 6, 2017)
 *@since OMIS 3.0
 *
 */
public interface ChemicalUseSectionSummaryDao
	extends GenericDao<ChemicalUseSectionSummary> {
	
	/**
	 * Finds and returns a ChemicalUseSectionSummary by specified 
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ChemicalUseSectionSummary found by specified 
	 * PresentenceInvestigationRequest
	 */
	public ChemicalUseSectionSummary find(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Finds and returns a ChemicalUseSectionSummary by specified 
	 * PresentenceInvestigationRequest excluding specified
	 * ChemicalUseSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param chemicalUseSectionSummaryExcluded - ChemicalUseSectionSummary
	 * to exclude
	 * @return ChemicalUseSectionSummary found by specified 
	 * PresentenceInvestigationRequest excluding specified
	 * ChemicalUseSectionSummary
	 */
	public ChemicalUseSectionSummary findExcluding(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			ChemicalUseSectionSummary chemicalUseSectionSummaryExcluded);
	
}
