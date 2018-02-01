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

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.offenseterm.web.form.HistoricalOffenseTermForm;
import omis.sentence.web.validator.delegate.SentenceFieldsValidatorDelegate;

/**
 * Validator for form for historical offense terms.
 *
 * <p>Historical offense terms are represented by inactive offense terms.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class HistoricalOffenseTermFormValidator implements Validator {
	
	private final SentenceFieldsValidatorDelegate
	sentenceFieldsValidatorDelegate; 

	/**
	 * Instantiates validator for form for historical offense terms.
	 * 
	 * @param sentenceFieldsValidatorDelegate delegate for sentence fields
	 */
	public HistoricalOffenseTermFormValidator(
			final SentenceFieldsValidatorDelegate
				sentenceFieldsValidatorDelegate) {
		this.sentenceFieldsValidatorDelegate = sentenceFieldsValidatorDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return HistoricalOffenseTermForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		HistoricalOffenseTermForm form = (HistoricalOffenseTermForm) target;
		this.sentenceFieldsValidatorDelegate.validate(
				form.getSentenceFields(), "sentenceFields", errors);
	}
}