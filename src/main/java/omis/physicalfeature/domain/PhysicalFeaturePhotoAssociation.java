package omis.physicalfeature.domain;

import omis.personphoto.domain.PersonPhotoAssociation;

/**
 * Physical feature photo association.
 * @author Joel Norris
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public interface PhysicalFeaturePhotoAssociation 
	extends PersonPhotoAssociation {

	/**
	 * Returns the physical feature association of the physical feature photo.
	 * @return physicalFeatureAssociation physical feature association
	 */
	PhysicalFeatureAssociation getPhysicalFeatureAssociation();

	/**
	 * Sets the physical feature association of the physical feature photo.
	 * @param physicalFeatureAssociation physical feature association
	 */
	void setPhysicalFeatureAssociation(
			PhysicalFeatureAssociation physicalFeatureAssociation);
	
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