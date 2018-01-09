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
package omis.grievance.web.validator.delegate;

import org.springframework.validation.Errors;

import omis.grievance.web.form.GrievanceDispositionItem;

/**
 * Validator for grievance disposition item.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Oct 6, 2015)
 * @since OMIS 3.0
 */
public class GrievanceDispositionItemValidator {

	/* Constructors. */
	
	/** Validator for grievance disposition items. */
	public GrievanceDispositionItemValidator() {
		// Default instantiation
	}
	
	/* Methods. */
	
	/**
	 * Validators grievance disposition item.
	 * 
	 * @param grievanceDispositionItem grievance disposition item to validate
	 * @param errors errors
	 */
	public void validate(
			final GrievanceDispositionItem grievanceDispositionItem,
			final Errors errors,
			final String grievanceDispositionItemName) {
		if (grievanceDispositionItem.getEdit() != null
				&& grievanceDispositionItem.getEdit()) {
			if (grievanceDispositionItem.getStatus() == null) {
				errors.rejectValue(
					grievanceDispositionItemName + ".status",
					"grievanceDispositionStatus.empty");
			}
			if (grievanceDispositionItem.getResponseDueDate() == null) {
				errors.rejectValue(
					grievanceDispositionItemName + ".responseDueDate",
					"grievanceDispositionResponseDueDate.empty");
			}
			if (grievanceDispositionItem.getStatus() != null
					&& grievanceDispositionItem.getStatus().getClosed()
					&& grievanceDispositionItem.getClosedDateAllowed() != null
					&& grievanceDispositionItem.getClosedDateAllowed()
					&& grievanceDispositionItem.getClosedDate() == null) {
				errors.rejectValue(
						grievanceDispositionItemName + ".closedDate",
							"grievanceDispositionClosedDate.requiredByStatus");
			}
		}
	}
}