package omis.health.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.health.domain.MedicalFacility;
import omis.organization.domain.Organization;

/**
 * Data access object for medical facilities.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 23, 2014)
 * @since OMIS 3.0
 */
public interface MedicalFacilityDao
		extends GenericDao<MedicalFacility> {

	/**
	 * Returns hospitals.
	 * 
	 * @return hospitals
	 */
	List<MedicalFacility> findHospitals();

	/**
	 * Returns hospitals by organization.
	 * 
	 * @param organization organization
	 * @return hospitals by organization
	 */
	List<MedicalFacility> findHospitalsByOrganization(
			Organization organization);
}