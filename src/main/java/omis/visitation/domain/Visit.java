package omis.visitation.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;
import omis.location.domain.Location;
import omis.visitation.domain.component.VisitFlags;

/**
 * Visit.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 17, 2013)
 * @since OMIS 3.0
 */
public interface Visit extends Creatable, Updatable {

	/**
	 * Return the id of the visit.
	 * @return the id
	 */
	Long getId();

	/**
	 * Set the id of the visit.
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * Return the visitor of the visit.
	 * @return the visitor
	 */
	VisitationAssociation getVisitationAssociation();

	/**
	 * Set the visitor of the visit.
	 * @param visitationAssociation the visitor to set
	 */
	void setVisitationAssociation(VisitationAssociation visitationAssociation);
	
	/**
	 * Returns the date range.
	 * 
	 * @return date range
	 */
	DateRange getDateRange();
	
	/**
	 * Sets the date range.
	 * 
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Return the badge number of the visit.
	 * @return the badgeNumber
	 */
	String getBadgeNumber();

	/**
	 * Set the badge number of the visit.
	 * @param badgeNumber the badgeNumber to set
	 */
	void setBadgeNumber(final String badgeNumber);
	
	/**
	 * Returns visit flags.
	 * 
	 * @return flags
	 */
	VisitFlags getFlags();
	
	/**
	 * Sets visit flags.
	 * 
	 * @param flags flags
	 */
	void setFlags(VisitFlags flags);
	
	/**
	 * Returns notes.
	 * 
	 * @return notes
	 */
	String getNotes();
	
	/**
	 * Sets notes.
	 * 
	 * @param notes notes
	 */
	void setNotes(String notes);
	
	/**
	 * Returns the location.
	 * 
	 * @return location
	 */
	Location getLocation();
	
	/**
	 * Sets the location.
	 * 
	 * @param location location
	 */
	void setLocation(Location location);
	
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
