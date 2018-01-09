package omis.person.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.person.domain.AlternativeIdentityAssociation;
import omis.person.domain.AlternativeIdentityCategory;
import omis.person.domain.AlternativeNameAssociation;
import omis.person.domain.PersonIdentity;

/**
 * Implementation of alternative identity association.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Nov 21, 2013)
 * @since OMIS 3.0
 */
public class AlternativeIdentityAssociationImpl
		implements AlternativeIdentityAssociation {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private CreationSignature creationSignature;

	private UpdateSignature updateSignature;

	private PersonIdentity identity;

	private DateRange dateRange;

	private AlternativeIdentityCategory category;
	
	private AlternativeNameAssociation alternativeNameAssociation;
	
	/**
	 * Instantiates a default implementation of alternative identity
	 * association.
	 */
	public AlternativeIdentityAssociationImpl() {
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
	public void setIdentity(final PersonIdentity identity) {
		this.identity = identity;
	}

	/** {@inheritDoc} */
	@Override
	public PersonIdentity getIdentity() {
		return this.identity;
	}

	/** {@inheritDoc} */
	@Override
	public void setDateRange(final DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(
			final AlternativeIdentityCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public AlternativeIdentityCategory getCategory() {
		return this.category;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setAlternativeNameAssociation(
			final AlternativeNameAssociation alternativeNameAssociation) {
		this.alternativeNameAssociation = alternativeNameAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public AlternativeNameAssociation getAlternativeNameAssociation() {
		return this.alternativeNameAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof AlternativeIdentityAssociation)) {
			return false;
		}
		AlternativeIdentityAssociation that =
				(AlternativeIdentityAssociation) obj;
		if (this.getIdentity() == null) {
			throw new IllegalStateException("Identity required");
		}
		if (!this.getIdentity().equals(that.getIdentity())) {
			return false;
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required");
		}
		if (!this.getCategory().equals(that.getCategory())) {
			return false;
		}
		if (this.getAlternativeNameAssociation() == null) {
			throw new IllegalStateException("Alternate name required");
		}
		if (!this.getAlternativeNameAssociation().equals(that.getAlternativeNameAssociation())) {
			return false;
		}
		if (this.getDateRange() != null) {
			if (!this.getDateRange().equals(that.getDateRange())) {
				return false;
			}
		} else if (that.getDateRange() != null) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getIdentity() == null) {
			throw new IllegalStateException("Identity required");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required");
		}
		if (this.getAlternativeNameAssociation() == null) {
			throw new IllegalStateException("Alternate name required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getIdentity().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		hashCode = 29 * hashCode + this.getAlternativeNameAssociation().hashCode();
		if (this.getDateRange() != null) {
			hashCode = 29 * hashCode + this.getDateRange().hashCode();
		}
		return hashCode;
	}
	
}