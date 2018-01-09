package omis.asrc.dao;

import omis.asrc.domain.AssessmentSanctionRevocationCenter;
import omis.dao.GenericDao;
import omis.location.domain.Location;

/**
 * Data access object for assessment sanction revocation center.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 13, 2017)
 * @since OMIS 3.0
 */
public interface AssessmentSanctionRevocationCenterDao 
		extends GenericDao<AssessmentSanctionRevocationCenter> {

	/**
	 * Returns the assessment sanction revocation center matching the specified 
	 * location, name and telephone number.
	 * 
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @return assessment sanction revocation center
	 */
	AssessmentSanctionRevocationCenter find(Location location, String name, 
			Long telephoneNumber);
	
	/**
	 * Returns the assessment sanction revocation center matching the specified 
	 * location, name and telephone number, excluding the specified assessment
	 * sanction revocation center.
	 * 
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @param excludedAssessmentSanctionRevocationCenter
	 * @return assessment sanction revocation center
	 */
	AssessmentSanctionRevocationCenter findExcluding(Location location, 
			String name, Long telephoneNumber, 
			AssessmentSanctionRevocationCenter 
				excludedAssessmentSanctionRevocationCenter);
}
