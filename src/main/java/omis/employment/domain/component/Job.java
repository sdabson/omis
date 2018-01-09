package omis.employment.domain.component;

import java.io.Serializable;

import omis.employment.domain.Employer;
import omis.location.domain.Location;

/**
 * Job
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (Jan 30, 2015)
 * @since: OMIS 3.0
 */
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;
	private String jobTitle;
	private String phoneExtension;
	private Location employmentLocation;
	private Wage wage;
	private WorkShift workShift;
	private Employer employer;
	private String supervisorName;

	/** Instantiates a default job. */
	public Job() {
		// Do nothing
	}
	
	/**
	 * Sets job title
	 * 
	 * @param jobTitle job title
	 */
	public void setJobTitle(final String jobTitle){
		this.jobTitle = jobTitle;
	}
	
	/**
	 * Gets job title.
	 * 
	 * @return jobTitle job title
	 */
	public String getJobTitle(){
		return this.jobTitle;
	}
	
	/**
	 * Sets the phone extension
	 * 
	 * @param phoneExtension phone extension
	 */
	public void setPhoneExtension(final String phoneExtension){
		this.phoneExtension = phoneExtension;
	}
	
	/**
	 * Gets the phone extension.
	 * 
	 * @return phoneExtension phone extension
	 */
	public String getPhoneExtension(){
		return this.phoneExtension;
	}
	
	/**
	 * Sets the employment location
	 * 
	 * @param employmentLocation employment location
	 */
	public void setEmploymentLocation(final Location employmentLocation){
		this.employmentLocation = employmentLocation;
	}
	
	/**
	 * Gets the employment location.
	 * 
	 * @return employmentLocation employment location
	 */
	public Location getEmploymentLocation(){
		return this.employmentLocation;
	}

	/**
	 * Sets the wage
	 * 
	 * @param wage wage
	 */
	public void setWage(final Wage wage){
		this.wage = wage;
	}
	
	/**
	 * Gets the wage.
	 * 
	 * @return wage wage
	 */
	public Wage getWage(){
		return this.wage;
	}

	/**
	 * Sets the work shift
	 * 
	 * @param workShift work shift
	 */
	public void setWorkShift(final WorkShift workShift){
		this.workShift = workShift;
	}
	
	/**
	 * Gets the work shift.
	 * 
	 * @return workShift work shift
	 */
	public WorkShift getWorkShift(){
		return this.workShift;
	}
	
	/**
	 * Sets the employer
	 * 
	 * @param employer employer
	 */
	public void setEmployer(final Employer employer){
		this.employer = employer;
	}
	
	/**
	 * Gets the employer.
	 * 
	 * @return employer employer
	 */
	public Employer getEmployer(){
		return this.employer;
	}
	
	/**
	 * Sets the supervisor name
	 * 
	 * @param supervisor name
	 */
	public void setSupervisorName(final String supervisorName){
		this.supervisorName = supervisorName;
	}
	
	/**
	 * Gets the supervisor name.
	 * 
	 * @return supervisor name
	 */
	public String getSupervisorName(){
		return this.supervisorName;
	}
}