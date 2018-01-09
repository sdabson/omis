package omis.search.report;

import java.io.Serializable;

/** Person search result.
 * @author Yidong Li
 * @version 0.1.0 (March 20, 2015)
 * @since OMIS 3.0 */
public class EmployerSearchResult implements Serializable {
	private static final long serialVersionUID = 1L;

	private String employerName;
	private Long employerId;
	private String value;
	private String cityName;
	private String stateName;

	/** constructor for a employer search result.
	 * @param employerId id for employer.
	 * @param employerName employer name.
	 */

	public EmployerSearchResult(final String employerName,
		final Long employerId, final String value, final String cityName, final String stateName){
		this.employerName = employerName;
		this.employerId = employerId;
		this.value = value;
		this.cityName = cityName;
		this.stateName = stateName;
	}

	/** sets the employerId.
	 * @param employerId */
	public void setEmployerId(final Long employerId) { 
		this.employerId = employerId; }
	
	/** gets the employerID.
	 * @return employerId. */
	public Long getEmployerId() { return this.employerId; }
	
	/** sets the employerName.
	 * @param employerName */
	public void setEmployerName(final String employerName) {
		this.employerName = employerName;
	}
	
	/** gets the employerName.
	 * @return employerName */
	public String getEmployerName() {
		return this.employerName;
	}

	/**
	 * Gets address value.
	 * 
	 * @return address value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets address value.
	 * 
	 * @param value
	 */
	public void setValue(final String value) {
		this.value = value;
	}

	/** sets the city name.
	 * @param city name */
	public void setCityName(final String cityName) {
		this.cityName = cityName;
	}
	
	/** gets the city name.
	 * @return city name */
	public String getCityName() {
		return this.cityName;
	}
	
	/** sets the state name.
	 * @param state name */
	public void setStateName(final String stateName) {
		this.stateName = stateName;
	}
	
	/** gets the state name.
	 * @return state name */
	public String getStateName() {
		return this.stateName;
	}
} 