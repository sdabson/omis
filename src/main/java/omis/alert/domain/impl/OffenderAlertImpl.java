package omis.alert.domain.impl;

import java.util.Date;

import omis.alert.domain.OffenderAlert;
import omis.alert.domain.component.AlertResolution;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.offender.domain.Offender;

/**
 * Implementation of offender alert.
 * 
 * @author Jason Nelson
 * @author Stephen Abson
 * @version 0.1.1 (Dec 11, 2013)
 * @since OMIS 3.0
 */
public class OffenderAlertImpl implements OffenderAlert {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Offender offender;
	
	private String description;
	
	private Long expireDate;
	
	private AlertResolution resolution;
	
	private UpdateSignature updateSignature;
	
	private CreationSignature creationSignature;
	
	/** Instantiates an implementation of offender alert. */
	public OffenderAlertImpl() {
		// Default instantiation
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
	public Date getExpireDate() {
		if (this.expireDate != null) {
			return new Date(this.expireDate);
		} else {
			return null;
		}
		
	}

	/** {@inheritDoc} */
	@Override
	public void setExpireDate(final Date expireDate) {
		if (expireDate != null) {
			this.expireDate = expireDate.getTime();
		} else {
			this.expireDate = null;
		}
		
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
	public void setResolution(final AlertResolution resolution) {
		this.resolution = resolution;
	}

	/** {@inheritDoc} */
	@Override
	public AlertResolution getResolution() {
		return this.resolution;
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
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof OffenderAlert)) {
			return false;
		}
		OffenderAlert that = (OffenderAlert) obj;
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required");
		}
		if (!this.getDescription().equals(that.getDescription())) {
			return false;
		}
		if (this.getExpireDate() == null) {
			throw new IllegalStateException("Expire Date required");
		}
		if (!this.getExpireDate().equals(that.getExpireDate())) {
			return false;
		}
		return true;
	}
		
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 14;
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Alert Description required");
		}
		if (this.getExpireDate() == null) {
			throw new IllegalStateException("Expire Date required");
		}
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		hashCode = 29 * hashCode + this.getExpireDate().hashCode();	
		return hashCode;
	}
}