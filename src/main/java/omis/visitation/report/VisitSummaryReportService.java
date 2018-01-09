package omis.visitation.report;

import java.util.Date;
import java.util.List;

import omis.facility.domain.Facility;
import omis.offender.domain.Offender;

/**
 * Report service for visitor log.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 30, 2013)
 * @since OMIS 3.0
 */
public interface VisitSummaryReportService {

	/**
	 * Return a list of visitor log summary for the specified offender on the
	 * specified date. As part of the visitor log summary, this will also check
	 * to see if any visits are still open with the specified date time.
	 * 
	 * @param offender offender
	 * @param date date
	 * @param dateTime date and time
	 * @return list of visitor log summary
	 */
	List<VisitSummary> findVisitSummariesByOffenderOnDate(
			Offender offender, Date date, Date dateTime);

	/**
	 * Return a list of visitor log summary for the specified offender that
	 * took place between the specified start and end date. As part of the 
	 * visitor log summary, this will also check to see if any visits are still
	 * open with the specified date time.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @param dateTime date and time
	 * @return list of visitor log summary
	 */
	List<VisitSummary> findVisitSummariesByOffenderInDateRange(
			Offender offender, Date startDate, Date endDate, Date dateTime);

	/**
	 * Return a list of visitor log summary for the specified facility that 
	 * took place on the specified date. As part of the visitor Log summary,
	 * this will also check to see if any of the visits are still open with the
	 * specified date time.
	 * 
	 * @param facility facility
	 * @param date date
	 * @param dateTime date time
	 * @return list of visitor log summary
	 */
	List<VisitSummary> findVisitSummariesByFacilityOnDate(
			Facility facility, Date date, Date dateTime);

	/**
	 * Return visit summary for the specified facility that took place between
	 * the specified start and end date.
	 * 
	 * @param facility facility
	 * @param startDate start date
	 * @param endDate end date
	 * @return list of visit summaries
	 */
	List<VisitSummary> findSummariesByFacilityInRange(Facility facility,
			Date startDate, Date endDate);
}