package omis.supervisionfee.domain;

import java.io.Serializable;

import omis.datatype.DateRange;
import omis.offender.domain.Offender;

/**
 * Supervision fee transaction.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 27, 2014)
 * @since OMIS 3.0
 */
public interface SupervisionFeeTransaction extends Serializable{
	
	/**
	 * Returns the id of the supervision fee transaction.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id of the supervision fee transaction.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the date of the supervision fee transaction.
	 * 
	 * @return date
	 */
	DateRange getDate();
	
	/**
	 * Sets the date of the supervision fee transaction.
	 * 
	 * @param date date
	 */
	void setDate(DateRange date);
	
	/**
	 * Returns the amount of the supervision fee transaction.
	 * 
	 * @return amount
	 */
	Integer getAmount();
	
	/**
	 * Sets the amount of the supervision fee transaction.
	 * 
	 * @param amount amount
	 */
	void setAmount(Integer amount);
	
	/**
	 * Returns the offender of the supervision fee transaction.
	 * 
	 * @return offender
	 */
	Offender getOffender();
	
	/**
	 * Sets the offender of the supervision fee transaction.
	 * 
	 * @param offender offender
	 */
	void setOffender(Offender offender);
	
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