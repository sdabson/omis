/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.locationterm.web.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.locationterm.web.form.LocationReasonTermItem;
import omis.locationterm.web.form.LocationReasonTermItemOperation;
import omis.locationterm.web.form.LocationTermForm;
import omis.util.DateManipulator;
import omis.web.validator.StringLengthChecks;

/**
 * Validator for form for location terms.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 15, 2014)
 * @since OMIS 3.0
 */
public class LocationTermFormValidator
		implements Validator {
	
	private final StringLengthChecks stringLengthChecks;

	/**
	 * Instantiates a validator for form for location terms.
	 * 
	 * @param stringLengthChecks string length checks
	 */
	public LocationTermFormValidator(
			final StringLengthChecks stringLengthChecks) {
		this.stringLengthChecks = stringLengthChecks;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return LocationTermForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		LocationTermForm locationTermForm = (LocationTermForm) target;
		if (locationTermForm.getAllowLocation() != null
				&& locationTermForm.getAllowLocation()
				&& locationTermForm.getLocation() == null) {
			errors.rejectValue("location", "location.empty");
		}
		if (locationTermForm.getStartDate() != null
				&& locationTermForm.getEndDate() != null
					&& DateManipulator.getDateAtTimeOfDay(
							locationTermForm.getStartDate(),
							locationTermForm.getStartTime()).getTime()
						> DateManipulator.getDateAtTimeOfDay(
								locationTermForm.getEndDate(),
								locationTermForm.getEndTime()).getTime()) {
			errors.rejectValue("startDate",
					"dateRange.startDateGreaterThanEndDate");
		}
		if (locationTermForm.getStartDate() == null) {
			errors.rejectValue("startDate", "startDate.empty");
		}
		if (locationTermForm.getStartTime() == null) {
			errors.rejectValue("startTime", "startTime.empty");
		}
		if (locationTermForm.getEndDate() != null
				&& locationTermForm.getEndTime() == null) {
			errors.rejectValue("endTime", "endTime.empty");
		}
		if (locationTermForm.getEndDate() == null
				&& locationTermForm.getEndTime() != null) {
			errors.rejectValue("endDate", "endDate.empty");
		}
		if (this.isDateTimePairEqual(
				locationTermForm.getStartDate(),
				locationTermForm.getStartTime(),
				locationTermForm.getEndDate(),
				locationTermForm.getEndTime())) {
			errors.rejectValue("endDate", "endDateTime.equalsStartDateTime");
		}
		this.stringLengthChecks.getVeryLargeCheck().check("notes",
				locationTermForm.getNotes(), errors);
		if (locationTermForm.getAssociateMultipleReasonTerms() != null
				&& locationTermForm.getAssociateMultipleReasonTerms()) {
			boolean atLeastOne;
			if (locationTermForm.getReasonTermItems() != null) {
				atLeastOne = false;
				for (int index = 0;
						index < locationTermForm.getReasonTermItems().size();
						index++) {
					LocationReasonTermItem currentItem
						= locationTermForm.getReasonTermItems().get(index);
					if (currentItem != null
							&& (LocationReasonTermItemOperation
								.CREATE.equals(currentItem.getOperation())
							|| LocationReasonTermItemOperation
								.UPDATE.equals(currentItem.getOperation()))) {
						if (currentItem.getReason() == null) {
							errors.rejectValue(
									"reasonTermItems[" + index + "].reason",
									"locationReason.empty");
						}
						if (currentItem.getStartDate() == null) {
							errors.rejectValue(
								"reasonTermItems[" + index + "].startDate",
								"startDate.empty");
						}
						if (currentItem.getStartTime() == null) {
							errors.rejectValue(
								"reasonTermItems[" + index + "].startTime",
								"startTime.empty");
						}
						if (currentItem.getEndDate() != null
								&& currentItem.getEndTime() == null) {
							errors.rejectValue(
								"reasonTermItems[" + index + "].endTime",
								"endTime.empty");
						}
						if (currentItem.getEndDate() == null
								&& currentItem.getEndTime() != null) {
							errors.rejectValue(
								"reasonTermItems[" + index + "].endDate",
								"endDate.empty");
						}
						if (currentItem.getStartDate() != null
								&& currentItem.getEndDate() != null
									&& DateManipulator.getDateAtTimeOfDay(
											currentItem.getStartDate(),
											currentItem.getStartTime())
												.getTime()
										> DateManipulator.getDateAtTimeOfDay(
												currentItem.getEndDate(),
												currentItem.getEndTime())
													.getTime()) {
							errors.rejectValue(
								"reasonTermItems[" + index
									+ "].startDate",
								"dateRange.startDateGreaterThanEndDate");
						}
						if (index == 0) {
							if (this.isDateTimePairBefore(
										currentItem.getStartDate(),
										currentItem.getStartTime(),
										locationTermForm.getStartDate(),
										locationTermForm.getStartTime())) {
									errors.rejectValue(
										"reasonTermItems["
											+ index + "].startDate",
										"locationReasonTerm"
											+ ".mustBeWithinLocationTerm");
							}
						} else {
							LocationReasonTermItem previousItem
								= locationTermForm.getReasonTermItems()
									.get(index - 1);
							if (this.isDateTimePairBefore(
									currentItem.getStartDate(),
									currentItem.getStartTime(),
									previousItem.getEndDate(),
									previousItem.getEndTime())) {
								errors.rejectValue(
									"reasonTermItems[" + index
										+ "].startDate",
									"locationReasonTerm"
									+ ".mustBeAfterOrOnPreviousReason");	
							}
						}
						if (index == locationTermForm
								.getReasonTermItems().size() - 1) {
							if (this.isDateTimePairAfter(
									currentItem.getEndDate(),
									currentItem.getEndTime(),
									locationTermForm.getEndDate(),
									locationTermForm.getEndTime())) {
								errors.rejectValue(
										"reasonTermItems["
											+ index + "].endDate",
										"locationReasonTerm"
											+ ".mustBeWithinLocationTerm");	
							}
						} else if (index > 1) {
							LocationReasonTermItem previousItem
								= locationTermForm.getReasonTermItems()
									.get(index - 1);
							if (this.isDateTimePairBefore(
									currentItem.getStartDate(),
									currentItem.getStartTime(),
									previousItem.getEndDate(),
									previousItem.getEndTime())) {
								errors.rejectValue(
									"reasonTermItems["
										+ index + "].startDate",
									"locationReasonTerm"
										+ ".mustBeAfterOrOnPreviousReason");
							}
						}
						if (!atLeastOne) {
							atLeastOne = true;
						}
					}
				}
			} else {
				atLeastOne = false;
			}
			if (!atLeastOne) {
				errors.rejectValue("reasonTermItems",
						"locationTerm.atLeastOneReasonTermRequired");
			}
		} else {
			if (locationTermForm.getReason() == null) {
				errors.rejectValue("reason", "locationReason.empty");
			}
		}
		if (locationTermForm.getAllowNextChangeAction() != null
				&& locationTermForm.getAllowNextChangeAction()
				&& locationTermForm.getNextChangeAction() != null) {
			if (locationTermForm.getEndDate() == null) {
				errors.rejectValue("endDate",
						"locationTerm.endDate.requiredWithNextChangeAction");
			}
			if (locationTermForm.getEndTime() == null) {
				errors.rejectValue("endTime",
						"locationTerm.endTime.requiredWithNextChangeAction");
			}
		}
	}
	
	// Returns whether date/time pair is before other date/time pair
	private boolean isDateTimePairBefore(
			final Date date, final Date time,
			final Date otherDate, final Date otherTime) {
		if (date != null && time != null) {
			if (otherDate != null && otherTime != null) {
				if (DateManipulator.getDateAtTimeOfDay(date, time).getTime()
						>= DateManipulator.getDateAtTimeOfDay(
								otherDate, otherTime).getTime()) {
					return false;
				}
			} else {
				return false;
			}
		} else if (otherDate == null && otherTime == null) {
			return false;
		}
		return true;
	}
	
	// Returns whether date/time pair is after other date/time pair
	private boolean isDateTimePairAfter(
			final Date date, final Date time,
			final Date otherDate, final Date otherTime) {
		if (date != null && time != null) {
			if (otherDate != null & otherTime != null) {
				if (DateManipulator.getDateAtTimeOfDay(date, time).getTime()
						<= DateManipulator.getDateAtTimeOfDay(
								otherDate, otherTime).getTime()) {
					return false;
				}
			} else {
				return false;
			}
		} else if (otherDate == null && otherTime == null) {
			return false;
		}
		return true;
	}
	
	// Returns whether date/time pair is equal
	private boolean isDateTimePairEqual(
			final Date date, final Date time,
			final Date otherDate, final Date otherTime) {
		if (date != null && time != null) {
			if (otherDate != null & otherTime != null) {
				if (DateManipulator.getDateAtTimeOfDay(date, time).getTime()
						== DateManipulator.getDateAtTimeOfDay(
								otherDate, otherTime).getTime()) {
					return true;
				}
			} else {
				return false;
			}
		} else if (otherDate == null && otherTime == null) {
			return true;
		}
		return false;
	}
}