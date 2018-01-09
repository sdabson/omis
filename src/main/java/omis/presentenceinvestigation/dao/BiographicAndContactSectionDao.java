package omis.presentenceinvestigation.dao;

import java.util.Date;

import omis.presentenceinvestigation.domain.BiographicAndContactSection;
import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Biographical and Contact Section data access object.
 * 
 * @author Jonny Santy
 * @version 0.1.0 (Oct 27, 2016)
 * @since OMIS 3.0
 */
public interface BiographicAndContactSectionDao 
	extends GenericDao<BiographicAndContactSection>{


	/**
	 * Returns the Biographic And Contact Section with the specified PSI request, name, and date of PSI report
	 * 
	 * @param presentenceInvestigationRequest
	 * @param name
	 * @param dateOfReport
	 * @return BiographicAndContactSection given it's primary business key
	 */
	BiographicAndContactSection find(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			String name,
			Date dateOfReport);
	
	/**
	 * 
	 * Returns the Biographic And Contact Section with the specified PSI request, name, and date of PSI report
	 * excluding the specified biographicAndContactSection.  This can be used to help find duplicates without relying
	 * <i>exclusively</i> upon the database constraints.
	 * 
	 * @param biographicAndContactSection
	 * @param presentenceInvestigationRequest
	 * @param name
	 * @param dateOfReport
	 * @return Biographic And Contact Section
	 */
	BiographicAndContactSection findExcluding(
			BiographicAndContactSection biographicAndContactSection,
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			String name,
			Date dateOfReport);
}