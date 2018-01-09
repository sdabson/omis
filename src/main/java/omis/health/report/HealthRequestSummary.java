package omis.health.report;

import java.io.Serializable;
import java.util.Date;

import omis.health.domain.HealthRequestCategory;
import omis.health.domain.HealthRequestStatus;

/**
 * Summary of health request.
 *
 * @author Stephen Abson
 * @version 0.1.0 (Apr 11, 2014)
 * @since OMIS 3.0
 */
public class HealthRequestSummary
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Long id;
	
	private final Long authorizationRequestId;
	
	private final Long authorizationId;

	private final Boolean authorized;
	
	private final String offenderLastName;

	private final String offenderFirstName;

	private final Integer offenderNumber;

	private final Date date;

	private final HealthRequestCategory category;

	private final String facilityName;

	private final Boolean asap;

	private final Boolean labsRequired;
	
	private final HealthRequestStatus status;
	
	private final String unitName;
	
	private final String notes;

	/**
	 * Instantiates an implementation of health request summary.
	 *
	 * @param id ID
	 * @param authorizationRequestId ID of authorization request
	 * @param authorizationId ID of authorization
	 * @param authorized whether the request is authorized
	 * @param offenderLastName last name of offender
	 * @param offenderFirstName first name of offender
	 * @param offenderNumber offender number
	 * @param date date
	 * @param category category
	 * @param facilityName name of facility
	 * @param asap asap
	 * @param labsRequired labs required
	 * @param status status
	 * @param unitName unit name
	 * @param notes notes.
	 */
	public HealthRequestSummary(final Long id,
			final Long authorizationRequestId,
			final Long authorizationId,
			final Boolean authorized,
			final String offenderLastName, final String offenderFirstName,
			final Integer offenderNumber, final Date date,
			final HealthRequestCategory category,
			final String facilityName, final Boolean asap, 
			final Boolean labsRequired,
			final HealthRequestStatus status,
			final String unitName,
			final String notes) {
		this.id = id;
		this.authorizationRequestId = authorizationRequestId;
		this.authorizationId = authorizationId;
		this.authorized = authorized;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderNumber = offenderNumber;
		this.date = date;
		this.category = category;
		this.facilityName = facilityName;
		this.asap = asap;
		this.labsRequired = labsRequired;
		this.status = status;
		this.unitName = unitName;
		this.notes = notes;
	}

	/**
	 * Returns the request ID.
	 *
	 * @return request ID
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Returns the ID of the authorization request.
	 * 
	 * @return ID of authorization request
	 */
	public Long getAuthorizationRequestId() {
		return this.authorizationRequestId;
	}
	
	/**
	 * Returns the ID of the authorization.
	 * 
	 * @return ID of authorization
	 */
	public Long getAuthorizationId() {
		return this.authorizationId;
	}

	/**
	 * Returns whether the request is authorized.
	 * 
	 * @return whether request is authorized
	 */
	public Boolean getAuthorized() {
		return this.authorized;
	}

	/**
	 * Returns the offender last name.
	 *
	 * @return offender last name
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}

	/**
	 * Returns the offender first name.
	 *
	 * @return offender first name.
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}

	/**
	 * Returns the offender number.
	 *
	 * @return offender number
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}

	/**
	 * Returns the date of the request.
	 *
	 * @return date of request
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Returns the category of the request.
	 *
	 * @return category of request
	 */
	public HealthRequestCategory getCategory() {
		return this.category;
	}

	/**
	 * Returns the name of the facility.
	 *
	 * @return name of facility
	 */
	public String getFacilityName() {
		return this.facilityName;
	}

	/**
	 * Returns asap status of request.
	 * 
	 * @return asap asap
	  */
	public Boolean getAsap() {
		return this.asap;
	}

	/**
	 * Returns whether labs are required.
	 * 
	 * @return whether labs are required
	 */
	public Boolean getLabsRequired() {
		return this.labsRequired;
	}
	
	/**
	 * Returns the status.
	 * 
	 * @return status
	 */
	public HealthRequestStatus getStatus() {
		return this.status;
	}
	
	/**
	 * Returns the unit name.
	 * 
	 * @return unit name
	 */
	public String getUnitName() {
		return this.unitName;
	}
	
	/**
	 * Returns notes of request.
	 * 
	 * @return notes notes.
	 */
	public String getNotes() {
		return this.notes;
	}
}