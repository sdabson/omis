package omis.supervisionfee.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.supervisionfee.domain.MonthlySupervisionFee;

/**
 * Data access object for monthly supervision fee.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 26, 2014)
 * @since OMIS 3.0
 */
public interface MonthlySupervisionFeeDao 
				extends GenericDao<MonthlySupervisionFee> {

	/**
	 * Returns a list of monthly supervision fees for the specified 
	 * offender  that have a start date and/or end date that fall 
	 * within the specified dates.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @return list monthly supervision fees matching the criteria
	 */
	List <MonthlySupervisionFee> findByDateRange(Offender offender,
						 		Date startDate, Date endDate);

	 /**
	  * Returns a list of monthly supervision fees excluding the one in view.
	  * 
	  * @param offender offender
	  * @param startDate start date
	  * @param endDate end date
	  * @param supervisionFee supervision fee
	  * @return list of monthly supervision fees matching the criteria
	  */
	List <MonthlySupervisionFee> findByDateRangeExcluding(Offender offender, 
			 					Date startDate, Date endDate, 
			 					MonthlySupervisionFee supervisionFee);
}