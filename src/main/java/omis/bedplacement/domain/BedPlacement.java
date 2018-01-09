package omis.bedplacement.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;
import omis.facility.domain.Bed;
import omis.offender.domain.OffenderAssociable;

/**
 * Bed Placement
 * @author Joel Norris
 * @version 0.1.1 (Feb 2, 2015)
 * @since OMIS 3.0
 */
public interface BedPlacement extends Creatable, Updatable, OffenderAssociable {

	/**
	 * Returns the id.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the bed for bed placement.
	 * 
	 * @return bed
	 */
	Bed getBed();

	/**
	 * Sets the bed for bed placement.
	 * 
	 * @param bed bed
	 */
	void setBed(Bed bed);

	/**
	 * Return the bed placement reason.
	 * 
	 * @return bed placement reason
	 */
	BedPlacementReason getBedPlacementReason();

	/**
	 * Sets the movement reason of the bed placement.
	 * 
	 * @param bedPlacementReason bed placement reason
	 */
	void setBedPlacementReason(final BedPlacementReason bedPlacementReason);
	
	/**
	 * Return the confirmed value of the bed placement.
	 * 
	 * @return confirmed
	 */
	Boolean getConfirmed();
	
	/**
	 * Set the confirmed value of the bed placement.
	 * 
	 * @param confirmed confirmed
	 */
	void setConfirmed(final Boolean confirmed);

	/**
	 * Return the description of the bed placement.
	 * 
	 * @return the description
	 */
	String getDescription();

	/**
	 * Set the description of the bed placement.
	 * 
	 * @param description description
	 */
	void setDescription(final String description);
	
	/**
	 * Returns the date range of the bed placement.
	 * 
	 * @return the dateRange
	 */
	DateRange getDateRange();

	/**
	 * Sets the date range of the bed placement.
	 * 
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);
	
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