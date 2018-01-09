package omis.employment.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Employment summary
 * @author Yidong 
 * @version 0.1.0 (Feb 9, 2015)
 * @since OMIS 3.0
 */

public class EmploymentSummary implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Long id;
	private final Date startDate;
	private final Date endDate;
	private final String employerName;
	private final String value;
	private final String cityName;
	private final String stateName;
	private final String zipCode;
	private final String zipCodeExtension;
	private final String jobTitle;
	private final String supervisorName;
	private final String countryName;
	
	/**
	 * Instantiates a default instance of the employment history item.
	 */
	public EmploymentSummary(
		final Long id,
		final Date startDate,
		final Date endDate,
		final String employerName,
		final String value,
		final String cityName,
		final String stateName,
		final String zipCode,
		final String zipCodeExtension,
		final String jobTitle,
		final String supervisorName,
		final String countryName
		){
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employerName = employerName;
		this.value = value;
		this.cityName = cityName;
		this.stateName = stateName;
		this.zipCode = zipCode;
		this.zipCodeExtension = zipCodeExtension;
		this.jobTitle = jobTitle;
		this.supervisorName = supervisorName;
		this.countryName = countryName;
	}
	
	/**
	 * Return the employment history term Id.
	 * 
	 * @return the employment history term Id
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Return the start date.
	 * 
	 * @return the start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}
	
	/**
	 * Return the end date.
	 * 
	 * @return the end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}
	
	/**
	 * Return the employer name.
	 * 
	 * @return the employer name
	 */
	public String getEmployerName() {
		return this.employerName;
	}
	
	/**
	 * Returns address value.
	 * 
	 * @return address value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Return city name.
	 * 
	 * @return city name
	 */
	public String getCityName() {
		return this.cityName;
	}
	
	/**
	 * Return state name.
	 * 
	 * @return state name
	 */
	public String getStateName() {
		return this.stateName;
	}
	
	/**
	 * Return zip code.
	 * 
	 * @return zip code
	 */
	public String getZipCode() {
		return this.zipCode;
	}
	
	/**
	 * Return zip code extension.
	 * 
	 * @return zip code extension
	 */
	public String getZipCodeExtension() {
		return this.zipCodeExtension;
	}
	
	/**
	 * Return job title.
	 * 
	 * @return jobTitle job title
	 */
	public String getJobTitle() {
		return this.jobTitle;
	}
	
	/**
	 * Return supervisor name.
	 * 
	 * @return supervisor name
	 */
	public String getSupervisorName() {
		return this.supervisorName;
	}
	
	/**
	 * Return country name.
	 * 
	 * @return country name
	 */
	public String getCountryName() {
		return this.countryName;
	}
}
