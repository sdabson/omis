package omis.commitstatus.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

/**
 * Commit status term 
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (May 31, 2017)
 * @since: OMIS 3.0
 */
public interface CommitStatusTerm extends Creatable, Updatable{
	/**
	 * Returns the commit status id.
	 * 
	 * @returns id
	 */
	Long getId();
	
	
	/**
	 * Sets id
	 * 
	 * @param name
	 */
	void setId(Long id);
	
	/**
	 * Returns the offender.
	 * 
	 * @returns offender
	 */
	Offender getOffender();
	
	/**
	 * Sets the offender
	 * 
	 * @param offender
	 */
	void setOffender(Offender offender);
	
	/**
	 * Returns the status.
	 * 
	 * @returns status
	 */
	CommitStatus getStatus();
	
	/**
	 * Sets the status
	 * 
	 * @param status
	 */
	void setStatus(CommitStatus status);
	
	/**
	 * Returns the date range.
	 * 
	 * @returns date range
	 */
	DateRange getDateRange();
	
	/**
	 * Sets the date range
	 * 
	 * @param date range
	 */
	void setDateRange(DateRange dateRange);
	
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