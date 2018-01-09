package omis.mail.domain.impl;

import omis.address.domain.ZipCode;
import omis.mail.domain.PoBox;

/**
 * Implementation of PO box.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 28, 2014)
 * @since OMIS 3.0
 * @deprecated use {@code omis.contact.domain.component.PoBox} instead
 */
@Deprecated
public class PoBoxImpl
		implements PoBox {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String value;
	
	private ZipCode zipCode;

	/** Instantiates an implementation of PO box. */
	public PoBoxImpl() {
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
	public void setZipCode(final ZipCode zipCode) {
		this.zipCode = zipCode;
	}

	/** {@inheritDoc} */
	@Override
	public ZipCode getZipCode() {
		return this.zipCode;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof PoBox)) {
			return false;
		}
		PoBox that = (PoBox) obj;
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required");
		}
		if (!this.getValue().equals(that.getValue())) {
			return false;
		}
		if (this.getZipCode() == null) {
			throw new IllegalStateException("ZIP code required");
		}
		if (!this.getZipCode().equals(that.getZipCode())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required");
		}
		if (this.getZipCode() == null) {
			throw new IllegalStateException("ZIP code required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getValue().hashCode();
		hashCode = 29 * hashCode + this.getZipCode().hashCode();
		return hashCode;
	}
}