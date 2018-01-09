package omis.schedule.web.controller;

/**
 * Monthly recurring schedule type builder form.
 * @author Stephen Abson
 * @version 0.1.0 (Mar 23, 2012)
 * @since OMIS 3.0
 */
public class MonthlyRecurringScheduleBuilderTypeForm extends
		AbstractRecurringScheduleBuilderTypeForm {

	private static final long serialVersionUID = 1L;
	
	private Integer dayOfMonth;

	private String monthlyBuilderType;
	
	/**
	 * Returns the day of month.
	 * 
	 * @return day of month
	 */
	public Integer getDayOfMonth() {
		return this.dayOfMonth;
	}

	/**
	 * Sets the day of month.
	 * 
	 * @param dayOfMonth day of month
	 */
	public void setDayOfMonth(final Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	/**
	 * Returns the monthly builder type.
	 * 
	 * @return monthly builder type
	 */
	public String getMonthlyBuilderType() {
		return this.monthlyBuilderType;
	}

	/**
	 * Sets the monthly builder type.
	 * 
	 * @param monthlyBuilderType monthly builder type
	 */
	public void setMonthlyBuilderType(final String monthlyBuilderType) {
		this.monthlyBuilderType = monthlyBuilderType;
	}
}