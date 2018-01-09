package omis.warrant.web.form;

import java.util.Date;

import omis.facility.domain.Facility;
import omis.person.domain.Person;
import omis.region.domain.County;

/**
 * WarrantReleaseForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantReleaseForm {
	
	public String addressee;
	
	public Facility facility;
	
	public County county;
	
	public Date releaseDate;
	
	public String instructions;
	
	private Person clearedBy;
	
	private Date clearedByDate;
	
	/**
	 * 
	 */
	public WarrantReleaseForm() {
	}

	/**
	 * Returns the addressee
	 * @return addressee - String
	 */
	public String getAddressee() {
		return addressee;
	}

	/**
	 * Sets the addressee
	 * @param addressee - String
	 */
	public void setAddressee(final String addressee) {
		this.addressee = addressee;
	}

	/**
	 * Returns the facility
	 * @return facility - Facility
	 */
	public Facility getFacility() {
		return facility;
	}

	/**
	 * Sets the facility
	 * @param facility - Facility
	 */
	public void setFacility(final Facility facility) {
		this.facility = facility;
	}

	/**
	 * Returns the county
	 * @return county - County
	 */
	public County getCounty() {
		return county;
	}

	/**
	 * Sets the county
	 * @param county - County
	 */
	public void setCounty(final County county) {
		this.county = county;
	}

	/**
	 * Returns the releaseDate
	 * @return releaseDate - Date
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 * Sets the releaseDate
	 * @param releaseDate - Date
	 */
	public void setReleaseDate(final Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * Returns the instructions
	 * @return instructions - String
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * Sets the instructions
	 * @param instructions - String
	 */
	public void setInstructions(final String instructions) {
		this.instructions = instructions;
	}

	/**
	 * Returns the clearedBy
	 * @return clearedBy - Person
	 */
	public Person getClearedBy() {
		return clearedBy;
	}

	/**
	 * Sets the clearedBy
	 * @param clearedBy - Person
	 */
	public void setClearedBy(final Person clearedBy) {
		this.clearedBy = clearedBy;
	}

	/**
	 * Returns the clearedByDate
	 * @return clearedByDate - Date
	 */
	public Date getClearedByDate() {
		return clearedByDate;
	}

	/**
	 * Sets the clearedByDate
	 * @param clearedByDate - Date
	 */
	public void setClearedByDate(final Date clearedByDate) {
		this.clearedByDate = clearedByDate;
	}
	
	
}
