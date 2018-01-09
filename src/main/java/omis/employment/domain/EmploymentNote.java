package omis.employment.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * EmploymentNote
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (Feb 2, 2015)
 * @since: OMIS 3.0
 */
public interface EmploymentNote extends Updatable, Creatable {

	/**
	 * Returns the employment note id.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the employment note id
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the employment note value.
	 * 
	 * @return employment note value
	 */
	String getValue();
	
	/**
	 * Sets the employment note value.
	 * 
	 * @param value note value
	 */
	void setValue(String value);

	/**
	 * Sets the employment note date.
	 * 
	 * @param date employment note date
	 */
	void setDate(Date date);
	
	/**
	 * Gets the employment note date.
	 * 
	 * @return employment note date
	 */
	Date getDate();
	
	/**
	 * Sets the employment term which is employment history.
	 * 
	 * @param employmentTerm employment term
	 */
	void setEmploymentTerm(EmploymentTerm employmentTerm);
	
	/**
	 * Gets the employment term which is employment history.
	 * 
	 * @return employment term
	 */
	EmploymentTerm getEmploymentTerm();
	
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