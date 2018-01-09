package omis.immigration.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;
import omis.offender.domain.OffenderAssociable;

/**
 * Alien residence.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 28, 2014)
 * @since OMIS 3.0
 */
public interface AlienResidence
		extends Creatable, Updatable, OffenderAssociable {

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
	 * Sets the immigration status.
	 * 
	 * @param immigrationStatus immigration status
	 */
	void setImmigrationStatus(ImmigrationStatus immigrationStatus);
	
	/**
	 * Returns the immigration status.
	 * 
	 * @return immigration status
	 */
	ImmigrationStatus getImmigrationStatus();
	
	/**
	 * Sets whether the alien is legal.
	 * 
	 * @param legal whether alien is legal
	 */
	void setLegal(Boolean legal);
	
	/**
	 * Returns whether the alien is legal.
	 * 
	 * @return whether alien is legal
	 */
	Boolean getLegal();
	
	/**
	 * Sets INS number.
	 * 
	 * @param insNumber INS number
	 */
	void setInsNumber(String insNumber);
	
	/**
	 * Returns INS number.
	 * 
	 * @return INS number
	 */
	String getInsNumber();
	
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