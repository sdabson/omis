package omis.location.search.report;

/** Search result for locations.
 * @author Ryan Johns
 * @version 0.1.0 (Jul 15, 2015)
 * @since OMIS 3.0 */
public class LocationSearchResult {
	private final Long locationId;
	private final Long organizationId;
	private final Long addressId;
	private final String organizationName;
	private final String value;
	private final String cityName;
	private final String stateName;
	private final String zipCode;
	
	/** Constructor.
	 * @param locationId - location id. 
	 * @param organizationId - organization id.
	 * @param addressId - address id.
	 * @param organizationName - organization name.
	 * @param streetNumber - street number.
	 * @param streetName - street name.
	 * @param streetSuffix - street suffix.
	 * @param cityName - city name.
	 * @param stateName - state name. 
	 * @param zipCode - zip code. */
	public LocationSearchResult(final Long locationId, 
			final Long organizationId, final Long addressId,
			final String organizationName,	final String value, 
			final String cityName, final String stateName, 
			final String zipCode) {
		this.locationId = locationId;
		this.organizationId = organizationId;
		this.addressId = addressId;
		this.organizationName = organizationName;
		this.value = value;
		this.cityName = cityName;
		this.stateName = stateName;
		this.zipCode = zipCode;
	}
	
	/** Gets location id. 
	 * @return location id. */
	public Long getLocationId() { 
		return this.locationId; 
	}
	
	/** Gets organization id.
	 * @return organization id. */
	public Long getOrganizationId() { 
		return this.organizationId; 
	}
	
	/** Gets address id. 
	 * @return address id. */
	public Long getAddressId() { 
		return this.addressId; 
	}
	
	/** Gets organization name.
	 * @return organization name. */
	public String getOrganizationName() { 
		return this.organizationName; 
	}
	
	
	/**
	 * Returns address value.
	 * 
	 * @return address value
	 */
	public String getValue() {
		return this.value;
	}

	/** Gets city name.
	 * @return city name. */
	public String getCityName() { 
		return this.cityName; 
	}
	
	/** gets state name.
	 * @return state name. */
	public String getStateName() { 
		return this.stateName; 
	}
	
	/** Gets zip code.
	 * @return zip code. */
	public String getZipCode() { 
		return this.zipCode; 
	}
}