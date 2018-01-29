package omis.residence.web.form;

import java.util.Date;

import omis.address.domain.Address;
import omis.location.domain.Location;
import omis.residence.domain.NonResidenceTerm;
import omis.residence.domain.ResidenceStatus;

/**
 * NonResidence term item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (January 8, 2018)
 * @since OMIS 3.0
 */
public class NonResidenceTermItem {

	private NonResidenceTerm term;
	private Date startDate;
	private Date endDate;
	private Address address;
	private Location location;
	private ResidenceStatus residenceStatus;
	
	public NonResidenceTermItem() {
		//Default constructor.
	}
	
	/**
	 * Instantiates a non residence term item with the specified values.
	 * 
	 * @param term non residence term
	 * @param startDate start date
	 * @param endDate end date
	 * @param address address
	 * @param residenceCategory residence category
	 * @param location location
	 * @param residenceStatus
	 */
	public NonResidenceTermItem(final NonResidenceTerm term, final Date startDate, final Date endDate,
			final Address address, final Location location, final ResidenceStatus residenceStatus) {
		this.term = term;
		this.startDate = startDate;
		this.endDate = endDate;
		this.address = address;
		this.location = location;
		this.residenceStatus = residenceStatus;
	}

	/**
	 * Returns the non residence term.
	 * 
	 * @return non residence term
	 */
	public NonResidenceTerm getTerm() {
		return this.term;
	}

	/**
	 * Sets the non residence term.
	 * 
	 * @param term non residence term
	 */
	public void setTerm(final NonResidenceTerm term) {
		this.term = term;
	}

	/**
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start date
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the address.
	 * 
	 * @return address
	 */
	public Address getAddress() {
		return this.address;
	}

	/**
	 * Sets the address.
	 * 
	 * @param address address
	 */
	public void setAddress(final Address address) {
		this.address = address;
	}

	/**
	 * Returns the location.
	 * 
	 * @return location
	 */
	public Location getLocation() {
		return this.location;
	}

	/**
	 * Sets the location.
	 * 
	 * @param location location
	 */
	public void setLocation(final Location location) {
		this.location = location;
	}

	/**
	 * Returns the residence status.
	 * 
	 * @return residence status
	 */
	public ResidenceStatus getResidenceStatus() {
		return this.residenceStatus;
	}

	/**
	 * Sets the residence status.
	 * 
	 * @param residenceStatus residence status
	 */
	public void setResidenceStatus(final ResidenceStatus residenceStatus) {
		this.residenceStatus = residenceStatus;
	}
}