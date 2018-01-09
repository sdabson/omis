package omis.warrant.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.facility.domain.Facility;
import omis.region.domain.County;
import omis.warrant.domain.component.ClearSignature;

/**
 * WarrantRelease.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public interface WarrantRelease extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the WarrantRelease
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the WarrantRelease
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the Warrant for the WarrantRelease
	 * @return warrant - Warrant
	 */
	public Warrant getWarrant();
	
	/**
	 * Sets the Warrant for the WarrantRelease
	 * @param warrant - Warrant
	 */
	public void setWarrant(Warrant warrant);
	
	/**
	 * Returns the Instructions for the WarrantRelease
	 * @return instructions - String
	 */
	public String getInstructions();
	
	/**
	 * Sets the Instructions for the WarrantRelease
	 * @param instructions - String
	 */
	public void setInstructions(String instructions);
	
	/**
	 * Returns the County for the WarrantRelease
	 * @return county - County
	 */
	public County getCounty();
	
	/**
	 * Sets the County for the WarrantRelease
	 * @param county - County
	 */
	public void setCounty(County county);
	
	/**
	 * Returns the Facility for the WarrantRelease
	 * @return facility - Facility
	 */
	public Facility getFacility();
	
	/**
	 * Sets the Facility for the WarrantRelease
	 * @param facility - Facility
	 */
	public void setFacility(Facility facility);
	
	/**
	 * Returns the ReleaseTimestamp for the WarrantRelease
	 * @return releaseTimestamp - Date
	 */
	public Date getReleaseTimestamp();
	
	/**
	 * Sets the ReleaseTimestamp for the WarrantRelease
	 * @param releaseTimestamp - Date
	 */
	public void setReleaseTimestamp(Date releaseTimestamp);
	
	/**
	 * Returns the Addressee for the WarrantRelease
	 * @return addressee - String
	 */
	public String getAddressee();
	
	/**
	 * Sets the Addressee for the WarrantRelease
	 * @param addressee - String
	 */
	public void setAddressee(String addressee);
	
	/**
	 * Returns the ClearSignature for the WarrantRelease
	 * @return clearSignature - ClearSignature
	 */
	public ClearSignature getClearSignature();
	
	/**
	 * Sets the ClearSignature for the WarrantRelease
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
