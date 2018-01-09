package omis.facility.domain.impl;

import omis.facility.domain.RoomDetail;

/**
 * @author Joel Norris
 * @version 0.1.0 (Feb, 06 2013)
 * @since OMIS 3.0
 */
public class RoomDetailImpl implements RoomDetail {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Integer occupancy;
	
	private Boolean permanent;
	
	/** Instantiates a default room detail implementation. */
	public RoomDetailImpl() {
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
	public Integer getOccupancy() {
		return this.occupancy;
	}

	/** {@inheritDoc} */
	@Override
	public void setOccupancy(final Integer occupancy) {
		this.occupancy = occupancy;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getPermanent() {
		return this.permanent;
	}

	/** {@inheritDoc} */
	@Override
	public void setPermanent(final Boolean permanent) {
		this.permanent = permanent;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof RoomDetail)) {
			return false;
		}
		
		RoomDetail that = (RoomDetail) o;
		
		if (this.getOccupancy() == null) {
			throw new IllegalStateException("Occupancy required");
		}
		if (!this.getOccupancy().equals(that.getOccupancy())) {
			return false;
		}
		if (this.getPermanent() == null) {
			throw new IllegalStateException("Permanent value required");
		}
		if (!this.getPermanent().equals(that.getPermanent())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOccupancy() == null) {
			throw new IllegalStateException("Occupancy required");
		}
		if (this.getPermanent() == null) {
			throw new IllegalStateException("Permanent value required");
		}
		
		int hashCode = 14;
			
		hashCode = 29 * hashCode + this.getOccupancy().hashCode();
		hashCode = 29 * hashCode + this.getPermanent().hashCode();
		
		return hashCode;
	}
}
