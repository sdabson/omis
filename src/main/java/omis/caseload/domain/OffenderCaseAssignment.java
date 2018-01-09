package omis.caseload.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

/**
 * Offender case assignment.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 26, 2016)
 * @since OMIS 3.0
 */
public interface OffenderCaseAssignment extends Creatable, Updatable {
	
	/**
	 * Sets the ID of the offender case assignment.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID of the offender case assignment.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the caseload for the offender case assignment.
	 *
	 * @param caseload caseload
	 */
	void setCaseload(Caseload caseload);
	
	/**
	 * Returns the caseload for the offender case assignment.
	 *
	 * @return caseload
	 */
	Caseload getCaseload();
	
	/**
	 * Sets the offender of the offender case assignment.
	 * 
	 * @param offender offender 
	 */
	void setOffender(Offender offender);
	
	/**
	 * Returns the offender of the offender case assignment.
	 * 
	 * @return offender
	 */
	Offender getOffender();
	
	/**
	 * Sets the date range of the offender case assignment.
	 * 
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns the date range of the offender case assignment.
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