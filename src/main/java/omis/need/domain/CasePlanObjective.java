package omis.need.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offender.domain.OffenderAssociable;
import omis.person.domain.Person;

/**
 * Case plan objective.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 23, 2015)
 * @since OMIS 3.0
 */
public interface CasePlanObjective 
extends Creatable, Updatable, OffenderAssociable {

	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the identification date.
	 * 
	 * @return identification date
	 */
	Date getIdentificationDate();
	
	/**
	 * Sets the identification date.
	 * 
	 * @param identificationDate identification date
	 */
	void setIdentificationDate(Date identificationDate);
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	String getName();
	
	/**
	 * Sets the name.
	 * 
	 * @param name name
	 */
	void setName(String name);
	
	/**
	 * Returns the comment.
	 * 
	 * @return comment
	 */
	String getComment();
	
	/**
	 * Sets the comment.
	 * 
	 * @param comment comment
	 */
	void setComment(String comment);
	
	/**
	 * Returns the priority.
	 * 
	 * @return objective priority
	 */
	ObjectivePriority getPriority();
	
	/**
	 * Sets the priority.
	 * 
	 * @param priority objective priority
	 */
	void setPriority(ObjectivePriority priority);
	
	/**
	 * Returns the staff member.
	 * 
	 * @return staff member
	 */
	Person getStaffMember();
	
	/**
	 * Sets staff member.
	 * 
	 * @param staffMember
	 */
	void setStaffMember(Person staffMember);
	
	/**
	 * Returns the criminogenic domain.
	 * 
	 * @return criminogenic domain
	 */
	NeedDomain getDomain();
	
	/**
	 * Sets the criminogenic domain.
	 * 
	 * @param domain criminogenic domain
	 */
	void setDomain(NeedDomain domain);
	
	/**
	 * Returns the objective source.
	 * 
	 * @return objective source
	 */
	ObjectiveSource getSource();
	
	/**
	 * Sets the objective source.
	 * 
	 * @param source objective source
	 */
	void setSource(ObjectiveSource source);
	
	/**
	 * Returns whether offender agrees applies.
	 * 
	 * @return offender agrees
	 */
	Boolean getOffenderAgrees();
	
	/**
	 * Sets whether offender agrees applies.
	 * 
	 * @param offenderAgrees offender agrees
	 */
	void setOffenderAgrees(Boolean offenderAgrees);
	
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