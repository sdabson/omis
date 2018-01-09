package omis.visitation.report;

import java.util.Date;
import java.util.List;

import omis.facility.domain.Facility;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.person.report.AlternateNameSummary;

/**
 * Report service for visitor list.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 24, 2013)
 * @since OMIS 3.0
 */
public interface VisitationAssociationSummaryReportService {

	/**
	 * Return a list of visitor summary for the specified offender on the 
	 * specified date. The date should be specified with the time of day desired
	 * to decide on the currentlyVisiting property of each visitor summary.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return list of visitor summary
	 */
	List<VisitationAssociationSummary> summarizeVisitationAssociations(
			Offender offender, Date date);

	/**
	 * Return a list of visitor summary for the specified facility on the 
	 * specified date. The date should be specified with the time of day desired
	 * to decide on the currentlyVisiting property of each visitor summary.
	 * 
	 * @param facility facility
	 * @param dateTime date time
	 * @return list of visitor summary
	 */
	List<VisitationAssociationSummary>
		summarizeVisitationAssociationsByFacility(Facility facility,
				Date dateTime);

	/**
	 * Summarizes visitation associations for the specified offender within the
	 * specified start date and end date.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @return list of visitation association summaries
	 */
	List<VisitationAssociationSummary> summarizeVisitationAssociationsInRange(
			Offender offender, Date startDate, Date endDate);
	
	List<AlternateNameSummary>  summarizeAlternativeNames(Person person, 
			Date effectiveDate);
}