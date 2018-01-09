package omis.supervisionfee.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.supervisionfee.domain.SupervisionFeeRequirementReason;

/**
 * Data access object for supervision fee requirement reason.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Oct 27, 2014)
 * @since OMIS 3.0
 */
public interface SupervisionFeeRequirementReasonDao 
extends GenericDao<SupervisionFeeRequirementReason> {
	
	/**
	 * Returns a list of all valid supervision fee requirement reasons.
	 * 
	 * @return list of valid supervision fee reasons.
	 */
	List<SupervisionFeeRequirementReason> 
	findAllSupervisionFeeRequirementReasons();
}
