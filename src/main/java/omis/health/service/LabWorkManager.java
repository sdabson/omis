package omis.health.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkResults;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.location.domain.Location;
import omis.offender.domain.Offender;

/**
 * Lab work manager.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 5, 2014)
 * @since OMIS 3.0
 */
public interface LabWorkManager {
	
	/**
	 * Schedules a lab work for the specified offender with the specified
	 * properties.
	 * 
	 * @param facility facility
	 * @param offender offender
	 * @param category lab work category
	 * @param sampleLab sample lab
	 * @param sampleDate sample date
	 * @param sampleTaken sample taken
	 * @param sampleNotes sample notes
	 * @param results lab work results
	 * @param order lab work order
	 * @param sampleRestrictions lab work sample restrictions
	 * @param schedulingNotes scheduling notes
	 * @return scheduled lab work
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	LabWork scheduleLabWork(Facility facility, Offender offender, 
			LabWorkCategory category, Lab sampleLab, Date sampleDate, 
			Boolean sampleTaken, String sampleNotes, LabWorkResults results, 
			LabWorkOrder order, LabWorkSampleRestrictions sampleRestrictions, 
			String schedulingNotes) throws DuplicateEntityFoundException;
	
	/**
	 * Request to reschedule the specified lab work.
	 * 
	 * @param labWork lab work
	 * @return lab work
	 */
	LabWork requestReschedule(LabWork labWork);
	
	/**
	 * Creates a new lab work item using the specified properties. The specified
	 * lab work item is updated to change the value of 
	 * {@code rescheduleRequired} to {@code true}.
	 * 
	 * @param labWork lab work
	 * @param date date
	 * @param starttime start time
	 * @param notes notes
	 * @return lab work
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	LabWork reschedule(LabWork labWork, Date date, Date starttime, 
			String notes)
		throws DuplicateEntityFoundException;
	
	/**
	 * Returns a list of valid labs.
	 * 
	 * @return list of labs
	 */
	List<Lab> findLabs();
	
	/**
	 * Returns a list of valid lab work categories.
	 * 
	 * @return list of lab work categories
	 */
	List<LabWorkCategory> findLabWorkCategories();
	
	/**
	 * Returns a list of valid labs with the specific location.
	 * 
	 * @param location location
	 * @return list of labs
	 */
	List<Lab> findLabsAtLocation(Location location);
	
	/**
	 * Updates an existing lab work with the specified properties.
	 * 
	 * @param labWork lab work
	 * @param labWorkCategory lab work category
	 * @param sampleLab sample lab
	 * @param sampleDate sample date
	 * @param sampleTaken sample taken
	 * @param sampleNotes sample notes
	 * @param results results
	 * @param order order
	 * @param sampleRestrictions sample restrictions
	 * @param schedulingNotes scheduling notes
	 * @return updated lab work
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	LabWork updateLabWork(LabWork labWork, LabWorkCategory labWorkCategory, 
			Lab sampleLab, Date sampleDate, Boolean sampleTaken, 
			String sampleNotes, LabWorkResults results, LabWorkOrder order, 
			LabWorkSampleRestrictions sampleRestrictions, 
			String schedulingNotes)
		throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified lab work.
	 * 
	 * @param labWork lab work
	 */
	void removeLabWork(LabWork labWork);
	
	/**
	 * Returns a list of provider assignments for the specified facility, whose
	 * date range includes the specified date.
	 * 
	 * @param facility facility
	 * @param date date
	 * @return list of provider assignments
	 */
	List<ProviderAssignment> findProviders(Facility facility, Date date);

	/**
	 * Returns the facility that is at the specified location.
	 * 
	 * @param location location
	 * @return facility 
	 */
	Facility findFacility(Location location);
	
	/**
	 * Returns all the lab works for the specified offender that are
	 * to take place on the specified date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return lab works 
	 */
	List<LabWork> findLabWorks(Offender offender, Date date);
}