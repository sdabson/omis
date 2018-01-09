package omis.presentenceinvestigation.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummary;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummaryNote;

/**
 * PsychologicalSectionSummaryNoteDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 1, 2017)
 *@since OMIS 3.0
 *
 */
public interface PsychologicalSectionSummaryNoteDao
		extends GenericDao<PsychologicalSectionSummaryNote> {
	
	
	/**
	 * Finds and returns a PsychologicalSectionSummaryNote if it is found with
	 * specified parameters
	 * @param description - String
	 * @param date - Date
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary
	 * @return PsychologicalSectionSummaryNote
	 */
	PsychologicalSectionSummaryNote find(String description, Date date,
			PsychologicalSectionSummary psychologicalSectionSummary);
	
	/**
	 * Finds and returns a PsychologicalSectionSummaryNote if it is found with
	 * specified parameters excluding specified PsychologicalSectionSummaryNote
	 * @param description - String
	 * @param date - Date
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary
	 * @param psychologicalSectionSummaryNoteExcluded - PsychologicalSectionSummaryNote
	 * to exclude
	 * @return PsychologicalSectionSummaryNote
	 */
	PsychologicalSectionSummaryNote findExcluding(String description, Date date,
			PsychologicalSectionSummary psychologicalSectionSummary,
			PsychologicalSectionSummaryNote
					psychologicalSectionSummaryNoteExcluded);
	
	/**
	 * Returns a list of PsychologicalSectionSummaryNotes found by specified
	 * PsychologicalSectionSummary
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary
	 * @return List of PsychologicalSectionSummaryNotes
	 */
	List<PsychologicalSectionSummaryNote> findByPsychologicalSectionSummary(
			PsychologicalSectionSummary psychologicalSectionSummary);
	
}
