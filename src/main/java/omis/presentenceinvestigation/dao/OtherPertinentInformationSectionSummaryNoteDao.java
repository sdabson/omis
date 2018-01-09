package omis.presentenceinvestigation.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.OtherPertinentInformationSectionSummary;
import omis.presentenceinvestigation.domain.OtherPertinentInformationSectionSummaryNote;

/**
 * OtherPertinentInformationSectionSummaryNoteDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public interface OtherPertinentInformationSectionSummaryNoteDao
		extends GenericDao<OtherPertinentInformationSectionSummaryNote> {
	
	/**
	 * Finds a OtherPertinentInformationSectionSummaryNote with the specified
	 * properties
	 * @param otherPertinentInformationSectionSummary -
	 * OtherPertinentInformationSectionSummary
	 * @param description - String
	 * @param date - Date
	 * @return OtherPertinentInformationSectionSummaryNote with the specified
	 * properties
	 */
	public OtherPertinentInformationSectionSummaryNote find(
			OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummary,
			String description, Date date);
	
	/**
	 * Finds a OtherPertinentInformationSectionSummaryNote with the specified
	 * properties excluding specified OtherPertinentInformationSectionSummaryNote
	 * @param otherPertinentInformationSectionSummary -
	 * OtherPertinentInformationSectionSummary
	 * @param description - String
	 * @param date - Date
	 * @param otherPertinentInformationSectionSummaryNoteExcluding -
	 * OtherPertinentInformationSectionSummaryNote to exclude from search
	 * @return OtherPertinentInformationSectionSummaryNote with the specified
	 * properties excluding specified OtherPertinentInformationSectionSummaryNote
	 */
	public OtherPertinentInformationSectionSummaryNote findExcluding(
			OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummary,
			String description, Date date,
			OtherPertinentInformationSectionSummaryNote
				otherPertinentInformationSectionSummaryNoteExcluding);
	
	/**
	 * Returns a list of all OtherPertinentInformationSectionSummaryNotes found
	 * with specified OtherPertinentInformationSectionSummary
	 * @param otherPertinentInformationSectionSummary -
	 * OtherPertinentInformationSectionSummary
	 * @return List of all OtherPertinentInformationSectionSummaryNotes found
	 * with specified OtherPertinentInformationSectionSummary
	 */
	public List<OtherPertinentInformationSectionSummaryNote>
		findByOtherPertinentInformationSectionSummary(
			OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummary);
}
