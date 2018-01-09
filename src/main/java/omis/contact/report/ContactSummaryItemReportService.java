package omis.contact.report;

import omis.offender.domain.Offender;

/**
 * Contact Summary Item Report Service Interface.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 26, 2017)
 *@since OMIS 3.0
 *
 */
public interface ContactSummaryItemReportService {
	
	/**
	 * Returns a Contact Summary by the specified Offender.
	 * 
	 * @param offender - Offender
	 * @return contanctSummary - Contact Summary by the specified Offender
	 */
	ContactSummary findContactSummaryByOffender(Offender offender);
	
}
