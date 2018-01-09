package omis.guardianship.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.Updatable;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.relationship.domain.Relationship;
import omis.relationship.domain.RelationshipAssociable;

/**
 * guardian.
 * @author Joel Norris
 * @version 0.1.0 (Sep 4, 2013)
 * @since OMIS 3.0
 */
public interface Guardianship 
	extends Creatable, Updatable, RelationshipAssociable {

	/**
	 * Return the id of the guardianship.
	 * @return id
	 */
	Long getId();

	/**
	 * Set the id of the guardianship.
	 * @param id id
	 */
	void setId(Long id);

	/**
	 * Return the relationship of the guardianship.
	 * @return relationship
	 */
	Relationship getRelationship();

	/**
	 * Set the relationship of the guardianship.
	 * @param relationship relationship
	 */
	void setRelationship(Relationship relationship);

	/**
	 * Return the date range of the guardianship.
	 * @return date range
	 */
	DateRange getDateRange();

	/**
	 * Set the date range of the guardianship.
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);

	/**
	 * Return the update signature of the guardianship.
	 * @return update signature
	 */
	UpdateSignature getUpdateSignature();

	/**
	 * Set the update signature of the guardianship.
	 * @param updateSignature update signature
	 */
	void setUpdateSignature(UpdateSignature updateSignature);

	/**
	 * Return the creation signature of the guardianship.
	 * @return creation signature
	 */
	CreationSignature getCreationSignature();

	/**
	 * Set the creation signature of the guardianship.
	 * @param creationSignature creation signature
	 */
	void setCreationSignature(CreationSignature creationSignature);
	
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
