package omis.adaaccommodation.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Accommodation issuance report service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sep 17, 2015)
 * @since OMIS 3.0
 */
public class AccommodationIssuanceSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long accommodationIssuanceId;
	
	private final String description;
	
	private final Date timestamp;
	
	private final String issuerFirstName;
	
	private final String issuerLastName;
	
	public AccommodationIssuanceSummary(
			final Long accommodationIssuanceId, final String description, 
			final Date timestamp, final String issuerFirstName,
			final String issuerLastName) {
		
		this.accommodationIssuanceId = accommodationIssuanceId;
		this.description = description;
		this.timestamp = timestamp;
		this.issuerFirstName = issuerFirstName;
		this.issuerLastName = issuerLastName;
	}
			
	/**
	 * Gets the accommodation issuance id.
	 * 
	 * @return the accommodationIssuanceId
	 */
	public Long getAccommodationIssuanceId() {
		return this.accommodationIssuanceId;
	}

	/**
	 * Gets the accommodation issuance description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Gets the accommodation issuance time stamp.
	 * 
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return this.timestamp;
	}
	
	/**
	 * Gets the accommodation issuance issuer first name.
	 * 
	 * @return the issuer first name
	 */
	public String getIssuerFirstName() {
		return this.issuerFirstName;
	}

	/**
	 * Gets the accommodation issuance issuer last name.
	 * 
	 * @return the issuer last name
	 */
	public String getIssuerLastName() {
		return this.issuerLastName;
	}
}