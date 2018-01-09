package omis.relationship.domain;

import java.io.Serializable;

import omis.datatype.DateRange;

/**
 * Supports a relationship property.
 * @author Stephen Abson
 * @version 0.1.1 (March 7, 2013)
 * @since OMIS 3.0
 */
public interface RelationshipAssociable
		extends Serializable {

	/**
	 * Sets the relationship.
	 * @param relationship relationship
	 */
	void setRelationship(Relationship relationship);
	
	/**
	 * Returns the relationship.
	 * @return relationship
	 */
	Relationship getRelationship();
	
	/**
	 * Sets the range of dates during which the association to the
	 * relationship applies.
	 * @param dateRange range of dates during which association to
	 * relationship applies
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns the range of dates during which the association to the
	 * relationship applies.
	 * @return range of dates during which association to relationship
	 * applies
	 */
	DateRange getDateRange();
}