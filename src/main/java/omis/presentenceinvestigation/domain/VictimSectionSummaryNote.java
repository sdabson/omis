package omis.presentenceinvestigation.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * VictimSectionSummaryNote.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public interface VictimSectionSummaryNote extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the VictimSectionSummaryNote
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the VictimSectionSummaryNote
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the description of the VictimSectionSummaryNote
	 * @return description - string
	 */
	public String getDescription();
	
	/**
	 * Sets the description of the VictimSectionSummaryNote
	 * @param description - String
	 */
	public void setDescription(String description);
	
	/**
	 * Returns the date of the VictimSectionSummaryNote
	 * @return date - Date
	 */
	public Date getDate();
	
	/**
	 * Sets the date of the VictimSectionSummaryNote
	 * @param date - Date
	 */
	public void setDate(Date date);
	
	/**
	 * Returns the victimSectionSummary of the VictimSectionSummaryNote
	 * @return victimSectionSummary - VictimSectionSummary
	 */
	public VictimSectionSummary getVictimSectionSummary();
	
	/**
	 * Sets the victimSectionSummary of the VictimSectionSummaryNote
	 * @param victimSectionSummary - VictimSectionSummary
	 */
	public void setVictimSectionSummary(
			VictimSectionSummary victimSectionSummary);
	
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
