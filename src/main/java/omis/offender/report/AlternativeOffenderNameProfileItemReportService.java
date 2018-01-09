package omis.offender.report;

import java.util.Date;

import omis.offender.domain.Offender;

/**
 * Report service for alternative offender name profile items.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface AlternativeOffenderNameProfileItemReportService {

	/**
	 * Returns count of alternative offender names effective on date.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return count of alternative offender names effective on date
	 */
	Integer findAlternativeOffenderNameCountByOffenderAndDate(
			Offender offender, Date effectiveDate);
}