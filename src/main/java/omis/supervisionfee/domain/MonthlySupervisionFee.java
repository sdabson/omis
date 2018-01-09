package omis.supervisionfee.domain;

import java.math.BigDecimal;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

/**
 * Monthly Supervision Fee.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 15, 2014)
 * @since  OMIS 3.0
 */
public interface MonthlySupervisionFee  extends Creatable, Updatable {
	
	/**
	 * Returns the id of the monthly supervision fee.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id of the monthly supervision fee.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the fee of the monthly supervision fee.
	 * 
	 * @return fee
	 */
	BigDecimal getFee();
	
	/**
	 * Sets the fee of the monthly supervision fee.
	 * 
	 * @param fee fee
	 */
	void setFee(BigDecimal fee);
	
	/**
	 * Returns the offender of the monthly supervision fee.
	 * 
	 * @return offender
	 */
	Offender getOffender();
	
	/**
	 * Sets the offender of the monthly supervision fee.
	 * 
	 * @param offender offender
	 */
	void setOffender(Offender offender);
	
	/**
	 * Returns the date range of the monthly supervision fee.
	 * 
	 * @return date range
	 */ 
	DateRange getDateRange();
	
	/**
	 * Sets the date range of the monthly supervision fee.
	 * 
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);	
	
	/**
	 * Returns the authority category of the monthly supervision fee.
	 * 
	 * @return authority category
	 */
	MonthlySupervisionFeeAuthorityCategory getAuthorityCategory();
	
	/**
	 * Sets the authority category of the monthly supervision fee.
	 * 
	 * @param authorityCategory authority category
	 */
	void setAuthorityCategory(MonthlySupervisionFeeAuthorityCategory 
						authorityCategory);
	
	/**
	 * Returns the comment of the monthly supervision fee.
	 * 
	 * @return comment
	 */
	String getComment();
	
	/**
	 * Sets the comment on the monthly supervision fee.
	 * 
	 * @param comment comment
	 */
	void setComment(String comment);
	
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