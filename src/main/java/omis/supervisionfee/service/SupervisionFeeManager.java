package omis.supervisionfee.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import omis.court.domain.Court;
import omis.exception.DateConflictException;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.supervisionfee.domain.MonthlySupervisionFee;
import omis.supervisionfee.domain.MonthlySupervisionFeeAuthorityCategory;
import omis.supervisionfee.domain.SupervisionFeeRequirement;
import omis.supervisionfee.domain.SupervisionFeeRequirementReason;

/**
 * Supervision fee manager.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 27, 2014)
 * @since OMIS 3.0
 */
public interface SupervisionFeeManager {

	/**
	 * Imposes a monthly supervision fee on an offender and start date.
	 * 
	 * @param offender offender
	 * @param fee fee
	 * @param startDate startDate
	 * @param endDate end date
	 * @param category category
	 * @param comment comment
	 * @return monthly supervision fee for offender
	 * @throws DateConflictException date conflict exception
	 */	
	MonthlySupervisionFee imposeMonthlySupervisionFee(Offender offender, 
					BigDecimal fee, Date startDate, Date endDate, 
					MonthlySupervisionFeeAuthorityCategory category, 
					String comment)
		throws DateConflictException;
	
	/**
	 * Ends the imposed monthly supervision fee.
	 * 
	 * @param monthlySupervisionFee monthly supervision fee
	 * @param endDate end date
	 * @return monthly supervision fee end date
	 * @throws DateConflictException date conflict exception
	 */
	MonthlySupervisionFee endMonthlySupervisionFee(
					MonthlySupervisionFee monthlySupervisionFee, Date endDate)
		throws DateConflictException;
	/**
	 * Removes the monthly supervision fee.
	 * 
	 * @param monthlySupervisionFee monthly supervision fee to remove
	 */
	void removeMonthlySupervisionFee(MonthlySupervisionFee 
					monthlySupervisionFee);
	
	/**
	 * Requirements for supervision fee, the person imposing supervision fee 
	 * start date and comments.
	 * 
	 * @param monthlySupervisionFee monthly supervision fee
	 * @param fee fee
	 * @param officer officer
	 * @param startDate start date
	 * @param endDate end date
	 * @param comments comments
	 * @param reason reason
	 * @return requirements for imposed supervision fee
	 * @throws DateConflictException date conflict exception
	 */
	SupervisionFeeRequirement imposeSupervisionFeeRequirement(
					MonthlySupervisionFee monthlySupervisionFee, BigDecimal fee,
					Person officer, Date startDate, Date endDate, 
					String comments, SupervisionFeeRequirementReason reason)
		throws DateConflictException;
	
	/**
	 * Requirement for supervision fee, the court imposing supervision fee
	 * and start date.
	 * 
	 * @param monthlySupervisionFee supervision fee
	 * @param fee fee
	 * @param court court
	 * @param startDate start date
	 * @param endDate end date
	 * @param comments comments
	 * @param reason reason
	 * @return requirements for imposed supervision fee
	 * @throws DateConflictException date conflict exception
	 */
	SupervisionFeeRequirement imposeSupervisionFeeRequirement(
					MonthlySupervisionFee monthlySupervisionFee, BigDecimal fee,
					Court court, Date startDate, Date endDate, String comments,
					SupervisionFeeRequirementReason reason)
		throws DateConflictException;
	
	/**
	 * Ends the supervision fee requirement.
	 * 
	 * @param supervisionFeeRequirement supervision fee requirement
	 * @param endDate end date
	 * @return supervision fee requirement end date
	 * @throws DateConflictException date conflict exception
	 */
	SupervisionFeeRequirement endSupervisionFeeRequirement(
					SupervisionFeeRequirement supervisionFeeRequirement, 
					Date endDate)
		throws DateConflictException;
	
	/**
	 * Remove the supervision fee requirement.
	 * 
	 * @param supervisionFeeRequirement supervision fee requirement
	 */
	void removeSupervisionFeeRequirement(
					SupervisionFeeRequirement supervisionFeeRequirement);
	
	/**
	 * Update the monthly supervision fee. 
	 * 
	 * @param monthlySupervisionFee monthly supervision fee
	 * @param fee fee
	 * @param startDate start date
	 * @param endDate end date
	 * @param comments comments
	 * @return monthly supervision fee
	 * @throws DateConflictException date conflict exception
	 */
	MonthlySupervisionFee updateMonthlySupervisionFee(
					MonthlySupervisionFee monthlySupervisionFee, BigDecimal fee,
					Date startDate, Date endDate, String comments)
		throws DateConflictException;
	
	/**
	 * Update the supervision fee requirement.
	 * 
	 * @param supervisionFeeRequirement supervision fee requirement
	 * @param fee fee
	 * @param reason reason
	 * @param startDate start date
	 * @param endDate end date
	 * @param comments comments
	 * @return supervision fee requirement
	 * @throws DateConflictException date conflict exception
	 */
	SupervisionFeeRequirement updateSupervisionFeeRequirement(
					SupervisionFeeRequirement supervisionFeeRequirement, 
					BigDecimal fee, SupervisionFeeRequirementReason reason, 
					Date startDate, Date endDate, String comments)
		throws DateConflictException;
	
	/**
	 * Returns supervision fee requirements by monthly supervision fee.
	 * 
	 * @param monthlySupervisionFee monthly supervision fee
	 * @return list of supervision fee requirements
	 */
	List<SupervisionFeeRequirement> 
					findSupervisionFeeRequirementsByMonthlySupervisionFee(
								MonthlySupervisionFee monthlySupervisionFee);
	/**
	 * Returns valid supervision fee requirement reasons.
	 * 
	 * @return valid supervision fee requirement reasons
	 */
	List<SupervisionFeeRequirementReason> 
					findAllSupervisionFeeRequirementReasons();
	
	/**
	 * Returns courts.
	 * 
	 * @return list of courts
	 */
	List<Court> findAllCourts();	
}
