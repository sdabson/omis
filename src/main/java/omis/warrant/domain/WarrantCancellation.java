package omis.warrant.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.warrant.domain.component.ClearSignature;

/**
 * WarrantCancellation.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public interface WarrantCancellation extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the WarrantCancellation
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the WarrantCancellation
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the Warrant for the WarrantCancellation
	 * @return warrant - Warrant
	 */
	public Warrant getWarrant();
	
	/**
	 * Sets the Warrant for the WarrantCancellation
	 * @param warrant - Warrant
	 */
	public void setWarrant(Warrant warrant);
	
	/**
	 * Returns the Date for the WarrantCancellation
	 * @return date - Date
	 */
	public Date getDate();
	
	/**
	 * Sets the Date for the WarrantCancellation
	 * @param date - Date
	 */
	public void setDate(Date date);
	
	/**
	 * Returns the Comment for the WarrantCancellation
	 * @return comment - String
	 */
	public String getComment();
	
	/**
	 * Sets the Comment for the WarrantCancellation
	 * @param comment - String
	 */
	public void setComment(String comment);
	
	/**
	 * Returns the ClearSignature for the WarrantCancellation
	 * @return clearSignature - ClearSignature
	 */
	public ClearSignature getClearSignature();
	
	/**
	 * Sets the ClearSignature for the WarrantCancellation
	 * @param clearSignature - ClearSignature
	 */
	public void setClearSignature(ClearSignature clearSignature);
	
	/** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	int hashCode();
}
