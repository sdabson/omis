package omis.citation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.citation.domain.MisdemeanorCitation;
import omis.citation.domain.MisdemeanorDisposition;
import omis.citation.domain.MisdemeanorOffense;
import omis.datatype.Month;
import omis.offender.domain.Offender;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Misdemeanor citation implementation.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 5, 2016)
 * @since OMIS 3.0
 */

public class MisdemeanorCitationImpl implements MisdemeanorCitation {
	
	private static final long serialVersionUID = 1L; 
	
	private Long id;
	
	private Offender offender;
	
	private MisdemeanorOffense offense;

	private State state;
	
	private City city;
	
	private Integer day;
	
	private Month month;
	
	private Integer year;
	
	private Integer counts;
	
	private MisdemeanorDisposition disposition;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;

	
	/**
	 * Instantiates a default instance of misdemeanor citation.
	 */
	public MisdemeanorCitationImpl() {
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
	public Offender getOffender() {
		return offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public MisdemeanorOffense getOffense() {
		return offense;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffense(final MisdemeanorOffense offense) {
		this.offense = offense;
	}

	/** {@inheritDoc} */
	@Override
	public State getState() {
		return state;
	}

	/** {@inheritDoc} */
	@Override
	public void setState(State state) {
		this.state = state;
	}

	/** {@inheritDoc} */
	@Override
	public City getCity() {
		return city;
	}

	/** {@inheritDoc} */
	@Override
	public void setCity(City city) {
		this.city = city;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getDay() {
		return day;
	}

	/** {@inheritDoc} */
	@Override
	public void setDay(Integer day) {
		this.day = day;
	}

	/** {@inheritDoc} */
	@Override
	public Month getMonth() {
		return month;
	}

	/** {@inheritDoc} */
	@Override
	public void setMonth(Month month) {
		this.month = month;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getYear() {
		return year;
	}

	/** {@inheritDoc} */
	@Override
	public void setYear(Integer year) {
		this.year = year;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer getCounts() {
		return counts;
	}

	/** {@inheritDoc} */
	@Override
	public void setCounts(Integer counts) {
		this.counts = counts;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDisposition(final MisdemeanorDisposition disposition) {
		this.disposition = disposition;
	}
	
	/** {@inheritDoc} */
	@Override
	public MisdemeanorDisposition getDisposition() {
		return this.disposition;
	}
	
	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof MisdemeanorCitation)) {
			return false;
		}
		
		MisdemeanorCitation that = (MisdemeanorCitation) o;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getOffense() == null) {
			throw new IllegalStateException("Offense required.");
		}
		if (!this.getOffense().equals(that.getOffense())) {
			return false;
		}
		if (this.getYear() == null) {
			throw new IllegalStateException("Year required.");
		}
		if (!this.getYear().equals(that.getYear())) {
			return false;
		}
		if (this.getDisposition() == null) {
			throw new IllegalStateException("Disposition required.");
		}
		if (!this.getDisposition().equals(that.getDisposition())) {
			return false;
		}
	
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (this.getOffense() == null) {
			throw new IllegalStateException("Offense required.");
		}
		if (this.getYear() == null) {
			throw new IllegalStateException("Year required.");
		}
		if (this.getDisposition() == null) {
			throw new IllegalStateException("Disposition required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getOffense().hashCode();
		hashCode = 29 * hashCode + this.getYear().hashCode();
		hashCode = 29 * hashCode + this.getDisposition().hashCode();
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format(
				"Offender: #%s, Offense: %s, State: %s, City: %s, Day: %s"
				+ "Month: %s, Year: %s, Id: %s, Disposition: %s",
				this.getOffender(),
				this.getOffense(),
				this.getState(),
				this.getCity(),
				this.getDay(),
				this.getMonth(),
				this.getYear(),
				this.getId(),
				this.getDisposition());
	}

}
