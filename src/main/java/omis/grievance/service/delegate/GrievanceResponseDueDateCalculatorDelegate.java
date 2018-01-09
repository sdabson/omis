package omis.grievance.service.delegate;

import java.util.Date;

import omis.util.DateManipulator;

/**
 * Delegate to calculate grievance response due date.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class GrievanceResponseDueDateCalculatorDelegate {
	
	private final Integer daysUntilResponseDue;

	/**
	 * Instantiates delegate to calculate grievance response due date.
	 * 
	 * @param daysUntilResponseDue days until response due
	 */
	public GrievanceResponseDueDateCalculatorDelegate(
			final Integer daysUntilResponseDue) {
		this.daysUntilResponseDue = daysUntilResponseDue;
	}
	
	/**
	 * Returns response due date calculated from opened date.
	 * 
	 * @param openedDate opened date
	 * @return response due date calculated from opened date 
	 */
	public Date calculate(final Date openedDate) {
		DateManipulator manipulator
			= new DateManipulator(openedDate);
		manipulator.changeDate(this.daysUntilResponseDue);
		return manipulator.getDate();
	}
}