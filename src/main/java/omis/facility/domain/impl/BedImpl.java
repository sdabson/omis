package omis.facility.domain.impl;

import omis.facility.domain.Bed;
import omis.facility.domain.Room;

/**
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 06 2013)
 * @since OMIS 3.0
 */
public class BedImpl implements Bed {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer number;
	
	private Room room;

	private Boolean active;
	
	/** Instantiates a default bed implementation. */
	public BedImpl() {
		//Empty constructor
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
	public Room getRoom() {
		return this.room;
	}

	/** {@inheritDoc} */
	@Override
	public void setRoom(final Room room) {
		this.room = room;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getNumber() {
		return this.number;
	}

	/** {@inheritDoc} */
	@Override
	public void setNumber(final Integer number) {
		this.number = number;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getActive() {
		return this.active;
	}

	/** {@inheritDoc} */
	@Override
	public void setActive(final Boolean active) {
		this.active = active;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof Bed)) {
			return false;
		}
		
		Bed that = (Bed) o;
		
		if (this.getRoom() == null) {
			throw new IllegalStateException("Room required");
		}
		if (!this.getRoom().equals(that.getRoom())) {
			return false;
		}
		if (this.getNumber() == null) {
			throw new IllegalStateException("Number required");
		}
		if (!this.getNumber().equals(that.getNumber())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		
		if (this.getRoom() == null) {
			throw new IllegalStateException("Room required");
		}
		if (this.getNumber() == null) {
			throw new IllegalStateException("Number required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getRoom().hashCode();
		hashCode = 29 * hashCode + this.getNumber().hashCode();
		
		return hashCode;
	}
}
