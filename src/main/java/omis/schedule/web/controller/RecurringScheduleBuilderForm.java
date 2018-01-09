package omis.schedule.web.controller;

import java.io.Serializable;

import omis.datatype.DateRange;


/**
 * Form the information from which a recurring schedule can be built.
 * @author Stephen Abson
 * @version 0.1.0 (Mar 23, 2012)
 * @since OMIS 3.0
 */
public class RecurringScheduleBuilderForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DateRange eventDateRange;
	
	private DateRange recurrenceDateRange;
	
	private AbstractRecurringScheduleBuilderTypeForm recurringScheduleBuilderTypeForm;
	
	private String determineEndDateBy = "endAfterOccurrences";
	
	private Integer endAfterOccurrences;
	
	private String builderType;

	/** Instantiates a default recurring schedule builder form. */
	public RecurringScheduleBuilderForm() {
		// Do nothing
	}
	
	public DateRange getEventDateRange() {
		return this.eventDateRange;
	}

	public void setEventDateRange(final DateRange eventDateRange) {
		this.eventDateRange = eventDateRange;
	}

	public DateRange getRecurrenceDateRange() {
		return this.recurrenceDateRange;
	}

	public void setRecurrenceDateRange(final DateRange recurrenceDateRange) {
		this.recurrenceDateRange = recurrenceDateRange;
	}

	public AbstractRecurringScheduleBuilderTypeForm
			getRecurringScheduleBuilderTypeForm() {
		return this.recurringScheduleBuilderTypeForm;
	}

	public void setRecurringScheduleBuilderTypeForm(
			final AbstractRecurringScheduleBuilderTypeForm
				recurringScheduleBuilderTypeForm) {
		this.recurringScheduleBuilderTypeForm = recurringScheduleBuilderTypeForm;
	}

	public String getDetermineEndDateBy() {
		return this.determineEndDateBy;
	}

	public void setDetermineEndDateBy(final String determineEndDateBy) {
		this.determineEndDateBy = determineEndDateBy;
	}

	public Integer getEndAfterOccurrences() {
		return this.endAfterOccurrences;
	}

	public void setEndAfterOccurrences(final Integer endAfterOccurrences) {
		this.endAfterOccurrences = endAfterOccurrences;
	}

	public String getBuilderType() {
		return this.builderType;
	}

	public void setBuilderType(final String builderType) {
		this.builderType = builderType;
	}
}