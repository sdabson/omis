package omis.presentenceinvestigation.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummary;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummaryNote;

/**
 * ChemicalUseSectionSummaryNoteDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 6, 2017)
 *@since OMIS 3.0
 *
 */
public interface ChemicalUseSectionSummaryNoteDao
	extends GenericDao<ChemicalUseSectionSummaryNote> {
	
	/**
	 * Finds and returns a ChemicalUseSectionSummaryNote by specified properties
	 * @param description - String
	 * @param date - Date
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary
	 * @return ChemicalUseSectionSummaryNote found with specified properies
	 */
	public ChemicalUseSectionSummaryNote find(String description, Date date,
			ChemicalUseSectionSummary chemicalUseSectionSummary);
	
	/**
	 * Finds and returns a ChemicalUseSectionSummaryNote with specified
	 * properties excluding specified ChemicalUseSectionSummaryNote
	 * @param description - String
	 * @param date - Date
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary
	 * @param chemicalUseSectionSummaryNoteExcluded - ChemicalUseSectionSummaryNote
	 * to exclude from search
	 * @return ChemicalUseSectionSummaryNote found with specified properties
	 * excluding specified ChemicalUseSectionSummaryNote
	 */
	public ChemicalUseSectionSummaryNote findExcluding(String description,
			Date date, ChemicalUseSectionSummary chemicalUseSectionSummary,
			ChemicalUseSectionSummaryNote chemicalUseSectionSummaryNoteExcluded);
	
	/**
	 * Returns a list of all ChemicalUseSectionSummaryNotes found with
	 * specified ChemicalUseSectionSummary
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary
	 * @return List of all ChemicalUseSectionSummaryNotes found with
	 * specified ChemicalUseSectionSummary
	 */
	public List<ChemicalUseSectionSummaryNote> findByChemicalUseSectionSummary(
			ChemicalUseSectionSummary chemicalUseSectionSummary);
	
}
