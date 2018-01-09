package omis.presentenceinvestigation.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummary;
import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummaryNote;

/**
 * JailAdjustmentSectionSummaryNoteDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 24, 2017)
 *@since OMIS 3.0
 *
 */
public interface JailAdjustmentSectionSummaryNoteDao 
	extends GenericDao<JailAdjustmentSectionSummaryNote> {
	
	/**
	 * Finds and returns a JailAdjustmentSectionSummaryNote if it exists
	 * with the given parameters
	 * @param description - String
	 * @param date - Date
	 * @param jailAdjustmentSectionSummary - JailAdjustmentSectionSummary
	 * @return JailAdjustmentSectionSummaryNote
	 */
	JailAdjustmentSectionSummaryNote find(String description, Date date,
			JailAdjustmentSectionSummary jailAdjustmentSectionSummary);
	
	
	/**
	 * Finds and returns a JailAdjustmentSectionSummaryNote if it exists
	 * with the given parameters
	 * @param description - String
	 * @param date - Date
	 * @param jailAdjustmentSectionSummary - JailAdjustmentSectionSummary
	 * @param jailAdjustmentSectionSummaryNoteExcluded -
	 * JailAdjustmentSectionSummaryNote to exclude
	 * @return JailAdjustmentSectionSummaryNote
	 */
	JailAdjustmentSectionSummaryNote findExcluding(String description, Date date,
			JailAdjustmentSectionSummary jailAdjustmentSectionSummary,
			JailAdjustmentSectionSummaryNote
					jailAdjustmentSectionSummaryNoteExcluded);
	
	/**
	 * Finds and returns a list of JailAdjustmentSectionSummaryNotes
	 * by specified JailAdjustmentSectionSummary
	 * @param jailAdjustmentSectionSummary - JailAdjustmentSectionSummary
	 * @return List of JailAdjustmentSectionSummaryNotes
	 */
	List<JailAdjustmentSectionSummaryNote> findByJailAdjustmentSectionSummary(
			JailAdjustmentSectionSummary jailAdjustmentSectionSummary);
}
