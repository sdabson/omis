package omis.offenderphoto.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.offenderphoto.domain.OffenderPhotoAssociation;
import omis.offenderphoto.domain.OffenderPhotoAssociationNote;

/**
 * Offender photo association note implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Dec 15, 2016)
 * @since OMIS 3.0
 */
public class OffenderPhotoAssociationNoteImpl
	implements OffenderPhotoAssociationNote {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String value;
	private Date date;
	private OffenderPhotoAssociation association;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates an instance of offender photo association note.
	 */
	public OffenderPhotoAssociationNoteImpl() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public String getValue() {
		return this.value;
	}

	/** {@inheritDoc} */
	@Override
	public void setValue(final String value) {
		this.value = value;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderPhotoAssociation getAssociation() {
		return this.association;
	}

	/** {@inheritDoc} */
	@Override
	public void setAssociation(final OffenderPhotoAssociation association) {
		this.association = association;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof OffenderPhotoAssociationNote)) {
			return false;
		}
		
		OffenderPhotoAssociationNote that = (OffenderPhotoAssociationNote) o;
		
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required");
		}
		if (!this.getValue().equals(that.getValue())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (this.getAssociation() == null) {
			throw new IllegalStateException("Association required");
		}
		if (!this.getAssociation().equals(that.getAssociation())) {
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
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (this.getAssociation() == null) {
			throw new IllegalStateException("Association required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getValue().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getAssociation().hashCode();
		
		return hashCode;
	}
}