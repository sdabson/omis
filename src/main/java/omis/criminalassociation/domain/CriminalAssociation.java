package omis.criminalassociation.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.criminalassociation.domain.component.CriminalAssociationFlags;
import omis.datatype.DateRange;
import omis.relationship.domain.Relationship;
import omis.relationship.domain.RelationshipAssociable;

/**
 * Criminal Association.
 * 
 * @author Joel Norris 
 * @author Yidong Li
 * @version 0.1.0 (Apr, 14 2015)
 * @since OMIS 3.0
 */
public interface CriminalAssociation 
	extends RelationshipAssociable, Creatable, Updatable {
	
	/**
	 * Return the id of the criminal association.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Set the id of the criminal association.
	 * 
	 * @param id the id to set
	 */
	void setId(Long id);
	
	/**
	 * Return the relationship of the criminal association.
	 * 
	 * @return the relationship
	 */
	Relationship getRelationship();

	/**
	 * Set the relationship of the criminal association. 
	 * 
	 * @param relationship the relationship to set
	 */
	void setRelationship(Relationship relationship);

	/**
	 * Return the date range of the criminal association.
	 * 
	 * @return the dateRange
	 */
	DateRange getDateRange();

	/**
	 * Set the date range of the criminal association.
	 * 
	 * @param dateRange the dateRange to set
	 */
	void setDateRange(DateRange dateRange);

	/**
	 * Return the criminal association category.
	 * 
	 * @return the criminal association category
	 */
	CriminalAssociationCategory getCriminalAssociationCategory();

	/**
	 * Set the criminal association category.
	 * 
	 * @param criminalAssociationCategory the criminal association category
	 */
	void setCriminalAssociationCategory(CriminalAssociationCategory 
		criminalAssociationCategory);
	
	/**
	 * Return the criminal association flags.
	 * 
	 * @return criminalAssociationFlags criminal association category
	 */
	CriminalAssociationFlags getCriminalAssociationFlags();

	/**
	 * Set the criminal association flags.
	 * 
	 * @param criminalAssociationFlags criminal association flags
	 */
	void setCriminalAssociationFlags(
			CriminalAssociationFlags criminalAssociationFlags);
	
	
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