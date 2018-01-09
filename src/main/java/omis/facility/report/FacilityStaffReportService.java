package omis.facility.report;

import java.util.Date;
import java.util.List;

import omis.facility.domain.Facility;
import omis.person.domain.Person;

/** Facility staff assignment report service.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 8, 2014)
 * @since OMIS 3.0
 * @deprecated use module specific report service for facility/staff lookup;
 * consider removal - SA
 */
@Deprecated
public interface FacilityStaffReportService {

	/** find facilities by staff member.
	 * @param staff person.
	 * @param date date.
	 * @return list of facilities. */
	List<Facility> findFacilitiesByStaff(Person staff, Date date);

	/** find all facilities.
	 * @return list of facilities. */
	List<Facility> findAllFacilities();
}
