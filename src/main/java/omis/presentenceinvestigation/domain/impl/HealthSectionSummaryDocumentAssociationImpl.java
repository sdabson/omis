package omis.presentenceinvestigation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.presentenceinvestigation.domain.HealthSectionSummary;
import omis.presentenceinvestigation.domain.HealthSectionSummaryDocumentAssociation;

/**
 * Health section summary documentation association implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 3, 2017)
 * @since OMIS 3.0
 */
public class HealthSectionSummaryDocumentAssociationImpl 
implements HealthSectionSummaryDocumentAssociation {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Document document;
	
	private HealthSectionSummary healthSectionSummary;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/** Instantiates an implementation of HealthSectionSummaryDocumentAssociationImpl */
	public HealthSectionSummaryDocumentAssociationImpl() {
		// Default constructor.
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
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
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
	public HealthSectionSummary getHealthSectionSummary() {
		return this.healthSectionSummary;
	}

	/** {@inheritDoc} */
	@Override
	public void setHealthSectionSummary(
			final HealthSectionSummary healthSectionSummary) {
		this.healthSectionSummary = healthSectionSummary;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof HealthSectionSummaryDocumentAssociation)) {
			return false;
		}
		
		HealthSectionSummaryDocumentAssociation that 
			= (HealthSectionSummaryDocumentAssociation) obj;
		
		if (this.getDocument() == null) {
			throw new IllegalStateException("Document required.");
		}
		if (!this.getDocument().equals(
				that.getDocument())) {
			return false;
		}
		if (this.getHealthSectionSummary() == null) {
			throw new IllegalStateException("Health section summary required.");
		}
		if (!this.getHealthSectionSummary().equals(
				that.getHealthSectionSummary())) {
			return false;
		}
		
		return true;		
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getDocument() == null) {
			throw new IllegalStateException("Document required.");
		}
		if (this.getHealthSectionSummary() == null) {
			throw new IllegalStateException("Health section summary required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getDocument().hashCode();
		hashCode = 29 * hashCode + this.getHealthSectionSummary().hashCode();
		
		return hashCode;
	}
}