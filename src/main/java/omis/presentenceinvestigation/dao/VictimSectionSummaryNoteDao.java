package omis.presentenceinvestigation.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.VictimSectionSummary;
import omis.presentenceinvestigation.domain.VictimSectionSummaryNote;

/**
 * VictimSectionSummaryNoteDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public interface VictimSectionSummaryNoteDao
	extends GenericDao<VictimSectionSummaryNote> {
	
	/**
	 * Returns a VictimSectionSummaryNote with the specified properties
	 * @param victimSectionSummary - VictimSectionSummary
	 * @param description - String
	 * @param date - Date
	 * @return VictimSectionSummary found with the specified properties
	 */
	public VictimSectionSummaryNote find(
			VictimSectionSummary victimSectionSummary, String description,
			Date date);
	
	/**
	 * Returns a VictimSectionSummaryNote with the specified properties
	 * excluding specified VictimSectionSummaryNote
	 * @param victimSectionSummary - VictimSectionSummary
	 * @param description - String
	 * @param date - Date
	 * @param victimSectionSummaryNoteExcluded - VictimSectionSummaryNote to
	 * exclude from search
	 * @return VictimSectionSummary found with the specified properties excluding
	 * specified VictimSectionSummaryNote
	 */
	public VictimSectionSummaryNote findExcluding(
			VictimSectionSummary victimSectionSummary, String description,
			Date date, VictimSectionSummaryNote victimSectionSummaryNoteExcluded);
	
	/**
	 * Returns a List of VictimSectionSummaryNotes found by specified
	 * VictimSectionSummary
	 * @param victimSectionSummary - VictimSectionSummary
	 * @return List of VictimSectionSummaryNotes found by specified
	 * VictimSectionSummary
	 */
	public List<VictimSectionSummaryNote> findByVictimSectionSummary(
			VictimSectionSummary victimSectionSummary);
	
}
