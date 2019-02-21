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
package omis.earlyreleasetracking.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import omis.document.web.form.DocumentTagItem;
import omis.document.web.form.DocumentTagOperation;
import omis.earlyreleasetracking.web.form.EarlyReleaseRequestDocumentAssociationItem;
import omis.earlyreleasetracking.web.form.EarlyReleaseRequestExternalOppositionItem;
import omis.earlyreleasetracking.web.form.EarlyReleaseRequestForm;
import omis.earlyreleasetracking.web.form.EarlyReleaseRequestInternalApprovalItem;
import omis.earlyreleasetracking.web.form.EarlyReleaseRequestItemOperation;
import omis.earlyreleasetracking.web.form.EarlyReleaseRequestNoteAssociationItem;

/**
 * Early Release Request Form Validator.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 12, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestFormValidator implements Validator {
	
	private static final String DATE_REQUIRED_MSG_KEY = "date.required";
	
	private static final String DESCRIPTION_REQUIRED_MSG_KEY =
			"description.required";

	private static final String DOCKET_REQUIRED_MSG_KEY =
			"earlyReleaseRequest.docket.required";
	
	private static final String REQUEST_DATE_REQUIRED_MSG_KEY =
			"earlyReleaseRequest.requestDate.required";
	
	private static final String REQUEST_BY_REQUIRED_MSG_KEY =
			"earlyReleaseRequest.requestBy.required";
	
	private static final String DECISION_REQUIRED_MSG_KEY =
			"earlyReleaseRequestInternalApproval.decision.required";
	
	private static final String NAME_REQUIRED_MSG_KEY =
			"earlyReleaseRequestInternalApproval.name.required";
	
	private static final String NARRATIVE_REQUIRED_MSG_KEY =
			"earlyReleaseRequest.narrative.required";
	
	private static final String PARTY_REQUIRED_MSG_KEY =
			"earlyReleaseRequestExternalOpposition.party.required";

	private static final String TITLE_REQUIRED_MSG_KEY = "document.title.empty";
	
	private static final String DOCUMENT_REQUIRED_MSG_KEY = "document.required";

	private static final String TAG_REQUIRED_MSG_KEY = "tag.required";
	
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return EarlyReleaseRequestForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "docket",
				DOCKET_REQUIRED_MSG_KEY);
		ValidationUtils.rejectIfEmpty(errors, "requestDate",
				REQUEST_DATE_REQUIRED_MSG_KEY);
		ValidationUtils.rejectIfEmpty(errors, "requestBy",
				REQUEST_BY_REQUIRED_MSG_KEY);
		
		EarlyReleaseRequestForm form = (EarlyReleaseRequestForm) target;

		if (form.getEarlyReleaseRequestInternalApprovalItems() != null) {
			int i = 0;
			for (EarlyReleaseRequestInternalApprovalItem item
					: form.getEarlyReleaseRequestInternalApprovalItems()) {
				if (EarlyReleaseRequestItemOperation.CREATE.equals(
							item.getItemOperation())
						|| EarlyReleaseRequestItemOperation.UPDATE.equals(
							item.getItemOperation())) {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors,
								"earlyReleaseRequestInternalApprovalItems"
								+ "[" + i + "].date", DATE_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
							"earlyReleaseRequestInternalApprovalItems"
							+ "[" + i + "].decision",
							DECISION_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmptyOrWhitespace(errors,
							"earlyReleaseRequestInternalApprovalItems"
							+ "[" + i + "].name", NAME_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmptyOrWhitespace(errors,
							"earlyReleaseRequestInternalApprovalItems"
							+ "[" + i + "].narrative",
							NARRATIVE_REQUIRED_MSG_KEY);
				}
				i++;
			}
		}
		
		if (form.getEarlyReleaseRequestExternalOppositionItems() != null) {
			int i = 0;
			for (EarlyReleaseRequestExternalOppositionItem item
					: form.getEarlyReleaseRequestExternalOppositionItems()) {
				if (EarlyReleaseRequestItemOperation.CREATE.equals(
							item.getItemOperation())
						|| EarlyReleaseRequestItemOperation.UPDATE.equals(
							item.getItemOperation())) {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors,
								"earlyReleaseRequestExternalOppositionItems"
								+ "[" + i + "].date", DATE_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
							"earlyReleaseRequestExternalOppositionItems"
							+ "[" + i + "].party",
							PARTY_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmptyOrWhitespace(errors,
							"earlyReleaseRequestExternalOppositionItems"
							+ "[" + i + "].narrative",
							NARRATIVE_REQUIRED_MSG_KEY);
				}
				i++;
			}
		}
		
		if (form.getEarlyReleaseRequestNoteAssociationItems() != null) {
			int i = 0;
			for (EarlyReleaseRequestNoteAssociationItem item
					: form.getEarlyReleaseRequestNoteAssociationItems()) {
				if (EarlyReleaseRequestItemOperation.CREATE.equals(
							item.getItemOperation())
						|| EarlyReleaseRequestItemOperation.UPDATE.equals(
							item.getItemOperation())) {
					ValidationUtils.rejectIfEmpty(errors,
							"earlyReleaseRequestNoteAssociationItems"
							+ "[" + i + "].date", DATE_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmptyOrWhitespace(errors,
							"earlyReleaseRequestNoteAssociationItems"
							+ "[" + i + "].description",
							DESCRIPTION_REQUIRED_MSG_KEY);
				}
				i++;
			}
		}
		
		if (form.getEarlyReleaseRequestDocumentAssociationItems() != null) {
			int i = 0;
			for (EarlyReleaseRequestDocumentAssociationItem item
					: form.getEarlyReleaseRequestDocumentAssociationItems()) {
				if (EarlyReleaseRequestItemOperation.CREATE.equals(
							item.getItemOperation())
						|| EarlyReleaseRequestItemOperation.UPDATE.equals(
							item.getItemOperation())) {
					ValidationUtils.rejectIfEmpty(errors,
							"earlyReleaseRequestDocumentAssociationItems"
							+ "[" + i + "].title", TITLE_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
							"earlyReleaseRequestDocumentAssociationItems"
							+ "[" + i + "].date", DATE_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
							"earlyReleaseRequestDocumentAssociationItems"
							+ "[" + i + "].data", DOCUMENT_REQUIRED_MSG_KEY);
					if (item.getDocumentTagItems() != null) {
						int j = 0;
						for (DocumentTagItem tagItem
								: item.getDocumentTagItems()) {
							if (DocumentTagOperation.CREATE.equals(
									tagItem.getDocumentTagOperation())
									|| DocumentTagOperation.UPDATE.equals(
									tagItem.getDocumentTagOperation())) {
								ValidationUtils.rejectIfEmpty(errors,
										"earlyReleaseRequestDocumentAssociation"
										+ "Items[" + i + "].documentTagItems"
												+ "[" + j + "].name",
												TAG_REQUIRED_MSG_KEY);
							}
							j++;
						}
					}
				}
				i++;
			}
		}
	}
}
