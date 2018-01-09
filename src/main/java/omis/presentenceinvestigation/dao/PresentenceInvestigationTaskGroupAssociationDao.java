package omis.presentenceinvestigation.dao;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskGroup;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskGroupAssociation;

/**
 * PresentenceInvestigationTaskGroupAssociation.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 11, 2017)
 *@since OMIS 3.0
 *
 */
public interface PresentenceInvestigationTaskGroupAssociationDao
		extends GenericDao<PresentenceInvestigationTaskGroupAssociation> {
	
	/**
	 * Returns a PresentenceInvestigationTaskGroupAssociation found with the
	 * specified properties
	 * @param group - PresentenceInvestigationTaskGroup
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return PresentenceInvestigationTaskGroupAssociation found with the
	 * specified properties
	 */
	public PresentenceInvestigationTaskGroupAssociation find(
			PresentenceInvestigationTaskGroup group,
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Returns a PresentenceInvestigationTaskGroupAssociation found with the
	 * specified properties excluding specified
	 * PresentenceInvestigationTaskGroupAssociation
	 * @param group - PresentenceInvestigationTaskGroup
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param presentenceInvestigationTaskGroupAssociationExcluded -
	 * PresentenceInvestigationTaskGroupAssociation to exclude from search
	 * @return PresentenceInvestigationTaskGroupAssociation found with the
	 * specified properties excluding specified
	 */
	public PresentenceInvestigationTaskGroupAssociation findExcluding(
			PresentenceInvestigationTaskGroup group,
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			PresentenceInvestigationTaskGroupAssociation
				presentenceInvestigationTaskGroupAssociationExcluded);
	
	/**
	 * Returns a PresentenceInvestigationTaskGroupAssociation with the specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return PresentenceInvestigationTaskGroupAssociation with the specified
	 * PresentenceInvestigationRequest
	 */
	public PresentenceInvestigationTaskGroupAssociation
		findByPresentenceInvestigationRequest(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
}
