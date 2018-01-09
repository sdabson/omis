package omis.criminalassociation.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for criminal association profile item.
 * @author Ryan JOhns
 * @version 0.1.0 (Mar 28, 2016)
 * @since OMIS 3.0 */
public interface CriminalAssociationProfileItemService {
	/** Finds criminal association count by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effectiveDate.
	 * @return count. */
	Integer findCriminalAssociationCountByOffenderAndDate(Offender offender, 
			Date effectiveDate);

}
