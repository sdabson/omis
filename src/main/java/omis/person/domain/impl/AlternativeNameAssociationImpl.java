package omis.person.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.person.domain.AlternativeNameAssociation;
import omis.person.domain.AlternativeNameCategory;
import omis.person.domain.PersonName;

/**
 * Implementation of alternative name association.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Nov 21, 2013)
 * @since OMIS 3.0
 */
public class AlternativeNameAssociationImpl
		implements AlternativeNameAssociation {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private CreationSignature creationSignature;

	private UpdateSignature updateSignature;

	private PersonName name;

	private DateRange dateRange;

	private AlternativeNameCategory category;
	
	/**
	 * Instantiates a default implementation of alternative name association.
	 */
	public AlternativeNameAssociationImpl() {
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
	public void setName(final PersonName name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public PersonName getName() {
		return this.name;
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
	public void setCategory(final AlternativeNameCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public AlternativeNameCategory getCategory() {
		return this.category;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof AlternativeNameAssociation)) {
			return false;
		}
		AlternativeNameAssociation that = (AlternativeNameAssociation) obj;
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required");
		}
		if (!this.getCategory().equals(that.getCategory())) {
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
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		if (this.getDateRange() != null) {
			hashCode = 29 * hashCode + this.getDateRange().hashCode();
		}
		return hashCode;
	}
}