package omis.presentenceinvestigation.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.presentenceinvestigation.domain.VictimSectionSummary;
import omis.presentenceinvestigation.domain.VictimSectionSummaryDocumentAssociation;

/**
 * Victim Section Summary Document Association Data Access Object
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (August 29, 2017)
 *@since OMIS 3.0
 *
 */
public interface VictimSectionSummaryDocumentAssociationDao 
		extends GenericDao<VictimSectionSummaryDocumentAssociation> {
	
	/**
	 * Finds and returns a VictimSectionSummaryDocumentAssociation
	 * found with specified properties
	 * @param document - Document
	 * @param victimSectionSummary - VictimSectionSummary
	 * @return VictimSectionSummaryDocumentAssociation
	 * found with specified properties
	 */
	public VictimSectionSummaryDocumentAssociation find(Document document,
			VictimSectionSummary victimSectionSummary);
	
	/**
	 * Finds and returns a VictimSectionSummaryDocumentAssociation found with
	 * specified properties excluding specified
	 * VictimSectionSummaryDocumentAssociation
	 * @param document - Document
	 * @param victimSectionSummary - VictimSectionSummary
	 * @param victimSectionSummaryDocumentAssociationExcluded -
	 * VictimSectionSummaryDocumentAssociation to exclude from search
	 * @return VictimSectionSummaryDocumentAssociation found with specified
	 * properties excluding specified
	 * VictimSectionSummaryDocumentAssociation
	 */
	public VictimSectionSummaryDocumentAssociation findExcluding(
			Document document, VictimSectionSummary victimSectionSummary,
			VictimSectionSummaryDocumentAssociation
				victimSectionSummaryDocumentAssociationExcluded);
	
	/**
	 * Returns a list of VictimSectionSummaryDocumentAssociations found with
	 * specified VictimSectionSummary
	 * @param victimSectionSummary - VictimSectionSummary
	 * @return List of VictimSectionSummaryDocumentAssociations found with
	 * specified VictimSectionSummary
	 */
	public List<VictimSectionSummaryDocumentAssociation>
		findByVictimSectionSummary(
				VictimSectionSummary victimSectionSummary);
	
}
