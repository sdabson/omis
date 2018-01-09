package omis.facility.report;

import java.io.Serializable;

import omis.address.domain.Address;
import omis.facility.domain.Facility;
import omis.location.domain.Location;
import omis.organization.domain.Organization;

/**
 * Summary of facility.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 9, 2016)
 * @since OMIS 3.0
 */
public class FacilitySummary
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String name;
	
	private final String organizationName;
	
	/**
	 * Instantiates summary of facility.
	 * 
	 * @param facility facility
	 * @param location location
	 * @param organization organization
	 * @param address address
	 */
	public FacilitySummary(
			final Facility facility,
			final Location location,
			final Organization organization,
			final Address address) {
		
		/*
		 * Note - address isn't used yet.
		 * 
		 * To use address, implement AddressSummarizable with the
		 * AddressSummaryDelegate - SA.
		 */
		this.id = facility.getId();
		this.name = facility.getName();
		this.organizationName = organization.getName();
	}
	
	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Returns name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns name of organization.
	 * 
	 * @return name of organization
	 */
	public String getOrganizationName() {
		return this.organizationName;
	}
}