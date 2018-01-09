package omis.health.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.HealthRequest;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkReferral;
import omis.health.domain.LabWorkReferralStatusReason;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.location.domain.Location;
import omis.offender.domain.Offender;

/**
 * Lab work referral scheduler.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 7, 2014)
 * @since OMIS 3.0
 */
public interface LabWorkReferralScheduler {

	/**
	 * Schedules a new lab work referral with the specified properties.
	 * 
	 * @param facility facility
	 * @param offender offender
	 * @param sampleDate sample date
	 * @param notes scheduling notes
	 * @return lab work referral
	 * @throws DuplicateEntityFoundException duplicate entity found
	 */
	LabWorkReferral schedule(Facility facility, Offender offender, 
			Date sampleDate, String notes) throws DuplicateEntityFoundException;
	
	/**
	 * Schedules a new lab work referral from the specified health request with
	 * the specified date.
	 * 
	 * @param healthRequest health request
	 * @param sampleDate sample date
	 * @param notes scheduling notes
	 * @return lab work referral
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	LabWorkReferral scheduleFromRequest(HealthRequest healthRequest, 
			Date sampleDate, String notes) throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified lab work referral with the specified sample date.
	 * 
	 * @param labWorkReferral lab work referral
	 * @param sampleDate sample date
	 * @param notes scheduling notes
	 * @return lab work referral
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	LabWorkReferral update(LabWorkReferral labWorkReferral, Date sampleDate,
			String notes)
		throws DuplicateEntityFoundException;
	
	/**
	 * Creates a new lab work and adds it do the specified lab work referral.
	 * 
	 * @param labWorkReferral lab work referral
	 * @param category lab work category
	 * @param sampleDate sample date
	 * @param sampleLab sample lab
	 * @param sampleNotes sample notes
	 * @param sampleTaken sample taken
	 * @param order lab work order
	 * @param sampleRestrictions lab work sample restrictions
	 * @param schedulingNotes scheduling notes
	 * @return lab work
	 * @throws DuplicateEntityFoundException thrown when the a lab work with
	 * the same natural key properties are found.
	 */
	LabWork addLabWork(LabWorkReferral labWorkReferral, 
			LabWorkCategory category, Date sampleDate, Lab sampleLab, 
			String sampleNotes, Boolean sampleTaken, LabWorkOrder order,
			LabWorkSampleRestrictions sampleRestrictions, 
			String schedulingNotes) throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified lab work.
	 * 
	 * @param labWorkReferral lab work referral
	 * @param labWork lab work
	 * @param category lab work category
	 * @param sampleDate sample date
	 * @param sampleLab sample lab
	 * @param sampleNotes sample notes
	 * @param sampleTaken sample taken
	 * @param order lab work order
	 * @param sampleRestrictions lab work sample restrictions
	 * @param schedulingNotes scheduling notes
	 * @return lab work
	 * @throws DuplicateEntityFoundException thrown if a duplicate lab work or
	 * duplicate offender appointment association is found
	 */
	LabWork updateLabWork(LabWorkReferral labWorkReferral, LabWork labWork, 
			LabWorkCategory category, Date sampleDate, Lab sampleLab, 
			String sampleNotes, Boolean sampleTaken, LabWorkOrder order, 
			LabWorkSampleRestrictions sampleRestrictions, 
			String schedulingNotes) throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified lab work and dissociates it from the specified
	 * lab work referral.
	 * 
	 * @param labWorkReferral lab work referral
	 * @param labWork lab work
	 */
	void removeLabWork(LabWorkReferral labWorkReferral, LabWork labWork);
	
	/**
	 * Returns a list of labs for the specified location.
	 * 
	 * @param location location
	 * @return list of labs
	 */
	List<Lab> findLabsAtLocation(Location location);
	
	/**
	 * Returns a list of all labs.
	 * 
	 * @return list of labs.
	 */
	List<Lab> findLabs();
	
	/**
	 * Returns a list of all valid lab work categories.
	 * 
	 * @return valid lab work categories
	 */
	List<LabWorkCategory> findLabWorkCategories();
	
	/**
	 * Returns a list of provider assignments with the specified facility,
	 * whose date range contains the specified date.
	 * 
	 * @param facility facility
	 * @param date date
	 * @return list of provider assignments
	 */
	List<ProviderAssignment> findProviders(Facility facility, Date date);
	
	/**
	 * Returns a list of lab work that share the same offender appointment
	 * association as the specified lab work referral.
	 * 
	 * @param labWorkReferral lab work referral
	 * @return list of related lab work
	 */
	List<LabWork> findLabWorks(LabWorkReferral labWorkReferral);
	
	/**
	 * Removes the specified lab work referral.
	 * 
	 * @param labWorkReferral lab work referral
	 */
	void remove(LabWorkReferral labWorkReferral);

	/**
	 * Returns status reasons for rescheduling a lab work referral.
	 * 
	 * @return list of rescheduling status reasons
	 */
	List<LabWorkReferralStatusReason> findRescheduleStatusReasons();
}