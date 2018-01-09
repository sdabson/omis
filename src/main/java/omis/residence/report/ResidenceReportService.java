package omis.residence.report;

import java.util.Date;
import java.util.List;

import omis.offender.domain.Offender;
import omis.residence.domain.ResidenceTerm;

/**
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb 20, 2015)
 * @since  OMIS 3.0
 */
public interface ResidenceReportService {
	
	/**
	 * Finds summary of residences by offender.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return residence summary
	 */
	List<ResidenceSummary> findByOffender(Offender offender, 
			Date effectiveDate);
	
	/**
	 * Summarize residence term.
	 *
	 *
	 * @param residenceTerm residence term
	 * @return residence term
	 */
	ResidenceSummary summarizeByResidenceTerm(ResidenceTerm residenceTerm);
}