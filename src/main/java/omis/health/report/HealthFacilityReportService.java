package omis.health.report;

import java.util.Date;
import java.util.List;

import omis.facility.domain.Facility;
import omis.person.domain.Person;

/**
 * Report service for health facilities.
 * 
 * Health facilities have providers assigned to them.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 9, 2016)
 * @since OMIS 3.0
 */
public interface HealthFacilityReportService {

	/**
	 * Returns health facilities that staff member is assigned to on date. 
	 * 
	 * @param staffMember staff member
	 * @param date date
	 * @return health facilities that staff member is assigned to on date
	 */
	List<Facility> findHealthFacilitiesForStaff(
			Person staffMember, Date date);
	
	/**
	 * Returns health facilities.
	 * 
	 * @return health facilities that staff member is assigned to on date
	 */
	List<Facility> findHealthFaciliites();
}