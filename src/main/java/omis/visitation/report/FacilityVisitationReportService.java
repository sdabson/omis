package omis.visitation.report;

import java.util.Date;
import java.util.List;

import omis.facility.domain.Facility;
import omis.location.domain.Location;
import omis.offender.domain.Offender;

/**
 * Facility visitation report service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (March 7, 2017)
 * @since OMIS 3.0
 */
public interface FacilityVisitationReportService {

	/**
	 * Returns facility visitation offender summaries for the specified
	 * facility on the specified effective date.
	 * 
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @return list of facility visitation offender summaries
	 */
	List<FacilityVisitationOffenderSummary> summarizeFacilityVisitsOnDate(
			Facility facility, Date effectiveDate);
	
	/**
	 * Returns facility visitation offender summaries for the specified
	 * facility within the specified date range.
	 * 
	 * @param facility facility
	 * @param startDate start date
	 * @param endDate end date
	 * @return list of facility visitation offender summaries
	 */
	List<FacilityVisitationOffenderSummary> summarizeFacilityVisitsInDateRange(
			Facility facility, Date startDate, Date endDate);
	
	/**
	 * Return a list of visitor log summary for the specified offender on the
	 * specified date. As part of the visitor log summary, this will also check
	 * to see if any visits are still open with the specified date time.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return list of visitor log summary
	 */
	List<VisitSummary> findVisitSummariesByOffenderOnDate(
			Offender offender, Date date);

	/**
	 * Return a list of visit summaries for the specified offender that
	 * took place between the specified start and end date. As part of the 
	 * visitor log summary, this will also check to see if any visits are still
	 * open with the specified date time.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @return list of visit summaries
	 */
	List<VisitSummary> findVisitSummariesByOffenderInDateRange(
			Offender offender, Date startDate, Date endDate);
	

	/**
	 * Returns offenders currently visiting within the specified location
	 * on the specified date.
	 * 
	 * @param location location
	 * @param date date
	 * @return list of visiting offenders
	 */
	List<Offender> findVisitingOffendersByLocationOnDate(Location location,
			Date date);

	/**
	 * Returns the housing unit name for the specified offender on the
	 * specified date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return name of housing unit 
	 */
	String findOffenderHousingUnit(Offender offender, Date date);

	/**
	 * Returns the ID of the photo currently used as the mugshot for the
	 * specified offender.
	 * 
	 * @param offender offender
	 * @return photo id
	 */
	Long findMugShotIdByOffender(Offender offender);
}