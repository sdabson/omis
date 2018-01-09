package omis.paroleboarditinerary.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.location.domain.Location;
import omis.paroleboarditinerary.domain.BoardMeetingSite;
import omis.paroleboarditinerary.domain.ParoleBoardItinerary;

/**
 * Implementation of board meeting site.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 16, 2017)
 * @since OMIS 3.0
 */
public class BoardMeetingSiteImpl implements BoardMeetingSite {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private ParoleBoardItinerary boardItinerary;
	
	private Location location;
	
	private Date date;
	
	private Integer order;
	
	/** 
	 * Instantiates an implementation of parole board itinerary. 
	 */
	public BoardMeetingSiteImpl() {
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
	public void setBoardItinerary(
			final ParoleBoardItinerary paroleBoardItinerary) {
		this.boardItinerary = paroleBoardItinerary;
	}

	/** {@inheritDoc} */
	@Override
	public ParoleBoardItinerary getBoardItinerary() {
		return boardItinerary;
	}

	/** {@inheritDoc} */
	@Override
	public void setLocation(final Location location) {
		this.location = location;
	}

	/** {@inheritDoc} */
	@Override
	public Location getLocation() {
		return location;
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
	public void setOrder(final Integer order) {
		this.order = order;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getOrder() {
		return order;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BoardMeetingSite)) {
			return false;
		}
		BoardMeetingSite that = (BoardMeetingSite) obj;
		if (this.getBoardItinerary() == null) {
			throw new IllegalStateException("Parole board itinerary required");
		}
		if (!this.getBoardItinerary().equals(
				that.getBoardItinerary())) {
			return false;
		}
		if (this.getLocation() == null) {
			throw new IllegalStateException("Location required");
		}
		if (!this.getLocation().equals(that.getLocation())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (this.getOrder() == null) {
			throw new IllegalStateException("Order required");
		}
		if (!this.getOrder().equals(that.getOrder())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getBoardItinerary() == null) {
			throw new IllegalStateException("Parole board itinerary required");
		}
		if (this.getLocation() == null) {
			throw new IllegalStateException("Location required");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (this.getOrder() == null) {
			throw new IllegalStateException("Order required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getBoardItinerary().hashCode();
		hashCode = 29 * hashCode + this.getLocation().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getOrder().hashCode();
		
		return hashCode;
	}
}
