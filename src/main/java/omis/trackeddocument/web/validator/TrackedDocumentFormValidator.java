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
package omis.trackeddocument.web.validator;

import omis.trackeddocument.web.form.TrackedDocumentForm;
import omis.trackeddocument.web.form.TrackedDocumentReceivalItem;
import omis.trackeddocument.web.form.TrackedDocumentReceivalItemOperation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for submitted tracked document form.
 * 
 * @author Yidong
 * @version 0.1.0 (Jan 4, 2018)
 * @since OMIS 3.0
 */
public class TrackedDocumentFormValidator
	implements Validator {

	/** Instantiates a validator for form for tracked document. */
	public TrackedDocumentFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return TrackedDocumentForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		TrackedDocumentForm form = (TrackedDocumentForm) target;
		
		if (form.getTrackedDocumentReceivalItems() != null) {
			for (int index = 0; index < form.getTrackedDocumentReceivalItems()
				.size(); index++) {
				if (TrackedDocumentReceivalItemOperation.CREATE.equals(
					form.getTrackedDocumentReceivalItems().get(index)
					.getOperation())) {
					TrackedDocumentReceivalItem item
						= form.getTrackedDocumentReceivalItems().get(index);
					if (form.getDocket() == null) {
						errors.rejectValue("docket", "docket.empty");
					}
					if (item.getCategory() == null) {
						errors.rejectValue("trackedDocumentReceivalItems[" 
								+ index + "].category", "category.empty");
					}
					if (item.getReceivedByUserAccount() == null) {
						errors.rejectValue("trackedDocumentReceivalItems[" 
							+ index + "].receivedByUserAccount",
							"receivedByUserAccount.empty");
					}
					
					for (int innerIndex = index + 1;
						innerIndex < form.getTrackedDocumentReceivalItems()
						.size(); innerIndex++) {
						if ((form.getTrackedDocumentReceivalItems().get(index)
							.getCategory().equals(
							form.getTrackedDocumentReceivalItems()
							.get(innerIndex).getCategory()))
							&& ((form.getTrackedDocumentReceivalItems()
							.get(index).getReceivedDate() == null
							&& form.getTrackedDocumentReceivalItems()
							.get(innerIndex).getReceivedDate() == null)
							|| (form.getTrackedDocumentReceivalItems()
							.get(index).getReceivedDate().equals(
							form.getTrackedDocumentReceivalItems()
							.get(innerIndex).getReceivedDate())))) {
							errors.rejectValue(
								"trackedDocumentReceivalItems["	+ innerIndex
								+ "].category", 
								"trackedDocumentReceivalItem.duplicate");
							break;
						}
					}
				}
			}
		}
	}
}