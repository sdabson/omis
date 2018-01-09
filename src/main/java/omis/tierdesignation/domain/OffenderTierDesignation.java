package omis.tierdesignation.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;
import omis.offender.domain.OffenderAssociable;
/**
 * Contains detailed information about an offenders status with a 
 * states Sexual and Violent Offender Registry.  
 * 
 * <p>Even though an offender may have committed a  
 * violent and/or sexual offense(s), local and state laws 
 * are the determining factors that require an offender to register.
 * 
 * <p>Only one Offender Tier Designation may be active at a time.  Active 
 * Tier Designations are identified by a null or blank end date.  
 * 
 * <p>Tier Designations  may be terminated by entities with the proper 
 * authority to do so.   Terminated Tier Designations are identified 
 * by.  
 * 
 * <p>Tier Designations need to have a designation source (authority)
 * associated with the Tier Designation.  This provides additional information
 * as to who or what entity is requiring the offender to register.
 *  
 * @author Jason Nelson
 * @author Stephen Abson
 * @version 0.1.0 (Dec 12, 2013)
 * @since OMIS 3.0
 */
public interface OffenderTierDesignation
		extends OffenderAssociable, Creatable, Updatable {
	
	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	Long getId();

	/**
	 * Sets the level.
	 * 
	 * @param level level
	 */
	void setLevel(TierLevel level);
	
	/**
	 * Returns the level.
	 * 
	 * @return level
	 */
	TierLevel getLevel();

	/**
	 * Sets the source.
	 * 
	 * @param source source
	 */
	void setSource(TierSource source);
	
	/**
	 * Returns the source.
	 * 
	 * @return source
	 */
	TierSource getSource();

	/**
	 * Sets the change reason.
	 * 
	 * @param changeReason change reason
	 */
	void setChangeReason(TierChangeReason changeReason);
	
	/**
	 * Returns the change reason.
	 * 
	 * @return change reason
	 */
	TierChangeReason getChangeReason();

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
	 * Sets the comment.
	 * 
	 * @param comment comment
	 */
	void setComment(String comment);
	
	/**
	 * Returns the comment.
	 * 
	 * @return comment
	 */
	String getComment();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
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
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}