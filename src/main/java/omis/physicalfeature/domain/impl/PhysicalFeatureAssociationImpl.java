package omis.physicalfeature.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.offender.domain.Offender;
import omis.physicalfeature.domain.PhysicalFeature;
import omis.physicalfeature.domain.PhysicalFeatureAssociation;

/**
 * Offender physical feature implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 21, 2013)
 * @since OMIS 3.0
 */
public class PhysicalFeatureAssociationImpl 
	implements PhysicalFeatureAssociation {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private PhysicalFeature feature;
	
	private String description;
	
	private Date originationDate;
	
	private Offender offender;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;

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
	public PhysicalFeature getFeature() {
		return this.feature;
	}

	/** {@inheritDoc} */
	@Override
	public void setFeature(final PhysicalFeature feature) {
		this.feature = feature;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/** {@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/** {@inheritDoc} */
	@Override
	public Date getOriginationDate() {
		return originationDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setOriginationDate(final Date originationDate) {
		this.originationDate = originationDate;
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
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
		if (!(o instanceof PhysicalFeatureAssociation)) {
			return false;
		}
		
		PhysicalFeatureAssociation that = (PhysicalFeatureAssociation) o;
		
		if (this.getFeature() == null) {
			throw new IllegalStateException("Feature required");
		}
		if (!this.getFeature().equals(that.getFeature())) {
			return false;
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required");
		}
		if (!this.getDescription().equals(that.getDescription())){
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getFeature() == null) {
			throw new IllegalStateException("Feature required");
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getFeature().hashCode();
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		
		return hashCode;
	}
}