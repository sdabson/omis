package omis.presentenceinvestigation.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.presentenceinvestigation.domain.OffenseSectionSummary;
import omis.presentenceinvestigation.domain.OffenseSectionSummaryAssociableDocument;

/**
 * OffenseSectionSummaryAssociableDocumentDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 31, 2017)
 *@since OMIS 3.0
 *
 */
public interface OffenseSectionSummaryAssociableDocumentDao
		extends GenericDao<OffenseSectionSummaryAssociableDocument> {
	
	/**
	 * Returns an OffenseSectionSummaryAssociableDocument found by
	 * specified OffenseSectionSummary and Document
	 * @param offenseSectionSummary - OffenseSectionSummary
	 * @param document - Document
	 * @return OffenseSectionSummaryAssociableDocument found by
	 * specified OffenseSectionSummary and Document
	 */
	public OffenseSectionSummaryAssociableDocument find(
			OffenseSectionSummary offenseSectionSummary, Document document);
	
	/**
	 * Returns an OffenseSectionSummaryAssociableDocument found by
	 * specified OffenseSectionSummary and Document excluding specified
	 * OffenseSectionSummaryAssociableDocument
	 * @param offenseSectionSummary - OffenseSectionSummary
	 * @param document - Document
	 * @param offenseSectionSummaryAssociableDocumentExcluded -
	 * OffenseSectionSummaryAssociableDocument to exclude
	 * @return OffenseSectionSummaryAssociableDocument found by
	 * specified OffenseSectionSummary and Document excluding specified
	 * OffenseSectionSummaryAssociableDocument
	 */
	public OffenseSectionSummaryAssociableDocument findExcluding(
			OffenseSectionSummary offenseSectionSummary, Document document,
			OffenseSectionSummaryAssociableDocument
				offenseSectionSummaryAssociableDocumentExcluded);
	
	/**
	 * Returns a list of OffenseSectionSummaryAssociableDocuments found by
	 * specified OffenseSectionSummary
	 * @param offenseSectionSummary - OffenseSectionSummary
	 * @return List of OffenseSectionSummaryAssociableDocuments found by
	 * specified OffenseSectionSummary
	 */
	public List<OffenseSectionSummaryAssociableDocument>
		findByOffenseSectionSummary(OffenseSectionSummary offenseSectionSummary);
}
