package omis.visitation.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.facility.domain.Facility;

/**
 * Facility log form.
 * 
 * @author Joel Norris
 * @version 0.1.0
 * @since OMIS 3.0
 */
public class FacilityVisitationLogForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date date;
	
	private Facility facility;
	
	/**
	 * Instantiates a default instance of facility log form.
	 */
	public FacilityVisitationLogForm() {
		//Default constructor.
	}
	
	/**
	 * Returns date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets date.
	 * 
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns facility.
	 * 
	 * @return facility
	 */
	public Facility getFacility() {
		return this.facility;
	}

	/**
	 * Sets facility.
	 * 
	 * @param facility facility
	 */
	public void setFacility(final Facility facility) {
		this.facility = facility;
	}
}