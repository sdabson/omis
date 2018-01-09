package omis.paroleboardmember.report;

import java.util.Date;
import java.util.List;

/**
 * Parole board member summary report service.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public interface ParoleBoardMemberSummaryReportService {

	/**
	 * Finds all parole board members for an effective date.
	 * 
	 * @param effectiveDate effective date
	 * @return list of parole board members
	 */
	List<ParoleBoardMemberSummary> findByDate(Date effectiveDate);
	
	/**
	 * Finds all parole board members with the specified start and end dates.
	 *
	 * @param startDate start date
	 * @param endDate end date
	 * @return list of parole board members
	 */
	List<ParoleBoardMemberSummary> findByDates(Date startDate, Date endDate);
}
