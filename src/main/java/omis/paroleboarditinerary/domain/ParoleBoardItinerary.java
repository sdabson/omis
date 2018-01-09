package omis.paroleboarditinerary.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;
import omis.location.domain.Location;

/**
 * Parole board itinerary.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 15, 2017)
 * @since OMIS 3.0
 */
public interface ParoleBoardItinerary extends Creatable, Updatable {

	/**
	 * Sets the ID of the parole board itinerary.
	 *
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Return the ID of the parole board itinerary.
	 *
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the location.
	 * 
	 * @param location location
	 */
	void setLocation(Location location);
	
	/**
	 * Returns the location.
	 * 
	 * @return location
	 */
	Location getLocation();
	
	/**
	 * Sets the date range.
	 * 
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns the date range.
	 * 
	 * @return date range
	 */
	DateRange getDateRange();
	
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
