package omis.paroleboardcondition.report;

import omis.offender.domain.Offender;

/**
 * Profile item service for parole board agreement.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Jan 18, 2018)
 * @since OMIS 3.0
 */
public interface ParoleBoardAgreementProfileItemService {

	/** Finds parole board agreement count by offender.
	 * @param offender - offender
	 * @return count
	 */
	Long findParoleBoardAgreementCountByOffender(Offender offender);
	
}
