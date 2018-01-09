package omis.courtcase.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.courtcase.domain.Charge;
import omis.courtcase.domain.CourtCase;
import omis.offense.domain.Offense;

/**
 * Implementation of charge.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.2 (Aug 11, 2017)
 * @since OMIS 3.0
 */
public class ChargeImpl
		implements Charge {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private CreationSignature creationSignature;

	private UpdateSignature updateSignature;

	private Long date;

	private Long fileDate;

	private CourtCase courtCase;
	
	private Offense offense;

	private Integer counts;
	
	/** Instantiates a default implementation of charge. */
	public ChargeImpl() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
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
	public void setUpdateSignature(
			final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		if (date != null) {
			this.date = date.getTime();
		} else {
			this.date = null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		if (this.date != null) {
			return new Date(this.date);
		} else {
			return null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public void setFileDate(final Date fileDate) {
		if (fileDate != null) {
			this.fileDate = fileDate.getTime();
		} else {
			this.fileDate = null;
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getFileDate() {
		if (this.fileDate != null) {
			return new Date(this.fileDate);
		} else {
			return null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public void setCourtCase(final CourtCase courtCase) {
		this.courtCase = courtCase;
	}

	/** {@inheritDoc} */
	@Override
	public CourtCase getCourtCase() {
		return this.courtCase;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setOffense(final Offense offense) {
		this.offense = offense;
	}
	
	/** {@inheritDoc} */
	@Override
	public Offense getOffense() {
		return this.offense;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCounts(final Integer counts) {
		this.counts = counts;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getCounts() {
		return this.counts;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Charge)) {
			return false;
		}
		Charge that = (Charge) obj;
		if (this.getCourtCase() == null) {
			throw new IllegalStateException("Court case required");
		}
		if (!this.getCourtCase().equals(that.getCourtCase())) {
			return false;
		}
		if (this.getOffense() == null) {
			throw new IllegalStateException("Offense required");
		}
		if (!this.getOffense().equals(that.getOffense())) {
			return false;
		}
		if (this.getCounts() == null) {
			throw new IllegalStateException("Count required");
		}
		if (!this.getCounts().equals(that.getCounts())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getCourtCase() == null) {
			throw new IllegalStateException("Court case required");
		}
		if (this.getOffense() == null) {
			throw new IllegalStateException("Offense required");
		}
		if (this.getCounts() == null) {
			throw new IllegalStateException("Count required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getCourtCase().hashCode();
		hashCode = 29 * hashCode + this.getOffense().hashCode();
		hashCode = 29 * hashCode + this.getCounts().hashCode();
		return hashCode;
	}
}