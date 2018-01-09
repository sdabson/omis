package omis.presentenceinvestigation.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummary;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummaryAssociableDocument;

/**
 * PleaAgreementSectionSummaryAssociableDocumentDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 13, 2017)
 *@since OMIS 3.0
 *
 */
public interface PleaAgreementSectionSummaryAssociableDocumentDao
		extends GenericDao<PleaAgreementSectionSummaryAssociableDocument> {

	/**
	 * Finds and returns a PleaAgreementSectionSummaryAssociableDocument with
	 * specified parameters
	 * @param pleaAgreementSectionSummary - PleaAgreementSectionSummary
	 * @param document - Document
	 * @return Found PleaAgreementSectionSummaryAssociableDocument with
	 * specified parameters
	 */
	public PleaAgreementSectionSummaryAssociableDocument find(
			PleaAgreementSectionSummary pleaAgreementSectionSummary,
			Document document);
	
	/**
	 * Finds and returns a PleaAgreementSectionSummaryAssociableDocument with
	 * specified parameters excluding specified
	 * PleaAgreementSectionSummaryAssociableDocument
	 * @param pleaAgreementSectionSummary - PleaAgreementSectionSummary
	 * @param document - Document
	 * @param pleaAgreementSectionSummaryAssociableDocumentExcluded -
	 * PleaAgreementSectionSummaryAssociableDocument to exclude from search
	 * @return Found PleaAgreementSectionSummaryAssociableDocument with
	 * specified parameters excluding specified
	 * PleaAgreementSectionSummaryAssociableDocument
	 */
	public PleaAgreementSectionSummaryAssociableDocument findExcluding(
			PleaAgreementSectionSummary pleaAgreementSectionSummary,
			Document document,
			PleaAgreementSectionSummaryAssociableDocument
				pleaAgreementSectionSummaryAssociableDocumentExcluded);
	
	/**
	 * Returns a list of all PleaAgreementSectionSummaryAssociableDocuments
	 * found with specified PleaAgreementSectionSummary
	 * @param pleaAgreementSectionSummary - PleaAgreementSectionSummary
	 * @return List of all PleaAgreementSectionSummaryAssociableDocuments
	 * found with specified PleaAgreementSectionSummary
	 */
	public List<PleaAgreementSectionSummaryAssociableDocument> 
		findByPleaAgreementSectionSummary(
				PleaAgreementSectionSummary pleaAgreementSectionSummary);
	
}
