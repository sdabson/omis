package omis.presentenceinvestigation.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummary;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummaryNote;

/**
 * PleaAgreementSectionSummaryNoteDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 13, 2017)
 *@since OMIS 3.0
 *
 */
public interface PleaAgreementSectionSummaryNoteDao
	extends GenericDao<PleaAgreementSectionSummaryNote> {
	
	/**
	 * Finds and returns a PleaAgreementSectionSummaryNote found with specified
	 * properties
	 * @param pleaAgreementSectionSummary - PleaAgreementSectionSummary
	 * @param description - String
	 * @return Found PleaAgreementSectionSummaryNote found with specified
	 * properties
	 */
	public PleaAgreementSectionSummaryNote find(
			PleaAgreementSectionSummary pleaAgreementSectionSummary,
			String description);
	
	/**
	 * Finds and returns a PleaAgreementSectionSummaryNote found with specified
	 * properties excluding specified PleaAgreementSectionSummaryNote
	 * @param pleaAgreementSectionSummary - PleaAgreementSectionSummary
	 * @param description - String
	 * @param pleaAgreementSectionSummaryNoteExcluded -
	 * PleaAgreementSectionSummaryNote to exclude from search
	 * @return Found PleaAgreementSectionSummaryNote found with specified
	 * properties excluding specified PleaAgreementSectionSummaryNote
	 */
	public PleaAgreementSectionSummaryNote findExcluding(
			PleaAgreementSectionSummary pleaAgreementSectionSummary,
			String description,
			PleaAgreementSectionSummaryNote pleaAgreementSectionSummaryNoteExcluded);
	
	/**
	 * Returns a list of all PleaAgreementSectionSummaryNotes with specified
	 * PleaAgreementSectionSummary
	 * @param pleaAgreementSectionSummary - PleaAgreementSectionSummary
	 * @return List of all PleaAgreementSectionSummaryNotes with specified
	 * PleaAgreementSectionSummary
	 */
	public List<PleaAgreementSectionSummaryNote> findByPleaAgreementSectionSummary(
			PleaAgreementSectionSummary pleaAgreementSectionSummary);
}
