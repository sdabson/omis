package omis.web.domain.impl;

import omis.web.domain.FormFieldTip;

/**
 * Implementation of form field tip.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 17, 2015)
 * @since OMIS 3.0
 */
public class FormFieldTipImpl
		implements FormFieldTip {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String form;

	private String fieldName;

	private String value;
	
	/** Implementation of form field tip. */
	public FormFieldTipImpl() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setForm(final String form) {
		this.form = form;
	}

	/** {@inheritDoc} */
	@Override
	public String getForm() {
		return this.form;
	}

	/** {@inheritDoc} */
	@Override
	public void setFieldName(final String fieldName) {
		this.fieldName = fieldName;
	}

	/** {@inheritDoc} */
	@Override
	public String getFieldName() {
		return this.fieldName;
	}

	/** {@inheritDoc} */
	@Override
	public void setValue(final String value) {
		this.value = value;
	}

	/** {@inheritDoc} */
	@Override
	public String getValue() {
		return this.value;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof FormFieldTip)) {
			return false;
		}
		FormFieldTip that = (FormFieldTip) obj;
		if (this.getForm() == null) {
			throw new IllegalStateException("Form required");
		}
		if (!this.getForm().equals(that.getForm())) {
			return false;
		}
		if (this.getFieldName() == null) {
			throw new IllegalStateException("Field name required");
		}
		if (!this.getFieldName().equals(that.getFieldName())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 14;
		if (this.getForm() == null) {
			throw new IllegalStateException("Form required");
		}
		if (this.getFieldName() == null) {
			throw new IllegalStateException("Field name required");
		}
		hashCode = 29 * hashCode + this.getForm().hashCode();
		hashCode = 29 * hashCode + this.getFieldName().hashCode();
		return hashCode;
	}
}