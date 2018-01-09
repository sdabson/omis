package omis.residence.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Residence search form.
 *
 * @author Sheronda Vaughn
 * @author Joel Norris
 * @version 0.1.1 (July 11, 2017)
 * @since OMIS 3.0
 */

public class ResidenceSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	private String value;
	
	private State state;
	
	private City city;
	
	private Date effectiveDate;

	/** Instantiates a default residence form. */
	public ResidenceSearchForm() {
		// Default instantiation
	}

	/**
	 * Returns the street value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Returns the state.
	 * 
	 * @return the state
	 */
	public State getState() {
		return this.state;
	}

	/**
	 * Returns the city.
	 * 
	 * @return the city
	 */
	public City getCity() {
		return this.city;
	}

	/**
	 * Returns the effective date.
	 * 
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	/**
	 * Sets the street value.
	 * 
	 * @param value value
	 */
	public void setValue(final String value) {
		this.value = value;
	}

	/**
	 * Sets the state.
	 * 
	 * @param state state
	 */
	public void setState(final State state) {
		this.state = state;
	}

	/**
	 * Sets the city.
	 * 
	 * @param city city
	 */
	public void setCity(final City city) {
		this.city = city;
	}

	/**
	 * Sets the effective date.
	 * 
	 * @param effectiveDate effective date
	 */
	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}	
}