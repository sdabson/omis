package omis.court.domain;

import java.io.Serializable;

import omis.datatype.DateRange;
import omis.person.domain.Person;

/**
 * Assignment of judge to court.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 27, 2013)
 * @since OMIS 3.0
 */
public interface CourtJudgeAssignment
		extends Serializable {

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
	 * Sets the judge.
	 * 
	 * @param judge judge
	 */
	void setJudge(Person judge);
	
	/**
	 * Returns the judge.
	 * 
	 * @return judge
	 */
	Person getJudge();
	
	/**
	 * Sets the court.
	 * 
	 * @param court court
	 */
	void setCourt(Court court);
	
	/**
	 * Returns the court.
	 * 
	 * @return court
	 */
	Court getCourt();
	
	/**
	 * Sets the range of dates during which the judge is assigned to the court.
	 * 
	 * @param dateRange range of dates during which judge is assigned to court
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns the range of dates during which the judge is assigned to the
	 * court.
	 * 
	 * @return range of dates during which judge is assigned to court
	 */
	DateRange getDateRange();
	
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