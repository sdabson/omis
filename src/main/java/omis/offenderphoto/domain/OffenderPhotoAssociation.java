package omis.offenderphoto.domain;

import omis.personphoto.domain.PersonPhotoAssociation;

/**
 * Association of offender to photo.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 23, 2014)
 * @since OMIS 3.0
 */
public interface OffenderPhotoAssociation
		extends PersonPhotoAssociation {

	/**
	 * Sets whether {@code this} associates the profile (mug shot) photo of
	 * the offender.
	 * 
	 * @param profile whether {@code this} associates the profile (mug shot)
	 * photo of the offender
	 */
	void setProfile(Boolean profile);
	
	/**
	 * Returns whether {@code this} associations the profile (mug shot)
	 * photo of the offender.
	 * 
	 * @return whether {@code this} associations the profile (mug shot)
	 * photo of the offender
	 */
	Boolean getProfile();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
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
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}