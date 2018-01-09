package omis.presentenceinvestigation.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.EducationSectionSummary;
import omis.presentenceinvestigation.domain.EducationSectionSummaryNote;

/**
 * EducationSectionSummaryNoteDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 16, 2017)
 *@since OMIS 3.0
 *
 */
public interface EducationSectionSummaryNoteDao
		extends GenericDao<EducationSectionSummaryNote> {
	
	/**
	 * Finds and returns a EducationSectionSummaryNote with specified 
	 * parameters
	 * @param description - String
	 * @param date - Date
	 * @param educationSectionSummary - EducationSectionSummary
	 * @return EducationSectionSummaryNote
	 */
	EducationSectionSummaryNote find(String description, Date date,
			EducationSectionSummary educationSectionSummary);
	
	/**
	 * Finds and returns a EducationSectionSummaryNote with specified 
	 * parameters excluding specified EducationSectionSummaryNote
	 * @param description - String
	 * @param date - Date
	 * @param educationSectionSummary - EducationSectionSummary
	 * @param educationSectionSummaryNoteExcluding -EducationSectionSummaryNote
	 * to exclude from search
	 * @return EducationSectionSummaryNote
	 */
	EducationSectionSummaryNote findExcluding(String description, Date date,
			EducationSectionSummary educationSectionSummary, EducationSectionSummaryNote educationSectionSummaryNoteExcluding);
	
	/**
	 * Returns a list of all EducationSectionSummaryNotes by EducationSectionSummary
	 * @param educationSectionSummary - EducationSectionSummary
	 * @return List of EducationSectionSummaryNotes
	 */
	List<EducationSectionSummaryNote> findByEducationSectionSummary(
			EducationSectionSummary educationSectionSummary);

}
