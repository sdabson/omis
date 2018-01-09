package omis.facility.report;


/** Staff and assigned facility report.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 8, 2014)
 * @since OMIS 3.0
 * @deprecated use module specific report service for facility/staff lookup;
 * consider removal - SA
 */
@Deprecated
public class FacilityStaffSummary {
	private final Long personId;
	private final Long facilityId;
	private final String facilityName;
	private final String organizationName;


	/** Constructor. */
	FacilityStaffSummary(final Long personId, final Long facilityId,
			final String facilityName, final String organizationName) {
		this.personId = personId;
		this.facilityId = facilityId;
		this.facilityName = facilityName;
		this.organizationName = organizationName;
	}

	/** gets person id.
	 * @return person id. */
	public Long getPersonId() {
		return this.personId;
	}

	/** gets facility id.
	 * @return facility id. */
	public Long getFacilityId() {
		return this.facilityId;
	}

	/** gets facility name.
	 * @return facility name. */
	public String getFacilityName() {
		return this.facilityName;
	}

	/** gets organizationMame.
	 * @return organization name. */
	public String getOrganizationName() {
		return this.organizationName;
	}
}
