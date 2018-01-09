package omis.supervisionfee.domain;

import java.math.BigDecimal;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.court.domain.Court;
import omis.datatype.DateRange;
import omis.person.domain.Person;

/**
 * Supervision Fee Requirement.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 15, 2014)
 * @since  OMIS 3.0
 */
public interface SupervisionFeeRequirement extends Creatable, Updatable {

	/**
	 * Returns the id of the supervision fee requirement.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id of the supervision fee requirement.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the fee of the supervision fee requirement.
	 * 
	 * @return fee
	 */
	BigDecimal getFee();
	
	/**
	 * Sets the fee of the supervision fee requirement.
	 * 
	 * @param fee fee
	 */
	void setFee(BigDecimal fee);
	
	/**
	 * Returns the monthly supervision fee requirement of the supervision fee.
	 * 
	 * @return monthly supervision fee
	 */
	MonthlySupervisionFee getMonthlySupervisionFee();
	
	/**
	 * Sets the monthly supervision fee requirement of the supervision fee.
	 * 
	 * @param monthlySupervisionFee monthly supervision fee
	 */
	void setMonthlySupervisionFee(MonthlySupervisionFee monthlySupervisionFee);
	
	/**
	 * Returns the date range of the supervision fee requirement.
	 * 
	 * @return date range
	 */
	DateRange getDateRange();
	
	/**
	 * Sets the date range of the supervision fee requirement.
	 * 
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns the comments of the supervision fee requirement.
	 * 
	 * @return comment
	 */
	String getComment();
	
	/**
	 * Sets the comments of the supervision fee requirement.
	 * 
	 * @param comment comment
	 */
	void setComment(String comment);
	
	/**
	 * Returns the reason for supervision fee requirement.
	 * 
	 * @return reason
	 */	
	SupervisionFeeRequirementReason getReason();
	
	/**
	 * Sets the reason for the supervision fee requirement.
	 * 
	 * @param reason reason
	 */ 
	void setReason(SupervisionFeeRequirementReason reason);
	
	/**
	 * Returns the officer for the supervision fee requirement.
	 * 
	 * @return officer
	 */
	Person getOfficer();
	
	/**
	 * Sets the officer for the supervision fee requirement.
	 * 
	 * @param officer officer
	 */
	void setOfficer(Person officer);
	
	/**
	 * Returns the court for the supervision fee requirement.
	 * 
	 * @return court
	 */
	Court getCourt();
	
	/**
	 * Sets the court for the supervision fee requirement.
	 * 
	 * @param court court
	 */
	void setCourt(Court court);
	
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