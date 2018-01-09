/**
 * 
 */
package omis.separationneed.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.person.domain.Person;
import omis.relationship.domain.Relationship;


/**
 * Separation Need.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 21 2013)
 * @since OMIS 3.0
 */
public interface SeparationNeed 
	extends Creatable, Updatable {

	/**
	 * Returns the id.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Sets the id.
	 * 
	 * @param id the id to set
	 */
	void setId(Long id);
	
	/**
	 * Returns the relationship.
	 * 
	 * @return relationship
	 */
	Relationship getRelationship();
	
	/**
	 * Sets the relationship.
	 * 
	 * @param relationship relationship
	 */
	void setRelationship(Relationship relationship);
	
	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	Date getDate();
	
	/** 
	 * Sets the date.
	 * 
	 * @param date date
	 */
	void setDate(Date date);
	
	/**
	 * Returns whether confidential applies.
	 * 
	 * @return confidential
	 */
	Boolean getConfidential();
	
	/**
	 * Sets whether confidential applies.
	 * 
	 * @param confidential confidential
	 */
	void setConfidential(Boolean confidential);
	
	/**
	 * Returns reporting staff.
	 * 
	 * @return reporting staff
	 */
	Person getReportingStaff();
	
	/**
	 * Sets reporting staff.
	 * 
	 * @param reportingStaff reporting staff
	 */
	void setReportingStaff(Person reportingStaff);
	
	/**
	 * Returns the creation comment.
	 * 
	 * @return creation comment
	 */
	String getCreationComment();
	
	/**
	 * Sets the creation comment.
	 * 
	 * @param creationComment creation comment
	 */
	void setCreationComment(String creationComment);
	
	/**
	 * Returns the separation need removal.
	 * 
	 * @return separation need removal
	 */
	SeparationNeedRemoval getRemoval();
	
	/**
	 * Sets the separation need removal.
	 * 
	 * @param removal separation need removal
	 */
	void setRemoval(SeparationNeedRemoval removal);
	
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