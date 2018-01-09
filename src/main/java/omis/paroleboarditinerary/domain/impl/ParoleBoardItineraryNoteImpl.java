package omis.paroleboarditinerary.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.paroleboarditinerary.domain.ParoleBoardItinerary;
import omis.paroleboarditinerary.domain.ParoleBoardItineraryNote;

/**
 * Implementation of parole board itinerary note.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 16, 2017)
 * @since OMIS 3.0
 */
public class ParoleBoardItineraryNoteImpl implements ParoleBoardItineraryNote {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private ParoleBoardItinerary boardItinerary;
	
	private String description;
	
	private Date date;
	
	/** 
	 * Instantiates an implementation of parole board itinerary note. 
	 */
	public ParoleBoardItineraryNoteImpl() {
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
		return creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setBoardItinerary(final ParoleBoardItinerary boardItinerary) {
		this.boardItinerary = boardItinerary;
	}

	/** {@inheritDoc} */
	@Override
	public ParoleBoardItinerary getBoardItinerary() {
		return boardItinerary;
	}

	/** {@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return description;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return date;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ParoleBoardItineraryNote)) {
			return false;
		}
		ParoleBoardItineraryNote that = (ParoleBoardItineraryNote) obj;
		if (this.getBoardItinerary() == null) {
			throw new IllegalStateException("Board itinerary required");
		}
		if (!this.getBoardItinerary().equals(that.getBoardItinerary())) {
			return false;
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required");
		}
		if (!this.getDescription().equals(that.getDescription())) {
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
		if (this.getBoardItinerary() == null) {
			throw new IllegalStateException("Board itinerary required");
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getBoardItinerary().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		
		return hashCode;
	}
}
