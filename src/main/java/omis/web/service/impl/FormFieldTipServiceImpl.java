package omis.web.service.impl;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.web.domain.FormFieldTip;
import omis.web.service.FormFieldTipService;
import omis.web.service.delegate.FormFieldTipDelegate;

/**
 * Implementation of service for form field tip.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 18, 2015)
 * @since OMIS 3.0
 */
public class FormFieldTipServiceImpl 
		implements FormFieldTipService {
	
	/* Helpers. */
	
	private final FormFieldTipDelegate formFieldTipDelegate;
	
	/* Constructors. */
	
	public FormFieldTipServiceImpl(
			final FormFieldTipDelegate formFieldTipDelegate) {
		this.formFieldTipDelegate = formFieldTipDelegate;
	}
	
	/* Methods. */

	/** {@inheritDoc} */
	@Override
	public FormFieldTip create(
			final String form, final String fieldName, final String value)
					throws DuplicateEntityFoundException {
		return this.formFieldTipDelegate.create(form, fieldName, value);
	}

	/** {@inheritDoc} */
	@Override
	public FormFieldTip update(
			final FormFieldTip tip, final String value) {
		return this.formFieldTipDelegate.update(tip, value);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final FormFieldTip tip) {
		this.formFieldTipDelegate.remove(tip);
	}

	/** {@inheritDoc} */
	@Override
	public List<FormFieldTip> findByForm(final String form) {
		return this.formFieldTipDelegate.findByForm(form);
	}
}