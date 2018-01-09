package omis.health.service;

import java.util.Date;
import java.util.List;

import omis.facility.domain.Facility;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.component.LabWorkResults;
import omis.health.exception.LabWorkResultsException;
import omis.health.exception.LabWorkSampleException;
import omis.offender.domain.Offender;

/**
 * Lab Work Results Service.
 *
 * @author Yidong Li
 * @version 0.1.0 (Nov 1, 2014)
 * @since OMIS 3.0
 */

public interface LabWorkResultsService {
	/**
	 * List all incomplete lab works by offender.
	 *
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @param sampleMustBeTaken confirm if sample must be taken or not 
	 * @return List of lab work           
	 */
	List<LabWork> findIncompleteByOffender(Offender offender, Facility facility, 
			Date startDate, Date endDate, Boolean sampleMustBeTaken);
	
	/**
	 * List all incomplete lab works by offender by search.
	 *
	 * @param offender offender
	 * @param facility facility
	 * @param startDate start date
	 * @param endDate end date
	 * @param sampleMustBeTaken  confirm if sample must be taken or not
	 * @return List of lab work           
	 */
	List<LabWork> findIncompleteByOffenderBySearch(Offender offender, 
			Facility facility, Date startDate, Date endDate, 
			Boolean sampleMustBeTaken);
	
	/**
	 * List all incomplete lab work by facility.
	 *
	 * @param facility facility
	 * @param startDate start date
	 * @param endDate end date
	 * @param sampleMustBeTaken confirm if sample must be taken or not 
	 * @return List of lab work           
	 */
	List<LabWork> findIncompleteByFacility(Facility facility, Date startDate, 
			Date endDate, Boolean sampleMustBeTaken);
	
	/**
	 * List results labs.
	 * 
	 * @return List of lab           
	 */
	List<Lab> findResultsLabs();
	
	/**
	 * Take lab work sample.
	 *
	 * @param labWork lab work
	 * @param sampleNotes sample notes
	 * @return lab work  
	 * @throws LabWorkSampleException if sample is taken       
	 */
	LabWork takeSample(LabWork labWork, String sampleNotes)
	throws LabWorkSampleException;
	
	/**
	 * Updates sample notes.
	 * 
	 * @param labWork lab work
	 * @param sampleNotes sample notes
	 * @return lab work with update sample notes
	 * @throws LabWorkSampleException if sample is not taken
	 */
	LabWork updateSampleNotes(LabWork labWork, String sampleNotes)
	throws LabWorkSampleException;
	
	/**
	 * Update lab work result.
	 *
	 * @param labWork lab work
	 * @param results lab work results 
	 * @return Lab work           
	 */
	LabWork updateResults(LabWork labWork, LabWorkResults results)
	throws LabWorkResultsException;
	
	/**
	 * Remove lab work.
	 *
	 * @param labWork lab work
	 */
	void remove(LabWork labWork);
}
