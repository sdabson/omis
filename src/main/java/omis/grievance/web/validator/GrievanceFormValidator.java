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
package omis.grievance.web.validator;

import java.util.Date;

import omis.grievance.web.form.GrievanceDispositionItem;
import omis.grievance.web.form.GrievanceForm;
import omis.grievance.web.validator.delegate.GrievanceDispositionItemValidator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for grievance form.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 22, 2015)
 * @since OMIS 3.0
 */
public class GrievanceFormValidator
		implements Validator {

	/* Resources. */
	
	private final GrievanceDispositionItemValidator
	grievanceDispositionItemValidator;
	
	/* Constructors. */
	
	/** Instantiates validator for grievance form. */
	public GrievanceFormValidator(
			final GrievanceDispositionItemValidator
			grievanceDispositionItemValidator) {
		this.grievanceDispositionItemValidator
			= grievanceDispositionItemValidator;
	}
	
	/* Method implementations. */

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return GrievanceForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		GrievanceForm grievanceForm = (GrievanceForm) target;
		if (grievanceForm.getEdit() != null && grievanceForm.getEdit()) {
			if (grievanceForm.getComplaintCategory() == null) {
				errors.rejectValue("complaintCategory",
						"grievanceComplaintCategory.empty");
			}
			if (grievanceForm.getUnit() == null) {
				errors.rejectValue("unit", "grievanceUnit.empty");
			}
			if (grievanceForm.getLocation() == null) {
				errors.rejectValue("location", "grievanceLocation.empty");
			}
		}
		if (grievanceForm.getCoordinatorLevelDispositionItem() != null) {
			this.validateOnOrAfterDate(
					grievanceForm.getOpenedDate(),
					grievanceForm.getCoordinatorLevelDispositionItem(),
					errors, "coordinatorLevelDispositionItem");
			this.grievanceDispositionItemValidator.validate(
					grievanceForm.getCoordinatorLevelDispositionItem(),
					errors,
					"coordinatorLevelDispositionItem");
		}
		if (grievanceForm.getWardenFhaLevelDispositionItem() != null) {
			this.validateOnOrAfterDate(
					grievanceForm.getOpenedDate(),
					grievanceForm.getWardenFhaLevelDispositionItem(),
					errors, "wardenFhaLevelDispositionItem");
			this.grievanceDispositionItemValidator.validate(
					grievanceForm.getWardenFhaLevelDispositionItem(),
					errors,
					"wardenFhaLevelDispositionItem");
			if (grievanceForm.getCoordinatorLevelDispositionItem() != null) {
				this.validatePreviousDispositionItemDates(
						grievanceForm.getWardenFhaLevelDispositionItem(),
						grievanceForm.getCoordinatorLevelDispositionItem(),
						"wardenFhaLevelDispositionItem", errors);
			}
		}
		if (grievanceForm.getDirectorLevelDispositionItem() != null) {
			this.validateOnOrAfterDate(
					grievanceForm.getOpenedDate(),
					grievanceForm.getDirectorLevelDispositionItem(),
					errors, "directorLevelDispositionItem");
			this.grievanceDispositionItemValidator.validate(
					grievanceForm.getDirectorLevelDispositionItem(),
					errors,
					"directorLevelDispositionItem");
			if (grievanceForm.getWardenFhaLevelDispositionItem() != null) {
				this.validatePreviousDispositionItemDates(
						grievanceForm.getDirectorLevelDispositionItem(),
						grievanceForm.getWardenFhaLevelDispositionItem(),
						"directorLevelDispositionItem", errors);
			}
		}
	}
	
	// Validates that dates of previous disposition item come during or after
	// dates of disposition item
	private void validatePreviousDispositionItemDates(
			final GrievanceDispositionItem dispositionItem,
			final GrievanceDispositionItem previousDispositionItem,
			final String grievanceDispositionItemName,
			final Errors errors) {
		if (previousDispositionItem.getReceivedDate() != null
				&& dispositionItem.getReceivedDate() != null
						&& previousDispositionItem.getReceivedDate()
							.after(dispositionItem.getReceivedDate())) {
			errors.rejectValue(grievanceDispositionItemName + ".receivedDate",
					"grievanceDispositionReceivedDate.beforePrevious");
		}
		if (previousDispositionItem.getResponseDueDate() != null
				&& dispositionItem.getResponseDueDate() != null
						&& previousDispositionItem.getResponseDueDate()
							.after(dispositionItem.getResponseDueDate())) {
			errors.rejectValue(
					grievanceDispositionItemName + ".responseDueDate",
					"grievanceDispositionResponseDueDate.beforePrevious");
		}
		if (previousDispositionItem.getResponseToOffenderDate() != null
				&& dispositionItem.getResponseToOffenderDate() != null
						&& previousDispositionItem.getResponseToOffenderDate()
							.after(dispositionItem
									.getResponseToOffenderDate())) {
			errors.rejectValue(
					grievanceDispositionItemName + ".responseToOffenderDate",
					"grievanceDispositionResponseToOffenderDate"
							+".beforePrevious");
		}
		if (dispositionItem.getAllowAppealDate() != null
				&& dispositionItem.getAllowAppealDate()) {
			if (previousDispositionItem.getAppealDate() != null
					&& dispositionItem.getAppealDate() != null
							&& previousDispositionItem.getAppealDate()
								.after(dispositionItem.getAppealDate())) {
				errors.rejectValue(
						grievanceDispositionItemName + ".appealDate",
						"grievanceDispositionAppealDate.beforePrevious");
			}
		}
	}
	
	// Validates that dates of disposition item are on or after date
	private void validateOnOrAfterDate(
			final Date date, final GrievanceDispositionItem item,
			final Errors errors, final String grievanceDispositionItemName) {
		if (date != null && item.getReceivedDate() != null
				&& date.after(item.getReceivedDate())) {
			errors.rejectValue(grievanceDispositionItemName + ".receivedDate",
					"grievanceDispositionReceivedDate"
							+ ".afterGrievanceOpenedDate");
		}
		if (date != null && item.getResponseDueDate() != null
				&& date.after(item.getResponseDueDate())) {
			errors.rejectValue(grievanceDispositionItemName
							+ ".responseDueDate",
					"grievanceDispositionResponseDueDate"
							+ ".afterGrievanceOpenedDate");
		}
		if (date != null && item.getResponseToOffenderDate() != null
				&& date.after(item.getResponseToOffenderDate())) {
			errors.rejectValue(grievanceDispositionItemName
							+ ".responseToOffenderDate",
					"grievanceDispositionResponseToOffenderDate"
							+ ".afterGrievanceOpenedDate");
		}
		if (item.getAllowAppealDate() != null && item.getAllowAppealDate()) {
			if (date != null && item.getAppealDate() != null
					&& date.after(item.getAppealDate())) {
				errors.rejectValue(grievanceDispositionItemName + ".appealDate",
					"grievanceDispositionAppealDate.afterGrievanceOpenedDate");
			}
		}
	}
}