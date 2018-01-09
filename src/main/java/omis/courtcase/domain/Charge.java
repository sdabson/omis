package omis.courtcase.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offense.domain.Offense;

/**
 * Charge against a defendant in a court case.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.2 (Aug 11, 2017)
 * @since OMIS 3.0
 */
public interface Charge
		extends Creatable, Updatable {

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
	 * Sets the charge date.
	 * 
	 * @param date charge date
	 */
	void setDate(Date date);
	
	/**
	 * Returns the charge date.
	 * 
	 * @return charge date
	 */
	Date getDate();
	
	/**
	 * Sets the file date.
	 * 
	 * @param fileDate file date
	 */
	void setFileDate(Date fileDate);
	
	/**
	 * Returns the file date.
	 * 
	 * @return file date
	 */
	Date getFileDate();
	
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
	 * Sets the offense.
	 * 
	 * @param offense offense
	 */
	void setOffense(Offense offense);
	
	/**
	 * Returns the offense.
	 * 
	 * @return offense
	 */
	Offense getOffense();
	
	/**
	 * Sets the counts.
	 * 
	 * @param counts counts
	 */
	void setCounts(Integer counts);
	
	/**
	 * Returns the counts.
	 * 
	 * @return count
	 */
	Integer getCounts();
	
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