package omis.visitation.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.visitation.domain.VisitationAssociation;
import omis.visitation.domain.VisitationAssociationNote;

/**
 * Implementation for visitation association note.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 9, 2017)
 * @since OMIS 3.0
 */
public class VisitationAssociationNoteImpl 
	implements VisitationAssociationNote {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String value;
	private Date date;
	private VisitationAssociation association;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default instance of visitation association note.
	 */
	public VisitationAssociationNoteImpl() {
		//Default constructor.
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public VisitationAssociation getAssociation() {
		return association;
	}

	public void setAssociation(VisitationAssociation association) {
		this.association = association;
	}

	public CreationSignature getCreationSignature() {
		return creationSignature;
	}

	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	public void setUpdateSignature(UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof VisitationAssociationNote)) {
			return false;
		}
		
		VisitationAssociationNote that = (VisitationAssociationNote) o;
		
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required.");
		}
		if (!this.getValue().equals(that.getValue())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (this.getAssociation() == null) {
			throw new IllegalStateException("Association required.");
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
			throw new IllegalStateException("Value required.");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (this.getAssociation() == null) {
			throw new IllegalStateException("Association required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getValue().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getAssociation().hashCode();
		
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "ID: " + this.id + " Value: " + this.value
				+ " Date: " + this.date 
				+ " Association: " + this.association
				+ " Creation Signature: " + this.creationSignature
				+ " Update Signature: " + this.updateSignature;
	}
}