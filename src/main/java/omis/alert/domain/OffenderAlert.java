package omis.alert.domain;

import java.util.Date;

import omis.alert.domain.component.AlertResolution;
import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offender.domain.OffenderAssociable;

/**
 * Offender alert.
 * 
 * @author Jason Nelson
 * @author Stephen Abson
 * @version 0.1.1 (Dec 11, 2013)
 * @since OMIS 3.0
 */
public interface OffenderAlert
		extends OffenderAssociable, Creatable, Updatable {
	
	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	Long getId();

	/**
	 * Sets the expire date.
	 * 
	 * @param expireDate expire date
	 */
	void setExpireDate(Date expireDate);
	
	/**
	 * Returns the expire date.
	 * 
	 * @return expireDate expire date
	 */
	Date getExpireDate();

	/**
	 * Sets the description.
	 * 
	 * @param description description
	 */
	void setDescription(String description);
	
	/**
	 * Returns the description.
	 * 
	 * @return description description
	 */
	String getDescription();
	
	/**
	 * Sets the resolution.
	 * 	
	 * @param resolution resolution 
	 */
	void setResolution(AlertResolution resolution);
	
	/**
	 * Returns the resolution.
	 * 
	 * @return resolution
	 */
	AlertResolution getResolution();
	
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