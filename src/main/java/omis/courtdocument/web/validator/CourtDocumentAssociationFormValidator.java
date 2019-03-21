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
package omis.courtdocument.web.validator;

import java.util.List;

import omis.courtdocument.web.form.CourtDocumentAssociationForm;
import omis.document.validator.DocumentTagItemValidator;
import omis.document.web.form.DocumentTagItem;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/** 
 * Validator for court document association form.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.1 (Aug 9, 2018)
 * @since OMIS 3.0
 */
public class CourtDocumentAssociationFormValidator 
	implements Validator {
	
	/* Field names */
	
	private static final String DATE_FIELD_NAME = "date";
	
	private static final String TITLE_FIELD_NAME = "title";
	
	/* Message keys */
		
	private static final String DATE_REQUIRED_MSG = "date.empty";
	
	private static final String TITLE_REQUIRED_MSG = 
			"document.title.empty";
	
	private final DocumentTagItemValidator documentTagItemValidator =
			new DocumentTagItemValidator();
	
	/** Constructor. */
	public CourtDocumentAssociationFormValidator() { }
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return clazz.isAssignableFrom(CourtDocumentAssociationForm.class);
		
	}
	
	/** {@inheritDoc} */
	@Override
	public void validate(final Object obj, final Errors errors) {
		final CourtDocumentAssociationForm courtDocumentAssociationForm = 
			(CourtDocumentAssociationForm) obj;
		ValidationUtils.rejectIfEmpty(errors, DATE_FIELD_NAME, 
				DATE_REQUIRED_MSG);
		ValidationUtils.rejectIfEmpty(errors, TITLE_FIELD_NAME, 
				TITLE_REQUIRED_MSG);
		List<DocumentTagItem> documentTagItems = 
				courtDocumentAssociationForm.getDocumentTagItems();
		if (documentTagItems != null) {
			this.documentTagItemValidator.validate(documentTagItems, errors);
		}
	}
}