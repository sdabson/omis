package omis.health.domain.impl;

import java.util.Date;

import omis.facility.domain.Facility;
import omis.health.domain.HealthAppointment;
import omis.health.domain.HealthAppointmentStatus;

/**
 * Health appointment implementation.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public class HealthAppointmentImpl implements HealthAppointment {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Date date;
	
	private Date startTime;
	
	private Date endTime;
	
	private HealthAppointmentStatus status;
	
	private Date timeKept;
	
	private Facility facility;

	/**
	 * Instantiates a default health appointment implementation.
	 */
	public HealthAppointmentImpl() {
		//default constructor
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
	public Date getStartTime() {
		return this.startTime;
	}

	/** {@inheritDoc} */
	@Override
	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	/** {@inheritDoc} */
	@Override
	public Date getEndTime() {
		return this.endTime;
	}

	/** {@inheritDoc} */
	@Override
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	/** {@inheritDoc} */
	@Override
	public HealthAppointmentStatus getStatus() {
		return this.status;
	}

	/** {@inheritDoc} */
	@Override
	public void setStatus(final HealthAppointmentStatus status) {
		this.status = status;
	}

	/** {@inheritDoc} */
	@Override
	public Date getTimeKept() {
		return this.timeKept;
	}

	/** {@inheritDoc} */
	@Override
	public void setTimeKept(final Date timeKept) {
		this.timeKept = timeKept;
	}
	
	/** {@inheritDoc} */
	@Override
	public Facility getFacility() {
		return this.facility;
	}

	/** {@inheritDoc} */
	@Override
	public void setFacility(final Facility facility) {
		this.facility = facility;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof HealthAppointment)) {
			return false;
		}
		
		HealthAppointment that = (HealthAppointment) o;
		
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (this.getStartTime() == null) {
			throw new IllegalStateException("Start time required.");
		}
		if (!this.getStartTime().equals(that.getStartTime())) {
			return false;
		}
		if (this.getFacility() == null) {
			throw new IllegalStateException("Facility required.");
		}
		if (!this.getFacility().equals(that.getFacility())) {
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
		if (this.getStartTime() == null) {
			throw new IllegalStateException("Start time required.");
		}
		if (this.getFacility() == null) {
			throw new IllegalStateException("Facility required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getStartTime().hashCode();
		hashCode = 29 * hashCode + this.getFacility().hashCode();
		return hashCode;
	}
}