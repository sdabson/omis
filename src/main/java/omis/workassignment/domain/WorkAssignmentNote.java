package omis.workassignment.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Work assignment note.
 * @author Yidong Li
 * @version 0.1.0 (Aug 17, 2016)
 * @since OMIS 3.0
 */
public interface WorkAssignmentNote
		extends Creatable, Updatable {

	/**
	 * Sets the ID.
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the date.
	 * @param date date
	 */
	void setDate(Date date);
	
	/**
	 * Returns the date. 
	 * @return date date
	 */
	Date getDate();
	
	/**
	 * Sets the value.
	 * @param notes notes
	 */
	void setValue(String value);
	
	/**
	 * Returns the value.
	 * @return value
	 */
	String getValue();
	
	/**
	 * Sets the work assignment.
	 * @param workAssignment work assignment
	 */
	void setAssignment(WorkAssignment workAssignment);
	
	/**
	 * Returns the work assignment.
	 * @return workAssignment work assignment
	 */
	WorkAssignment getAssignment();
	
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