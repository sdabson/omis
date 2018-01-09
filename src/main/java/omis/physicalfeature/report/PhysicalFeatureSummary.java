package omis.physicalfeature.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Physical need summary.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sep 6, 2016)
 * @since OMIS 3.0
 */
public class PhysicalFeatureSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String offenderFirstName;
	
	private String offenderLastName;
	
	private String offenderMiddleName;
	
	private String offenderSuffix;
	
	private Integer offenderNumber;
	
	private String featureClassificationName;
	
	private String physicalFeatureName;
	
	private String description;
	
	private Date date;
	
	

	/**
	 * Instantiates an implementation of Physical Feature Summary.
	 *
	 * @param id id
	 * @param featureClassificationName feature classification name
	 * @param physicalFeatureName physical feature name
	 * @param description description
	 * @param date date
	 */
	public PhysicalFeatureSummary(final Long id, final String offenderFirstName,
			final String offenderLastName, final String offenderMiddleName, 
			final String offenderSuffix, final Integer offenderNumber,			
			final String featureClassificationName, 
			final String physicalFeatureName, final String description, 
			final Date date) {
		this.id = id;
		this.offenderFirstName = offenderFirstName;
		this.offenderLastName = offenderLastName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderSuffix = offenderSuffix;
		this.offenderNumber = offenderNumber;
		this.featureClassificationName = featureClassificationName;
		this.physicalFeatureName = physicalFeatureName;
		this.description = description;
		this.date = date;
	}
	
	/**
	 * Gets the id of the  .
	 *
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the id of the   .
	 *
	 * @param id id
	 */
	public void setId(Long id) {
		this.id = id;}

	/**
	 * Gets the offenderFirstName of the  .
	 *
	 * @return the offenderFirstName
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}

	/**
	 * Sets the offenderFirstName of the   .
	 *
	 * @param offenderFirstName offenderFirstName
	 */
	public void setOffenderFirstName(String offenderFirstName) {
		this.offenderFirstName = offenderFirstName;
	}

	/**
	 * Gets the offenderLastName of the  .
	 *
	 * @return the offenderLastName
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}

	/**
	 * Sets the offenderLastName of the   .
	 *
	 * @param offenderLastName offenderLastName
	 */
	public void setOffenderLastName(String offenderLastName) {
		this.offenderLastName = offenderLastName;}

	/**
	 * Gets the offenderMiddleName of the  .
	 *
	 * @return the offenderMiddleName
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}

	/**
	 * Sets the offenderMiddleName of the   .
	 *
	 * @param offenderMiddleName offenderMiddleName
	 */
	public void setOffenderMiddleName(String offenderMiddleName) {
		this.offenderMiddleName = offenderMiddleName;}

	/**
	 * Gets the offenderSuffix of the  .
	 *
	 * @return the offenderSuffix
	 */
	public String getOffenderSuffix() {
		return this.offenderSuffix;
	}

	/**
	 * Sets the offenderSuffix of the   .
	 *
	 * @param offenderSuffix offenderSuffix
	 */
	public void setOffenderSuffix(String offenderSuffix) {
		this.offenderSuffix = offenderSuffix;}

	/**
	 * Gets the offenderNumber of the  .
	 *
	 * @return the offenderNumber
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}

	/**
	 * Sets the offenderNumber of the   .
	 *
	 * @param offenderNumber offenderNumber
	 */
	public void setOffenderNumber(Integer offenderNumber) {
		this.offenderNumber = offenderNumber;}

	/**
	 * Gets the featureClassificationName of the  .
	 *
	 * @return the featureClassificationName
	 */
	public String getFeatureClassificationName() {
		return this.featureClassificationName;
	}

	/**
	 * Sets the featureClassificationName of the   .
	 *
	 * @param featureClassificationName featureClassificationName
	 */
	public void setFeatureClassificationName(String featureClassificationName) {
		this.featureClassificationName = featureClassificationName;
	}

	/**
	 * Gets the physicalFeatureName of the  .
	 *
	 * @return the physicalFeatureName
	 */
	public String getPhysicalFeatureName() {
		return this.physicalFeatureName;
	}

	/**
	 * Sets the physicalFeatureName of the   .
	 *
	 * @param physicalFeatureName physicalFeatureName
	 */
	public void setPhysicalFeatureName(String physicalFeatureName) {
		this.physicalFeatureName = physicalFeatureName;
	}

	/**
	 * Gets the description of the  .
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description of the   .
	 *
	 * @param description description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the date of the  .
	 *
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date of the   .
	 *
	 * @param date date
	 */
	public void setDate(Date date) {
		this.date = date;
	}	
}