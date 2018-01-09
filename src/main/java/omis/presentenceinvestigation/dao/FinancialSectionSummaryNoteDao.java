package omis.presentenceinvestigation.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.FinancialSectionSummary;
import omis.presentenceinvestigation.domain.FinancialSectionSummaryNote;

/**
 * FinancialSectionSummaryNoteDao
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (May 31, 2017)
 *@since OMIS 3.0
 *
 */
public interface FinancialSectionSummaryNoteDao 
	extends GenericDao<FinancialSectionSummaryNote> {
	
	/**
	 * Finds and returns a FinancialSectionSummaryNote if it exists with the
	 * given parameters
	 * @param description - String
	 * @param date - Date
	 * @param financialSectionSummary - FinancialSectionSummary
	 * @return FinancialSectionSummaryNote
	 */
	FinancialSectionSummaryNote find(String description, Date date, 
			FinancialSectionSummary financialSectionSummary);
	
	/**
	 * Finds and returns a FinancialSectionSummaryNote if it exists with the
	 * given parameters
	 * @param description - String
	 * @param date - Date
	 * @param financialSectionSummary - FinancialSectionSummary
	 * @param financialSectionSummaryNoteExcluded - FinancialSectionSummaryNote
	 * to exclude
	 * @return FinancialSectionSummaryNote
	 */
	FinancialSectionSummaryNote findExcluding(String description, Date date,
			FinancialSectionSummary financialSectionSummary, 
			FinancialSectionSummaryNote financialSectionSummaryNoteExcluded);
	
	/**
	 * Finds and returns a list of FinancialSectionSummaryNotes by specified
	 * FinancialSectionSummary
	 * @param financialSectionSummary - FinancialSectionSummary
	 * @return List of FinancialSectionSummaryNotes
	 */
	List<FinancialSectionSummaryNote> findByFinancialSectionSummary(
			FinancialSectionSummary financialSectionSummary);
	
}
