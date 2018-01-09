package omis.employment.web.form;

import java.io.Serializable;

import omis.region.domain.City;
import omis.region.domain.State;

/**
 * @author cib168
 *
 */
public class SearchEmployerForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String employerName;
	
	private City city;
	
	private State state;
	
	/** Instantiates a default offender form. */
	public SearchEmployerForm() {
		//Default instantiation
	}

	
	/**
	 * Returns the employer name.
	 *
	 * @return the employerName
	 */
	public String getEmployerName() {
		return employerName;
	}

	/**
	 * Sets the employer name.
	 * 
	 * @param employerName the employerName to set
	 */
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	/**
	 * Returns the city.
	 * 
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 * 
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
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
	 * Sets the state.
	 * 
	 * @param state state
	 */
	public void setState(State state) {
		this.state = state;
	}	
}