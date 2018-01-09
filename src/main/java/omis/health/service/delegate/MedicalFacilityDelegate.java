package omis.health.service.delegate;

import java.util.List;

import omis.health.dao.MedicalFacilityDao;
import omis.health.domain.MedicalFacility;
import omis.organization.domain.Organization;

/**
 * Delegate for medical facilities.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 10, 2016)
 * @since OMIS 3.0
 */
public class MedicalFacilityDelegate {

	/* Resources. */
	
	private final MedicalFacilityDao medicalFacilityDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for medical facilities.
	 * 
	 * @param medicalFacilityDao delegate for medical facilities
	 */
	public MedicalFacilityDelegate(
			final MedicalFacilityDao medicalFacilityDao) {
		this.medicalFacilityDao = medicalFacilityDao;
	}
	
	/* Methods. */
	
	/**
	 * Returns hospitals.
	 * 
	 * @return hospitals
	 */
	public List<MedicalFacility> findHospitals() {
		return this.medicalFacilityDao.findHospitals();
	}

	/**
	 * Returns hospitals by organization.
	 * 
	 * @param organization organization
	 * @return hospitals by organization
	 */
	public List<MedicalFacility> findHospitalsByOrganization(
			final Organization organization) {
		return this.medicalFacilityDao
				.findHospitalsByOrganization(organization);
	}
}