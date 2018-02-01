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
package omis.offenseterm.web.validator;

import java.beans.PropertyEditor;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.beans.factory.PropertyEditorFactory;
import omis.conviction.web.validator.delegate.ConvictionFieldsValidatorDelegate;
import omis.courtcase.web.validator.delegate.CourtCaseFieldsValidatorDelegate;
import omis.docket.web.validator.delegate.DocketFieldsValidatorDelegate;
import omis.offenseterm.web.form.OffenseItem;
import omis.offenseterm.web.form.OffenseItemConnection;
import omis.offenseterm.web.form.OffenseItemConnectionClassification;
import omis.offenseterm.web.form.OffenseTermForm;
import omis.sentence.domain.Sentence;
import omis.sentence.domain.SentenceConnectionClassification;
import omis.sentence.web.form.SentenceOperation;
import omis.sentence.web.validator.delegate.SentenceFieldsValidatorDelegate;

/**
 * Validator for for form for offense terms.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class OffenseTermFormValidator
		implements Validator {

	private static final String DOCKET_FIELDS_PROPERTY_NAME = "docketFields";
	
	private static final String COURT_CASE_FIELDS_PROPERTY_NAME = "fields";
	
	private static final String CONVICTION_FIELDS_PROPERTY_NAME
		= "offenseItems[%d].convictionFields";
	
	private static final String SENTENCE_FIELDS_PROPERTY_NAME
		= "offenseItems[%d].sentenceFields";

	private final DocketFieldsValidatorDelegate docketFieldsValidatorDelegate;
	
	private final CourtCaseFieldsValidatorDelegate 
	courtCaseFieldsValidatorDelegate;
	
	private final ConvictionFieldsValidatorDelegate
	convictionFieldsValidatorDelegate;
	
	private final SentenceFieldsValidatorDelegate
	sentenceFieldsValidatorDelegate;
	
	private final PropertyEditorFactory sentencePropertyEditorFactory;

	/**
	 * Instantiates validator for form for offense terms.
	 * 
	 * @param docketFieldsValidatorDelegate delegate to validate docket fields
	 * @param courtCaseFieldsValidatorDelegate delegate to validate court case
	 * fields
	 * @param convictionFieldsValidatorDelegate delegate to validate
	 * conviction fields
	 * @param sentenceFieldsValidatorDelegate delegate to validate sentence
	 * fields
	 * @param sentencePropertyEditorFactory used to resolve connected sentence
	 * of consecutive sentences. This practice is discouraged - it is
	 * necessary as the {@code connection.index} property of
	 * {@code OffenseTermItem} is used to store different types of value
	 * (one of which is a {@code Sentence}). Because the index property
	 * represents different types of values, an automatic property editor
	 * cannot be used to resolve it to a single type.  
	 */
	public OffenseTermFormValidator(
			final DocketFieldsValidatorDelegate
				docketFieldsValidatorDelegate,
			final CourtCaseFieldsValidatorDelegate 
				courtCaseFieldsValidatorDelegate,
			final ConvictionFieldsValidatorDelegate
				convictionFieldsValidatorDelegate,
			final SentenceFieldsValidatorDelegate
				sentenceFieldsValidatorDelegate,
			final PropertyEditorFactory
				sentencePropertyEditorFactory) {
		this.docketFieldsValidatorDelegate
			= docketFieldsValidatorDelegate;
		this.courtCaseFieldsValidatorDelegate
			= courtCaseFieldsValidatorDelegate;
		this.convictionFieldsValidatorDelegate
			= convictionFieldsValidatorDelegate;
		this.sentenceFieldsValidatorDelegate
			= sentenceFieldsValidatorDelegate;
		this.sentencePropertyEditorFactory
			= sentencePropertyEditorFactory;
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return OffenseTermForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		OffenseTermForm form = (OffenseTermForm) target;
		
		// Validates docket fields only when allowed and when existing docket
		// is not specified
		if (form.getAllowDocketFields() != null
				&& form.getAllowDocketFields()
				&& form.getExistingDocket() == null) {
			this.docketFieldsValidatorDelegate.validate(
					form.getDocketFields(),
					DOCKET_FIELDS_PROPERTY_NAME,
					errors);
		}
		
		// Validates rest of court case fields
		this.courtCaseFieldsValidatorDelegate
			.validate(form.getFields(),
					COURT_CASE_FIELDS_PROPERTY_NAME, errors);
		
		// Validates offense terms
		int index = 0;
		for (OffenseItem offenseTermItem : form.getOffenseItems()) {
			
			// Tracks rejected values per item; always expands item when
			// rejected
			int countRejected =
					this.convictionFieldsValidatorDelegate
						.validate(offenseTermItem.getConvictionFields(),
								String.format(CONVICTION_FIELDS_PROPERTY_NAME,
										index),
								errors);
			if (SentenceOperation.CREATE.equals(
					offenseTermItem.getSentenceOperation())
					|| SentenceOperation.UPDATE.equals(
							offenseTermItem.getSentenceOperation())
					|| SentenceOperation.AMEND.equals(
							offenseTermItem.getSentenceOperation())) {
				countRejected
					= countRejected + this.sentenceFieldsValidatorDelegate
						.validate(offenseTermItem.getSentenceFields(),
								String.format(SENTENCE_FIELDS_PROPERTY_NAME,
										index),
								errors);
				if (offenseTermItem.getConnection() == null) {
					errors.rejectValue("offenseItems[" + index + "].connection",
							"offenseTermForm.sentence.connectionEmpty");
					countRejected++;
				} else if (OffenseItemConnectionClassification.CONSECUTIVE
						.equals(offenseTermItem.getConnection()
								.getClassification())) {
					int consecutiveCounter = 0;
					for (OffenseItem innerOffenseTermItem
							: form.getOffenseItems()) {
						if ((SentenceOperation.CREATE.equals(
								innerOffenseTermItem.getSentenceOperation())
									|| SentenceOperation.UPDATE.equals(
											innerOffenseTermItem
												.getSentenceOperation())
									|| SentenceOperation.AMEND.equals(
											innerOffenseTermItem
												.getSentenceOperation()))
								&& (innerOffenseTermItem.getConnection() != null
									&& OffenseItemConnectionClassification
										.CONSECUTIVE.equals(
											innerOffenseTermItem.getConnection()
												.getClassification()))) {
							if (new Long(consecutiveCounter).equals(offenseTermItem
										.getConnection().getIndex())
									&& innerOffenseTermItem.getConnection()
										.getIndex().equals(new Long(index))) {
								errors.rejectValue(
									"offenseItems[" + index + "].connection",
									"offenseTermForm.sentence"
											+ ".connectionCircular");
								countRejected++;
							}
							consecutiveCounter++;
						}
					}
				} else if (OffenseItemConnectionClassification
						.CONSECUTIVE_OTHER_DOCKET
							.equals(offenseTermItem.getConnection()
								.getClassification())) {
					
					// Checks only existing sentences as only existing sentences
					// can be connected to sentences on other dockets
					if (SentenceOperation.UPDATE.equals(
								offenseTermItem.getSentenceOperation())
							|| SentenceOperation.AMEND.equals(
								offenseTermItem.getSentenceOperation())) {
						Sentence sentence = this.resolveSentence(
								offenseTermItem.getConnection());
						if (sentence != null
								&& SentenceConnectionClassification.CONSECUTIVE
									.equals(sentence.getConnection()
											.getClassification())
								&& sentence.getConnection().getSentence()
									.equals(offenseTermItem.getSentence())) {
							errors.rejectValue(
								"offenseItems[" + index + "].connection",
								"offenseTermForm.sentence"
										+ ".connectionCircularInOtherDocket");
							countRejected++;
						}
					}
				}
			}
			if ((offenseTermItem.getExpanded() == null
					|| !offenseTermItem.getExpanded()) && countRejected > 0) {
				offenseTermItem.setExpanded(true);
			}
			index++;
		}
	}
	
	// Resolves sentence from offender item connection
	private Sentence resolveSentence(
			final OffenseItemConnection connection) {
		
		// Manual resolution of entities by their IDs is discouraged; in this
		// case, it is a necessary evil as connection.index represents
		// different types (form item indexes when sentence is on same docket
		// or sentence on other dockets) - SA
		PropertyEditor propertyEditor = this.sentencePropertyEditorFactory
				.createPropertyEditor();
		propertyEditor.setAsText(connection.getIndex().toString());
		return (Sentence) propertyEditor.getValue(); 
	}
}