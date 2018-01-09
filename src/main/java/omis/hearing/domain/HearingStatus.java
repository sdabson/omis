package omis.hearing.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * HearingStatus.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 18, 2017)
 *@since OMIS 3.0
 *
 */
public interface HearingStatus extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the HearingStatus
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the HearingStatus
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the Date for the HearingStatus
	 * @return date - Date
	 */
	public Date getDate();
	
	/**
	 * Sets the Date for the HearingStatus
	 * @param date - Date
	 */
	public void setDate(Date date);
	
	/**
	 * Returns the Description for the HearingStatus
	 * @return description - String
	 */
	public String getDescription();
	
	/**
	 * Sets the Description for the HearingStatus
	 * @param description - String
	 */
	public void setDescription(String description);
	
	/**
	 * Returns the Hearing for the HearingStatus
	 * @return hearing - Hearing
	 */
	public Hearing getHearing();
	
	/**
	 * Sets the Hearing for the HearingStatus
	 * @param hearing - Hearing
	 */
	public void setHearing(Hearing hearing);
	
	/**
	 * Returns the Category for the HearingStatus
	 * @return category - HearingStatusCategory
	 */
	public HearingStatusCategory getCategory();
	
	/**
	 * Sets the Category for the HearingStatus
	 * @param category - HearingStatusCategory
	 */
	public void setCategory(HearingStatusCategory category);
	
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
