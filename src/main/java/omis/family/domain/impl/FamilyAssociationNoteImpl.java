package omis.family.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.family.domain.FamilyAssociation;
import omis.family.domain.FamilyAssociationNote;

/**
 * Family association note implementation.
 * 
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.1.1 (June 1, 2015)
 * @since OMIS 3.0
 */
public class FamilyAssociationNoteImpl implements FamilyAssociationNote {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date date;
	private String value;
	private FamilyAssociation association;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
	/** Instantiates an implementation of family association note. */
	public FamilyAssociationNoteImpl() {
		// do nothing
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return date;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public String getValue() {
		return value;
	}

	/** {@inheritDoc} */
	@Override
	public void setValue(final String value) {
		this.value = value;
	}
	
	/** {@inheritDoc} */
	@Override
	public FamilyAssociation getAssociation() {
		return association;
	}

	/** {@inheritDoc} */
	@Override
	public void setAssociation(final FamilyAssociation association) {
		this.association = association;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FamilyAssociationNote)) {
			return false;
		}
		
		FamilyAssociationNote that = (FamilyAssociationNote) obj;
		
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required.");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (this.getAssociation() == null) {
			throw new IllegalStateException("Family association required.");
		}
		if (!this.getAssociation().equals(that.getAssociation())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getAssociation() == null) {
			throw new IllegalStateException("Family relationship required.");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getAssociation().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getValue().hashCode();
		return hashCode;
	}

	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
		
	}

	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}
}