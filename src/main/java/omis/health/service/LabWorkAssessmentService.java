package omis.health.service;

import java.util.Date;

import omis.health.domain.LabWork;

/**
 * Lab work assessment service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 2, 2014)
 * @since OMIS 3.0
 */
public interface LabWorkAssessmentService {

	/**
	 * Assesses the specified lab work.
	 * 
	 * @param labWork lab work
	 * @param timeKept time kept
	 * @param notes notes
	 */
	void assess(LabWork labWork, Date timeKept, String notes);
}