package omis.condition.service;

import java.util.List;

import omis.condition.domain.Agreement;
import omis.offender.domain.Offender;

/**
 * Service for Agreements.
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @version 0.1.2 (July 7, 2016)
 * @since OMIS 3.0
 */
public interface AgreementService  {
	
		/**
	 * Returns Agreements by an Offender
	 * @param offender offender we want Agreements from
	 * @return Agreements relevant to Offender
	 */
	List<Agreement> findByOffender(Offender offender);
	


}
