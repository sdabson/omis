package omis.physicalfeature.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.physicalfeature.domain.PhysicalFeatureAssociation;
import omis.physicalfeature.domain.PhysicalFeatureAssociationNote;

/**
 * Physical feature association note implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 5, 2015)
 * @since OMIS 3.0
 */
public class PhysicalFeatureAssociationNoteImpl
 implements PhysicalFeatureAssociationNote {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String note;
	
	private Date date;
	
	private PhysicalFeatureAssociation physicalFeatureAssociation;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default instance of physical feature association note.
	 */
	public PhysicalFeatureAssociationNoteImpl() {
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
	public String getNote() {
		return this.note;
	}

	/** {@inheritDoc} */
	@Override
	public void setNote(final String note) {
		this.note = note;
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
	public PhysicalFeatureAssociation getPhysicalFeatureAssociation() {
		return this.physicalFeatureAssociation;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setPhysicalFeatureAssociation(
			final PhysicalFeatureAssociation physicalFeatureAssociation) {
		this.physicalFeatureAssociation = physicalFeatureAssociation;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof PhysicalFeatureAssociationNote)) {
			return false;
		}
		
		PhysicalFeatureAssociationNote that = (PhysicalFeatureAssociationNote) o;
		
		if (this.getNote() == null) {
			throw new IllegalStateException("Note required.");
		}
		if (!this.getNote().equals(that.getNote())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (this.getPhysicalFeatureAssociation() == null) {
			throw new IllegalStateException(
					"PhysicalFeatureAssociation required");
		}
		if (!this.getPhysicalFeatureAssociation().equals(
				that.getPhysicalFeatureAssociation())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getNote() == null) {
			throw new IllegalStateException("Note required.");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (this.getPhysicalFeatureAssociation() == null) {
			throw new IllegalStateException(
					"PhysicalFeatureAssociation required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getNote().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getPhysicalFeatureAssociation()
				.hashCode();
		
		return hashCode;
	}
}