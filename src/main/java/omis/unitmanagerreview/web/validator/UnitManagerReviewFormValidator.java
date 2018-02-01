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
package omis.unitmanagerreview.web.validator;

import java.util.HashSet;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.unitmanagerreview.web.form.DocumentTagItem;
import omis.unitmanagerreview.web.form.UnitManagerReviewDocumentAssociationItem;
import omis.unitmanagerreview.web.form.UnitManagerReviewForm;
import omis.unitmanagerreview.web.form.UnitManagerReviewItemOperation;

/**
 * Validator for unit manager reviews.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 29, 2018)
 * @since OMIS 3.0
 */
public class UnitManagerReviewFormValidator implements Validator {

	/* Message Keys. */
	
	private static final String TITLE_REQUIRED_MSG_KEY = "title.required";
	
	private static final String FILE_DATE_REQUIRED_MSG_KEY =
			"fileDate.required";
	
	private static final String DOCUMENT_REQUIRED_MSG_KEY = "document.required";
	
	private static final String NAME_REQUIRED_MSG = "tag.name.empty";
	
	private static final String NAME_DUPLICATED_MSG = "tag.name.duplicate";
	
	/* Field Names. */
	
	private static final String NAME_FIELD_NAME = "name";
	
	private static final String DOCUMENT_TAG_ITEM_FIELD_NAME = 
			"unitManagerReviewDocumentAssociationItems[%d]"
											+ ".documentTagItems[%d]";
	
	/**
	 * Instantiates a default unit manager review form validator.
	 */
	public UnitManagerReviewFormValidator() {
		// Default instantiation
	} 
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return UnitManagerReviewForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		UnitManagerReviewForm form = (UnitManagerReviewForm) target;
		
		if (form.getStaffAssignment() == null) {
			errors.rejectValue("staffAssignment", 
					"unitManagerReview.staffAssignment.empty");
		}
		if (form.getDate() == null) {
			errors.rejectValue("date", "unitManagerReview.date.empty");
		}
		if (form.getText() == null || form.getText().isEmpty()) {
			errors.rejectValue("text", "unitManagerReview.text.empty");
		}
		if(form.getUnitManagerReviewDocumentAssociationItems() != null){
			int i = 0;
			for(UnitManagerReviewDocumentAssociationItem item :
					form.getUnitManagerReviewDocumentAssociationItems()){
				if(UnitManagerReviewItemOperation.CREATE.equals(
						item.getItemOperation()) ||
					UnitManagerReviewItemOperation.UPDATE.equals(
						item.getItemOperation())){
					
					ValidationUtils.rejectIfEmpty(errors,
						"unitManagerReviewDocumentAssociationItems"
						+ "["+i+"].title",
							TITLE_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
						"unitManagerReviewDocumentAssociationItems"
						+ "["+i+"].fileDate",
							FILE_DATE_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
							"unitManagerReviewDocumentAssociationItems"
							+ "["+i+"].data", 
							DOCUMENT_REQUIRED_MSG_KEY);
					
					if(item.getDocumentTagItems() != null){
						List<DocumentTagItem> documentTags =
								item.getDocumentTagItems();
						HashSet<String> documentTagNames = 
								new HashSet<String>(documentTags.size());
						for (int x = 0; x < documentTags.size(); x++) {
							DocumentTagItem documentTagItem = documentTags.get(x);
							UnitManagerReviewItemOperation
								documentTagOperation = documentTagItem
									.getItemOperation();
							if (documentTagOperation ==
									UnitManagerReviewItemOperation.CREATE 
									|| documentTagOperation ==
									UnitManagerReviewItemOperation.UPDATE){
								errors.pushNestedPath(String.format(
										DOCUMENT_TAG_ITEM_FIELD_NAME, i, x));
								if (documentTagNames.add(documentTagItem
										.getName())) {
									ValidationUtils.rejectIfEmpty(
											errors, NAME_FIELD_NAME, 
											NAME_REQUIRED_MSG);
								}
								else {
									errors.rejectValue(NAME_FIELD_NAME, 
											NAME_DUPLICATED_MSG);
								}
								errors.popNestedPath();
							}
						}
					}
				}
				i++;
			}
		}
	}
}