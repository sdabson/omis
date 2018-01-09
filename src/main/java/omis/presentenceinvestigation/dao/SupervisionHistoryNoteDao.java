package omis.presentenceinvestigation.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.SupervisionHistoryNote;
import omis.presentenceinvestigation.domain.SupervisionHistorySectionSummary;

public interface SupervisionHistoryNoteDao 
	extends GenericDao<SupervisionHistoryNote> {
	
	/**
	 * Find supervision history note data access object.
	 *
	 *
	 * @param supervisionHistorySectionSummary supervision history 
	 * section summary
	 * @param description description
	 * @param date date
	 * @return supervision history note
	 */
	SupervisionHistoryNote find(
			SupervisionHistorySectionSummary supervisionHistorySectionSummary,
			String description, Date date);
	
	/**
	 * Find supervision history note by supervision history section summary.
	 *
	 *
	 * @param supervisionHistoryNote supervision history note
	 * @param supervisionHistorySectionSummary supervision history 
	 * section summary
	 * @param description description
	 * @param date date
	 * @return supervision history note
	 */
	SupervisionHistoryNote findExcluding(
			SupervisionHistoryNote supervisionHistoryNote,
			SupervisionHistorySectionSummary supervisionHistorySectionSummary,
			String description, Date date);
	
	/**
	 * Return supervision history note.
	 *
	 *
	 * @param supervisionHistorySectionSummary supervision history 
	 * section summary
	 * @return list of supervision history note
	 */
	List<SupervisionHistoryNote> 
	findSupervisionHistoryNotesBySupervisionHistorySectionSummary(
			SupervisionHistorySectionSummary supervisionHistorySectionSummary);
}