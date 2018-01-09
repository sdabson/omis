package omis.physicalfeature.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offender.domain.OffenderAssociable;

/**
 * Offender physical feature.
 * @author Joel Norris
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public interface PhysicalFeatureAssociation 
	extends OffenderAssociable, Creatable, Updatable {
	
	/**
	 * Returns the id of the offender physical feature.
	 * @return id Long
	 */
	Long getId();

	/**
	 * Sets the id of the offender physical feature.
	 * @param id id
	 */
	void setId(Long id);

	/**
	 * Returns the physical feature of the offender physical feature.
	 * @return feature PhysicalFeature
	 */
	PhysicalFeature getFeature();

	/**
	 * Sets the physical feature of the offender physical feature.
	 * @param feature physical feature
	 */
	void setFeature(PhysicalFeature feature);

	/**
	 * Returns the description of the offender physical feature.
	 * @return description String
	 */
	String getDescription();

	/**
	 * Sets the description of the offender physical feature.
	 * @param description description
	 */
	void setDescription(String description);
	
	/**
	 * Returns the origination date.
	 * 
	 * @return origination date
	 */
	Date getOriginationDate();
	
	/**
	 * Sets the origination date.
	 * 
	 * @param originationDate origination date
	 */
	void setOriginationDate(Date originationDate);
	
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
