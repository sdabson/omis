package omis.courtcase.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.domain.CourtCaseNote;

public class CourtCaseNoteImpl implements CourtCaseNote {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private CourtCase courtCase;
	
	private Date date;
	
	private String value;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;

	/**
	 * Instantiates a default instance of court case note.
	 */
	public CourtCaseNoteImpl() {
		//Default constructor.
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public void setCourtCase(CourtCase courtCase) {
		this.courtCase = courtCase;
	}

	/** {@inheritDoc} */
	@Override
	public CourtCase getCourtCase() {
		return this.courtCase;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/** {@inheritDoc} */
	@Override
	public void setValue(String value) {
		this.value = value;
	}

	/** {@inheritDoc} */
	@Override
	public String getValue() {
		return this.value;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof CourtCaseNote)) {
			return false;
		}
		
		CourtCaseNote that = (CourtCaseNote) o;
		
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required.");
		}
		if (!this.getValue().equals(that.getValue())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getValue().hashCode();
		
		return hashCode;
	}
}
