package omis.identificationnumber.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.identificationnumber.domain.IdentificationNumber;
import omis.identificationnumber.domain.IdentificationNumberCategory;
import omis.identificationnumber.domain.IdentificationNumberIssuer;
import omis.offender.domain.Offender;

/**
 * Implementation of identification number.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class IdentificationNumberImpl
		implements IdentificationNumber {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Offender offender;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private IdentificationNumberIssuer issuer;
	
	private IdentificationNumberCategory category;
	
	private String value;
	
	private Date issueDate;
	
	private Date expireDate;
	
	/**
	 * Instantiates implementation of identification number.
	 */
	public IdentificationNumberImpl() {
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
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(
			final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setIssuer(final IdentificationNumberIssuer issuer) {
		this.issuer = issuer;
	}

	/** {@inheritDoc} */
	@Override
	public IdentificationNumberIssuer getIssuer() {
		return this.issuer;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(
			final IdentificationNumberCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public IdentificationNumberCategory getCategory() {
		return this.category;
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
	public void setIssueDate(final Date issueDate) {
		this.issueDate = issueDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getIssueDate() {
		return this.issueDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setExpireDate(final Date expireDate) {
		this.expireDate = expireDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getExpireDate() {
		return this.expireDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof IdentificationNumber)) {
			return false;
		}
		IdentificationNumber that = (IdentificationNumber) obj;
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getIssuer() == null) {
			throw new IllegalStateException("Issuer required");
		}
		if (!this.getIssuer().equals(that.getIssuer())) {
			return false;
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required");
		}
		if (!this.getCategory().equals(that.getCategory())) {
			return false;
		}
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required");
		}
		if (!this.getValue().equals(that.getValue())) {
			return false;
		}
		if (this.getIssueDate() != null) {
			if (!this.getIssueDate().equals(that.getIssueDate())) {
				return false;
			}
		} else if (that.getIssueDate() != null) {
			return false;
		}
		if (this.getExpireDate() != null) {
			if (this.getExpireDate().equals(that.getExpireDate())) {
				return false;
			}
		} else if (that.getExpireDate() != null) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (this.getIssuer() == null) {
			throw new IllegalStateException("Issuer required");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required");
		}
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getIssuer().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		hashCode = 29 * hashCode + this.getValue().hashCode();
		if (this.getIssueDate() == null) {
			hashCode = 29 * hashCode + this.getIssueDate().hashCode();
		}
		if (this.getExpireDate() == null) {
			hashCode = 31 * hashCode + this.getExpireDate().hashCode();
		}
		return hashCode;
	}
	
	/**
	 * Returns string representation of {@code this}.
	 * 
	 * <p>See {@link Object#toString}.
	 * @return string representation of {@code this}
	 */
	@Override
	public String toString() {
		String offender;
		if (this.getOffender() != null) {
			offender = this.getOffender().getOffenderNumber().toString();
		} else {
			offender = null;
		}
		String issuerName;
		if (this.getIssuer() != null) {
			issuerName = this.getIssuer().getName();
		} else {
			issuerName = null;
		}
		String categoryName;
		if (this.getCategory() != null) {
			categoryName = this.getCategory().getName();
		} else {
			categoryName = null;
		}
		return String.format(
				"offender: %s; issuer: %s; category: %s; value: %s;"
					+ " issue date: %s; expire date: %s",
				offender, issuerName, categoryName, this.getValue(),
					this.getIssueDate(), this.getExpireDate());
	}
}