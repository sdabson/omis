package omis.presentenceinvestigation.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequestNote;

/**
 * PresentenceInvestigationRequestNoteDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 16, 2017)
 *@since OMIS 3.0
 *
 */
public interface PresentenceInvestigationRequestNoteDao
	extends GenericDao<PresentenceInvestigationRequestNote> {
	
	/**
	 * Finds a PresentenceInvestigationRequestNote with the specified properties
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param description - String
	 * @param date - Date
	 * @return PresentenceInvestigationRequestNote found with the specified
	 * properties
	 */
	public PresentenceInvestigationRequestNote find(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			String description, Date date);
	
	/**
	 * Finds a PresentenceInvestigationRequestNote with the specified properties
	 * excluding specified PresentenceInvestigationRequestNote
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param description - String
	 * @param date - Date
	 * @param presentenceInvestigationRequestNoteExcluding -
	 * PresentenceInvestigationRequestNote to exclude from search
	 * @return PresentenceInvestigationRequestNote found with the specified
	 * properties excluding specified PresentenceInvestigationRequestNote
	 */
	public PresentenceInvestigationRequestNote findExcluding(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			String description, Date date,
			PresentenceInvestigationRequestNote
				presentenceInvestigationRequestNoteExcluding);
	
	/**
	 * Returns a list of PresentenceInvestigationRequestNotes found with the
	 * specified PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return List of PresentenceInvestigationRequestNotes found with the
	 * specified PresentenceInvestigationRequest
	 */
	public List<PresentenceInvestigationRequestNote>
		findByPresentenceInvestigationRequest(
				PresentenceInvestigationRequest presentenceInvestigationRequest);
}
