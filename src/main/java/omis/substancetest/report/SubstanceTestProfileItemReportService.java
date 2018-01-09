package omis.substancetest.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for substance test profile.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public interface SubstanceTestProfileItemReportService {
	/** Finds last substance test date.
	 * @param offender - offender.
	 * @return date. */
	Date findLastSubstanceTestDateByOffender(Offender offender);

}
