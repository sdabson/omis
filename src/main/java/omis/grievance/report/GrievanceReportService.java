package omis.grievance.report;

import java.util.Date;
import java.util.List;

import omis.grievance.domain.GrievanceLocation;
import omis.grievance.domain.GrievanceSubject;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderSummary;

/**
 * Report service for grievances.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 22, 2015)
 * @since OMIS 3.0
 */
public interface GrievanceReportService {

	/**
	 * Returns summaries of grievances by offender.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return summaries of grievances by offender
	 */
	List<GrievanceSummary> summarizeByOffender(
			Offender offender, Date effectiveDate);
	
	/**
	 * Returns summaries of grievances by location.
	 * 
	 * @param location location
	 * @param effectiveDate effective date
	 * @return summaries of grievances by location
	 */
	List<GrievanceSummary> summarizeByLocation(
			GrievanceLocation location, Date effectiveDate);
	
	/**
	 * Returns summaries of offenders based on query.
	 * 
	 * @param query query
	 * @return summaries of offenders based on query
	 */
	List<OffenderSummary> searchOffenders(String query);
	
	/**
	 * Returns grievance locations
	 * 
	 * @return grievance locations
	 */
	List<GrievanceLocation> findGrievanceLocations();
	
	/**
	 * Returns grievance subjects.
	 * 
	 * @return grievance subjects
	 */
	List<GrievanceSubject> findGrievanceSubjects();
}