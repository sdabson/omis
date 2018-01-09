package omis.health.service;

import java.util.Date;
import java.util.List;

import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.facility.domain.Unit;
import omis.facility.exception.FacilityLayoutException;
import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderTitle;
import omis.health.domain.ProviderUnitAssignment;
import omis.person.domain.Person;
import omis.staff.exception.StaffAssignmentNotFoundException;

/**
 * Manages provider assignments.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 7, 2014)
 * @since OMIS 3.0
 */
public interface ProviderAssignmentManager {

	/**
	 * Assigns a provider to a facility.
	 * 
	 * <p>The provider must have a staff assignment to the facility, otherwise
	 * a {@code StaffMemberNotFoundException} will be thrown.
	 * 
	 * @param provider provider
	 * @param facility facility
	 * @param dateRange date range
	 * @param title title
	 * @return assignment for provider to facility
	 * @throws DateConflictException if the provider is already assigned to
	 * the facility for the category during a date range that overlaps the
	 * date range specified
	 * @throws DuplicateEntityFoundException if the assignment exists
	 * @throws StaffAssignmentNotFoundException if the provider is not assigned
	 * to the facility
	 */
	ProviderAssignment assign(Person provider, Facility facility,
			DateRange dateRange, ProviderTitle title)
					throws DateConflictException, DuplicateEntityFoundException,
						StaffAssignmentNotFoundException;
	
	/**
	 * Assigns an external provider to a facility.
	 * 
	 * @param provider provider
	 * @param facility facility
	 * @param medicalFacility medical facility
	 * @param dateRange date range
	 * @param title title
	 * @return assignment for external provider to facility
	 * @throws DateConflictException if the provider is already assigned to
	 * the facility for the category during a date range that overlaps the
	 * date range specified
	 * @throws DuplicateEntityFoundException if the assignment exists
	 */
	ProviderAssignment assignExternal(Person provider, Facility facility,
			MedicalFacility medicalFacility, DateRange dateRange,
			ProviderTitle title)
					throws DateConflictException, DuplicateEntityFoundException;
	
	/**
	 * Assigns a contracted provider to a facility.
	 * 
	 * @param provider provider
	 * @param facility facility
	 * @param dateRange date range
	 * @param title title
	 * @return assignment of contracted provider to facility
	 * @throws DateConflictException if the provider is already assigned to
	 * the facility for the category during a date range that overlaps the
	 * date range specified
	 * @throws DuplicateEntityFoundException if the assignment exists
	 */
	ProviderAssignment assignContracted(Person provider, Facility facility,
			DateRange dateRange, ProviderTitle title)
		throws DateConflictException, DuplicateEntityFoundException;
	
	/**
	 * Updates an existing provider assignment with the specified provider 
	 * title and date range.
	 * 
	 * @param assignment provider assignment
	 * @param title provider title
	 * @param dateRange date range
	 * @return provider assignment
	 * @throws DateConflictException date conflict exception
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	ProviderAssignment update(ProviderAssignment assignment, 
			ProviderTitle title, DateRange dateRange)
		throws DateConflictException, DuplicateEntityFoundException;
	
	/**
	 * Assigns a provider to a unit for the duration of the assignment.
	 * 
	 * @param providerAssignment provider assignment
	 * @param unit unit
	 * @return assignment of provider to unit in facility of assignment
	 * @throws DuplicateEntityFoundException if the assignment exists
	 * @throws FacilityLayoutException if the unit is not part of the facility
	 * of the assignment
	 */
	ProviderUnitAssignment assignToUnit(ProviderAssignment providerAssignment,
			Unit unit)
		throws DuplicateEntityFoundException, FacilityLayoutException;
	
	/**
	 * Removes the provider assignment.
	 * 
	 * @param providerAssignment provider assignment to remove
	 */
	void removeAssignment(ProviderAssignment providerAssignment);
	
	/**
	 * Removes the provider unit assignment.
	 * 
	 * @param providerUnitAssignment provider unit assignment to remove 
	 */
	void removeUnitAssignment(ProviderUnitAssignment providerUnitAssignment);
	
	/**
	 * Returns provider assignments to facility on date.
	 * 
	 * @param facility facility
	 * @param date date
	 * @return provider assignments to facility on date
	 */
	List<ProviderAssignment> findAssignmentsToFacility(Facility facility,
			Date date);
	
	/**
	 * Returns unit assignment on date.
	 * 
	 * @param unit unit
	 * @param date date
	 * @return unit assignments on date
	 */
	List<ProviderUnitAssignment> findUnitAssignments(Unit unit, Date date);
	
	/**
	 * Returns provider titles.
	 * @return provider titles
	 */
	List<ProviderTitle> findTitles();
	
	/**
	 * Returns medical facilities.
	 * 
	 * @return medical facilities
	 */
	List<MedicalFacility> findMedicalFacilities();
	
	/**
	 * Returns assignments of external providers by medical facility on date.
	 * 
	 * @param medicalFacility medical facility
	 * @param date date
	 * @return assignments of external providers by medical facility on date
	 */
	List<ProviderAssignment> findAssignmentsByMedicalFacility(
			MedicalFacility medicalFacility, Date date);
}