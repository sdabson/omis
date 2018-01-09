package omis.placement.report;

import java.util.Date;
import java.util.List;

import omis.locationterm.report.LocationTermSummary;
import omis.offender.domain.Offender;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.domain.SupervisoryOrganizationTerm;
import omis.supervision.report.CorrectionalStatusTermSummary;
import omis.supervision.report.SupervisoryOrganizationTermSummary;

/**
 * Report service for placement. 
 *
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.0.1 (Nov 17, 2014)
 * @since OMIS 3.0
 */
public interface PlacementReportService {

	/**
	 * Returns placement summary for offender effective on date.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return placement summary for offender effective on date
	 */
	PlacementSummary findPlacementSummary(Offender offender,
			Date effectiveDate);
	
	/**
	 * Returns summaries of correctional status terms for offender.
	 * 
	 * <p>The start and end dates are optional.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @param effectiveDate effective date
	 * @return summaries of correctional status terms for offender
	 */
	List<CorrectionalStatusTermSummary> findCorrectionalStatusTermSummaries(
			Offender offender, Date startDate, Date endDate,
			Date effectiveDate);
	
	/**
	 * Returns summaries of supervisory organization terms for offender.
	 * 
	 * <p>The start and end dates are optional.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @param effectiveDate effective date
	 * @return summaries of supervisory organization terms for offender
	 */
	List<SupervisoryOrganizationTermSummary>
	findSupervisoryOrganizationTermSummaries(Offender offender,
			Date startDate, Date endDate, Date effectiveDate);
	
	/**
	 * Returns summaries of location terms during supervisory organization term.
	 * 
	 * @param supervisoryOrganizationTerm supervisory organization term
	 * @param effectiveDate effective date
	 * @return summaries of locations terms during supervisory organization term
	 */
	List<LocationTermSummary>
	findLocationTermSummariesDuringSupervisoryOrganizationTerm(
			SupervisoryOrganizationTerm supervisoryOrganizationTerm,
			Date effectiveDate);

	/**
	 * Returns correctional status change summaries allowed for the correctional
	 * status of the offender on the effective date.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return allowed correctional status change summaries for offender on date
	 */
	List<AllowedCorrectionalStatusChangeSummary>
	findAllowedCorrectionalStatusChangeSummaries(
			Offender offender, Date effectiveDate);
	
	/**
	 * Returns allowed change summaries from correctional status.
	 * 
	 * @param correctionalStatus correctional status
	 * @return allowed change summaries from correctional status
	 */
	List<AllowedCorrectionalStatusChangeSummary>
	findAllowedChangeSummariesFromCorrectionalStatus(
			CorrectionalStatus correctionalStatus);
	
	/**
	 * Returns location term change action summaries for correctional status.
	 * 
	 * @param correctionalStatus correctional status
	 * @return location term change action summaries for correctional status
	 */
	List<LocationTermChangeActionSummary>
	findLocationTermChangeActionSummariesForCorrectionalStatus(
			CorrectionalStatus correctionalStatus);
	
	/**
	 * Returns whether offender has active placement on date.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return whether offender has active placement on date
	 */
	boolean hasActivePlacementTerm(Offender offender, Date effectiveDate);
	
	/** Returns summary of offenders placement by offender and date.
	 * * TODO: We'll want to return to the implementation of this when we are 
	 * getting ready to bring placement to production, This is using a 
	 * summary that is tailored to the information exposed via OMIS 2.
	 * @param offender - offender.
	 * @param effectiveDate - effective date. 
	 * @return offender placement summary. */
	OffenderPlacementSummary findOffenderPlacementSummaryByOffender(
			 	Offender offender, Date effectiveDate);
	
	/** Returns summary of offenders placement by offender and date.
	 * * TODO: We'll want to return to the implementation of this when we are 
	 * getting ready to bring placement to production, This is using a 
	 * summary that is tailored to the information exposed via OMIS 2.
	 * @param offender - offender.
	 * @param effectiveDate - effective date. 
	 * @return offender placement summary. */
	LegacyOffenderPlacementSummary findLegacyOffenderPlacementSummaryByOffender(
			 	Offender offender, Date effectiveDate);
	
	/**
	 * Returns placement term change action summaries for supervisory
	 * organization change.
	 * 
	 * @param correctionalStatus correctional status
	 * @param supervisoryOrganization supervisory organization
	 * @return placement term change action summaries for supervisory
	 * organization change
	 */
	List<PlacementTermChangeActionSummary>
	findPlacementTermChangeActionSummariesForSupervisoryOrganizationChange(
			CorrectionalStatus correctionalStatus,
			SupervisoryOrganization supervisoryOrganization);
}	