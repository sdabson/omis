package omis.family.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;
import omis.family.domain.component.FamilyAssociationFlags;
import omis.relationship.domain.Relationship;
import omis.relationship.domain.RelationshipAssociable;

/** 
 * Family association.
 * 
 * @author Ryan Johns
 * @author Joel Norris.
 * @author Yidong Li.
 * @author Sheronda Vaughn
 * @author Josh Divine
 * @version 0.1.2 (Dec 13, 2017) 
 * @since OMIS 3.0 
 */
public interface FamilyAssociation 
			extends RelationshipAssociable, Creatable, Updatable {
	
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
	 * Returns family association flags.
	 * 
	 * @return family association flags
	 */
	FamilyAssociationFlags getFlags();
	
	/**
	 * Sets family association flags.
	 * 
	 * @param flags family association flags
	 */
	void setFlags(FamilyAssociationFlags flags);
	
	/**
	 * Return family association category.
	 * 
	 * @return category
	 */
	FamilyAssociationCategory getCategory();

	/**
	 * Sets the family association category.
	 * 
	 * @param category family association category
	 */
	void setCategory(FamilyAssociationCategory category);
	
	/**
	 * Return marriage date.
	 * 
	 * @return marriage date
	 */
	Date getMarriageDate();

	/**
	 * Sets marriage date.
	 * 
	 * @param marriageDate marriage date
	 */
	void setMarriageDate(Date marriageDate);
	
	/**
	 * Return divorce date.
	 * 
	 * @return divorce date
	 */
	Date getDivorceDate();

	/**
	 * Sets divorce date.
	 * 
	 * @param divorceDate divorce date
	 */
	void setDivorceDate(Date divorceDate);
	
	/**
	 * Return date range.
	 * 
	 * @return date range
	 */
	DateRange getDateRange();

	/**
	 * Sets date range.
	 * 
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);
	
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