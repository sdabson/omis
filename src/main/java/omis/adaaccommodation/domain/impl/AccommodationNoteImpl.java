package omis.adaaccommodation.domain.impl;

import java.util.Date;

import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.AccommodationNote;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;

/**
 * Implementation of ADA accommodation note.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 15, 2015)
 * @since OMIS 3.0
 */
public class AccommodationNoteImpl 
	implements AccommodationNote {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private String text;
	
	private Date date;
	
	private Accommodation accommodation;

	/** Instantiates an implementation of the ADA accommodation note. */
	public AccommodationNoteImpl() {
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
	public void setText(final String text) {
		this.text = text;
	}

	/** {@inheritDoc} */
	@Override
	public String getText() {
		return this.text;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	@Override
	public void setAccommodation(final Accommodation accommodation) {
		this.accommodation = accommodation;
	}
	
	@Override
	public Accommodation getAccommodation() {
		return this.accommodation;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AccommodationNote)) {
			return false;
		}
		AccommodationNote that = (AccommodationNote) obj;
		if (this.getText() == null) {
			throw new IllegalStateException("Text required");
		}
		if (!this.getText().equals(that.getText())) {
			return false;
		}
		if (this.getAccommodation() == null) {
			throw new IllegalStateException("Accommodation required");
		}
		if (!this.getAccommodation().equals(that.getAccommodation())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getText() == null) {
			throw new IllegalStateException("Text required");
		}
		if (this.getAccommodation() == null) {
			throw new IllegalStateException("Accommodation required");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getText().hashCode();
		hashCode = 29 * hashCode + this.getAccommodation().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		
		return hashCode;
	}
}