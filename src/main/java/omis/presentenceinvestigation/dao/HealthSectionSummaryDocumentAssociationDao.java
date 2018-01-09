package omis.presentenceinvestigation.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.presentenceinvestigation.domain.HealthSectionSummary;
import omis.presentenceinvestigation.domain.HealthSectionSummaryDocumentAssociation;

/**
 * Health section summary document association data access object.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 9, 2017)
 * @since OMIS 3.0
 */
public interface HealthSectionSummaryDocumentAssociationDao 
	extends GenericDao<HealthSectionSummaryDocumentAssociation> {

	/**
	 * Find health section summary document association.
	 *
	 * @param healthSectionSummary health section summary
	 * @return 
	 */
	public HealthSectionSummaryDocumentAssociation find(
			HealthSectionSummary healthSectionSummary, Document document);

	/**
	 * 
	 *
	 * @param healthSectionSummaryDocumentAssociation
	 * @param healthSectionSummary
	 * @return
	 */
	public HealthSectionSummaryDocumentAssociation findExcluding(
			HealthSectionSummaryDocumentAssociation 
				healthSectionSummaryDocumentAssociation,
			HealthSectionSummary healthSectionSummary,
			Document document);

	/**
	 * 
	 *
	 * @param healthSectionSummary
	 * @return
	 */
	public List<HealthSectionSummaryDocumentAssociation> 
	findHealthSectionSummaryDocumentAssociation(
			HealthSectionSummary healthSectionSummary);	
}