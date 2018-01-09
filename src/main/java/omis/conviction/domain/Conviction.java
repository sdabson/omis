package omis.conviction.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.conviction.domain.component.ConvictionFlags;
import omis.courtcase.domain.CourtCase;
import omis.offense.domain.Offense;

/**
 * Conviction.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.2 (Jan 30, 2016)
 * @since OMIS 3.0
 */
public interface Conviction
	extends Updatable, Creatable {

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
	 * Sets the offense of which the associated offender is convicted.
	 * 
	 * @param offense offense of which offender is convicted
	 */
	void setOffense(Offense offense);
	
	/**
	 * Returns the offense of which the associated offender is convicted.
	 * 
	 * @return offense of which offender is convicted
	 */
	Offense getOffense();
	
	/**
	 * Sets the conviction date.
	 * 
	 * @param date conviction date
	 */
	void setDate(Date date);
	
	/**
	 * Returns the conviction date.
	 * 
	 * @return conviction date
	 */
	Date getDate();
	
	/**
	 * Sets the counts.
	 * 
	 * @param counts counts
	 */
	void setCounts(Integer counts);
	
	/**
	 * Returns the counts.
	 * 
	 * @return counts
	 */
	Integer getCounts();
	

	/**
	 * Sets the court case.
	 * 
	 * @param courtCase court case
	 */
	void setCourtCase(CourtCase courtCase);
	
	/**
	 * Returns the court case.
	 * 
	 * @return court case
	 */
	CourtCase getCourtCase();
	
	/**
	 * Sets the offense severity.
	 * 
	 * @param severity offense severity
	 */
	void setSeverity(OffenseSeverity severity);
	
	/**
	 * Returns the offense severity.
	 * 
	 * @return offense severity
	 */
	OffenseSeverity getSeverity();
	
	/**
	 * Sets the conviction flags.
	 * 
	 * @param flags flags
	 */
	void setFlags(ConvictionFlags flags);
	
	/**
	 * Returns the conviction flags.
	 * 
	 * @return flags
	 */
	ConvictionFlags getFlags();
	
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