package omis.presentenceinvestigation.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.presentenceinvestigation.domain.SupervisionHistoryNote;
import omis.presentenceinvestigation.domain.SupervisionHistorySectionSummary;

/**
 * Supervision history note implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 8, 2017)
 * @since OMIS 3.0
 */
public class SupervisionHistoryNoteImpl implements SupervisionHistoryNote {

	private static final long serialVersionUID = 1L;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private String description;
	
	private Date date;
	
	private SupervisionHistorySectionSummary supervisionHistorySectionSummary;

	/** Instantiates an implementation of SupervisionHistoryNoteImpl */
	public SupervisionHistoryNoteImpl() {
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
	public SupervisionHistorySectionSummary 
		getSupervisionHistorySectionSummary() {
		return this.supervisionHistorySectionSummary;
	}

	/** {@inheritDoc} */
	@Override
	public void setSupervisionHistorySectionSummary(
			final SupervisionHistorySectionSummary 
			supervisionHistorySectionSummary) {
		this.supervisionHistorySectionSummary 
			= supervisionHistorySectionSummary;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SupervisionHistoryNote)) {
			return false;
		}
		
		SupervisionHistoryNote that = (SupervisionHistoryNote) obj;
		
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
		if (this.getSupervisionHistorySectionSummary() == null) {
			throw new IllegalStateException(
					"Supervision history section summary required.");
		}
		if (!this.getSupervisionHistorySectionSummary().equals(
				that.getSupervisionHistorySectionSummary())) {
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
		if (this.getSupervisionHistorySectionSummary() == null) {
			throw new IllegalStateException(
					"Supervision history section summary required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode 
				+ this.getSupervisionHistorySectionSummary().hashCode();
		
		return hashCode;
	}
}