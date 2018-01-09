package omis.visitation.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;
import omis.relationship.domain.Relationship;
import omis.relationship.domain.RelationshipAssociable;

/**
 * Visitation Association.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 17, 2013)
 * @since OMIS 3.0
 */
public interface VisitationAssociation 
	extends RelationshipAssociable, Creatable, Updatable {

	/**
	 * Returns the id of the visitation association.
	 * @return id of the visitation association
	 */
	Long getId();

	/**
	 * Sets the id of the visitation association.
	 * @param id id of the visitation association
	 */
	void setId(Long id);
	
	/**
	 * Returns the relationship of the visitation association.
	 * @return relationship of the visitation association
	 */
	Relationship getRelationship();

	/**
	 * Sets the relationship of the visitation association.
	 * @param relationship relationship of the visitation association
	 */
	void setRelationship(Relationship relationship);
	
	/**
	 * Returns the visitation association flags.
	 * 
	 * @return visitation association flags
	 */
	VisitationAssociationFlags getFlags();
	
	/**
	 * Sets the visitation association flags.
	 * 
	 * @param flags visitation association flags
	 */
	void setFlags(VisitationAssociationFlags flags);
	
	/**
	 * Returns the visitation approval.
	 * 
	 * @return visitation approval
	 */
	VisitationApproval getApproval();
	
	/**
	 * Sets the visitation approval.
	 * 
	 * @param approval visitation approval
	 */
	void setApproval(VisitationApproval approval);
	
	/**
	 * Returns the category.
	 * 
	 * @return category
	 */
	VisitationAssociationCategory getCategory();
	
	/**
	 * Sets the category.
	 * 
	 * @param category category
	 */
	void setCategory(VisitationAssociationCategory category);
	
	/**
	 * Returns the notes.
	 * 
	 * @return notes
	 */
	String getNotes();
	
	/**
	 * Sets the notes.
	 * 
	 * @param notes notes
	 */
	void setNotes(String notes);
	
	/**
	 * Returns the date range.
	 *
	 */
	DateRange getDateRange();
	
	/**
	 * Sets the date range.
	 *
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns guardianship.
	 * 
	 * @return guardianship
	 */
	String getGuardianship();
	
	/**
	 * Sets guardianship.
	 * 
	 * @param guardianship guardianship
	 */
	void setGuardianship(String guardianship);
	
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