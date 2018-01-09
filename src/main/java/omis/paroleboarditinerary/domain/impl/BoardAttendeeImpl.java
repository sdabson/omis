package omis.paroleboarditinerary.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.paroleboarditinerary.domain.AttendeeRoleCategory;
import omis.paroleboarditinerary.domain.BoardAttendee;
import omis.paroleboarditinerary.domain.ParoleBoardItinerary;
import omis.paroleboardmember.domain.ParoleBoardMember;

/**
 * Implementation of board attendee.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 16, 2017)
 * @since OMIS 3.0
 */
public class BoardAttendeeImpl implements BoardAttendee {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private ParoleBoardItinerary boardItinerary;
	
	private ParoleBoardMember boardMember;
	
	private Long number;
	
	private AttendeeRoleCategory role;
	
	/** 
	 * Instantiates an implementation of parole board itinerary. 
	 */
	public BoardAttendeeImpl() {
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
			final ParoleBoardItinerary boardItinerary) {
		this.boardItinerary = boardItinerary;
	}

	/** {@inheritDoc} */
	@Override
	public ParoleBoardItinerary getBoardItinerary() {
		return boardItinerary;
	}

	/** {@inheritDoc} */
	@Override
	public void setBoardMember(final ParoleBoardMember boardMember) {
		this.boardMember = boardMember;
	}

	/** {@inheritDoc} */
	@Override
	public ParoleBoardMember getBoardMember() {
		return boardMember;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setNumber(final Long number) {
		this.number = number;
	}

	/** {@inheritDoc} */
	@Override
	public Long getNumber() {
		return number;
	}

	/** {@inheritDoc} */
	@Override
	public void setRole(final AttendeeRoleCategory role) {
		this.role = role;
	}

	/** {@inheritDoc} */
	@Override
	public AttendeeRoleCategory getRole() {
		return role;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BoardAttendee)) {
			return false;
		}
		BoardAttendee that = (BoardAttendee) obj;
		if (this.getBoardItinerary() == null) {
			throw new IllegalStateException("Board itinerary required");
		}
		if (!this.getBoardItinerary().equals(that.getBoardItinerary())) {
			return false;
		}
		if (this.getBoardMember() == null) {
			throw new IllegalStateException("Board member required");
		}
		if (!this.getBoardMember().equals(that.getBoardMember())) {
			return false;
		}
		if (this.getNumber() == null) {
			throw new IllegalStateException("Number required");
		}
		if (!this.getNumber().equals(that.getNumber())) {
			return false;
		}
		if (this.getRole() == null) {
			throw new IllegalStateException("Role required");
		}
		if (!this.getRole().equals(that.getRole())) {
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
		if (this.getBoardMember() == null) {
			throw new IllegalStateException("Board member required");
		}
		if (this.getNumber() == null) {
			throw new IllegalStateException("Number required");
		}
		if (this.getRole() == null) {
			throw new IllegalStateException("Role required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getBoardItinerary().hashCode();
		hashCode = 29 * hashCode + this.getBoardMember().hashCode();
		hashCode = 29 * hashCode + this.getNumber().hashCode();
		hashCode = 29 * hashCode + this.getRole().hashCode();
		
		return hashCode;
	}
}
