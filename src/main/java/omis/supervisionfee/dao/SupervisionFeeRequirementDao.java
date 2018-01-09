package omis.supervisionfee.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.supervisionfee.domain.MonthlySupervisionFee;
import omis.supervisionfee.domain.SupervisionFeeRequirement;

/**
 * Data access object for supervision fee requirement.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 26, 2014)
 * @since OMIS 3.0
 */
public interface SupervisionFeeRequirementDao  
				extends GenericDao<SupervisionFeeRequirement> {
	
	/**
	 * Returns a list of all the supervision fee requirements for the specified 
	 * monthly supervision fee.
	 * 
	 * @param monthlySupervisionFee monthly supervision fee
	 * @return list of supervision fee requirements
	 */
	List<SupervisionFeeRequirement> findByMonthlySupervisionFee(
					MonthlySupervisionFee monthlySupervisionFee);
	
	/**
	 * Returns a list of all the supervision fee requirements excluding the 
	 * requirement in view.
	 * 
	 * @param startDate start date
	 * @param endDate end date
	 * @param monthlySupervisionFee monthly supervision fee
	 * @param feeRequirement fee requirement
	 * @return list of supervision fee requirements
	 */
	List <SupervisionFeeRequirement> findByDateRangeExcluding(
					Date startDate, Date endDate, MonthlySupervisionFee 
					monthlySupervisionFee, 
					SupervisionFeeRequirement feeRequirement);
	
	/**
	 * Returns a list of all the supervision fee requirements by date range.
	 * 
	 * @param startDate start date
	 * @param endDate end date
	 * @param monthlySupervisionFee monthly supervision fee
	 * @return list of supervision fee requirements
	 */
	List <SupervisionFeeRequirement> findByDateRange(
					Date startDate, Date endDate, MonthlySupervisionFee 
					monthlySupervisionFee);
	
	/**
	 * Returns a list of all the supervision fee requirements not in the 
	 * specified monthly supervision fee date range.
	 * 
	 * @param startDate start date
	 * @param endDate end date
	 * @param monthlySupervisionFee monthly supervision fee
	 * @return list of supervision fee requirements
	 */
	List <SupervisionFeeRequirement> findWithinMonthlySupervisionFeeDateRange(
					Date startDate, Date endDate,
					MonthlySupervisionFee monthlySupervisionFee);
}