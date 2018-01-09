package omis.victim.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.victim.domain.VictimDocketAssociation;
import omis.victim.domain.VictimDocketDocumentAssociation;

/**
 * Victim docket document association implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 08, 2017)
 * @since OMIS 3.0
 */
@Deprecated
public class VictimDocketDocumentAssociationImpl implements VictimDocketDocumentAssociation {

	private static final long serialVersionUID = 1L;
	private Long id;
	private VictimDocketAssociation victimDocketAssociation;
	private Document document;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default instance of victim docket document association.
	 */
	public VictimDocketDocumentAssociationImpl() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public VictimDocketAssociation getVictimDocketAssociation() {
		return this.victimDocketAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public void setVictimDocketAssociation(final VictimDocketAssociation victimDocketAssociation) {
		this.victimDocketAssociation = victimDocketAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public Document getDocument() {
		return this.document;
	}

	/** {@inheritDoc} */
	@Override
	public void setDocument(final Document document) {
		this.document = document;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
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
		if (!(o instanceof VictimDocketDocumentAssociation)) {
			return false;
		}
		
		VictimDocketDocumentAssociation that = (VictimDocketDocumentAssociation) o;
		
		if (this.getVictimDocketAssociation() == null) {
			throw new IllegalStateException("VictimDocketAssociation required.");
		}
		if (!this.getVictimDocketAssociation().equals(that.getVictimDocketAssociation())) {
			return false;
		}
		if (this.getDocument() == null) {
			throw new IllegalStateException("Document required");
		}
		if (!this.getDocument().equals(that.getDocument())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getVictimDocketAssociation() == null) {
			throw new IllegalStateException("VictimDocketAssociation required.");
		}
		if (this.getDocument() == null) {
			throw new IllegalStateException("Document required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getVictimDocketAssociation().hashCode();
		hashCode = 29 * hashCode + this.getDocument().hashCode();
		
		return hashCode;
	}
}