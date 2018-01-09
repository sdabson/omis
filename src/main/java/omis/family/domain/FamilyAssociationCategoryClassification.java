package omis.family.domain;

/** Family Relation.
 * @author Yidong Li
 * @author Stephen Abson
 * @author Joel Norris
 * @author Ryan Johns
 * @author Jason Nelson
 * @author Sheronda Vaughn
 * @version 0.1.1 (June 15, 2015)
 * @since OMIS 3.0
 */
public enum FamilyAssociationCategoryClassification {
	/**
	 * For father, mother, step father, and step mother categories. 
	 */
	PARENT,
	/**
	 * For son, daughter, step-son, and step-daughter categories.
	 */
	CHILD,
	/**
	 * For husband and wife categories.
	 */
	SPOUSE,
	/**
	 * For brother, sister, step-brother, and step-sister, 
	 * half-brother, half-sister categories.
	 */
	SIBLING,
	/**
	 * For aunt, uncle, cousin, and grandparent categories.
	 */
	EXTENDED_FAMILY,
	/**
	 * For fiance, fiancee categories.
	 */
	FIANCE;
	
	/**
	 * Return the name of the family association category classification.
	 * @return string
	 */
	public String getName() { 
		return this.name(); 
	}
}