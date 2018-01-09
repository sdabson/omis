package omis.presentenceinvestigation.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummary;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummaryDocument;

/**
 * PsychologicalSectionSummaryDocumentDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 1, 2017)
 *@since OMIS 3.0
 *
 */
public interface PsychologicalSectionSummaryDocumentDao
		extends GenericDao<PsychologicalSectionSummaryDocument> {
	
	
	/**
	 * Finds and returns a PsychologicalSectionSummaryDocument if it exists
	 * with the specified parameters
	 * @param document - Document
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary
	 * @return PsychologicalSectionSummaryDocument
	 */
	PsychologicalSectionSummaryDocument find(Document document,
			PsychologicalSectionSummary psychologicalSectionSummary);
	
	/**
	 *Finds and returns a PsychologicalSectionSummaryDocument if it exists
	 * with the specified parameters excluding specified
	 * PsychologicalSectionSummaryDocument
	 * @param document - Document
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary
	 * @param psychologicalSectionSummaryDocumentExcluding -
	 * PsychologicalSectionSummaryDocument to exclude
	 * @return PsychologicalSectionSummaryDocument
	 */
	PsychologicalSectionSummaryDocument findExcluding(Document document,
			PsychologicalSectionSummary psychologicalSectionSummary,
			PsychologicalSectionSummaryDocument
				psychologicalSectionSummaryDocumentExcluding);
	
	/**
	 * Returns a list of all PsychologicalSectionSummaryDocuments by
	 * specified PsychologicalSectionSummary
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary
	 * @return List of PsychologicalSectionSummaryDocuments
	 */
	List<PsychologicalSectionSummaryDocument> findByPsychologicalSectionSummary(
			PsychologicalSectionSummary psychologicalSectionSummary);
	
	
}
