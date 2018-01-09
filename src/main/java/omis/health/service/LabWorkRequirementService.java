package omis.health.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.HealthRequest;
import omis.health.domain.Lab;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkRequirementRequest;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.location.domain.Location;

/**
 * Service for lab work requirements.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 4, 2014)
 * @since OMIS 3.0
 */
public interface LabWorkRequirementService {

	/**
	 * Requests a lab work requirement.
	 * 
	 * @param healthRequest health request
	 * @param category category
	 * @param order order
	 * @param sampleDate sample date
	 * @param sampleLab sample lab
	 * @param resultsLab results lab
	 * @param sampleRestrictions sample restrictions
	 * @param schedulingNotes scheduling notes
	 * @return request for lab work requirement
	 * @throws DuplicateEntityFoundException if the request exists
	 */
	LabWorkRequirementRequest request(HealthRequest healthRequest,
			LabWorkOrder order, LabWorkCategory category, Date sampleDate,
			Lab sampleLab, Lab resultsLab,
			LabWorkSampleRestrictions sampleRestrictions,
			String schedulingNotes)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates the lab work requirement request.
	 * 
	 * @param request request to update
	 * @param order order
	 * @param category category
	 * @param sampleDate sample date
	 * @param sampleLab sample lab
	 * @param resultsLab results lab
	 * @param schedulingNotes scheduling notes
	 * @param sampleRestrictions sample restrictions
	 * @return updated request for lab work requirement
	 * @throws DuplicateEntityFoundException if the request exists
	 */
	LabWorkRequirementRequest update(LabWorkRequirementRequest request,
			LabWorkOrder order, LabWorkCategory category, Date sampleDate,
			Lab sampleLab, Lab resultsLab,
			LabWorkSampleRestrictions sampleRestrictions,
			String schedulingNotes)
					throws DuplicateEntityFoundException;
	
	/**
	 * Returns lab work requests required for the health request.
	 * 
	 * @param healthRequest health request
	 * @return lab work requests required for health request
	 */
	List<LabWorkRequirementRequest> findRequestsByHealthRequest(
			HealthRequest healthRequest);
	
	/**
	 * Removes the lab work requirement request.
	 * 
	 * @param request request to remove
	 */
	void remove(LabWorkRequirementRequest request);
	
	/**
	 * Returns the categories of lab work.
	 * 
	 * @return categories of lab work
	 */
	List<LabWorkCategory> findLabWorkCategories();
	
	/**
	 * Returns of labs for location.
	 * 
	 * @return labs for location 
	 */
	List<Lab> findLabsForLocation(Location location);
	
	/**
	 * Returns providers by assignment to facility on date.
	 * 
	 * @param facility facility
	 * @param date date
	 * @return provider providers by assignment to facility on date
	 */
	List<ProviderAssignment> findProviders(Facility facility, Date date);
}