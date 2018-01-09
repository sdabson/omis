package omis.locationterm.report.delegate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.datatype.DateRange;
import omis.locationterm.dao.LocationReasonTermDao;
import omis.locationterm.dao.LocationTermDao;
import omis.locationterm.domain.LocationReasonTerm;
import omis.locationterm.domain.LocationTerm;
import omis.locationterm.report.LocationTermSummary;
import omis.offender.domain.Offender;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Delegate for location term summaries.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Feb 9, 2015)
 * @since OMIS 3.0
 */
public class LocationTermSummaryDelegate {

	private final LocationTermDao locationTermDao;
	
	private final LocationReasonTermDao locationReasonTermDao;
	
	/**
	 * Instantiates a delegate for location term summaries.
	 * 
	 * @param locationTermDao
	 * @param locationReasonTermDao
	 */
	public LocationTermSummaryDelegate(
			final LocationTermDao locationTermDao,
			final LocationReasonTermDao locationReasonTermDao) {
		this.locationTermDao = locationTermDao;
		this.locationReasonTermDao = locationReasonTermDao;
	}
	
	/**
	 * Returns location term summaries by supervisory organization for
	 * offender between dates.
	 * 
	 * @param supervisoryOrganizaton supervisory organization
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @return location term summaries by supervisory organization for offender
	 * between dates
	 */
	public List<LocationTermSummary>
	findBySupervisoryOrganizationForOffenderBetweenDates(
			final SupervisoryOrganization supervisoryOrganizaton,
			final Offender offender, final Date startDate, final Date endDate,
			final Date effectiveDate) {
		List<LocationTermSummary> summaries
			= new ArrayList<LocationTermSummary>();
		List<LocationTerm> locationTerms = this.locationTermDao
				.findBySupervisoryOrganizationBetweenDates(
						supervisoryOrganizaton, offender, startDate, endDate);
		for (LocationTerm locationTerm : locationTerms) {
			List<LocationReasonTerm> reasonTerms
				= this.locationReasonTermDao.findByLocationTerm(locationTerm);
			String singleReasonName;
			if (reasonTerms.size() == 1) {
				singleReasonName = reasonTerms.get(0).getReason().getName();
			} else {
				singleReasonName = null;
			}
			LocationTermSummary summary = new LocationTermSummary(
					locationTerm.getId(),
					locationTerm.getOffender().getName().getLastName(),
					locationTerm.getOffender().getName().getFirstName(),
					locationTerm.getOffender().getName().getMiddleName(),
					locationTerm.getOffender().getName().getSuffix(),
					locationTerm.getOffender().getOffenderNumber(),
					locationTerm.getLocation().getOrganization().getName(),
					DateRange.getStartDate(locationTerm.getDateRange()),
					DateRange.getEndDate(locationTerm.getDateRange()),
					Long.valueOf(reasonTerms.size()),
					singleReasonName, effectiveDate);
			summaries.add(summary);
		}
		return summaries;
	}

	/**
	 * Returns location term summaries by offender between dates.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @param effectiveDate effective date
	 * @return location term summaries by offender between dates
	 */
	public List<LocationTermSummary> findForOffenderBetweenDates(
			final Offender offender, final Date startDate,
			final Date endDate, final Date effectiveDate) {
		List<LocationTermSummary> summaries
			= new ArrayList<LocationTermSummary>();
		List<LocationTerm> locationTerms = this.locationTermDao
				.findByOffenderBetweenDates(
						offender, startDate, endDate);
		for (LocationTerm locationTerm : locationTerms) {
			List<LocationReasonTerm> reasonTerms
				= this.locationReasonTermDao.findByLocationTerm(locationTerm);
			String singleReasonName;
			if (reasonTerms.size() == 1) {
				singleReasonName = reasonTerms.get(0).getReason().getName();
			} else {
				singleReasonName = null;
			}
			LocationTermSummary summary = new LocationTermSummary(
					locationTerm.getId(),
					locationTerm.getOffender().getName().getLastName(),
					locationTerm.getOffender().getName().getFirstName(),
					locationTerm.getOffender().getName().getMiddleName(),
					locationTerm.getOffender().getName().getSuffix(),
					locationTerm.getOffender().getOffenderNumber(),
					locationTerm.getLocation().getOrganization().getName(),
					DateRange.getStartDate(locationTerm.getDateRange()),
					DateRange.getEndDate(locationTerm.getDateRange()),
					Long.valueOf(reasonTerms.size()),
					singleReasonName,
					effectiveDate);
			summaries.add(summary);
		}
		return summaries;
	}
}