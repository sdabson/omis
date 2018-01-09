package omis.presentenceinvestigation.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.HealthSectionSummary;
import omis.presentenceinvestigation.domain.HealthSectionSummaryNote;

/**
 * Health section summary note data access object.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 9, 2017)
 * @since OMIS 3.0
 */
public interface HealthSectionSummaryNoteDao 
	extends GenericDao<HealthSectionSummaryNote> {
	

	/**
	 * Find health section summary note.
	 *
	 * @param healthSectionSummary health section summary
	 * @param description description
	 * @param date date
	 * @return health section summary note
	 */
	public HealthSectionSummaryNote find(HealthSectionSummary healthSectionSummary, 
			String description, Date date);

	/**
	 * Find health section summary note excluding the one in view.
	 *
	 * @param healthSectionSummaryNote health section summary note
	 * @return health section summary note
	 */
	public HealthSectionSummaryNote findExcluding(HealthSectionSummaryNote 
			healthSectionSummaryNote, HealthSectionSummary healthSectionSummary,
			String description, Date date);
	
	/**
	 * Find list of health section summary notes.
	 *
	 *
	 * @param healthSectionSummary health section summary
	 * @return list of health section summary notes
	 */
	public List<HealthSectionSummaryNote> findHealthSectionSummaryNotesByHealthSectionSummary(
			HealthSectionSummary healthSectionSummary);
}