package omis.health.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.facility.domain.Facility;
import omis.offender.domain.OffenderAssociable;

/**
 * Health Request.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public interface HealthRequest
		extends Updatable, Creatable, OffenderAssociable {
	
	/**
	 * Returns the ID of the health request.
	 * 
	 * @return health request ID
	 */
	Long getId();

	/**
	 * Sets the ID of the health request.
	 * 
	 * @param id health request ID
	 */
	void setId(Long id);
	
	/**
	 * Sets the status.
	 * 
	 * @param status status
	 */
	void setStatus(HealthRequestStatus status);
	
	/**
	 * Returns the status.
	 * 
	 * @return status
	 */
	HealthRequestStatus getStatus();
	
	/**
	 * Sets the facility.
	 * 
	 * @param facility facility
	 */
	void setFacility(Facility facility);
	
	/**
	 * Returns the facility.
	 * 
	 * @return facility
	 */
	Facility getFacility();
	
	/**
	 * Returns whether labs are required.
	 * 
	 * @return whether labs are required
	 */
	Boolean getLabsRequired();
	
	/**
	 * Sets the whether labs are required.
	 * 
	 * @param labsRequired whether labs are required
	 */
	void setLabsRequired(Boolean labsRequired);
	
	/**
	 * Returns the date and time of the health request.
	 * 
	 * @return date
	 */
	Date getDate();
	
	/**
	 * Sets the date and time of the health request.
	 * 
	 * @param date date
	 */
	void setDate(Date date);
	
	/**
	 * Returns the health request category of the health request.
	 * 
	 * @return health request category
	 */
	HealthRequestCategory getCategory();
	
	/**
	 * Sets the health request category of the health request.
	 * 
	 * @param category health request category
	 */
	void setCategory(HealthRequestCategory category);
	
	/**
	 * Returns whether the appointment is to be scheduled ASAP.
	 * 
	 * @return whether appointment is to be scheduled ASAP
	 */
	Boolean getAsap();
	
	/**
	 * Sets whether appointment is to be scheduled ASAP.
	 * 
	 * @param asap whether appointment is to be scheduled ASAP
	 */
	void setAsap(Boolean asap);
	
	/**
	 * Returns the provider level.
	 * 
	 * @return provider level
	 */
	ProviderLevel getProviderLevel();
	
	/**
	 * Sets the provider level.
	 * 
	 * @param providerLevel provider level
	 */
	void setProviderLevel(ProviderLevel providerLevel);
	
	/**
	 * Returns the notes.
	 * 
	 * @return notes
	 */
	String getNotes();
	
	/**
	 * Sets the notes.
	 * 
	 * @param notes notes
	 */
	void setNotes(String notes);
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}