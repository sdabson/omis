package omis.adaaccommodation.report;

import java.io.Serializable;
import java.util.Date;

import omis.adaaccommodation.domain.AccommodationCategory;
import omis.adaaccommodation.domain.DisabilityClassificationCategory;

/**
 * Accommodation summary.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 23, 2015)
 * @since OMIS 3.0
 */
public class AccommodationSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long accommodationId;
	
	private final String disabilityDescription;
	
	private final String accommodationDescription;
	
	private final String authorizationSource;
	
	private final Date authorizationStartDate;
	
	private final Date authorizationEndDate;
	
	private final String createdByFirstName;
	
	private final String createdByUsername;
	
	private final String createdByLastName;
	
	private final Date createdDate;
	
	private final DisabilityClassificationCategory disabilityCategory;
	
	private final AccommodationCategory accommodationCategory;
	
	private final String disabilityClassificationCategory;
	
	private final String accCategory;
	
	/**
	 * Constructor. 
	 * 
	 * @param accommodationId accommodation id
	 * @param disabilityDescription disability description
	 * @param accommodationDescription accommodation description
	 * @param authorizationSource authorization source
	 * @param authorizationStartDate authorization start date
	 * @param authorizationEndDate authorization end date
	 * @param createdByFirstName created by first name
	 * @param createdByUserName created by user name
	 * @param createdByLastName created by last name
	 * @param createdDate created date
	 * @param disabilityCategory disability category
	 * @param accommodationCategory accommodation category
	 */ 
	public AccommodationSummary(
			final Long accommodationId, final String disabilityDescription,
			final String accommodationDescription, 
			final String authorizationSource, final Date authorizationStartDate, 
			final Date authorizationEndDate, final String createdByFirstName, 
			final String createdByUsername, final String createdByLastName, 
			final Date createdDate,
			final DisabilityClassificationCategory disabilityCategory,
			final AccommodationCategory accommodationCategory,
			final String disabilityClassificationCategory,
			final String accCategory) {		
	
		this.accommodationId = accommodationId;
		this.disabilityDescription = disabilityDescription;
		this.accommodationDescription = accommodationDescription;
		this.authorizationSource = authorizationSource;
		this.authorizationStartDate = authorizationStartDate;
		this.authorizationEndDate = authorizationEndDate;
		this.createdByFirstName = createdByFirstName;
		this.createdByUsername = createdByUsername;
		this.createdByLastName = createdByLastName;
		this.createdDate = createdDate;
		this.disabilityCategory = disabilityCategory;
		this.accommodationCategory = accommodationCategory;
		this.disabilityClassificationCategory = null;
		this.accCategory = null;
	}
	
	public AccommodationSummary(final Long accommodationId, 
			final String disabilityDescription,
			final String accommodationDescription,
			final String authorizationSource, final Date authorizationStartDate,
			final Date authorizationEndDate, final String createdByFirstName, 
			final String createdByUsername, final String createdByLastName,
			final Date createdDate, 
			final String disabilityClassificationCategory,
			final String accCategory) {
		this.accommodationId = accommodationId;
		this.disabilityDescription = disabilityDescription;
		this.accommodationDescription = accommodationDescription;
		this.authorizationSource = authorizationSource;
		this.authorizationStartDate = authorizationStartDate;
		this.authorizationEndDate = authorizationEndDate;
		this.createdByFirstName = createdByFirstName;
		this.createdByUsername = createdByUsername;
		this.createdByLastName = createdByLastName;
		this.createdDate = createdDate;
		this.disabilityCategory = null;
		this.accommodationCategory = null;
		this.disabilityClassificationCategory = 
				disabilityClassificationCategory;
		this.accCategory = accCategory;
	}
	
	/**
	 * Gets the accommodation id.
	 * 
	 * @return the accommodation id
	 */
	public Long getAccommodationId() {
		return this.accommodationId;
	}
	
	/**
	 * Gets the disability description. 
	 * 
	 * @return the disability description
	 */
	public String getdisabilityDescription() {
		return this.disabilityDescription;
	}
	
	/**
	 * Gets the accommodation description.
	 * 
	 * @return the accommodation description
	 */
	public String getAccommodationDescription() {
		return this.accommodationDescription;
	}
	
	/**
	 * Gets the authorization source.
	 * 
	 * @return the authorization source
	 */
	public String getAuthorizationSource() {
		return this.authorizationSource;
	}
	
	/**
	 * Gets the authorization start date.
	 * 
	 * @return the authorization start date
	 */
	public Date getauthorizationStartDate() {
		return this.authorizationStartDate;
	}
	
	/**
	 * Gets the authorization end date.
	 * 
	 * @return the authorization end date
	 */
	public Date getAuthorizationEndDate() {
		return this.authorizationEndDate;
	}
	
	/**
	 * Gets the created by first name.
	 * 
	 * @return the created by first name
	 */
	public String getCreatedByFirstName() {
		return this.createdByFirstName;
	}
	
	/**
	 * Gets the created by user name.
	 * 
	 * @return the created by user name
	 */
	public String getCreatedByUsername() {
		return this.createdByUsername;
	}
	
	/**
	 * Gets the created by last name.
	 * 
	 * @return the created by last name
	 */
	public String getCreatedByLastName() {
		return this.createdByLastName;
	}
	
	/**
	 * Gets the created date.
	 * 
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return this.createdDate;
	}
	
	/**
	 * Gets the disability classification category.
	 * 
	 * @return disability category
	 */
	public DisabilityClassificationCategory getDisabilityCategory() {
		return this.disabilityCategory;
	}
	
	public AccommodationCategory getAccommodationCategory() {
		return this.accommodationCategory;
	}
	
	public String getDisabilityClassificationCategory() {
		return this.disabilityClassificationCategory;
	}
	
	public String getAccCategory() {
		return this.accCategory;
	}
}
