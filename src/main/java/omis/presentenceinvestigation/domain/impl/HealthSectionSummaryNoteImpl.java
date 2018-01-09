package omis.presentenceinvestigation.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.presentenceinvestigation.domain.HealthSectionSummary;
import omis.presentenceinvestigation.domain.HealthSectionSummaryNote;

/**
 * Health section summary note implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 3, 2017)
 * @since OMIS 3.0
 */
public class HealthSectionSummaryNoteImpl implements HealthSectionSummaryNote {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String description;
	
	private Date date;
	
	private HealthSectionSummary healthSectionSummary;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;

	/** Instantiates an implementation of HealthSectionSummaryNoteImpl */
	public HealthSectionSummaryNoteImpl() {
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
		if (!(obj instanceof HealthSectionSummaryNote)) {
			return false;
		}
		
		HealthSectionSummaryNote that = (HealthSectionSummaryNote) obj;
		
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required.");
		}
		if (!this.getDescription().equals(that.getDescription())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (!this.getDate().equals(that.getDate())) {
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
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required.");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (this.getHealthSectionSummary() == null) {
			throw new IllegalStateException("Health section summary required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getHealthSectionSummary().hashCode();
		
		return hashCode;
	}
}