package omis.family.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * Report service for family association.
 * 
 * @author Yidong Li
 * @version 0.1.0 (June 22, 2015)
 * @since OMIS 3.0
 */
public interface FamilyAssociationReportService {

	/**
	 * Returns a list of offender family associations.
	 * 
	 * @param offender offender
	 * @return list of offender family association
	 */

	List<FamilyAssociationSummary> findByOffender(Offender offender); 

}